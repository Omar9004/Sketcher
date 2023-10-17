package project_own;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import component.ConstructComponent;
import component.Fshape;
import component.Wall;
import component.Window;
import output_input.OutputWriter;

public class MenuBar extends JMenuBar implements ActionListener,UpdateObserver {
	/**@invariant Component_Panel is not visible if construct_Panel is visible and vice versa.
	 * @invariant Data will be saved to a file.
	 * */
	/**Panel used to manage Fshape element. */
	private JPanel component_Panel;
	/**Panel used to manage ConstructComponent element. */
	private JPanel construct_Panel;
	/**Panel used to manage changing between component_Panel and construct_Panel. */
	private JPanel main_panel;
	private JMenu menu_1;
	private JMenu menu_2;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private ArrayList<Fshape> listToSave;
	private OutputWriter output;
	private JFrame helpFrame;
	/**
	 * Initialise panels,JMenuItem and JMenu.
	 * @param panel_1 is the JPane used to manage Fshape element.
	 * @param is the JPane used to manage ConstructComponent element.
	 * @precondition panel_1!=null<p>panel_2!=null
	 * @postcondition The panels will be initialised
	 * */
	public MenuBar(JPanel panel_1,JPanel panel_2) {
		helpFrame=new Help();
		component_Panel=panel_1;
		construct_Panel=panel_2;
		component_Panel.setBackground(Color.LIGHT_GRAY);
		construct_Panel.setBackground(Color.LIGHT_GRAY);
		main_panel=new JPanel();
		main_panel.add(component_Panel);
		main_panel.add(construct_Panel);
		construct_Panel.setVisible(true);
		component_Panel.setVisible(false);
		main_panel.setBackground(Color.LIGHT_GRAY);
		menu_1=new JMenu("Tools");
		menu_2=new JMenu("Help");
		menu_1.add(main_panel);
		menuItem_1=new JMenuItem("Draw House structure");
		menuItem_2=new JMenuItem("Add furniture");
		menuItem_3=new JMenuItem("Save");
		menuItem_4=new JMenuItem("Open help screen");
		addToMenu(menu_1,menuItem_1);
		addToMenu(menu_1,menuItem_2);
		addToMenu(menu_1,menuItem_3);
		addToMenu(menu_2,menuItem_4);
		this.add(menu_1);
		this.add(menu_2);
		
	}
	/**Add JMenuItem to JMenu.
	 * @param menu is the JMenu that the menuItem will be added to. 
	 * @param menuItem is the JMenuItem that will be added to menu.
	 * @precondition menu != null <p>menuItem != null
	 * @postcondition menuItem will be added to menu <p> Attache ActionListener to menuItem 
	 * */
	private void addToMenu(JMenu menu,JMenuItem menuItem) {
		menu.add(menuItem);
		menuItem.addActionListener(this);
	}
	
	/*public void add_Component_Panel(JPanel panel) {
		component_Panel=panel;
	}
	public void add_Construct_Panel(JPanel panel) {
		construct_Panel=panel;
	}*/
	/**Method return the main panel that contains the sub panel.
	 * @return JPanel to be used in other classes.
	 * @precondition main_panel!=null
	 * @postcondition The main panel will be returned.
	 * */
	public JPanel getMainPanel() {
		if(main_panel!=null)return main_panel;
		else return null;
	}
	/**Method take care of changing between panels. It take care also of saving 
	 * progress when is triggered by the JMenu Item (menuItem_3).
	 * @precondition menuItem_1!=null<p>menuItem_2!=null<p>menuItem_3!=null<p>
	 * component_Panel!=null<p>construct_Panel!=null<p>output!=null
	 * @postcondition Only one of the two panel will set to be visible<p>Data will be
	 * saved.  */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuItem_1) {
			
			component_Panel.setVisible(false);
			construct_Panel.setVisible(true);
			repaint();
    		validate();
		}
		if(e.getSource()==menuItem_2){
			component_Panel.setVisible(true);
			construct_Panel.setVisible(false);
			repaint();
    		validate();
		}
		if(e.getSource()==menuItem_3) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter nameFilter=new FileNameExtensionFilter(".sc", "sc");
			fileChooser.addChoosableFileFilter(nameFilter);
			int v=fileChooser.showSaveDialog(null);
			if(v==JFileChooser.APPROVE_OPTION) {
				Path path=Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
				if(Files.notExists(path)) {
					File f=new File(fileChooser.getSelectedFile().getAbsolutePath()+".sc");
					output.writeToFolder(f.getAbsolutePath());
				}
				else if(Files.exists(path)) {
					output.writeToFolder(fileChooser.getSelectedFile().getAbsolutePath());
				}	
				
			}
		}
		if(e.getSource()==menuItem_4) {
			helpFrame.setVisible(true);
		}
	}
	/**Get the OutputWriter used by the application.
	 * @param writer is the OutputWriter to be added to this class.
	 * @precondition writer!=null<p>output is a variable of type OutputWriter.
	 * @postcondition  The variable output will refer writer  */
	public void addOutputWriter(OutputWriter writer) {
		output=writer;
	}
	
	@Override
	public void updateCoordinate(ArrayList<Fshape> list,List<Wall> wall,List<ConstructComponent> co) {
		listToSave=list;
	}
	
}