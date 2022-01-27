package game.core;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import game.gui.MainWindow;
import game.obj.Box;
import game.obj.FPSCounter;
import game.obj.Ground;
import game.obj.Player;
import game.obj.StatsDisplay;

public class Initialization {

	public static void runEarlySetup() {
		Var.FrameWidth = 1280;
		Var.FrameHeight = 720;
		Var.FrameIcon = "res/img/icon.png";
		Var.MainGameName = "Info Game";
		
		if(Var.Args.length>1) {
			Var.fps = Integer.parseInt(Var.Args[1]);
		}else {
			Var.fps = 120;
		}
		
		Var.BoxGameRunning = true;
		Var.speed = 40;
		
		System.out.println("Size: " + Var.FrameWidth + "x" + Var.FrameHeight + "; FPS: " + Var.fps);
		
		loadFont2();
		
		createGameObjects();
		
		System.out.println("PreGui finished after " + (Var.millis()-Var.firstMillis) + " ms");
		
		runGUI();
		
	}
	
	private static void createGameObjects() {
		Var.b1 = new Box("Box 1", 0, 0, 40, 40, Color.WHITE, 40, 5 , 0.1, Var.p1);
		Var.b2 = new Box("Box 2", 1, 0, 40, 40, Color.CYAN, 40, 5 , 0.1, Var.p2);
		
		Var.FPSCount = new FPSCounter(20, 25);
		Var.g1 = new Ground(240, 80, 40, 20, 15);
		Var.g1.setcheckerd(false, Func.hex2Rgb("#253812"), Func.hex2Rgb("#6b4633"));
		Var.p1 = new Player("Player 1", 5, 0);
		Var.p2 = new Player("Player 2", 5, 19);
		Var.s1 = new StatsDisplay(35, 300, Var.p1);
		Var.s2 = new StatsDisplay(1065, 300, Var.p2);
	}
	
	private static void runGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Var.frame = new MainWindow();
					Var.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void loadFont() {
		try {
			Var.pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/coders_crux.ttf")).deriveFont(125f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/coders_crux.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//Not working
	private static void loadFont2() {
		try {
			Var.pixelFont = Font.createFont(Font.TRUETYPE_FONT, Func.getResourceAsFile("res/font/coders_crux.ttf")).deriveFont(125f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Func.getResourceAsFile("res/font/coders_crux.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
