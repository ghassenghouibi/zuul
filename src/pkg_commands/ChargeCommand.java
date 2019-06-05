/**
 * Implementation of the 'charge' user command.
 * 
 * @author  Universit� Paris8 Groupe5 
 * @version 3.0 (May 2019)
 */
package src.pkg_commands;
import src.pkg_characters.*;

public class ChargeCommand extends Command {

	public ChargeCommand() {
		// TODO Auto-generated constructor stub
		command = "charge";
	}
	
	/**
     * This function charge your beamer that allow's you to remeber this room
     * 
     * @param player
     */
	@Override
	public boolean execute(Player player) {
		// TODO Auto-generated method stub
		if (player.checkItemInTheBag("beamer") != null) {
            if (player.checkItemInTheBag("ammo") != null) {
                player.removeItemFromBag("ammo");
                engine.setBeamerCharged(engine.getCurrentRoom());
            } else {
                gui.println("You are OUT OF AMMO");
            }
        } else {
            gui.println("You must have a beamer first");
        }
		return false;
	}

}
