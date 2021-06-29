package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;
import net.miginfocom.swing.MigLayout;

//Family-FamilyList
public class FamilyList extends JDialog {

	AfPanel root = new AfPanel();
	JPanel jpanel1 = new JPanel();
	JPanel jpanel2 = new JPanel();
	JPanel jpanel3 = new JPanel();
	JPanel jpanel4 = new JPanel();
	JPanel jpanel5 = new JPanel();
	MigLayout layout = new MigLayout();
//	MigLayout layout1=new MigLayout();
	JTable table = null;
	DefaultTableModel familytableModel = new DefaultTableModel();

	private boolean retValue = false;

	public FamilyList(JFrame owner) {
		super(owner, "FamilyList", true);
		this.setSize(1300, 800);

		this.setContentPane(root);
		root.setLayout(layout);
		// jpanel1.setSize(700, 200);
		jpanel1.setPreferredSize(new Dimension(650, 200));
		jpanel2.setPreferredSize(new Dimension(650, 200));
		jpanel3.setPreferredSize(new Dimension(650, 200));
		jpanel4.setPreferredSize(new Dimension(650, 200));
		jpanel5.setPreferredSize(new Dimension(1300, 400));
		root.add(jpanel1);
		root.add(jpanel2, "wrap");
		root.add(jpanel3);
		root.add(jpanel4, "wrap");
		root.add(jpanel5, "dock south");

		jpanel1.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JPanel fatherlistPanel = new JPanel();// zs1
		JPanel motherlistPanel = new JPanel();// zx3
		JPanel fatherparentlistPanel = new JPanel();
		JPanel motherparentlistPanel = new JPanel();
		fatherparentlistPanel.setPreferredSize(new Dimension(600, 180));
		motherparentlistPanel.setPreferredSize(new Dimension(600, 180));
		jpanel1.add(fatherlistPanel, BorderLayout.NORTH);
		jpanel2.add(fatherparentlistPanel, BorderLayout.NORTH);
		jpanel3.add(motherlistPanel, BorderLayout.NORTH);
		jpanel4.add(motherparentlistPanel, BorderLayout.NORTH);

		JLabel fatherlistlabel = new JLabel("Father:");
		JLabel fatherparentlistlabel = new JLabel("Father's Parents:");
		JLabel motherlistlabel = new JLabel("Mother:");
		JLabel motherparentlistlabel = new JLabel("Mother's Parents:");
		fatherlistPanel.add(fatherlistlabel);// z1

		motherlistPanel.add(motherlistlabel);//
		motherparentlistPanel.add(motherparentlistlabel);//

		JButton fatherlistbutton = new JButton("father");
		JButton motherlistbutton = new JButton("mother");
		fatherlistPanel.add(fatherlistbutton);// z1
		motherlistPanel.add(motherlistbutton);//

		JList fatherlist = new JList();
		String[] dd = { "XXX(name)" };
		fatherlist = new JList(dd);
		fatherlist.setVisibleRowCount(2);
		JScrollPane fatherlistscrollPane = new JScrollPane(fatherlist);
		fatherlistscrollPane.setPreferredSize(new Dimension(600, 180));
		fatherlistPanel.add(fatherlistscrollPane, BorderLayout.CENTER);

		fatherparentlistPanel.setLayout(new AfColumnLayout(10));
		JTextField ffatherfield = new JTextField("+ Click to add Father", 20);
		JTextField mfatherfield = new JTextField("+ Click to add Mother", 20);
		fatherparentlistPanel.add(fatherparentlistlabel);//
		fatherparentlistPanel.add(ffatherfield);
		fatherparentlistPanel.add(mfatherfield);

		JList motherlist = new JList();
		String[] dd1 = { "XXX(name)" };
		motherlist = new JList(dd1);
		motherlist.setVisibleRowCount(2);
		JScrollPane motherlistscrollPane = new JScrollPane(motherlist);
		motherlistscrollPane.setPreferredSize(new Dimension(600, 180));
		motherlistPanel.add(motherlistscrollPane, BorderLayout.CENTER);

		motherparentlistPanel.setLayout(new AfColumnLayout(10));
		JTextField fmotherfield = new JTextField("+ Click to add Father", 20);
		JTextField mmotherfield = new JTextField("+ Click to add Mother", 20);
		motherparentlistPanel.add(motherparentlistlabel);//
		motherparentlistPanel.add(fmotherfield);
		motherparentlistPanel.add(mmotherfield);

		// Family List (Lower part of the table)
		JPanel childlistPanel = new JPanel();

		JTable familytable = new JTable(familytableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane childlistscrollPane = new JScrollPane(familytable);
		childlistscrollPane.setPreferredSize(new Dimension(1250, 380));
		jpanel5.add(childlistPanel.add(childlistscrollPane));

		familytable.setFillsViewportHeight(true);
		familytable.setRowSelectionAllowed(true);
		familytable.setRowHeight(30);

		familytableModel.addColumn("Person ID");
		familytableModel.addColumn("First Name");
		familytableModel.addColumn("Last Name");
		familytableModel.addColumn("Sex");
		familytableModel.addColumn("Birth Date");
		familytableModel.addColumn("Death Date");
		familytableModel.addColumn("Home Address");

		familytable.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		familytable.getColumnModel().getColumn(0).setPreferredWidth(110);

	}

	public boolean exec() {

		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		this.setVisible(true);

		return retValue;
	}

}
