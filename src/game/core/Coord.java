package game.core;

public class Coord {
	public double x, y; 

	public Coord(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord() {
		
	}
	
	public double getDistance(Coord c1, Coord c2){ 
		double distance = Math.sqrt((c1.x-c2.x)*(c1.x-c2.x)+(c1.y-c2.y)*(c1.y-c2.y));
		if(distance < 0){
			distance = distance*(-1);
		}
		return distance;
	}
	
}