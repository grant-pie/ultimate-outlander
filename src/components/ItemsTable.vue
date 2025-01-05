<template>
    <div>
      <table class="table table-striped table-hover">
        <thead class="table">
          <tr>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Link</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in items" :key="index">
            <td>{{ item.description }}</td>
            <td>
              <input
                type="number"
                class="form-control"
                v-model.number="item.price"
                @input="updateItemPrice(index, item.price)"
              />
            </td>
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
              typeof item.price === "number"
          ),
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
        const baseUrl = "https://portal.uooutlands.com/vendor-search?searchTerm=";
        const query = encodeURIComponent(description);
        const sortParams = "&sortActive=Price&sortDirection=asc";
        return `${baseUrl}${query}${sortParams}`;
      },
    },
  };
  </script>
  
  <style scoped>
  /* Additional styles can go here if needed */
  </style>
  