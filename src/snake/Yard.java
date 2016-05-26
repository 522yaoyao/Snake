package snake;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Yard  extends Frame{
	
   public static final int ROWS = 50;
   public static final int COLS =50;
   public static final int BLOCK_SIZE = 10;
   public boolean gameOver=false;
   public int score=0;
   
   PaintThread paintThread=new PaintThread();
	Snake s=new Snake(this);
	Egg e=new Egg();
	
	Image offScreenImage=null;
	
	//绘制容器;
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
		g.setColor(Color.YELLOW);
		g.setFont(new Font("中华彩云",Font.BOLD,20));
		g.drawString("score="+score, 10, 60);
		g.setColor(c);
		s.draw(g);
		e.draw( g);
		s.eat( e);
		g.setColor(Color.YELLOW);
		if(gameOver){
			g.setFont(new Font("中华彩云",Font.BOLD,50));
			g.drawString("游戏结束", 150, 250);
			paintThread.gameOver();//此时结束线程；
		}
	}
	@Override
	/*解决屏幕文字闪烁的问题，解决缓冲问题*/
	public void update(Graphics g){
		if(offScreenImage==null){
			offScreenImage=this.createImage(COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);
			}
		Graphics goff=offScreenImage.getGraphics();//创建供绘制闭屏图像（off-screen image）使用的图形上下文；
		paint(goff);//绘制容器；
		g.drawImage(offScreenImage, 0, 0, null);//绘制当前可用的指定图像的指定区域，动态地缩放图像使其符合目标绘制表面的指定区域；
	}
	public void stop(){
	//	flag=false;
		gameOver=true;
	}
	private class PaintThread  implements Runnable{
		private boolean running=true;
		public void run(){
			while(running){
//				System.out.println(i++);
				repaint();//重绘此组件；
				try{
//蛇每走一步执行一次此线程；
					Thread.sleep(300);
				}catch(InterruptedException e){
					
				}
			}
		}
		public void gameOver(){
			running=false;
		}
	}
	private class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			s.keyPressed(e);
			
		}
		
	}
	public void setScore(int score){
		this.score=score;
	}
	public int getScore(){
		return  score;
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
		this.addKeyListener(new KeyMonitor());//添加键盘监听；
		new Thread(paintThread).start();
	}
	
	
	public static void main(String args[]){
		new Yard().launch();
		
		
	}

}
