# Dreamland Game #

## Summary ##

Dreamland is a multi-player adventure game.

## Getting started ##

To launch from Eclipse:

[1] Right-click ui package and click "Run as Java Application".

[2] Click on the "Login" button on the welcome screen, enter your name, and click "Ok".

[3] Click on "Host" to start the game server.

[4] Click "Join" to start the game as the first player.

To join the game join game as the 2nd player, follow the same steps above excluding [3].

## Controls   ##

You control player movement with up, down, left, and right arrows.

You automatically pick up collectable objects such as keys and coins by walking over them. They will appear in your inventory.

You can inspect an item in the inventory by right-clicking the image of the item and clicking "Inspect Item". To drop an item in the inventory, you can right-click the image of the item and click "Drop Item". You can use an item, for example eating some candy, by right-clicking the image of the item in the inventory and clicking "Use item".

You can rotate the orientation of the game map by pushing 'r'.

You can defend against enemy attack by standing still and pushing the space bar (causing their health to reduce).

## Packages: ##

#### Control: ####

This package includes the networking elements, as well as the game timer and enemy and player controllers.

#### Model: ####

This package contains most of the game element objects. Most objects are subtypes of the Actor class, which includes its Position on the Board and can return a BoundingBox for collision logic purposes. The Actor subtypes are the moving actors (Player and Enemy), the Container class (which has subtypes CoinBag and Inventory), the Collectable class (which has subtypes Key and coin), as well as Door and Wall.

The GameState class contains a list of the active Actor objects in the game, and is called from other parts of the program to determine current state.

The Collision class is used to run the game collision logic. It is called from the moving Actor objects when they move and gets details of collisions from the GameState object.

#### Save packages: ####

These packages hold the classes that handle serialization and saving.

#### Tests: ####

This package contains all the game tests.

#### UI: ####

This package contains the GUI/Swing elements including the main gameFrame, the splash/login screen, and the information and display panels.

#### View: ####

This package includes the Tile and Object maps, the actor assets, and the rendering classes.

#### Images: ####

This package holds the game images.

## Authors ##
    D Newton
    C Lategan
    K McLean
    B Liao
    A Daly