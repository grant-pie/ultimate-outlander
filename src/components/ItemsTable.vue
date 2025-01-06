<template>
  <div>
    <div class="card-header d-flex justify-content-between align-items-center">
      <h5>Items Table</h5>
      <input
        type="text"
        class="form-control w-25"
        v-model="searchQuery"
        placeholder="Search..."
      />
    </div>
    <table class="table table-striped table-hover mt-3">
      <thead>
        <tr>
          <th scope="col">Description</th>
          <th scope="col">Price</th>
          <th scope="col">Qty</th>
          <th scope="col">Link</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in filteredItems" :key="index">
          <td>{{ item.description }}</td>
          <td>
            <input
              type="number"
              class="form-control"
              v-model.number="item.price"
              @input="updateItemPrice(index, item.price)"
            />
          </td>
          <td> {{ item.qty }} </td>
          <td>
            <a
              :href="generateLink(item.description)"
              target="_blank"
              rel="noopener noreferrer"
            >
              <button class="btn btn-primary">Link</button>
            </a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "ItemsTable",
  props: {
    items: {
      type: Array,
      required: true,
      validator: (items) =>
        items.every(
          (item) =>
            typeof item.description === "string" &&
            typeof item.price === "number" &&
            typeof item.qty === "number"
        ),
    },
  },
  data() {
    return {
      searchQuery: "", // Holds the search input value
    };
  },
  computed: {
    filteredItems() {
      // Filters items based on the search query
      return this.items.filter((item) =>
        item.description.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
  },
  methods: {
    updateItemPrice(index, price) {
      this.$emit("update:items", [
        ...this.items.slice(0, index),
        { ...this.items[index], price },
        ...this.items.slice(index + 1),
      ]);
    },
    generateLink(description) {
      const baseUrl =
        "https://portal.uooutlands.com/vendor-search?searchTerm=";
      const query = encodeURIComponent(description);
      const sortParams = "&sortActive=Price&sortDirection=asc";
      return `${baseUrl}${query}${sortParams}`;
    },
  },
};
</script>

<style scoped>
.card-header {
  background-color: #f8f9fa;
  padding: 1rem;
  border-bottom: 1px solid #dee2e6;
}
</style>
