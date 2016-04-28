import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Ball Component in physical board
 * @author DO NOT USE THIS!
 *
 */
public class Ball {
	
	int	x, y, dx, dy;
	public static final int DIAMETER = 30;
	
	/**
	 * Initializing Constructor
	 * @param color
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 */
	public Ball(int x, int y, int dx, int dy) {
		super();
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * set ball color
	 * find rotate amount w.r.t. physical board
	 * transform x and y
	 * paint ball according to convenience of user
	 * @param g2d
	 */
	public void paint(Graphics2D g2d) {
//		g2d.setColor(Constants.theme.getBallColor());
		
		g2d.setColor(Constants.theme.getBallColor());
		
		int rotate = (4+Constants.position - Constants.reference)%4;
		int xp = x, yp = y;
		
		switch(rotate){
			case 1:
				xp = y;
				yp = Constants.board_height - x - DIAMETER;
				break;
			case 2:
				xp = Constants.board_width - x - DIAMETER;
				yp = Constants.board_height - y - DIAMETER;
				break;
			case 3:
				xp = Constants.board_height - y - DIAMETER;
				yp = x;
				break;
			default:
				break;
		}
		
		g2d.fillOval(Constants.sx + xp, Constants.sy + yp, Ball.DIAMETER, Ball.DIAMETER);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Constants.theme.getBallColor());
		
		int xp = x, yp = y;
		
		switch(Constants.singlePosition){
			case 1:
				xp = y;
				yp = Constants.board_height - x - DIAMETER;
				break;
			case 2:
				xp = Constants.board_width - x - DIAMETER;
				yp = Constants.board_height - y - DIAMETER;
				break;
			case 3:
				xp = Constants.board_height - y - DIAMETER;
				yp = x;
				break;
			default:
				break;
		}
		
		g2d.fillOval(Constants.sx + xp, Constants.sy + yp, Ball.DIAMETER, Ball.DIAMETER);
	}
	
	/**
	 * return bounding rectangle
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
