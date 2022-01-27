//TODO Debug Mode | Menus | Config File for Map | HP Heart Design | Font load Fix | CharCreationPanel

package game.main;

import game.core.Initialization;
import game.core.Var;

public class Game {

	public static void main(String[] args) {
		
		Var.skipMenu = true;
		
		Var.Args = args;
		
		Var.firstMillis = System.currentTimeMillis();
		
		System.out.println("Launching " + (Var.millis()-Var.firstMillis) + " ms");
		
		Initialization.runEarlySetup();
		
	}
	
}
//Lion ist lost