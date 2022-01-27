package game.gui;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.core.Func;
import game.core.Keys;
import game.core.MoveMouseListener;
import game.core.Var;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	JLabel lblInfo;
	public static JPanel mainmenupanel, gamepanel, charcreationpanel;
	private static ArrayList<JPanel> Scenes = new ArrayList<JPanel>();
	
	public static void changeScene(JPanel panel) {
		for (int i = 0; i < Scenes.size(); i++) {
			Scenes.get(i).setVisible(false);
		}
		panel.setVisible(true);
		System.out.println("Scene changed");
	}
	
	public static void notifyRescale() {
		for (int i = 0; i < Scenes.size(); i++) {
			Scenes.get(i).setSize(Var.frame.getWidth(), Var.frame.getHeight());
		}
		MainMenuPanel.rescale();
	}
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Var.FrameWidth, Var.FrameHeight);
		setTitle("Game Info");
		try {
			setIconImage(ImageIO.read(new File(Var.FrameIcon)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setResizable(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		addKeyListener(new Keys());
		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
		setUndecorated(true);
		
		MoveMouseListener mml = new MoveMouseListener(contentPane);
		addMouseListener(mml);
		addMouseMotionListener(mml);
		
		gamepanel = new GamePanel();
	    gamepanel.setVisible(true);
	    gamepanel.setBounds(0,0,Var.FrameWidth, Var.FrameHeight);
	    contentPane.add(gamepanel);
	    
	    mainmenupanel = new MainMenuPanel();
	    mainmenupanel.setVisible(true);
	    mainmenupanel.setBounds(0,0,Var.FrameWidth, Var.FrameHeight);
	    contentPane.add(mainmenupanel);
	    
	    charcreationpanel = new CharCreationPanel();
	    charcreationpanel.setVisible(true);
	    charcreationpanel.setBounds(0,0,Var.FrameWidth, Var.FrameHeight);
	    contentPane.add(charcreationpanel);
	    
	    Scenes.add(gamepanel);
	    Scenes.add(mainmenupanel);
	    Scenes.add(charcreationpanel);
	    
	    if(Var.skipMenu) {
	    	changeScene(gamepanel);
	    }else {
	    	changeScene(mainmenupanel);
	    }
	    
	    
	    addComponentListener(new ComponentAdapter() {
	        public void componentResized(ComponentEvent componentEvent) {
	        	Func.changeScale(getWidth(), getHeight());
	            gamepanel.setSize(getWidth(), getHeight());
	        }
	    });
	    
	    addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	System.out.println("Exiting via Window Close Event");
	            System.exit(0);
	        }
	    });
	    
	    System.out.println("Main Window launched " + (Var.millis()-Var.firstMillis) + " ms");
	    
	    if(Var.Args.length > 0) {
	    	String[] parts = Var.Args[0].split("x");
	    	int width = Integer.parseInt(parts[0]);
	    	int height = Integer.parseInt(parts[1]);
	    	setSize(width, height);
	    	Func.changeScale(width, height);
            gamepanel.setSize(width, height);
            setLocationRelativeTo(null);
	    }
	    
	}
	
}
