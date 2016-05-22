package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	int row,col;
	int w=Yard.BLOCK_SIZE;
	int h=Yard.BLOCK_SIZE;
	
	private static Random r=new Random();
	
	public Egg(int row,int col){
		this.row=row;
		this.col=col;
	}
	public Egg(){
		//����һ��α�����������ȡ�Դ���������������еġ��� 0����������ָ��ֵ����������֮����ȷֲ��� int ֵ;
		this(r.nextInt(Yard.ROWS-3)+3,r.nextInt(Yard.COLS));
	}
	public void setRow(int row){
		this.row=row;
	}
	public int getRow(){
		return row;
	}
	public void setCol(int col){
		this.col=col;
	}
	public int getCol(){
		return col;
	}
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.GREEN);
		g.fillOval(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);//ʹ�õ�ǰ��ɫ������ָ�����ο����Բ;
		g.setColor(c);
		
	}
//��ȡ����λ�ã�
	public Rectangle getRect(){
		return new Rectangle(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);
	}
//�����Ժ����³��֣�
	public void reAppear(){
		this.row=r.nextInt(Yard.ROWS-3)+3;//����Ḳ��ס�������Ļ������Ǵ���ʹ�3��ʼ�������������������ȡֵ��Χÿ��������������� 
		this.col=r.nextInt(Yard.COLS);
		
	}
}
