package game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import game.core.Func;
import game.core.Var;

public class GamePanel extends JPanel {
	
	public static boolean repaint;
	private static boolean firstRun = true;
	public static double s = 1;
	private static long m = 0;
	private static double delta = 0;
	
	public GamePanel() {
		
		setLayout(null);
		setBackground(Color.BLACK);
		System.out.println("Game Panel launched " + (Var.millis()-Var.firstMillis) + " ms");
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        m = System.nanoTime();
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        	    RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.scale(s, s);
        g.setFont(Var.getModifiedFont(Var.pixelFont, 18));
        
        //g2.translate(-Var.g1.getCoordX(Var.p1.getActiveBox().getX()) , -Var.g1.getCoordX(Var.p1.getActiveBox().getY()));
        
        g.setColor(Color.WHITE);
        
        g.drawString("Quit - ESC", 20, 40);
        
        Var.g1.render(g);
        Var.FPSCount.render(g);
        Var.p1.render(g);
        Var.p2.render(g);
        Var.s1.render(g);
        Var.s2.render(g);
        
        
        //repaint(Var.FPSCount.getX()-10, Var.FPSCount.getY()-10, 100, 50);
        //repaint(Var.p1.getActiveBox().getBounds(Var.g1));
        //repaint(Var.p2.getActiveBox().getBounds(Var.g1));
        
        repaint();
        
        Var.FPSCount.tick();
        
        if(firstRun) {
        	System.out.println("First Repaint after " + (Var.millis()-Var.firstMillis) + " ms");
        	firstRun = false;
        }
        
        delta = System.nanoTime() - m;
        delta = delta/1_000_000.0;
        //System.out.println("Delta " + delta);
        
        if((int) (Func.FPStoMS(Var.fps) - delta) > 0) {
        	long start = System.nanoTime();
        	double sleepingTime = (Func.FPStoMS(Var.fps) - delta) + Var.SleptDiff;
        	if((int)sleepingTime > 0) {
        		Func.sleeping((int)sleepingTime);
            	double slept = ((System.nanoTime() - start)/ 1_000_000.0);
            	Var.SleptDiff = sleepingTime - slept;
        	}
        	//System.out.println("delta: " + delta);
        	//System.out.println("ideal sleep: " + (Func.FPStoMS(Var.fps) - delta));
        	//System.out.println("fps to ms: " + Func.FPStoMS(Var.fps));
        	//System.out.println("Sleeping: " + sleepingTime);
        	//System.out.println("Slept: " + slept);
        	//System.out.println("Diff: " + Var.SleptDiff);
        }
        
    }
	
}
