import java.util.Random;

public class Player extends Thread{

	PlayBoard playBoard;
	int position;
	Paddle paddle;
	boolean isUser = false;
	int level = Constants.level;
	boolean suspend = false;
	Random random;
	int espeed;
	
	Player(PlayBoard playBoard, int position){
		this.playBoard = playBoard;
		this.position = position;
		paddle = new Paddle(position);
		random = new Random();
		
		switch(level){
		case 0:
			espeed =1;
		case 1:
			espeed =3;
		case 2:
			espeed =2; 
		}
	}
	
	/**
	 * TODO - each task has its own response time
	 * 
	 * determine predicted position of ball
	 * --time proportional to distance from player 
	 * 
	 * determine predicted position of ball and time for next collision
	 * --time taken proportional to closest distance from board
	 * 
	 * detect the event of collision
	 * --timely check
	 * 
	 * 
	 * 
	 * assess the player of target - 1.1(right after a collision)
	 * assess time required to reach player - 1.2(only before another collision)
	 * if ball toward other player:
	 * 
	 * if sufficient time available:
	 * task1.1
	 * guess direction of movement of ball 
	 * task1.1.1: assuming spin zero, final state reached assuming current state and 
	 * task1.1.2: thus make a plan on required state
	 * task1.1.3: execute the plan
	 * plan involves finding position from which we can cover max range possible
	 * task1.2:
	 * assume collision with wall
	 * find position where the ball arrives and try to reach there
	 * else:
	 * remain stationary
	 * 
	 * if ball toward us:
	 * 
	 * Analyze the reach position (predict position => range) - 
	 * prediction accuracy increases with available time
	 * if time sufficient :
	 * perform both attack and defense strategies
	 * if insufficient track final position with higher accuracy and lower response time
	 * else:
	 * task2.1 assess the position of all other users
	 * task2.2 assume all 3 possibilities and track which direction ball goes
	 * task2.3 given final states in each, find the user which takes max. time to reach destination
	 * task2.3.1 find max. of these max.times and hit ball in that direction.  
	 *   
	 */

