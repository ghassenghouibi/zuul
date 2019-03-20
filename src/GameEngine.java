/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
package src;

import java.io.PrintStream;

public class GameEngine{


    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;

        
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        parser = new Parser();
        createRooms();
    }

    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Welcome to the One Piece treasure cruise!");
        gui.println("One Piece is a new, incredibly boring adventure game.");
        gui.println("Type 'help' if you need help.");
        gui.print("\n");
        gui.println(currentRoom.getLongDescription());
        gui.showImage(currentRoom.getImageName());

    }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
      
        Room cocoyashi, nooberland, wanoKuni, water7, kalen,ortopia,alabasta,krakenland,amazoneLily,skypia,paris8,rafel;
              
        cocoyashi = new Room("Cocoyashi","src/images/kokoyashi.png");
        nooberland = new Room("Nooberland","src/images/Nooberland.png");
        wanoKuni = new Room("Wano_kuni","src/images/wanokuni.png");
        water7 = new Room("Water7","src/images/Water_Seven.png");
        kalen = new Room("Kalen","src/images/kalen.png");
        ortopia = new Room("Ortopia","src/images/Ortopia.png");
        alabasta = new Room("Alabasta","src/images/Alabasta.png");
        krakenland = new Room("Krakenland","src/images/Krakenland.png");
        amazoneLily = new Room("Amazone_lily","src/images/AmazoneLily.png");
        skypia = new Room("Skypia","src/images/skypia.png");
        paris8 = new Room("Paris8, il semble que vous avez découvert une île absente sur votre carte, et si vous l'exploriez ?","src/images/paris8.png");
        rafel = new Room("Rafel, ~votre log pose n'arrête pas de s'agiter ...~","src/images/raftel.png");

        // initialise room exits
        cocoyashi.setExits("north",nooberland);

        nooberland.setExits("east",water7);
        nooberland.setExits("south",cocoyashi);
        nooberland.setExits("west",wanoKuni);
        nooberland.setExits("northWest",kalen);
        nooberland.setExits("northEast",alabasta);

        wanoKuni.setExits("east",nooberland);

        water7.setExits("west",nooberland);

        kalen.setExits("north",skypia);
        kalen.setExits("southEast",nooberland);

        ortopia.setExits("north",krakenland);
        ortopia.setExits("west",kalen);
        ortopia.setExits("northEast",amazoneLily);

        alabasta.setExits("southWest",nooberland);
        
        krakenland.setExits("south",ortopia);
        krakenland.setExits("west",skypia);

        amazoneLily.setExits("southWest",ortopia);

        skypia.setExits("north",paris8);
        skypia.setExits("east",krakenland);
        skypia.setExits("south",kalen);
        skypia.setExits("northEast",rafel);

        paris8.setExits("south",skypia);

        rafel.setExits("southWest",skypia);
      

        currentRoom = cocoyashi;  // start game outside
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("eat"))
            eat();
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at Saint Denis, University Campus." + "\n");
        gui.print("Your command words are: " + parser.showCommands());
    }


    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }

    private void endGame() {
		gui.println("Thank you for playing.  Good bye !");
        gui.enable(false);
    }
    
 

    private void look(){
        gui.print(currentRoom.getLongDescription());
    }
    
    private void eat(){
        gui.println("You have eaten now and you are not hungry any more");
    }
    
}