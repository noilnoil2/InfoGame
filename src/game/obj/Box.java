package game.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.core.Coord;
import game.core.Func;
import game.core.Var;

public class Box {

	private double x, y;
	private int width, height;
	private String Name;
	private Color color;
	private boolean visible = true, showName = true, moving, running, regon, WM3, WM4, WM5;
	private ArrayList<Integer> moves = new ArrayList<Integer>();
	private ArrayList<Integer> moves_follow = new ArrayList<Integer>();
	public static final int DIRECTION_LEFT = 0, DIRECTION_RIGHT = 1, DIRECTION_UP = 2, DIRECTION_DOWN = 3;
	public static final int WALKMODE_INSTANT_RUN = 0, WALKMODE_LIST_RUN = 1, WALKMODE_POINT_RUN = 2, WALKMODE_FOLLOW_RUN = 3, WALKMODE_FOLLOW_TARGET_RUN = 4;
	private int maxSpeed, startwidth, startheight, angel, walkmode, followBoxWM3, xWM4, yWM4, statusWM4, xtarget, ytarget, followBoxWM5;
	private double regeneration, stamina, maxStamina;
	private Coord pnow, ptarget, pWM4;
	Player player;
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getX() {
		return pnow.x;
	}

	public void setX(double x) {
		pnow.x = x;
	}

	public double getY() {
		return pnow.y;
	}

