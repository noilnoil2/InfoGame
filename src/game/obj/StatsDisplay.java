package game.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.core.Func;

public class StatsDisplay {
	String Name;
	int x, y, k2, mo, m1, m2, m3, mc;
	ArrayList<Box> bs = new ArrayList<Box>();
	Player player;

	public StatsDisplay(int x, int y, Player player) {
		this.x = x;
		this.y = y;
		this.k2 = 0;
		this.Name = player.getName();
		this.bs = player.getBoxes();
		this.player = player;
	}
	
	public void addBox(Box Box) {
		bs.add(Box);
	}
	
	public void render(Graphics g) {
		
		for (int i = 0; i < bs.size(); i++) {
			renderStats(g, bs.get(i), i);
		}
		
	}
	
	private void renderStats(Graphics g, Box box, int i){
		int k = 0;
		Boolean abox = false;
		if(i == player.getActive()) {
			abox = true;
			k = 65;
		}else if(i < player.getActive()){
			k = y+i*100;
		}else {
			k = y+(i-1)*100;
		}
		
		//Layout
		g.setColor(Color.WHITE);
		g.fillRect(x, k, 60, 15);
		if(abox) {
			g.drawRect(x, 15+k, 180, 200);
		} else {
			g.drawRect(x, 15+k, 180, 80);
		}
		g.setColor(Color.BLACK);
		g.drawString(box.getName(), x+2, 12+k);
		k2 = k + 21;
		
		//Stats
		showStatsCircle(g, 6, k, "grün", box.getStamina(), box.getMaxStamina());
		showStatsCircle(g, 51, k, "gelb", box.getStamina(), box.getMaxStamina());
		showStatsCircle(g, 96, k, "rot", box.getStamina(), box.getMaxStamina());
		showStatsCircle(g, 141, k, "blau", box.getStamina(), box.getMaxStamina());
		k2 = k2 + 40;
		//showStatsPercent(g, y, k2,"ST", box.getStamina(), box.getMaxStamina());
		//k2 = k2 + 15;
		
		g.drawString("WM:" + box.getWalkmode() + " "+ player.getBox(box.getFollowBoxWM3()).getName() + " -> [" + (int) box.getPtarget().x + "/" + (int) box.getPtarget().y + "]", x+6, k2+11);
		k2 = k2 + 15;
		if(box.getWalkmode() == 2) {
			g.drawString(showWM4(box), x+6, k2+11); //"Now:[" + (int) box.getX() + "/" + (int) box.getY() + "] Soon:["+ (int) box.getxWM4() + "/" + (int) box.getyWM4() + "]"
			k2 = k2 + 15;
		}else {
			g.drawString("Walksize: "+box.getSizeList()+ " " + box.getMoveList(8), x+6, k2+11); //-8
			k2 = k2 + 15;
		}
		
	}
	
	
	//k2 ist abhängig von der Y Koordinate eines Graphen --> schrift muss mit Schriftgröße addiert werden
	//Funktionen für Stats
	private void showStatsPercent(Graphics g, int y, int k2 ,String name, double currentstat, double maxstat) {
		double p = currentstat/maxstat;
		
		//Objekte
		if(p > 0.75) {
			g.setColor(Color.GREEN);
		}else if(p>0.25) {
			g.setColor(Color.YELLOW);
		}else{
			g.setColor(Color.RED);
		}
		
		g.fillRect(x+55, k2, (int) (92*p), 11);
		g.setColor(Color.WHITE);
		g.drawString(""+name+": "+Func.round(currentstat, 1), x+6, k2+11);
		g.drawRect(x+55, k2, 92, 11);
		g.drawString(""+Func.round(maxstat, 1), x+152, k2+11);
		
		//k2 = k2 + 15;
	}
	
	private void showStatsCircle(Graphics g, int addX, int k, String farbe, double currentstat, double maxstat) {
		int y2 = k2;
		int x2 = x+addX;
		double p = currentstat/maxstat;
		double z = (360*p);
		
		if(farbe == "grün") {
			g.setColor(Color.GREEN);
		}else if(farbe == "gelb") {
			g.setColor(Color.YELLOW);
		}else if(farbe == "rot"){
			g.setColor(Color.RED);
		}else if(farbe == "blau"){
			g.setColor(Color.BLUE);
		}
		
		g.fillArc(x2, y2, 33, 33, 90, (int) z);
		g.setColor(Color.BLACK);
		g.fillArc(x2+3, y2+3, 27, 27, 0, 360);
		g.setColor(Color.WHITE);
		g.drawString(""+Func.round(currentstat, 1), x+12, y2+21);
		
		//k2 = k2 + 35;
	}
	
	private String showWM4(Box box) {
		String s1 = "[";
		String s2 = "/";
		String s3 = "] -> [";
		String s4 = "/";
		String s5 = "] ";
		if(box.getX() < 10) {
			s1 = "[0";
		}if(box.getY() < 10) {
			s2 = "/0";
		}if(box.getStatusWM4() == 0) {
			s3 = s3 + "#";
		}if(box.getpWM4().x < 10) {
			s3 = s3 + "0";
		}if(box.getStatusWM4() == 1) {
			s4 = s4 + "#";
		}if(box.getpWM4().y < 10) {
			s4 = s4 + "0";
		}if(box.getStatusWM4() == 2) {
			s5 = s5 + "*";
		}if(box.getBooleanWM4()) {
			s5 = s5 + "ON";
		}else {
			s5 = s5 + "OFF";
		}
		String sg = s1 + (int) box.getX() + s2 + (int) box.getY() + s3 + (int)box.getpWM4().x + s4 + (int) box.getpWM4().y + s5;
		return sg;
	}
	
	private void showWMStats(Graphics g, int k, int wm) {
		if(wm == 1) {
			
		}
	}
	
	/*private void menu() {
		if(){
			
		}
	}*/
}

