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
	
	//��������;
	@Override
	public void paint(Graphics g){
		Color c=g.getColor();// ��ȡ��ͼ�������ĵĵ�ǰ��ɫ;
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.WHITE);
		//�����ߣ�
		for(int i=1;i<ROWS;i++){
			g.drawLine(0, i*BLOCK_SIZE, COLS * BLOCK_SIZE, i*BLOCK_SIZE );
		}
	
		//�����ߣ�
		for(int i=1;i<ROWS;i++){
			g.drawLine( i*BLOCK_SIZE, 0, i*BLOCK_SIZE,BLOCK_SIZE*ROWS );
		}
		g.setColor(Color.YELLOW);
		g.setFont(new Font("�л�����",Font.BOLD,20));
		g.drawString("score="+score, 10, 60);
		g.setColor(c);
		s.draw(g);
		e.draw( g);
		s.eat( e);
		g.setColor(Color.YELLOW);
		if(gameOver){
			g.setFont(new Font("�л�����",Font.BOLD,50));
			g.drawString("��Ϸ����", 150, 250);
			paintThread.gameOver();//��ʱ�����̣߳�
		}
	}
	@Override
	/*�����Ļ������˸�����⣬�����������*/
	public void update(Graphics g){
		if(offScreenImage==null){
			offScreenImage=this.createImage(COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);
			}
		Graphics goff=offScreenImage.getGraphics();//���������Ʊ���ͼ��off-screen image��ʹ�õ�ͼ�������ģ�
		paint(goff);//����������
		g.drawImage(offScreenImage, 0, 0, null);//���Ƶ�ǰ���õ�ָ��ͼ���ָ�����򣬶�̬������ͼ��ʹ�����Ŀ����Ʊ����ָ������
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
				repaint();//�ػ�������
				try{
//��ÿ��һ��ִ��һ�δ��̣߳�
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
		this.setLocation(200, 200);//������Ƶ���λ�á�ͨ���������������ռ��е� x �� y ������ָ����λ�õ����Ͻǣ�
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);// ��������Ĵ�С��ʹ����Ϊ width���߶�Ϊ height��
		this.setVisible(true);// ���ݲ��� b ��ֵ��ʾ�����ش� Window��
		this.addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e){
			System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());//��Ӽ��̼�����
		new Thread(paintThread).start();
	}
	
	
	public static void main(String args[]){
		new Yard().launch();
		
		
	}

}
