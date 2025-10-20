Ultimate Outlander
A free web-based tool for Ultima Online Outlands players to automate vendor management through container cataloging and Razor script generation.

🎮 What is Ultimate Outlander?
Ultimate Outlander streamlines the tedious process of stocking and pricing items on your UO Outlands vendor. Instead of manually cataloging items and setting prices one by one, this tool generates Razor scripts that automate the entire workflow - from cataloging container contents to restocking your vendor with accurate pricing.

✨ Features

Automated Container Cataloging - Generate Razor scripts to catalog all items in your containers
Item Pricing Management - Set and manage prices for all your items in one place
Vendor Stock Script Generation - Automatically generate scripts to restock your vendor with correct pricing
Market Price Integration - Quick links to UO Outlands vendor search for competitive pricing
JSON Import/Export - Save and reuse your pricing data across sessions
Multi-Container Support - Catalog and manage multiple containers simultaneously
Comprehensive Item Support - Works with phylacteries, aspect cores, skill scrolls, maps, equipment, and more

🚀 Quick Start
Step 1: Generate Catalog Script

Visit Ultimate Outlander
Copy either the "Catalog Loot" or "Catalog Equipment" script
Paste the script into Razor's script editor

Step 2: Run the Script in Game

Run the catalog script in Razor
Select the containers you want to catalog when prompted
Log out of the game to generate a journal log file

Step 3: Import Journal Log

Find your journal log in: Game Folder\ClassicUO\Data\Client\JournalLogs
Copy the contents of the latest journal log file
Paste into Ultimate Outlander's import box and click "Import"

Step 4: Set Prices

Review the imported items in the pricing table
Use the vendor search links to check market prices
Set your prices for each item
Export your pricing as JSON for future use (optional)

Step 5: Generate Vendor Stock Script

Copy the generated vendor stock script
Run it in Razor
Select your vendor-owned container when prompted
The script will automatically stock and price all items

Step 6: Verify
Always double-check item prices in-game before finalizing!
📦 Supported Item Types
Loot Items

Phylacteries
Aspect Cores (including Chroma Cores)
Aspect Distillations
Skill Mastery Scrolls
Treasure Maps

Equipment

All armor types (Leather, Studded, Ringmail, Chainmail, Platemail, Bone)
Weapons (Swords, Axes, Maces, Staves, Bows, Crossbows, Daggers, Spears)
Shields
Musical Instruments
And more...

🛠️ Technology Stack

Vue 3 - Progressive JavaScript framework
Vite - Next generation frontend tooling
Bootstrap 5 - UI component library
Google Fonts - Cinzel serif font

💻 Local Development
Prerequisites

Node.js (v14 or higher)
npm or yarn

Installation
# Clone the repository
git clone https://github.com/yourusername/ultimate-outlander.git

# Navigate to project directory
cd ultimate-outlander

# Install dependencies
npm install

# Start development server
npm run dev

The application will be available at http://localhost:5173

Build for Production
# Create production build
npm run build

# Preview production build
npm run preview
```

## 📁 Project Structure
```
ultimate-outlander/
├── public/
│   └── images/
│       ├── logo.png
│       └── store_menu.jpg
├── src/
│   ├── components/
│   │   ├── ContainerContentsTable.vue
│   │   └── ItemPricingTable.vue
│   ├── data/
│   │   └── item_types.json
│   ├── App.vue
│   └── main.js
├── index.html
├── package.json
└── README.md

🎯 Use Cases
For New Vendors

Quickly catalog your inventory
Research competitive market prices
Set up your vendor with accurate pricing

For Established Vendors

Streamline restocking process
Maintain consistent pricing across sessions
Save time on repetitive tasks

For Bulk Sellers

Manage multiple containers efficiently
Export/import pricing data for reuse
Handle large inventories with ease

⚠️ Important Notes
Vendor Container Setup
When running the vendor stock script, you must select a vendor-owned container that is set to "not for sale." If you select your vendor's root backpack, items will stack together and prices will be incorrect.

Price Verification
Always double-check item prices in-game before finalizing your vendor stock. While Ultimate Outlander automates the process, it's good practice to verify pricing, especially for high-value items.
Browser Compatibility
Ultimate Outlander works best in modern browsers (Chrome, Firefox, Edge, Safari). JavaScript must be enabled.
🐛 Known Issues

Some special characters in item descriptions may need manual verification
Very large journal logs (1000+ items) may take a few seconds to process
Browser storage is not used by default (can be enabled in code comments)

🤝 Contributing
Contributions are welcome! If you'd like to contribute:

Fork the repository
Create a feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request

📝 Feature Requests & Bug Reports
Have an idea for a new feature or found a bug? Please submit feedback through our feedback form.
📜 License
This project is licensed under the MIT License - see the LICENSE file for details.
🙏 Acknowledgments

Built for the Ultima Online Outlands community
Designed to work with ClassicUO and Razor
Special thanks to all beta testers and contributors

🔗 Links

Live Site: https://yourusername.github.io/ultimate-outlander
UO Outlands: https://www.uooutlands.com
ClassicUO: https://www.classicuo.eu
Feedback Form: Submit Feedback

📧 Contact
For questions or support, please use the feedback form or open an issue on GitHub.