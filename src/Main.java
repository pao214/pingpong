import java.util.HashMap;
import java.util.Stack;

import javax.swing.JFrame;

/**
 * Main class that handles event flow 
 * current - contains current activity
 * objects - contains intent sent by previous activity 
 * launcher - contains launcher activity
 * @author DO NOT USE THIS!
 *
 */
public class Main {
	//TODO splash screen
	JFrame jFrame;
	GameActivity current;
	HashMap<String, Object> objects;
	Stack<GameActivity> stack = new Stack<GameActivity>();
	
	/**
	 * transfer control to incoming activity
	 * @param intent
	 */
	public void transfer(Intent intent){
		
		Class<? extends GameActivity> activity = intent.activity;
		this.objects = intent.objects;
		
		stack.push(current);
		
		if (current!=null)
			current.halt();
		try{
			current = (GameActivity)activity.newInstance();
		}catch(IllegalAccessException accessException){
			accessException.printStackTrace();
		}catch(InstantiationException instantiationException){
			instantiationException.printStackTrace();
		}
		current.onCreate(this);
		
		jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jFrame.setVisible(true);
	}
	
	/**
	 * starts launcher activity
	 * sets full screen mode
	 * @param args
	 */
	public static void main(String[] args) {
		
		Main main = new Main();
		main.jFrame = new JFrame("Ping-Pong");
		
		GameActivity launcher = new Menu();
		main.current = launcher;
		launcher.onCreate(main);
		main.stack.push(launcher);
		
		main.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		main.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		main.jFrame.setUndecorated(true);
		main.jFrame.setVisible(true);
	}
}
