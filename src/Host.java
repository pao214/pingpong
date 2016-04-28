import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *  
 * @author DO NOT USE THIS!
 *
 */
@SuppressWarnings("serial")
public class Host extends GameActivity {

	int ref = Constants.up;
	boolean enough_clients = false;
	HashMap<String, Guest> peers = new HashMap<String, Guest>();
	HashMap<String, Racquet> racquets = new HashMap<String, Racquet>();
	
	/**
	 * Press play button to start game
	 * Create the panel.
	 */
	public Host() {
		super();
		setLayout(null);
	}
	
	/**
	 * create server socket
	 * take user name and password input
	 * advertise these details
	 * store incoming clients
	 * send all available peers to all peers
	 * send intents to board class
	 */
	@Override
	protected void onCreate(Main main) {

		super.onCreate(main);
		
		// take user name and password input
		try {
			final ServerSocket serverSocket = new ServerSocket(1729);
			Constants.serverSocket = serverSocket;
			
			JPanel details = new JPanel();
			details.setLayout(new BoxLayout(details, BoxLayout.PAGE_AXIS));
			details.add(new JLabel("Username"));
			details.add(Box.createVerticalStrut(2));
			JTextField user_panel = new JTextField(10);
			details.add(user_panel);
			details.add(Box.createVerticalStrut(10));
			details.add(new JLabel("Password"));
			details.add(Box.createVerticalStrut(2));
			JTextField pass_panel = new JTextField(10);
			details.add(pass_panel);
			
			String[] next = {"Connect Players"};
			JOptionPane.showOptionDialog(getParent(),
					details,
					"Enter Username",
					JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					next,
					next[0]);
			
			final String username = user_panel.getText();
			final String password = pass_panel.getText();
			
			final JPanel users = new JPanel();
			final DefaultListModel<String> model = new DefaultListModel<String>();
			JList<String> jList = new JList<String>(model);
			users.add(jList);
			
			final StringBuilder cson = new StringBuilder("");
			
			//advertise these details
			(new Thread(new Runnable(){ 
				
				@Override
				public void run() {
					
					while(!enough_clients){
						
						try {
							final Guest guest = new Guest(serverSocket.accept());
							
							final String addra = Constants.getIP(
									guest.socket.getRemoteSocketAddress().toString());
							guest.send(username+";"+password);
							(new Thread(new Runnable(){

								//store incoming clients
								@Override
								public void run() {
									try {
										
										guest.name = guest.receive();
										model.addElement(guest.name);
										System.out.println(guest.name);
										peers.put(addra, guest);
										racquets.put(addra, new Racquet(ref, guest.name));
										
										cson.append(ref);
										cson.append(":");
										cson.append(addra);
										cson.append(":");
										cson.append(guest.name);
										cson.append(";");
										
										ref = Constants.nextRef(ref);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								
							})).start();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			})).start();
			
			//send all available peers to all peers
			(new Thread(new Runnable(){

				@Override
				public void run() {
					
					while(!enough_clients){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					
					String csonString = cson.toString();
					
					//send info of all clients to all clients
					Iterator<Guest> all = peers.values().iterator();
					while(all.hasNext()){
						Guest single = all.next();
						single.send(csonString);
					}
					
					//Go to board class
					Intent intent = new Intent(Board.class);
					intent.putExtra("username", username);
					intent.putExtra("peers", peers);
					intent.putExtra("racquets", racquets);
					startActivity(intent);
				}
				
			})).start();
			
			String[] start = {"Start Game"};
			int g2 = JOptionPane.showOptionDialog(getParent(),
					users,
					"Enter Username",
					JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					start,
					start[0]);
			
			if(g2==0){
				enough_clients = true;
			}
			
		} catch (IOException e) {
			
			int retry = JOptionPane.showConfirmDialog(getParent(), e.getMessage(), "Retry", 0);
			if(retry == 0){
				startActivity(new Intent(Host.class));
			}else{
				startActivity(new Intent(MultiPlayer.class));
			}
		}
	}
}