	@Override
	public void run() {
		
		while(true){
			
			
			while(suspend){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			int count = 0;
			int[] predict = predictBall();
			int[] nextState = targetState(predict[0], predict[1],
					predict[2], predict[3]);
			do{
				nextState = targetState(nextState[1], nextState[2],
						nextState[3], nextState[4]);
				count = count + nextState[5];
				
			}while(nextState[0] != position);
			
			
			
			int xp = 0;
			
			switch(position){
				case Constants.bottom:
					xp = nextState[1] + Ball.DIAMETER/2;
					break;
				case Constants.right:
					xp = Constants.board_height - nextState[2] - Ball.DIAMETER/2;
					break;
				case Constants.up:
					xp = Constants.board_width - nextState[1] - Ball.DIAMETER/2;
					break;
				case Constants.left:
					xp = nextState[2] + Ball.DIAMETER/2;
					break;
			}
			
			int req = xp - (paddle.x + Constants.racquet_width/2);
			
			if(req > 0){
				int rest = count - (req + Constants.racquet_width/2)/espeed;
				if(rest > 0){
					paddle.dx = 0;
					try {
						Thread.sleep(rest*(10-2*level));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					paddle.dx = espeed;
				}else{
					paddle.dx = espeed;
				}
			}else if(req < 0){
				int rest = count + (req - Constants.racquet_width/2)/espeed;
				if(rest > 0){
					paddle.dx = 0;
					try {
						Thread.sleep(rest*10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					paddle.dx = - espeed;
				}else{
					paddle.dx = -espeed;
				}
			}else{
				paddle.dx = 0;
			}
			
			try {
				Thread.sleep(300-level*75);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int[] targetState(int x, int y, int dx, int dy){
		
		int count = 0;
		while(true){
			if (y + dy > Constants.board_height - Ball.DIAMETER){
				return new int[]{Constants.bottom,
						x, Constants.board_height - Ball.DIAMETER,
						dx, -dy, count};
			}else if (x + dx > Constants.board_width - Ball.DIAMETER){
				return new int[]{Constants.right,
						Constants.board_width - Ball.DIAMETER, y,
						-dx, dy, count};
			}else if (y + dy < 0){
				return new int[]{Constants.up,
						x, 0,
						dx, -dy, count};
			}else if (x + dx < 0){
				return new int[]{Constants.left,
						0, y,
						-dx, dy, count};
			} 
			
			x = x +dx;
			y = y + dy;
			count++;
		}
	}
	
	public boolean detectCollide(){
		int[] pb = predictBall();
		int[] b = predictBall();
		int[] sb = predictBall();
		return (b[0] - pb[0])*(sb[0] - b[0]) < 0 || (b[1] - pb[1])*(sb[1] - b[1]) < 0;
	}
	
	public int[][] predictCollide(){
		int[] pb = predictBall();
		int[] b = predictBall();
		int dx = b[0] - pb[0],
			dy = b[1] - pb[1],
			x = b[0],
			y = b[1];
		int[] first;
		while(true){
			if (y + dy > Constants.board_height - Ball.DIAMETER){
				first = new int[]{Constants.bottom,
						x + Ball.DIAMETER/2};
				y = Constants.board_height - Ball.DIAMETER;
				dy = -dy;
				break;
			}else if (x + dx > Constants.board_width - Ball.DIAMETER){
				first = new int[]{Constants.right,
						Constants.board_width - y - Ball.DIAMETER/2};
				x = Constants.board_width - Ball.DIAMETER;
				dx = -dx;
				break;
			}else if (y + dy < 0){
				first = new int[]{Constants.up,
						Constants.board_width - x - Ball.DIAMETER/2};
				y = 0;
				dy = -dy;
				break;
			}else if (x + dx < 0){
				first = new int[]{Constants.up,
						y};
				x = 0;
				dx = -dx;
				break;
			} 
			x = x +dx;
			y = y +dy;
		}
		
		while(true){
			if (y + dy > Constants.board_height - Ball.DIAMETER){
				return new int[][]{first, new int[]{Constants.bottom,
						x + Ball.DIAMETER/2}};
			}else if (x + dx > Constants.board_width - Ball.DIAMETER){
				return new int[][]{first, new int[]{Constants.right,
						Constants.board_width - y - Ball.DIAMETER/2}};
			}else if (y + dy < 0){
				return new int[][]{first, new int[]{Constants.up,
						Constants.board_width - x - Ball.DIAMETER/2}};
			}else if (x + dx < 0){
				return new int[][]{first, new int[]{Constants.up,
						y}};
			} 
			x = x +dx;
			y = y +dy;
		}
	}
	
	public int[] predictBall(){
		int pxp = 0, pyp = 0, xp = 0, yp =0;
		
		int pbx = playBoard.ball.x, pby = playBoard.ball.y;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int bx = playBoard.ball.x,
				by = playBoard.ball.y;
		
		switch(position){
			case Constants.bottom:
				
				pxp = Constants.board_height - pby;
				pyp = pbx;
				pxp = pbx + random.nextInt(pxp/2+1);
				pyp = pby - random.nextInt(pyp/2+1);
				
				
				xp = Constants.board_height - by;
				yp = bx;
				xp = bx + random.nextInt(xp/2+1);
				yp = by - random.nextInt(yp/2+1);
				break;
				
			case Constants.right:
				pxp = pby;
				pyp = Constants.board_width - pbx;
				pxp = pbx - random.nextInt(pxp/2+1); 
				pyp = pby - random.nextInt(pyp/2+1); 
				
				xp = by;
				yp = Constants.board_width - bx;
				xp = bx - random.nextInt(xp/2+1); 
				yp = by - random.nextInt(yp/2+1); 
				break;
			case Constants.up:
				pxp = pby;
				pyp = pbx;
				pxp = pbx - random.nextInt(pxp/2+1);
				pyp = pby + random.nextInt(pyp/2+1);				
				
				xp = by;
				yp = bx;
				xp = bx - random.nextInt(xp/2+1);
				yp = by + random.nextInt(yp/2+1);
				break;
			case Constants.left:
				pxp = pby;
				pyp = pbx;
				pxp = pxp + random.nextInt(pxp/2+1);
				pyp = pby + random.nextInt(pyp/2+1);				
				
				xp = by;
				yp = bx;
				xp = xp + random.nextInt(xp/2+1);
				yp = by + random.nextInt(yp/2+1);
				break;
			default:
				break;
		}
		return new int[]{bx, by, bx - pbx, by - pby};
	}
	
	public boolean collision(){
		return playBoard.ball.getBounds().intersects(paddle.getBounds());
	}
}
