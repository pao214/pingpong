import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * handle sockets more gracefully
 * @author DO NOT USE THIS!
 */
public class Guest {
	
	private DataInputStream inStream;
	private PrintStream outStream;
	Socket socket;
	String name;
	
	/**
	 * Initialize input and output streams with the socket
	 * @param socket
	 */
	Guest(Socket socket){
		try{
			inStream = new DataInputStream(
					new BufferedInputStream(socket.getInputStream(), 1024)); 
			outStream = new PrintStream(
					new BufferedOutputStream(socket.getOutputStream(), 1024),
					true);
			this.socket = socket;
		}catch(IOException exception){
			System.out.println("Couldn't initialize socket action: "
					+exception.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * send out a message through the socket
	 * @param s
	 */
	public void send(String s){
		outStream.println(s);
	}
	
	/**
	 * receive a message through the socket
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public String receive() throws IOException{
		return inStream.readLine();
	}
	
	/**
	 * close connection of socket when not in use
	 */
	public void closeConnections(){
		try {
			socket.close();
			socket = null;
		} catch (IOException e) {
			System.out.println("Couldn't close Socket: "+ e.getMessage());
		}
	}
	
	/**
	 * check if socket is connected
	 * @return
	 */
	public boolean isConnected(){
		return (inStream!=null && outStream!=null) && socket!= null;
	}
	
	/**
	 * remove all resources
	 */
	public void finalize(){
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Couldn't close Socket: "+ e.getMessage());
			}
			socket = null;
		}
	}
}
