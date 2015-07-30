/*This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package Interface;



import Controller.AGTT;
import Interface.dummy;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.security.AllPermission;
import java.util.ArrayList;

import javax.security.auth.callback.UnsupportedCallbackException;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.tools.DocumentationTool.Location;

public class GUI {

	  public static boolean RIGHT_TO_LEFT = false;
	  static String  fileID = new String() ;
	  public static Label HeaderLabel;
	  private static JTextField textField;
	  public static Panel ContentPanel;
	  public static Panel ButtonPanel;
	  public static JPanel aa;
	  public static JButton StartButton;
	  static AGTT obj = null;
	  static int index = 0;
	  static JList list = new JList<>();
	  static JTable table = null;
		static DefaultTableModel model = null;
	  public static JTextField minutes = new JTextField();
	  public static void addComponentsToPane(Container contentPane) {

	    	contentPane.setLayout(new BorderLayout(5,5));
	        if (!(contentPane.getLayout() instanceof BorderLayout)) {
	            contentPane.add(new JLabel("Container doesn't use BorderLayout!"));
	            return;
	        }
	        if (RIGHT_TO_LEFT) {
	            contentPane.setComponentOrientation(
	                java.awt.ComponentOrientation.RIGHT_TO_LEFT);
	        }
	        
	        HeaderLabel = new Label("Automated Game Testing Tool",Label.CENTER);
	        HeaderLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 22));
	        contentPane.add(HeaderLabel,BorderLayout.PAGE_START);

	        ContentPanel = new Panel();
	        ContentPanel.setSize(500,100);
	        ContentPanel.setBackground(Color.decode("#eeeeee"));
	        contentPane.add(ContentPanel,BorderLayout.CENTER);
	        
	        JLabel labelScenario = new JLabel("Start By recording one scenario, by pressing start !!");
	        labelScenario.setBounds(1, 13, 400, 50);
	        ContentPanel.add(labelScenario,BorderLayout.LINE_START);
	        
	        JButton startButton = new JButton("Start");
	        startButton.setBackground(Color.decode("#33AD33"));
	        startButton.setForeground(Color.decode("#FFFFFF"));
	        startButton.setContentAreaFilled(false);
	        startButton.setOpaque(true);
	        startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Scenario s = new Scenario();
				}
				
			});
	        ContentPanel.add(startButton,BorderLayout.LINE_START);
	        
	        JButton endButton = new JButton("Stop");
	        endButton.setBackground(Color.decode("#CC0000"));
	        endButton.setForeground(Color.decode("#FFFFFF"));
	        endButton.setContentAreaFilled(false);
	        endButton.setOpaque(true);
	        endButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//controller.ReadStateMachine(fileID);
					Scenario s = new Scenario();
				}
				
			});
	        ContentPanel.add(endButton,BorderLayout.LINE_START);
	        
	        textField = new JTextField();
			textField.setPreferredSize(new Dimension(300, 20));
			textField.setBackground(Color.WHITE);
			ContentPanel.add(textField,BorderLayout.WEST);
			textField.setColumns(30);
	        
	        final JButton btnNewButton = new JButton("Browse");
			btnNewButton.setBackground(UIManager.getColor("Button.light"));
			btnNewButton.addActionListener(new ActionListener() {
			JFileChooser chooser;	
			public void actionPerformed(ActionEvent arg0) {
					if (arg0.getSource() == btnNewButton)
				    {
				        chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Desktop")); //Downloads Directory as default
				        chooser.setDialogTitle("Select Location");
				        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				        chooser.setAcceptAllFileFilterUsed(false);
				        if (chooser.showSaveDialog(btnNewButton) == JFileChooser.APPROVE_OPTION)
				        { 
				            fileID = chooser.getSelectedFile().getPath();
				            fileID = fileID.replace('\\','/');
				            textField.setText(fileID);
				            System.out.print(fileID);
				        }
				    }
				}
			});
			btnNewButton.setPreferredSize(new Dimension(120, 22));
			btnNewButton.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
			ContentPanel.add(btnNewButton);
	        
			JLabel labelmap = new JLabel("Map keyboard input you have recorded to the transitions of StateMachine !!");
	        ContentPanel.add(labelmap,BorderLayout.LINE_START);
			
			final JButton btnNewButton_1 = new JButton("Mapping");
			btnNewButton_1.setBackground(Color.decode("#357EBD"));
			btnNewButton_1.setForeground(Color.decode("#FFFFFF"));
			btnNewButton_1.setContentAreaFilled(false);
			btnNewButton_1.setOpaque(true);
			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//controller.ReadStateMachine(fileID);
					dummy newContentPane = new dummy();
					newContentPane.main();
					
				}
				
			});
			btnNewButton_1.setFont(new Font("Segoe UI Light", Font.BOLD, 12));
			btnNewButton_1.setPreferredSize(new Dimension(100, 22));
			ContentPanel.add(btnNewButton_1,BorderLayout.LINE_END);
			
			ArrayList<String> testcases =  obj.getAllTestCases();
			String[] data =  new String[testcases.size()];
			for(int i=0;i<testcases.size();i++)
			{
				data[i] = "Testcase "+(i+1);				
			}
			list = new JList<>(data);
			 JScrollPane pane = new JScrollPane(list);
			 pane.setPreferredSize(new Dimension(120,270));
			    DefaultListSelectionModel m = new DefaultListSelectionModel();
			    m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    m.setLeadAnchorNotificationEnabled(false);
			    list.setSelectionModel(m);
			    ContentPanel.add(pane,BorderLayout.LINE_START);
			    
			JPanel testcasePanel = new JPanel();
			JTextArea jb = new JTextArea("Testcase");
			jb.setPreferredSize(new Dimension( 380,70));
			jb.setLineWrap(true);
			jb.setEditable(false);
			testcasePanel.add(jb);
			
			
			list.addListSelectionListener(new ListSelectionListener() {
				
	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	if(model != null)
	                	{
	                	//	model.setRowCount(0);
	                //	model.getDataVector().removeAllElements();
	                       
	                	}
	                	jb.setText(testcases.get(list.getSelectedIndex()));

	                  String[] columns = new String[] {
	                          "State(parent)", "Transition(Child)", "Key Stroke"
	                      };
	                  
	                       String[] tokens = testcases.get(list.getSelectedIndex()).split(",");
	                       Object[][] data = new Object[tokens.length][3];
	                       int index = 0;
	                       for(String s : tokens){
	                    	   if(index%2 == 0){
	                    		   data[index][0] = s; 
	                    		   data[index][1] = "";
	                    		   data[index][2] = "";
	                    	   }
	                    	   else{
	                    		   data[index][0] = ""; 
	                    		   data[index][1] = s;
	                    		   if(s.equals(" runforward")){	data[index][2] = "Right";	}
	                    		   else if(s.equals(" runbackward")){	data[index][2] = "Left";	}
	                    		   else if(s.equals(" Jump") || s.equals(" jump")){	data[index][2] = "Space";	}
	                    		   else { 	data[index][2] = "null";	}
	                    	   }
	                    	   index++;
	                       }
	                      //actual data for the table in a 2d array
	                       
	                       
	                      final Class[] columnClass = new Class[] {
	                    		  String.class, String.class, String.class
	                      };
	               
	                      //create table model with data
	                      model = new DefaultTableModel(data, columns) {
	               
	                          @Override
	                          public boolean isCellEditable(int row, int column)
	                          {
	                              return false;
	                          }
	               
	                          @Override
	                          public Class<?> getColumnClass(int columnIndex)
	                          {
	                              return columnClass[columnIndex];
	                          }
	                      };
	                      
	                      table = new JTable(model);
	                      model.fireTableDataChanged();
	                     //  table.repaint();
	                        table.setPreferredScrollableViewportSize(new Dimension(380,160));
	                       //add the table to the frame
	                       testcasePanel.add(new JScrollPane(table));
	                       
	                     // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	                    //  this.pack();
	                     // this.setVisible(true);
	                      
	                }
	            }
	        });
			testcasePanel.setPreferredSize(new Dimension(400,270));
			testcasePanel.setBackground(Color.WHITE);
			ContentPanel.add(testcasePanel,BorderLayout.LINE_START);  
	        ButtonPanel = new Panel( new BorderLayout());
	        ButtonPanel.setSize(500,100);
	        ButtonPanel.setBackground(Color.decode("#e0e0e0"));
	        contentPane.add(ButtonPanel,BorderLayout.PAGE_END);
	        
	        StartButton = new JButton("Run Testcase");
	        StartButton.setBackground(Color.decode("#357EBD"));
	        StartButton.setForeground(Color.decode("#FFFFFF"));
	        StartButton.setContentAreaFilled(false);
	        StartButton.setOpaque(true);
	        StartButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int min = Integer.parseInt( minutes.getText());
					min = min*60000;
					obj.prepareTestcases(min);
					index++;
				}
				
			});
	        
	        JButton Report = new JButton("Report");
	        Report.setBackground(Color.decode("#357EBD"));
	        Report.setForeground(Color.decode("#FFFFFF"));
	        Report.setContentAreaFilled(false);
	        Report.setOpaque(true);
	        Report.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					obj.getReport();
				}
				
			});
	        JPanel p = new JPanel(new BorderLayout());
	        JLabel minuteLabel = new JLabel(" Specify Minutes to run the automated testcases");
	        p.add(minuteLabel,BorderLayout.WEST);
	        minutes.setPreferredSize(new Dimension(150, 30));
	        p.add(minutes,BorderLayout.EAST);
	        ButtonPanel.add(p, BorderLayout.CENTER);
	        ButtonPanel.add(Report,BorderLayout.WEST);
	        ButtonPanel.add(StartButton, BorderLayout.EAST);
	    }

	    private static void createAndShowGUI() {
	    	
	        JFrame.setDefaultLookAndFeelDecorated(true);
	        obj = new AGTT();
	        obj.generateTestCases();     
			ArrayList<String> cases = obj.getAllTestCases();
			obj.SetTestCases(cases);
	        JFrame frame = new JFrame("BorderLayout Source Demo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        addComponentsToPane(frame.getContentPane());

	        frame.pack();
	        frame.setSize(600, 500);
	        frame.setLocation(300, 100);
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	    	try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (ClassNotFoundException e) {
			}
			catch (InstantiationException e) {
			}
			catch (IllegalAccessException e) {
			}
			catch (UnsupportedLookAndFeelException e) {
			}
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
}
