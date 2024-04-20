# Virtual World - Java Version

Virtual World is a simulation game presenting a world full of animals and plants. The game is written fully according to OOP. The subject required us to write this project in 3 different object oriented programming languages.
There is also:

- [C++ version](https://github.com/kyrczak/virtual-world-cpp)
- [Python version](https://github.com/kyrczak/virtual-world-python)

## Game description
Virtual World is a turn-based game, where user controls human with special abilities and tries to survive in a enviroment
filled with various animals, both neutral and hostile, and with plants, each with their special characteristic.

Every entity has its own strength and initiative attribute value. Animals can move around the map in each, plants can spread 
in each round. When there's a collision between animals, an animal with bigger strength wins, unless animal has a 
special collision method. In collision where two animals have the same strength, the one with longer lifetime survives
 the encounter.

Below is the table of entities that you can interact with in the simulation:

| Entity              | Strength | Initiative | Action                                                  | Collision                                               |
|---------------------|----------|------------|---------------------------------------------------------|---------------------------------------------------------|
| Wolf                | 9        | 5          | -                                                       | -                                                       |
| Sheep               | 4        | 4          | -                                                       | -                                                       |
| Antelope            | 4        | 4          | Can move across 2 fields                                | 50% chance to avoid fight                               |
| Turtle              | 2        | 1          | 75% chance not to change position during the turn       | Blocks attack of a an animal with strength lower than 5 |
| Fox                 | 3        | 7          | Fox won't move to a field occupied by a stronger animal | -                                                       |
| Human               | 5        | 4          | Human is controlled by a user                           | Has special ability on 'F' key                          |
| Grass               | 0        | 0          | -                                                       | -                                                       |
| Dandelion           | 0        | 0          | Dandelion tries to spread up to 3 times per round       | -                                                       |
| Guarana             | 0        | 0          | -                                                       | Increases the strength of the animals that eats it by 3 |
| Wolf Berries        | 99       | 0          | -                                                       | Kill the animals that eats it                           |
| Sosnowski's Hogweed | 10       | 0          | Kill every animal around it                             | Animals that eat it die                                 |

## Installation
In order to run the game compile the game and run the main function located in VirtualWord.java file or download 
the release build and run the .jar file.

## Gameplay
After launching the simulator the user has an option to:
1. Input width and height parameters and create new game
2. Load existing game from .txt file

### Game
Each round the user can move human in the surrounding fields using `ARROW_KEYS` and interact with the virtual world. 
Human has a special ability which can be activated by using the `F` key. After activation human has a special shield 
blocking all incoming attacks from other animals. The ability is active for 5 turns and then has a cooldawn for 5 turns.

The user can also add new entities to the map by left-clicking on an empty field and choosing the appropriate entity.

Below the board, there is a log window which displays messages from few last rounds
### Built with
- [Swing](https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/package-summary.html)
