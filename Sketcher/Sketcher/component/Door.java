package component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Door implements ConstructComponent,MouseListener,MouseMotionListener {
	/**
	 * @invariant Window_List!=null;
	 * @invariant x>0;
	 * @invariant y>0;
	 * @invariant hight>0;
	 * @invariant width>0;
	 * @invariant rotation>=0;
	 */
	public int x,y,hight,width;
	private List<Door> Window_List;
	private boolean flag=false;
	public boolean flag_Button=false;
	private Point2D p;
	public int rotation=0;
	public Door() {
		Window_List=new ArrayList<Door>();
		x=(int)((500*Math.random())+100);
		y=98;
		hight=10;
		width=50;
	}
	@Override
	public void draw(Graphics2D g) {
		
		g.rotate((rotation*Math.PI)/2,x+(width/2), y+(hight/2));
		g.clearRect(x-2,y-5,width+5,hight);
		g.setStroke(new BasicStroke(5));
		//g.setStroke(new BasicStroke(5));
		g.draw(new Line2D.Double(x, y, x+5, y));
		g.draw(new Line2D.Double(x+45, y, x+50, y));
		g.setStroke(new BasicStroke(1));
		g.draw(new Line2D.Double(x+5, y, x+35, y-25));
		g.draw(new Line2D.Double(x+35, y-25, x+45, y));
		g.rotate((-Math.PI*rotation)/2, x+(width/2), y+(hight/2));
		
	}

	
	public void Set_Cor(int x_1, int y_1, int x_2, int y_2) {
		
	}
	public String toString() {return "Door";}
	public ConstructComponent clone() {
		return new Door();
	}
	public void increment() {
		rotation++;
		if(rotation==4)rotation=0;
		
	}
	public void setIncrement(int n) {
		rotation=n;
	}
	public int getIncrement() {
		return rotation;
	}
	@Override
	public void move(int x, int y) {
		this.x+=x;
		this.y+=y;
	}
	public void add(Door t) {
		Window_List.add(t);
	}
	public List<Door> list(){
		return Window_List;
	}
	@Override
	public Rectangle2D getBounds() {
		Rectangle2D r;
		if(rotation==1) {r=new Rectangle2D.Double(x+10,y-30,hight+40,width+25);}
		else if(rotation==2) {r=new Rectangle2D.Double(x-10,y-10,width+20,hight+40);}
		else if(rotation==3) {r=new Rectangle2D.Double(x-10,y-30,hight+40,width+25);}
		else {
			r=new Rectangle2D.Double(x-10,y-30,width+20,hight+40);}
		return r;
	}

	@Override
	public boolean contains(Point2D p) {
		return getBounds().contains(p);
	}
	public void changeStatus(boolean i) {
		flag_Button =i;
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		p=e.getPoint();
			if((contains(p)&&flag_Button) ) {
				flag=true;
			}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {
		if(!contains(p))return;
		if(flag&&flag_Button) {
			Point2D lastPos= p;
			p=e.getPoint();
			int xn =(int) (p.getX()- lastPos.getX());
			int yn = (int)(p.getY()- lastPos.getY());
			move(xn,yn);
		}	
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	public int get_x() {return x;}
	public int get_y() {return y;}
	public void set_x(int x_coordinate) {x=x_coordinate;}
	public void set_y(int y_coordinate) {y=y_coordinate;}
}
