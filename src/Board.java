import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Intents - peers, rackets, user name
 * @author DO NOT USE THIS!
 *TODO see collision effects
 *TODO themes
 */
@SuppressWarnings("serial")
public class Board extends GameActivity {
	
	Ball ball;
	HashMap<String, Racquet> racquets = new HashMap<String, Racquet>();
	HashMap<String, Guest> peers = new HashMap<String, Guest>();
	Racquet user;
	int speed;
	int[] hits;
	
	/**
	 * set background
	 * Create the panel.
	 */
	public Board() {
		super();
		setBackground(Constants.theme.getBackGroundColor());	
		setLayout(null);
		hits = new int[4];
		for(int i=0;i<4;i++){
			hits[i]=0;
		}
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Main main) {
		super.onCreate(main);
		ball = new Ball(Constants.random(0, Constants.board_width),
				Constants.random(0, Constants.board_height),
				Constants.chooseNeg(Constants.random(3, 6)),
				Constants.chooseNeg(Constants.random(3, 5)));
		user = new Racquet(Constants.reference, (String)getExtra("username"));
		user.isUser = true;
		peers = (HashMap<String, Guest>)getExtra("peers");
		racquets = (HashMap<String, Racquet>)getExtra("racquets");
		
		final JLabel bottom = new JLabel("bottom");
		bottom.setText(String.valueOf(hits[0]));
		bottom.setForeground(Color.GRAY);
		bottom.setBackground(Color.yellow);
		bottom.setBounds(Constants.screen_width-125,100,100, 100);
		bottom.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(bottom);
		
		final JLabel up = new JLabel("up");
		up.setText(String.valueOf(hits[2]));
		up.setForeground(Color.GRAY);
		up.setBackground(Color.yellow);
		up.setBounds(Constants.screen_width-125,225,100, 100);
		up.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(up);
		
		final JLabel left = new JLabel("left");
		left.setText(String.valueOf(hits[1]));
		left.setForeground(Color.GRAY);
		left.setBackground(Color.yellow);
		left.setBounds(Constants.screen_width-125,350,100, 100);
		left.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(left);
		
		final JLabel right = new JLabel("right");
		right.setText(String.valueOf(hits[3]));
		right.setForeground(Color.GRAY);
		right.setBackground(Color.yellow);
		right.setBounds(Constants.screen_width-125,475,100, 100);
		right.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(right);
		
		final JLabel player1 = new JLabel("player1");
		
		player1.setForeground(Color.GRAY);
		player1.setBackground(Color.yellow);
		player1.setBounds(Constants.screen_width-250,100,100, 100);
		player1.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(player1);
		
		final JLabel player2 = new JLabel("player2");
		
		player2.setForeground(Color.GRAY);
		player2.setBackground(Color.yellow);
		player2.setBounds(Constants.screen_width-250,225,100, 100);
		player2.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(player2);
		
		final JLabel player3 = new JLabel("player3");
		
		player3.setForeground(Color.GRAY);
		player3.setBackground(Color.yellow);
		player3.setBounds(Constants.screen_width-250,350,100, 100);
		player3.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(player3);
		
		final JLabel player4 = new JLabel("player4");
		
		player4.setForeground(Color.GRAY);
		player4.setBackground(Color.yellow);
		player4.setBounds(Constants.screen_width-250,475,100, 100);
		player4.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(player4);
		
		bottom.setVisible(false);
		up.setVisible(false);
		left.setVisible(false);
		right.setVisible(false);
		player1.setVisible(false);
		player2.setVisible(false);
		player3.setVisible(false);
		player4.setVisible(false);
		
		//listen to events
		(new Thread(new Runnable(){

			@Override
			public void run() {
				
				
				Iterator<Guest> all = peers.values().iterator();
				while(all.hasNext()){
					
					try{
						//connect to all server sockets
						final Guest client = all.next();
						final String address = Constants.getIP(
								client.socket.getRemoteSocketAddress().toString());
						
						//listen to events
						(new Thread(new Runnable(){

							@Override
							public void run() {
						
								while(true){
									try {
										String response = client.receive();
										String[] arr = response.split(";");
										String[] first = arr[0].split(":");
										Racquet racquet = racquets.get(address);
										racquet.cx = Integer.parseInt(first[0]);
										racquet.cy = Integer.parseInt(first[1]);
										racquet.cspeed = Integer.parseInt(first[2]);
										String[] second = arr[1].split(":");
										int event = 0;
										try{
											event = Integer.parseInt(second[0]);
										}catch(Exception e){
											e.printStackTrace();
										}
										switch(event){
											case Constants.none:
												break;
											case Constants.wall:
												hits[racquet.position]= hits[racquet.position]+1;
												break;
											case Constants.paddle:
												ball.x = Integer.parseInt(second[1]);
												ball.dx = Integer.parseInt(second[2]);
												ball.y = Integer.parseInt(second[3]);
												ball.dy = Integer.parseInt(second[4]); 
												break;
											case Constants.init:
												ball.x = Integer.parseInt(second[1]);
												ball.dx = Integer.parseInt(second[2]);
												ball.y = Integer.parseInt(second[3]);
												ball.dy = Integer.parseInt(second[4]); 
												break;
											default:
												break;
										}
										
										bottom.setText(String.valueOf(hits[0]));
										up.setText(String.valueOf(hits[2]));
										left.setText(String.valueOf(hits[1]));
										right.setText(String.valueOf(hits[3]));
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
							
						})).start();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
		})).start();

		final Object[] guests = peers.values().toArray();
		final ExecutorService[] executors = new ExecutorService[guests.length];
		for(int i=0; i< guests.length; i++){
			executors[i] = Executors.newFixedThreadPool(1);
		}
		
		//trigger event
		(new Thread(new Runnable(){

			@Override
			public void run() {	
				
				for(int j=0; j<guests.length; j++){
					Guest single = (Guest)guests[j];
					if (Constants.reference == Constants.bottom) {
						StringBuilder init = new StringBuilder(
								String.valueOf(Constants.init));
						init.append(":");
						init.append(String.valueOf(ball.x));
						init.append(":");
						init.append(String.valueOf(ball.dx));
						init.append(":");
						init.append(String.valueOf(ball.y));
						init.append(":");
						init.append(String.valueOf(ball.dy));
						single.send(getString(init));
					}
				}
				
				while(true){
					
					moveRacquet();
					final int event = moveBall();
					
					bottom.setText(String.valueOf(hits[0]));
					up.setText(String.valueOf(hits[2]));
					left.setText(String.valueOf(hits[1]));
					right.setText(String.valueOf(hits[3]));
					
					for(int i=0; i<guests.length; i++){
						final Guest single = (Guest)guests[i];
						executors[i].submit(new Thread(new Runnable(){

							@Override
							public void run() {
								
								if(single.isConnected()){
									switch(event){
										case 0:
											StringBuilder builder =
												new StringBuilder(String.valueOf(Constants.none));
											builder.append(":");
											single.send(getString(builder));
											break;
										case 1:
											builder = new StringBuilder(
													String.valueOf(Constants.wall));
											builder.append(":");
											single.send(getString(builder));
											break;
										case 2:
											builder = new StringBuilder(
													String.valueOf(Constants.paddle));
											builder.append(":");
											builder.append(String.valueOf(ball.x));
											builder.append(":");
											builder.append(String.valueOf(ball.dx));
											builder.append(":");
											builder.append(String.valueOf(ball.y));
											builder.append(":");
											builder.append(String.valueOf(ball.dy));
											single.send(getString(builder));
											break;
										default:
											break;
									}
								}else{
									peers.remove(single);
								}
							}
							
						}));
					}
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		})).start();

				
		//display event
		(new Thread(new Runnable(){

			@Override
			public void run() {
				
				while(true){
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		})).start();
		
		Iterator<Racquet> names = racquets.values().iterator();
		while(names.hasNext()){
			Racquet r = names.next();
			int position = Constants.getAbs(r.position);
			
			switch(r.position){
			case Constants.bottom:
				player1.setText(r.username);
				player1.setVisible(true);
				bottom.setVisible(true);
				break;
			case Constants.right:
				player4.setText(r.username);
				player4.setVisible(true);
				right.setVisible(true);
				break;
			case Constants.up:
				player2.setText(r.username);
				player2.setVisible(true);
				up.setVisible(true);
				break;
			case Constants.left:
				player3.setText(r.username);
				player3.setVisible(true);
				left.setVisible(true);
				break;
		}
			
			switch(position){
			case Constants.bottom:
				JLabel jLabel = new JLabel(r.username);
				jLabel.setForeground(Color.GRAY);
				jLabel.setBackground(Color.yellow);
				jLabel.setBounds(Constants.sx + Constants.board_width/2 - 50,
									Constants.sy + Constants.board_height + 5,
									100, 100);
				jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
				add(jLabel);
				break;
			case Constants.right:
				jLabel = new JLabel(r.username);
				jLabel.setForeground(Color.GRAY);
				jLabel.setBackground(Color.yellow);
				jLabel.setBounds(Constants.sx + Constants.board_width + 25,
									Constants.sy + Constants.board_height - 50 ,
									100, 100);
				jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
				add(jLabel);
				break;
			case Constants.up:
				jLabel = new JLabel(r.username);
				jLabel.setForeground(Color.GRAY);
				jLabel.setBackground(Color.yellow);
				jLabel.setBounds(Constants.sx + Constants.board_width/2 - 50,
									Constants.sy - 125 ,
									100, 100);
				jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
				add(jLabel);
				break;
			case Constants.left:
				jLabel = new JLabel(r.username);
				jLabel.setForeground(Color.GRAY);
				jLabel.setBackground(Color.yellow);
				jLabel.setBounds(Constants.sx + -125,
									Constants.sy + Constants.board_height/2 -50,
									100, 100);
				jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
				add(jLabel);
				break;
			default:
				break;
		}
		
	}
		
		switch(Constants.reference){
		case Constants.bottom:
			player1.setText(user.username);
			player1.setVisible(true);
			bottom.setVisible(true);
			break;
		case Constants.right:
			player4.setText(user.username);
			player4.setVisible(true);
			right.setVisible(true);
			break;
		case Constants.up:
			player2.setText(user.username);
			player2.setVisible(true);
			up.setVisible(true);
			break;
		case Constants.left:
			player3.setText(user.username);
			player3.setVisible(true);
			left.setVisible(true);
			break;
		}
		
		switch(Constants.position){
		case Constants.bottom:
			JLabel jLabel = new JLabel(user.username);
			jLabel.setForeground(Color.GRAY);
			jLabel.setBackground(Color.yellow);
			jLabel.setBounds(Constants.sx + Constants.board_width/2 - 50,
								Constants.sy + Constants.board_height + 5,
								100, 100);
			jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
			add(jLabel);
			break;
		case Constants.right:
			jLabel = new JLabel(user.username);
			jLabel.setForeground(Color.GRAY);
			jLabel.setBackground(Color.yellow);
			jLabel.setBounds(Constants.sx + Constants.board_width + 25,
								Constants.sy + Constants.board_height - 50 ,
								100, 100);
			jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
			add(jLabel);
			break;
		case Constants.up:
			jLabel = new JLabel(user.username);
			jLabel.setForeground(Color.GRAY);
			jLabel.setBackground(Color.yellow);
			jLabel.setBounds(Constants.sx + Constants.board_width/2 - 50,
								Constants.sy - 125 ,
								100, 100);
			jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
			add(jLabel);
			break;
		case Constants.left:
			jLabel = new JLabel(user.username);
			jLabel.setForeground(Color.GRAY);
			jLabel.setBackground(Color.yellow);
			jLabel.setBounds(Constants.sx + -125,
								Constants.sy + Constants.board_height/2 -50,
								100, 100);
			jLabel.setFont(new Font("VERDANA", Font.BOLD, 20));
			add(jLabel);
			break;
		default:
			break;
    }
	
		
		
		
		
		addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()){
					case KeyEvent.VK_RIGHT:
						switch(Constants.position){
							case Constants.bottom:
								speed = 3;
								break;
							case Constants.up:
								speed = -3;
								break;
							default:
								break;
						}	
						break;
					case KeyEvent.VK_UP:
						switch(Constants.position){
							case Constants.right:
								speed = 3;
								break;
							case Constants.left:
								speed = -3;
								break;
							default:
								break;
						}
						break;
					case KeyEvent.VK_LEFT:
						switch(Constants.position){
							case Constants.up:
								speed = 3;
								break;
							case Constants.bottom:
								speed = -3;
								break;
							default:
								break;
						}
						break;
					case KeyEvent.VK_DOWN:
						switch(Constants.position){
							case Constants.left:
								speed = 3;
								break;
							case Constants.right:
								speed = -3;
								break;
							default:
								break;
						}
						break;
				}			
			}

			@Override
			public void keyReleased(KeyEvent e) {
				speed = 0;
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
		
		setFocusable(true);
		requestFocusInWindow();
	}
	
	public int moveBall(){
		
		int ret = Constants.none;
		int x = ball.x,
				y = ball.y,
				dx = ball.dx,
				dy = ball.dy;
		
		if (y + dy > Constants.board_height - Ball.DIAMETER){
			ball.dy = -ball.dy;
			ball.y = Constants.board_height - Ball.DIAMETER;
			if(Constants.reference==Constants.bottom){
				ret=Constants.wall;
				hits[Constants.reference]=hits[Constants.reference]+1;
				System.out.println(hits[Constants.reference]);
				}
		}else if (x + dx > Constants.board_width - Ball.DIAMETER){
			ball.dx = -ball.dx;
			ball.x = Constants.board_width - Ball.DIAMETER;
			if(Constants.reference==Constants.right){
				ret=Constants.wall;
				hits[Constants.reference]=hits[Constants.reference]+1;
			}
		}else if (y + dy < 0){
			ball.dy = -ball.dy;
			ball.y = 0;
			if(Constants.reference==Constants.up){
				ret=Constants.wall;
				hits[Constants.reference]=hits[Constants.reference]+1;
			}
		}else if (x + dx < 0){
			ball.dx = -ball.dx;
			ball.x = 0;
			if(Constants.reference==Constants.left){
				ret=Constants.wall;
				hits[Constants.reference]=hits[Constants.reference]+1;
			}
		} 
		
		if(peers.isEmpty()){
			JOptionPane.showConfirmDialog(getParent(), "You've won");
			System.exit(1);
		}else if(hits[Constants.reference] > 4){
			
				Iterator<Guest> alt = peers.values().iterator();
				while(alt.hasNext()){
					alt.next().finalize();
				}
				JOptionPane.showConfirmDialog(getParent(), "You've lost");
				System.exit(1);
		}
		
		switch(Constants.reference){
			case Constants.bottom:
				if (y + dy > Constants.board_height - Ball.DIAMETER){
					
				}else if (collision()){
					if(dy > 0){
						ball.dy = -ball.dy;
						ball.y = Constants.board_height - Ball.DIAMETER - Constants.racquet_height;
					}else{
						ball.dx = -ball.dx;
						if(dx > 0){
							ball.x = user.cx - Constants.racquet_width/2 - Ball.DIAMETER;
						}else{
							ball.x = user.cx + Constants.racquet_width/2;
						}
					}
					ret = Constants.paddle;
				} 
				break;
			case Constants.right:
				if (x + dx > Constants.board_width - Ball.DIAMETER){
					
				}else if (collision()){
					if(dx > 0){
						ball.dx = -ball.dx;
						ball.x = Constants.board_width - Ball.DIAMETER - Constants.racquet_height;
					}else{
						ball.dy = - ball.dy;
						if(dy > 0){
							ball.y = user.cy - Constants.racquet_width/2- Ball.DIAMETER;
						}else{
							ball.y = user.cy + Constants.racquet_width/2;
						}
					}
					ret = Constants.paddle;
				} 
				break;
			case Constants.up:
				if (y + dy < 0){
					
				}else if (collision()){
					System.out.println("before: " + ball.x + ":" + ball.y + ":"
							+ ball.dx + ":" + ball.dy + ";" +
							(Constants.board_width - user.x - Constants.racquet_width)) ;
					if(dy < 0){
						ball.dy = -ball.dy;
						ball.y = Constants.racquet_height;
					}else{
						ball.dx = -ball.dx;
						if(dx > 0){
							ball.x = user.cx - Constants.racquet_width/2 - Ball.DIAMETER;
						}else{
							ball.x = user.cx + Constants.racquet_width/2;
						}
					}
					
					System.out.println("After: "+ball.dx +":" +ball.dy);
					ret = Constants.paddle;
				} 
				break;
			case Constants.left:
				if (x + dx < 0){
					
				}else if (collision()){
					if(dx < 0){
						ball.dx = -ball.dx;
						ball.x = Constants.board_width - Ball.DIAMETER - Constants.racquet_height;
					}else{
						ball.dy = - ball.dy;
						if(dy > 0){
							ball.y = user.cy - Constants.racquet_width/2- Ball.DIAMETER;
						}else{
							ball.y = user.cy + Constants.racquet_width/2;
						}
					}
					ret = Constants.paddle;
				} 
				break;
			default:
				break;
		}

		ball.x = ball.x + ball.dx;
		ball.y = ball.y + ball.dy;
		
		return ret;
	}
	
	public void moveRacquet(){
		
		//TODO paddle paddle collision
		
		
		int sum = 0;
		switch(Constants.reference){
			case Constants.bottom:
				sum = user.cx + speed;
				if(sum>Constants.racquet_width/2 &&
						sum < Constants.board_width - Constants.racquet_width/2){
					user.cx = sum;
				}
				break;
			case Constants.right:
				sum = user.cy - speed;
				if(sum>Constants.racquet_width/2 &&
						sum < Constants.board_height - Constants.racquet_width/2){
					user.cy = sum;
				}
				break;
			case Constants.up:
				sum = user.cx - speed;
				if(sum>Constants.racquet_width/2 &&
						sum < Constants.board_width - Constants.racquet_width/2){
					user.cx = sum;
				}
				break;
			case Constants.left:
				sum = user.cy + speed;
				if(sum>Constants.racquet_width/2 &&
						sum < Constants.board_height - Constants.racquet_width/2){
					user.cy = sum;
				}
				break;
			default:
				break;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.CYAN);
		g2d.fillRect(Constants.sx, Constants.sy, Constants.board_width, Constants.board_height);
		
		Constants.theme.paint(g2d);
		
		ball.paint(g2d);
		Iterator<Racquet> all = racquets.values().iterator();
		while(all.hasNext()){
			
			Racquet r = all.next();
			r.paint(g2d);
		}
		user.paint(g2d);
	}
	
	private boolean collision() {
		return user.getBounds().intersects(ball.getBounds());
	}
	
	public String getString(StringBuilder event){
		StringBuilder builder = new StringBuilder(String.valueOf(user.cx));
		builder.append(":");
		builder.append(String.valueOf(user.cy));
		builder.append(":");
		builder.append(String.valueOf(speed));
		builder.append(";");
		builder.append(event);
		return builder.toString();		
	}
	
	public void finalize(){
		Iterator<Guest> all = peers.values().iterator();
		while(all.hasNext()){
			all.next().finalize();
		}
		try {
			Constants.serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
