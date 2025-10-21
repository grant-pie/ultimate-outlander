<script setup>
import { computed, onMounted, ref, watch } from 'vue'
import ItemsPricingTable from './components/ItemPricingTable.vue';
import ContainerContentsTable from './components/ContainerContentsTable.vue';
import ItemTypes from "./data/item_types.json";

const journalInput = ref('');
const statusMessage = ref('');
const isDragging = ref(false);
const items = ref([]);
const containers = ref([]);

const isProcessing = ref(false);
const processingStage = ref('');
const processingProgress = ref(0);

const showFaq = ref(false);

const hasData = computed(() => items.value.length > 0 || containers.value.length > 0);


const VALIDATION_RULES = {
  MAX_FILE_SIZE: 10 * 1024 * 1024, // 10MB
  ALLOWED_EXTENSIONS: ['.txt', '.json'],
  ALLOWED_MIME_TYPES: ['text/plain', 'application/json'],
  MAX_TEXT_LENGTH: 5 * 1024 * 1024, // 5MB of text
};

// Uncomment the follwoing watch statement to enable local storage backup
// You also might want to enable the corresponding code in onMounted to restore from backup
// This is commented out by default as it may not be desired behavior for all users.
/*
watch([items, containers], () => {
  try {
    localStorage.setItem('items_backup', JSON.stringify(items.value));
    console.log('Saved items:', items.value);
    localStorage.setItem('containers_backup', JSON.stringify(containers.value));
  } catch (e) {
    console.warn('Failed to save backup:', e);
  }
}, { deep: true });
*/

function validatePrice(price) {
  const errors = [];
  
  if (price === null || price === undefined) {
    errors.push('Price is required');
    return { valid: false, errors };
  }
  
  let numPrice = price;
  if (typeof price === 'string') {
    numPrice = parseFloat(price);
  }
  
  if (typeof numPrice !== 'number' || isNaN(numPrice)) {
    errors.push('Price must be a valid number');
    return { valid: false, errors };
  }
  
  if (!isFinite(numPrice)) {
    errors.push('Price must be a finite number');
  }
  
  if (numPrice < 0) {
    errors.push('Price cannot be negative');
  }
  
  if (numPrice > 1000000000) {
    errors.push('Price too large (maximum 1,000,000,000)');
  }
  
  const decimalPlaces = (numPrice.toString().split('.')[1] || '').length;
  if (decimalPlaces > 2) {
    errors.push('Price can have at most 2 decimal places');
  }
  
  return {
    valid: errors.length === 0,
    errors,
    value: numPrice
  };
}

function validateFile(file) {
  const errors = [];
  
  if (!file) {
    errors.push('No file provided');
    return { valid: false, errors };
  }
  
  if (file.size === 0) {
    errors.push('File is empty');
  }
  
  if (file.size > VALIDATION_RULES.MAX_FILE_SIZE) {
    const sizeMB = (VALIDATION_RULES.MAX_FILE_SIZE / (1024 * 1024)).toFixed(0);
    errors.push(`File too large. Maximum size is ${sizeMB}MB`);
  }
  
  const fileName = file.name.toLowerCase();
  const hasValidExtension = VALIDATION_RULES.ALLOWED_EXTENSIONS.some(ext => 
    fileName.endsWith(ext)
  );
  
  if (!hasValidExtension) {
    errors.push(`Invalid file type. Allowed types: ${VALIDATION_RULES.ALLOWED_EXTENSIONS.join(', ')}`);
  }
  
  if (file.type && !VALIDATION_RULES.ALLOWED_MIME_TYPES.includes(file.type) && !file.type.startsWith('text/')) {
    errors.push('File must be a text-based file');
  }
  
  return {
    valid: errors.length === 0,
    errors
  };
}

function validateTextInput(text) {
  const errors = [];
  
  if (!text || typeof text !== 'string') {
    errors.push('Input must be text');
    return { valid: false, errors };
  }
  
  const trimmedText = text.trim();
  
  if (trimmedText === '') {
    errors.push('Input cannot be empty');
    return { valid: false, errors };
  }
  
  if (trimmedText.length < 10) {
    errors.push('Input too short. Please paste complete journal log or JSON data');
  }
  
  if (trimmedText.length > VALIDATION_RULES.MAX_TEXT_LENGTH) {
    errors.push('Input too large to process');
  }
  
  const suspiciousPatterns = [
    /<script[^>]*>.*?<\/script>/gi,
    /javascript:/gi,
    /\bon(abort|blur|change|click|dblclick|error|focus|keydown|keypress|keyup|load|mousedown|mousemove|mouseout|mouseover|mouseup|reset|resize|select|submit|unload)\s*=/gi,
  ];
  
  for (const pattern of suspiciousPatterns) {
    if (pattern.test(trimmedText)) {
      errors.push('Input contains potentially malicious content.');
      break;
    }
  }
  
  return {
    valid: errors.length === 0,
    errors,
    data: trimmedText
  };
}

function validateJSONInput(jsonString, requiredProps = []) {
  try {
    const textValidation = validateTextInput(jsonString);
    if (!textValidation.valid) {
      return textValidation;
    }
    
    const trimmed = jsonString.trim();
    if (!trimmed.startsWith('[') && !trimmed.startsWith('{')) {
      return { 
        valid: false, 
        errors: ['Input does not appear to be valid JSON (must start with [ or {)'] 
      };
    }
    
    let data;
    try {
      data = JSON.parse(jsonString);
    } catch (parseError) {
      return { 
        valid: false, 
        errors: [`Invalid JSON format: ${parseError.message}`] 
      };
    }
    
    if (!Array.isArray(data)) {
      return { 
        valid: false, 
        errors: ['JSON must contain an array of items'] 
      };
    }
    
    if (data.length === 0) {
      return { 
        valid: false, 
        errors: ['JSON array is empty'] 
      };
    }
    
    if (data.length > 10000) {
      return { 
        valid: false, 
        errors: ['Too many items in JSON (maximum 10,000)'] 
      };
    }
    
    const errors = [];
    for (let i = 0; i < data.length; i++) {
      const item = data[i];
      
      if (typeof item !== 'object' || item === null) {
        errors.push(`Item at index ${i} is not an object`);
        continue;
      }
      
      for (const prop of requiredProps) {
        if (!item.hasOwnProperty(prop)) {
          errors.push(`Item at index ${i} missing required property: "${prop}"`);
        }
      }
      
      if (item.hasOwnProperty('description')) {
        if (typeof item.description !== 'string' || item.description.trim() === '') {
          errors.push(`Item at index ${i} has invalid description`);
        }
        if (item.description.length > 500) {
          errors.push(`Item at index ${i} description too long (max 500 characters)`);
        }
      }
      
      if (item.hasOwnProperty('price')) {
        const priceValidation = validatePrice(item.price);
        if (!priceValidation.valid) {
          errors.push(`Item at index ${i}: ${priceValidation.errors.join(', ')}`);
        }
      }
      
      if (item.hasOwnProperty('id')) {
        if (typeof item.id !== 'string' && typeof item.id !== 'number') {
          errors.push(`Item at index ${i} has invalid id type`);
        }
      }
    }
    
    if (errors.length > 10) {
      errors.splice(10);
      errors.push(`...and ${errors.length - 10} more errors`);
    }
    
    return {
      valid: errors.length === 0,
      errors,
      data
    };
    
  } catch (error) {
    return { 
      valid: false, 
      errors: [`Validation error: ${error.message}`] 
    };
  }
}

