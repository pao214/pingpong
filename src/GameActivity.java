import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameActivity extends JPanel implements Runnable{
	
	Main main;
	Thread t;
 	HashMap<JComponent, Integer> ids; 
 	
 	public GameActivity() {
		t = new Thread();
	}
	
 	
	@SuppressWarnings("unused")
	private void add(JComponent view){
		add(view);
		ids.put(view, generateId(ids));
	}
	
	@SuppressWarnings("unused")
	private int getId(JComponent jComponent){
		return ids.get(jComponent);
	}
	
	private int generateId(HashMap<JComponent, Integer> ids){
		HashSet<Integer> set = (HashSet<Integer>) ids.values(); 
		boolean b= true;
		Random random = new Random();
		int next = 0;
		do{
			next = random.nextInt(Integer.MAX_VALUE);
			b = set.contains(next);
		}while(b);
		return next;
	}
	
	protected void halt(){
		main.jFrame.remove(this);
	}
	
	protected void onCreate(Main main){
		this.main = main;
		main.jFrame.repaint();
		main.jFrame.add(this);
	}
	
	@Override
	public void run() {	
		
	}
	
	public void startActivity(Intent intent){
		main.transfer(intent);
	}
	
	public Object getExtra(String key){
		return main.intent.getExtra(key);
	}
}
