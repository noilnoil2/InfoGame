package game.obj;

import java.awt.Color;
import java.awt.Graphics;

public class FPSCounter {

	private int FPS = 0, FPSCache = 0, x, y;
	private String Prefix = "";
	private Thread t;
	private boolean running = true;
	private Color color = Color.WHITE;
	
	public FPSCounter(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(running) {
					FPS = FPSCache;
					FPSCache = 0;
					try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
				}
			}
		});
		t.start();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		FPSCache++;
	}
	
	public int getFPS() {
		return FPS;
	}
	
	public void setPrefix(String prefix) {
		this.Prefix = prefix;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawString(Prefix + FPS, x, y);
	}
	
}
