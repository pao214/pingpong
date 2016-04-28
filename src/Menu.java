
import javax.swing.border.Border;

import javax.swing.JLabel;
import javax.swing.KeyStroke;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;

@SuppressWarnings("serial")
public class Menu extends GameActivity {

	/**
	 * Create the panel.
	 */	
	public Menu() {
		super();
		setLayout(null);
		setBackground(Color.BLACK);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
			Constants.index = Integer.parseInt(reader.readLine());
			switch(Constants.index){
				case Constants.theme1:
					Constants.theme = Constants.t1;
					break;
				case Constants.theme2:
					Constants.theme = Constants.t2;
					break;
				case Constants.theme3:
					Constants.theme = Constants.t3;
					break;
			}
			Constants.position = Integer.parseInt(reader.readLine());
			Constants.sound = Integer.parseInt(reader.readLine());
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*@Override
	 protected void paintComponent(Graphics g) {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     g.drawImage(new Image(),0,0,this);
	 }*/
	
	@Override
	protected void onCreate(Main main) {
		super.onCreate(main);
		
		//single player icon
		final JLabel single = new JLabel("New label");
		single.setIcon(new ImageIcon(Menu.class.getResource("/images/single.png")));
		single.setBounds(Constants.screen_width/5-80, Constants.screen_height*2/5, 160, 160);
		single.setFocusable(true);
		
		//multiple player icon 
		final JLabel multi = new JLabel("New label");
		multi.setIcon(new ImageIcon(Menu.class.getResource("/images/multiplayer.png")));
		multi.setBounds(2*Constants.screen_width/5-80,Constants.screen_height*2/5, 160, 160);
		multi.setFocusable(true);
		
		//settings icon
		final JLabel settings = new JLabel("New label");
		settings.setIcon(new ImageIcon(Menu.class.getResource("/images/settings.png")));
		settings.setBounds(3*Constants.screen_width/5-80,Constants.screen_height*2/5, 160, 160);
		settings.setFocusable(true);
		
		final JLabel exit = new JLabel("EXIT");
		exit.setIcon(new ImageIcon(Menu.class.getResource("/images/exit.png")));
		exit.setBounds(4*Constants.screen_width/5-80,Constants.screen_height*2/5, 160, 160);
		exit.setFocusable(true);
		
		final Border redBorder = BorderFactory.createLineBorder(java.awt.Color.CYAN,5);
		
		single.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				startActivity(new Intent(SinglePlayer.class));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			    single.setBorder(redBorder);
			    try {
			    	Constants.music("audio/click.wav");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
			
			@Override
			public void mouseExited(MouseEvent e) {
				single.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				single.setBorder(redBorder);
			}
		});
		
		single.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				single.setBorder(getBorder());
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				single.setBorder(redBorder);
			    try {
			    	Constants.music("audio/click.wav");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		single.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					startActivity(new Intent(SinglePlayer.class));
				}
			}
		});
		
		multi.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				startActivity(new Intent(MultiPlayer.class));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
		        multi.setBorder(redBorder);
		        try {
					Constants.music("audio/click.wav");
				} catch (IOException e1) {
					e1.printStackTrace();
				}		        
		    }
			
			@Override
			public void mouseExited(MouseEvent e) {
				multi.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				multi.setBorder( redBorder);
			}
			
			
		});
		
		multi.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				multi.setBorder(getBorder());
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				multi.setBorder(redBorder);
			    try {
			    	Constants.music("audio/click.wav");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		multi.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					startActivity(new Intent(MultiPlayer.class));
				}
			}
		});
		
		settings.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				startActivity(new Intent(Settings.class));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
		        settings.setBorder(redBorder);
		        try {
					Constants.music("audio/click.wav");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
		    }
			
			@Override
			public void mouseExited(MouseEvent e) {
				settings.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				settings.setBorder( redBorder);
			}
		});
		
		settings.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				settings.setBorder(getBorder());
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				settings.setBorder(redBorder);
			    try {
			    	Constants.music("audio/click.wav");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		settings.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					startActivity(new Intent(Settings.class));
				}
			}
		});
		
		exit.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(1);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setBorder(redBorder);
		        try {
					Constants.music("audio/click.wav");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				exit.setBorder( redBorder);
			}
		});
		
		exit.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				exit.setBorder(getBorder());
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				exit.setBorder(redBorder);
			    try {
			    	Constants.music("audio/click.wav");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		exit.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				System.exit(1);
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
				}
			}
		});
		
		this.add(single);
		this.add(multi);
		this.add(settings);
		this.add(exit);
		
		main.jFrame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				single.requestFocusInWindow();
			}
		});
		
		Set<AWTKeyStroke> keys = getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		Set<AWTKeyStroke> newkeys = new HashSet<AWTKeyStroke>(keys);
		newkeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newkeys);
		keys = getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
		newkeys = new HashSet<AWTKeyStroke>(keys);
		newkeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newkeys);
	}
}
