# README #

# Dreamland Game #

### Summary ###

Dreamland is a multi-player adventure game.

### Getting started ###

To launch from Eclipse:
1. Click "Run as Java Application" with "UIMain" configuration.
2. Click on the "Login" button on the welcome screen, enter your name, and click "Ok".
3. Click on "Host" to start the game server.
4. Click "Join" to start the game as the first player.

>>>>>> to add: join game as 2nd player.

### Controls   ###

Player movement is controlled with up, down, left, and right arrows. 
Collectable objects such as keys and coins are automatically picked up when you walk over them
You can inspect an item in the inventory by right-clicking the image of the item and clicking "Inspect Item".
You can drop an item in the inventory by right-clicking the image of the item and clicking "Drop Item".

>>>>>> to add: rotate screen


### Packages: ###
     
1. Control: 

>>>>>

2. Model:

This package contains most of the game element objects. Most objects are subtypes of the Actor class, which includes its Position on the Board and can return a BoundingBox for collision logic purposes. The Actor subtypes are the moving actors (Player and Enemy), the Container class (which has subtypes CoinBag and Inventory), the Collectable class (which has subtypes Key and coin), as well as Door and Wall.

The GameState class contains a list of the active Actor objects in the game, and is called from other parts of the program to determine current state.

The Collision class is used to run the game collision logic. It is called from the moving Actor objects when they move and gets details of collisions from the GameState object.

3. Save packages - save, save.actor, save.boundingbox, save gamestate, save.position:

>>>>>

4. Tests

This class contains all the game tests.

5. UI

>>>>>

6. View

>>>>>
    
### Authors ###
     D Newton
     C Lategan
     K McLean
     B Liao
     A Daly

##