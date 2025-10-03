<script setup>
import { onMounted, ref, watch } from 'vue'

import Card from './components/Card.vue'
import ItemsTable from './components/ItemsTable.vue';
import ContainerContentsTable from './components/ContainerContentsTable.vue';

import ItemTypes from "./data/item_types.json";

const input = ref('');
const msg = ref('');

const items = ref([]);
const containers = ref([]);

const minimizeItemsTable = ref(false);
const testJournal = `[10/03/2025 13:56]  [Razor]: Now cataloguing...
[10/03/2025 13:56]  [Razor]: 1268384077
[10/03/2025 13:56]  [Razor]: Finished 17 queued actions in 9.6 seconds.
[10/03/2025 13:56]  [Razor]: animal lore skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1475345576 to ignore list
[10/03/2025 13:56]  [Razor]: blacksmithy skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1535162425 to ignore list
[10/03/2025 13:56]  [Razor]: mining skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1535901036 to ignore list
[10/03/2025 13:56]  [Razor]: spirit speak skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1546426497 to ignore list
[10/03/2025 13:56]  [Razor]: peacemaking skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1546941378 to ignore list
[10/03/2025 13:56]  [Razor]: alchemy skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1118610180 to ignore list
[10/03/2025 13:56]  [Razor]: taste id skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1122402331 to ignore list
[10/03/2025 13:56]  [Razor]: blacksmithy skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1122849462 to ignore list
[10/03/2025 13:56]  [Razor]: poisoning skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1124919684 to ignore list
[10/03/2025 13:56]  [Razor]: detect hidden skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1127695081 to ignore list
[10/03/2025 13:56]  [Razor]: provocation skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1139062282 to ignore list
[10/03/2025 13:56]  [Razor]: spirit speak skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1141032881 to ignore list
[10/03/2025 13:56]  [Razor]: cartography skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1193379978 to ignore list
[10/03/2025 13:56]  [Razor]: tailoring skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1268076083 to ignore list
[10/03/2025 13:56]  [Razor]: animal lore skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1277662265 to ignore list
[10/03/2025 13:56]  [Razor]: poisoning skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1277665890 to ignore list
[10/03/2025 13:56]  [Razor]: provocation skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/03/2025 13:56]  [Razor]: Added 1277666508 to ignore list
[10/03/2025 13:56]  [Razor]: Ignore List cleared
[10/03/2025 13:56]  [Razor]: Cataloguing done.

`;


/*  Parsing  */
function parseId(logData) {

// Look for the first occurrence of "[Razor]: " followed by exactly 10 digits

const idMatch = logData.match(/\[Razor\]: (\d{10})/);

if (idMatch) {

    return idMatch[1]; // Return the ID if found

}

return null; // Return null if no ID is found

}

