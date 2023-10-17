package project_own;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Help extends JFrame implements ActionListener{
	/**@invariant A frame that contain information about how to use the program
	 * */
	/**JTextArea to write the data to.*/
	private JTextArea text;
	/**JScrollPane to add text to it.*/
	private JScrollPane scroll;
	/**Button used to close the help frame*/
	private JButton closeButton;
	private JPanel panel;
	/**By baking a variable of this class as JFrame, user can set this frame visible
	 * to display the help text as an extra frame to the main program.
	 * @precondition Help.txt file exist at the main program directory.<p>
	 * This frame set to be visible.
	 * @postcondition The help text will appear as an external frame.   */
	public Help() {
		panel=new JPanel();
		closeButton=new JButton("Close help window");
		closeButton.addActionListener(this);
		panel.add(closeButton);
		panel.setBackground(Color.LIGHT_GRAY);
		this.setBackground(Color.LIGHT_GRAY);
		text=new JTextArea();
		text.setFont(new Font("Font", Font.BOLD, 20));
		text.setEditable(false);
		text.setBackground(Color.LIGHT_GRAY);
		readHelpFile();
		scroll=new JScrollPane(text);
		this.add(panel,BorderLayout.SOUTH);
		this.add(scroll, BorderLayout.CENTER);
		this.setSize(800, 800);
		this.setUndecorated(true);
	}
	/**Read from the file that contain help information.
	 * @precondition file exist
	 * @postcondition Data at the file will be readed and added to text */
	private void readHelpFile() {
		String path="C:\\Users\\lord_\\git\\local_repository_AOOP_project\\AOOP_Project\\src\\project_own\\Help.txt";
		String temp="";
		try {
			BufferedReader reader=new BufferedReader(new FileReader(path));
			try {
				while((temp=reader.readLine())!=null) {
					text.append(temp+"\n");
					
				}
				reader.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==closeButton) {
			this.setVisible(false);
		}
		
	}
}
