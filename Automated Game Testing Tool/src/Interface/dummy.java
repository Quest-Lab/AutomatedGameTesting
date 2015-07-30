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
*/package Interface;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.modeldriven.alf.syntax.expressions.ForAllOrExistsOrOneExpression;

import Controller.AGTT;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class dummy extends JPanel {
    private boolean DEBUG = false;
    AGTT controller = new AGTT();
    public dummy() {
        super(new GridLayout(1,0));

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(350, 350));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);

        //Fiddle with the Sport column's cell editors/renderers.
        setUpSportColumn(table, table.getColumnModel().getColumn(1));

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();
        table.setRowHeight(30);
        for (int i = 0; i <2; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            if (DEBUG) {
                System.out.println("Initializing width of column "
                                   + i + ". "
                                   + "headerWidth = " + headerWidth
                                   + "; cellWidth = " + cellWidth);
            }

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public void setUpSportColumn(JTable table,
                                 TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        ArrayList<String> keys = null;
        try {
			 keys = controller.KeysConversion();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        for (int i = 0; i < keys.size(); i++) {
        	comboBox.addItem(keys.get(i));
		}
        comboBox.addItem("None of the above");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }

    class MyTableModel extends AbstractTableModel {
    	boolean check = false; 
        private String[] columnNames = {"Transitions",                                       
                                        "Keys"};
        ArrayList<String> list = controller.GetTransitions();
        
        private Object[][] data = new Object[list.size()][2];
        

        public final Object[] longValues = {"keys", "keys",
                                            "None of the above",
                                            new Integer(20), Boolean.TRUE};
        public void populateData()
        {
        	System.out.println(list.size());
        	for (int i = 0; i < list.size(); i++) {
                data[i][0]=list.get(i);
                data[i][1]="None of the above";
            }
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
        	if(!check)
        	{
        		ArrayList<String> keys = new ArrayList<>();
        		try {
					keys = controller.KeysConversion();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		//System.out.println(keys.size());
        		this.populateData();
        		check = true;
        	}
        	
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel jp = new JPanel();
       // jp.setBounds(10, 10, 400, 400);
        jp.setPreferredSize(new Dimension(350,400));
        frame.setLocation(200, 200);
        frame.setPreferredSize(new Dimension(400,500));
        //Create and set up the content pane.
        dummy newContentPane = new dummy();
        newContentPane.setOpaque(true); //content panes must be opaque
        jp.add(newContentPane);
        
        final JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		//btnNewButton.setBounds(434, 42, 97, 31);
		btnNewButton.setPreferredSize(new Dimension(150,30));
		btnNewButton.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
			
		jp.add(btnNewButton);
		
        frame.setContentPane(jp);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
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