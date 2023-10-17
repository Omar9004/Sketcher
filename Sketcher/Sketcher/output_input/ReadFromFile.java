package output_input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import component.Bed;
import component.ConstructComponent;
import component.Desk;
import component.DiningTable;
import component.Door;
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

public class ReadFromFile {
	/**@invariant Data will be extracted from the chosen file and add to the lists.
	 * @invariant toDraw.size()>0 && wall_List.size()>0 && list_con.size()>0
	 * */
	/**Array of elements of type Fshape.*/
	Fshape[] comp = { new Dresser(), new DiningTable(), new Table(), new DoubleSofa(), new TripleSofa(), new Bed(),
			new SingelBed(), new SmallDresser(),new Shower(),new ToiletChair(), new Washbasin(),new TVbench(), new KitchenCounter(),
			new Desk()};
	/**ArrayList that hold elements of type Fshape.*/
	private ArrayList<Fshape> toDraw=new ArrayList<Fshape>();
	/**List that hold elements of type Wall.*/
	private List<Wall> wall_List=new ArrayList<Wall>();
	/**Array of elements of type ConstructComponent.*/
	private ConstructComponent[] con_Co= {new Door(),new Window()};
	/**List that hold elements of type ConstructComponent.*/
	private List<ConstructComponent> list_con=new ArrayList<ConstructComponent>();;
	private Wall tempWall;
	public ReadFromFile() {
		
	}
	/**This method is used to read data from file with the path s. The data  will
	 * be added to the lists(toDraw,list_con and wall_List) .
	 * @param s is the string that represent the file path used in this method.
	 * @precondition s is valid path to a file.<p>toDraw!=null<p>list_con!=null
	 * <p>wall_List!=null
	 * @postcondition the data from the file will be added to list(toDraw,list_con and wall_List)
	 * */
	public void read(String s) {
		String shape="";
		try {
			BufferedReader reader=new BufferedReader(new FileReader(s));
			try {
				while((shape=reader.readLine())!=null) {
					String[]array =shape.split(" ");
					if(array[0].equals("Window")) {
						ConstructComponent co=con_Co[1].clone();
						co.set_x(Integer.parseInt(array[1]));
						co.set_y(Integer.parseInt(array[2]));
						co.setIncrement(Integer.parseInt(array[3]));
						list_con.add(co);
					}
					else if(array[0].equals("Wall")) {
						tempWall=new Wall();
						tempWall.x_1=Integer.parseInt(array[1]);
						tempWall.y_1=Integer.parseInt(array[2]);
						tempWall.x_2=Integer.parseInt(array[3]);
						tempWall.y_2=Integer.parseInt(array[4]);
						wall_List.add(tempWall);
					}
					else if(array[0].equals("Door")) {
						
						ConstructComponent co=con_Co[0].clone();
						co.set_x(Integer.parseInt(array[1]));
						co.set_y(Integer.parseInt(array[2]));
						co.setIncrement(Integer.parseInt(array[3]));
						list_con.add(co);
						
					}
					else{
						if(getIndex(array[0])>=0) {
						Fshape sh=comp[getIndex(array[0])].clone();
						sh.set_x(Integer.parseInt(array[1]));
						sh.set_y(Integer.parseInt(array[2]));
						sh.setIncrement(Integer.parseInt(array[3]));
						toDraw.add(sh);}}
					}
				reader.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	/**Return the index of element of Type Fshape with the same toString() value as s.
	 * @param is the string that will be compared to the element in comp.
	 * @precondition comp != null<p>s != null
	 * @postcondition return the index of the element or -1 if there is no element in comp
	 * that have toString() value equal to s.*/
	private int getIndex(String s) {
		
		int index=-1;
		for(int i=0;i<comp.length;i++) {
			if(comp[i].toString().equals(s)) {return i;}
		}
		return index;
	}
	/**Return ArrayList of type Fshape. Method used in other classes to get the loaded 
	 * data. 
	 * @return ArrayList of type Fshape
	 * @precondition toDraw!=null
	 * @postcondition return toDraw
	 * */
	public ArrayList<Fshape> getList(){
		return toDraw;
	}
	/**Return List of type Wall. Method used in other classes to get the loaded 
	 * data.
	 * @return List of type Wall
	 * @precondition wall_List!=null
	 * @postcondition return wall_List
	 * */
	public List<Wall> getWallList(){
		return wall_List;
	}
	/**Return List of type ConstructComponent. Method used in other classes to get the loaded 
	 * data. 
	 * @return List of type ConstructComponent
	 * @precondition list_con!=null
	 * @postcondition return list_con
	 * */
	public List<ConstructComponent>  getWindowList(){return list_con;}
}
