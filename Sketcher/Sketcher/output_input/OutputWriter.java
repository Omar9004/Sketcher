package output_input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import component.ConstructComponent;
import component.Fshape;
import component.Wall;
import component.Window;
import Application.UpdateObserver;

public class OutputWriter implements UpdateObserver {
	/**@invariant The data will be uploaded to new file or an existing file 
	 * @invariant This class have the last version of updated data*/
	/**Used to refer to the list that hold elements of type Fshape.*/
	private ArrayList<Fshape> listToSave;
	/**Used to refer to the list that hold elements of type Wall.*/
	private List<Wall> wall_List;
	/**Used to refer to the list that hold elements of type ConstructComponent.*/
	private List<ConstructComponent> list_Co;
	/*@Override
	public void updateInfo(int n,String s) {
		writeTo(n,s);
		
	}
	public void writeTo(int s,String str) {
		String st=Integer.toString(s) ;
	}*/
	/**@precondition list!=null<p>wall_List!=null<p>list_Con
	 * @postcondition The classes that implement UpdateObserver have access to the new data 
	 * in the lists.*/
	@Override
	public void updateCoordinate(ArrayList<Fshape> list,List<Wall> wall,List<ConstructComponent> list_Con) {
		listToSave=list;
		list_Co=list_Con;
		wall_List=wall;	
	}
	/**This method is used to update the file with the path s with the data that will
	 * be saved.
	 * @param s is the string that represent the file path used in this method.
	 * @precondition s is valid path to a file.<p>listToSave!=null<p>list_Co!=null
	 * <p>wall_List!=null
	 * @postcondition the data from the list(listToSave,list_Co and wall_List)
	 * will be uploaded to the file. */
	public void writeToFolder(String s) {
		int x=0;
		int y=0;
		int i=0;
		try {
			BufferedWriter b=new BufferedWriter(new FileWriter(s,false));
			for(Fshape sh:listToSave) {
				x=(int)sh.get_x();
				y=(int)sh.get_y();
				i=sh.getIncrement();
				b.write(sh.toString()+" "+x+" "+y+" "+i+"\n");
			}
			for(ConstructComponent c:list_Co) {
				b.write(c.toString()+" "+c.get_x()+" "+c.get_y()+" "+c.getIncrement()+"\n");
			}
			for(Wall w:wall_List) {
				b.write("Wall"+" "+w.x_1+" "+w.y_1+" "+w.x_2+" "+w.y_2+"\n");
			}
			b.close();
		 } catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
