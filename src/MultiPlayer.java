import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class MultiPlayer extends GameActivity {

	/**
	 * Create the panel.
	 */
	public MultiPlayer() {
		super();
		setLayout(null);
	}
	
	/**
	 * Check if user wants to host or connect the program
	 */
	@Override
	protected void onCreate(Main main) {
		super.onCreate(main);
		String[] options = {"Host", "Connect"};
		int n = JOptionPane.showOptionDialog(getParent(),
				"Would you like to Host/Connect?",
				"Network Game",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		
		if(n == 0){
			startActivity(new Intent(Host.class));
		}else{
			startActivity(new Intent(Connect.class));
		}
	}
}
