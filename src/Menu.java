import javax.swing.border.Border;
import javax.swing.JLabel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;

@SuppressWarnings("serial")
public class Menu extends GameActivity {

	/**
	 * Create the panel.
	 */	
	public Menu() {
		super();
		
		//
		setBackground(java.awt.Color.BLACK);
		setLayout(null);
		
		//single player icon
		final JLabel single = new JLabel("New label");
		single.setIcon(new ImageIcon(Menu.class.getResource("/images/single.png")));
		single.setBounds(50, 202, 160, 160);
		
		//multiple player icon 
		final JLabel multi = new JLabel("New label");
		multi.setIcon(new ImageIcon(Menu.class.getResource("/images/multiplayer.png")));
		multi.setBounds(250, 202, 160, 160);
		
		//settings icon
		final JLabel settings = new JLabel("New label");
		settings.setIcon(new ImageIcon(Menu.class.getResource("/images/settings.png")));
		settings.setBounds(450,202, 160, 160);
		
		//Dam - set icon
		final JLabel exit = new JLabel("EXIT");
		exit.setFont(new Font("Tahoma", Font.BOLD, 60));
		exit.setBounds(650, 202, 160, 160);
		
		//Dam - set focus traversal
		
		//Dam - border on focus
		final Border redBorder = BorderFactory.createLineBorder(java.awt.Color.CYAN,5);
		
		//
		single.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {

				startActivity(new Intent(SinglePlayer.class));
			}
			
			//shows enter event
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
			    single.setBorder(redBorder);
			    try {
			    	music("click.wav");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {

				single.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

				single.setBorder( redBorder);
			}
			
			
		});
		
		//
		multi.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {

				startActivity(new Intent(MultiPlayer.class));
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
		        multi.setBorder(redBorder);
		        try {
					music("click.wav");
				} catch (IOException e1) {

					e1.printStackTrace();
				}		        
		    }
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {

				multi.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

				multi.setBorder( redBorder);
			}
			
			
		});
		
		settings.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {

				startActivity(new Intent(Settings.class));
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {

		        settings.setBorder(redBorder);
		        try {
					music("click.wav");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
		    }
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {

				settings.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

				settings.setBorder( redBorder);
			}
			
			
		});
		
		
		exit.addMouseListener(new MouseAdapter(){
			
			//Dam
			@Override
			public void mousePressed(MouseEvent arg0) {
					
				super.mousePressed(arg0);
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {

				exit.setBorder(redBorder);
		        try {
					music("click.wav");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {

				exit.setBorder(getBorder());
			}
			
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

				exit.setBorder( redBorder);
			}
			
			
		});
		
		this.add(single);
		this.add(multi);
		this.add(settings);
		this.add(exit);
	}
	
	/**
	 * plays music
	 * @param path
	 * @throws IOException
	 */
	public static void music(String path) throws IOException{
		
		URL url = Menu.class.getResource(path);
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
    }
	
	public void focusGained(FocusEvent fe){
		
        // Get what text field got focus
        JLabel t=(JLabel)fe.getSource();
        t.setBackground(java.awt.Color.BLUE);
    }
}
