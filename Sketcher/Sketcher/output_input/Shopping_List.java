package output_input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import component.Bed;
import component.ConstructComponent;
import component.Desk;
import component.DiningTable;
import component.DoubleSofa;
import component.Dresser;
import component.Fshape;
import component.KitchenCounter;
import component.Shower;
import component.SingelBed;
import component.SmallDresser;
import component.TVbench;
import component.Table;
import component.ToiletChair;
import component.TripleSofa;
import component.Wall;
import component.Washbasin;
import component.Window;
import Application.UpdateObserver;

public class Shopping_List implements UpdateObserver {
	/**JFrame to display the shopping list. */
	JFrame frame;
	/**JTextArea to write the data to.*/
	private JTextArea text;
	/**JScrollPane to add text to it.*/
	JScrollPane scroll;
	private JPanel panel;
	/**Array of element of type Fshape.*/
	private Fshape[] comp = { new Dresser(), new DiningTable(), new Table(), new DoubleSofa(), new TripleSofa(), new Bed(),
			new SingelBed(), new SmallDresser() ,new Shower(),new ToiletChair(), new Washbasin(),new TVbench(), new KitchenCounter()
			,new Desk()};
	/**Array to hold the Quantity of the Fshape elements.*/
	private int Quantity[];
	/**initialise and add the component to shopping list frame.
	 * @precondition comp!=null<p>comp size > 0 
	 * @postcondition The user have ability to display the Shopping list*/
	public Shopping_List() {
		panel=new JPanel();
		Quantity=new int[comp.length];
		Arrays.fill(Quantity, 0);
		frame=new JFrame("Invoice ");
		text=new JTextArea();
		text.setFont(new Font("Font", Font.BOLD, 30));
		text.setEditable(false);
		scroll=new JScrollPane(text);
		frame.add(scroll);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setSize(400,400);
		text.setBackground(Color.LIGHT_GRAY);
		frame.setUndecorated(true);
		
	}
	/*@Override
	public void updateInfo(int n,String s) {
		//text.append(s+" "+Integer.toString(n)+"\n");
		getIndex(s);
		text.setText("");
		for(int i=0;i<Quantity.length;i++) {
			//if(Quantity[i]!=0) {text.append(comp[i].toString()+" "+ Quantity[i]+"\n");}
		}
	}*/
	/**Used to manage visibility of the shopping list frame.
	 * @param status is the boolean value used to manage visibility of the shopping
	 * list frame.
	 * @precondition frame!=null<p> status is true or false
	 * @postcondition the shopping list frame changing visibility according to the 
	 * value of status.*/
	public void setvisibil(boolean status) {
		frame.setVisible(status);
	}
	/**@precondition list!=null<p>wall_List!=null<p>list_Con
	 * @postcondition The in list will be printed on the shopping list frame.
	 *  */
	@Override
	public void updateCoordinate(ArrayList<Fshape> list,List<Wall> wall,List<ConstructComponent> co) {
		text.selectAll();
		text.replaceSelection("");
		Quantity=new int[comp.length];
		for(int j=0;j<list.size();j++) {
			getIndex(list.get(j).toString());
		}
		for(int i=0;i<Quantity.length;i++) {
			if(Quantity[i]!=0) {text.append(comp[i].toString()+" "+ Quantity[i]+"\n");}
		}
		
	}
	/**Update the quantity of each element of type Fshape.
	 * @param s is the string that refer to the element in comp
	 * @precondition comp!=null<p>Quantity!=null<p>s!=null
	 * @postcondition The quantity of Fshape elements will be updated.  */
	private void getIndex(String s) {
		
		for(int i=0;i<comp.length;i++) {
			if(s.equals(comp[i].toString())) {
				Quantity[i]++;
			}
		}
		
	}

}
