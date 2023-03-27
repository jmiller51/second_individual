Hello, and welcome to the map game!
This program simulates a text-based adventure game where the player can navigate between different rooms, pick up different items, and solve various puzzles.



<Where does the data for the rooms, items, and puzzles come from?>
The data for the rooms is stored in a text file called Rooms.txt. For items, the data is stored in an Items.txt, and for puzzles, the data is stored in a Puzzles.txt.

The format of the Rooms file consists of one room per line. Each line is constructed as such:
[Room ID],[Room Name],[North Room],[East Room],[South Room],[West Room]

Room ID (int): Each room is assigned a unique integer ID to differentiate them from each other.
Room Name (String): Each room is designated a name for theming.
North/East/South/West Room (int): Each room contains the integer ID of the room to its north, to its east, to its south, and to its west.

Each room also has a visited boolean field which cannot be edited by the user. This field keeps track of whether a room has been visited previously and is used to print out a unique message if the user visits a room they have already visited.

The format of the Items and Puzzles files are a little different. Each piece of data is stored on a different line with different instances of the data type separated by a line of three dashes.

Each item consists of three lines: the name of the item, the description of the item, and the room in which the item spawns at the start of the game. Each puzzle consists of five lines: the name of the puzzle, the puzzle text that is displayed in-game, the answer to the puzzle, the number of attempts given, and the room which must be entered to activate the puzzle.



<What classes does this program use?>
Room: This class stores the ID of the room, its name, the rooms adjacent to the room in all four directions, the items present in the room, any potential puzzle which may be in the room, and whether the room has been previously visited. The class has getters and setters for the various attributes. It also contains methods to check if the room has a puzzle, to remove the puzzle from the remove, to add and remove items from the room, to list all items present in the room, to mark a room as visited, and to check if the room has been previously visited.

Player: This class stores the player's current room and their inventory. This class has getters and setters for the two attributes as well as methods to print all items in the inventory and to add and remove items to the inventory.

Item: This class stores the item's name, description, and starting location. In terms of methods, it only includes getters and setters.

Puzzle: This class stores the item's name, displayed text, answer, number of attepts, and room in which it is located. Like item, the only methods in this class are the getters and setters.

Game: This is where the entire game is located. This class reads the data files, converts the data into a readable format to set up the game, and performs the key gameplay loop.



<What commands are available for the user to use?>
Note: All commands are case insensitive.

"North"/"N", "East"/"E", "West"/"W", and "South"/"S": attempts to move the user to the room adjacent to the current room in the given direction. If there is no room in that direction, the program informs the user and nothing happens. If there is a room in that direction, the program changes the player's current room to that room, marks it as visited, checks to see if the room has been visited previously and outputs a special message if it has, and checks if the room has a puzzle in it in order to check whether to activate puzzle mode.

"Explore": Lists all items that are currently in the room that the player is in.

"Inventory": Lists all items that are currently in the player's inventory.

"Inspect [item]": Checks if the given item is in the player's current room. If the item is in the room, the program outputs the description of the item; otherwise, the player will be told that the given item is not in the room.

"Pickup [item]": Checks if the given item is in the player's current room. If the item is in the room, the program removes the item from the player's current room adds the item to the player's inventory; otherwise, the player will be told that the given item is not in the room.

"Drop [item]": Checks if the given item is in the player's inventory. If the item is in the player's inventory, the item will be removed from the player's inventory and added to the player's current room; otherwise, the player will be told that the given item is not in their inventory.

"Quit": Exits the program

Puzzle mode commands: While the program is in puzzle mode, all commands will be interpreted as a guess to the answer of the puzzle. Once the user sends a number of incorrect guesses equal to the given number of attempts or if the user guesses the correct answer, the program will exit puzzle mode and resume normal operations.

<What does the default map look like?>

            [1]

             |

[2]    -    [3]    -    [4]

                         |
			 
                        [5]

                         |

                        [6]

1: Arcade
2: Break Room - contains a puzzle
3: Cafeteria - contains an item (Golden Spatula)
4: Bathroom - contains an item (Crowbar)
5: Unnecessarily Long-Named Room - contains a puzzle
6: Library - contains an item (The Necronomicon) and a puzzle.
