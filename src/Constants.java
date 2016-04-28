import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

//TODO number of lives
//TODO pause for game
//TODO two balls
public class Constants {
	
	public static final Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static final int screen_width = (int)screenSize.getWidth();
	public static final int screen_height = (int)screenSize.getHeight();

	public static ServerSocket serverSocket = null; 
	
	//Settings
	public static final int theme1 = 0, theme2 = 1, theme3 = 2;
	public static final int bottom = 0, left = 3, up = 2, right = 1;
	public static final int on = 0, off = 1;
	
	
	public static ImageIcon waiting = new ImageIcon("ajax-loader.gif");
	public static final int easy = 0, medium = 1, hard = 2;
	
	public static final int maxSingle = 4, maxMulti = 4;
	
	//Themes
	public static final Theme t1 = new Theme(new DrawBoard(){

		@Override
		public void paint(Graphics2D g2d) {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(sx, sy, board_width, board_height);
			g2d.setColor(Color.WHITE);
			//Set top
			g2d.drawLine(sx+40, sy+30, sx+360, sy+30);
			g2d.drawLine(sx+40, sy+40, sx+360, sy+40);
			//Set bottom
			g2d.drawLine(sx+40, sy+360, sx+360, sy+360);
			g2d.drawLine(sx+40, sy+370, sx+360, sy+370);
			//Set left
			g2d.drawLine(sx+40, sy+40, sx+40, sy+360);
			g2d.drawLine(sx+30, sy+40, sx+30, sy+360);
			//Set right
			g2d.drawLine(sx+360, sy+40, sx+360, sy+360);
			g2d.drawLine(sx+370, sy+40, sx+370, sy+360);
			//Corner ovals
			g2d.fillOval(sx+30, sy+30, 10, 10);
			g2d.fillOval(sx+360, sy+30, 10, 10);
			g2d.fillOval(sx+30, sy+360, 10, 10);
			g2d.fillOval(sx+360, sy+360, 10, 10);
			//Center circle
			g2d.fillOval(sx+148, sy+148, 104, 104);
			g2d.setColor(Color.BLACK);
			g2d.fillOval(sx+150, sy+150, 100, 100);
			g2d.setColor(Color.WHITE);
			//Projections
			g2d.drawLine(sx+200, sy+225, sx+200, sy+275);
			g2d.drawLine(sx+225, sy+200, sx+275, sy+200);
			g2d.drawLine(sx+200, sy+175, sx+200, sy+125);
			g2d.drawLine(sx+125, sy+200, sx+175, sy+200);
			//From Corners
			g2d.drawLine(sx+40, sy+40, sx+90, sy+90);
			g2d.drawLine(sx+360, sy+40, sx+310, sy+90);
			g2d.drawLine(sx+40, sy+360, sx+90, sy+310);
			g2d.drawLine(sx+360, sy+360, sx+310, sy+310);
			
		}
		
	}, Color.CYAN, Color.YELLOW, Color.GREEN);

	public static final Theme t2 = new Theme(new DrawBoard(){

		@Override
		public void paint(Graphics2D g2d) {
			g2d.setColor(Color.WHITE);
			g2d.fillRect(sx, sy, board_width, board_height);
			g2d.setColor(Color.BLACK);
			//Set top
			g2d.drawLine(sx+40, sy+30, sx+360, sy+30);
			g2d.drawLine(sx+40, sy+40, sx+360, sy+40);
			//Set bottom
			g2d.drawLine(sx+40, sy+360, sx+360, sy+360);
			g2d.drawLine(sx+40, sy+370, sx+360, sy+370);
			//Set left
			g2d.drawLine(sx+40, sy+40, sx+40, sy+360);
			g2d.drawLine(sx+30, sy+40, sx+30, sy+360);
			//Set right
			g2d.drawLine(sx+360, sy+40, sx+360, sy+360);
			g2d.drawLine(sx+370, sy+40, sx+370, sy+360);
			//Corner ovals
			g2d.fillOval(sx+30, sy+30, 10, 10);
			g2d.fillOval(sx+360, sy+30, 10, 10);
			g2d.fillOval(sx+30, sy+360, 10, 10);
			g2d.fillOval(sx+360, sy+360, 10, 10);
			//Center circle
			g2d.fillOval(sx+148, sy+148, 104, 104);
			g2d.setColor(Color.WHITE);
			g2d.fillOval(sx+150, sy+150, 100, 100);
			g2d.setColor(Color.BLACK);
			//Projections
			g2d.drawLine(sx+200, sy+225, sx+200, sy+275);
			g2d.drawLine(sx+225, sy+200, sx+275, sy+200);
			g2d.drawLine(sx+200, sy+175, sx+200, sy+125);
			g2d.drawLine(sx+125, sy+200, sx+175, sy+200);
			//From Corners
			g2d.drawLine(sx+40, sy+40, sx+90, sy+90);
			g2d.drawLine(sx+360, sy+40, sx+310, sy+90);
			g2d.drawLine(sx+40, sy+360, sx+90, sy+310);
			g2d.drawLine(sx+360, sy+360, sx+310, sy+310);
			
		}
		
	}, Color.BLUE, Color.RED, Color.BLACK);

