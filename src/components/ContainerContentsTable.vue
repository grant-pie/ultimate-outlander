<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-2">
      <div class="d-flex flex-column">
        <h5>Container Contents</h5>
        <p>Item Count: {{ containerContents.length }}</p>
      </div>
      <label for="container-search" class="visually-hidden">Search container contents</label>
      <input
        id="container-search"
        type="text"
        class="form-control w-25 bg-dark border-light text-light"
        v-model="searchQuery"
        placeholder="Search..."
        aria-label="Search container contents by description"
        role="searchbox"
        :aria-controls="'container-table-' + containerID"
      />
    </div>
    <div 
    role="status" 
    aria-live="polite" 
    aria-atomic="true"
    class="visually-hidden"
    >
    {{ copyStatus }}
    </div>
    <div class="table-responsive">
      <table class="table table-dark table-striped table-hover">
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
            Item Description
            <span class="sort-icon" aria-hidden="true">{{ getSortIcon('description') }}</span>
          </th>
          <th 
            scope="col"
            :aria-sort="getSortDirection('id')"
            @click="sortBy('id')"
            class="sortable-header"
            role="button"
            tabindex="0"
            @keypress.enter="sortBy('id')"
          >
            Item Serial
            <span class="sort-icon" aria-hidden="true">{{ getSortIcon('id') }}</span>
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
      <tbody>
        <tr v-for="(content, index) in sortedContents" :key="index">
          <td>{{ toTitleCase(content.item.description) }}</td>
          <td>{{ content.item.id }}</td>
          <td>{{ content.item.price }}</td>
          <td class="d-flex">

            <button 
              @click="$emit('removeItem', content.item.id)" 
              class="btn btn-primary btn-sm"
              :aria-label="'Remove ' + content.item.description + ' from container'"
              type="button"
            >
              <span aria-hidden="true">🗑️</span>
            </button>
            <button 
            @click="copyGetItemScript(content.item.id, $event)" 
            :id="'copyGetItemScriptBtn_' + content.item.id"
            class="btn ms-2 btn-primary btn-sm"
            :aria-label="'Copy script to get ' + content.item.description"
          >
          
            Copy Get Item Script
          </button>
          </td>

        </tr>
      </tbody>
      <tfoot>
      <tr>
        <th colspan="3" scope="row" class="text-end">Grand Total:</th>
          <td>
            <strong aria-label="Grand total: {{ grandTotal }} gold">
              {{ grandTotal }}
            </strong>
          </td>
        </tr>
      </tfoot>
    </table>
    </div>
    <button 
      type="button" 
      class="btn w-100 btn-primary mb-4" 
      @click="exportContents(containerContents)"
      :aria-label="'Export contents of container ' + containerID + ' as JSON'"
      aria-describedby="exportHelp"
    >
      <span aria-hidden="true">💾</span> Export Contents
    </button>
    <div id="exportHelp" class="visually-hidden">
      Downloads a JSON file with all items in this container
    </div>
    <button 
    :id="'copyStockScriptBtn_' + containerID"  
    type="button" 
    class="btn w-100 btn-primary" 
    @click="copyVendorStockScript(containerContents)"
    aria-label="Copy vendor stock script to clipboard"
  >      <span aria-hidden="true">📋</span>
    Copy Vendor Stock Script
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
      searchQuery: "",
      copyStatus: "",
      sortColumn: null,
      sortDirection: 'asc'
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
    sortedContents() {
      if (!this.sortColumn) {
        return this.filteredContents;
      }

      return [...this.filteredContents].sort((a, b) => {
        let aVal = a.item[this.sortColumn];
        let bVal = b.item[this.sortColumn];

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
    },
    grandTotal() {
      return this.sortedContents.reduce((sum, content) => {
        return sum + content.item.price;
      }, 0);
    },
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
    copyGetItemScript(itemID, event) {
      const text = `
        lift ${itemID}
        wait 250
        drop backpack
      `;
      
      this.copyToClipboard(text);
      
      // Announce to screen readers
      this.copyStatus = 'Script copied to clipboard';
      
      // Update button text
      const copyScriptBtn = event.target;
      const originalText = copyScriptBtn.textContent;
      copyScriptBtn.textContent = 'Copied!';
      
      window.setTimeout(() => {
        const btn = document.getElementById('copyGetItemScriptBtn_' + itemID);
        if (btn) {
          btn.textContent = originalText;
        }
        // Clear the announcement
        this.copyStatus = '';
      }, 2000);
    },
    generateVendorStockScript(itemsToStock) {
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

      return textToCopy;
    },

    copyVendorStockScript(itemsToStock) {
      const script = this.generateVendorStockScript(itemsToStock);
      navigator.clipboard.writeText(script).then(() => {
        const copyScriptBtn = document.getElementById('copyStockScriptBtn_' + this.containerID);
        if (copyScriptBtn) {
          const originalHTML = copyScriptBtn.innerHTML; // Store original HTML
          copyScriptBtn.innerHTML = '<span aria-hidden="true">✓</span> Copied!';
          window.setTimeout(() => {
            const btn = document.getElementById('copyStockScriptBtn_' + this.containerID);
            if (btn) btn.innerHTML = originalHTML; // Restore original HTML
          }, 2000);
        }
      });
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
    toTitleCase(str) {
      if (!str) return '';
      
      const smallWords = /^(a|an|and|as|at|but|by|for|from|in|into|of|on|or|the|to|with)$/i;
      
      return str
        .toLowerCase()
        .split(' ')
        .map((word, index, array) => {
          // Check if word starts with a special character
          const startsWithSpecial = /^[^a-zA-Z0-9]/.test(word);
          
          if (startsWithSpecial && word.length > 1) {
            // Keep the special character(s) and capitalize the first letter
            const firstLetterIndex = word.search(/[a-zA-Z]/);
            if (firstLetterIndex !== -1) {
              return word.slice(0, firstLetterIndex) + 
                    word.charAt(firstLetterIndex).toUpperCase() + 
                    word.slice(firstLetterIndex + 1);
            }
            return word;
          }
          
          // Always capitalize first and last word
          if (index === 0 || index === array.length - 1) {
            return word.charAt(0).toUpperCase() + word.slice(1);
          }
          
          // Don't capitalize small words
          if (smallWords.test(word)) {
            return word;
          }
          
          // Capitalize everything else
          return word.charAt(0).toUpperCase() + word.slice(1);
        })
        .join(' ');
    }
   
  }
 
};
</script>

<style scoped>
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