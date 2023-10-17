package component;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface ConstructComponent extends Cloneable {
	/**This method is responsible to draw shapes component 
	 * @param <Graphics2D> 
	 * @precondition x,y,w,h>0. And they have been already initialised  <p>
	 * @postcondition create a composite shape that is essentially consist of 
	 * many shapes imported from java.awt.geom package.
	 */
	public void draw(java.awt.Graphics2D g);
	/** 
	 * Set_cor method give the user an ability to change x and y coordinate of a shape component manually   
	 * @param x_1 is x coordinate for the start point
	 * @param y_1 is y coordinate for the start point
	 * @param x_2 is x coordinate for the end point
	 * @param y_2 is y coordinate for the end point
	 * @precondition x&y coordinates must be already initialised and they have a Double value
	 * @postcondition the current x,y coordinate aren't equal to the previous x,y coordinate   
	 */
	public void Set_Cor(int x_1,int y_1,int x_2,int y_2);
	/**
	 * This method change positioning coordinate of the shape. 
	 * @param x is the new x coordinate 
	 * @param y is the new y coordinate 
	 * @precondition getBounds() method must be already implemented
	 * @postcondition  the shape coordinate become updated. 
	 */
	public void move(int x,int y);
	/**
	 * Return the shape dimensions. The dimensions can be varied according to "rotation" variable.
	 * @precondition rotation >= 0 and it has already initialised.
	 * @postcondition 
	 * @return four options of returning Rectangle2D according to "rotation" variable's value.  
	 */
	public Rectangle2D getBounds();
	/**
	 * This method will check if the coordinate that has been indicated by mouse clicking (by parameter Point2D p)
	 * is within the range of shape's dimensions.
	 * @param p 
	 * @precondition getBounds() method must be already implemented
	 * @postcondition   
	 * @return true, if the mouse click within the range of shape's dimensions.
	 * Otherwise,false
	 */
	public boolean contains(Point2D p);
	/**
	 * This method return a new instance of the class.
	 * @precondition Classes that implements the interface must have clone method.
	 * @postcondition a new instance of the class is returned.
	 */
	public abstract ConstructComponent clone();
	/**This method will just return the class name 
	 *@precondition
	 *@postcondition 
	 *@return the method name as type of String
	 */
	public String toString();
	/** 
	 * @precondition x coordinate must be already initialised and has a Double value
	 * @postcondition x has a value
	 * @return the current x coordinate of the component in JScrollpane 
	 */
	public int get_x();
	/** 
	 * @precondition y coordinate must be already initialised and has a Double value
	 * @postcondition y has a value
	 * @return the current y coordinate of the component in JScrollpane 
	 * */
	public int get_y();
	/** 
	 * Set_x method give the user an ability to change x coordinate of a shape component manually   
	 * @param x_Coorrdinate the requested x coordinate
	 * @precondition x coordinate must be already initialised and has a Double value
	 * @postcondition the current x coordinate isn't equal to the previous x coordinate   
	 */
	public void set_x(int x_coordinate);
	/** 
	 * Set_y method give the user an ability to change y coordinate of a shape component manually   
	 * @param y_Coorrdinate the requested y coordinate
	 * @precondition y coordinate must be already initialised and has a Double value
	 * @postcondition the current y coordinate isn't equal to the previous y coordinate   
	 */
	public void set_y(int y_coordinate);
	/**
	 * increment() method increments the integer variable of "rotation" from 0->4.
	 * "rotation" variable used  only to multiply its value with Math.PI (in draw method) 
	 * which can help us to rotate the desirable shape around itself.
	 * OBS-calling this method in an inappropriate place inside the code 
	 * can lead to a change  of any shape rotation and position inside the panel.
	 * @precondition "rotation" variable must be initialised before calling this method
	 * @postcondition rotation <=4    
	 *  
	 */
	public void increment();
	/**
	 * @param i is the boolean parameter which its status will be changed.

	 * @precondition variable =false/true;
	 * 
	 * @postcondition variable = false/true;<p>
	 * @return returning the new status of the parameter either false->true or visa versa.*/
	public void changeStatus(boolean i);
	/**
	 * setIncrement(int n) Give the user an ability to change "rotation" variable manually
	 * @param n the desirable value*90 degree  on rotation.
	 * @precondition "rotation" variable must be initialised before calling this method 
	 * @postcondition rotation != to the old rotation's value  
	 */
	public void setIncrement(int n);
	/**
	 * getIncrement() returns the current value of rotation variable
	 * @precondition "rotation" variable must be initialised before calling this method
	 * @postcondition rotation <=4    
	 * @return return value of "rotation"
	 */
	public int getIncrement();
}
