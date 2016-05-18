package snake;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
     Node head=null;
     Node tail=null;
     int size=0;
     public Snake(Node node){
    	 head=node;
    	 tail=node;
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
		tail=node;//tailʼ��ָ��β�ڵ㣻
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
		head=node;
	}
	private class Node{
	   private	int w=Yard.BLOCK_SIZE;
	   private 	int h=Yard.BLOCK_SIZE;
	   private int row,col;
		Dir dir=Dir.L;
		Node next=null;
	  private 	Node(int row,int col,Dir dir){
			this.row=row;
			this.col=col;
			this.dir=dir;
			
		}
		//�������ߣ�
		void draw(Graphics g){
			Color c=g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(row*Yard.BLOCK_SIZE, col*Yard.BLOCK_SIZE, w, h);
		}
	}
}
