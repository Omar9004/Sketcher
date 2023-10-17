package component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ToiletChair implements Fshape,MouseListener,MouseMotionListener  {
	/**
	 * @invariant x>0;
	 * @invariant y>0;
	 * @invariant w>0;
	 * @invariant h>0;
	 * @invariant rotation>0;
	 */
	private double x=1200;
	private double y=(Math.random()*370)+1;
	private double w=12;
	private double h=20;
	private boolean flag=false;
	public boolean flag_Button=false;
	private Point2D p;
	public boolean Rotate=false;
	public boolean color_flag=false;
	int rotation=0;
	@Override
	public void draw(Graphics2D g) {
		if(color_flag) {g.setColor(Color.RED);}
		g.rotate(rotation*Math.PI/2,x+(w/2), y+(h/2));
		g.drawOval((int)(x+5), (int)(y), 30, 20);
		g.clearRect((int)x, (int)y, (int)w, (int)h);
		g.draw(new Rectangle2D.Double(x,y,w,h));
		g.fillOval((int)(x+3), (int)(y+7), 5, 5);
		if(color_flag) {
			g.drawString("Wrong prosition", (int)(x+10),(int) (y+(h/2)));
			color_flag=false;}
		g.rotate(rotation*-Math.PI/2, x+(w/2), y+(h/2));	
		g.setColor(Color.BLACK);
	}
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
			return new ToiletChair();	
	}
	@Override
	public Rectangle2D getBounds() {
		Rectangle2D r;
	    if(rotation==1) {
	    	r= new Rectangle2D.Double(x-10, y,h+10,w+30 );
	    	}
		else if(rotation==2) {
		r= new Rectangle2D.Double(x-25, y-5,h+20,w+20 );}
		else if(rotation==3){
		r= new Rectangle2D.Double(x-10, y-20,h+10,w+25 );}
		else {
			r=new Rectangle2D.Double(x, y-5,h+20,w+20 );
		}
		return r;
	}
	@Override
	public void changeStatus(boolean i) {
		flag_Button =i;
	}
	public double get_x() {return x;}
	public double get_y() {return y;}
	public void set_x(int x_Coorrdinate) {x=x_Coorrdinate;}
	public void set_y(int y_Coorrdinate) {y=y_Coorrdinate;}
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
	public void increment() {
		if(rotation==4)rotation=0;
		rotation++;
	}
	public String toString() {
		return"ToiletChair";
	}
	@Override
	public void updateColor(boolean b) {
		color_flag=b;
	}
	@Override
	public int getIncrement() {
		return rotation;
	}
	@Override
	public void setIncrement(int inc) {
		rotation=inc;
	}

}
