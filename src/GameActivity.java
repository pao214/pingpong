import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * main game activity super class
 * handles transfer, control and memory management
 * @author DO NOT USE THIS!
 */
@SuppressWarnings("serial")
public class GameActivity extends JPanel {
	
	Main main;
	
	/**
	 * remove current Panel from the frame 
	 */
	protected void halt(){
		main.jFrame.remove(this);
	}
	
	/**
	 * called on creation
	 * @param main
	 */
	protected void onCreate(final Main main){
		this.main = main;
		main.jFrame.add(this);
		registerKeyboardAction(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				halt();
				final GameActivity activity = main.stack.pop();
				main.jFrame.addWindowFocusListener(new WindowFocusListener() {
					
					@Override
					public void windowLostFocus(WindowEvent arg0) {
						
					}
					
					@Override
					public void windowGainedFocus(WindowEvent arg0) {
						activity.requestFocusInWindow();
					}
				});
				main.jFrame.add(activity);
				activity.onCreate(main);
				main.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				main.jFrame.setVisible(true);
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		main.jFrame.repaint();
	}
	
	/**
	 * transfers control to a new activity
	 * @param intent
	 */
	public void startActivity(Intent intent){
		main.transfer(intent);
	}
	
	/**
	 * retrieve intents of previous activities
	 * @param key
	 * @return
	 */
	public Object getExtra(String key){
		return main.objects.get(key);
	}
}