	public void setY(double y) {
		pnow.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setstartHeight(int startheight) {
		this.startheight = startheight;
	}
	
	public double getStamina() {
		return stamina;
	}
	
	public double getMaxStamina() {
		return maxStamina;
	}
	
	public int getSizeList() {
		return moves.size();
	}
	
	public Coord getpWM4() {
		return pWM4;
	}
	
	public int getStatusWM4() {
		return statusWM4;
	}
	
	public boolean getBooleanWM4() {
		return WM4;
	}
	
	public String getListString(int i) {
		String rs = "";
		if(moves.get(i) == 0) {
			rs = "L";
		}else if(moves.get(i) == 1) {
			rs = "R";
		}else if(moves.get(i) == 2) {
			rs = "U";
		}else if(moves.get(i) == 3) {
			rs = "D";
		}
		return rs;
	}
	
	public String getMoveList(int i) {
		String ml = "";
		int k = 0;
		if(i<moves.size()) {
			k = i;
		}else {
			k = moves.size();
		}
		for (int j = 0; j < k; j++) {
			ml = ml + getListString(j);
		}
		return ml;
	}
	
	public int getFollowBoxWM3() {
		return followBoxWM3;
	}

	public int getWalkmode() {
		return walkmode;
	}
	
	public Coord getP() {
		return pnow;
	}
	
	public Coord getPtarget() {
		return ptarget;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Box(String Name, int x, int y, int width, int height, Color color, int maxspeed, double maxstamina, double regeneration, Player player) {
		this.Name = Name;
		this.pWM4 = new Coord();
		this.pnow = new Coord();
		this.pnow.x = x;
		this.pnow.y = y;
		this.ptarget = new Coord();
		this.ptarget.x = x;
		this.ptarget.y = y;
		this.width = width;
		this.height = height;
		this.startwidth = width;
		this.startheight = height;
		this.color = color;
		this.maxSpeed = maxspeed;
		this.maxStamina = maxstamina;
		this.stamina = this.maxStamina;
		this.regeneration = regeneration;
		this.angel = 165;
		this.walkmode = 0;
		this.player = player;
		this.followBoxWM3 = 0;
	}
	
	public Box(String Name) {
		this.Name = Name;
	}
	
	public Rectangle getBounds(Ground g) {
		return new Rectangle((int)(g.getCoordX(x)), (int)(g.getCoordY(y)), width, height);
	}
	
	public void addList (int direction){
		move(direction);
	}
	
	public void switch_walkmode() {
		if(walkmode < 4) {
			walkmode++;
		}else {
			walkmode = 0;
		}
	}
	
	
	
	
	//MOVEMENT
	
	public void move(int dir){
		if(walkmode == WALKMODE_INSTANT_RUN){
			Runnable wm0 = new Runnable() {
				
				@Override
				public void run() {
					moveBox(dir);
					running = false;
				}
			};
			if(!running) {
				running = true;
				Thread r0 = new Thread(wm0);
				r0.start();
				if(dir == DIRECTION_DOWN && y < 13.01) {
					ptarget.y++;
				}else if(dir == DIRECTION_UP && y > 0.999) {
					ptarget.y = ptarget.y - 1;
				}else if(dir == DIRECTION_RIGHT && x < 18.001) {
					ptarget.x++;
				}else if(dir == DIRECTION_LEFT && x > 0.999){
					ptarget.x = ptarget.x - 1;
				}
				calcStamina();
			}
		}else if(walkmode == WALKMODE_LIST_RUN) {
			moves.add(dir);
			ptarget.x = pnow.x;
			ptarget.y = pnow.y;
			for (int i = 0; i < moves.size(); i++) {
				if(moves.get(i) == DIRECTION_DOWN) {
					ptarget.y++;
				}else if(moves.get(i) == DIRECTION_UP) {
					ptarget.y = ptarget.y -1;
				}else if(moves.get(i) == DIRECTION_RIGHT) {
					ptarget.x++;
				}else if(moves.get(i) == DIRECTION_LEFT) {
					ptarget.x = ptarget.x -1;
				}
			}
			Runnable wm1 = new Runnable() {
				
				@Override
				public void run() {
					while(moves.size() > 0) {
						moveBox(moves.get(0));
						Func.sleeping(5);
					}
					running = false;
				}
			};
			if(!running) {
				running = true;
				Thread r1 = new Thread(wm1);
				r1.start();
				calcStamina();
			}
		}else if(walkmode == WALKMODE_POINT_RUN) {
			if(dir == DIRECTION_UP) {
				if(statusWM4 == 0) {
					pWM4.x = add1((int) pWM4.x, 0, 19);
				}else if(statusWM4 == 1) {
					pWM4.y = add1((int) pWM4.y, 0, 14);
				}
			}else if(dir == DIRECTION_DOWN) {
				if(statusWM4 == 0) {
					pWM4.x = sub1((int) pWM4.x, 0, 19);
				}else if(statusWM4 == 1) {
					pWM4.y = sub1((int) pWM4.y, 0, 14);
				}
			}else if(dir == DIRECTION_LEFT) {
				if(statusWM4 > 0) {
					statusWM4 = statusWM4 -1;
				}
			}else if(dir == DIRECTION_RIGHT) {
				if(statusWM4 < 2) {
					statusWM4++;
				}else if(statusWM4 == 2 && !WM4) {
					Runnable wm4 = new Runnable() {
						
						@Override
						public void run() {
							while(WM4 == true) {
								ptarget.x = pWM4.x;
								ptarget.y = pWM4.y;
								if(stamina >= 1) {
									fillListMove(pWM4, moves_follow);
								}
								Func.sleeping(5);
							}
							running = false;
						}
					};
					if(!running) {
						running = true;
						WM4 = true;
						System.out.println("hello_Point");
						Thread r4 = new Thread(wm4);
						r4.start();
						calcStamina();
					}
				}
			}
		}else if(walkmode == WALKMODE_FOLLOW_RUN) {
			if(dir == DIRECTION_UP) {
				followBoxWM3 = add1(followBoxWM3, 0, 4);
				if(followBoxWM3 == player.getActive()) {
					followBoxWM3 = add1(followBoxWM3, 0, 4);
				}
			}else if(dir == DIRECTION_DOWN) {
				followBoxWM3 = sub1(followBoxWM3, 0, 4);
				if(followBoxWM3 == player.getActive()) {
					followBoxWM3 = sub1(followBoxWM3, 0, 4);
				}
			}else if(dir == DIRECTION_LEFT) {
				//lol
			}else if(dir == DIRECTION_RIGHT) {
				if(WM3) {
					WM3 = false;
				}else {
					Runnable wm3 = new Runnable() {
						
						@Override
						public void run() {
							while(WM3 == true) {
								ptarget.x = player.getBox(followBoxWM3).getP().x;
								ptarget.y = player.getBox(followBoxWM3).getP().y;
								if(stamina >= 1) {
									fillListMove(player.getBox(followBoxWM3).getP(), moves_follow);
								}
								Func.sleeping(5);
							}
							running = false;
						}
					};
					if(!running) {
						running = true;
						WM3 = true;
						System.out.println("hello_follow");
						Thread r3 = new Thread(wm3);
						r3.start();
						calcStamina();
					}
				}
			}
		}else if(walkmode == WALKMODE_FOLLOW_TARGET_RUN) {
			if(dir == DIRECTION_UP) {
				followBoxWM3 = add1(followBoxWM3, 0, 4);
				if(followBoxWM3 == player.getActive()) {
					followBoxWM3 = add1(followBoxWM3, 0, 4);
				}
			}else if(dir == DIRECTION_DOWN) {
				followBoxWM3 = sub1(followBoxWM3, 0, 4);
				if(followBoxWM3 == player.getActive()) {
					followBoxWM3 = sub1(followBoxWM3, 0, 4);
				}
			}else if(dir == DIRECTION_LEFT) {
				//left
			}else if(dir == DIRECTION_RIGHT) {
				if(WM5) {
					WM5 = false;
				}else {
					Runnable wm5 = new Runnable() {
						@Override
						public void run() {
							System.out.println("hi");
							while(WM5 == true) {
								while (stamina >= 1) {
									ptarget.x = player.getBox(followBoxWM3).getPtarget().x;
									ptarget.y = player.getBox(followBoxWM3).getPtarget().y;
									System.out.println(ptarget.x + "/" + ptarget.y);
									fillListMove(player.getBox(followBoxWM3).getPtarget(), moves_follow);
								}
								Func.sleeping(1000);
							}
							running = false;
						}
					};
					if(!running) {
						running = true;
						WM5 = true;
						System.out.println("hello1");
						Thread r5 = new Thread(wm5);
						r5.start();
						calcStamina();
					}
				}
			}
		}
		System.out.println("target: [" + (int) ptarget.x + "/" + (int) ptarget.y + "]");
	}
	
	
	private void moveBox(int dir){
		if(stamina >= 1) {
			if((dir == DIRECTION_DOWN && pnow.y < 13.01) || (dir == DIRECTION_UP && pnow.y > 0.999) || (dir == DIRECTION_RIGHT && pnow.x < 18.001) || (dir == DIRECTION_LEFT && pnow.x > 0.999)){
				for (int i = 0; i < 40; i++) {
					directionAngel(dir);
					if(dir == DIRECTION_DOWN){
						pnow.y = pnow.y + 0.025;
					}else if( dir == DIRECTION_UP){
						pnow.y = pnow.y - 0.025;
					}else if( dir == DIRECTION_RIGHT){
						pnow.x = pnow.x + 0.025;
					}else if(dir == DIRECTION_LEFT){
						pnow.x = pnow.x - 0.025;
					}
					stamina = stamina-0.025;
					calcStamina();
					if(walkmode == 1) {
						Func.sleeping(calcspeed(40, i));
					}else {
						Func.sleeping((int) (0.15*((i-20)*(i-20))+40));
					}
				}	
			} 
			if(walkmode == WALKMODE_LIST_RUN) {
				moves.remove(0);
			}
		}
	}
	
	private void fillListMove(Coord p, ArrayList<Integer> list) {
		list.clear();
		double deltaX = Func.round((pnow.x - p.x),1);
		double deltaY = Func.round((pnow.y - p.y), 1) ;
		if(deltaX > 0) {
			for (int i = 0; i < deltaX; i++) {
				list.add(DIRECTION_LEFT);
			}
		}else if(deltaX < 0) {
			for (int i = 0; i < -deltaX; i++) {
				list.add(DIRECTION_RIGHT);
			}
		}
		if(deltaY > 0) {
			for (int i = 0; i < deltaY; i++) {
				list.add(DIRECTION_UP);
			}
		}else if(deltaY < 0) {
			for (int i = 0; i < -deltaY; i++) {
				list.add(DIRECTION_DOWN);
			}
		}
		if(deltaY == 0 && deltaX == 0) {
			Func.sleeping(1000);
		}else {
			System.out.println("xWM3: "+ deltaX + " yWM3: " + deltaY);
			moveBox(moves_follow.get(0));
		}
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
	
	private void calcStamina(){
		if(!regon) {
			Runnable sta = new Runnable() {
				
				@Override
				public void run() {
					while(stamina < maxStamina){
						stamina = stamina + (regeneration/20);
						Func.sleeping(50);
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
	
	private int add1(int kon, int a, int b) {
		if(kon < b) {
			kon++;
		} else {
			kon = a;
		}
		return kon;
	}
	
	private int sub1(int kon, int a, int b) {
		if(kon > a) {
			kon = kon - 1;
		} else {
			kon = b;
		}
		return kon;
	}
	
	
	//Rotation

	public void directionAngel(int dir2) {
		int dir = dir2;
		if(dir == DIRECTION_DOWN){
			angel = 75;
		}else if( dir == DIRECTION_UP){
			angel = 255;
		}else if( dir == DIRECTION_RIGHT){
			angel = 165;
		}else if(dir == DIRECTION_LEFT){
			angel = 345;
		}
	}
	
	
	
	
	//RENDER
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(visible) {
			if(color == null) {
				g.setColor(Color.WHITE);
			}else {
				g.setColor(color);
			}
			
			if(showName) {
				g.drawString(Name, (int) (Var.g1.getCoordX(pnow.x)), (int) (Var.g1.getCoordY(pnow.y))-1);
			}
			
			g.fillArc((int) (Var.g1.getCoordX(pnow.x)+10*(Math.cos((angel-165)*Math.PI/180))), (int) (Var.g1.getCoordY(pnow.y)+10*(Math.cos((angel-75)*Math.PI/180))), 40, 40, angel, 30);
		}
	}
	
}
