package project_own;
/**Reference
 * https://alvinalexander.com/blog/post/jfc-swing/closing-your-java-swing-application-when-user-presses-close-but/?page=2
 * https://stackoverflow.com/questions/23092794/how-to-stop-jfilechooser-from-being-closed-when-approve-or-cancel-button-is-pres/23093436
 * https://www.codejava.net/java-se/swing/preventing-jframe-window-from-closing
 * */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import output_input.OutputWriter;
import output_input.ReadFromFile;
public class Sketcher extends JFrame implements ActionListener{
	/**@invariant Saving the data when exiting the program
	 * @invariant Loading the data when entering the program
	 * @invariant component,MenuBar ...etc will be viewed on the main frame 
	 * */
	/**Used to manage create new file.*/
	private JButton newFile;
	/**Used to manage opening an existing file.*/
	private JButton open;
	/**Used as welcome frame before opening the main frame.*/
	private JFrame popUp;
	/**Variable of class Drawing. It manage drawing and laying component.*/
	private Drawing toDraw;
	/**Used to hold the two buttons (newFile and open)*/
	private JPanel buttonPanel; 
	/**Variable of class OutputWriter. Used to take care of saving data. */
	private OutputWriter writer;
	/**Variable of class ReadFromFile. Used to take care of reading data. */
	private ReadFromFile reader;
	/**Initialise the elements used in this class(main frame,writer..etc).
	 * Initialise and attache WindowListener and added to the main frame.  
	 */
	public  Sketcher() {
		initialise();
		initialise_addWindowListener();
		
	}
	/**Used to initialising and adding WindowListener to the main frame. The method 
	 * used also to handle the close operation of the main frame.
	 * @precondition toDraw!=null and writer!=null
	 * @postcondition Data saved or rewrited at the choosed file, the main frame is closed
	 * or the closing operation is cancelled.*/
	private void initialise_addWindowListener() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int value=showWarning();
				if(value==0) {
					System.exit(0);
				}
				else if(value==1) {
					if(toDraw.checkList()) {
					JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
					fileChooser.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter nameFilter=new FileNameExtensionFilter(".sc", "sc");
					fileChooser.addChoosableFileFilter(nameFilter);
					int v=fileChooser.showSaveDialog(null);
					if(v==JFileChooser.APPROVE_OPTION) {
						Path path=Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
			
							if(Files.notExists(path)) {
								File f=new File(fileChooser.getSelectedFile().getAbsolutePath()+".sc");
								writer.writeToFolder(f.getAbsolutePath());
								System.exit(0);	
							}
							else if(Files.exists(path)) {
								writer.writeToFolder(fileChooser.getSelectedFile().getAbsolutePath());
								System.exit(0);	
							}
					}
					}
					else {
						//System.out.println("There is nothing to save");
						return;
					}
				}
				else if(value==2) {
					setVisible(true);
				}
			}
		});
	}
	/**Initialise the component that will be used and add them to the main frame.
	 * @precondition Variable of type(ReadFromFile, JButton, ReadFromFile, Drawing,
	 *  addOutputWriter etc) exist. 
	 * @postcondition The last mentioned variables will be initialised and added to the frame. */
	private void initialise() {
		reader=new ReadFromFile();
		buttonPanel=new JPanel();
		popUp=new JFrame("Sketcher");
		newFile=new JButton("Create new");
		open=new JButton("Open");
		newFile.addActionListener(this);
		open.addActionListener(this);
		buttonPanel.add(newFile);
		buttonPanel.add(open);
		JLabel label= new JLabel();
		ImageIcon backGround = new ImageIcon("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\My_apartment.PNG"); 
		label.setIcon(backGround);
		buttonPanel.add(label);
		popUp.add(buttonPanel);
		popUp.setSize(1500,750);
		popUp.setLocationRelativeTo(null);
		popUp.setResizable(false);
		popUp.setVisible(true);
		toDraw=new Drawing();
		writer=new OutputWriter();
		toDraw.addObserver(writer);
		JScrollPane scrollPane= new JScrollPane();
		scrollPane.setViewportView(toDraw);
		scrollPane.setBackground(Color.LIGHT_GRAY);
		MenuBar menu=new MenuBar(toDraw.getComponentPanel(),toDraw.getConstructPanel());
		toDraw.addObserver(menu);
		menu.addOutputWriter(writer);
		this.setJMenuBar(menu);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.add(menu.getMainPanel(),BorderLayout.NORTH);
		this.setSize(1700,950);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sketcher");
	}
	/**Pop up warning dialog in case user try to close the main frame. Give ability to
	 * the user to close the main frame without saving, cancel or exit and save.
	 * @precondition The method is called when exiting the main frame.
	 * @postcondition The user will have ability to (close the main frame, save data
	 * and close the main frame, cancel this dialog without exiting the main frame).
	 * */
	private int showWarning() {
		String[] options={"Exit Without saving","Save and Exit","Cancel"};
		String initialValue=options[1];
		Icon c=new ImageIcon(("C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\exit.png"));
		return JOptionPane.showOptionDialog(this, "You are about to exit the programe \n"+"Do you want to save changes?"
				, "Warning Message", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, 
				c, options, initialValue);
	}
	/**Take care of handling the event caused by the element that have the main 
	 * frame as ActionListener 
	 * @precondition newFile!=null<p>popUp!=null<p>open!=null<p>reader!=null<p>
	 * toDraw!=null
	 * @postcondition Event caused by the element that have the main 
	 * frame as ActionListener will be handled.<p>User is able to save data.*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newFile) {
			setVisible(true);
			popUp.setVisible(false);
			
		}
		if(e.getSource()==open) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter nameFilter=new FileNameExtensionFilter(".sc", "sc");
			fileChooser.addChoosableFileFilter(nameFilter);
			int v=fileChooser.showOpenDialog(null);
			
			if(v==JFileChooser.APPROVE_OPTION) {
				String s=fileChooser.getSelectedFile().getAbsolutePath();
				if(fileChooser.getSelectedFile().exists()&&fileChooser.getSelectedFile()!=null) {
				reader.read(fileChooser.getSelectedFile().getAbsolutePath());
				toDraw.updateToDraw(reader.getList());
				toDraw.updateWall(reader.getWallList());
				toDraw.updateListCon(reader.getWindowList());
				setVisible(true);
				popUp.setVisible(false);
				
				}
				else {
					//System.out.println("Selected file is empty");
				}
			
			}
		}
		
	}
	
	
}
