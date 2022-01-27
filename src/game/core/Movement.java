package game.core;

import game.obj.Box;
import java.util.ArrayList;


public class Movement {
	
	public static final int MODUS_INSTANT_RUN = 0;
	public static final int DIRECTION_LEFT = 0, DIRECTION_RIGHT = 1, DIRECTION_UP = 2, DIRECTION_DOWN = 3;
	Box box;
	
	private ArrayList<Integer> moves = new ArrayList<Integer>();

	public Movement() {
		// TODO Auto-generated constructor stub
		
	}
	/*
	public void move(int modus){
		if(modus == MODUS_INSTANT_RUN){
			
		}else if(){
			
		}
	}
	
	private void moveBox(Box box, int dir){
		if((dir == DIRECTION_DOWN && box.getY() < 13.001) || (dir == DIRECTION_UP && box.getY() > 0.999) || dir == DIRECTION_RIGHT && box.getX < 18.001 || dir == DIRECTION_LEFT && box.getX > 0.999){
			for (int i = 0; i < 40; i++) {
				directionAngel();
				if(dir == DIRECTION_DOWN){
					y= y + 0.025;
				}else if( dir == DIRECTION_UP){
					y= y - 0.025;
				}else if( dir == DIRECTION_RIGHT){
					x= x + 0.025;
				}else if(dir == DIRECTION_LEFT){
					x = x - 0.025;
				}
				stamina = stamina-0.025;
				regstamina();
				Func.sleeping(calcspeed(40, i));
			}	
		} 
	}
	
	public void addList (int direction){
		moves.add(direction);
	}
	
	private int calcspeed(int maxspeed, int i){
		int speed = 40;
		if(moves.size() > 1){
			if((i < 20 && speed > 40) || (i > 19 && moves.get(0) != moves.get(1))) {
				speed = (int) (0.15*((i-20)*(i-20))+40);
			}else {
				speed = 40;
			}
		}else {
			speed = (int) (0.15*((i-20)*(i-20))+40);
		}
		return speed;
	}
	
	private void calcStamina(boolean regon, int stamina, int regeneration, int maxStamina){
		if(!regon) {
			Runnable sta = new Runnable() {
				
				@Override
				public void run() {
					while(stamina < maxStamina){
						stamina = stamina + (regeneration/20);
						Func.sleeping(50);
						if(stamina >= 1) {
							constantmove();
						}
					}
					if(stamina > maxStamina){
						stamina = maxStamina;
					}
					regon = false;
				}
			};
			regon = true;
			Thread s = new Thread(sta);
			s.start();
		}
	}
	
	
	
	*/
}
