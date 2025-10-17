<template>
  <div>
    <div class="card-header bg-dark text-light d-flex justify-content-between align-items-center">
      <h5>Items Table</h5>
      <label for="items-search" class="visually-hidden">Search items</label>
      <input
        id="items-search"
        type="text"
        class="form-control w-25 bg-dark border-light"
        v-model="searchQuery"
        placeholder="Search..."
        aria-label="Search items by description"
        role="searchbox"
        aria-controls="items-table-body"
      />
    </div>
    <div class="table-responsive">
      <table 
      class="table table-dark table-striped table-hover mt-3"
      role="table"
      aria-label="Items price list"
    >
      <caption class="visually-hidden">
        List of {{ sortedItems.length }} items with prices
      </caption>
      <thead>
        <tr>
          <th 
            scope="col" 
            :aria-sort="getSortDirection('description')"
            @click="sortBy('description')"
            class="sortable-header"
            role="button"
            tabindex="0"
            @keypress.enter="sortBy('description')"
          >
            Description
            <span class="sort-icon" aria-hidden="true">{{ getSortIcon('description') }}</span>
          </th>
          <th 
            scope="col"
            :aria-sort="getSortDirection('type')"
            @click="sortBy('type')"
            class="sortable-header"
            role="button"
            tabindex="0"
            @keypress.enter="sortBy('type')"
          >
            Type
            <span class="sort-icon" aria-hidden="true">{{ getSortIcon('type') }}</span>
          </th>
          <th 
            scope="col"
            :aria-sort="getSortDirection('price')"
            @click="sortBy('price')"
            class="sortable-header"
            role="button"
            tabindex="0"
            @keypress.enter="sortBy('price')"
          >
            Price
            <span class="sort-icon" aria-hidden="true">{{ getSortIcon('price') }}</span>
          </th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody id="items-table-body">
        <tr v-for="(item, index) in sortedItems" :key="index">
          <td>{{ item.description }}</td>
          <td>{{ item.type }}</td>
          <td>
            <label :for="'price-' + index" class="visually-hidden">
              Price for {{ item.description }}
            </label>
            <input
              :id="'price-' + index"
              type="number"
              class="form-control bg-dark border-light text-light"
              :class="{ 'is-invalid': priceErrors[index] }"
              v-model.number="item.price"
              @input="updateItemPrice(index, item.price)"
              @blur="validateItemPrice(index, item.price)"
              min="0"
              max="1000000000"
              step="1"
              :aria-label="'Price for ' + item.description"
              :aria-invalid="!!priceErrors[index]"
              :aria-describedby="priceErrors[index] ? 'price-error-' + index : undefined"
            />
            <div 
              v-if="priceErrors[index]" 
              class="invalid-feedback"
              :id="'price-error-' + index"
              role="alert"
              aria-live="polite"
            >
              {{ priceErrors[index] }}
            </div>
          </td>
          <td>
            <a
            :href="generateLink(item.description, item.type)"
            target="_blank"
            rel="noopener noreferrer"
            :aria-label="'Search for ' + item.description + ' on UO Outlands vendor search (opens in new tab)'"
            class="btn btn-primary"
            title="Search on UO Outlands"
            >
            <span aria-hidden="true">🔗</span>
            <span class="visually-hidden">Opens in new tab</span>
            </a>
          </td>
        </tr>
  
      </tbody>
    </table>
    </div>
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
            typeof item.type === "string" &&
            typeof item.price === "number"
        ),
    },
  },
  data() {
    return {
      searchQuery: "",
      priceErrors: {},
      sortColumn: null,
      sortDirection: 'asc'
    };
  },
  computed: {
    filteredItems() {
      return this.items.filter((item) =>
        item.description.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
    sortedItems() {
      if (!this.sortColumn) {
        return this.filteredItems;
      }

      return [...this.filteredItems].sort((a, b) => {
        let aVal = a[this.sortColumn];
        let bVal = b[this.sortColumn];

        // Handle string comparison (case-insensitive)
        if (typeof aVal === 'string' && typeof bVal === 'string') {
          aVal = aVal.toLowerCase();
          bVal = bVal.toLowerCase();
        }

        let comparison = 0;
        if (aVal > bVal) {
          comparison = 1;
        } else if (aVal < bVal) {
          comparison = -1;
        }

        return this.sortDirection === 'asc' ? comparison : -comparison;
      });
    }
  },
  methods: {
    sortBy(column) {
      if (this.sortColumn === column) {
        // Toggle direction if clicking the same column
        this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
      } else {
        // New column, default to ascending
        this.sortColumn = column;
        this.sortDirection = 'asc';
      }
    },
    getSortIcon(column) {
      if (this.sortColumn !== column) {
        return '⇅';
      }
      return this.sortDirection === 'asc' ? '↑' : '↓';
    },
    getSortDirection(column) {
      if (this.sortColumn !== column) {
        return 'none';
      }
      return this.sortDirection === 'asc' ? 'ascending' : 'descending';
    },
    validateItemPrice(index, price) {
      const validation = validatePrice(price);
      
      if (!validation.valid) {
        this.priceErrors[index] = validation.errors.join(', ');
      } else {
        delete this.priceErrors[index];
      }
      
      this.priceErrors = { ...this.priceErrors };
    },
    
    updateItemPrice(index, price) {
      const validation = validatePrice(price);
      
      if (validation.valid) {
        this.$emit("update:items", [
          ...this.items.slice(0, index),
          { ...this.items[index], price: validation.value },
          ...this.items.slice(index + 1),
        ]);
        
        delete this.priceErrors[index];
        this.priceErrors = { ...this.priceErrors };
      } else {
        this.priceErrors[index] = validation.errors[0];
        this.priceErrors = { ...this.priceErrors };
      }
    },
    generateLink(description, itemType) {
      const baseUrl =
        "https://portal.uooutlands.com/vendor-search?searchTerm=";
        if (itemType === "skill mastery scroll") {
          description = description.replace(" (used to increase a player's skill cap for a skill by 1)", "");
        }
      const query = encodeURIComponent(description);
      const sortParams = "&sortActive=Price&sortDirection=asc";
      return `${baseUrl}${query}${sortParams}`;
    }
    
  },
  
};

function validatePrice(price) {
  const errors = [];
  
  if (price === null || price === undefined) {
    errors.push('Price is required');
    return { valid: false, errors };
  }
  
  let numPrice = price;
  if (typeof price === 'string') {
    numPrice = parseFloat(price);
  }
  
  if (typeof numPrice !== 'number' || isNaN(numPrice)) {
    errors.push('Price must be a valid number');
    return { valid: false, errors };
  }
  
  if (!isFinite(numPrice)) {
    errors.push('Price must be a finite number');
  }
  
  if (numPrice < 0) {
    errors.push('Price cannot be negative');
  }
  
  if (numPrice > 1000000000) {
    errors.push('Price too large (maximum 1,000,000,000)');
  }
  
  const decimalPlaces = (numPrice.toString().split('.')[1] || '').length;
  if (decimalPlaces > 2) {
    errors.push('Price can have at most 2 decimal places');
  }
  
  return {
    valid: errors.length === 0,
    errors,
    value: numPrice
  };
}
</script>

<style scoped>
.card-header {
  background-color: #f8f9fa;
  padding: 1rem;
  border-bottom: 1px solid #dee2e6;
}

.sortable-header {
  cursor: pointer;
  user-select: none;
  transition: background-color 0.2s;
}

.sort-icon {
  margin-left: 0.5rem;
  font-size: 0.9em;
  opacity: 0.6;
}

.sortable-header:hover .sort-icon {
  opacity: 1;
}

.visually-hidden {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  white-space: nowrap;
  border-width: 0;
}
</style>