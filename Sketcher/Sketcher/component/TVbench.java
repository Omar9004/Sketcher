package component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

public class TVbench implements Fshape,MouseListener,MouseMotionListener {
	/**
	 * @invariant x>0;
	 * @invariant y>0;
	 * @invariant w>0;
	 * @invariant h>0;
	 * @invariant rotation>0;
	 */
	private double x=300;
	private double y=(Math.random()*500)+1;
	private double w=140;
	private double h=40;
	private boolean flag=false;
	public boolean flag_Button=false;
	public boolean Rotate=false;
	public boolean color_flag=false;
	private Point2D p;
	private int rotation=0;
	@Override
	public void draw(Graphics2D g) {
		if(color_flag) {g.setColor(Color.RED);}
		g.rotate((rotation*Math.PI)/2,x+(w/2), y+(h/2));
		g.clearRect((int)x, (int)y, (int)w, (int)h);
		g.draw(new Rectangle2D.Double(x,y,w,h));
		g.draw(new Rectangle2D.Double(x,y+40,w-90,h-37));
		g.draw(new Rectangle2D.Double(x+90,y+40,w-90,h-37));
		//g.draw(new Rectangle2D.Double(x+10,y+5,w-20,h-30));
		g.draw(new Line2D.Double(x+20,y+20,x+120,y+20));
		g.draw(new Line2D.Double(x+25,y+15,x+115,y+15));
		g.draw(new Line2D.Double(x+20,y+20,x+25,y+15));
		g.draw(new Line2D.Double(x+120,y+20,x+115,y+15));
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


	public boolean contains(Point2D p) {
		 return getBounds().contains(p);
	}

	@Override
	public Fshape clone() {
		
		return new TVbench();
	}
	
	public Rectangle2D getBounds() {
		Rectangle2D r;
		if(rotation==1) {r= new Rectangle2D.Double(x+47,y-50,h+3 ,w );}
		else if(rotation==2) {r= new Rectangle2D.Double(x,y-3,w ,h+3 );}
		else if(rotation==3){r= new Rectangle2D.Double(x+50,y-50,h+3 ,w ); }
		else {
			r=new Rectangle2D.Double(x,y, w, h);
		}
		return r;
	}

	public void changeStatus(boolean i) {
		flag_Button =i;
		
		
	}
	

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
	public String toString() {
		return"Tv_Bench";
	}

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