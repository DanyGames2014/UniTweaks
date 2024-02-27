# UniTweaks
[![wakatime](https://wakatime.com/badge/user/38aa5505-b4f7-4c8d-849e-7c33caecee59/project/018cfcb9-ebf8-4fea-b92d-62b38b8ed951.svg)](https://wakatime.com/badge/user/38aa5505-b4f7-4c8d-849e-7c33caecee59/project/018cfcb9-ebf8-4fea-b92d-62b38b8ed951)

![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)
![java17](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/built-with/java17_vector.svg)
![fabric](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/fabric_vector.svg)
![risugamis-modloader](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/unsupported/risugamis-modloader_vector.svg)

## Tweaks
### General
* Show Quit Button on the Main Menu
* FOV Slider with proper hand adjustment
* Fog Density Slider
* Render Distance slider that goes up to 32 chunks
* Improved Controls Menu
* Packet Speedup Patch - Removes an artificial delay when processing packets (upto 392ms for sending an action and
  receiving a response) ***non-configurable***
* Text field clearing with Right Mouse Button
* Rebindable Hotbar Keys and Function Keys (F1,F2,F3,F5,F6,F11)
* Dismount Key
* FPS Limit slider
* Armor Outlines in Player Inventory

### Gameplay
* No Food Wastage - Prevents you from eating when your health is already full
* Step Assist - Allows you to step up one block
* Pick Block from Inventory -- Allow picking blocks from inventory
* Shift Placing - Bypassing use actions on blocks when crouching
* Right Click Armor Equip - Equip/Swap armor by right-clicking it
* Fence Jumping - Allows you to jump on fences

### Features
* Fast Leaf Decay
* Better Burning
    * Burning Skeletons have a 70% chance of shooting a burning arrow
    * Burning Arrows ignite entities
    * Mobs on fire attacking player have a 30% chance to spread that fire onto the player
* Zoom Key

### Tweaks
* Sugar Cane can be placed on sand (from Beta 1.8)
* Boats drop themselves when broken by a player
* Fences placeable like normal - lifted restrictions on the block below having to be solid or fence
* Fences connect to blocks
* Bookshelves drop 3 books
* Allow placing pressure plates on fences
* [WIP] Better boat handling
* Harvestable Cobwebs and Tall Grass using Shears
* Prevent damaging Flint and Steel on failed ignite
* Ability to disable sleeping but still allow setting spawnpoints at beds
* Ability to disable spawning mobs when going to sleep (Nightmares)
* Ability to prevent Items and Arrows from stopping Minecarts
* Ignite Entities using Flint and Steel
* Ability to place trapdoors without supporting block

### Old Features
* Disable Dead Bush Generation
* Disable Tall Grass Generation
* Punch TNT to light it
* Punch Sheep for wool

### Recipes
* Tool Repair recipes
* More Furnace Fuels

<details>
    <summary>Modern Recipes</summary>

* Shapeless Flint and Steel
* Shalepess Mushroom Stew
* Shapeless Chest Minecart
* Shapeless Furnace Minecart
* Shapeless Sticky Piston
* Books Require Leather
* Wool Redyeing
* 6 Slabs per Craft
* Button requires 1 stone
* Modern Fence Recipe
* Snow Layer Recipe
* 3 Ladder per Craft

</details>

<details>
    <summary>Tweaked Recipes</summary>

* Shapeless Jack o' Lantern
* Adjustable Stairs per Craft

</details>

<details>
    <summary>Obtainable Recipes</summary>

* Craftable Grass Blocks
* Craftable Cobwebs
* Craftable Fire
* Craftable Coal Ore
* Craftable Iron Ore
* Craftable Gold Ore
* Craftable Lapis Ore
* Craftable Diamond Ore

</details>

## Bugfixes
* Bit Depth Fix - Fixes Z-Fighting on AMD graphic cards
* Slime Split Fix - Fixes slimes not splitting when their health is below zero after dying
* Stairs Drop Fix - Stairs now drop themselves
* Boat Dismount Fix - Fixes sometimes falling through the boat when dismounting it
* Block Effectiveness Fix - Fixes axes and pickaxes not being effective on various blocks
    * Axe : Crafting Table, Wooden Slab, Wooden Stairs, Fence, Wooden Door, Ladder, Sign, Pumpkin, Jack o' Latern,
      Wooden Pressure Plate, Jukebox and Noteblock
    * Pickaxe : Furnace, Cobblestone Stairs, Bricks, Redstone Ore, Iron Door, Rails, Dispenser, Stone Pressure Plates
      and Spawner
* Pig Drop Saddle Fix - Fixes saddled pig not dropping saddle on death
* Multiplayer Entity Physics Fix - Fixes jittering caused by client interpolation
* Fence Bounding Box Fix - Fence's bounding box now better reflect its current shape
* Pick Block Fix - Fixes some blocks not being pickable using Pick Block
* Spring Propagation Fix - Fixes water source blocks not forming when a block below is water
* Lava Without Source Fix - Flowing lava now correctly dissapears when source block is removed
* Bow Held Fix - Bows are now being held correctly and not as only items
* Leggings Riding Fix - Fix leggings not adjusting while riding
* Video Settings Slider Fix - Fixes sliders not being slidable in the video settings screen
* Fullscreen Cursor Fix - Fixes cursor not being centered when opening inventories in fullscreen
* Furnace Consume Bucket Fix - Fixes furnace consuming bucket when fueled with lava
* Fish Velocity Fix - Fix fish flying way behind the player when caught
* Torch Bottom Texture Fix - Fixes torches not having a bottom textures
* Grass Block Item Fix - Fixes Grass Block item not rendering properly
* Slab Crash Fix - Fixes slabs with no name crashing the game