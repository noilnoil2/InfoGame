package game.gui;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.core.Var;

public class CharCreationPanel extends JPanel{

	static JLabel lblTitle;
	static JComboBox<String> cb;
	
	public CharCreationPanel() {
		
		setLayout(null);
		setBackground(Color.BLACK);
		
		lblTitle = new JLabel();
		lblTitle.setSize(Var.FrameWidth, 200);
		lblTitle.setLocation(0, 0);
		lblTitle.setText("Create your Character");
		lblTitle.setFont(Var.getModifiedFont(Var.pixelFont, 80));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		lblTitle.setVisible(true);
		lblTitle.setFocusable(false);
		add(lblTitle);
		
		String[] choices = { "Bernd", "Franz", "Günter", "Adam", "Eva"};
		
		JComboBox<String> cb = new JComboBox<String>(choices);
		cb.setBounds(Var.FrameWidth/2, 220, 200, 100);
		cb.setFocusable(false);
		cb.setVisible(true);
		
		
		
		add(cb);
		
	}

	public static void rescale() {
		lblTitle.setSize(Var.frame.getWidth(), s(200));
		lblTitle.setFont(Var.getModifiedFont(Var.pixelFont, s(80)));
	}
	
	private static int s(int p){return (int) (p*GamePanel.s);}
	
}
