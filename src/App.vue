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
const testJournal = `[03/14/2025 07:23]  System: WorldMap loading...
[03/14/2025 07:23]  System: Loading WorldMap markers..
[03/14/2025 07:23]  System: ..userMarkers.usr (25)
[03/14/2025 07:23]  System: ..Banks_and_Healers.xml (39)
[03/14/2025 07:23]  System: ..Dungeons.xml (13)
[03/14/2025 07:23]  System: ..Dungeon_Gates.xml (396)
[03/14/2025 07:23]  System: ..Healer_Caravans.xml (42)
[03/14/2025 07:23]  System: ..Kings_Faire.xml (49)
[03/14/2025 07:23]  System: ..Moongates.xml (10)
[03/14/2025 07:23]  System: ..POI.xml (251)
[03/14/2025 07:23]  System: ..PvP_Locations.xml (71)
[03/14/2025 07:23]  System: ..Shrines.xml (9)
[03/14/2025 07:23]  System: ..Sub-Dungeons.xml (4)
[03/14/2025 07:23]  System: ..Townships.xml (10)
[03/14/2025 07:23]  System: WorldMap markers loaded (919)
[03/14/2025 07:23]  System: Welcome Gluk'Urk!
[03/14/2025 07:23]  System: WorldMap loaded!
[03/14/2025 07:23]  Gluk'Urk: Monster Hunter Dignitary
[03/14/2025 07:23]  Gluk'Urk: Lord Gluk'Urk
[03/14/2025 07:23]  System: You have rejoined the party.
[03/14/2025 07:24]  [Razor]: Select target for variable 'rare_loot_container'
[03/14/2025 07:24]  [Razor]: 'rare_loot_container' script variable updated to '0x4089E923'
[03/14/2025 07:24]  [Razor]: Now cataloguing...
[03/14/2025 07:24]  [Razor]: 1082779939
[03/14/2025 07:24]  [Razor]: Air Aspect Core : 2
[03/14/2025 07:24]  [Razor]: Added 1161806015 to ignore list
[03/14/2025 07:24]  You see: Earth Aspect Core : 2
[03/14/2025 07:24]  [Razor]: Select target for variable 'rare_loot_container'
[03/14/2025 07:24]  [Razor]: 'rare_loot_container' script variable updated to '0x4089E923'
[03/14/2025 07:24]  [Razor]: Now cataloguing...
[03/14/2025 07:24]  [Razor]: 1082779939
[03/14/2025 07:24]  [Razor]: Earth Aspect Core : 2
[03/14/2025 07:24]  [Razor]: Added 1085210704 to ignore list
[03/14/2025 07:24]  [Razor]: Fortune Aspect Core
[03/14/2025 07:24]  [Razor]: Added 1152384927 to ignore list
[03/14/2025 07:24]  [Razor]: Eldritch Aspect Core : 2
[03/14/2025 07:24]  [Razor]: Added 1159657583 to ignore list
[03/14/2025 07:24]  [Razor]: Void Aspect Core
[03/14/2025 07:24]  [Razor]: Added 1220388922 to ignore list
[03/14/2025 07:24]  [Razor]: Madness Aspect Core
[03/14/2025 07:24]  [Razor]: Added 1213299230 to ignore list
[03/14/2025 07:24]  [Razor]: Frost Aspect Core
[03/14/2025 07:24]  [Razor]: Added 1214639822 to ignore list
[03/14/2025 07:24]  [Razor]: Harvest Aspect Core
[03/14/2025 07:24]  [Razor]: Added 1342869590 to ignore list
[03/14/2025 07:24]  [Razor]: Death Aspect Core
[03/14/2025 07:24]  [Razor]: Added 1293340755 to ignore list
[03/14/2025 07:24]  [Razor]: a chromatic core : 2
[03/14/2025 07:24]  [Razor]: Added 1259495345 to ignore list
[03/14/2025 07:24]  [Razor]: Ignore List cleared
[03/14/2025 07:24]  [Razor]: Cataloguing done.
[03/14/2025 07:24]  [Razor]: Select target for variable 'rare_loot_container'
[03/14/2025 07:24]  [Razor]: 'rare_loot_container' script variable updated to '0x785A861C'
[03/14/2025 07:24]  [Razor]: Now cataloguing...
[03/14/2025 07:24]  [Razor]: 2019198492
[03/14/2025 07:24]  [Razor]: Air Aspect Distillation : 2
[03/14/2025 07:24]  [Razor]: Added 1148837370 to ignore list
[03/14/2025 07:24]  [Razor]: Blood Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1088919910 to ignore list
[03/14/2025 07:24]  [Razor]: Fire Aspect Distillation : 2
[03/14/2025 07:24]  [Razor]: Added 1121710291 to ignore list
[03/14/2025 07:24]  [Razor]: Artisan Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1368154481 to ignore list
[03/14/2025 07:24]  [Razor]: Frost Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1165913952 to ignore list
[03/14/2025 07:24]  [Razor]: Holy Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1195261117 to ignore list
[03/14/2025 07:24]  [Razor]: Poison Aspect Distillation : 2
[03/14/2025 07:24]  [Razor]: Added 1733222166 to ignore list
[03/14/2025 07:24]  [Razor]: Void Aspect Distillation : 2
[03/14/2025 07:24]  [Razor]: Added 1431943843 to ignore list
[03/14/2025 07:24]  [Razor]: Harvest Aspect Distillation : 2
[03/14/2025 07:24]  [Razor]: Added 1190250980 to ignore list
[03/14/2025 07:24]  [Razor]: Death Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1239872627 to ignore list
[03/14/2025 07:24]  [Razor]: Madness Aspect Distillation : 3
[03/14/2025 07:24]  [Razor]: Added 1214627871 to ignore list
[03/14/2025 07:24]  [Razor]: Fortune Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1220437845 to ignore list
[03/14/2025 07:24]  [Razor]: Eldritch Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1342881440 to ignore list
[03/14/2025 07:24]  [Razor]: Lightning Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1219308898 to ignore list
[03/14/2025 07:24]  [Razor]: Water Aspect Distillation
[03/14/2025 07:24]  [Razor]: Added 1401749244 to ignore list
[03/14/2025 07:24]  [Razor]: chromatic distillation : 2
[03/14/2025 07:24]  [Razor]: Added 1239622948 to ignore list
[03/14/2025 07:24]  [Razor]: Ignore List cleared
[03/14/2025 07:24]  [Razor]: Cataloguing done.
[03/14/2025 07:24]  [Razor]: Select target for variable 'rare_loot_container'
[03/14/2025 07:24]  [Razor]: 'rare_loot_container' script variable updated to '0x436BF5E2'
[03/14/2025 07:24]  [Razor]: Now cataloguing...
[03/14/2025 07:24]  [Razor]: 1131148770
[03/14/2025 07:24]  [Razor]: detect hidden skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Select target for variable 'rare_loot_container'
[03/14/2025 07:24]  [Razor]: 'rare_loot_container' script variable updated to '0x436BF5E2'
[03/14/2025 07:24]  [Razor]: Now cataloguing...
[03/14/2025 07:24]  [Razor]: 1131148770
[03/14/2025 07:24]  [Razor]: detect hidden skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1802080494 to ignore list
[03/14/2025 07:24]  [Razor]: taste id skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1161806014 to ignore list
[03/14/2025 07:24]  [Razor]: cartography skill mastery scroll : 2 (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1173823732 to ignore list
[03/14/2025 07:24]  [Razor]: mining skill mastery scroll : 2 (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1182436614 to ignore list
[03/14/2025 07:24]  [Razor]: item id skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1113463241 to ignore list
[03/14/2025 07:24]  [Razor]: animal lore skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1220524745 to ignore list
[03/14/2025 07:24]  [Razor]: necromancy skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1351147995 to ignore list
[03/14/2025 07:24]  [Razor]: discordance skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1401964721 to ignore list
[03/14/2025 07:24]  [Razor]: blacksmithy skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:24]  [Razor]: Added 1182412501 to ignore list
[03/14/2025 07:24]  [Razor]: Ignore List cleared
[03/14/2025 07:24]  [Razor]: Cataloguing done.
[03/14/2025 07:24]  [Razor]: Select target for variable 'rare_loot_container'
[03/14/2025 07:25]  [Razor]: 'rare_loot_container' script variable updated to '0x5A45EE0D'
[03/14/2025 07:25]  [Razor]: Now cataloguing...
[03/14/2025 07:25]  [Razor]: 1514532365
[03/14/2025 07:25]  [Razor]: water phylactery
[03/14/2025 07:25]  [Razor]: Added 1613130439 to ignore list
[03/14/2025 07:25]  [Razor]: rare cloth (hue 2093: shadowspire cathedral)
[03/14/2025 07:25]  [Razor]: Added 1401626041 to ignore list
[03/14/2025 07:25]  [Razor]: rare cloth (hue 1382: powder lemon)
[03/14/2025 07:25]  [Razor]: Added 1225765951 to ignore list
[03/14/2025 07:25]  [Razor]: dyeable carpet tile
[03/14/2025 07:25]  [Razor]: Added 2051253151 to ignore list
[03/14/2025 07:25]  [Razor]: dyeable carpet tile
[03/14/2025 07:25]  [Razor]: Added 1373415996 to ignore list
[03/14/2025 07:25]  [Razor]: dyeable rug tile
[03/14/2025 07:25]  [Razor]: Added 1577236465 to ignore list
[03/14/2025 07:25]  [Razor]: research materials : 4 (double-click to research)
[03/14/2025 07:25]  [Razor]: Added 1148837371 to ignore list
[03/14/2025 07:25]  [Razor]: mastercrafting diagram : 3
[03/14/2025 07:25]  [Razor]: Added 1373580807 to ignore list
[03/14/2025 07:25]  [Razor]: mining skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1111873406 to ignore list
[03/14/2025 07:25]  [Razor]: detect hidden skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1802080494 to ignore list
[03/14/2025 07:25]  [Razor]: taste id skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1161806014 to ignore list
[03/14/2025 07:25]  [Razor]: cartography skill mastery scroll : 2 (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1173823732 to ignore list
[03/14/2025 07:25]  [Razor]: mining skill mastery scroll : 2 (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1182436614 to ignore list
[03/14/2025 07:25]  [Razor]: item id skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1113463241 to ignore list
[03/14/2025 07:25]  [Razor]: animal lore skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1220524745 to ignore list
[03/14/2025 07:25]  [Razor]: necromancy skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1351147995 to ignore list
[03/14/2025 07:25]  [Razor]: discordance skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1401964721 to ignore list
[03/14/2025 07:25]  [Razor]: blacksmithy skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[03/14/2025 07:25]  [Razor]: Added 1182412501 to ignore list
[03/14/2025 07:25]  [Razor]: Air Aspect Core
[03/14/2025 07:25]  [Razor]: Added 1123661072 to ignore list
[03/14/2025 07:25]  [Razor]: Air Aspect Core : 2
[03/14/2025 07:25]  [Razor]: Added 1161806015 to ignore list
[03/14/2025 07:25]  [Razor]: Earth Aspect Core : 2
[03/14/2025 07:25]  [Razor]: Added 1085210704 to ignore list
[03/14/2025 07:25]  [Razor]: Fortune Aspect Core
[03/14/2025 07:25]  [Razor]: Added 1152384927 to ignore list
[03/14/2025 07:25]  [Razor]: Eldritch Aspect Core : 2
[03/14/2025 07:25]  [Razor]: Added 1159657583 to ignore list
[03/14/2025 07:25]  [Razor]: Void Aspect Core
[03/14/2025 07:25]  [Razor]: Added 1220388922 to ignore list
[03/14/2025 07:25]  [Razor]: Madness Aspect Core
[03/14/2025 07:25]  [Razor]: Added 1213299230 to ignore list
[03/14/2025 07:25]  [Razor]: Frost Aspect Core
[03/14/2025 07:25]  [Razor]: Added 1214639822 to ignore list
[03/14/2025 07:25]  [Razor]: Harvest Aspect Core
[03/14/2025 07:25]  [Razor]: Added 1342869590 to ignore list
[03/14/2025 07:25]  [Razor]: Death Aspect Core
[03/14/2025 07:25]  [Razor]: Added 1293340755 to ignore list
[03/14/2025 07:25]  [Razor]: a chromatic core : 2
[03/14/2025 07:25]  [Razor]: Added 1259495345 to ignore list
[03/14/2025 07:25]  [Razor]: Gadget Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1132054652 to ignore list
[03/14/2025 07:25]  [Razor]: Air Aspect Distillation : 2
[03/14/2025 07:25]  [Razor]: Added 1148837370 to ignore list
[03/14/2025 07:25]  [Razor]: Blood Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1088919910 to ignore list
[03/14/2025 07:25]  [Razor]: Fire Aspect Distillation : 2
[03/14/2025 07:25]  [Razor]: Added 1121710291 to ignore list
[03/14/2025 07:25]  [Razor]: Artisan Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1368154481 to ignore list
[03/14/2025 07:25]  [Razor]: Frost Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1165913952 to ignore list
[03/14/2025 07:25]  [Razor]: Holy Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1195261117 to ignore list
[03/14/2025 07:25]  [Razor]: Poison Aspect Distillation : 2
[03/14/2025 07:25]  [Razor]: Added 1733222166 to ignore list
[03/14/2025 07:25]  [Razor]: Void Aspect Distillation : 2
[03/14/2025 07:25]  [Razor]: Added 1431943843 to ignore list
[03/14/2025 07:25]  [Razor]: Harvest Aspect Distillation : 2
[03/14/2025 07:25]  [Razor]: Added 1190250980 to ignore list
[03/14/2025 07:25]  [Razor]: Death Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1239872627 to ignore list
[03/14/2025 07:25]  [Razor]: Madness Aspect Distillation : 3
[03/14/2025 07:25]  [Razor]: Added 1214627871 to ignore list
[03/14/2025 07:25]  [Razor]: Fortune Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1220437845 to ignore list
[03/14/2025 07:25]  [Razor]: Eldritch Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1342881440 to ignore list
[03/14/2025 07:25]  [Razor]: Lightning Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1219308898 to ignore list
[03/14/2025 07:25]  [Razor]: Water Aspect Distillation
[03/14/2025 07:25]  [Razor]: Added 1401749244 to ignore list
[03/14/2025 07:25]  [Razor]: chromatic distillation : 2
[03/14/2025 07:25]  [Razor]: Added 1239622948 to ignore list
[03/14/2025 07:25]  [Razor]: Ignore List cleared
[03/14/2025 07:25]  [Razor]: Cataloguing done.
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


  //setvar take_container

  wait 250

  if not listexists rare_loot_ids

      createlist rare_loot_ids

  else

      clearlist rare_loot_ids


  endif

  pushlist rare_loot_ids "Phylactery"
  //pushlist rare_loot_ids "Bottle"
  //pushlist rare_loot_ids "Blank Scroll"
  pushlist rare_loot_ids "Folded Cloth"
  pushlist rare_loot_ids "Carpet"
  pushlist rare_loot_ids "Research Materials"
  pushlist rare_loot_ids "Book of Truth"
  pushlist rare_loot_ids "scroll of calling"
  pushlist rare_loot_ids "aspect core"
  pushlist rare_loot_ids "chroma core"
  pushlist rare_loot_ids "flask"
  pushlist rare_loot_ids "chroma distil"
  pushlist rare_loot_ids "map"

  sysmsg "Now cataloguing..."
  wait 250
  sysmsg rare_loot_container
  wait 250
  foreach id in rare_loot_ids

      while findtype id rare_loot_container as item

          getlabel item description

          sysmsg description

          wait 250
          
          // lift item 60000
          //wait 250
          
          //drop take_container
          //
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
            <p class="mb-0 mt-1">Catalog Parser</p>
            <button id="copyCatalogScriptBtn" type="button" class="btn btn-secondary" @click="copyCatalogScript">Copy Catalog Script</button>
          </div>
          <div class="card-body">
            <form>
              
              <div class="mb-3 d-flex flex-column">
  
                  <label for="floatingTextarea" class="form-label">Journal Text: </label>
  
                  <textarea  id="journalInput" class="form-control" placeholder="Paste journal text here"
                  v-model="input" 
                  ></textarea>
  
                  <div  class="form-text">Journal text to parse.</div>
  
                  <div  class="form-text text-muted" ><p>{{ msg }}</p></div>
  
                  <button type="button" class="btn btn-primary " @click="parseInput()">Parse Catalog</button>
      
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

