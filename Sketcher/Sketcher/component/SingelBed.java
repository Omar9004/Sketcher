package component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class SingelBed implements Fshape,MouseListener,MouseMotionListener {
	/**
	 * @invariant x>0;
	 * @invariant y>0;
	 * @invariant w>0;
	 * @invariant h>0;
	 * @invariant rotation>0;
	 */
	private double x=200;
	private double y=(Math.random()*150)+1;
	private double w=75;
	private double h=140;
	private boolean flag=false;
	private Point2D p;
	public boolean flag_Button=false;
	public boolean color_flag=false;
	private int rotation=0;
	public void updateColor(boolean b) {
		color_flag=b;
		
	}
	@Override
	public void draw(Graphics2D g) {
		if(color_flag) {g.setColor(Color.RED);}
		g.rotate((rotation*Math.PI)/2,x+(w/2), y+(h/2));
		g.clearRect((int)x ,(int)y ,(int) w, (int)h);
		g.draw(new Rectangle2D.Double(x,y,w,h));
		g.draw(new Line2D.Double(x,y+40,x+55, y+40));
		g.draw(new Rectangle2D.Double(x+5,y+5,w-10,h-110));
		g.draw(new Line2D.Double(x+55,y+40,x+75, y+60));
		g.draw(new Line2D.Double(x+55,y+60,x+75, y+60));
		g.draw(new Line2D.Double(x+55,y+60,x+55, y+40));
		if(color_flag) {
			g.drawString("Wrong prosition", (int)(x+10),(int) (y+(h/2)));
			color_flag=false;}
		g.rotate((-Math.PI*rotation)/2,x+(w/2), y+(h/2));
		g.setColor(Color.BLACK);
		
	}
	@Override
	public void move(double X, double Y) {
		x +=X;
		y +=Y;
	}
	@Override
	public boolean contains(Point2D p) {
		 return getBounds().contains(p);
	}
	public Fshape clone() {return new SingelBed();}
	public String toString() {
		return "SingelBed";
	}
	public Rectangle2D getBounds() {
		Rectangle2D r;
		 if(rotation==1) {r= new Rectangle2D.Double(x-33,y+33,h,w );}
		else if(rotation==2) {r= new Rectangle2D.Double(x,y,w,h );}
		else if(rotation==3){r= new Rectangle2D.Double(x-33,y+33,h,w ); }
		else {
			r=new Rectangle2D.Double(x,y, w, h);
		}
		return r;
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		p=e.getPoint();
		if(contains(p)&&flag_Button) {
			flag=true;}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	public void changeStatus(boolean i) {
		flag_Button =i;	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(!contains(p))return;
		if(flag&&flag_Button) {
			Point2D lastPos= p;
			p=e.getPoint();
			double xn = p.getX()- lastPos.getX();
			double yn = p.getY()- lastPos.getY();
			move(xn,yn);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public double get_x() {return x;}
	@Override
	public double get_y() {return y;}
	@Override
	public void set_x(int x_Coorrdinate) {x=x_Coorrdinate;}
	@Override
	public void set_y(int y_Coorrdinate) {y=y_Coorrdinate;}
	public void increment() {
		rotation++;
		if(rotation==4)rotation=0;
	}
	public int getIncrement() {
		return rotation;
	}
	public void setIncrement(int inc) {
		rotation=inc;
	}
}