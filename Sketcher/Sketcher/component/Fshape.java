package component;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public interface Fshape extends Cloneable  {
	/**
	 * The method is customised to change the boolean flag "color_flag".
	 * @param b is the boolean value to be assigned to "color_flag"
	 * @precondition color_flag =false/true; and it has been properly initialised.
	 * @postcondition color_flag =false->true or visa versa;
	 * */
	public void updateColor(boolean b);
	/**This method is responsible to draw shapes component 
	 * @param <Graphics2D> 
	 * @precondition x,y,w and h>0. And they have been already initialised  <p>
	 * @postcondition create a composite shape that is essentially consist of 
	 * many shapes imported from java.awt.geom package.
	 */
	public void draw(Graphics2D g);
	/**
	 * This method is responsible to move a shape by adding  
	 * the new coordinate(X,Y) that has been provided in method's argument.
	 * @param X is the new x coordinate
	 * @param Y is the new y coordinate   
	 * @precondition x & y coordinate must be already initialised and has a Double value.
	 * @postcondition x & y will obtain the a new coordinate. 
	 */
	public void move(double X, double Y);
	/**
	 * This method will check if the coordinate that has been indicated by mouse clicking (by parameter Point2D p)
	 * is within the range of shape's dimensions.
	 * @param p is the Point2D to be checked with getBounds() of the shape.
	 * @precondition getBounds() method must be already implemented
	 * @postcondition  
	 * @return true, if the given coordinates by mouse click  are within the range of shape's dimensions.
	 * Otherwise,false
	 */
	public boolean contains(Point2D p);
	/**This method will just return the class name 
	 *@precondition
	 *@postcondition 
	 *@return the method name as type of String
	 */
	public String toString();
	/**This method will generate a new copy of shape once the user call it.
	 * Additionally it is very beneficial 
	 * when it comes to draw the same shape more than one time.  
	 *@precondition "Cloneable" interface must be implemented before before implementing this method 
	 *@postcondition The shape's instance will return to its default values
	 *@return Copy of  the same class
	 */
	public abstract Fshape clone();
	/**
	 * Return the shape dimensions. The dimensions can be varied according to "rotation" variable.
	 * @precondition rotation >= 0 and it has already initialised.
	 * @postcondition 
	 * @return four options of returning Rectangle2D according to "rotation" variable's value.  
	 */
	public Rectangle2D getBounds();
	/**
	 * @param i is the boolean parameter which its status will be changed.
	 * @precondition variable =false/true;
	 * @postcondition variable = false/true;<p>
	 * @return returning the new status of the parameter either false->true or visa versa.
	 */
	public void changeStatus(boolean i);
	/** 
	 * @precondition x coordinate must be already initialised and has a Double value
	 * @postcondition x has a value
	 * @return the current x coordinate of the component 
	 */
	public double get_x();
	/** 
	 * @precondition y coordinate must be already initialised and has a Double value
	 * @postcondition y has a value
	 * @return the current y coordinate of the component
	 * */
	public double get_y();
	/** 
	 * Set_x method give the user an ability to change x coordinate of a shape component manually   
	 * @param x_Coorrdinate the requested x coordinate
	 * @precondition x coordinate must be already initialised and has a Double value
	 * @postcondition the current x coordinate isn't equal to the previous x coordinate   
	 */
	public void set_x(int x_Coorrdinate);
	/** 
	 * Set_y method give the user an ability to change x coordinate of a shape component manually   
	 * @param y_Coorrdinate the requested x coordinate
	 * @precondition y coordinate must be already initialised and has a Double value
	 * @postcondition the current y coordinate isn't equal to the previous y coordinate   
	 */
	public void set_y(int y_Coorrdinate);
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
	 * getIncrement() returns the current value of rotation variable
	 * @precondition "rotation" variable must be initialised before calling this method
	 * @postcondition rotation <=4    
	 * @return return value of "rotation"
	 */
	public int getIncrement();
	/**
	 * setIncrement(int inc) Give the user an ability to change "rotation" variable manually
	 * @param inc the desirable value on rotation.
	 * @precondition "rotation" variable must be initialised before calling this method 
	 * @postcondition rotation != to the old rotation's value  
	 */
	public void setIncrement(int inc);
}