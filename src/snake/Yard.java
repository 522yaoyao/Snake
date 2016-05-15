package snake;

import java.awt.Frame;

public class Yard  extends Frame{
	
	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	
	public void launch() {
		this.setLocation(200, 200);
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		this.setVisible(true);
	}
	
	
	public static void main(String args[]){
		new Yard().launch();
		
	}

}
