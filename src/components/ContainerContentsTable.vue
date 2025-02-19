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
          <th scope="col">Stack Size</th>
          <th scope="col">Total</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(content, index) in filteredContents" :key="index">
          <td>{{ content.item.description }}</td>
          <td>{{ content.item.id }}</td>
          <td>
            <p>{{ content.stack_size }}</p>
          </td>
          <td>
            <button @click="copyGetItemScript(containerID, content.item.id)" class="btn btn-primary">Get</button>
          </td>
          <td>{{ calculateTotal(content.item.price, content.stack_size) }}</td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="2" class="text-end"><strong>Grand Total:</strong></td>
          <td>{{ grandTotal }}</td>
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
            typeof content.item.description === "string" &&
            typeof content.item.price === "number" &&
            typeof content.stack_size === "number"
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
        return sum + content.item.price * content.stack_size;
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
