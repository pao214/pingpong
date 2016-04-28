import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Racquet {
	int cx = 0, cy=0, cspeed = 0, px = 0; 
	int position, x;
	boolean isUser = false;
	
	Racquet(int position){
		this.position = position;
		switch(position){
			case Constants.bottom:
				cx = Constants.board_width/2;
				cy = Constants.board_height;
				break;
			case Constants.right:
				cx = Constants.board_width;
				cy = Constants.board_height/2;
				break;
			case Constants.up:
				cx = Constants.board_width/2;
				cy = 0;
				break;
			case Constants.left:
				cx = 0;
				cy = Constants.board_height/2;
				break;
			default:
				break;
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		int y=0;
		
		switch(position){
			case Constants.bottom:
				y = cx - Constants.racquet_width/2;
				break;
			case Constants.left:
				y = cy - Constants.racquet_width/2;
				break;
			case Constants.up:
				y = Constants.board_width - cx - Constants.racquet_width/2;
				break;
			case Constants.right:
				y = Constants.board_height - cy - Constants.racquet_width/2;
				break;
			default:
				break;
		}
	     
		if ((((cspeed>=0 && px<y) || (cspeed<=0 && px>y))&&Math.abs(px-y)<5)||isUser ){
			x=y;
		}else{
			x=x+cspeed;
		}
		
		px=x;
		
		
		
		
		if(x > Constants.board_width - Constants.racquet_width){
			x = Constants.board_width - Constants.racquet_width;
		}
		
		if(x < 0){
			x = 0;
		}
		
		switch(Constants.getAbs(position)){
		
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
		
		int position = Constants.bottom;
		int x = cx - Constants.racquet_width/2;
		if(cx == 0){
			position = Constants.left;
			x = cy - Constants.racquet_width/2;
		}else if(cy==0){
			position = Constants.up;
			x = Constants.board_width - cx - Constants.racquet_width/2;
		}else if(cx > Constants.board_width - 10){
			position = Constants.right;
			x = Constants.board_height - cy - Constants.racquet_width/2;
		}
		
		switch(position){
		
			case Constants.bottom:
				return new Rectangle(Constants.sx + x,
						Constants.sy + Constants.board_height - Constants.racquet_height,
						Constants.racquet_width,
						Constants.racquet_height);
			case Constants.right:
				return new Rectangle(Constants.sx + Constants.board_width - Constants.racquet_height,
						Constants.sy + Constants.board_height - x - Constants.racquet_width,
						Constants.racquet_height,
						Constants.racquet_width);
			case Constants.up:
				return new Rectangle(Constants.sx + Constants.board_width - x - Constants.racquet_width,
						Constants.sy + 0,
						Constants.racquet_width,
						Constants.racquet_height);
			case Constants.left:
				return new Rectangle(Constants.sx + 0,
						Constants.sy + x,
						Constants.racquet_height,
						Constants.racquet_width);
			default:
				return null;
		}
	}
}
