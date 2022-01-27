package game.core;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import game.gui.GamePanel;
import game.gui.MainWindow;

public class Func {
	
	private static boolean showGUIWait, toggleFullScreenWait;
	
	public static void onExit() {
		System.out.println("Thank you for playing");
	}
	
	public static void sleeping(int ms) {
		try{Thread.sleep(ms);}catch(InterruptedException e){e.printStackTrace();}
	}
	
	public static double FPStoMS(int FPS){return 1000.0/(double)FPS;}
	
	public static double getCoordX(double x) {
		return 240+(x*40);
	}
	public static double getCoordY(double y) {
		return 80+(y*40);
	}
	
	public static void changeScale(int Width, int Height) {
		if((double)Width/Var.FrameWidth>(double)Var.FrameHeight/720) {
			GamePanel.s = (double)Height/Var.FrameHeight;
		}else {
			GamePanel.s = (double)Width/Var.FrameWidth;
		}
	}
	
	public static void toggleFullScreen() {
		if(!toggleFullScreenWait) {
			toggleFullScreenWait = true;
			if(Var.fullScreen) {
				Var.fullScreen = false;
				Var.frame.dispose();
				Var.frame.setUndecorated(true);
				Var.frame.setVisible(true);
				Var.frame.setSize(Var.FrameWidth_Cache, Var.FrameHeight_Cache);
				Var.frame.setLocationRelativeTo(null);
				Func.changeScale(Var.frame.getWidth(), Var.frame.getHeight());
				MainWindow.notifyRescale();
			}else {
				Var.fullScreen = true;
				Var.FrameWidth_Cache = Var.frame.getWidth();
				Var.FrameHeight_Cache = Var.frame.getHeight();
				Var.frame.dispose();
				Var.frame.setUndecorated(true);
				Var.frame.setVisible(true);
				Var.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				Var.frame.setLocation(0, 0);
				Func.changeScale(Var.frame.getWidth(), Var.frame.getHeight());
				MainWindow.notifyRescale();
			}
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					sleeping(500);
					toggleFullScreenWait = false;
				}
			});
			t.start();
		}
		
	}
	
	public static void toggleGUI() {
		if(!showGUIWait) {
			showGUIWait = true;
			if(Var.showGUI) {
				Var.showGUI = false;
				Var.b1.setShowName(Var.showGUI);
	            Var.b2.setShowName(Var.showGUI);
			}else {
				Var.showGUI = true;
				Var.b1.setShowName(Var.showGUI);
	            Var.b2.setShowName(Var.showGUI);
			}
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					sleeping(500);
					showGUIWait = false;
				}
			});
			t.start();
		}
		
	}
	
	public static void countFPS() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(Var.BoxGameRunning) {
					
				}
			}
		});
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
	public static File getResourceAsFile(String resourcePath) {
	    try {
	        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
	        if (in == null) {
	            return null;
	        }

	        File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
	        tempFile.deleteOnExit();

	        try (FileOutputStream out = new FileOutputStream(tempFile)) {
	            //copy stream
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	            }
	        }
	        return tempFile;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
}
