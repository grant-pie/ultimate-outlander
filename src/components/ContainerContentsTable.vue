<script setup>
function copyToClipboard(textToCopy){
  navigator.clipboard.writeText(textToCopy);
}
function copyStockScript(itemsToStock, event) {
  console.log({log:itemsToStock})
  let textToCopy = `
  wft 500
setvar vendor_container
  if not listexists itemIDs

    createlist itemIDs

else

    clearlist itemIDs

endif \n`
  
  itemsToStock.forEach((item) => {
    console.log(item.item.id)
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

  textToCopy += `foreach id in itemIDs
    overhead id
    
    wait 250
    
    lift id
    
    wait 250
        
    drop vendor_container 1 0 0

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
clearignore`

  //show popoover that hides on next click
  const copyScriptBtn = event.target;
  copyScriptBtn.innerText = 'Copied!'
  window.setTimeout(() => {
  
    const copyScriptBtn = document.getElementById('copyStockScriptBtn');
    copyScriptBtn.innerText = 'Copy Stock Script'
  }, 2000)
  return copyToClipboard(textToCopy);
}

</script>


<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-2">
      <h5>Container Contents</h5>
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
        </tr>
      </thead>
      <tbody>
        <tr v-for="(content, index) in filteredContents" :key="index">
          <td>{{ content.item.description }}</td>
          <td>{{ content.item.id }}</td>
          <td>{{ content.item.price }}</td>
          <td>
            <button @click="copyGetItemScript(containerID, content.item.id)" class="btn btn-primary">Get</button>
          </td>

        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="4" class="text-end"><strong>Grand Total:</strong></td>
          <td>{{ grandTotal }}</td>
        </tr>
        <tr> 
          <td colspan="4" class="text-end">
            <button 
            id="copyStockScriptBtn"  type="button" class="btn btn-primary ms-4" @click="copyStockScript(containerContents, $event)">Copy Stock Script
            </button>
          </td> 
        </tr>
      </tfoot>
    </table>
  </div>
</template>

<script>
export default {
  name: "ContainerContentsTable",
  props: {
    containerID: {
      type: String,
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
    copyGetItemScript(containerID, itemID){
      const text =  `
        if find ${itemID} ${containerID} as item
            
            overhead 'item found'
            
            lift item
            
            wait 250
            
            drop backpack
            
        else 
                
            overhead 'item not found'
        endif
        `;
      this.copyToClipboard(text);
    }
  },
};
</script>

<style scoped>
/* Additional styles can go here if needed */
</style>
