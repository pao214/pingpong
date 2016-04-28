import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
	
	int position, x, dx;
	
	Paddle(int position){
		this.position = position;
		x = (Constants.board_width - Constants.racquet_width)/2 ;
	}
	
	public void paint(Graphics2D g){
		
		g.setColor(Constants.theme.getRacquetColor());
		
		switch((4+Constants.singlePosition+position)%4){
			case Constants.bottom:
				g.fillRect(Constants.sx + x,
						Constants.sy + Constants.board_height - Constants.racquet_height,
						Constants.racquet_width,
						Constants.racquet_height);	
				break;
			case Constants.right:
				g.fillRect(Constants.sx + Constants.board_width - Constants.racquet_height,
						Constants.sy + Constants.board_height - x - Constants.racquet_width,
						Constants.racquet_height,
						Constants.racquet_width);
				break;
			case Constants.up:
				g.fillRect(Constants.sx + Constants.board_width - x - Constants.racquet_width,
						Constants.sy + 0,
						Constants.racquet_width,
						Constants.racquet_height);
				break;
			case Constants.left:
				g.fillRect(Constants.sx + 0,
						Constants.sy + x,
						Constants.racquet_height,
						Constants.racquet_width);
				break;
			default:
				break;
		}
	}
	
	public Rectangle getBounds() {
		
		switch(position){
		
			case Constants.bottom:
				return new Rectangle(x,
						Constants.board_height - Constants.racquet_height,
						Constants.racquet_width,
						Constants.racquet_height);
			case Constants.right:
				return new Rectangle(Constants.board_width - Constants.racquet_height,
						Constants.board_height - x - Constants.racquet_width,
						Constants.racquet_height,
						Constants.racquet_width);
			case Constants.up:
				return new Rectangle(Constants.board_width - x - Constants.racquet_width,
						0,
						Constants.racquet_width,
						Constants.racquet_height);
			case Constants.left:
				return new Rectangle(0,
						x,
						Constants.racquet_height,
						Constants.racquet_width);
			default:
				return null;
		}
	}	
}
