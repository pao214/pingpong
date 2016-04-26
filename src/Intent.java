import java.util.HashMap;

public class Intent {

	Class<? extends GameActivity> activity;
	HashMap<String, Object> objects;
	
	public Intent(Class<? extends GameActivity> activity) {
		this.activity = activity;
	}
	
	public void putExtra(String key, Object value){
		objects.put(key, value);
	}
	
	public Object getExtra(String key){
		return objects.get(key);
	}
}
