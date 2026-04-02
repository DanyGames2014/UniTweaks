# UniTweaks
[![wakatime](https://wakatime.com/badge/user/38aa5505-b4f7-4c8d-849e-7c33caecee59/project/018cfcb9-ebf8-4fea-b92d-62b38b8ed951.svg)](https://wakatime.com/badge/user/38aa5505-b4f7-4c8d-849e-7c33caecee59/project/018cfcb9-ebf8-4fea-b92d-62b38b8ed951)

![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)
![java17](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/built-with/java17_vector.svg)
![fabric](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/fabric_vector.svg)
![risugamis-modloader](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/unsupported/risugamis-modloader_vector.svg)

### Note: Nearly all of the listed tweaks and bugfixes are toggleable

## Tweaks
### General
* Disable pausing on lost focus
* Allow for changing the autosave interval (defaults to 30s)
* Enable the TCPNoDelay flag on the network socket
* Packet Speedup Patch - Removes an artificial delay when processing packets (upto 392ms for sending an action and
  receiving a response)
* Raw Input
* Disables the non-functional controller initialization code
* Allow changing the Resource URL (Defaults to Betacraft)
* Allow disabling loading certain dimensions on server

### User Interface
* Photo Mode for taking isometric screenshots of your world
* Show quit button on the main menu
* Improved controls menu
* Hide the achievement toast
* The Done Button in Achievements will lead to ingame menu instead of unpausing
* Disable the entity id nametags when debug menu is open
* Front View Third Person
* Clear text fields using the right mouse button
* FOV Slider with proper hand adjustment
* Brightness Slider
* Cloud Height Slider
* Clouds Toggle
* Fog Density Slider
* GUI Scale Slider
* FPS Limit slider
* Render Distance slider that goes up to 32 chunks
* Main Menu Panorama like in Beta 1.8 with a customizable background (default to the Glacier background)
* Show the version text in game and allow its customization
* Rebindable Hotbar Keys and Function Keys (F1,F2,F3,F5,F6,F11)
* Dismount Keybind
* Keybind for releasing the mouse from the game window
* Armor Outlines in Player Inventory

### Gameplay
* No Food Wastage - Prevents you from eating when your health is already full
* Pick Block from Inventory - Allow picking blocks from inventory
* Shift Placing - Bypassing block's use actions on blocks when crouching
* Right Click Armor Equip - Equip/Swap armor by right-clicking it
* Fence Jumping - Allows you to jump on fences

### Features
* Fast Leaf Decay
* Better Burning
      * Burning Skeletons have a 70% chance of shooting a burning arrow
      * Burning Arrows ignite entities
      * Mobs on fire attacking player have a 30% chance to spread that fire onto the player
* Step Assist - Allows you to step up one block
* Zoom Key
* Sounds for Chest, Item Breaking, Sheep Shearing and Eating

### Tweaks
* Make sugar cane placeable on sand
* Boats drop themselves when broken by a player
* Boats don't break on impact
* Pumpkins and Jack'o'Lanterns placeable like normal - lifted restrictions on the block below having to be solid
* Fences placeable like normal - lifted restrictions on the block below having to be solid or fence
* Fences connect to blocks
* Bookshelves drop 3 books
* Allow placing pressure plates on fences
* Harvestable Cobwebs and Tall Grass using Shears
* Expand the chicken hitbox to modern size
* Stackable Chests
* Prevent damaging Flint and Steel on failed ignite
* Ability to disable sleeping but still allow setting spawnpoints at beds
* Ability to disable spawning mobs when going to sleep (Nightmares)
* Prevent items and arrows from stopping minecarts
* Allow igniting entities using Flint and Steel
* Ability to place trapdoors without supporting block
* Beta 1.8 Leaves Shading

### Old Features
* Disable Dead Bush Generation
* Disable Tall Grass Generation
* Punch TNT to light it
* Punch Primed TNT to defuse it
* Punch Sheep for wool
* Hoes Grass for Seeds
* Boat Elevators
* Ladder Gaps
* Minecart Boosters

### Recipes
* Tool & Armor Repair recipes
* More Furnace Fuels

<details>
    <summary>Modern Recipes</summary>

* Shapeless Flint and Steel
* Shapeless Mushroom Stew
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
* Adjustable Trapdoors per Craft

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
* Far Lands Jitter Fix - Fixes jittering near farlands
* Slime Split Fix - Fixes slimes not splitting when their health is below zero after dying
* Nightmare Pathfinding Fix - Fixes the nightmares being able to path and wake up player thru walls
* Multiplayer Entity Physics Fix - Fixes jittering caused by client interpolation
* Boat Dismount Fix - Fixes sometimes falling through the boat when dismounting it
* Sleeping Camera Rotation Fix - Fixes the sleeping camera being rotated the wrong way
* Stairs Drop Fix - Stairs now drop themselves
* Block Effectiveness Fix - Fixes axes and pickaxes not being effective on various blocks
  * Axe : Crafting Table, Wooden Slab, Wooden Stairs, Fence, Wooden Door, Ladder, Sign, Pumpkin, Jack o' Lantern,
  Wooden Pressure Plate, Jukebox, Noteblock, Trapdoor
  * Pickaxe : Furnace, Cobblestone Stairs, Bricks, Redstone Ore, Iron Door, Rails, Dispenser, Stone Pressure Plate, 
  Spawner, Buttons
  * Shovel : Soul Sand
* Pig Drop Saddle Fix - Fixes saddled pig not dropping saddle on death
* Fence Bounding Box Fix - Fence's bounding box now better reflect its current shape
* Pick Block Fix - Fixes some blocks not being pickable using Pick Block
* Spring Propagation Fix - Fixes water source blocks not forming when a block below is water
* Lava Without Source Fix - Flowing lava now correctly disappears when source block is removed
* Liquid Block Drop Fix - Fixes liquid flowing down from above not dropping items such as torches and rails when broken
* Bow Held Fix - Bows are now being held correctly and not as only items
* Leggings Riding Fix - Fix leggings not adjusting while riding
* ItemStack Rendering Fix - Fixes itemstacks being render below text in containers
* Fish Velocity Fix - Fix fish flying way behind the player when caught
* Furnace Consume Bucket Fix - Fixes furnace consuming bucket when fueled with lava
* Armor Icon Fix - Fixes armor icons not being displayed
* Dropped Item Size Fix - Fixes some blocks being too large when dropped
* Breaking Animation Fix - Fixes the breaking animation sometimes not being visible from below
* Death Screen Formatting Fix - Fixes the score color on death screen
* Hotbar Rendering Fix - Fixes hotbar turning white when looking at entity with no clouds rendering on Fast graphics
* Wooden Slab Mining Fix - Fixes wooden slabs only being mineable with a pickaxe
* Grass Block Item Fix - Fixes Grass Block item not rendering properly
* Multiplayer Block Mining Delay Fix - Fixes the inconsistent delay between mining blocks in multiplayer
* Last Durability Fix - Fixes blocks not dropping when mined with the last durability point of the tool
* Fence Lighting Fix - Fixes fence bottom face being dark when placed on a block
* Video Settings Slider Fix - Fixes sliders not being slidable in the video settings screen
* Fullscreen Cursor Fix - Fixes cursor not being centered when opening inventories in fullscreen
* Torch Bottom Texture Fix - Fixes torches not having a bottom textures
* Slab Crash Fix - Fixes slabs with no name crashing the game
* Block Entity Loading Fix - Stops the game from erasing an entire chunk when block entity fails to load