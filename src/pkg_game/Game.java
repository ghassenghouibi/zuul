/**
*  This class is the main class of the "World of Zuul" application. 
*  "World of Zuul" is a very simple, text based adventure game.  Users 
*  can walk around some scenery. That's all. It should really be extended 
*  to make it more interesting!
* 
*  To play this game, create an instance of this class.
* 
*  This main class creates the necessary implementation objects and starts the game off.
* 
* @author  Universit� Paris8 Groupe5
* @version 3.0 (May 2019)
*/
package src.pkg_game;

import src.pkg_utils.UserInterface;


public class Game
{
    private UserInterface gui;
    private GameEngine engine;

    /**
     * Create the game and initialise its internal map.
     */
    public Game(){      
        engine = new GameEngine(); 
        gui = new UserInterface(engine);
        engine.play(gui);
    }
    
}