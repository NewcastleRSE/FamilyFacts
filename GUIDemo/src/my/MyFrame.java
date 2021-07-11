package my;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import af.common.json.AfJSON;
import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyFrame extends JFrame {

	JPanel root = new JPanel();
	JTable table = null;// Left table

	JPanel tablePanel = new JPanel();

	List<Person> dataList = new ArrayList<>();

	DefaultTableModel tableModel = new DefaultTableModel();

	JButton addButton, deleteButton, editButton, treeButton;
	JTextField searchField = new JTextField();

	// tabbedPane

	AfPanel tabbedPane = new AfPanel();

	// The upper left corner table of fathertable in tabbedPane
	JTable fathertable = new JTable();
	DefaultTableModel fathermodel = new DefaultTableModel();
	String[] fathercolumnNames = { "Facts", "Informations" };

	// Father's parent table right upper corner table in tabbedPane
	JTextField ffatherfield;
	JTextField mfatherfield;

	// The upper left corner table of mother's parent table in tabbedPane
	JTextField fmotherfield;
	JTextField mmotherfield;

	// The lower left corner table of mothertable in tabbedPane
	JTable mothertable = new JTable();;
	DefaultTableModel mothermodel = new DefaultTableModel();
	String[] mothercolumnNames = { "Facts", "Informations" };

	// The bottom table of childtable in tabbedPane
	DefaultTableModel familytableModel = new DefaultTableModel();

	// Parameters of familyList
	String ffirstnamefamilylist = null;
	String flastnamefamilylist = null;
	String fbirthfamilylist = null;
	String fdeathfamilylist = null;
	String faddressfamilylist = null;
	String ffatherfirstnamefamilylist = null;
	String mfatherfirstnamefamilylist = null;

	String mfirstnamefamilylist = null;
	String mlastnamefamilylist = null;
	String mbirthfamilylist = null;
	String mdeathfamilylist = null;
	String maddressfamilylist = null;
	String fmotherfirstnamefamilylist = null;
	String mmotherfirstnamefamilylist = null;

	// Parameters of familytree
	String personname = null;
	String fathername = null;
	String mothername = null;

	// Judge whether the interface is successful, pop-up window prompt
	String connect = null;

	public MyFrame(String title) {
		super(title);
		root.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Content Pane
		this.setContentPane(root);

		// JTextField textField = new JTextField();
		MigLayout ml = new MigLayout();
		root.setLayout(ml);
		root.add(tablePanel, "dock west");
		root.add(tabbedPane, "dock east");
		// tabbedPane.setPreferredSize(new Dimension(1300,925));
		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		tabbedPane.setBorder(BorderFactory.createTitledBorder("Family List"));

		MigLayout ml1 = new MigLayout();
		tablePanel.setLayout(ml1);

		// Add menu
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);

		// File
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		JMenuItem fileNewCmd = new JMenuItem("New");
		JMenuItem fileOpenCmd = new JMenuItem("Open");
		JMenuItem fileRenameCmd = new JMenuItem("Rename");
		JMenuItem fileDelectCmd = new JMenuItem("Delect");

		fileMenu.add(fileNewCmd);
		fileMenu.add(fileOpenCmd);
		fileMenu.add(fileRenameCmd);
		fileMenu.add(fileDelectCmd);

		JMenuItem fileExitCmd = new JMenuItem("Exit");
		fileMenu.addSeparator();
		fileMenu.add(fileExitCmd);

		fileNewCmd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onFileNew();
			}
		});

		fileOpenCmd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onFileOpen();
			}
		});
		fileRenameCmd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onFileRename();
			}
		});

		fileDelectCmd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onFileDelete();
			}
		});

		// Edit
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);

		JMenu editDelete = new JMenu("Delete");
		JMenu editUnlink = new JMenu("Unlink");
		// JMenuItem editSwap = new JMenuItem("Swap Husband and wife");

		// Submenu
		JMenuItem editDeletePerson = new JMenuItem("Person");
		JMenuItem editDeleteFamily = new JMenuItem("Family");
		JMenuItem editUnlinkFromParent = new JMenuItem("From Parent");
		JMenuItem editUnlinkFromSpouse = new JMenuItem("From Spouse");

		JMenuItem editUnlinkFromParentmother = new JMenuItem("From Mother");
		JMenuItem editUnlinkFromParentfather = new JMenuItem("From Father");

		editUnlinkFromParent.add(editUnlinkFromParentfather);
		editUnlinkFromParent.add(editUnlinkFromParentmother);

		editDelete.add(editDeletePerson);
		editDelete.add(editDeleteFamily);
		editUnlink.add(editUnlinkFromParent);
		editUnlink.add(editUnlinkFromSpouse);

		editMenu.add(editDelete);
		editMenu.add(editUnlink);
		// editMenu.add(editSwap);

		editDeletePerson.addActionListener((e) -> {

			int[] rows = table.getSelectedRows();
			if (rows.length == 0)
				return;

			// Pop up a dialog box to confirm
			int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "Ok",
					JOptionPane.YES_NO_OPTION);
			if (select != 0)
				return; // 0号按钮是'确定'按钮

			for (int i = rows.length - 1; i >= 0; i--) {
				int row = rows[i];
				String condition = (String) tableModel.getValueAt(row, 0);
				testhttpdeleteperson(condition);
			}

		});
		editUnlinkFromParentmother.addActionListener((e) -> {
			onUnlinkFromParentmother();
		});

		editUnlinkFromParentfather.addActionListener((e) -> {
			onUnlinkFromParentfather();
		});

		editUnlinkFromSpouse.addActionListener((e) -> {
			onUnlinkFromSpouse();
		});

		// List
		JMenu listMenu = new JMenu("List");
		menubar.add(listMenu);

		JMenuItem listAddress = new JMenuItem("Address List");
