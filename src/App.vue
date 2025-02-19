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

  const lines = logData.split('\n'); // Split the log into lines
  const items = []; // Initialize an array to store the items

  // Iterate through the lines to process item data
  for (let i = 0; i < lines.length; i++) {

    const line = lines[i];

    // Look for lines that start with "[Razor]: " (item description lines)
    if (line.includes('[Razor]:') && !line.includes('Now cataloguing') && !line.includes('... cataloguing done')) {
        let description = line.split('[Razor]: ')[1].trim(); // Extract the description
        let qty = 1; // Default amount

        // Check if the description ends with ": <integer>"
        const qtyMatch = description.match(/: (\d+)$/);
        if (qtyMatch) {

            qty = parseInt(qtyMatch[1], 10); // Extract and parse the integer
            description = description.replace(/: \d+$/, '').trim(); // Remove the ": <integer>" part

        }

        // Check the next line for the item ID
        const nextLine = lines[i + 1];

        if (nextLine && nextLine.includes('Added') && nextLine.includes('to ignore list')) {

            const idMatch = nextLine.match(/Added (\d+) to/); // Extract the ID using regex
    
            if (idMatch) {

                items.push({
                  description,
                  id: idMatch[1],
                  qty: parseInt(qty), 
                });
                
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
     console.log(items)
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
 
       const parsedItems = parseItems(chestLog);
       const contents = [];
      
       parsedItems.forEach((parsedItem) => { 
 
         if(parsedItems.length > 0) {
 
          let item = {};
               const existingItem = items.value.find(existingItem => existingItem.description === parsedItem.description);
               
               if(existingItem){
                //the item exists and we should update its qty
                existingItem.qty = existingItem.qty + parsedItem.qty
                item = existingItem;
                console.log
               } else {
                //the item does not exist and should be added
                console.log(parsedItem)
                 item = {
              
                     description: parsedItem.description,
                     id: parsedItem.id,
                     type: getItemType(parsedItem.description),
                     price: 0,
                     qty: parsedItem.qty,
                 };
                   
                 items.value.push(item);
               }
               
 
               contents.push({
                 item: item,
                 stack_size:parsedItem.qty
               });
 
         }
       });
       const container = {
           id: parseId(chestLog),
           date_updated: parseContainerDate(chestLog),
           time_updated: parseContainerTime(chestLog),
           contents: contents
       }
 
       containers.value.push(container);
 
     });
 
     console.log({
      items,containers
     });
 
     return 1;
   }
 
}

/*  End Parsing  */

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

//TODO: this should maybe be category and not type
function getItemType(itemDescription) {
  for (const [type, itemsOfType] of Object.entries(ItemTypes)) {

    if (itemsOfType.some(item => itemDescription.includes(item))) {
      return type;
    }
    
  }
  return -1;
}
</script>

<template>
  <div>

    <!--input-->   
    <div class="row">

      <div class="col-12">


        <Card
        cardHeader="Parser"
        >

          <form>
                        
            <div class="mb-3">

                <label for="floatingTextarea" class="form-label">Text: </label>

                <textarea  class="form-control" placeholder="Paste journal text here"
                v-model="input" 
                ></textarea>

                <div  class="form-text">Input text here.</div>

                <div  class="form-text text-muted" ><p>{{ msg }}</p></div>

            </div>
            
            <button type="button" class="btn btn-primary" @click="parseInput()">Parse</button>

          </form>

        </Card>

      </div>

      </div>

      <!-- items table -->
      <div class="row">

      <div class="col-12">


        <Card
        cardHeader="Items"
        >

          <ItemsTable 
          :items="items"
          />

          <button v-show="items.length > 1" type="button" class="btn btn-primary" @click="downloadJSON(items, 'items.json')">Export</button>

        </Card>

      </div>

      </div>
      <!-- container table-->
      <div 
      v-for="container in containers" :key="container.id"
      class="row">

      <div class="col-12">

      <Card
      :cardHeader="container.id + ' | ' + parseInt(container.id).toString(16)"
      >

        <ContainerContentsTable
        :containerID="container.id"
        :containerContents="container.contents"
        />        

      </Card>


      </div>

    </div>

  </div>

</template>

