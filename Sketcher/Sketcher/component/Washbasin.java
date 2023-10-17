package component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Washbasin implements Fshape,MouseListener,MouseMotionListener {
	/**
	 * @invariant x>0;
	 * @invariant y>0;
	 * @invariant w>0;
	 * @invariant h>0;
	 * @invariant rotation>0;
	 */
	private double x=1000;
	private double y=(Math.random()*500)+1;
	private double w=100;
	private double h=50;
	private boolean flag=false;
	public boolean flag_Button=false;
	private Point2D p;
	public boolean Rotate=false;
	private int rotation=0;
	public boolean color_flag=false;
	@Override
	public void draw(Graphics2D g) {
		if(color_flag) {g.setColor(Color.RED);}
		g.rotate((rotation*Math.PI)/2,x+(w/2), y+(h/2));
		g.clearRect((int)x, (int)y, (int)w+35, (int)h);
		g.draw(new Rectangle2D.Double(x,y,w,h));
		g.draw(new Rectangle2D.Double(x,y,w-65,h));
		g.draw(new Rectangle2D.Double(x+100,y,w-65,h));
		g.draw(new Rectangle2D.Double(x+42,y+10,w-50,h-15));
		Shape r = new Rectangle2D.Double(x+64,y+3,w-95,h-35);
		g.draw(r);
		g.fill(r);
		if(color_flag) {
			g.drawString("Wrong prosition", (int)(x+10),(int) (y+(h/2)));
			color_flag=false;}
			g.rotate((-Math.PI*rotation)/2,x+(w/2), y+(h/2));
			g.setColor(Color.BLACK);
		
	}
	@Override
	public void move(double X, double Y) {
		x+=X;
		y+=Y;

	}

	@Override
	public boolean contains(Point2D p) {
		 return getBounds().contains(p);
	}
	@Override
	public Fshape clone() {
		return new Washbasin();
	}
	public String toString() {
		return"Wash_basin";
	}

	@Override
	public Rectangle2D getBounds() {
		Rectangle2D r;
		if(rotation==1) {r= new Rectangle2D.Double(x+25,y-25,h ,w+35 );}
		else if(rotation==2) {r= new Rectangle2D.Double(x-35,y,w+35 ,h );}
		else if(rotation==3){r= new Rectangle2D.Double(x+25,y-60,h,w+35 ); }
		else {
			r=new Rectangle2D.Double(x,y, w+35, h);
		}
		return r;
	}
	@Override
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
	public void mouseClicked(MouseEvent e) {}
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

	public double get_x() {return x;}
	public double get_y() {return y;}
	public void set_x(int x_Coorrdinate) {x=x_Coorrdinate;}
	public void set_y(int y_Coorrdinate) {y=y_Coorrdinate;}

	public void updateColor(boolean b) {
		color_flag=b;
		
	}
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