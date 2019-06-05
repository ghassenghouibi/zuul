/**
 * Implementation of the 'attack' user command.
 * 
 * @author  Universit� Paris8 Groupe5 
 * @version 3.0 (May 2019)
 */
package src.pkg_commands;
import src.pkg_characters.*;

public class AttackCommand extends Command {

	public AttackCommand() {
		// TODO Auto-generated constructor stub
		command = "attack";
	}
	
	/**
     * this function attack enemies if you kill enemies you can pick up stuff to eat
     * or other
     * 
     * @param player
     */
	@Override
	public boolean execute(Player player) {
		// TODO Auto-generated method stub
		if (!hasSecondWord()) {
            gui.print("attack Who ?");
            return false;
        }
        String name = getSecondWord();
        if(engine.getCurrentRoom().checkEnemiesInTheRoom(name) != null) {
	        if (engine.getCurrentRoom().checkEnemiesInTheRoom(name).getStrength() < player.getStrength()) {
	        	engine.getCurrentRoom().addItems(engine.getCurrentRoom().checkEnemiesInTheRoom(name).getItem().getName(),
	        			engine.getCurrentRoom().checkEnemiesInTheRoom(name).getItem());
	        	engine.getCurrentRoom().removeEnemy(engine.getCurrentRoom().checkEnemiesInTheRoom(name).getName());
	            gui.println("Enemy killed");
	        } else {
	            if (player.getCrewNumber() - 10 <= 0) {
	                player.setLife(player.getLife() - 1);
	                gui.setLife(player.getLife());
	            } else {
	                player.setCrewNumber(player.getCrewNumber() - 10);
	                gui.setCrew(player.getCrewNumber());
	                gui.println("Enemy kill some of your crew\n");
	            }
	        }
        }else {
        	gui.println("There is no enemy with this name");
        }
		return false;
	}

	

}
