<template>
  <div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">Item Description</th>
          <th scope="col">Stack Size</th>
          <th scope="col">Total</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(content, index) in containerContents" :key="index">
          <td>{{ content.item.description }}</td>
          <td>
            <p>{{  content.stack_size }}</p>
          </td>
          <td>{{ calculateTotal(content.item.price, content.stack_size)  }}</td>
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
  computed: {
    grandTotal() {
      return this.containerContents.reduce((sum, content) => {
        return sum + content.item.price * content.stack_size;
      }, 0);
    },
  },
  methods: {
    updateQuantity(index, stack_size) {
      this.$emit("update:containerContents", [
        ...this.containerContents.slice(0, index),
        { ...this.containerContents[index], stack_size },
        ...this.containerContents.slice(index + 1),
      ]);
    },
    calculateTotal(price, stack_size) {
      return price * stack_size;
    }
  },
  filters: {
    currency(value) {
      return new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      }).format(value);
    },
  },
};
</script>

<style scoped>
/* Additional styles can go here if needed */
</style>
 