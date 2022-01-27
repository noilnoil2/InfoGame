package game.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.core.Var;

public class MainMenuPanel extends JPanel{

	static JLabel lblTitle;
	static JButton btnPlay, btnExit, btnSettings;
	
	public MainMenuPanel() {
		
		setLayout(null);
		setBackground(Color.BLACK);
		
		lblTitle = new JLabel();
		lblTitle.setSize(Var.FrameWidth, 400);
		lblTitle.setLocation(0, 0);
		lblTitle.setText(Var.MainGameName);
		lblTitle.setFont(Var.getModifiedFont(Var.pixelFont, 80));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		lblTitle.setVisible(true);
		lblTitle.setFocusable(false);
		add(lblTitle);
		
		btnPlay = new JButton();
		btnPlay.setText("PLAY");
		btnPlay.setBackground(Color.DARK_GRAY);
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setBorder(null);
		btnPlay.setBorderPainted(false);
		btnPlay.setFont(Var.getModifiedFont(Var.pixelFont, 40));
		btnPlay.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlay.setVerticalAlignment(SwingConstants.CENTER);
		btnPlay.setSize(200, 75);
		btnPlay.setLocation(Var.FrameWidth/2-btnPlay.getWidth()/2, 500);
		btnPlay.setVisible(true);
		btnPlay.setFocusable(false);
		btnPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.changeScene(MainWindow.charcreationpanel);
			}
		});
		add(btnPlay);
		
		btnExit = new JButton();
		btnExit.setText("EXIT");
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setForeground(Color.WHITE);
		btnExit.setBorder(null);
		btnExit.setBorderPainted(false);
		btnExit.setFont(Var.getModifiedFont(Var.pixelFont, 30));
		btnExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.setVerticalAlignment(SwingConstants.CENTER);
		btnExit.setSize(200, 75);
		btnExit.setLocation(Var.FrameWidth/2-btnExit.getWidth()/2, 600);
		btnExit.setVisible(true);
		btnExit.setFocusable(false);
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exiting via Exit Button");
	            System.exit(0);
			}
		});
		add(btnExit);
		
		
		System.out.println("Main Menu Panel launched " + (Var.millis()-Var.firstMillis) + " ms");
		
	}

	public static void rescale() {
		lblTitle.setSize(Var.frame.getWidth(), s(400));
		lblTitle.setFont(Var.getModifiedFont(Var.pixelFont, s(80)));
		
		btnPlay.setSize(s(200), s(75));
		btnPlay.setLocation(Var.frame.getWidth()/2-btnPlay.getWidth()/2, s(500));
		btnPlay.setFont(Var.getModifiedFont(Var.pixelFont, s(40)));
		
		btnExit.setSize(s(200), s(75));
		btnExit.setLocation(Var.frame.getWidth()/2-btnExit.getWidth()/2, s(600));
		btnExit.setFont(Var.getModifiedFont(Var.pixelFont, s(40)));
	}
	
	private static int s(int p){return (int) (p*GamePanel.s);}
	
}