//		JMenuItem listRepository = new JMenuItem("Repository List");

		listMenu.add(listAddress);
//		listMenu.add(listRepository);

		listAddress.addActionListener((e) -> {
			onListAddress();

		});

//		listRepository.addActionListener((e) -> {
//			onListRepository();
//		});

		// Add
		JMenu addMenu = new JMenu("Add");
		menubar.add(addMenu);

		JMenuItem addIndividual = new JMenuItem("Individual");
		JMenuItem addSpouse = new JMenuItem("Spouse");
		JMenu addParents = new JMenu("Parents");
//		JMenuItem addChild = new JMenuItem("Child");

		JMenuItem addFather = new JMenuItem("Father");
		JMenuItem addMother = new JMenuItem("Mother");
		addParents.add(addFather);
		addParents.add(addMother);

		addIndividual.addActionListener((e) -> {
			onAddIndividual();
		});

		addSpouse.addActionListener((e) -> {
			onAddSpouse();
		});

		addFather.addActionListener((e) -> {
			onAddFather();

		});
		addMother.addActionListener((e) -> {

			onAddMother();

		});

		addMenu.add(addIndividual);
		addMenu.add(addSpouse);
		addMenu.add(addParents);

		// Search
		JMenu familyMenu = new JMenu("Family");
		menubar.add(familyMenu);
		// JMenuItem searchPersonList = new JMenuItem("Person List");
		JMenuItem FamilyTree = new JMenuItem("Family Tree");
		JMenuItem FamilyList = new JMenuItem("Family List");
		// searchMenu.add(searchPersonList);
		familyMenu.add(FamilyTree);
		familyMenu.add(FamilyList);

		FamilyTree.addActionListener((e) -> {

			onFamilyTree();
		});
		FamilyList.addActionListener((e) -> {

			onFamilyList();
		});

		// Menu event response
		fileExitCmd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Initialize the toolbar
		initToolBar();

		// Form initialization (left side of main page)
		initTable();

		// Load person list
		testhttp();

		// Create a familylist tab window
		initfamilylist();

		onFamilyList();

	}

	private void onFamilyList() {

		String fatherfirstname;
		String fatherlastname;
		String fatherbirth;
		String fatherdeath;
		String fatheraddress;

		String fatherfatherfirstname;
		String fathermotherfirstname;

		String motherfirstname;
		String motherlastname;
		String motherbirth;
		String motherdeath;
		String motheraddress;

		String motherfatherfirstname;
		String mothermotherfirstname;

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to view the List?", "Ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // Button 0 is the'OK' button

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);
			// TestCallHttpFamilyList tchfl = new TestCallHttpFamilyList();

			JSONObject fatherlistpersonvoObject = null;
			JSONObject fatherObject = null;
			JSONObject fatherfatherlistpersonvoObject = null;
			JSONObject fatherfatherObject = null;
			JSONObject fathermotherlistpersonvoObject = null;
			JSONObject fathermotherObject = null;

			JSONObject motherlistpersonvoObject = null;
			JSONObject motherObject = null;
			JSONObject motherfatherlistpersonvoObject = null;
			JSONObject motherfatherObject = null;
			JSONObject mothermotherlistpersonvoObject = null;
			JSONObject mothermotherObject = null;

			JSONObject familylist = TestCallHttpFamilyList.httpURLGETCase(personidfilter);// familylist=a(TestCallHttpFamilyList)personidfilter:id

			// Father

			System.out.println("--———————a中的father(选中的person的father)(字符串)++++++------");
			String r = familylist.getString("father");

			if (r.equals("null")) {
				System.out.println("！！！！！！！！！！！！");
				fatherfirstname = "null";
				fatherlastname = "null";
				fatherbirth = "null";
				fatherdeath = "null";
				fatheraddress = "null";
				fatherfatherfirstname = "null";
				fathermotherfirstname = "null";
				System.out.println("father" + r);
			} else {
				System.out.println("father" + r);
				fatherlistpersonvoObject = JSONObject.fromObject(r);
				String fatherpersonVO = fatherlistpersonvoObject.getString("personVO");
				System.out.println("father中的personVO(字符串)：" + fatherpersonVO);
				fatherObject = JSONObject.fromObject(fatherpersonVO);
				// String fatherfirstname = fatherObject.getString("firstName");
				fatherfirstname = fatherObject.getString("firstName");
				System.out.println("father firstname：" + fatherfirstname);
				// String fatherlastname = fatherObject.getString("lastName");
				fatherlastname = fatherObject.getString("lastName");
				System.out.println("father lastname：" + fatherlastname);
				// String fatherbirth = fatherObject.getString("birth");
				fatherbirth = fatherObject.getString("birth");
				System.out.println("father birth：" + fatherbirth);
				// String fatherdeath = fatherObject.getString("death");
				fatherdeath = fatherObject.getString("death");
				System.out.println("father death：" + fatherdeath);
				// String fatheraddress = fatherObject.getString("address");
				fatheraddress = fatherObject.getString("address");
				System.out.println("father address：" + fatheraddress);

				// Father's father
				String fatherfather = fatherlistpersonvoObject.getString("father");
				System.out.println(fatherfather);

				if (fatherfather.equals("null")) {
					System.out.println("------------");
					fatherfatherfirstname = "null";
				} else {

					System.out.println("father的father(字符串)：" + fatherfather);
					fatherfatherlistpersonvoObject = JSONObject.fromObject(fatherfather);
					String fatherfatherpersonVO = fatherfatherlistpersonvoObject.getString("personVO");
					System.out.println("father的father中的personVO(字符串)：" + fatherfatherpersonVO);
					fatherfatherObject = JSONObject.fromObject(fatherfatherpersonVO);
					// String fatherfatherfirstname = fatherfatherObject.getString("firstName");
					fatherfatherfirstname = fatherfatherObject.getString("firstName");
					System.out.println("father的father firstname：" + fatherfatherfirstname);

				}

				// Father's mother
				String fathermother = fatherlistpersonvoObject.getString("mother");
				System.out.println(fathermother);

				if (fatherfather.equals("null")) {
					System.out.println("------------");
					fathermotherfirstname = "null";
				} else {

					System.out.println("father的mother(字符串)：" + fathermother);
					fathermotherlistpersonvoObject = JSONObject.fromObject(fathermother);
					String fathermotherpersonVO = fathermotherlistpersonvoObject.getString("personVO");
					System.out.println("father的mother中的personVO(字符串)：" + fathermotherpersonVO);
					fathermotherObject = JSONObject.fromObject(fathermotherpersonVO);
					// String fathermotherfirstname = fathermotherObject.getString("firstName");
					fathermotherfirstname = fathermotherObject.getString("firstName");
					System.out.println("father的mother firstname：" + fathermotherfirstname);

				}

			}

			// mother

			System.out.println("--———————a中的mother(选中的person的mother)(字符串)++++++------");
			String rr = familylist.getString("mother");

			if (rr.equals("null")) {
				System.out.println("！！！！！！！！！！！！");
				motherfirstname = "null";
				motherlastname = "null";
				motherbirth = "null";
				motherdeath = "null";
				motheraddress = "null";
				motherfatherfirstname = "null";
				mothermotherfirstname = "null";
				System.out.println("mother" + r);
			} else {

				System.out.println("mother" + rr);

				motherlistpersonvoObject = JSONObject.fromObject(rr);
				String motherpersonVO = motherlistpersonvoObject.getString("personVO");
				System.out.println("mother中的personVO(字符串)：" + motherpersonVO);
				motherObject = JSONObject.fromObject(motherpersonVO);
				// String motherfirstname = motherObject.getString("firstName");
				motherfirstname = motherObject.getString("firstName");
				System.out.println("mother firstname：" + motherfirstname);
				// String motherlastname = motherObject.getString("lastName");
				motherlastname = motherObject.getString("lastName");
				System.out.println("mother lastname：" + motherlastname);
				// String motherbirth = motherObject.getString("birth");
				motherbirth = motherObject.getString("birth");
				System.out.println("mother birth：" + motherbirth);
				// String motherdeath = motherObject.getString("death");
				motherdeath = motherObject.getString("death");
				System.out.println("father death：" + motherdeath);
				// String motheraddress = motherObject.getString("address");
				motheraddress = motherObject.getString("address");
				System.out.println("mother address：" + motheraddress);

				// mother's father
				String motherfather = motherlistpersonvoObject.getString("father");
				System.out.println(motherfather);

				if (motherfather.equals("null")) {
					System.out.println("------------");
					motherfatherfirstname = "null";
				} else {

					System.out.println("mother的father(字符串)：" + motherfather);
					motherfatherlistpersonvoObject = JSONObject.fromObject(motherfather);
					String motherfatherpersonVO = motherfatherlistpersonvoObject.getString("personVO");
					System.out.println("mother的father中的personVO(字符串)：" + motherfatherpersonVO);
					motherfatherObject = JSONObject.fromObject(motherfatherpersonVO);
					// String fatherfatherfirstname = fatherfatherObject.getString("firstName");
					motherfatherfirstname = motherfatherObject.getString("firstName");
					System.out.println("mother的father firstname：" + motherfatherfirstname);

				}

				// mother's mother
				String mothermother = fatherlistpersonvoObject.getString("mother");
				System.out.println(mothermother);

				if (mothermother.equals("null")) {
					System.out.println("------------");
					mothermotherfirstname = "null";
				} else {

					System.out.println("mother的mother(字符串)：" + mothermother);
					mothermotherlistpersonvoObject = JSONObject.fromObject(mothermother);
					String mothermotherpersonVO = mothermotherlistpersonvoObject.getString("personVO");
					System.out.println("mother的mother中的personVO(字符串)：" + mothermotherpersonVO);
					mothermotherObject = JSONObject.fromObject(mothermotherpersonVO);
					// String fathermotherfirstname = fathermotherObject.getString("firstName");
					mothermotherfirstname = mothermotherObject.getString("firstName");
					System.out.println("mother的mother firstname：" + mothermotherfirstname);

				}

			}

			System.out.println("--———————a中的children(选中的person家庭的所有孩子)(字符串)++++++------");
			String rrr = familylist.getString("children");
			System.out.println(rrr);

			JSONArray arraychildren = null;
			if (rrr.length() != 0) {
				AfJSON afJson = new AfJSON();
				arraychildren = afJson.jsonToArray(rrr);

			}
			familytableModel.setRowCount(0);// 清空
			if (!arraychildren.isEmpty()) {
				for (int j = 0; j < arraychildren.size(); j++) {
					JSONObject jsonObject = arraychildren.getJSONObject(j);
					Person person = new Person();
					person.personid = Integer.toString(jsonObject.getInt("personId"));
					person.firstname = jsonObject.getString("firstName");
					person.lastname = jsonObject.getString("lastName");
					String str = jsonObject.getString("sex");
					person.sex = "male".equals(str) ? true : false;
					person.birthdate = Integer.toString(jsonObject.getInt("birth"));
					person.deathdate = Integer.toString(jsonObject.getInt("death"));
					person.homeaddress = jsonObject.getString("address");

					addTableRowFamilyList(person);
				}
			}

			ffirstnamefamilylist = fatherfirstname;
			flastnamefamilylist = fatherlastname;
			fbirthfamilylist = fatherbirth;
			fdeathfamilylist = fatherdeath;
			faddressfamilylist = fatheraddress;

			ffatherfirstnamefamilylist = fatherfatherfirstname;// father's father firstname
			mfatherfirstnamefamilylist = fathermotherfirstname;// father's mother firstname

			mfirstnamefamilylist = motherfirstname;
			mlastnamefamilylist = motherlastname;
			mbirthfamilylist = motherbirth;
			mdeathfamilylist = motherdeath;
			maddressfamilylist = motheraddress;

			fmotherfirstnamefamilylist = motherfatherfirstname;// mother's father firstname
			mmotherfirstnamefamilylist = mothermotherfirstname;// mother's mother firstname

		}
		Test t = new Test();
		t.test1(ffatherfirstnamefamilylist);
		ffatherfield.setText(ffatherfirstnamefamilylist);

		t.test1(mfatherfirstnamefamilylist);
		mfatherfield.setText(mfatherfirstnamefamilylist);

		t.test1(fmotherfirstnamefamilylist);
		fmotherfield.setText(fmotherfirstnamefamilylist);

		t.test1(mfatherfirstnamefamilylist);
		mmotherfield.setText(mfatherfirstnamefamilylist);

		t.ttest(ffirstnamefamilylist, flastnamefamilylist, fbirthfamilylist, fdeathfamilylist, faddressfamilylist);
		Object[][] fatherdata = { { "First name", ffirstnamefamilylist }, { "Last name", flastnamefamilylist },
				{ "Birth", fbirthfamilylist }, { "death", fdeathfamilylist }, { "Address", faddressfamilylist } };

		fathermodel.setDataVector(fatherdata, fathercolumnNames);
		fathertable.setModel(fathermodel);
		fathertable.setFillsViewportHeight(true);
		fathertable.setRowSelectionAllowed(true);
		fathertable.setRowHeight(30);

		t.ttest(mfirstnamefamilylist, mlastnamefamilylist, mbirthfamilylist, mdeathfamilylist, maddressfamilylist);
		Object[][] motherdata = { { "First name", mfirstnamefamilylist }, { "Last name", mlastnamefamilylist },
				{ "Birth", mbirthfamilylist }, { "death", mdeathfamilylist }, { "Address", maddressfamilylist } };

		mothermodel.setDataVector(motherdata, mothercolumnNames);
		mothertable.setModel(mothermodel);
		mothertable.setFillsViewportHeight(true);
		mothertable.setRowSelectionAllowed(true);
		mothertable.setRowHeight(30);
	}

	private void initfamilylist() {

		JPanel jpanel1 = new JPanel();
		JPanel jpanel2 = new JPanel();
		JPanel jpanel3 = new JPanel();
		JPanel jpanel4 = new JPanel();
		JPanel jpanel5 = new JPanel();
		MigLayout layout = new MigLayout();

		tabbedPane.setLayout(layout);

		jpanel1.setPreferredSize(new Dimension(650, 300));
		jpanel2.setPreferredSize(new Dimension(650, 300));
		jpanel3.setPreferredSize(new Dimension(650, 300));
		jpanel4.setPreferredSize(new Dimension(650, 300));
		jpanel5.setPreferredSize(new Dimension(1300, 500));

		tabbedPane.add(jpanel1);
		tabbedPane.add(jpanel2, "wrap");
		tabbedPane.add(jpanel3);
		tabbedPane.add(jpanel4, "wrap");
		tabbedPane.add(jpanel5, "dock south");

		jpanel1.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JPanel fatherlistPanel = new JPanel();// zs1
		JPanel motherlistPanel = new JPanel();// zx3
		JPanel fatherparentlistPanel = new JPanel();
		JPanel motherparentlistPanel = new JPanel();
		fatherparentlistPanel.setPreferredSize(new Dimension(630, 280));
		motherparentlistPanel.setPreferredSize(new Dimension(630, 280));
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

//	JTable fathertable= new JTable();;
//	DefaultTableModel fathermodel= new DefaultTableModel();
		JScrollPane fatherlistscrollPane = new JScrollPane(fathertable);
		fatherlistscrollPane.setPreferredSize(new Dimension(630, 230));
		fatherlistPanel.add(fatherlistscrollPane, BorderLayout.CENTER);

//	String[] fathercolumnNames = {"Facts", "Informations"};
		Object[][] fatherdata = { { "First name", ffirstnamefamilylist }, { "Last name", flastnamefamilylist },
				{ "Birth", fbirthfamilylist }, { "death", fdeathfamilylist }, { "Address", faddressfamilylist } };

		fathermodel.setDataVector(fatherdata, fathercolumnNames);
		fathertable.setModel(fathermodel);
		fathertable.setFillsViewportHeight(true);
		fathertable.setRowSelectionAllowed(true);
		fathertable.setRowHeight(30);

		fatherparentlistPanel.setLayout(new AfColumnLayout(10));
		ffatherfield = new JTextField("+ Click to add Father", 20);
		mfatherfield = new JTextField("+ Click to add Mother", 20);
		fatherparentlistPanel.add(fatherparentlistlabel);//
		fatherparentlistPanel.add(ffatherfield);
		fatherparentlistPanel.add(mfatherfield);

//	JTable mothertable= new JTable();;
//	DefaultTableModel mothermodel= new DefaultTableModel();
		JScrollPane motherlistscrollPane = new JScrollPane(mothertable);
		motherlistscrollPane.setPreferredSize(new Dimension(630, 230));
		motherlistPanel.add(motherlistscrollPane, BorderLayout.CENTER);
//	String[] mothercolumnNames = {"Facts", "Informations"};
		Object[][] motherdata = { { "First name", mfirstnamefamilylist }, { "Last name", mlastnamefamilylist },
				{ "Birth", mbirthfamilylist }, { "death", mdeathfamilylist }, { "Address", maddressfamilylist } };
		mothermodel.setDataVector(motherdata, mothercolumnNames);
		mothertable.setModel(mothermodel);
		mothertable.setFillsViewportHeight(true);
		mothertable.setRowSelectionAllowed(true); // 整行选择
		mothertable.setRowHeight(30);

		motherparentlistPanel.setLayout(new AfColumnLayout(10));
		fmotherfield = new JTextField("+ Click to add Father", 20);
		mmotherfield = new JTextField("+ Click to add Mother", 20);
		motherparentlistPanel.add(motherparentlistlabel);//
		motherparentlistPanel.add(fmotherfield);
		motherparentlistPanel.add(mmotherfield);

		// The lower part of the Family List
		JPanel childlistPanel = new JPanel();
		JTable familytable = new JTable(familytableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane childlistscrollPane = new JScrollPane(familytable);
		childlistscrollPane.setPreferredSize(new Dimension(1270, 420));
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
		familytable.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度

	}

	private void addTableRowFamilyList(Person item) {

		Vector<Object> rowData = new Vector<>();
		rowData.add(item.personid);
		rowData.add(item.firstname);
		rowData.add(item.lastname);
		rowData.add(item.sex);
		rowData.add(item.birthdate);
		rowData.add(item.deathdate);
		rowData.add(item.homeaddress);

		familytableModel.addRow(rowData);
	}

//	public void paint(Graphics g) {
//		super.paint(g);
//		//画连接线,x1 y1 x2 y2分别代表线的两端点 左上角为0,0点,往右逐渐x增加,往下逐渐y增加
//		//final static BasicStroke wideStroke = new BasicStroke(8.0f);
//		final BasicStroke wideStroke = new BasicStroke(2.0f);
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setStroke(wideStroke);
//		//1
//		g2.drawLine(580, 280, 580, 500);
//		g2.drawLine(580, 280, 670, 280);
//		
//		g2.drawLine(580, 540, 580, 760);
//		g2.drawLine(580, 760, 670, 760);
//		//2
//		g2.drawLine(775, 160, 865, 160);
//		g2.drawLine(775, 160, 775, 260);
//		
//		g2.drawLine(775, 395, 865, 395);
//		g2.drawLine(775, 395, 775, 295);
//		
//		g2.drawLine(775, 640, 865, 640);
//		g2.drawLine(775, 640, 775, 740);
//
//		g2.drawLine(775, 880, 865, 880);
//		g2.drawLine(775, 880, 775, 780);
//		//3
//		g2.drawLine(970, 100, 1060, 100);
//		g2.drawLine(970, 100, 970, 140);
//
//		g2.drawLine(970, 175, 970, 215);
//		g2.drawLine(970, 215, 1060, 215);
//		
//		
//		g2.drawLine(970, 340, 1060, 340);
//		g2.drawLine(970, 340, 970, 380);
//		
//		g2.drawLine(970, 415, 970, 455);
//		g2.drawLine(970, 455, 1060, 455);
//		
//		g2.drawLine(970, 580, 1060, 580);
//		g2.drawLine(970, 580, 970, 620);
//
//		g2.drawLine(970, 655, 970, 695);
//		g2.drawLine(970, 695, 1060, 695);
//		
//		g2.drawLine(970, 820, 1060, 820);
//		g2.drawLine(970, 820, 970, 860);
//		
//		g2.drawLine(970, 895, 970, 935);
//		g2.drawLine(970, 935, 1060, 935);
////4
//		g2.drawLine(1165, 68, 1255, 68);
//		g2.drawLine(1165, 68, 1165, 85);
//
//		g2.drawLine(1165, 110, 1165, 130);
//		g2.drawLine(1165, 130, 1255, 130);
//
//		g2.drawLine(1165, 188, 1255, 188);
//		g2.drawLine(1165, 188, 1165, 205);
//
//		g2.drawLine(1165, 230, 1165, 250);
//		g2.drawLine(1165, 250, 1255, 250);
//
//		g2.drawLine(1165, 308, 1255, 308);
//		g2.drawLine(1165, 308, 1165, 325);
//
//		g2.drawLine(1165, 350, 1165, 370);
//		g2.drawLine(1165, 370, 1255, 370);
//
//		g2.drawLine(1165, 428, 1255, 428);
//		g2.drawLine(1165, 428, 1165, 445);
//
//		g2.drawLine(1165, 470, 1165, 490);
//		g2.drawLine(1165, 490, 1255, 490);
//		
//		g2.drawLine(1165, 548, 1255, 548);
//		g2.drawLine(1165, 548, 1165, 564);
//
//		g2.drawLine(1165, 590, 1165, 610);
//		g2.drawLine(1165, 610, 1255, 610);
//		
//		g2.drawLine(1165, 668, 1255, 668);
//		g2.drawLine(1165, 668, 1165, 685);
//
//		g2.drawLine(1165, 710, 1165, 730);
//		g2.drawLine(1165, 730, 1255, 730);
////
//		g2.drawLine(1165, 788, 1255, 788);
//		g2.drawLine(1165, 788, 1165, 804);
//
//		g2.drawLine(1165, 830, 1165, 850);
//		g2.drawLine(1165, 850, 1255, 850);
//		
//		g2.drawLine(1165, 908, 1255, 908);
//		g2.drawLine(1165, 908, 1165, 925);
//
//		g2.drawLine(1165, 950, 1165, 970);
//		g2.drawLine(1165, 970, 1255, 970);
//		
//	}	

	public void initTable() {

		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane);// ml1 miglagout
		scrollPane.setPreferredSize(new Dimension(500, 925));

		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true); // 整行选择
		table.setRowHeight(30);

		tableModel.addColumn("Person ID");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Sex");
		tableModel.addColumn("Birth Date");
		tableModel.addColumn("Death Date");
		tableModel.addColumn("Home Address");

		table.getColumnModel().getColumn(3).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度
	}

	private void initToolBar() {
		JToolBar toolBar = new JToolBar();

		tablePanel.add(toolBar, "dock north");// ml1 miglagout

		toolBar.setFloatable(false);

		// Button

		addButton = createToolButton("Add", "ic_add.png");
		toolBar.add(addButton);
		addButton.addActionListener((e) -> {
			onAddIndividual();

		});

		deleteButton = createToolButton("Delete", "ic_delete.png");
		toolBar.add(deleteButton);
		deleteButton.addActionListener((e) -> {

			int[] rows = table.getSelectedRows();
			if (rows.length == 0)
				return;

			int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "ok",
					JOptionPane.YES_NO_OPTION);
			if (select != 0)
				return;

			for (int i = rows.length - 1; i >= 0; i--) {
				int row = rows[i];

				String condition = (String) tableModel.getValueAt(row, 0);
				testhttpdeleteperson(condition);
			}
		});

		editButton = createToolButton("Edit", "ic_edit.png");
		toolBar.add(editButton);
		editButton.addActionListener((e) -> {
			// onEdit();
		});

		// Search
		toolBar.addSeparator(new Dimension(40, 10));

		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {
			// Trigger an event when you press enter

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");// Replace all numeric characters with empty
			if (str1.length() == 0) {

				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);
				// 逗号分隔，逗号前是firstname，后是lastname
				// Separate by comma, firstname before comma, lastname after comma
				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {
						// 当成数组的写法 As an array
						String str = tt[i];
						// 判断是否存在空格
						// Determine whether there are spaces
						if (str.indexOf(" ") != -1) {
							System.out.println("存在空格!");
							// 空格替换为%20
							// Replace spaces with %20
							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("bu存在空格");
							first_name = str;
						}

//Gustav Carl Diederich,Henning

						// first_name=str;
					} else {
						String str = tt[i]; // 当成数组的写法 As an array
						last_name = str;
					}

				}
				System.out.println(first_name + "-----------" + last_name);// 文本框中的值是int类型
				System.out.println("qqqqqqqqqqqqqqqqqqqqqq");
				testsearchfullnamehttp(first_name, last_name);

			}

		});
	}

	protected JButton createToolButton(String text, String icon) {
		// 图标 icon
		String imagePath = "/icons/" + icon;
		URL imageURL = getClass().getResource(imagePath);

		// 创建按钮 Create button
		JButton button = new JButton(text);
		// button.setActionCommand(action);
		button.setToolTipText(text);
		button.setIcon(new ImageIcon(imageURL));
		button.setFocusPainted(false);
		return button;
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

		tableModel.addRow(rowData); // 添加一行
	}

	private void onAddIndividual() {
		AddIndividual ai = new AddIndividual(this);
		if (ai.exec()) {
			Person person = ai.getValue();
			String firstname = person.firstname;
			String lastname = person.lastname;
			boolean sex1 = person.sex;
			String sex = String.valueOf(sex1);
			System.out.println("sex:" + sex);
			String birth = person.birthdate;
			String death = person.deathdate;
			String address = person.homeaddress;
			ai.testhttpaddperson(firstname, lastname, sex, birth, death, address);
		}
		tableModel.setRowCount(0);// 清空
		testhttp();

	}

	private void onAddFather() {
		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add father?", "ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id
			AddFather.personid = personidfilter;

		}
		AddFather af = new AddFather(this);
		if (af.exec()) {

		}
	}

	private void onAddMother() {
		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add mum?", "ok", JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id
			AddMother.personid = personidfilter;

		}
		AddMother am = new AddMother(this);
		if (am.exec()) {

		}
	}

	private void onAddSpouse() {

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add a spouse?", "ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id
			AddSpouse.personid = personidfilter;

		}
		AddSpouse as = new AddSpouse(this);
		if (as.exec()) {

		}
	}

	private void oneditperson() {
		EditPerson ep = new EditPerson(this);
		if (ep.exec()) {

		}
	}

	private void onListAddress() {
		ListAddress la = new ListAddress(this);
		if (la.exec()) {

		}
	}

	private void onFileOpen() {
		FileOpen fileopen = new FileOpen(this);
		if (fileopen.exec()) {

		}

	}

	private void onFileNew() {
		FileNew filenew = new FileNew(this);
		if (filenew.exec()) {

		}

	}

	private void onFileRename() {
		FileRename fr = new FileRename(this);
		if (fr.exec()) {

		}

	}

	private void onFileDelete() {
		FileDelete fd = new FileDelete(this);
		if (fd.exec()) {

		}

	}

	private void onUnlinkFromParentfather() {

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete fahter?", "ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id
			EditUnlinkFromParentFather.personid = personidfilter;

		}

		EditUnlinkFromParentFather eufpf = new EditUnlinkFromParentFather(this);
		if (eufpf.exec()) {

		}
	}

	private void onUnlinkFromParentmother() {

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete mother?", "ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id
			EditUnlinkFromParentMother.personid = personidfilter;

		}

		EditUnlinkFromParentMother eufpm = new EditUnlinkFromParentMother(this);
		if (eufpm.exec()) {

		}
	}

	private void onUnlinkFromSpouse() {

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete spouse?", "ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id
			EditUnlinkFromSpouse.personid = personidfilter;

		}

		EditUnlinkFromSpouse eufs = new EditUnlinkFromSpouse(this);
		if (eufs.exec()) {

		}
	}

	// 显示全部Person数据 Display all Person data
	public void testhttp() {
		TestCallHttpList tchpl = new TestCallHttpList();
		// 获取服务器响应结果 Get server response result
		JSONArray array = tchpl.httpURLGETCase("http://3.9.172.108:8090/api/person/list");
		// 显示返回的person列表信息 Display the returned person list information
		if (!array.isEmpty()) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				Person person = new Person();
				person.personid = Integer.toString(jsonObject.getInt("personId"));
				person.firstname = jsonObject.getString("firstName");
				person.lastname = jsonObject.getString("lastName");
				String str = jsonObject.getString("sex");
				person.sex = "male".equals(str) ? true : false;
				person.birthdate = Integer.toString(jsonObject.getInt("birth"));
				person.deathdate = Integer.toString(jsonObject.getInt("death"));
				person.homeaddress = jsonObject.getString("address");

				addTableRow(person);
			}
		}
	}

	// 查找person（按照id） Find person (according to id)
	public void testsearchhttp(String filter) {

		// 过滤条件为空 Filter condition is empty
		if (filter.length() == 0) {

			tableModel.setRowCount(0);// Empty
			TestCallHttpList tchpl = new TestCallHttpList();

			JSONArray array = tchpl.httpURLGETCase("http://3.9.172.108:8090/api/person/list");

			if (!array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject = array.getJSONObject(i);
					Person person = new Person();
					person.personid = Integer.toString(jsonObject.getInt("personId"));
					person.firstname = jsonObject.getString("firstName");
					person.lastname = jsonObject.getString("lastName");
					String str = jsonObject.getString("sex");
					person.sex = "male".equals(str) ? true : false;
					person.birthdate = Integer.toString(jsonObject.getInt("birth"));
					person.deathdate = Integer.toString(jsonObject.getInt("death"));
					person.homeaddress = jsonObject.getString("address");

					addTableRow(person);
				}
			}

		} else {
			tableModel.setRowCount(0);// 清空
			TestCallHttpPersonSearchID tchps = new TestCallHttpPersonSearchID();
			JSONObject jo = tchps.httpURLGETCase(filter);
			if (!jo.isEmpty()) {

				Person person = new Person();
				person.personid = Integer.toString(jo.getInt("personId"));
				person.firstname = jo.getString("firstName");
				person.lastname = jo.getString("lastName");
				String str = jo.getString("sex");
				person.sex = "male".equals(str) ? true : false;
				person.birthdate = Integer.toString(jo.getInt("birth"));
				person.deathdate = Integer.toString(jo.getInt("death"));
				person.homeaddress = jo.getString("address");

				addTableRow(person);
			}
		}

	}

	// 查找person（按照fullname） Find person (according to fullname)
	public void testsearchfullnamehttp(String first_name, String last_name) {
		// 过滤条件为空
		if (first_name.length() == 0 && last_name.length() == 0) {
			// 显示接口所有数据
			tableModel.setRowCount(0);// 清空
			TestCallHttpList tchpl = new TestCallHttpList();
			// 获取服务器响应结果
			JSONArray array = tchpl.httpURLGETCase("http://3.9.172.108:8090/api/person/list");
			// 显示返回的person列表信息
			if (!array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject = array.getJSONObject(i);
					Person person = new Person();
					person.personid = Integer.toString(jsonObject.getInt("personId"));
					person.firstname = jsonObject.getString("firstName");
					person.lastname = jsonObject.getString("lastName");
					String str = jsonObject.getString("sex");
					person.sex = "male".equals(str) ? true : false;
					person.birthdate = Integer.toString(jsonObject.getInt("birth"));
					person.deathdate = Integer.toString(jsonObject.getInt("death"));
					person.homeaddress = jsonObject.getString("address");
					// 在表格中添加person
					addTableRow(person);
				}
			}
		} else {
			tableModel.setRowCount(0);// 清空
			TestCallHttpPersonSearchFullName tchpsfn = new TestCallHttpPersonSearchFullName();
			JSONArray array = tchpsfn.httpURLGETCase(first_name, last_name);
			System.out.println("qqqqqq4444444444444444444qqqqqq");
			if (!array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject = array.getJSONObject(i);
					Person person = new Person();
					person.personid = Integer.toString(jsonObject.getInt("personId"));
					person.firstname = jsonObject.getString("firstName");
					person.lastname = jsonObject.getString("lastName");
					String str = jsonObject.getString("sex");
					person.sex = "male".equals(str) ? true : false;
					person.birthdate = Integer.toString(jsonObject.getInt("birth"));
					person.deathdate = Integer.toString(jsonObject.getInt("death"));
					person.homeaddress = jsonObject.getString("address");
					// 在表格中添加person
					addTableRow(person);
				}
			}

		}

	}

	// 删除接口 delete person

	public void testhttpdeleteperson(String filter) {
		// TODO Auto-generated method stub

		TestCallHttpPersonDelect tchpd = new TestCallHttpPersonDelect();
		tchpd.httpURLGETCase(filter);
		tableModel.setRowCount(0);// 清空
		testhttp();
		JOptionPane.showMessageDialog(null, "successfully deleted！", "prompt", JOptionPane.PLAIN_MESSAGE);
	}

	private void onFamilyTree() {

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to view the tree?", "ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);
			TestCallHttpFamilytree tchft = new TestCallHttpFamilytree();
			JSONObject aperson = null;
			JSONObject afatherpersonvo = null;
			JSONObject afather = null;
			JSONObject amotherpersonvo = null;
			JSONObject amother = null;
			JSONObject familytree = TestCallHttpFamilytree.httpURLGETCase(personidfilter);// familytree=a(TestCallHttpFamilytree)personidfilter:id

			System.out.println("--———————a中的personVO(选中的person)(字符串)++++++------");
			String r = familytree.getString("personVO");
			System.out.println(r);
			aperson = JSONObject.fromObject(r);
			String personfirstname = aperson.getString("firstName");
			System.out.println("选中的person firstname:" + personfirstname);

			System.out.println("------a中的father(字符串)----");
			String rr = familytree.getString("father");
			System.out.println(rr);
			afatherpersonvo = JSONObject.fromObject(rr);
			String fatherpersonVO = afatherpersonvo.getString("personVO");
			System.out.println("father中的personVO(字符串)：" + fatherpersonVO);
			afather = JSONObject.fromObject(fatherpersonVO);
			String fatherfirstname = afather.getString("firstName");
			System.out.println("father firstname：" + fatherfirstname);

			System.out.println("-----a中的mother(字符串)+++++==----");
			String rrr = familytree.getString("mother");
			System.out.println(rrr);
			amotherpersonvo = JSONObject.fromObject(rrr);
			String motherpersonVO = amotherpersonvo.getString("personVO");
			System.out.println("mother中的personVO(字符串)：" + motherpersonVO);
			amother = JSONObject.fromObject(motherpersonVO);
			String motherfirstname = amother.getString("firstName");
			System.out.println("mother firstname：" + motherfirstname);

			System.out.println("+以上不该动++++++");

			personname = personfirstname;
			fathername = fatherfirstname;
			mothername = motherfirstname;
			System.out.println("personname:" + personname);
			System.out.println("++++++++++++++++++++++++++++++++++");

		}
		FamilyTree ft = new FamilyTree(this);
		System.out.println("++++++++——————————————————————————————————");
		ft.parent.setText(personname);
		ft.textField1.setText(fathername);
		ft.textField2.setText(mothername);

		if (ft.exec()) {

		}

	}

}