	public static final Theme t3 = new Theme(new DrawBoard(){

		@Override
		public void paint(Graphics2D g2d) {
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(sx, sy, board_width, board_height);
			g2d.setColor(Color.BLACK);
			//Set top
			g2d.drawLine(sx+40, sy+30, sx+360, sy+30);
			g2d.drawLine(sx+40, sy+40, sx+360, sy+40);
			//Set bottom
			g2d.drawLine(sx+40, sy+360, sx+360, sy+360);
			g2d.drawLine(sx+40, sy+370, sx+360, sy+370);
			//Set left
			g2d.drawLine(sx+40, sy+40, sx+40, sy+360);
			g2d.drawLine(sx+30, sy+40, sx+30, sy+360);
			//Set right
			g2d.drawLine(sx+360, sy+40, sx+360, sy+360);
			g2d.drawLine(sx+370, sy+40, sx+370, sy+360);
			//Corner ovals
			g2d.setColor(Color.RED);
			g2d.fillOval(sx+30, sy+30, 10, 10);
			g2d.fillOval(sx+360, sy+30, 10, 10);
			g2d.fillOval(sx+30, sy+360, 10, 10);
			g2d.fillOval(sx+360, sy+360, 10, 10);
			//Center circle
			g2d.setColor(Color.BLACK);
			g2d.fillOval(sx+148, sy+148, 104, 104);
			g2d.setColor(Color.ORANGE);
			g2d.fillOval(sx+150, sy+150, 100, 100);
			g2d.setColor(Color.BLACK);
			//Projections
			g2d.drawLine(sx+200, sy+225, sx+200, sy+275);
			g2d.drawLine(sx+225, sy+200, sx+275, sy+200);
			g2d.drawLine(sx+200, sy+175, sx+200, sy+125);
			g2d.drawLine(sx+125, sy+200, sx+175, sy+200);
			//From Corners
			g2d.drawLine(sx+40, sy+40, sx+90, sy+90);
			g2d.drawLine(sx+360, sy+40, sx+310, sy+90);
			g2d.drawLine(sx+40, sy+360, sx+90, sy+310);
			g2d.drawLine(sx+360, sy+360, sx+310, sy+310);
			
		}
		
	}, Color.BLACK, Color.BLACK, Color.WHITE);

	
	
	
	public static int paddleSpeed = 2;
	
	public static Theme theme = t1;
	public static int index = Constants.theme1;
	public static int position = bottom;
	public static int sound = on;
	
	public static final int board_width = 400, board_height = 400;
	public static final int sx = screen_width/2-board_width/2, sy = screen_height/2-board_height/2;
	public static final int racquet_width = 100, racquet_height = 15;
	
	public static int reference = Constants.bottom;
	
	public static final int none = 0, wall = 1, paddle = 2, init = 3;
	
	public static int singlePosition = bottom;
	public static int numPlayers = 1;
	public static int level = easy;
	
	/**
	 * Exit from the game
	 */
	public static void gameOver(){
		System.exit(1);
	}
	
	/**
	 * plays music
	 * @param path
	 * @throws IOException
	 */
	public static void music(String path) throws IOException{
		
		if(sound == on){
			URL url = Menu.class.getResource(path);
			AudioClip clip = Applet.newAudioClip(url);
			clip.play();
		}
    }
	
	/**
	 * store settings data
	 */
	public static void store(){
		
		try {
			String content = theme + "\n" + position + "\n" + sound + "\n"; 

			File file = new File("data/store.txt");

			// if file does not exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get value of IP address from remote socket address
	 * @param addra
	 * @return
	 */
	public static String getIP(String addra){
		String[] par = (addra.split(":")[0]).split("/");
		return par[par.length-1];
	}
	
	/**
	 * get value of IP address from InetAddress 
	 * @param addra
	 * @return
	 */
	public static String fromLocal(String addra){
		return addra.split("/")[1];
	}
	
	/**
	 * to be referred next in order
	 * @param position
	 * @return
	 */
	public static int nextRef(int position){
		switch(position){
			case Constants.bottom:
				return Constants.up;
			case Constants.up:
				return Constants.right;
			case Constants.right:
				return Constants.left;
			default:
				return Constants.bottom;
		}
	}
	
	/**
	 * get absolute position given user physical and display addresses
	 * and racket's physical address
	 * @param rel
	 * @return
	 */
	public static int getAbs(int rel){
		
		int rotate = (4 + Constants.position - Constants.reference)%4;
		return (rel + rotate)%4;
	} 
	
	/**
	 * generate random number closer to the middle of the range than farther
	 * @param low
	 * @param high
	 * @return
	 */
	public static int random(int low, int high){
		int mid = (low + high)/2;
		int diff = high - low;
		Random random = new Random();
		int first = 0, second = 0;
		boolean b= false, c = false;
		do{
			first = random.nextInt(diff);
			second = random.nextInt(diff);
			b = Math.abs(first - second) > 3*Math.abs(mid - first - low);
			c = Math.abs(first - second) > 3*Math.abs(mid - second - low);
		}while(!b&&!c);
		
		if(b){
			return low + first;
		}
		
		return low + second;
	}
	
	public static int chooseNeg(int x){
		Random random = new Random();
		int p = random.nextInt(2);
		if(p<1){
			return -x;
		}else{
			return x;
		}
	}
	
	static interface DrawBoard {
		abstract public void paint(Graphics2D g2d);
	}
	
	static class Theme{

		private Color backgroundColor;
		private Color ballColor;
		private Color racquetColor;
		private DrawBoard drawBoard;
		
		Theme(DrawBoard drawBoard, Color boardColor,
				Color ballColor, Color racquetColor){
			this.drawBoard = drawBoard;
			this.backgroundColor = boardColor;
			this.ballColor = ballColor;
			this.racquetColor = racquetColor;
		}
		
		public void paint(Graphics2D g2d){
			drawBoard.paint(g2d);
		}

		public Color getBackGroundColor() {
			return backgroundColor;
		}
		
		public Color getBallColor() {
			return ballColor;
		}

		public Color getRacquetColor() {
			return racquetColor;
		}		
	}
}