function validateCatalogLog(logText) {
  const errors = [];
  
  const textValidation = validateTextInput(logText);
  if (!textValidation.valid) {
    return textValidation;
  }
  
  if (!logText.includes('[Razor]:')) {
    errors.push('Log does not appear to be from Razor (missing [Razor]: markers)');
  }
  
  if (!logText.includes('Now we catalog items...')) {
    errors.push('Log does not contain catalog start marker');
  }
  
  if (!logText.includes('Cataloguing done.')) {
    errors.push('Log does not contain catalog end marker');
  }
  
  const lines = logText.split('\n');
  if (lines.length < 5) {
    errors.push('Log appears incomplete (too few lines)');
  }
  
  return {
    valid: errors.length === 0,
    errors,
    data: logText
  };
}

function validateItemId(id) {
  const errors = [];
  
  if (!id) {
    errors.push('Item ID is required');
    return { valid: false, errors };
  }
  
  const idStr = String(id);
  
  if (!/^\d+$/.test(idStr)) {
    errors.push('Item ID must contain only digits');
  }
  
  if (idStr.length < 1 || idStr.length > 10) {
    errors.push('Item ID has invalid length');
  }
  
  return {
    valid: errors.length === 0,
    errors,
    value: idStr
  };
}

function validateContainerSerial(serial) {
  const errors = [];
  
  if (!serial) {
    errors.push('Container serial is required');
    return { valid: false, errors };
  }
  
  const serialStr = String(serial);
  
  if (!/^\d{10}$/.test(serialStr)) {
    errors.push('Container serial must be exactly 10 digits');
  }
  
  try {
    const hexValue = parseInt(serialStr).toString(16);
    if (hexValue === 'NaN') {
      errors.push('Invalid serial number');
    }
  } catch (e) {
    errors.push('Cannot convert serial to hex');
  }
  
  return {
    valid: errors.length === 0,
    errors,
    value: serialStr
  };
}

function handleDragOver(e) {
  isDragging.value = true;
}

function handleDragEnter(e) {
  isDragging.value = true;
}

function handleDragLeave(e) {
  isDragging.value = false;
}

function handleDrop(e) {
  try {
    isDragging.value = false;
    isProcessing.value = true;
    processingStage.value = 'Reading file...';
    
    const files = e.dataTransfer?.files;
    
    if (!files || files.length === 0) {
      statusMessage.value = 'No file dropped';
      isProcessing.value = false;
      return;
    }
    
    const file = files[0];
    
    const validation = validateFile(file);
    if (!validation.valid) {
      statusMessage.value = validation.errors.join('. ');
      isProcessing.value = false;
      return;
    }
    
    const reader = new FileReader();
    
    reader.onload = (event) => {
      try {
        const content = event.target?.result;
        
        if (!content) {
          statusMessage.value = 'Failed to read file contents';
          isProcessing.value = false;
          return;
        }
        
        if (content.length > VALIDATION_RULES.MAX_TEXT_LENGTH) {
          statusMessage.value = 'File content too large to process';
          isProcessing.value = false;
          return;
        }
        
        if (typeof content !== 'string') {
          statusMessage.value = 'File content is not valid text';
          isProcessing.value = false;
          return;
        }
        
        journalInput.value = content;
        statusMessage.value = `File "${file.name}" loaded successfully (${(file.size / 1024).toFixed(1)} KB)`;
        isProcessing.value = false;
        processingStage.value = '';
        
      } catch (error) {
        console.error('Error processing file contents:', error);
        statusMessage.value = `Error processing file: ${error.message}`;
        isProcessing.value = false;
        processingStage.value = '';
      }
    };
    
    reader.onerror = (error) => {
      console.error('FileReader error:', error);
      statusMessage.value = `Error reading file: ${reader.error?.message || 'Unknown error'}`;
      isProcessing.value = false;
      processingStage.value = '';
    };
    
    reader.readAsText(file);
    
  } catch (error) {
    console.error('Error in handleDrop:', error);
    statusMessage.value = `Error handling file drop: ${error.message}`;
    isProcessing.value = false;
    processingStage.value = '';
  }
}

function parseId(logData) {
  const idMatch = logData.match(/\[Razor\]: (\d{10})/);
  return idMatch ? idMatch[1] : null;
}

