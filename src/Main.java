import javax.swing.JFrame;

public class Main {
	
	JFrame jFrame;
	GameActivity current;
	Intent intent;
	
	public void transfer(Intent intent){
		
		Class<? extends GameActivity> activity = intent.activity;
		this.intent = intent;
		
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
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.jFrame = new JFrame("Ping-Pong");
		
		GameActivity launcher = new Menu();
		main.current = launcher;
		launcher.onCreate(main);	
		
		main.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		main.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		main.jFrame.setUndecorated(true);
		main.jFrame.setVisible(true);
	}
}
