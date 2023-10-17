package component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Window implements ConstructComponent,MouseListener,MouseMotionListener {
	
	private List<Window> Window_List;
	private boolean flag=false;
	public boolean flag_Button=false;
	private Point2D p;
	private int x;
	private int y;
	private int hight;
	private int width;
	public boolean rotate=false;
	public int rotation=0;
	public Window() {
		Window_List=new ArrayList<Window>();
		x=200;
		y=(int) ((Math.random()*98)+20);
		hight=8;
		width=80;
		
	}
	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(0));
		g.rotate((rotation*Math.PI)/2,x+(width/2), y+(hight/2));
		g.clearRect(x,y,width,hight);
			
		g.draw(new Rectangle2D.Double(x,y,width,hight));
		g.draw(new Line2D.Double(x, y+4, x+80, y+4));
		g.draw(new Line2D.Double(x+40, y, x+40, y+8));
		
		g.rotate((-Math.PI*rotation)/2,x+(width/2), y+(hight/2));
		
	}
	public void Set_Cor(int x_1, int y_1, int x_2, int y_2) {
		
	}
	public String toString() {return "Window";}
	public ConstructComponent clone() {
		return new Window();
	}
	
	public void increment() {
		rotation++;
		if(rotation==4)rotation=0;
		
	}
	
	@Override
	public void move(int x, int y) {
		this.x+=x;
		this.y+=y;
	}
	public void add(Window t) {
		Window_List.add(t);
	}
	public List<Window> list(){
		return Window_List;
	}
	public void setList(List<Window> list) {
		Window_List=list;
	}
	@Override
	public Rectangle2D getBounds() {
		Rectangle2D r;
		if(rotation==1) {r=new Rectangle2D.Double(x+15,y-35,hight+30,width);}
		else if(rotation==2) {r=new Rectangle2D.Double(x,y-20,width,hight+40);}
		else if(rotation==3) {r=new Rectangle2D.Double(x+15,y-35,hight+40,width);}
		else {
			r=new Rectangle2D.Double(x,y-20,width,hight+40);}
		return r;
	}

	@Override
	public boolean contains(Point2D p) {
		return getBounds().contains(p);
	}
	public void changeStatus(boolean i) {
		flag_Button =i;
		
	}
	public void setIncrement(int n) {
		rotation=n;
	}
	public int getIncrement() {
		return rotation;
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
