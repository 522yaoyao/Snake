package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {
	
     Node head=null;
     Node tail=null;
     int size=0;
     Yard y=null;
     Node n=new Node(10,10,Dir.D);
     public Snake(Yard y){
    	 head=n;
    	 tail=n;
    	 size=1;
    	 this.y=y;
     }
	public void addToTail(){
		Node node=null;
		switch(tail.dir){
		case L:
			node=new Node(tail.row,tail.col+1,tail.dir);
			break;
		case R:
			node=new Node(tail.row,tail.col-1,tail.dir);
			break;
		case U:
			node=new Node(tail.row+1,tail.col,tail.dir);
			break;
		case D:	
			node=new Node(tail.row-1,tail.col,tail.dir);
			break;
			 }
		tail.next=node;//使tail的next属性指向下一个节点；
		node.prev=tail;
		tail=node;//tail始终指向尾节点；
		size++;
	}
	public void  addToHead(){
		Node node=null;
		switch(head.dir){
		case L:
			node=new Node(head.row,head.col-1,head.dir);
			break;
		case R:
			node=new Node(head.row,head.col+1,head.dir);
			break;
		case U:
			node=new Node(head.row-1,head.col,head.dir);
			break;
		case D:	
			node=new Node(head.row+1,head.col,head.dir);
			break;
			 }
		node.next=head;
		head.prev=node;
		head=node;
		size++;
	}
	public void draw(Graphics g){
		if(size<=0)
			return;
		Node n=head;
		//move();
		//遍历这条蛇的每一个节点，并依此画出；
	while(n!=null){
			n.draw(g);//内部类的draw()方法；
			n=n.next;
			
		}
	/*	for(Node n = head; n != null; n = n.next) {
			n.draw(g);
		}*/
	move();
	//(在一个方法中调用同一个类的方法)使蛇开始移动；
	}
 public  void move(){
	    addToHead();
		deleteFromTail();
		cherkDead();
	}
 public void cherkDead(){
	 //撞到墙停止移动；
	 if(head.row<3||head.row>Yard.ROWS||head.col<0||head.col>Yard.COLS)
		 y.stop();
	 //头部吃到自己停止移动
	for(Node n = head.next; n != null; n = n.next) {
			if(n.row==head.row&&n.col==head.col)
				y.stop();
	}
 }
  public void deleteFromTail(){
	  if(size<=0)return;
/*
	  tail=null;//使tail的前一个节点指向null;
	  tail.prev=null;//使其不指向前驱节点；
	  */
	  tail=tail.prev;
	  tail.next=null;
	  
  }
public void eat(Egg e){
	//计算此 Rectangle 与指定 Rectangle 的交集;
	if(this .getRect().intersects(e.getRect())){
		e.reAppear();
	    addToHead();
	}
		
}
//获取头节点的位置；
public Rectangle getRect(){
	return new Rectangle(head.col*Yard.BLOCK_SIZE, head.row*Yard.BLOCK_SIZE, head.w, head.h);
}
	private class Node{
	   private	int w=Yard.BLOCK_SIZE;
	   private 	int h=Yard.BLOCK_SIZE;
	   private int row,col;
		Dir dir=Dir.L;
		Node next=null;
		Node prev=null;//设置一个前驱节点；
	  private 	Node(int row,int col,Dir dir){
			this.row=row;
			this.col=col;
			this.dir=dir;
			
		}
		//画这条蛇的每一个点；
		void draw(Graphics g){
			Color c=g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);
			g.setColor(c);
		}
	
	}
	public void keyPressed(KeyEvent e){
		  int  key=e.getKeyCode();//返回与此事件中的键关联的整数 keyCode;
			switch(key){
			case KeyEvent.VK_LEFT:
				if(head.dir!=Dir.R)
					 head.dir=Dir.L;
				break;
			case KeyEvent.VK_UP:
				if(head.dir!=Dir.D)
					head.dir=Dir.U;
				break;
			case KeyEvent.VK_RIGHT:
				if(head.dir!=Dir.L)
				    head.dir=Dir.R;
				break;
			case KeyEvent.VK_DOWN:
				if(head.dir!=Dir.U)
				   head.dir=Dir.D;
				break;
			}
			
		}
}