function parseContainerDate(logData) {
  const dateMatch = logData.match(/\[(\d{2}\/\d{2}\/\d{4})/);
  return dateMatch ? dateMatch[1] : null;
}

function parseContainerTime(logData) {
  const timeMatch = logData.match(/\[\d{2}\/\d{2}\/\d{4} (\d{2}:\d{2})\]/);
  return timeMatch ? timeMatch[1] : null;
}

function parseItems(logData) {
  try {
    if (!logData || typeof logData !== 'string') {
      console.warn('Invalid log data provided to parseItems');
      return [];
    }
    
    const lines = logData.split('\n');
    const parsedItems = [];
    let cataloging = false;

    for (let i = 0; i < lines.length; i++) {
      try {
        const line = lines[i];

        if (line.includes('Now we catalog items...')) {
          cataloging = true;
          continue;
        }

        if (line.includes('Cataloguing done.')) {
          break;
        }

        if (cataloging && line.includes('[Razor]:')) {
          const description = line.split('[Razor]: ')[1]?.trim();
          
          if (!description) continue;
          
          if (description.includes('Added') || 
              description.includes('Ignore List cleared') || 
              description.includes('Finished')) {
            continue;
          }

          for (let j = i + 1; j < lines.length; j++) {
            const lookAheadLine = lines[j];
            
            if (lookAheadLine.includes('System:')) {
              continue;
            }
            
            if (lookAheadLine.includes('Added') && 
              lookAheadLine.includes('to ignore list')) {
              const idMatch = lookAheadLine.match(/Added (\d+) to/);
              if (idMatch && idMatch[1]) {
                parsedItems.push({
                  description,
                  id: idMatch[1],
                });
              }
              break;
            }
            
            if (lookAheadLine.includes('[Razor]:')) {
              break;
            }
          }
        }
      } catch (lineError) {
        console.warn(`Error processing line ${i}:`, lineError);
        continue;
      }
    }

    return parsedItems;
    
  } catch (error) {
    console.error('Error in parseItems:', error);
    return [];
  }
}

function extractAllChestLogs(content, startingRegex, endingRegex) {
  try {
    if (!content || typeof content !== 'string') {
      console.warn('Invalid content provided to extractAllChestLogs');
      return [];
    }
    
    if (!(startingRegex instanceof RegExp) || !(endingRegex instanceof RegExp)) {
      console.error('Invalid regex patterns provided');
      return [];
    }
    
    const blocks = [];
    let remainingContent = content;
    let startIndex;
    let iterationCount = 0;
    const MAX_ITERATIONS = 1000;

    while ((startIndex = remainingContent.search(startingRegex)) !== -1) {
      if (++iterationCount > MAX_ITERATIONS) {
        console.error('Maximum iterations exceeded in extractAllChestLogs');
        break;
      }
      
      const blockStartIndex = startIndex;
      const afterStartContent = remainingContent.slice(blockStartIndex);
      const endMatch = afterStartContent.match(endingRegex);
      
      if (!endMatch) break;

      const blockEndIndex = blockStartIndex + endMatch.index + endMatch[0].length;
      const withinBlockContent = remainingContent.slice(blockStartIndex + 1, blockEndIndex);
      const nextStartMatch = withinBlockContent.match(startingRegex);
      
      if (nextStartMatch) {
        remainingContent = remainingContent.slice(blockStartIndex + 1);
        continue;
      }

      const block = remainingContent.slice(blockStartIndex, blockEndIndex);
      blocks.push(block);
      remainingContent = remainingContent.slice(blockEndIndex);
    }

    return blocks;
    
  } catch (error) {
    console.error('Error in extractAllChestLogs:', error);
    return [];
  }
}

async function parseInput() {
  try {
    isProcessing.value = true;
    processingProgress.value = 0;
    processingStage.value = 'Validating input...';
    
    // Simulate async delay for UI feedback
    await new Promise(resolve => setTimeout(resolve, 100));
    
    const textValidation = validateTextInput(journalInput.value);
    if (!textValidation.valid) {
      statusMessage.value = textValidation.errors.join('. ');
      isProcessing.value = false;
      processingStage.value = '';
      return false;
    }
    
    processingProgress.value = 10;
    const cleanInput = textValidation.data;
    
    // Try parsing as JSON
    processingStage.value = 'Checking for JSON format...';
    await new Promise(resolve => setTimeout(resolve, 50));
    
    const requiredProps = ["description", "price"];
    const jsonValidation = validateJSONInput(cleanInput, requiredProps);
    
    if (jsonValidation.valid) {
      processingStage.value = 'Importing items...';
      processingProgress.value = 50;
      await new Promise(resolve => setTimeout(resolve, 100));
      
      items.value = jsonValidation.data;
      
      processingProgress.value = 80;
      if (containers.value.length > 0) {
        processingStage.value = 'Updating container prices...';
        await new Promise(resolve => setTimeout(resolve, 50));
        updateContainerContentsPrices();
      }
      
      processingProgress.value = 100;
      statusMessage.value = `Successfully imported ${jsonValidation.data.length} items`;
      
      // Reset loading state after brief delay
      setTimeout(() => {
        isProcessing.value = false;
        processingStage.value = '';
      }, 500);
      
      return true;
    }
    
    // Try parsing as catalog log
    processingStage.value = 'Checking for catalog log format...';
    processingProgress.value = 20;
    await new Promise(resolve => setTimeout(resolve, 50));
    
    const catalogValidation = validateCatalogLog(cleanInput);
    
    if (catalogValidation.valid) {
      containers.value = [];
      processingStage.value = "Extracting catalog data...";
      processingProgress.value = 30;
      await new Promise(resolve => setTimeout(resolve, 100));
      
      const startingRegex = /Now we catalog items\.\.\./;
      const endingRegex = /Cataloguing done\./;
      const chestLogs = extractAllChestLogs(cleanInput, startingRegex, endingRegex);
      
      if (chestLogs.length === 0) {
        statusMessage.value = "No valid chest catalogs found.";
        isProcessing.value = false;
        processingStage.value = '';
        return false;
      }
      
      processingStage.value = `Processing ${chestLogs.length} container(s)...`;
      processingProgress.value = 40;
      
      let containerId = 1;
      let totalItems = 0;
      const totalLogs = chestLogs.length;
      
      for (let logIndex = 0; logIndex < chestLogs.length; logIndex++) {
        const chestLog = chestLogs[logIndex];
        
        // Update progress for each container
        processingProgress.value = 40 + ((logIndex / totalLogs) * 50);
        processingStage.value = `Processing container ${logIndex + 1} of ${totalLogs}...`;
        await new Promise(resolve => setTimeout(resolve, 50));
        
        try {
          const parsedItems = parseItems(chestLog);
          const contents = [];
          
          parsedItems.forEach((parsedItem) => {
            const idValidation = validateItemId(parsedItem.id);
            if (!idValidation.valid) {
              console.warn(`Invalid item ID: ${parsedItem.id}`, idValidation.errors);
              return;
            }
            
            if (parsedItems.length > 0) {
              const existingItem = items.value.find(
                item => item.description === parsedItem.description
              );
              
              let item;
              if (existingItem) {
                item = {
                  description: parsedItem.description,
                  type: existingItem.type,
                  price: existingItem.price,
                };
              } else {
 
                item = {
                  description: parsedItem.description,
                  type: getItemType(parsedItem.description),
                  price: 0,
                };

                items.value.push(item);
              }

              const itemCopy = structuredClone(item);
              itemCopy.id = idValidation.value;              
              contents.push({ item: itemCopy });
              totalItems++;
            }
          });
          
          const containerSerial = parseId(chestLog);
          const serialValidation = validateContainerSerial(containerSerial);
          
          if (!serialValidation.valid) {
            console.warn(`Invalid container serial: ${containerSerial}`, serialValidation.errors);
          }
          
          const container = {
            id: containerId++,
            serial: serialValidation.valid ? serialValidation.value : containerSerial,
            date_updated: parseContainerDate(chestLog),
            time_updated: parseContainerTime(chestLog),
            contents: contents,
            show: true
          };
          
          containers.value.push(container);
          
        } catch (chestError) {
          console.error('Error parsing chest log:', chestError);
        }
      }
      
      processingProgress.value = 100;
      statusMessage.value = `Successfully imported ${containers.value.length} container(s) with ${totalItems} items`;
      
      // Reset loading state after brief delay
      setTimeout(() => {
        isProcessing.value = false;
        processingStage.value = '';
      }, 500);
      
      return true;
    }
    
    // Neither format is valid
    statusMessage.value = `Unrecognized input format. ${jsonValidation.errors[0] || catalogValidation.errors[0] || 'Please paste valid JSON or catalog log.'}`;
    isProcessing.value = false;
    processingStage.value = '';
    return false;
    
  } catch (error) {
    console.error('Unexpected error in parseInput:', error);
    statusMessage.value = `Unexpected error: ${error.message}`;
    isProcessing.value = false;
    processingStage.value = '';
    return false;
  }
}

function downloadJSON(jsonObject, fileName) {
  const jsonString = JSON.stringify(jsonObject, null, 2);
  const blob = new Blob([jsonString], { type: "application/json" });
  const link = document.createElement("a");
  link.download = fileName;
  link.href = URL.createObjectURL(blob);
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  URL.revokeObjectURL(link.href);
}

function getItemType(itemDescription) {
  for (const [type, itemsOfType] of Object.entries(ItemTypes)) {
    if (itemsOfType.some(item => itemDescription.includes(item))) {
      return type;
    }
  }
  return 'unknown';
}

function generateCatalogLootScript() {
  return `
  setvar container_to_catalog

  wait 250
  
  setvar take_container

  wait 250

  if not listexists item_ids

  createlist item_ids

  else

  clearlist item_ids


  endif
    
  # SET ITEM's TO CATALOG HERE #
  
  # Comment out any item types that you do not want to catalog.
  # You can add other item types here as well, note that they have not been tested yet.
  # Phylacteries
  pushlist item_ids "Phylactery"    
  # Skill Mastery Scrolls  
  pushlist item_ids "scroll of calling"
  # Aspect Cores
  pushlist item_ids "aspect core"
  pushlist item_ids "chroma core"
  # Aspect Distillations 
  pushlist item_ids "flask"
  pushlist item_ids "chroma distil"
  # Maps
  pushlist item_ids "map"
    
  //split items
  sysmsg "First we split item stacks..."
  wait 250
  sysmsg container_to_catalog
  wait 250
  //split items
  foreach id in item_ids

      while findtype id container_to_catalog as item
          
        lift item 1
        
        wait 500
          
        drop take_container
        
        wait 500
        
        ignore item
      endwhile

  endfor
  clearignore
  
  //catalog items
  sysmsg "Now we catalog items..."
  wait 250
  sysmsg take_container
  wait 250
  foreach id in item_ids

  while findtype id take_container as item

          getlabel item description

          sysmsg description

          wait 250
          
          ignore item
          
      endwhile

  endfor
  
  clearignore
  
  sysmsg "Cataloguing done."
  
  wait 250
  
  sysmsg "You can now log out to create a journal entry. Finds your journal logs here: Game Folder\ClassicUO\Data\Client\JournalLogs"
  `;
  
}

function copyCatalogLootScript() {
  const script = generateCatalogLootScript();
  navigator.clipboard.writeText(script).then(() => {
    const copyScriptBtn = document.getElementById('copyCatalogLootScriptBtn');
    if (copyScriptBtn) {
      const originalHTML = copyScriptBtn.innerHTML; // Store original HTML
      copyScriptBtn.innerHTML = '<span aria-hidden="true">✓</span> Copied!';
      window.setTimeout(() => {
        const btn = document.getElementById('copyCatalogLootScriptBtn');
        if (btn) btn.innerHTML = originalHTML; // Restore original HTML
      }, 2000);
    }
  });
}

function generateCatalogEquipmentScript() {
  return `
  setvar container_to_catalog

  wait 250
  
  setvar take_container

  wait 250
    
  
if not listexists equipment_ids

    createlist equipment_ids

else

    clearlist equipment_ids

endif


pushlist equipment_ids "leather armor"
pushlist equipment_ids "leather sleeves"
pushlist equipment_ids "leather tunic"
pushlist equipment_ids "leather Gloves"
pushlist equipment_ids "leather gorget"
pushlist equipment_ids "leather cap"
pushlist equipment_ids "leather bustier"
pushlist equipment_ids "leather leggings"
pushlist equipment_ids "leather shorts"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "bone arms"
pushlist equipment_ids "bone armor"
pushlist equipment_ids "bone gloves"
pushlist equipment_ids "studded gorget"
pushlist equipment_ids "bone gorget"
pushlist equipment_ids "bone helmet"
pushlist equipment_ids "bone legs"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "leather jerkin"
pushlist equipment_ids "bone skirt"
pushlist equipment_ids "studded armor"
pushlist equipment_ids "studded sleeves"
pushlist equipment_ids "studded gloves"
pushlist equipment_ids "studded gorget"
pushlist equipment_ids "leather cap"
pushlist equipment_ids "studded cap"
pushlist equipment_ids "studded leggings"
pushlist equipment_ids "studded tunic"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "studded skirt"
pushlist equipment_ids "studded bustier"
pushlist equipment_ids "orc helm"
pushlist equipment_ids "ringmail sleeves"
pushlist equipment_ids "ringmail tunic"
pushlist equipment_ids "ringmail gloves"
pushlist equipment_ids "studded gorget"
pushlist equipment_ids "ringmail gorget"
pushlist equipment_ids "helmet"
pushlist equipment_ids "ringmail leggings"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "ringmail skirt"
pushlist equipment_ids "ringmail sleeves"
pushlist equipment_ids "chain arms"
pushlist equipment_ids "chainmail tunic"
pushlist equipment_ids "ringmail gloves"
pushlist equipment_ids "chain gloves"
pushlist equipment_ids "leather gorget"
pushlist equipment_ids "chain gorget"
pushlist equipment_ids "chainmail coif"
pushlist equipment_ids "chainmail leggings"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "chain skirt"
pushlist equipment_ids "platemail arms"
pushlist equipment_ids "norse helm"
pushlist equipment_ids "close helm"
pushlist equipment_ids "plate armor"
pushlist equipment_ids "platemail gloves"
pushlist equipment_ids "platemail gorget"
pushlist equipment_ids "plate helm"
pushlist equipment_ids "platemail legs"
pushlist equipment_ids "platemail"
pushlist equipment_ids "bascinet"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "plate skirt"

pushlist equipment_ids "abyssal staff"
pushlist equipment_ids "ancestral staff"
pushlist equipment_ids "angelspire staff"
pushlist equipment_ids "boneshard staff"
pushlist equipment_ids "burial staff"
pushlist equipment_ids "celestial staff"
pushlist equipment_ids "chaos staff"
pushlist equipment_ids "darklight staff"
pushlist equipment_ids "dragonbone staff"
pushlist equipment_ids "druid staff"
pushlist equipment_ids "fiendish staff"
pushlist equipment_ids "helix staff"
pushlist equipment_ids "nautilus staff"
pushlist equipment_ids "necromancer staff"
pushlist equipment_ids "paradox staff"
pushlist equipment_ids "prismatic staff"
pushlist equipment_ids "razorcrest staff"
pushlist equipment_ids "shaman staff"
pushlist equipment_ids "summoner staff"
pushlist equipment_ids "vine staff"

pushlist equipment_ids "balestra"
pushlist equipment_ids "bow"
pushlist equipment_ids "crossbow"
pushlist equipment_ids "long bow"
pushlist equipment_ids "heavy crossbow"
pushlist equipment_ids "composite bow"
pushlist equipment_ids "stubnose crossbow"
pushlist equipment_ids "tower guard crossbow"
pushlist equipment_ids "recurve bow"

pushlist equipment_ids "assassins dagger"
pushlist equipment_ids "dagger"
pushlist equipment_ids "epee"
pushlist equipment_ids "sabre"
pushlist equipment_ids "tracker knife"
pushlist equipment_ids "kryss"
pushlist equipment_ids "kukri"
pushlist equipment_ids "pitchfork"
pushlist equipment_ids "rapier"
pushlist equipment_ids "sarissa"
pushlist equipment_ids "short spear"
pushlist equipment_ids "Long Spear"
pushlist equipment_ids "war fork"

pushlist equipment_ids "bamboo flute"
pushlist equipment_ids "drum"
pushlist equipment_ids "gemshorn"
pushlist equipment_ids "lap harp"
pushlist equipment_ids "hurdy gurdy"
pushlist equipment_ids "lute"
pushlist equipment_ids "psaltery"
pushlist equipment_ids "sackbut"
pushlist equipment_ids "shawm"
pushlist equipment_ids "tambourine"
pushlist equipment_ids "vielle"

pushlist equipment_ids "black staff"
pushlist equipment_ids "club"
pushlist equipment_ids "cudgel"
pushlist equipment_ids "flail"
pushlist equipment_ids "flanged mace"
pushlist equipment_ids "giant club"
pushlist equipment_ids "giant spiked club"
pushlist equipment_ids "gnarled staff"
pushlist equipment_ids "great mace"
pushlist equipment_ids "sledgehammer"
pushlist equipment_ids "hammer pick"
pushlist equipment_ids "mace"
pushlist equipment_ids "maul"
pushlist equipment_ids "quarter staff"
pushlist equipment_ids "shepherd's crook"
pushlist equipment_ids "skull club"
pushlist equipment_ids "war axe"
pushlist equipment_ids "war hammer"
pushlist equipment_ids "war mace"

pushlist equipment_ids "spellbook"
pushlist equipment_ids "wand"

pushlist equipment_ids "bone shield"
pushlist equipment_ids "bronze shield"
pushlist equipment_ids "buckler"
pushlist equipment_ids "Chaos shield"
pushlist equipment_ids "heater shield"
pushlist equipment_ids "kite shield"
pushlist equipment_ids "metal shield"
pushlist equipment_ids "Order shield"
pushlist equipment_ids "parrying gauche"
pushlist equipment_ids "wooden kite shield"
pushlist equipment_ids "wooden shield"

pushlist equipment_ids "axe"
pushlist equipment_ids "bardiche"
pushlist equipment_ids "battle axe"
pushlist equipment_ids "broadsword"
pushlist equipment_ids "cutlass"
pushlist equipment_ids "double axe"
pushlist equipment_ids "executioner's axe"
pushlist equipment_ids "giant axe"
pushlist equipment_ids "halberd"
pushlist equipment_ids "katana"
pushlist equipment_ids "large battle axe"
pushlist equipment_ids "longsword"
pushlist equipment_ids "norse axe"
pushlist equipment_ids "scimitar"
pushlist equipment_ids "two handed axe"
pushlist equipment_ids "viking sword"
pushlist equipment_ids "zweihander"

pushlist equipment_ids "kryss"
pushlist equipment_ids "viking sword"
pushlist equipment_ids "club"
pushlist equipment_ids "bow"

pushlist equipment_ids "caesti"
pushlist equipment_ids "fistblade"
pushlist equipment_ids "martial arts scroll"

pushlist equipment_ids "harpoon"
pushlist equipment_ids "trident"
pushlist equipment_ids "buckler "
    
  # SET ITEMS TO CATALOG HERE #
  # Comment out any item types that you do not want to catalog.
  
    
  //split items
  sysmsg "Now we catalog items..."
  wait 250
  sysmsg container_to_catalog
  wait 250
  //split items
  foreach id in equipment_ids

      while findtype id container_to_catalog as item
      
        getlabel item description
        
        sysmsg description
          
        lift item 1
        
        wait 250
          
        drop take_container
        
        wait 250
        
        ignore item
      endwhile

  endfor
  clearignore
  
  clearignore
  
  sysmsg "Cataloguing done."
  
  wait 250
  
  sysmsg "You can now log out to create a journal entry. Finds your journal logs here: Game FolderClassicUODataClientJournalLogs"`; // Your existing script
  
}

function copyCatalogEquipmentScript() {
  const script = generateCatalogEquipmentScript();
  navigator.clipboard.writeText(script).then(() => {
    const copyScriptBtn = document.getElementById('copyCatalogEquipmentScriptBtn');
    if (copyScriptBtn) {
      const originalHTML = copyScriptBtn.innerHTML; // Store original HTML
      copyScriptBtn.innerHTML = '<span aria-hidden="true">✓</span> Copied!';
      window.setTimeout(() => {
        const btn = document.getElementById('copyCatalogEquipmentScriptBtn');
        if (btn) btn.innerHTML = originalHTML; // Restore original HTML
      }, 2000);
    }
  });
}

function removeContainer(containerId) {
  if (confirm('Are you sure you want to remove this container? This action cannot be undone.')) {
    containers.value = containers.value.filter(container => container.id !== containerId);
    statusMessage.value = 'Container removed successfully';
  }
}

function removeItemFromContainer(containerId, itemID) {
  if (confirm('Are you sure you want to remove this item from the container?')) {
    const container = containers.value.find(c => c.id === containerId);
    if (container) {
      container.contents = container.contents.filter(content => content.item.id !== itemID);
      statusMessage.value = 'Item removed successfully';
    }
  }
}

function updateContainerContentsPrices(){
  if (containers.value.length > 0) {   
    // Create a lookup map for better performance
    const itemsMap = new Map(items.value.map(item => [item.description, item]));
    
    containers.value.forEach((container) => {
      container.contents.forEach((content) => {
        const matchingItem = itemsMap.get(content.item.description);
        if (matchingItem) {
          content.item.price = matchingItem.price;
        }
      });
    });
  }
}

// Uncomment the following onmounted call to enable local storage restoration.
// You also might want to enable the corresponding code in watch to save changes.
// This is commented out by default as it may not be desired behavior for all users.
/*
onMounted(() => {
  try {
    const savedItems = localStorage.getItem('items_backup');
    console.log('Saved items found:', JSON.parse(savedItems));
    if (savedItems && savedItems.length > 0) items.value = JSON.parse(savedItems);
    
    const savedContainers = localStorage.getItem('containers_backup');
    console.log('Saved contianers found:', JSON.parse(savedContainers));
    if (savedContainers && savedContainers.length > 0) containers.value = JSON.parse(savedContainers);
  
  } catch (e) {
    console.warn('Failed to restore backup:', e);
  }
});
*/

</script>

<template>

  <div
  class="px-4 mt-5"
  >
    <!--Loading-->
    <div 
    v-if="isProcessing" 
    class="loading-overlay"
    role="status"
    aria-live="polite"
    aria-label="Processing data"
    >
    <div class="loading-content bg-dark">
        <div class="spinner-border text-primary mb-3" role="status">
        <span class="visually-hidden">Loading...</span>
        </div>
        <h5 class="mb-3">{{ processingStage }}</h5>
        <div class="progress" style="width: 300px; height: 25px;">
        <div 
            class="progress-bar progress-bar-striped progress-bar-animated" 
            role="progressbar" 
            :style="{ width: processingProgress + '%' }"
            :aria-valuenow="processingProgress"
            aria-valuemin="0" 
            aria-valuemax="100"
        >
            {{ processingProgress }}%
        </div>
        </div>
    </div>
    </div>
    <!--Loading-->

    <!-- quick nav -->
    <nav class="mb-4 row" aria-label="Page navigation">
      <div class="card">
        <div class="card-body">
          <h6 class="card-title">Quick Navigation:</h6>
          <div class="d-flex flex-wrap gap-2">
            <a href="#step-1" class="btn btn-sm btn-outline-primary">Step 1: Scripts</a>
            <a href="#step-2" class="btn btn-sm btn-outline-primary">Step 2: Journal Log</a>
            <a href="#step-3" class="btn btn-sm btn-outline-primary">Step 3: Import</a>
            <a v-show="items.length > 0" href="#step-4" class="btn btn-sm btn-outline-primary">Step 4: Pricing</a>
            <a v-show="containers.length > 0" href="#step-5" class="btn btn-sm btn-outline-primary">Step 5: Vendor Script</a>
            <a v-show="items.length > 0" href="#step-6" class="btn btn-sm btn-outline-primary">Step 6: Verification</a>
          </div>
        </div>
      </div>
    </nav>
    <!--quick nav-->

    <!--heading stuff for SEO-->
    <div class="row my-4">
      <div class="alert alert-info" role="region" aria-label="Welcome message">
        <h2 class="h5">Welcome to Ultimate Outlander</h2>
        <p>
          <strong>Ultimate Outlander</strong> is a free tool designed to help Ultima Online Outlands 
          players efficiently manage their vendor inventory. Generate catalog scripts for Razor, 
          import container contents, set prices, and create vendor restock scripts automatically.
        </p>
      </div>
    </div>
    <!--heading stuff for SEO-->

    <!--FAQ/Information Section-->
    <div class="row mb-4">
      <div class="card mx-auto p-0">
        <div class="card-header bg-primary text-light">
          <button 
            class="btn btn-link text-white text-decoration-none w-100 text-start d-flex justify-content-between align-items-center p-0"
            type="button"
            @click="showFaq = !showFaq"
            :aria-expanded="showFaq"
            aria-controls="faq-content"
          >
            <span>About Ultimate Outlander & FAQ</span>
            <span aria-hidden="true">{{ showFaq ? '−' : '+' }}</span>
          </button>
        </div>
        <div 
          v-show="showFaq"
          id="faq-content"
          class="card-body"
        >
          <h2 class="h5">What is Ultimate Outlander?</h2>
          <p>
            Ultimate Outlander is a free web-based tool designed specifically for Ultima Online Outlands players 
            who want to streamline their vendor management process. This vendor script generator helps you automate 
            the tedious task of cataloging container contents and restocking vendor inventory. Whether you're managing 
            phylacteries, aspect cores, aspect distillations, skill mastery scrolls, maps, equipment, or other valuable 
            items, Ultimate Outlander makes the process efficient and error-free.
          </p>

          <h2 class="h5 mt-4">How Does the Container Catalog Work?</h2>
          <p>
            The catalog tool works by generating Razor scripts that systematically identify and log every item in your 
            containers. When you run the catalog script in Razor, it creates a detailed journal log of all items, 
            including their unique serial numbers. You can then import this journal log into Ultimate Outlander, where 
            the tool parses the data and organizes it into an easy-to-manage pricing table. This eliminates manual 
            counting and reduces pricing errors when stocking your vendor.
          </p>

          <h2 class="h5 mt-4">What is a Vendor Stock Script?</h2>
          <p>
            A vendor stock script is an automated Razor script that takes items from a container and places them on 
            your vendor with the correct prices already set. Instead of manually dragging each item to your vendor 
            and typing in prices one by one, the vendor stock script does all the work for you. Ultimate Outlander 
            generates these scripts based on your catalog data and the prices you've set, ensuring every item is 
            priced accurately and consistently.
          </p>

          <h2 class="h5 mt-4">Why Use Ultimate Outlander for UO Outlands?</h2>
          <p>
            Managing a vendor in Ultima Online Outlands can be time-consuming, especially when dealing with rare items 
            like aspect cores, phylacteries, and skill scrolls that have varying market values. Ultimate Outlander 
            saves you hours by automating the cataloging process and script generation. The tool helps you maintain 
            competitive pricing by allowing you to quickly reference market prices through integrated vendor search 
            links. You can export your item pricing data as JSON for backup and reuse it across multiple restocking 
            sessions.
          </p>

          <h2 class="h5 mt-4">Supported Item Types</h2>
          <p>
            Ultimate Outlander currently supports a wide range of item types commonly sold by Ultima Online Outlands 
            vendors. The loot catalog script handles phylacteries, aspect cores (including chroma cores), aspect 
            distillations, skill mastery scrolls (used to increase skill caps), treasure maps. The 
            equipment catalog script covers all armor types including leather, studded, ringmail, chainmail, and 
            platemail armor pieces. It also catalogs weapons such as swords, axes, maces, staves, bows, crossbows, 
            daggers, spears, musical instruments, shields, and parrying daggers. Support for additional item types 
            is continuously being added based on community feedback. You can easily edit the item catalog script to add more items but take note that not all items are properly supported yet.
          </p>

          <h2 class="h5 mt-4">How to Use Razor Scripts with ClassicUO</h2>
          <p>
            Razor is a popular assistant tool for ClassicUO that enables scripting and automation. To use Ultimate 
            Outlander's generated scripts, simply copy the script text and paste it into Razor's script editor. Run 
            the catalog script first to generate your journal log, which you'll find in your ClassicUO folder under 
            ClassicUO\Data\Client\JournalLogs. After importing the journal log into Ultimate Outlander and setting your prices, 
            generate and run the vendor stock script to automatically place items on your vendor with correct pricing.
          </p>

          <h2 class="h5 mt-4">Tips for Pricing Your Items</h2>
          <p>
            Competitive pricing is crucial for vendor success in UO Outlands. Use the integrated vendor search links 
            in the pricing table to quickly check current market prices for each item. Consider pricing slightly below 
            competitors for fast-moving items, or hold firm on rare items with limited supply. The export feature 
            lets you save your pricing structure, making it easy to maintain consistent prices across restocking 
            sessions. Always double-check prices in-game before finalizing, especially for high-value items like 
            rare phylacteries or perfect aspect cores.
          </p>

          <h2 class="h5 mt-4">Troubleshooting Common Issues</h2>
          <p>
            If your journal log isn't importing correctly, ensure you copied the complete log including the catalog 
            start and end markers. If you run the item catalog script but you see that it doesn't catalog any items, make sure that your avatar is standing close enough to the container being cataloged.The tool requires logs generated by the Ultimate Outlander catalog scripts to 
            parse correctly. If item prices aren't being set on your vendor, verify that you selected a vendor-owned 
            container that is set to "not for sale" rather than the vendor's root backpack. Items placed in the root 
            backpack will stack together, causing pricing issues. If you find that prices are not being set, make sure that you disabled the "Use Item Pricing Menu" checkbox in the store menu. If you encounter any bugs or have feature requests, 
            please use the feedback form to help improve Ultimate Outlander.
          </p>
        </div>
      </div>
    </div>
    <!--End FAQ-->

    <!--step 1 Copy and run script-->
    <div id="step-1" class="row mb-4">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary">
            <div class="d-flex align-items-center text-light">
              <span class="badge bg-light text-primary me-2">1</span>
              Step 1: Copy and run catalog script in Razor.
            </div>
        </div>

        <div class="card-body bg-background d-flex flex-column justify-content-center">

          <div class="d-md-flex justify-content-center">

            <div
            class="card"
            >

              <div class="card-body">
                <p class="fw-bold">Catalog Loot:</p>
                <p>Supported Items:</p>
                <ul class="list-group">
                  <li class="list-group-item">Aspect Cores</li>
                  <li class="list-group-item">Aspect Distillations</li>
                  <li class="list-group-item">Skill Scrolls</li>
                  <li class="list-group-item">Phylacteries</li>
                  <li class="list-group-item">Maps</li>
                  <li class="list-group-item">More coming soon...</li>
                </ul>

                <button 
                id="copyCatalogLootScriptBtn" 
                type="button" 
                class="btn btn-secondary w-100 mt-3" 
                @click="copyCatalogLootScript"
                :disabled="isProcessing"
                aria-label="Copy catalog loot script to clipboard"
                :aria-busy="isProcessing"
                >
                <span aria-hidden="true">📋</span>
                Copy Catalog Loot Script
                </button>

                </div>

            </div>

            <div
            class="card ms-md-4"
            >

              <div class="card-body d-flex flex-column justify-content-between">
                <div>
                  <p class="fw-bold">Catalog Equipment:</p>
                  <p>Supported Items:</p>
                  <ul class="list-group">
                    <li class="list-group-item">Most Equipment</li>
                  </ul>
                </div>
                
                <button 
                id="copyCatalogEquipmentScriptBtn" 
                type="button" 
                class="btn btn-secondary w-100 mt-3" 
                @click="copyCatalogEquipmentScript"
                :disabled="isProcessing"
                aria-label="Copy catalog equipment script to clipboard"
                :aria-busy="isProcessing"
                >
                <span aria-hidden="true">📋</span>
                Copy Catalog Equipment Script
                </button>
                </div>

            </div>
          </div>

          <p class="mt-4">Once cataloging is done, log out of the game. This creates a new journal log.</p>

        </div>

      </div>

    </div>
    <!--end step 1-->

    <!--step 2 Copy Journal text-->
    <div id="step-2" class="row mb-4">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary text-light">
            <div class="d-flex align-items-center">
                <span class="badge bg-light text-primary me-2">2</span>
                Step 2: Find and copy latest journal log.
            </div>
        </div>
        <div class="card-body d-flex flex-column justify-content-center">

          <p>Find your journal logs here:</p>
          <p> Game Folder\ClassicUO\Data\Client\JournalLogs</p>

        </div>

      </div>

    </div>
    <!--end step 2-->

    <!--step 3 Import-->
    <div id="step-3" class="row mb-4">

      <div class="card mx-auto p-0">
        <div class="card-header bg-primary text-light">
            <div class="d-flex align-items-center">
                <span class="badge bg-light text-primary me-2">3</span>
                Step 3: Import journal text.
            </div>
        </div>
          <div class="card-body">

            <form @submit.prevent="parseInput">
              
              <div class="mb-3 d-flex flex-column">
  
                <label for="floatingTextarea" class="form-label">Journal Text/Items JSON: </label>
  
                <div
                role="region"
                aria-label="File upload area"
                @drop.prevent="handleDrop"
                @dragover.prevent="handleDragOver"
                @dragenter.prevent="handleDragEnter"
                @dragleave.prevent="handleDragLeave"
                :aria-busy="isProcessing"
                >
                    <textarea 
                        id="journalInput" 
                        class="form-control bg-dark text-light border-light" 
                        :class="{ 
                        'drag-over': isDragging,
                        'border-success': hasData && !isProcessing
                        }"
                        v-model="journalInput"
                        aria-label="Journal text input or drop zone"
                        aria-describedby="dropZoneHelp journalStatus"
                        aria-required="true"
                        :aria-invalid="statusMessage.toLowerCase().includes('error')"
                        :disabled="isProcessing"
                        rows="8"
                    ></textarea>
                    <div id="dropZoneHelp" class="form-text">
                        You can paste text or drag and drop a file here
                    </div>
                    <div 
                    id="journalStatus" 
                    class="form-text mt-2"
                    role="status"
                    aria-live="polite"
                    :class="{
                        'text-danger': statusMessage.toLowerCase().includes('error') || statusMessage.toLowerCase().includes('invalid'),
                        'text-success': statusMessage.toLowerCase().includes('success'),
                        'text-muted': !statusMessage.toLowerCase().includes('error') && !statusMessage.toLowerCase().includes('success')
                    }"
                    >
                    <p class="mb-0">{{ statusMessage }}</p>
                    </div>
                </div>

                <button 
                type="submit" 
                class="btn btn-primary mt-3" 
                :disabled="isProcessing || !journalInput"
                :aria-busy="isProcessing"
                >
                <span v-if="isProcessing" class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                {{ isProcessing ? 'Processing...' : 'Import' }}
                </button>
      
              </div>
  
  
            </form>
          </div>

      </div>

    </div>
    <!--end step 3-->

    <!--step 4 Pricing-->
    <div id="step-4" class="row mb-4"  v-show="items.length > 0">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary text-light">
            <div class="d-flex align-items-center">
                <span class="badge bg-light text-primary me-2">4</span>
                Step 4: Set item prices.
            </div>
        </div>
        <div class="card-body d-flex flex-column justify-content-center">

          <ItemsPricingTable
          :items="items"
          @update:items="updateContainerContentsPrices($event)"
          />

            <button 
            v-show="items.length > 1" 
            type="button" 
            class="btn btn-primary" 
            @click="downloadJSON(items, 'items.json')"
            :disabled="isProcessing"
            aria-label="Export item prices as JSON file"
            >
            Export Item Prices
            </button>

        </div>

      </div>

    </div>
    <!--end step 4-->

    <!--step 5 Containers-->
    <div id="step-5" class="row mb-4" v-show="containers.length > 0">

      <div class="card mx-auto p-0 ">
    
        <div class="card-header bg-primary text-light">
            <div class="d-flex align-items-center">
                <span class="badge bg-light text-primary me-2">5</span>
               Step 5: Copy and run the Vendor Restock Script.
            </div>
        </div>
        <div class="card-body d-flex flex-column justify-content-center">
          <p>The script stores all items in a vendor owned container, which price must be set to "not for sale". If you select the root container of your vendor, the items will stack and prices will be incorrect. If you select a vendor owned container which price has not been set to "not for sale" the item prices will not be set.</p>
          <img src="/images/store_menu.jpg" alt="store menu" class="w-25 mx-auto">
          <div 
          v-for="container in containers" :key="container.id"
          class="row mt-5 ">

            <div class="col-12">

              <div class="card ">

                <div class="d-flex card-header text-light bg-primary">
                    <p class="mb-0 mt-2">Container Serial: {{ container.serial + ' / ' + parseInt(container.serial).toString(16) }}</p>
                    <button
                    class="btn border border-light text-light ms-auto"
                    @click="removeContainer(container.id)"
                    :aria-label="'Remove container ' + container.serial"
                    type="button"
                    >
                    <span aria-hidden="true">🗑️</span>
                    <span class="visually-hidden">Remove container {{ container.serial }}</span>
                    </button>
                    <button 
                    class="btn border border-light text-light ms-2"
                    @click="container.show = !container.show"
                    :aria-expanded="container.show"
                    :aria-controls="'container-body-' + container.id"
                    :aria-label="container.show ? 'Collapse container' : 'Expand container'"
                    >
                        <span aria-hidden="true">{{ container.show ? '−' : '+' }}</span>
                    </button>
                </div>

                <div 
                class="card-body" 
                v-show="container.show"
                :id="'container-body-' + container.id"
                role="region"
                :aria-label="'Container ' + container.serial + ' contents'"
                >
                  <ContainerContentsTable
                    :containerID="container.id"
                    :containerContents="container.contents"
                    @removeItem="removeItemFromContainer(container.id, $event)"
                  />   

  
                </div>


              </div>
          
            </div>

          </div>
        </div>

      </div>

    </div>
    <!--end step 5-->

    
    <!--step 6 Check prices-->
    <div id="step-6" class="row mb-4"  v-show="items.length > 0">

      <div class="card mx-auto p-0">
        <div class="card-header bg-primary text-light">
            <div class="d-flex align-items-center">
                <span class="badge bg-light text-primary me-2">6</span>
               Step 6: Your items should now be stocked. Make sure to double check the prices.
            </div>
        </div>
        <div class="card-body d-flex flex-column justify-content-center">
        <div class="alert alert-warning" role="alert">
            <strong>⚠️ Important:</strong> This site is still in development. Always double-check item prices in-game before finalizing your vendor stock.
        </div>
        </div>

      </div>

    </div>
    <!--end step 6-->
  </div>

  <a href="#" class="back-to-top btn btn-primary" aria-label="Back to top">
  ↑ Top
  </a>
