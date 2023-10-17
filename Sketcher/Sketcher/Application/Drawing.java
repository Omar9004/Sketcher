package project_own;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import component.*;
import component.Window;
import output_input.Shopping_List;

public class Drawing extends JComponent implements ActionListener {
	/**
	 * @invariant myObservers.size()>0 &&Buttons.size()>0 &&toDraw.size()>0 
	 * &&theWall.list.size()>0 && list_con.size()>0 
	 * @invariant paintComponent(Graphics g) method will draw component in the lists
	 * @invariant classes that implement UpdateObserver will have the last updated
	 * version of the lists(toDraw, theWall.list(), list_con)
	 * */
	/**Used to hold Observer of type UpdateObserver.*/
	private ArrayList<UpdateObserver> myObservers;
	/**Used to hold buttons of type JButton.*/
	private ArrayList<JButton> Buttons;
	/**Used to hold elements of type Fshape.*/
	private ArrayList<Fshape> toDraw;
	/**Used to manage movement of component of type Fshape. */
	private JToggleButton move;
	private JToggleButton drawWall,invoiceButton;
	private Wall theWall=new Wall();
	private Wall tempWall;
	private JButton addWindow;
	private int x_1=0,y_1=0,x_2=0,y_2=0;
	/**Array of elements of type Fshape.*/
	Fshape[] comp = { new Dresser(), new DiningTable(), new Table(), new DoubleSofa(), new TripleSofa(), new Bed(),
			new SingelBed(), new SmallDresser() ,new Shower(),new ToiletChair(), new Washbasin(),new TVbench(), new KitchenCounter()
			,new Desk()};
	/** Used to hold the buttons. Panels added to this class*/
	private JPanel component_Panel, construct_Panel;
	/**Is a variable of class Invoice. Add ass an UpdateObserver in this class. */
	private Shopping_List inovice =new Shopping_List();
	/**Used to manage rotation of component of type Fshape. */
	private JToggleButton rotateButton;
	/**Used to manage delete of component of type ConstructComponent and Wall.*/
	private JToggleButton remove_C;
	/**Used to manage delete of the component of type Fshape.*/
	private JToggleButton remove_F;
	private JButton add_Door;
	private ConstructComponent[] con_Co= {new Door(),new Window()};
	/**Used to hold elements of type ConstructComponent.*/
	private List<ConstructComponent> list_con;
	/**Used to manage rotation of component of type ConstructComponent. */
	private JToggleButton rotate_On_construct;
	/**Used to manage movement of component of type ConstructComponent. */
	private JToggleButton move_On_Construct;
	/**Initialise the elements used in this class, initialise mouseListener
	 *  and initialise mouseMotionListener.
	 * */
	public Drawing() {
		initialise();
		initialise_MouseListener();
		initialise_MouseMotionListener();
	}
	/**Initialise and attache MouseMotionListener to this class. Responsible of keep tracking
	 * which panel is in use and disable the other one by calling changePanel() method.  
	 * @precondition panels!=null and buttons!=null
	 * @precondition panels and buttons are already initialised.
	 * @postcondition the panel which is not visible, it's buttons set to be disabled.
	 * @postcondition the panel which is visible, it's buttons set to be enabled.  */
	private void initialise_MouseMotionListener() {
		addMouseMotionListener(new MouseMotionAdapter() { 
			  public void mouseDragged(MouseEvent event) {
				  changePanel();	
			  }});
	}
	/**Initialise and attache MouseListener to this class. Responsible of keep tracking activities
	 * like rotation of components, deleting components, moving components and wall
	 * drawing.
	 * @precondition panels!=null and buttons!=null
	 * @precondition Component list size > 0
	 * @postcondition User is able to draw wall,rotating components, moving components
	 * deleting components.*/
	private void initialise_MouseListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				tempWall=new Wall();
				if(rotateButton.isSelected()) {
					if(toDraw.size()>0) {
						for(Fshape f:toDraw) {
							if(f.contains(event.getPoint())) {
								f.increment();
							}
						}
					}	
					repaint();
				}
				if(rotate_On_construct.isSelected()) {
					for(ConstructComponent c:list_con) {
						if(c.contains(event.getPoint())) {
							c.increment();	
						}
					}
				}
				Point2D mousePoint = event.getPoint();
				x_1=(int) mousePoint.getX();
				y_1=(int)mousePoint.getY();
				if(event.getButton()==event.BUTTON3) {
					Point2D p = event.getPoint();
					if(remove_C.isSelected()) {
						drawWall.setSelected(false);
						if(remove_F.isSelected()) {remove_F.setSelected(false);}
						if(list_con!=null&&list_con.size()>0) {
							int index=findCon(p);
							if(index>=0) {list_con.remove(index);return;}	
						}
						if(theWall.list()!=null&&theWall.list().size()>0) {
							int indexWall=theWall.findWall(p);
							if(indexWall>=0) {theWall.list().remove(indexWall);}
						}
					}
					if(remove_F.isSelected()) {
						if(rotateButton.isSelected()) {rotateButton.setSelected(false);}
						if(remove_C.isSelected()) {remove_C.setSelected(false);}
						if(getShape(p)==null) {return;}
						else if(toDraw.size()!=0) {
						toDraw.remove(findInteger(toDraw,p));}
					}
					
					}
				repaint();
			}
			public void mouseReleased(MouseEvent event) {
				Point2D mousePoint = event.getPoint();
				x_2=(int) mousePoint.getX();
				y_2=(int)mousePoint.getY();
				tempWall.Set_Cor(x_1, y_1, x_2, y_2);
				if(drawWall.isSelected()&&(rotate_On_construct.isSelected()||move_On_Construct.isSelected()||remove_C.isSelected())) {
					drawWall.setSelected(false);
				}
				if(drawWall.isSelected()) {theWall.add(tempWall);}
				theWall.Set_Cor(x_1, y_1, x_2, y_2);
				repaint();
			}
		});
		repaint();
	}
	/**Initialise the buttons, panels and lists.
	 * @precondition comp!=null && comp.length()>0
	 * @precondition con_Co!=null && con_Co.length()>0
	 * @precondition inovice!=null
	 * @postcondition each of the button in buttons added to it's panel.
	 * @postcondition buttons panels and lists will be initialised. 
	 * */
	private void initialise() {
		list_con=new ArrayList<ConstructComponent>();
		toDraw = new ArrayList<Fshape>();
		invoiceButton=new JToggleButton();
		invoiceButton.setIcon(new ImageIcon("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\shopping.png"));
		invoiceButton.setToolTipText("Shopping List");
		myObservers=new ArrayList<UpdateObserver>();
		this.setBackground(Color.LIGHT_GRAY);
		drawWall=new JToggleButton("Draw Wall");
		addWindow=new JButton("Add window");
		rotateButton=new JToggleButton();
		rotateButton.setIcon(new ImageIcon("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\rotate.png"));
		rotateButton.setToolTipText("Rotate furniture");
		rotate_On_construct=new JToggleButton();
		rotate_On_construct.setIcon(new ImageIcon("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\rotate.png"));
		rotate_On_construct.setToolTipText("Rotate window or door");
		move = new JToggleButton();
		move.setIcon(new ImageIcon(("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\Move.png")));
		move.setToolTipText("Move furniture");
		move_On_Construct=new JToggleButton();
		move_On_Construct.setIcon(new ImageIcon(("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\Move.png")));
		move_On_Construct.setToolTipText("Move door or window");
		
		remove_C=new JToggleButton();
		remove_C.setIcon(new ImageIcon("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\icons8-remove.png"));
		remove_C.setToolTipText("Activate and use left mouse Button to mark and remove windows, door or wall");
		remove_F=new JToggleButton();
		remove_F.setIcon(new ImageIcon("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\icons8-remove.png"));
		remove_F.setToolTipText("Activate and use left mouse Button to mark and remove the shape");
		add_Door=new JButton("Add door");
		Buttons = new ArrayList<>();
		component_Panel = new JPanel();
		construct_Panel = new JPanel();
		for (int i = 0; i < comp.length; i++) {Buttons.add(new JButton(comp[i].toString()));}
		for (JButton g : Buttons) {addButtonToPanel(component_Panel,g);} 
		addButtonToPanel(construct_Panel,addWindow);
		addToggleButtonToPanel(component_Panel,move);
		addToggleButtonToPanel(component_Panel,rotateButton);
		addToggleButtonToPanel(component_Panel,remove_F);
		addObserver(inovice);
		addToggleButtonToPanel(component_Panel,invoiceButton);
		addToggleButtonToPanel(construct_Panel,drawWall);
		addButtonToPanel(construct_Panel,add_Door);
		addToggleButtonToPanel(construct_Panel,remove_C);
		addToggleButtonToPanel(construct_Panel,rotate_On_construct);
		addToggleButtonToPanel(construct_Panel,move_On_Construct);
		for (Fshape p : comp) {addMouseListener((MouseListener) p);}
	}
	/**
	 * Find the index of the ConstructComponent in list_con that contains the Point2D p
	 * and return the index of this element or -1 if no element contains p: 
	 * @param p Point2D at the the JComponent
	 * @return -1 if no component in inlist_con that contains p, otherwise return the 
	 * index of the component in inlist_con that contains p
	 * @precondition list_con.get(index).contains(p)
	 * @precondition list_con.size()>0
	 * @postcondition return index>=0&&index<=list_con.size()*/
	private int findCon(Point2D p) {
		int index=-1;
		for(int i=0;i<list_con.size();i++){
			if(list_con.get(i).contains(p)){
				return i;
				}
			}
		return index;
	}
	/**
	 * Check which one of the tow JPanel(construct_Panel and component_Panel) is
	 * visible, disable the JToggleButton at the JPanel that is not visible.
	 * @precondition construct_Panel!=null&&component_Panel!=null
	 * @precondition construct_Panel.isVisible()||component_Panel.isVisible()
	 * @postcondition JTogglButtons.setSelected(false) at the panel that in not visible*/
	private void changePanel() {
		
		  if(construct_Panel.isVisible()&&!component_Panel.isVisible()) {
				move.setSelected(false);
				rotateButton.setSelected(false);
				remove_F.setSelected(false);
				disableMovment();
				repaint(); 
				validate();
			}
		  if(component_Panel.isVisible()&&(drawWall.isSelected()||remove_C.isSelected()||rotate_On_construct.isSelected()||move_On_Construct.isSelected())) {
			  drawWall.setSelected(false);
			  remove_C.setSelected(false);
			  rotate_On_construct.setSelected(false);
			  move_On_Construct.setSelected(false);
			  repaint(); 
			  validate();
		  }
		  repaint(); 
	}
	/**
	 * Find and return the element in toDraw list that contains p. 
	 * @param p Point2D at the the JComponent
	 * @return Fshape that contains p or null 
	 * @precondition toDraw.size()>0
	 * @precondition toDraw !=null
	 * @precondition p !=null
	 * @precondition toDraw.get(index).contains(p)
	 * @postcondition return Fshape in toDraw that contains p */
	public Fshape getShape(Point2D p) {
		for (Fshape s : toDraw) {
			if (s.contains(p)) {
				return s;
			}
		}
		return null;
	}
	/**
	 * Find and return the index of the element in shape that contains h.
	 * @param shape the list of Fshape to checked if one if it's elements contains the Point2D h
	 * @param h the Point2D to be checked 
	 * @return 0 or the index of Fshape in shape list that contains the Point2D h
	 * @precondition h !=null 
	 * @precondition shape !=null and shape.size > 0
	 * @postcondition return index>=0&&index<=shap.size() */
	private int findInteger(List<Fshape> shape, Point2D h) {
		for (int i=0;i<shape.size();i++) {
			if(shape.get(i).contains(h)) {
				return i;
			}	
		}
		return 0;
	}
	/**
	 * Draw the elements that is in lists(toDraw, theWall.list() and list_con).
	 * It is also takes care of updating the Observer that Implements UpdateObserver,
	 *  with the last mentioned three lists. 
	 * @param Graphics g
	 * @precondition toDraw!=null &&theWall.list()!=null && list_con!=null
	 * @precondition toDraw.size()>0||theWall.list().size()>0||list_con.size()>0
	 * @postcondition draw component from toDraw, theWall.list() or list_con
	 * @postcondition send lists to every observer class that implements UpdateObserver
	 * @postcondition update number on invoiceButton with number of element in shopping list
	 * 
	 * */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(toDraw!=null) {for(Fshape s: toDraw) {
			intersection(s);
			s.draw(g2);}}
		invoiceButton.setText(Integer.toString( toDraw.size()));
		if(theWall.list()!=null) {for(Wall w:theWall.list())w.draw(g2);}
		if(list_con.size()>0) {for(ConstructComponent c:list_con)c.draw(g2);}
		sendList(toDraw,theWall.list(),list_con);	
	}
	/**Check if one of the element in toDraw, list_con or theWall.list() intersecting
	 * with the Fshape s. If that is the case the color_flag at that will be set to
	 * be true. 
	 * @param s is Fshape to be checked if it is intersects with other component
	 * @precondition Fshape s!=null
	 * @precondition toDraw!=null &&theWall.list()!=null && list_con!=null
	 * @precondition toDraw.size()>0||theWall.list().size()>0||list_con.size()>0
	 * @postcondition s.updateColor(true)
	 * */
	private void intersection(Fshape s) {
		if(s!=null) {
			for(Fshape f:toDraw) {
				if(s.getBounds().intersects(f.getBounds())&&!s.equals(f)) {
					s.updateColor(true);
				}
			}
			for(ConstructComponent w:list_con) {
				if(s.getBounds().intersects(w.getBounds())) {
					s.updateColor(true);
				}
			}
			for(Wall w:theWall.list()) {
				if(s.getBounds().intersects(w.getBounds())) {
					s.updateColor(true);
				}
			}
		} 
	}
	/**Add the button to the specified panel. Attach this class as actionListener 
	 * to button.
	 * @param panel is the JPanel to add the JToggleButton to it
	 * @param button is the JToggleButton to be added to the panel
	 * @precondition panel!=null
	 * @precondition button!=null
	 * @postcondition panel.add(button)
	 * @postcondition button.addActionListener(this)
	 * */
	private void addToggleButtonToPanel(JPanel panel,JToggleButton button) {
		if(panel!=null&&button!=null) {
		panel.add(button);
		button.addActionListener(this);}
	}
	/**Add the button to the specified panel. Attach this class as actionListener 
	 * to button.
	 * @param panel is the JPanel to add button to it
	 * @param button is the JButton to be added to panel
	 * @precondition panel!=null
	 * @precondition button!=null
	 * @postcondition panel.add(button)
	 * @postcondition button.addActionListener(this)
	 * */
	private void addButtonToPanel(JPanel panel,JButton button) {
		if(panel!=null&&button!=null) {
			panel.add(button);
			button.addActionListener(this);}
	}
	/**Return the JPanel that represent the Fshape. 
	 * @return JPanel the component_Panel
	 * @precondition component_Panel != null
	 * @postcondition return component_Panel*/
	public JPanel getComponentPanel() {
		if (component_Panel != null)
			return component_Panel;
		return null;
	}
	/**Return the JPanel that represent the ConstructComponent. 
	 * @return JPanel the construct_Panel
	 * @precondition construct_Panel != null
	 * @postcondition return construct_Panel*/
	public JPanel getConstructPanel(){
		if (construct_Panel != null)
			return construct_Panel;
		return null;
	}
	/**Return true if shape contains h, else return false. 
	 * @param shape is the list to be checked.
	 * @param h is the Fshape that will be searched for in shape.
	 * @precondition shape!=null and shape.size()>0
	 * @precondition h!=null 
	 * @postcondition return true if shape contains h
	 * @postcondition return false if shape dose not contains h*/
	private boolean find(List<Fshape> shape, Fshape h) {
		for (int i=0;i<shape.size();i++) {
			if(shape.get(i) == h) {return true;}
		}
		return false;
	}
	/**Take care of changing the flag button depending on which move button is selected 
	 * @precondition rotateButton!=null and rotate_On_construct!=null
	 * @precondition move_On_Construct !=null and move_On_Construct.isSelected==true
	 * @postcondition  set flag_Button=true at each element in list_con
	 * @postcondition list_con != null and list_con.size()>0
	 * @precondition move.isselected()==true
	 * @postcondition set flag_Button=true at each element in toDraw*/
	private void disableMovment() {
		if(rotateButton.isSelected()) {rotateButton.setSelected(false);}
		if(rotate_On_construct.isSelected()) {rotate_On_construct.setSelected(false);}
		if(move_On_Construct.isSelected()) {
			for(ConstructComponent c:list_con) {c.changeStatus(true);}
		}
		else if(!move_On_Construct.isSelected()) {
		for(ConstructComponent c:list_con) {c.changeStatus(false);}}
		for(Fshape s1: toDraw) {
			if(move.isSelected()) {s1.changeStatus(true);}
			else if(!move.isSelected()||move_On_Construct.isSelected()) {s1.changeStatus(false);}	
		}
		repaint();	
	}
	/**Update toDraw to refer to list. Used when need to load data from saved file.
	 * Attache each Fshape in toDraw as addMouseListener and addMouseMotionListener to
	 * this class.  
	 * @param list is the list of Fshape that toDraw will refer to.
	 * @precondition list!= null and toDraw !=null
	 * @postcondition toDraw=list*/
	public void updateToDraw(ArrayList<Fshape> list) {
		toDraw=list;
		if(toDraw!=null) {
			for(Fshape f:toDraw) {
				this.addMouseListener((MouseListener) f);
				this.addMouseMotionListener((MouseMotionListener) f);
			}
		}
	}
	/**Update theWall.list() with list. Used when need to loaddata from saved file.
	 *@param list is the list of Wall that theWall.list() will refer to.
	 *@precondition list!=null and theWall.list()!=null
	 *@postcondition theWall.setList(list)*/
	public void updateWall(List<Wall> list) {
		theWall.setList(list);
	}
	/**Update list_con with list. Used when need to load data from saved file.
	 * Attache each ConstructComponent in list_con as addMouseListener and 
	 * addMouseMotionListener to this class
	 * @param list is the list of ConstructComponent that list_con will refer to.
	 * @precondition list != null and list_con!=null
	 * @postcondition list_con=list*/
	public void updateListCon(List<ConstructComponent> list) {
		list_con=list;
		for(ConstructComponent c:list_con) {
		this.addMouseListener((MouseListener) c);
		this.addMouseMotionListener((MouseMotionListener) c);}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int i =0;
		if(e.getSource()==add_Door||e.getSource()==addWindow) {
			changePanel();
			disableMovment();
			ConstructComponent temp;
			if(e.getSource()==add_Door) {temp=con_Co[0].clone();}
			else {temp=con_Co[1].clone();}
			this.addMouseListener((MouseListener) temp);
			this.addMouseMotionListener((MouseMotionListener) temp);
			if(drawWall.isSelected()) {drawWall.setSelected(false);}
			list_con.add(temp);
			repaint();
		}
		for(JButton s:Buttons) {
			if(move.isSelected()&& e.getSource()!=move) {
				rotateButton.setSelected(false);
				move.setSelected(false);
				disableMovment();
			}
			if(e.getSource()==s) {
				rotateButton.setSelected(false);
				if(find(toDraw , comp[i])) {
					
					Fshape shape = comp[i].clone();
					
					this.addMouseListener((MouseListener) shape);
					this.addMouseMotionListener((MouseMotionListener) shape);
					toDraw.add(shape);
					//nextItem(i,shape.toString());
				}else {toDraw.add(comp[i]);
					//nextItem(i,comp[i].toString());
					}
				
				this.addMouseListener((MouseListener) comp[i]);
				this.addMouseMotionListener((MouseMotionListener) comp[i]);
				repaint();
				}
			i++;
		}
		if(e.getSource()==move) {disableMovment();}
	
		if(e.getSource()==invoiceButton&&invoiceButton.isSelected()) {inovice.setvisibil(true);}
		else if(e.getSource()==invoiceButton&&!invoiceButton.isSelected()) {inovice.setvisibil(false);}
		
	}
	/*private void nextItem(int n,String s) {
		for (UpdateObserver so : myObservers) {
			so.updateInfo(n,s);
		}		
	}*/
	/**Used to update each class that implement the UpdateObserver with list
	 * of fshape, list of wall and list of ConstructComponent.
	 * @param list is the list of Fshape that each observer will be updated with.
	 * @param wall is the list of wall that each observer will be updated with.
	 * @param co is the list of ConstructComponent that each observer will be updated with.
	 * @precondition list!=null && wall != null && co!= null
	 * @precondition myObservers !=null && myObservers.size>0 
	 * @postcondition all classes that implement UpdateObserver will be updated with 
	 * the lists.*/
	private void sendList(ArrayList<Fshape> list,List<Wall> wall,List<ConstructComponent> co) {
		for (UpdateObserver so : myObservers) {
			so.updateCoordinate(list,wall,co);
		}
	}
	/**Add new UpdateObserver to myObservers.
	 * @param o is the UpdateObserver that will be added to the list of UpdateObserver
	 * @precondition o != null and myObservers!=null
	 * @postcondition The UpdateObserver o will be added to myObservers*/
	public void addObserver(UpdateObserver o) {
		myObservers.add(o);
	}
	/**Check if one of the component list size is > 0, return true. Otherwise the 
	 * method will return false. 
	 * @return true if toDraw.size()>0 or list_con.size()>0 or  theWall.list().size()>0
	 * otherwise return false.
	 * @precondition toDraw!=null and list_con!=null and theWall.list()!=null
	 * @precondition toDraw.size()>0 or list_con.size()>0 or theWall.list().size()>0
	 * @postcondition return true or false */
	public boolean checkList() {
		return toDraw.size()>0||list_con.size()>0||theWall.list().size()>0;
	}

}