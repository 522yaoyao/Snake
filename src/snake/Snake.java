package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	
     Node head=null;
     Node tail=null;
     int size=0;
     Node n=new Node(10,10,Dir.D);
     public Snake(){
    	 head=n;
    	 tail=n;
    	 size=1;
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
		tail.next=node;//ʹtail��next����ָ����һ���ڵ㣻
		node.prev=tail;
		tail=node;//tailʼ��ָ��β�ڵ㣻
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
		//���������ߵ�ÿһ���ڵ㣬�����˻�����
	while(n!=null){
			n.draw(g);//�ڲ����draw()������
			n=n.next;
			
		}
	/*	for(Node n = head; n != null; n = n.next) {
			n.draw(g);
		}*/
	move();//(��һ�������е���ͬһ����ķ���)ʹ�߿�ʼ�ƶ���
	}
 public  void move(){
	    addToHead();
		deleteFromTail();
	}
  public void deleteFromTail(){
	  if(size<=0)return;
/*
	  tail=null;//ʹtail��ǰһ���ڵ�ָ��null;
	  tail.prev=null;//ʹ�䲻ָ��ǰ���ڵ㣻
	  */
	  tail=tail.prev;
	  tail.next=null;
	  
  }
	
	private class Node{
	   private	int w=Yard.BLOCK_SIZE;
	   private 	int h=Yard.BLOCK_SIZE;
	   private int row,col;
		Dir dir=Dir.L;
		Node next=null;
		Node prev=null;//����һ��ǰ���ڵ㣻
	  private 	Node(int row,int col,Dir dir){
			this.row=row;
			this.col=col;
			this.dir=dir;
			
		}
		//�������ߵ�ÿһ���㣻
		void draw(Graphics g){
			Color c=g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);
			g.setColor(c);
		}
	
	}
	public void keyPressed(KeyEvent e){
		  int  key=e.getKeyCode();//��������¼��еļ����������� keyCode;
			switch(key){
			case KeyEvent.VK_LEFT:
				head.dir=Dir.L;
				break;
			case KeyEvent.VK_UP:
				head.dir=Dir.U;
				break;
			case KeyEvent.VK_RIGHT:
				head.dir=Dir.R;
				break;
			case KeyEvent.VK_DOWN:
				head.dir=Dir.D;
				break;
			}
			
		}
}
