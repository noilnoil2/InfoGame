package game.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player {

	private String Name;
	private ArrayList<Box> bs = new ArrayList<Box>();
	private int activeBox = 0;
	
	public Player(String Name, int nBox, int start) {
		this.Name = Name;
		for (int i = 0; i < nBox; i++) {
			bs.add(new Box("Box " + i, start, i*2, 40, 40, Color.WHITE, 40, 5 , 0.1, this));
		}
		
		bs.get(activeBox).setColor(Color.RED);
		
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public ArrayList<Box> getBoxes(){
		return bs;
	}
	
	public Box getBox(int i) {
		return bs.get(i);
	}
	
	public int getActive() {
		return activeBox;
	}
	
	public Box getActiveBox() {
		return bs.get(activeBox);
	}
	
	public void changeBox() {
		bs.get(activeBox).setColor(Color.WHITE);
		if(activeBox < 4){
			activeBox ++;
		}else {
			activeBox = 0;
		}
		bs.get(activeBox).setColor(Color.RED);
		
			
	}

	public void render(Graphics g) {
		for (int i = 0; i < bs.size(); i++) {
			bs.get(i).render(g);
		}
		
		
	}
	
}
