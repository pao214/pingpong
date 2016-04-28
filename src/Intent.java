import java.util.HashMap;

/**
 * to send information from one activity to other
 * @author DO NOT USE THIS!
 *
 */
public class Intent {

	Class<? extends GameActivity> activity;
	HashMap<String, Object> objects = new HashMap<String, Object>();
	
	public Intent(Class<? extends GameActivity> activity) {
		this.activity = activity;
	}
	
	/**
	 * place required objects to send by their name
	 * @param key
	 * @param value
	 */
	public void putExtra(String key, Object value){
		objects.put(key, value);
	}
	
	/**
	 * retrieve specific objects by their name
	 * @param key
	 * @return
	 */
	public Object getExtra(String key){
		return objects.get(key);
	}
}
