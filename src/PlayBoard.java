import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class PlayBoard extends GameActivity {

	Player user = new Player(this, Constants.bottom);
	Player[] comps = new Player[Constants.numPlayers];
	Ball ball;
	Thread movement;
	boolean suspend = false;
	int[] hits;
	
	/**
	 * move a ball at some favored position to center
	 * move ball
	 * Create the panel.
	 */
	public PlayBoard() {
		super();
		setBackground(Constants.theme.getBackGroundColor());
		setLayout(null);
		
		user.isUser = true;
		
		ball = new Ball(Constants.random(0, Constants.board_width),
				Constants.random(0, Constants.board_height),
				Constants.chooseNeg(Constants.random(3, 6)),
				Constants.chooseNeg(Constants.random(3, 6)));
		
		if(Constants.numPlayers == 1){
			comps[0] = new Player(this, Constants.up);	
		}else{
			comps[0] = new Player(this, Constants.right);
			comps[1] = new Player(this, Constants.up);
			comps[2] = new Player(this, Constants.left);
		}
		
		hits=new int[4];
		for(int i=0;i<4;i++){
			hits[i]=0;
		}
		
	}
	
	/**
	 * start all computer threads
	 * add key listener
	 * move ball
	 * move all paddles
	 * repaint given board 
	 */
	@Override
	protected void onCreate(Main main) {
		super.onCreate(main);
		
		final JLabel bottom = new JLabel("bottom");
		
		bottom.setForeground(Color.GRAY);
		bottom.setBackground(Color.yellow);
		bottom.setBounds(Constants.screen_width-125,100,100, 100);
		bottom.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(bottom);
		
		final JLabel up = new JLabel("up");
		
		up.setForeground(Color.GRAY);
		up.setBackground(Color.yellow);
		up.setBounds(Constants.screen_width-125,225,100, 100);
		up.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(up);
		
		final JLabel left = new JLabel("left");
		
		left.setForeground(Color.GRAY);
		left.setBackground(Color.yellow);
		left.setBounds(Constants.screen_width-125,350,100, 100);
		left.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(left);
		
		final JLabel right = new JLabel("right");
		
		right.setForeground(Color.GRAY);
		right.setBackground(Color.yellow);
		right.setBounds(Constants.screen_width-125,475,100, 100);
		right.setFont(new Font("VERDANA", Font.BOLD, 20));
		add(right);
		
		final JLabel player1 = new JLabel("YOU");
		
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
		
		for (int i = 0; i < comps.length; i++) {
			comps[i].start();
		}
		
		if(Constants.numPlayers==1){
			left.setVisible(false);
			right.setVisible(false);
			player3.setVisible(false);
			player4.setVisible(false);
		}
		
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				
				switch(arg0.getKeyCode()){
					case KeyEvent.VK_RIGHT:
						switch(Constants.singlePosition){
							case Constants.bottom:
								user.paddle.dx = Constants.paddleSpeed;
								break;
							case Constants.up:
								user.paddle.dx = -Constants.paddleSpeed;
								break;
							default:
								break;
						}	
						break;
					case KeyEvent.VK_UP:
						switch(Constants.singlePosition){
							case Constants.right:
								user.paddle.dx = Constants.paddleSpeed;
								break;
							case Constants.left:
								user.paddle.dx = -Constants.paddleSpeed;
								break;
							default:
								break;
						}
						break;
					case KeyEvent.VK_LEFT:
						switch(Constants.singlePosition){
							case Constants.up:
								user.paddle.dx = Constants.paddleSpeed;
								break;
							case Constants.bottom:
								user.paddle.dx = -Constants.paddleSpeed;
								break;
							default:
								break;
						}
						break;
					case KeyEvent.VK_DOWN:
						switch(Constants.singlePosition){
							case Constants.left:
								user.paddle.dx = Constants.paddleSpeed;
								break;
							case Constants.right:
								user.paddle.dx = -Constants.paddleSpeed;
								break;
							default:
								break;
						}
						break;
					case  KeyEvent.VK_A:
						pauseGame();
						Constants.singlePosition = Constants.left;
						break;
					case  KeyEvent.VK_S:
						pauseGame();
						Constants.singlePosition = Constants.bottom;
						break;
					case  KeyEvent.VK_D:
						pauseGame();
						Constants.singlePosition = Constants.right;
						break;
					case  KeyEvent.VK_W:
						pauseGame();
						Constants.singlePosition = Constants.up;
						break;
					case KeyEvent.VK_Z:
						Constants.index = (Constants.index+2)%3;
						switch(Constants.index){
							case Constants.theme1:
								Constants.theme = Constants.t1;
								break;
							case Constants.theme2:
								Constants.theme = Constants.t2;
								break;
							case Constants.theme3:
								Constants.theme = Constants.t3;
								break;
							default:
								break;
						}
						setBackground(Constants.theme.getBackGroundColor());
						break;
					case KeyEvent.VK_X:
						Constants.index = (Constants.index+1)%3;
						switch(Constants.index){
							case Constants.theme1:
								Constants.theme = Constants.t1;
								break;
							case Constants.theme2:
								Constants.theme = Constants.t2;
								break;
							case Constants.theme3:
								Constants.theme = Constants.t3;
								break;
							default:
								break;
						}
						setBackground(Constants.theme.getBackGroundColor());
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				user.paddle.dx = 0;
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		});
		
		movement = new Thread(new Runnable(){

			@Override
			public void run() {
				
				while(true){
					
					while(true){
						if(suspend){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}else{
							repaint();
							break;
						}
					}
					
					moveBall();
					
					bottom.setText(String.valueOf(hits[0]));
					up.setText(String.valueOf(hits[2]));
					left.setText(String.valueOf(hits[1]));
					right.setText(String.valueOf(hits[3]));
					
					int sum = user.paddle.x + user.paddle.dx;
					if(sum > 0 && sum < Constants.board_width - Constants.racquet_width){
						user.paddle.x = sum;
					}
					
					for(int i=0; i< comps.length ;i++){
						
						if(comps[i]!=null){
							sum = comps[i].paddle.x + comps[i].paddle.dx;
							if(sum>0 && sum < Constants.board_width - Constants.racquet_width){
								comps[i].paddle.x = sum;
							}
						}
					}
					
					repaint();
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
				}				
			}
			
		});
		
		movement.setPriority(5);
		movement.start();
		
		setFocusable(true);
		requestFocusInWindow();
	}

	public void pauseGame(){
		suspend = true;
		for(int i=0; i<comps.length; i++){
			if(comps[i]!=null){
				comps[i].suspend = true;
			}
		}
		String[] options = {"Resume"};
		JOptionPane.showOptionDialog(getParent(),
				"Would you like to Resume the game?",
				"Network Game",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		suspend = false;
		for(int i=0; i< comps.length; i++){
			if(comps[i]!=null){
				comps[i].suspend = false;
			}
		}
	}
	
	/**
	 * handling wall collision
	 * handle collision with user
	 * 
	 */
	public void moveBall(){
		
		int x = ball.x,
				y = ball.y,
				dx = ball.dx,
				dy = ball.dy;
		
		if (y + dy > Constants.board_height - Ball.DIAMETER){
			ball.dy = -ball.dy;
			ball.y = Constants.board_height - Ball.DIAMETER;
			hits[Constants.bottom]=hits[Constants.bottom]+1;
		}else if (x + dx > Constants.board_width - Ball.DIAMETER){
			ball.dx = -ball.dx;
			ball.x = Constants.board_width - Ball.DIAMETER;
			hits[Constants.right]=hits[Constants.right]+1;
		}else if (y + dy < 0){
			ball.dy = -ball.dy;
			ball.y = 0;
			hits[Constants.up]=hits[Constants.up]+1;
		}else if (x + dx < 0){
			ball.dx = -ball.dx;
			ball.x = 0;
			hits[Constants.left]=hits[Constants.left]+1;
		} 
		
		for(int i = 0; i<3; i++){
			if(hits[i]>Constants.maxSingle){
				if(Constants.numPlayers == 1&&i==Constants.up){
					comps[0] = null;
				}else if(Constants.numPlayers == 3){
					comps[i] = null;
				}
			}
		}
		
		if(Constants.numPlayers==1){
			if(hits[0]>4&&hits[2]<=4){
				JOptionPane.showConfirmDialog(getParent(), "You've lost the game");
				System.exit(1);
			}else if(hits[0]<=4&&hits[2]>4){
				JOptionPane.showConfirmDialog(getParent(), "You've won the game");
				System.exit(1);
			}
		}else{
			if(hits[0] <=4 && hits[1] > 4 && hits[2]>4 && hits[3]>4){
				JOptionPane.showConfirmDialog(getParent(), "You've won the game");
				System.exit(1);
			}else if(hits[0]>4){
				JOptionPane.showConfirmDialog(getParent(), "You've lost the game");
				System.exit(1);
			}
		}
		
		if(user.collision()){
			//TODO add music
			(new Thread(new Runnable(){

				@Override
				public void run() {

					try {
						Constants.music("audio/ball.wav");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			})).start();
			if(dy > 0){
				ball.dy = -ball.dy;
				ball.y = Constants.board_height - Ball.DIAMETER - Constants.racquet_height;
			}else{
				ball.dx = -ball.dx;
				if(dx > 0){
					ball.x = user.paddle.x - Ball.DIAMETER;
				}else{
					ball.x = user.paddle.x + Constants.racquet_width;
				}
			}
		}
		
		for(int i=0; i<comps.length; i++){
			if(comps[i]!=null){
				int pos = comps[i].position;
				switch(pos){
					case Constants.bottom:
						if (comps[i]!=null&&comps[i].collision()){
							if(dy > 0){
								ball.dy = -ball.dy;
								ball.y = Constants.board_height - Ball.DIAMETER
										- Constants.racquet_height;
							}else{
								ball.dx = -ball.dx;
								if(dx > 0){
									ball.x = user.paddle.x - Ball.DIAMETER;
								}else{
									ball.x = user.paddle.x + Constants.racquet_width;
								}
							}
						} 
						break;
					case Constants.right:
						if (comps[i]!=null&&comps[i].collision()){
							if(dx > 0){
								ball.dx = -ball.dx;
								ball.x = Constants.board_width - Constants.racquet_height - Ball.DIAMETER;
							}else{
								ball.dy = -ball.dy;
								if(dy > 0){
									ball.y = Constants.board_height - user.paddle.x - Constants.racquet_width - Ball.DIAMETER;
								}else{
									ball.y = Constants.board_height - user.paddle.x;
								}
							}
						} 
						break;
					case Constants.up:
						if (comps[i]!=null&&comps[i].collision()){
							if(dy < 0){
								ball.dy = -ball.dy;
								ball.y = Constants.racquet_height;
							}else{
								ball.dx = -ball.dx;
								if(dx > 0){
									ball.x = 
										Constants.board_width 
										- user.paddle.x - Constants.racquet_width - Ball.DIAMETER;
								}else{
									ball.x = Constants.board_width - user.paddle.x;
								}
							}
						} 
						break;
					case Constants.left:
						if (comps[i]!=null&&comps[i].collision()){
							if (dx < 0){
								ball.dx = -ball.dx;
								ball.x = Constants.racquet_height;
							}else{
								ball.dy = -ball.dy;
								if(dy >0){
									ball.y = user.paddle.x - Ball.DIAMETER;
								}else{
									ball.y = Constants.board_height - user.paddle.x - Constants.racquet_width;
								}
							}
						} 
						break;
					default:
						break;
				}
			}
		}
		
		ball.x = ball.x + ball.dx;
		ball.y = ball.y + ball.dy;
	}
	
	/**
	 * paint the ball
	 * paint the paddles
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		Constants.theme.paint(g2d);
		
		ball.draw(g2d);
		user.paddle.paint(g2d);
		for(int i=0; i<comps.length; i++){
			if(comps[i]!=null){
				comps[i].paddle.paint(g2d);
			}
		}
	}

}
