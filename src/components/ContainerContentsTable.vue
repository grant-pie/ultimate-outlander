<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-2">
      <div class="d-flex flex-column">
        <h5>Container Contents</h5>
      <p>Item Count: {{ containerContents.length }}</p>
      </div>
      <input
        type="text"
        class="form-control w-25"
        v-model="searchQuery"
        placeholder="Search..."
      />
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">Item Description</th>
          <th scope="col">Item Serial</th>
          <th scope="col">Price</th>

          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(content, index) in filteredContents" :key="index">
          <td>{{ content.item.description }}</td>
          <td>{{ content.item.id }}</td>
          <td>{{ content.item.price }}</td>
          <td class="d-flex">

            <button @click="$emit('removeItem', content.item.id)" class="btn btn-primary btn-sm font-size-6">Remove</button>
            <button @click="copyGetItemScript(content.item.id, $event)" :id="'copyStockScriptBtn_' + content.item.id" class="btn ms-2 btn-primary btn-sm font-size-6">Copy Get Item Script</button>

          </td>

        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="4" class="text-end"><strong>Grand Total:</strong></td>
          <td>{{ grandTotal }}</td>
        </tr>
      </tfoot>
    </table>
    <button 
    :id="'exportContentsBtn_' + containerID"  type="button" class="btn w-100 btn-primary mb-4" @click="exportContents(containerContents)">Export Contents
    </button>
    <button 
    :id="'copyStockScriptBtn_' + containerID"  type="button" class="btn w-100 btn-primary " @click="copyVendorStockScript(containerContents, $event)">Copy Vendor Stock Script
    </button>
  </div>
</template>

<script>
export default {
  name: "ContainerContentsTable",
  props: {
    containerID: {
      type: Number,
      required: true,
    },
    containerContents: {
      type: Array,
      required: true,
      validator: (contents) =>
        contents.every(
          (content) =>
            typeof content.item === "object" &&
            typeof content.item.description === "string"
        ),
    },
  },
  data() {
    return {
      searchQuery: "", // Holds the search input value
    };
  },
  computed: {
    filteredContents() {
      return this.containerContents.filter((content) =>
        content.item.description
          .toLowerCase()
          .includes(this.searchQuery.toLowerCase())
      );
    },
    grandTotal() {
      return this.filteredContents.reduce((sum, content) => {
        return sum + content.item.price;
      }, 0);
    },
  },
  methods: {
    calculateTotal(price, stack_size) {
      return price * stack_size;
    },
    copyToClipboard(text) {
      navigator.clipboard.writeText(text)
      .then(() => {
        console.log("Text copied to clipboard:", text);
      })
      .catch(err => {
        console.error("Failed to copy text: ", err);
      });
    },
    copyGetItemScript(itemID, event){
      const text =  `
          
        lift ${itemID}
        
        wait 250
        
        drop backpack

        `;
      this.copyToClipboard(text);
      const copyScriptBtn = event.target;
      copyScriptBtn.innerText = 'Copied!'
      window.setTimeout(() => {
      
        const copyScriptBtn = document.getElementById('copyStockScriptBtn_' + itemID);
        copyScriptBtn.innerText = 'Copy Stock Script'
      }, 2000)
      return this.copyToClipboard(textToCopy);
    },
    copyVendorStockScript(itemsToStock, event) {
      console.log({log:itemsToStock})
      let textToCopy = `wft 500

        overhead "Select vendor owned container to store items. If you select your vendor's root backpack, items will stack and prices will be incorrect."
        setvar vendor_container

        if not listexists itemIDs

            createlist itemIDs

          else

            clearlist itemIDs

        endif
        \n`
      
      itemsToStock.forEach((item) => {
        textToCopy += `pushlist itemIDs '${item.item.id}'` + `\n`
      });

      textToCopy += `if not listexists itemPrices

        createlist itemPrices

    else

        clearlist itemPrices

    endif \n`

      itemsToStock.forEach((item) => {
        textToCopy += `pushlist itemPrices '${item.item.price}'` + `\n`
      });

      textToCopy += `
    overhead "First we add items to vendor and set prices"
    foreach id in itemIDs
        overhead id
        
        wait 250
        
        lift id
        
        wait 250
            
        drop vendor_container 1 0

        wait 250

        //here we set price
        pause 500
        if poplist 'itemPrices' front as 'price'
            overhead 'price'
            promptresponse 'price'
        endif

        wait 250
        
        ignore item


    endfor
    clearignore

    overhead "Your items have been added and priced, don't forget to check if prices had been set correctly."
    `

      const copyScriptBtn = event.target;
      copyScriptBtn.innerText = 'Copied!'
      window.setTimeout(() => {
      
        const copyScriptBtn = document.getElementById('copyStockScriptBtn_' + this.containerID);
        copyScriptBtn.innerText = 'Copy Stock Script'
      }, 2000)
      return this.copyToClipboard(textToCopy);
    },
    exportContents(contents) {
      const dataStr = JSON.stringify(contents, null, 2);
      const blob = new Blob([dataStr], { type: "application/json" });
      const url = URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = url;
      link.download = `container_${this.containerID}_contents.json`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
      URL.revokeObjectURL(url);
    },
   
  }
 
};
</script>

<style scoped>
/* Additional styles can go here if needed */
</style>