</template>

<style scoped>
.back-to-top {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 1000;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
  backdrop-filter: blur(4px);
}

.loading-content {
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  min-width: 350px;
}

.drag-over {
  border: 3px dashed #0d6efd !important;
  background-color: #e7f3ff !important;
}

textarea:disabled {
  background-color: #f8f9fa;
  cursor: not-allowed;
}

.badge {
  font-size: 1rem;
  padding: 0.5rem 0.75rem;
}

.border-success {
  border-color: #198754 !important;
  border-width: 2px !important;
}

.card {
  transition: all 0.3s ease;
}

.card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.btn {
  transition: all 0.2s ease;
}

.btn:disabled {
  cursor: not-allowed;
  opacity: 0.6;
}

.visually-hidden {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  white-space: nowrap;
  border-width: 0;
}

/* Focus visible for keyboard navigation */
*:focus-visible {
  outline: 3px solid #0d6efd;
  outline-offset: 2px;
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.row.mt-5 {
  animation: slideIn 0.3s ease;
}

.table-responsive {
  overflow-x: auto;
  -webkit-overflow-scrolling: touch;
  margin-bottom: 1rem;
}

@media (max-width: 768px) {
  .table {
    font-size: 0.875rem;
  }
  
  .btn-sm {
    min-height: 44px;
    min-width: 44px;
  }
  
  .form-control {
    font-size: 16px; /* Prevents zoom on iOS */
  }
}
</style>