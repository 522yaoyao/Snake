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
		//返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值;
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
		g.fillOval(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);//使用当前颜色填充外接指定矩形框的椭圆;
		g.setColor(c);
		
	}
//获取蛋的位置；
	public Rectangle getRect(){
		return new Rectangle(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);
	}
//蛋被吃后重新出现；
	public void reAppear(){
		this.row=r.nextInt(Yard.ROWS-3)+3;//上面会覆盖住，这样的话行数是从最低从3开始（随机数减少了三个，取值范围每个随机数加三）； 
		this.col=r.nextInt(Yard.COLS);
		
	}
}
