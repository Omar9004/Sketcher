package component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Wall {
	/**
	 * @invariant wall_List!=null;
	 * @invariant x_1>=0;
	 * @invariant y_1>=0;
	 * @invariant x_2>=0;
	 * @invariant y_2>=0;
	 */
	private List<Wall> wall_List;
	
	public int x_1,y_1,x_2,y_2;
	public Wall() {
		wall_List=new ArrayList<Wall>();
		x_1=0;
		y_1=0;
		x_2=0;
		y_2=0;
	}
	/**This method is responsible to draw shapes component 
	 * @param <Graphics2D> 
	 * @precondition x_1,y_1,x_2,y_2>=0. And they have been already initialised  <p>
	 * @postcondition create a composite shape that is essentially consist of 
	 * many shapes imported from java.awt.geom package.
	 */
	public void draw(java.awt.Graphics2D g) {
		int dx=x_2-x_1;
		int dy=y_2-y_1;
		g.setStroke(new BasicStroke(10));
		if(Math.abs(dx)>Math.abs(dy)&&dx>0&&(Math.cos(dx)>-0.75||(Math.cos(dx)<0.25)))
			y_2=y_1;
		else if(Math.abs(dx)>Math.abs(dy)&&dx<0&&(Math.cos(dx)>-0.75||(Math.cos(dx)<0.25)))
			y_1=y_2;
		if(Math.abs(dy)>Math.abs(dx)&&dy>0&&(Math.sin(dx)>-0.75||Math.sin(dx)<0.25))
			x_2=x_1;
		else if(Math.abs(dy)>Math.abs(dx)&&dy<0&&(Math.sin(dx)>-0.75||Math.sin(dx)<0.25))
			x_1=x_2;
		g.draw(new Line2D.Double(x_1,y_1,x_2,y_2));
		g.setStroke(new BasicStroke(0));
		
	
	}
	/**This method will just return the class name 
	 *@precondition
	 *@postcondition 
	 *@return the method name as type of String
	 */
	public String toString() {return "Wall";}
	/** 
	 * Set_cor method give the user an ability to change x and y coordinate of a shape component manually   
	 * @param x_1 is x coordinate for the start point
	 * @param y_1 is y coordinate for the start point
	 * @param x_2 is x coordinate for the end point
	 * @param y_2 is y coordinate for the end point
	 * @precondition x&y coordinates must be already initialised and they have a Double value
	 * @postcondition the current x,y coordinate aren't equal to the previous x,y coordinate   
	 */
	public void Set_Cor(int x_1,int y_1,int x_2,int y_2) {
		this.x_1=x_1;
		this.x_2=x_2;
		this.y_1=y_1;
		this.y_2=y_2;
		
	}
	/**Method find the wall in the list of wall, that its getBounds() contains p.
	 * @param p is the Point2D to be checked.
	 * @return the index of the wall in list or -1 if no wall contain p.
	 * @precondition p is valid Point2D<p> wall_List!=null<p>wall_List size > 0
	 * @postcondition the index of the wall in wall_List*/
	public int findWall(Point2D p) {
		int index=-1;
		if(list()!=null&&list().size()>0) {
			for(int i=0;i<list().size();i++) {
				if(list().get(i).contains(p)) {
					index=i;
				}
			}
		}
		return index;
	}
	/**
	 * To add the painted walls in the List of walls
	 * @param t is the wall to be added to wall_List  
	 * @precondition wall_List!=null;
	 * @postcondition the new wall will be added to the wall_List. 
	 */
	public void add(Wall t) {
		wall_List.add(t);
	}
	/**
	 * Returning the wall_List List.
	 * @precondition wall_List!=null;
	 * @postcondition Return list of walls;
	 */
	public List<Wall> list(){
		return wall_List;
	}
	/**
	 * Update the wall_List with List.
	 * @precondition wall_List!=null;
	 * @postcondition wall_List is updated with list.
	 */
	public void setList(List<Wall> list) {
		wall_List=list;
	}
	/**
	 * This method change positioning coordinate of the shape. 
	 * @param x is the new x coordinate 
	 * @param y is the new y coordinate 
	 * @precondition getBounds() method must be already implemented
	 * @postcondition  the shape coordinate become updated. 
	 */
	public void move(int x,int y) {
		this.x_2=x;
		this.y_2=y;
	}
	/**
	 * Return the shape dimensions. The dimensions can be varied according to "rotation" variable.
	 * @precondition rotation >= 0 and it has already initialised.
	 * @postcondition 
	 * @return four options of returning Rectangle2D according to "rotation" variable's value.  
	 */
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x_1-2,y_1-2,(x_2-x_1)+10,(y_2-y_1)+10);
	}
	/**
	 * This method will check if the coordinate that has been indicated by mouse clicking (by parameter Point2D p)
	 * is within the range of shape's dimensions.
	 * @param p 
	 * @precondition getBounds() method must be already implemented
	 * @postcondition   
	 * @return true, if the mouse click within the range of shape's dimensions.
	 * Otherwise,false
	 */
	public boolean contains(Point2D p) {
		return getBounds().contains(p);
	}
	 
}
