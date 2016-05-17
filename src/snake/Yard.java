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
	}
	
	
	public static void main(String args[]){
		new Yard().launch();
		
	}

}
