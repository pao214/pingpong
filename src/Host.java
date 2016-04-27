import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Host extends GameActivity {

	boolean enough_clients = false;
	HashMap<String, Guest> peers = new HashMap<String, Guest>();
	HashMap<String, Racquet> racquets = new HashMap<String, Racquet>();
	
	/**
	 * Create the panel.
	 */
	public Host() {
		setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				enough_clients = true;
			}
		});
		btnPlay.setBounds(34, 35, 89, 23);
		add(btnPlay);
	}
	
	@Override
	protected void onCreate(Main main) {

		super.onCreate(main);
		
		//Dam - All connected hosts
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(220, 92, 5, 5);
		add(tabbedPane);
		
		// try to host server
		try {
			final ServerSocket serverSocket = new ServerSocket(1729);
			final String username = JOptionPane.showInternalInputDialog(getParent(), "Username");
			final String password = JOptionPane.showInternalInputDialog(getParent(), "Password");

			//send details of user
			final Thread host_info = new Thread(new Runnable(){

				@Override
				public void run() {
					while(!enough_clients){
						
						try {
							final Guest guest = new Guest(serverSocket.accept());
							guest.send(username+";"+password);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			});
			
			host_info.start();

			final StringBuilder cson = new StringBuilder("");
			
			final Thread accept_clients = new Thread(new Runnable(){

				@Override
				public void run() {
					
					
					//store all available clients  
					while(!enough_clients){
						Guest guest;
						try {
							guest = new Guest(serverSocket.accept());
							guest.name = guest.receive();
							/**/
							tabbedPane.add(new JLabel(guest.name));
							/**/
							String addra = guest.socket.getRemoteSocketAddress().toString();
							peers.put(addra, guest);
							racquets.put(addra,
									new Racquet(Constants.up));
							cson.append(addra);
							cson.append(";");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			});
			
			accept_clients.start();
			
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
					
					host_info.interrupt();
					accept_clients.interrupt();
					
					Intent intent = new Intent(Board.class);
					intent.putExtra("peers", peers);
					intent.putExtra("racquets", racquets);
					startActivity(intent);
				}
				
			})).start();
			
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