function parseContainerDate(logData) {

// Look for the first occurrence of "[MM/DD/YYYY"

const dateMatch = logData.match(/\[(\d{2}\/\d{2}\/\d{4})/);

if (dateMatch) {

    return dateMatch[1]; // Return the date if found
    
}

return null; // Return null if no date is found

}

function parseContainerTime(logData) {

  // Look for the first occurrence of "[MM/DD/YYYY HH:MM]"
  const timeMatch = logData.match(/\[\d{2}\/\d{2}\/\d{4} (\d{2}:\d{2})\]/);

  if (timeMatch) {

      return timeMatch[1]; // Return the time if found

  }

  return null; // Return null if no time is found

}

function parseItems(logData) {
  const lines = logData.split('\n');
  const items = [];

  for (let i = 0; i < lines.length; i++) {
    const line = lines[i];

    if (line.includes('[Razor]:') && !line.includes('Now cataloguing') && !line.includes('... cataloguing done')) {
        let description = line.split('[Razor]: ')[1].trim();
        
        console.log(`Line ${i}: Found description: "${description}"`);

        const nextLine = lines[i + 1];
        console.log(`Line ${i+1}: Next line: "${nextLine}"`);

        if (nextLine && nextLine.includes('Added') && nextLine.includes('to ignore list')) {
            const idMatch = nextLine.match(/Added (\d+) to/);
            console.log(`Line ${i+1}: ID Match:`, idMatch);
            
            if (idMatch) {
                console.log(`Pushing: ${description} -> ${idMatch[1]}`);
                items.push({
                  description,
                  id: idMatch[1],
                });
                i++; // Skip the next line
            }
        }
    }
  }

  return items;
}

function extractAllChestLogs(content, startingRegex, endingRegex) {
  /**
   * Extracts all complete blocks of text starting with `startingRegex` and ending with `endingRegex`.
   * 
   * @param {string} content - The log content as a string.
   * @param {RegExp} startingRegex - The starting delimiter regex.
   * @param {RegExp} endingRegex - The ending delimiter regex.
   * @return {string[]} - An array of all valid blocks found.
   */
  const blocks = [];
  let startIndex;

  while ((startIndex = content.search(startingRegex)) !== -1) {
      const blockStartIndex = startIndex;

      // Find the end of the current block
      const afterStartContent = content.slice(blockStartIndex);
      const endMatch = afterStartContent.match(endingRegex);
      if (!endMatch) break; // No ending match, stop searching

      const blockEndIndex = blockStartIndex + endMatch.index + endMatch[0].length;

      // Check for another start delimiter within this block
      const withinBlockContent = content.slice(blockStartIndex + 1, blockEndIndex);
      const nextStartMatch = withinBlockContent.match(startingRegex);
      if (nextStartMatch) {
          // Skip this block since another start was found
          content = content.slice(blockStartIndex + 1);
          continue;
      }

      // Valid block found
      const block = content.slice(blockStartIndex, blockEndIndex);
      blocks.push(block);

      // Remove the current block from content
      content = content.slice(blockEndIndex);
  }

  return blocks;
}

function validateJSON(jsonString, requiredProps) {

  try {
      // Parse the JSON string
      const data = JSON.parse(jsonString);

      // Check if parsed data is an array
      if (!Array.isArray(data)) {
          throw new Error("JSON is valid but does not contain an array.");
      }

      // Validate each object in the array
      for (const item of data) {
          if (typeof item !== "object" || item === null) {
              throw new Error("One or more elements in the array are not objects.");
          }

          // Check if all required properties exist in the object
          for (const prop of requiredProps) {
              if (!item.hasOwnProperty(prop)) {
                  throw new Error(`Missing required property: ${prop}`);
              }
          }
      }

      return { valid: true, message: "Valid JSON with all required properties." };
  } catch (error) {
      return { valid: false, message: error.message };
  }

}

function parseInput () {
   
   //if input empty show message
   if(input.value === ''){
     msg.value = "Paste text first."
     return 0;
   }
 
   const requiredProps = ["description", "price"];
   if(validateJSON(input.value, requiredProps).valid){
     msg.value = "valid json";
     items.value = JSON.parse(input.value);
     //here we need to update the prices for containers
    console.log(containers.value);
    if(containers.value.length > 0){
      console.log('containers exist');

      containers.value.forEach((container) => {
        container.contents.forEach((content) => {
          const matchingItem = items.value.find(item => item.description === content.item.description);
          if(matchingItem){
   
            content.item.price = matchingItem.price;
          }
        });
      });

    }

    return 1
   }
 
   if(input.value.includes("[Razor]: Now cataloguing...")){
     msg.value = "valid catalog";
 
     const startingRegex = /Now cataloguing\.\.\./;
     const endingRegex = /Cataloguing done\./;
     const chestLogs = extractAllChestLogs(input.value, startingRegex, endingRegex);
 
     if(chestLogs.length < 0){
 
       msg.value = "No valid chest catalogs found."
 
       return 0;
 
     }
 
     chestLogs.forEach((chestLog) => {
      console.log(chestLog)
       const parsedItems = parseItems(chestLog);
       const contents = [];
      
parsedItems.forEach((parsedItem) => { 
 
  if(parsedItems.length > 0) {
 
    //check if item already exists in items list
    const existingItem = items.value.find(existingItem => existingItem.description === parsedItem.description);
    
    let item;
    if(existingItem){
      //the item exists - create a new object with the current parsedItem's ID
      item = {
        description: parsedItem.description,
        id: parsedItem.id,  // Use the current parsedItem's unique ID
        type: existingItem.type,
        price: existingItem.price,
      };
    } else {
      //the item does not exist and should be added
      item = {
        description: parsedItem.description,
        id: parsedItem.id,
        type: getItemType(parsedItem.description),
        price: 0,
      };
      items.value.push(item);
    }
    
    contents.push({
      item: item,
    });
  }
});
       const container = {
           id: parseId(chestLog),
           date_updated: parseContainerDate(chestLog),
           time_updated: parseContainerTime(chestLog),
           contents: contents,
           show: true
       }
 
       containers.value.push(container);
 
     });
 
     console.log({
      items,containers
     });
 
     return 1;
   }
 
}

function downloadJSON(jsonObject, fileName) {
    // Convert JSON object to string
    const jsonString = JSON.stringify(jsonObject, null, 2);

    // Create a Blob from the JSON string
    const blob = new Blob([jsonString], { type: "application/json" });

    // Create a link element
    const link = document.createElement("a");

    // Set the download attribute with the filename
    link.download = fileName;

    // Create a URL for the Blob and set it as the href attribute
    link.href = URL.createObjectURL(blob);

    // Append the link to the document
    document.body.appendChild(link);

    // Programmatically click the link to trigger the download
    link.click();

    // Remove the link from the document
    document.body.removeChild(link);
}

function getItemType(itemDescription) {
  for (const [type, itemsOfType] of Object.entries(ItemTypes)) {

    if (itemsOfType.some(item => itemDescription.includes(item))) {
      return type;
    }
    
  }
  return -1;
}

function copyToClipboard(textToCopy){
  navigator.clipboard.writeText(textToCopy);
}

function copyCatalogScript (event){

  const textToCopy = `
  setvar rare_loot_container

  wait 250

  dclick rare_loot_container
  setvar take_container

  wait 250

  if not listexists rare_loot_ids

      createlist rare_loot_ids

  else

      clearlist rare_loot_ids


  endif

  //pushlist rare_loot_ids "Phylactery"
  //pushlist rare_loot_ids "Bottle"
  //pushlist rare_loot_ids "Blank Scroll"
  //pushlist rare_loot_ids "Folded Cloth"
  //pushlist rare_loot_ids "Carpet"
  //pushlist rare_loot_ids "Research Materials"
  //pushlist rare_loot_ids "Book of Truth"
  //pushlist rare_loot_ids "scroll of calling"
  //pushlist rare_loot_ids "aspect core"
  // pushlist rare_loot_ids "chroma core"
  pushlist rare_loot_ids "flask"
  //pushlist rare_loot_ids "chroma distil"
  //pushlist rare_loot_ids "map"
    
  //split items
  sysmsg "Now splitting items..."
  wait 250
  sysmsg rare_loot_container
  wait 250
  //split items
  foreach id in rare_loot_ids

      while findtype id rare_loot_container as item
          
        lift item 1
        //wait 250
          
        drop take_container
        //
        ignore item
      endwhile

  endfor
  clearignore
  
  //catalog items
  sysmsg "Now cataloguing..."
  wait 250
  sysmsg take_container
  wait 250
  foreach id in rare_loot_ids

  while findtype id take_container as item

          getlabel item description

          sysmsg description

          wait 250
          
          ignore item
      endwhile

  endfor
  clearignore
  sysmsg "Cataloguing done."`
  
  //show popoover that hides on next click
  const copyScriptBtn = event.target;
  copyScriptBtn.innerText = 'Copied!'
  window.setTimeout(() => {
  
    const copyScriptBtn = document.getElementById('copyCatalogScriptBtn');
    copyScriptBtn.innerText = 'Copy Catalog Script'
  }, 2000)
  return copyToClipboard(textToCopy);
}


function removeContainer(containerId){
  console.log(containerId)
  console.log(containers.value)
  containers.value = containers.value.filter(function( obj ) {
      return obj.id !== containerId;
  });
}
onMounted(() => {
  console.log('Yo');
  input.value = testJournal;
  parseInput();
});
</script>

<template>

  <div
  class="px-4 mt-5"
  >

    <!--Nav-->

    <!--input-->   
    <div class="row mt-5 mx-5">

      <div class="col-12">

        <div class="card border shadow">
          <div class="d-flex justify-content-between card-header text-light bg-primary">
            <p class="mb-0 mt-1">Importer</p>
            <button id="copyCatalogScriptBtn" type="button" class="btn btn-secondary" @click="copyCatalogScript">Copy Catalog Script</button>
          </div>
          <div class="card-body">
            <form>
              
              <div class="mb-3 d-flex flex-column">
  
                  <label for="floatingTextarea" class="form-label">Journal Text: </label>
  
                  <textarea  id="journalInput" class="form-control" placeholder="Paste journal text here"
                  v-model="input" 
                  ></textarea>
  
                  <div  class="form-text">Journal text/item json</div>
  
                  <div  class="form-text text-muted" ><p>{{ msg }}</p></div>
  
                  <button type="button" class="btn btn-primary " @click="parseInput()">Import</button>
      
              </div>
  
  
            </form>
          </div>
        </div>
      </div>

    </div>

    <!-- items table -->
    <div class="row  mt-5 mx-5"
    v-show="items.length > 0"
    >

    <div class="col-12">
      <div class="card border shadow">
        <div class="card-header d-flex justify-content-between text-light bg-primary">
          <p class="mb-0">Item Prices</p>
          <button 
          class="btn btn-secondary"
          @click="minimizeItemsTable=!minimizeItemsTable"
          ><strong>{{ minimizeItemsTable ? '+' : '-' }}</strong></button>
        </div>
        <div class="card-body">
          <ItemsTable 
        :items="items"
        v-show="!minimizeItemsTable"
        />

        <button 
        v-show="items.length > 1 && !minimizeItemsTable" type="button" class="btn btn-primary" @click="downloadJSON(items, 'items.json')">Export</button>

      
        </div>
      </div>

    </div>

    </div>

    <!-- container table-->
    <div 
    v-for="container in containers" :key="container.id"
    class="row mt-5 mx-5">

      <div class="col-12">

        <div class="card border shadow">

          <div class="d-flex card-header text-light bg-primary">
            <p class="mb-0 mt-2">Serial: {{ container.id + ' / ' + parseInt(container.id).toString(16) }}</p>
            <button
            class="btn btn-danger ms-auto"
            @click="removeContainer(container.id)"
            > X </button>
            <button 
            class="btn btn-secondary ms-2"
            @click="container.show = !container.show"
            ><strong>{{ container.show ? '+' : '-' }}</strong></button>
          </div>

          <div class="card-body"
               v-show="container.show"
          >
            <ContainerContentsTable
              :containerID="container.id"
              :containerContents="container.contents"
         
            />   
          </div>

        </div>
    
      </div>

    </div>

  </div>

</template>

