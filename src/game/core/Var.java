package game.core;

import java.awt.Font;

import game.gui.MainWindow;
import game.obj.Box;
import game.obj.FPSCounter;
import game.obj.Ground;
import game.obj.Player;
import game.obj.StatsDisplay;

public class Var {

	public static long firstMillis;
	public static String[] Args;
	public static boolean skipMenu;
	public static boolean debug;
	public static String FrameIcon;
	public static Font pixelFont;
	
	public static String MainGameName;
	
	public static MainWindow frame;
	public static int FrameWidth, FrameHeight;
	public static int FrameWidth_Cache, FrameHeight_Cache;
	
	public static boolean BoxGameRunning = true;
	public static boolean left, right, up, down;
	public static int speed;
	public static int fps;
	public static double scale = 1.0;
	public static boolean fullScreen, showGUI = true;
	public static int CurrentFPS = 0, FPS_Cache = 0;
	
	public static Box b1, b2;
	public static FPSCounter FPSCount;
	public static Ground g1;
	public static Player p1, p2;
	public static StatsDisplay s1, s2;
	
	public static double SleptDiff = 0;
	
	public static long millis() {
		return System.currentTimeMillis();
	}
	
	public static Font getModifiedFont(Font font, int size) {
		return new Font(font.getName(), Font.PLAIN, size);
	}
	
	public static Font getModifiedFont(Font font, int style, int size) {
		return new Font(font.getName(), style, size);
	}
	
}
