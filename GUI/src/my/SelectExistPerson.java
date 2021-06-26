package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//Add-Select Exis tPerson
public class SelectExistPerson extends JDialog {

	AfPanel root = new AfPanel();
	JPanel jpanel1 = new JPanel();
	JPanel jpanel2 = new JPanel();
	JPanel jpanel3 = new JPanel();
	MigLayout layout = new MigLayout();
	JTable table = null;
	DefaultTableModel tableModel = new DefaultTableModel();
	JTextField searchField = new JTextField();
	List<Person> dataList = new ArrayList<>();

	private boolean retValue = false;

	public SelectExistPerson(AddSpouse addSpouse) {
		super(addSpouse, "Select Exist Person", true);
		this.setSize(1000, 800);
		this.setContentPane(root);
		root.setLayout(layout);

		jpanel1.setPreferredSize(new Dimension(500, 800));
		jpanel2.setPreferredSize(new Dimension(500, 400));
		jpanel3.setPreferredSize(new Dimension(500, 400));

		root.add(jpanel1, "dock west");
		root.add(jpanel2, "wrap");
		root.add(jpanel3);

		jpanel1.setLayout(new BorderLayout());
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");
		JList informationlist = new JList();
		String[] dd = { "XXX(information)" };
		informationlist = new JList(dd);
		informationlist.setVisibleRowCount(2);
		JScrollPane informationlistscrollPane = new JScrollPane(informationlist);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		JLabel relevantlabel = new JLabel("Relevant personnel:");
		JList relevantlist = new JList();
		String[] dd1 = { "XXX(relevant)" };
		relevantlist = new JList(dd1);
		relevantlist.setVisibleRowCount(2);
		JScrollPane relevantlistscrollPane = new JScrollPane(relevantlist);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		initToolBar();

		initTable();

	}

	public SelectExistPerson(AddFather addFather) {
		super(addFather, "Select Exist Person", true);
		this.setSize(1000, 800);
		this.setContentPane(root);
		root.setLayout(layout);

		jpanel1.setPreferredSize(new Dimension(500, 800));
		jpanel2.setPreferredSize(new Dimension(500, 400));
		jpanel3.setPreferredSize(new Dimension(500, 400));

		root.add(jpanel1, "dock west");
		root.add(jpanel2, "wrap");
		root.add(jpanel3);

		jpanel1.setLayout(new BorderLayout());
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");
		JList informationlist = new JList();
		String[] dd = { "XXX(information)" };
		informationlist = new JList(dd);
		informationlist.setVisibleRowCount(2);
		JScrollPane informationlistscrollPane = new JScrollPane(informationlist);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		JLabel relevantlabel = new JLabel("Relevant personnel:");
		JList relevantlist = new JList();
		String[] dd1 = { "XXX(relevant)" };
		relevantlist = new JList(dd1);
		relevantlist.setVisibleRowCount(2);
		JScrollPane relevantlistscrollPane = new JScrollPane(relevantlist);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		initToolBar();

		initTable();

	}

	public SelectExistPerson(AddMother addMother) {
		super(addMother, "Select Exist Person", true);
		this.setSize(1000, 800);
		this.setContentPane(root);
		root.setLayout(layout);

		jpanel1.setPreferredSize(new Dimension(500, 800));
		jpanel2.setPreferredSize(new Dimension(500, 400));
		jpanel3.setPreferredSize(new Dimension(500, 400));

		root.add(jpanel1, "dock west");
		root.add(jpanel2, "wrap");
		root.add(jpanel3);

		jpanel1.setLayout(new BorderLayout());
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");
		JList informationlist = new JList();
		String[] dd = { "XXX(information)" };
		informationlist = new JList(dd);
		informationlist.setVisibleRowCount(2);
		JScrollPane informationlistscrollPane = new JScrollPane(informationlist);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		JLabel relevantlabel = new JLabel("Relevant personnel:");
		JList relevantlist = new JList();
		String[] dd1 = { "XXX(relevant)" };
		relevantlist = new JList(dd1);
		relevantlist.setVisibleRowCount(2);
		JScrollPane relevantlistscrollPane = new JScrollPane(relevantlist);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		initToolBar();

		initTable();

	}

	public SelectExistPerson(AddChild addChild) {
		super(addChild, "Select Exist Person", true);
		this.setSize(1000, 800);
		this.setContentPane(root);
		root.setLayout(layout);

		jpanel1.setPreferredSize(new Dimension(500, 800));
		jpanel2.setPreferredSize(new Dimension(500, 400));
		jpanel3.setPreferredSize(new Dimension(500, 400));

		root.add(jpanel1, "dock west");
		root.add(jpanel2, "wrap");
		root.add(jpanel3);

		jpanel1.setLayout(new BorderLayout());
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");
		JList informationlist = new JList();
		String[] dd = { "XXX(information)" };
		informationlist = new JList(dd);
		informationlist.setVisibleRowCount(2);
		JScrollPane informationlistscrollPane = new JScrollPane(informationlist);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		JLabel relevantlabel = new JLabel("Relevant personnel:");
		JList relevantlist = new JList();
		String[] dd1 = { "XXX(relevant)" };
		relevantlist = new JList(dd1);
		relevantlist.setVisibleRowCount(2);
		JScrollPane relevantlistscrollPane = new JScrollPane(relevantlist);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		initToolBar();

		initTable();

	}

	private void initToolBar() {
		JToolBar toolBar = new JToolBar();
		jpanel1.add(toolBar, BorderLayout.NORTH);

		toolBar.setFloatable(false);

		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {

			String condition = searchField.getText().trim();

		});
	}

	private void initTable() {

		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		jpanel1.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(475, 785));

		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(30);

		tableModel.addColumn("Person ID");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Birth Date");

		table.getColumnModel().getColumn(3).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
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

	private void addTableRow(Person item) {

		Vector<Object> rowData = new Vector<>();
		rowData.add(item.personid);
		rowData.add(item.firstname);
		rowData.add(item.lastname);
		rowData.add(item.sex);
		rowData.add(item.birthdate);
		rowData.add(item.deathdate);
		rowData.add(item.homeaddress);
		tableModel.addRow(rowData);
	}

}
