package project_own;

import java.util.ArrayList;
import java.util.List;

import component.ConstructComponent;
import component.Fshape;
import component.Wall;
import component.Window;

public interface UpdateObserver {
	
	//void updateInfo(int n,String s);
	/**Used to update the classes that implement UpdateObserver interface with
	 * the new version of(list of Fshape, list of wall and list of ConstructComponent) 
	 * when they get updated. 
	 * @param list is the list of Fshape
	 * @param wall_List is the list of Wall
	 * @param list_Con is the list of ConstructComponent
	 * */
	void updateCoordinate(ArrayList<Fshape> list,List<Wall> wall_List,List<ConstructComponent> list_Con);
}
