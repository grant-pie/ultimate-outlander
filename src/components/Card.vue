<template>
    <div class="card border shadow">
      <div class="card-header text-light bg-primary">
        {{ cardHeader }}
      </div>
      <div class="card-body">
        <slot />
      </div>
    </div>
  </template>
  
  <script>
  import { defineComponent } from "vue";
  
  export default defineComponent({
    name: "Card",
    props: {
      cardHeader: {
        type: String,
        required: true,
      },
    },
  });
  </script>
  
  <style scoped>
  .card {
    border-radius: 0.25rem;
  }
  </style>
  