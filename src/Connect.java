import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Connect extends GameActivity{
	
	ServerSocket serverSocket;
	
	public Connect() {
		
		super();
		setLayout(null);
		try {
			serverSocket = new ServerSocket(1729);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * take user name input
	 * take IP address input
	 * Retrieve user name and validate authentication using password
	 * In case no connection is made, prompted to retry  connecting to other hosts
	 */
	@Override
	protected void onCreate(Main main) {

		super.onCreate(main);
		
		final String username = JOptionPane.showInternalInputDialog(getParent(), "Username");
		final String host = JOptionPane.showInputDialog("Enter IP address");
		(new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					
					final Guest single = new Guest(new Socket(host, 1729));
					final String hostname = single.receive();
					String[] details = hostname.split(";");
					single.name = details[0];
					String passwd = JOptionPane.showInputDialog(getParent(),
							"Enter Password to connect to \n" + details[0]);
					if(details.length != 2||passwd.equals(details[1])){
						connectHost(single, username);
					}
				}catch(Exception e){
					String[] start = {"Retry", "Exit"};
					int g2 = JOptionPane.showOptionDialog(getParent(),
							"Do you want to retry connecting other hosts?",
							"Connection failure",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							start,
							start[0]);
					if(g2 == 0){
						startActivity(new Intent(Connect.class));
					}else{
						startActivity(new Intent(Menu.class));
					}
				} 
			}
		})).start();
	}
	
	/**
	 * connect to host and send details
	 * wait for peer information from host
	 * connect to their servers
	 * set the configuration of rackets
	 * handled exception
	 * @param ip_host
	 * @param name
	 */
	public void connectHost(final Guest guest, String name){
		try {
			
			guest.send(name);
			
			//TODO waiting symbol
			String[] all_clients = guest.receive().split(";");
			
			String local = InetAddress.getLocalHost().toString();
			HashMap<String, Racquet> racquets = new HashMap<String, Racquet>();
			HashMap<String, Guest> peers = new HashMap<String, Guest>();
			String ip_host = Constants.getIP(guest.socket.getRemoteSocketAddress().toString()); 
			racquets.put(ip_host, new Racquet(Constants.bottom, guest.name));
			peers.put(ip_host, guest);
			
			for(int i=0; i<all_clients.length; i++){
				String[] spl = all_clients[i].split(":");
				if(!Constants.getIP(spl[1]).equals(Constants.fromLocal(local))){
					final Guest single = new Guest(new Socket(Constants.getIP(spl[1]),
							1729));
					single.name = spl[2];
					final String address =
							Constants.getIP(single.socket.getRemoteSocketAddress().toString());					
					
					peers.put(address, single);
					racquets.put(address, new Racquet(Integer.parseInt(spl[0]), spl[2]));
					
				}else{
					Constants.reference = Integer.parseInt(spl[0]);
				}
			}
			
			Intent intent = new Intent(Board.class);
			intent.putExtra("username", name);
			intent.putExtra("peers", peers);
			intent.putExtra("racquets", racquets);
			startActivity(intent);
			
		} catch (IOException e) {
			String[] start = {"Retry", "Exit"};
			int g2 = JOptionPane.showOptionDialog(getParent(),
					"Do you want to retry connecting other hosts?",
					"Problem connecting to host.",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					start,
					start[0]);
			if(g2 == 0){
				startActivity(new Intent(Connect.class));
			}else{
				startActivity(new Intent(Menu.class));
			}
		}
	}
}
