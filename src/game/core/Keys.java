package game.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.gui.MainWindow;
import game.obj.Box;

public class Keys implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getExtendedKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Var.p2.getActiveBox().addList(Box.DIRECTION_LEFT);
		}if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Var.p2.getActiveBox().addList(Box.DIRECTION_RIGHT);
		}if(e.getKeyCode() == KeyEvent.VK_UP) {
			Var.p2.getActiveBox().addList(Box.DIRECTION_UP);
		}if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Var.p2.getActiveBox().addList(Box.DIRECTION_DOWN);
		}if(e.getKeyCode() == KeyEvent.VK_A) {
			Var.p1.getActiveBox().addList(Box.DIRECTION_LEFT);
		}if(e.getKeyCode() == KeyEvent.VK_D) {
			Var.p1.getActiveBox().addList(Box.DIRECTION_RIGHT);
		}if(e.getKeyCode() == KeyEvent.VK_W) {
			Var.p1.getActiveBox().addList(Box.DIRECTION_UP);
		}if(e.getKeyCode() == KeyEvent.VK_S) {
			Var.p1.getActiveBox().addList(Box.DIRECTION_DOWN);
		}if(e.getKeyCode() == KeyEvent.VK_F) {
			Func.toggleFullScreen();
		}if(e.getKeyCode() == KeyEvent.VK_G) {
			Func.toggleGUI();
		}if(e.getKeyCode() == KeyEvent.VK_Q) {
			Var.p1.getActiveBox().switch_walkmode();
		}if(e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == 45) {
			Var.p2.getActiveBox().switch_walkmode();
		}if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(Var.skipMenu) {
				System.out.println("Exiting via Keystroke Escape");
				System.exit(0);
			}else if(!MainWindow.mainmenupanel.isVisible()){
				MainWindow.changeScene(MainWindow.mainmenupanel);
			}else if(MainWindow.mainmenupanel.isVisible()) {
				System.out.println("Exiting via Keystroke Escape");
				System.exit(0);
			}
		}if(e.getKeyCode() == KeyEvent.VK_E) {
			Var.p1.changeBox();
		}if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			Var.p2.changeBox();
		}if(e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == 93) {
			if(!Var.fullScreen) {
				Var.frame.setSize((int)(Var.frame.getWidth()+64), (int)(Var.frame.getHeight()+36));
				Func.changeScale(Var.frame.getWidth(), Var.frame.getHeight());
	            Var.frame.setLocationRelativeTo(null);
	            MainWindow.notifyRescale();
	            //System.out.println("Resolution: " + Var.frame.getWidth() + " " + Var.frame.getHeight());
			}
			
		}if(e.getKeyCode() == KeyEvent.VK_L || e.getKeyCode() == 16777468) {
			if(!Var.fullScreen && Var.frame.getWidth()>=256 && Var.frame.getHeight() >=144) {
				Var.frame.setSize((int)(Var.frame.getWidth()-64), (int)(Var.frame.getHeight()-36));
				Func.changeScale(Var.frame.getWidth(), Var.frame.getHeight());
	            Var.frame.setLocationRelativeTo(null);
	            MainWindow.notifyRescale();
	            //System.out.println("Resolution: " + Var.frame.getWidth() + " " + Var.frame.getHeight());
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
