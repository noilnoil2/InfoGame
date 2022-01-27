package game.obj;

import java.awt.Color;
import java.awt.Graphics;

import game.gui.GamePanel;

public class Ground {

	private Color color = Color.WHITE, boardColor1, boardColor2;
	private int x, y, width, height, tileSize;
	private boolean checkerd;
	
	public Ground(int x, int y, int tileSize, int width, int height) {
		this.x = x;
		this.y = y;
		this.tileSize = tileSize;
		this.width = width;
		this.height = height;
	}
	
	public void setcheckerd(boolean checkerd) {
		this.checkerd = checkerd;
	}

	public void setcheckerd(boolean checkerd, Color c1, Color c2) {
		this.checkerd = checkerd;
		this.boardColor1 = c1;
		this.boardColor2 = c2;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	public double getCoordX(double x) {
		return this.x+(x*tileSize);
	}
	public double getCoordY(double y) {
		return this.y+(y*tileSize);
	}
	
	public void render(Graphics g) {
		
		g.setColor(color);
		
		if(this.checkerd) {
			//Schachbrettmuster
	        for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					int k = i+j;
					if ((k % 2) == 0) {
				        g.setColor(this.boardColor1);
				        g.fillRect((int) (getCoordX(i)), (int) (getCoordY(j)), (int) (tileSize), (int) (tileSize));
				    } else if ((k % 2) >= 0) {
				        g.setColor(this.boardColor2);
				        g.fillRect((int) (getCoordX(i)), (int) (getCoordY(j)), (int) (tileSize), (int) (tileSize));
				    }
				}
			}
		}else {
			//GRID
	        for (int i = (int) (x); i <= (int) x+(tileSize * width); i+=(int)tileSize) {
	    		g.drawLine(i, (int)(y), i, (int)(y+(tileSize * height)));
			}
			for (int i = (int) (y); i <= (int) y+(tileSize * height); i+=(int)tileSize) {
	    		g.drawLine((int)(x), i, (int)(x+(tileSize * width)), i);
			}
		}
        
		
	}			//x = 240; y = 80; titleSize = 40; Width = 20; height = 15
	
}
