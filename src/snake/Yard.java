package snake;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Yard  extends Frame{
	
   public static final int ROWS = 50;
	public static final int COLS =50;
	public static final int BLOCK_SIZE = 10;
	
	@Override
	public void paint(Graphics g){
		Color c=g.getColor();// 获取此图形上下文的当前颜色;
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.WHITE);
		//画横线；
		for(int i=1;i<ROWS;i++){
			g.drawLine(0, i*BLOCK_SIZE, COLS * BLOCK_SIZE, i*BLOCK_SIZE );
		}
	
		//画竖线；
		for(int i=1;i<ROWS;i++){
			g.drawLine( i*BLOCK_SIZE, 0, i*BLOCK_SIZE,BLOCK_SIZE*ROWS );
		}
	}
	
	public void launch() {
		this.setLocation(200, 200);//将组件移到新位置。通过此组件父级坐标空间中的 x 和 y 参数来指定新位置的左上角；
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);// 调整组件的大小，使其宽度为 width，高度为 height；
		this.setVisible(true);// 根据参数 b 的值显示或隐藏此 Window；
		this.addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e){
			System.exit(0);
			}
		});
	}
	
	
	public static void main(String args[]){
		new Yard().launch();
		
	}

}
