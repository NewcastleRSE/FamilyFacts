package my;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smx.common.json.AfJSON;
import smx.swing.AfPanel;
import smx.swing.layout.AfColumnLayout;

public class MyFrame extends JFrame {

	JPanel root = new JPanel();
	// left table
	// 左侧表格
	JTable table = null;

	JPanel tablePanel = new JPanel();

	// dataList: maintain all records, tableModel: records to be displayed
	// dataList: 维护所有记录 , tableModel: 要显示出来的记录
	List<Person> dataList = new ArrayList<>();

	DefaultTableModel tableModel = new DefaultTableModel();

	JButton addButton, deleteButton, editButton, treeButton;
	JTextField searchField = new JTextField();

	// 选项卡tabbedPane
	AfPanel tabbedPane = new AfPanel();

	// The upper left corner table of the fathertable in the tabbedPane
	// 选项卡tabbedPane中fathertable 左上角表格
	JTable fathertable = new JTable();
	DefaultTableModel fathermodel = new DefaultTableModel();
	String[] fathercolumnNames = { "Facts", "Informations" };

	// Father's parent table right upper corner table in tabbedPane
	// 选项卡tabbedPane中father's parent table right上角表格
	JTextField ffatherfield;
	JTextField mfatherfield;

	// mother's parent table left upper corner table in tabbedPane
	// 选项卡tabbedPane中mother's parent table 左上角表格
	JTextField fmotherfield;
	JTextField mmotherfield;

	// The lower left corner table of mothertable in tabbedPane
	// 选项卡tabbedPane中mothertable 左下角表格
	JTable mothertable = new JTable();;
	DefaultTableModel mothermodel = new DefaultTableModel();
	String[] mothercolumnNames = { "Facts", "Informations" };

	// The bottom table of childtable in tabbedPane
	// 选项卡tabbedPane中childtable 最下表格
	DefaultTableModel familytableModel = new DefaultTableModel();

	JTextField childfield;

	// Parameters of familyList
	// familyList的参数
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

	// parameters of familytree
	// familytree的参数
	String personname = null;
	String fathername = null;
	String fatherfathername = null;
	String fffathername = null;
	String ffffathername = null;
	String fffmothername = null;
	String ffmothername = null;
	String ffmfathername = null;
	String ffmmothername = null;
	String fathermothername = null;
	String fmfathername = null;
	String fmffathername = null;
	String fmfmothername = null;
	String fmmothername = null;
	String fmmfathername = null;
	String fmmmothername = null;

	String mothername = null;
	String motherfathername = null;
	String mffathername = null;
	String mfffathername = null;
	String mffmothername = null;
	String mfmothername = null;
	String mfmfathername = null;
	String mfmmothername = null;
	String mothermothername = null;
	String mmfathername = null;
	String mmffathername = null;
	String mmfmothername = null;
	String mmmothername = null;
	String mmmfathername = null;
	String mmmmothername = null;

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

		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		tabbedPane.setBorder(BorderFactory.createTitledBorder("Family List"));

		MigLayout ml1 = new MigLayout();
		tablePanel.setLayout(ml1);

		// add menu
		// 添加菜单
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);

		// menu File
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

		// menu Edit
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);

		JMenuItem editDeletePerson = new JMenuItem("Delete Person");// Sub-menu exists
		JMenu editUnlink = new JMenu("Unlink");

		JMenu editUnlinkFromParent = new JMenu("From Parent");
		JMenuItem editUnlinkFromSpouse = new JMenuItem("From Spouse");

		JMenuItem editUnlinkFromParentmother = new JMenuItem("From Mother");
		JMenuItem editUnlinkFromParentfather = new JMenuItem("From Father");

		editUnlinkFromParent.add(editUnlinkFromParentfather);
		editUnlinkFromParent.add(editUnlinkFromParentmother);

		editUnlink.add(editUnlinkFromParent);
		editUnlink.add(editUnlinkFromSpouse);

		editMenu.add(editDeletePerson);
		editMenu.add(editUnlink);

		editDeletePerson.addActionListener((e) -> {

			int[] rows = table.getSelectedRows();
			if (rows.length == 0)
				return;

			// Pop-up dialog to confirm
			// 弹出对话框确认
			int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "OK",
					JOptionPane.YES_NO_OPTION);
			if (select != 0)
				// Button 0 is the'OK' button
				// 0号按钮是'确定'按钮
				return;

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

		// Menu List
		JMenu listMenu = new JMenu("List");
		menubar.add(listMenu);

		JMenuItem listAddress = new JMenuItem("Address List");

		listMenu.add(listAddress);

		listAddress.addActionListener((e) -> {
			onListAddress();

		});

		// Menu Add 菜单
		JMenu addMenu = new JMenu("Add");
		menubar.add(addMenu);

		JMenuItem addIndividual = new JMenuItem("Individual");
		JMenuItem addSpouse = new JMenuItem("Spouse");
		JMenu addParents = new JMenu("Parents");

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

		// Search menu
		JMenu familyMenu = new JMenu("Family");
		menubar.add(familyMenu);

		JMenuItem FamilyTree = new JMenuItem("Family Tree");
		JMenuItem FamilyList = new JMenuItem("Family List");

		familyMenu.add(FamilyTree);
		familyMenu.add(FamilyList);

		FamilyTree.addActionListener((e) -> {

			onFamilyTree();
		});
		FamilyList.addActionListener((e) -> {

			onFamilyList();
		});

		// 菜单事件响应
		fileExitCmd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Initialize the toolbar
		// 初始化工具栏
		initToolBar();

		// Form initialization (left side of main page)
		// 表格初始化（主页面左侧）
		initTable();

		// Load file
		// 加载文件
		testhttp();

		// Create a familylist tab window
		// 创建familylist选项卡窗口
		initfamilylist();

		onFamilyList();

		// Mouse double click event: display familylist
		// 鼠标双击事件：显示家庭列表
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				if (clickCount == 2) {
					System.out.println("clickCount2");
					onFamilyList();
					System.out.println("-——++++++++-");
				}
			}
		});

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

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to view the List?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);

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

				fatherfirstname = fatherObject.getString("firstName");
				System.out.println("father firstname：" + fatherfirstname);

				fatherlastname = fatherObject.getString("lastName");
				System.out.println("father lastname：" + fatherlastname);

				fatherbirth = fatherObject.getString("birth");
				System.out.println("father birth：" + fatherbirth);

				fatherdeath = fatherObject.getString("death");
				System.out.println("father death：" + fatherdeath);

				fatheraddress = fatherObject.getString("address");
				System.out.println("father address：" + fatheraddress);

				// father's father
				// 父亲的父亲
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

					fatherfatherfirstname = fatherfatherObject.getString("firstName");
					System.out.println("father的father firstname：" + fatherfatherfirstname);

				}

				// father's mother
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
				motherfirstname = motherObject.getString("firstName");
				System.out.println("mother firstname：" + motherfirstname);
				motherlastname = motherObject.getString("lastName");
				System.out.println("mother lastname：" + motherlastname);
				motherbirth = motherObject.getString("birth");
				System.out.println("mother birth：" + motherbirth);
				motherdeath = motherObject.getString("death");
				System.out.println("father death：" + motherdeath);
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
					mothermotherfirstname = mothermotherObject.getString("firstName");
					System.out.println("mother的mother firstname：" + mothermotherfirstname);

				}

			}

			// children
			System.out.println("--———————a中的children(选中的person家庭的所有孩子)(字符串)++++++------");
			String rrr = familylist.getString("children");
			System.out.println(rrr);

			if (rrr.equals("null")) {
				JOptionPane.showMessageDialog(null, "The person has no children!", "prompt", JOptionPane.PLAIN_MESSAGE);
				System.out.println("00000000000000000000000000000000000000");
			} else {
				JSONArray arraychildren = null;
				if (rrr.length() != 0) {
					AfJSON afJson = new AfJSON();
					arraychildren = afJson.jsonToArray(rrr);

				}
				familytableModel.setRowCount(0);
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
			}
			System.out.println("@@@@@@@@@@@@@@@@");//

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

		JScrollPane fatherlistscrollPane = new JScrollPane(fathertable);
		fatherlistscrollPane.setPreferredSize(new Dimension(630, 230));
		fatherlistPanel.add(fatherlistscrollPane, BorderLayout.CENTER);

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

		JScrollPane motherlistscrollPane = new JScrollPane(mothertable);
		motherlistscrollPane.setPreferredSize(new Dimension(630, 230));
		motherlistPanel.add(motherlistscrollPane, BorderLayout.CENTER);

		Object[][] motherdata = { { "First name", mfirstnamefamilylist }, { "Last name", mlastnamefamilylist },
				{ "Birth", mbirthfamilylist }, { "death", mdeathfamilylist }, { "Address", maddressfamilylist } };
		mothermodel.setDataVector(motherdata, mothercolumnNames);
		mothertable.setModel(mothermodel);
		mothertable.setFillsViewportHeight(true);
		mothertable.setRowSelectionAllowed(true);
		mothertable.setRowHeight(30);

		motherparentlistPanel.setLayout(new AfColumnLayout(10));
		fmotherfield = new JTextField("+ Click to add Father", 20);
		mmotherfield = new JTextField("+ Click to add Mother", 20);
		motherparentlistPanel.add(motherparentlistlabel);//
		motherparentlistPanel.add(fmotherfield);
		motherparentlistPanel.add(mmotherfield);

		// The lower part of the Family List
		// Family List 下半部分表格
		JPanel childlistPanel = new JPanel();

		JTable familytable = new JTable(familytableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane childlistscrollPane = new JScrollPane(familytable);

		childlistscrollPane.setPreferredSize(new Dimension(1270, 410));
		JLabel childlistlabel = new JLabel("Children:");
		jpanel5.add(childlistPanel.add(childlistlabel));
		jpanel5.add(childlistPanel.add(childlistscrollPane));
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");

		childfield = new JTextField(5);

		System.out.println("******************************");

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
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
	}

	private void initToolBar() {
		JToolBar toolBar = new JToolBar();

		tablePanel.add(toolBar, "dock north");// ml1 miglagout

		toolBar.setFloatable(false);

		// Button 按钮

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

			int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "OK",
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
			onEdit();
		});

		toolBar.addSeparator(new Dimension(40, 10));
		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");
			if (str1.length() == 0) {
				// System.out.println(“文本框中的值是int类型”);//文本框中的值是int类型
				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);
				// Separate by comma, firstname before comma, lastname after comma
				// 逗号分隔，逗号前是firstname，后是lastname
				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {
						// As an array
						// 当成数组的写法
						String str = tt[i];
						// Determine whether there are spaces
						// 判断是否存在空格
						if (str.indexOf(" ") != -1) {

							System.out.println("There are spaces!");
							// System.out.println("存在空格!");
							// Replace spaces with %20
							// 空格替换为%20
							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("There are not spaces!");
							first_name = str;
						}
						// first_name=str;
					} else {
						String str = tt[i]; // As an array 当成数组的写法
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

		String imagePath = "/icons/" + icon;
		URL imageURL = getClass().getResource(imagePath);

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

		tableModel.addRow(rowData);
	}

	private Person getTableRow(int row) {
		Person s = new Person();
		s.personid = (String) tableModel.getValueAt(row, 0);
		s.firstname = (String) tableModel.getValueAt(row, 1);
		s.lastname = (String) tableModel.getValueAt(row, 2);
		s.sex = (Boolean) tableModel.getValueAt(row, 3);
		s.birthdate = (String) tableModel.getValueAt(row, 4);
		s.deathdate = (String) tableModel.getValueAt(row, 5);
		s.homeaddress = (String) tableModel.getValueAt(row, 6);
		return s;
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
		tableModel.setRowCount(0);
		testhttp();

	}

	private void onEdit() {

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int row = rows[0];
		Person s = getTableRow(row);
		personidfilter = (String) tableModel.getValueAt(row, 0);// id
		AddIndividual.personid = personidfilter;

		AddIndividual ai = new AddIndividual(this);

		ai.setValue(s);

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
			ai.testhttpeditperson(personidfilter, firstname, lastname, sex, birth, death, address);

		}

		tableModel.setRowCount(0);
		testhttp();
	}

	private void onAddFather() {
		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add father?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

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

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add mother?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

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

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add spouse?", "OK",
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

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete fahter?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

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

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete mother?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

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

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete spouse?", "确认",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id
			EditUnlinkFromSpouse.personid = personidfilter;

		}

		EditUnlinkFromSpouse eufs = new EditUnlinkFromSpouse(this);
		if (eufs.exec()) {

		}
	}

	// Show all Person data
	// 显示全部Person数据
	public void testhttp() {
		TestCallHttpList tchpl = new TestCallHttpList();

		// Get server response result
		// 获取服务器响应结果
		JSONArray array = tchpl.httpURLGETCase("http://3.9.172.108:8090/api/person/list");

		// Display the returned person list information
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

				addTableRow(person);
			}
		}
	}

	// Find person (according to id)
	// 查找person（按照id）
	public void testsearchhttp(String filter) {

		// Filter condition is empty
		// 过滤条件为空
		if (filter.length() == 0) {

			// Display all data of the interface
			// 显示接口所有数据
			tableModel.setRowCount(0);// 清空
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
			tableModel.setRowCount(0);
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

	// Find person (according to fullname)
	// 查找person（按照fullname）
	public void testsearchfullnamehttp(String first_name, String last_name) {

		if (first_name.length() == 0 && last_name.length() == 0) {

			tableModel.setRowCount(0);
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
			tableModel.setRowCount(0);
			// TestCallHttpPersonSearchFullName tchpsfn = new
			// TestCallHttpPersonSearchFullName();
			JSONArray array = TestCallHttpPersonSearchFullName.httpURLGETCase(first_name, last_name);
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

					addTableRow(person);
				}
			}

		}

	}

	// delete person
	// 删除接口

	public void testhttpdeleteperson(String filter) {
		// TODO Auto-generated method stub

		TestCallHttpPersonDelect tchpd = new TestCallHttpPersonDelect();
		tchpd.httpURLGETCase(filter);

		tableModel.setRowCount(0);
		testhttp();

	}

	private void onFamilyTree() {

		String personidfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to view the tree?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);
			// TestCallHttpFamilytree tchft = new TestCallHttpFamilytree();
			JSONObject aperson = null;

			JSONObject afatherpersonvo = null;
			JSONObject afatherfatherpersonVO = null;
			JSONObject afffatherpersonVO = null;
			JSONObject affffatherpersonVO = null;
			JSONObject afffmotherpersonVO = null;
			JSONObject affmotherpersonVO = null;
			JSONObject affmfatherpersonVO = null;
			JSONObject affmmotherpersonVO = null;
			JSONObject afathermotherpersonVO = null;
			JSONObject afmfatherpersonVO = null;
			JSONObject afmffatherpersonVO = null;
			JSONObject afmfmotherpersonVO = null;
			JSONObject afmmotherpersonVO = null;
			JSONObject afmmfatherpersonVO = null;
			JSONObject afmmmotherpersonVO = null;

			JSONObject afather = null;
			JSONObject afatherfather = null;
			JSONObject afffather = null;
			JSONObject affffather = null;
			JSONObject afffmother = null;
			JSONObject affmother = null;
			JSONObject affmfather = null;
			JSONObject affmmother = null;
			JSONObject afathermother = null;
			JSONObject afmfather = null;
			JSONObject afmffather = null;
			JSONObject afmfmother = null;
			JSONObject afmmother = null;
			JSONObject afmmfather = null;
			JSONObject afmmmother = null;

			JSONObject amotherpersonvo = null;
			JSONObject amotherfatherpersonVO = null;
			JSONObject amffatherpersonVO = null;
			JSONObject amfffatherpersonVO = null;
			JSONObject amffmotherpersonVO = null;
			JSONObject amfmotherpersonVO = null;
			JSONObject amfmfatherpersonVO = null;
			JSONObject amfmmotherpersonVO = null;
			JSONObject amothermotherpersonVO = null;
			JSONObject ammfatherpersonVO = null;
			JSONObject ammffatherpersonVO = null;
			JSONObject ammfmotherpersonVO = null;
			JSONObject ammmotherpersonVO = null;
			JSONObject ammmfatherpersonVO = null;
			JSONObject ammmmotherpersonVO = null;

			JSONObject amother = null;
			JSONObject amotherfather = null;
			JSONObject amffather = null;
			JSONObject amfffather = null;
			JSONObject amffmother = null;
			JSONObject amfmother = null;
			JSONObject amfmfather = null;
			JSONObject amfmmother = null;
			JSONObject amothermother = null;
			JSONObject ammfather = null;
			JSONObject ammffather = null;
			JSONObject ammfmother = null;
			JSONObject ammmother = null;
			JSONObject ammmfather = null;
			JSONObject ammmmother = null;

			JSONObject familytree = TestCallHttpFamilytree.httpURLGETCase(personidfilter);// familytree=a(TestCallHttpFamilytree)personidfilter:id

			String fatherfirstname;
			String fatherfatherfirstname = null;
			String fffatherfirstname = null;
			String ffffatherfirstname = null;
			String fffmotherfirstname = null;
			String ffmotherfirstname = null;
			String ffmfatherfirstname = null;
			String ffmmotherfirstname = null;
			String fathermotherfirstname = null;
			String fmfatherfirstname = null;
			String fmffatherfirstname = null;
			String fmfmotherfirstname = null;
			String fmmotherfirstname = null;
			String fmmfathernamefirstname = null;
			String fmmmothernamefirstname = null;
			String fmmfatherfirstname = null;
			String fmmmotherfirstname = null;

			String motherfirstname;
			String motherfatherfirstname = null;
			String mffatherfirstname = null;
			String mfffatherfirstname = null;
			String mffmotherfirstname = null;
			String mfmotherfirstname = null;
			String mfmfatherfirstname = null;
			String mfmmotherfirstname = null;
			String mothermotherfirstname = null;
			String mmfatherfirstname = null;
			String mmffatherfirstname = null;
			String mmfmotherfirstname = null;
			String mmmotherfirstname = null;
			String mmmfatherfirstname = null;
			String mmmmotherfirstname = null;

			System.out.println("--———————a中的personVO(选中的person)(字符串)++++++------");
			String r = familytree.getString("personVO");
			System.out.println(r);
			aperson = JSONObject.fromObject(r);
			String personfirstname = aperson.getString("firstName");
			System.out.println("选中的person firstname:" + personfirstname);

			System.out.println("------a中的person's father(字符串)----");
			String rr = familytree.getString("father");// rr=father
			System.out.println(rr);
			if (rr.equals("null")) {
				System.out.println("------------");
				fatherfirstname = "null";
			} else {

				afatherpersonvo = JSONObject.fromObject(rr);
				String fatherpersonVO = afatherpersonvo.getString("personVO");
				System.out.println("father中的personVO(字符串)：" + fatherpersonVO);
				afather = JSONObject.fromObject(fatherpersonVO);
				fatherfirstname = afather.getString("firstName");
				System.out.println("father firstname：" + fatherfirstname);

				// father's father
				System.out.println("------a中的father's father(字符串)----");
				String fatherfather = afatherpersonvo.getString("father");
				System.out.println("father's father的信息(包括personVO和ID)(字符串)：" + fatherfather);

				if (fatherfather.equals("null")) {
					System.out.println("------------");
					fatherfatherfirstname = "null";
				} else {
					afatherfatherpersonVO = JSONObject.fromObject(fatherfather);
					String fatherfatherpersonVO = afatherfatherpersonVO.getString("personVO");
					System.out.println("father's father中的personVO(字符串)：" + fatherfatherpersonVO);
					afatherfather = JSONObject.fromObject(fatherfatherpersonVO);
					fatherfatherfirstname = afatherfather.getString("firstName");
					System.out.println("father's father firstname：" + fatherfatherfirstname);

					// father's grandparents1-fffather
					System.out.println("------a中的father's grandparents1(字符串)----");
					String fffather = afatherfatherpersonVO.getString("father");
					System.out.println("father's grandparents1的信息(包括personVO和ID)(字符串)：" + fffather);

					if (fffather.equals("null")) {
						System.out.println("------------");
						fffatherfirstname = "null";
					} else {
						afffatherpersonVO = JSONObject.fromObject(fffather);
						String fffatherpersonVO = afffatherpersonVO.getString("personVO");
						System.out.println("father's grandparents1中的personVO(字符串)：" + fffatherpersonVO);
						afffather = JSONObject.fromObject(fffatherpersonVO);
						fffatherfirstname = afffather.getString("firstName");
						System.out.println("father's grandparents1 firstname：" + fffatherfirstname);

						// father's great grandparents1-ffffather
						System.out.println("------a中的father's great grandparents1(字符串)----");
						String ffffather = afffatherpersonVO.getString("father");
						System.out.println("father's great grandparents1的信息(包括personVO和ID)(字符串)：" + ffffather);

						if (ffffather.equals("null")) {
							System.out.println("------------");
							ffffatherfirstname = "null";
						} else {
							affffatherpersonVO = JSONObject.fromObject(ffffather);
							String ffffatherpersonVO = affffatherpersonVO.getString("personVO");
							System.out.println("father's great grandparents1中的personVO(字符串)：" + ffffatherpersonVO);
							affffather = JSONObject.fromObject(ffffatherpersonVO);
							ffffatherfirstname = affffather.getString("firstName");
							System.out.println("father's great grandparents1 firstname：" + ffffatherfirstname);
						}

						// father's great grandparents2-fffmother
						System.out.println("------a中的father's great grandparents2(字符串)----");
						String fffmother = afffatherpersonVO.getString("mother");
						System.out.println("father's great grandparents2的信息(包括personVO和ID)(字符串)：" + fffmother);

						if (fffmother.equals("null")) {
							System.out.println("------------");
							fffmotherfirstname = "null";
						} else {
							afffmotherpersonVO = JSONObject.fromObject(fffmother);
							String fffmotherpersonVO = afffmotherpersonVO.getString("personVO");
							System.out.println("ffather's great grandparents2中的personVO(字符串)：" + fffmotherpersonVO);
							afffmother = JSONObject.fromObject(fffmotherpersonVO);
							fffmotherfirstname = afffmother.getString("firstName");
							System.out.println("father's great grandparents2 firstname：" + fffmotherfirstname);
						}

					}

					// father's grandparents2-ffmother
					System.out.println("------a中的father's grandparents2(字符串)----");
					String ffmother = afatherfatherpersonVO.getString("mother");
					System.out.println("father's grandparents2的信息(包括personVO和ID)(字符串)：" + ffmother);

					if (ffmother.equals("null")) {
						System.out.println("------------");
						ffmotherfirstname = "null";
					} else {
						affmotherpersonVO = JSONObject.fromObject(ffmother);
						String ffmotherpersonVO = affmotherpersonVO.getString("personVO");
						System.out.println("ffather's grandparents2中的personVO(字符串)：" + ffmotherpersonVO);
						affmother = JSONObject.fromObject(ffmotherpersonVO);
						ffmotherfirstname = affmother.getString("firstName");
						System.out.println("father's grandparents2 firstname：" + ffmotherfirstname);

						// father's great grandparents3-ffmfather
						System.out.println("------a中的father's great grandparents3(字符串)----");
						String ffmfather = affmotherpersonVO.getString("father");
						System.out.println("father's great grandparents3的信息(包括personVO和ID)(字符串)：" + ffmfather);

						if (ffmfather.equals("null")) {
							System.out.println("------------");
							ffmfatherfirstname = "null";
						} else {
							affmfatherpersonVO = JSONObject.fromObject(ffmfather);
							String ffmfatherpersonVO = affmfatherpersonVO.getString("personVO");
							System.out.println("father's great grandparents3中的personVO(字符串)：" + ffmfatherpersonVO);
							affmfather = JSONObject.fromObject(ffmfatherpersonVO);
							ffmfatherfirstname = affmfather.getString("firstName");
							System.out.println("father's great grandparents3 firstname：" + ffmfatherfirstname);
						}

						// father's great grandparents4-ffmmother
						System.out.println("------a中的father's great grandparents4(字符串)----");
						String ffmmother = affmotherpersonVO.getString("mother");
						System.out.println("father's great grandparents4的信息(包括personVO和ID)(字符串)：" + ffmmother);

						if (ffmmother.equals("null")) {
							System.out.println("------------");
							ffmmotherfirstname = "null";
						} else {
							affmmotherpersonVO = JSONObject.fromObject(ffmmother);
							String ffmmotherpersonVO = affmmotherpersonVO.getString("personVO");
							System.out.println("ffather's great grandparents4中的personVO(字符串)：" + ffmmotherpersonVO);
							affmmother = JSONObject.fromObject(ffmmotherpersonVO);
							ffmmotherfirstname = affmmother.getString("firstName");
							System.out.println("father's great grandparents4 firstname：" + ffmmotherfirstname);
						}

					}

				}

				// father's mother
				System.out.println("------a中的father's mother(字符串)----");
				String fathermother = afatherpersonvo.getString("mother");
				System.out.println("father's mother的信息(包括personVO和ID)(字符串)：" + fathermother);

				if (fathermother.equals("null")) {
					System.out.println("------------");
					fathermotherfirstname = "null";
				} else {
					afathermotherpersonVO = JSONObject.fromObject(fathermother);
					String fathermotherpersonVO = afathermotherpersonVO.getString("personVO");
					System.out.println("father's mother中的personVO(字符串)：" + fathermotherpersonVO);
					afathermother = JSONObject.fromObject(fathermotherpersonVO);
					fathermotherfirstname = afathermother.getString("firstName");
					System.out.println("father's mother firstname：" + fathermotherfirstname);

					// father's grandparents3-fmfather
					System.out.println("------a中的father's grandparents3(字符串)----");
					String fmfather = afathermotherpersonVO.getString("father");
					System.out.println("father's grandparents3的信息(包括personVO和ID)(字符串)：" + fmfather);

					if (fmfather.equals("null")) {
						System.out.println("------------");
						fmfatherfirstname = "null";
					} else {
						afmfatherpersonVO = JSONObject.fromObject(fmfather);
						String fmfatherpersonVO = afmfatherpersonVO.getString("personVO");
						System.out.println("ffather's grandparents3中的personVO(字符串)：" + fmfatherpersonVO);
						afmfather = JSONObject.fromObject(fmfatherpersonVO);
						fmfatherfirstname = afmfather.getString("firstName");
						System.out.println("father's grandparents3 firstname：" + fmfatherfirstname);

						// father's great grandparents5-fmffather
						System.out.println("------a中的father's great grandparents5(字符串)----");
						String fmffather = afmfatherpersonVO.getString("father");
						System.out.println("father's great grandparents5的信息(包括personVO和ID)(字符串)：" + fmffather);

						if (fmffather.equals("null")) {
							System.out.println("------------");
							fmffatherfirstname = "null";
						} else {
							afmffatherpersonVO = JSONObject.fromObject(fmffather);
							String fmffatherpersonVO = afmffatherpersonVO.getString("personVO");
							System.out.println("father's great grandparents5中的personVO(字符串)：" + fmffatherpersonVO);
							afmffather = JSONObject.fromObject(fmffatherpersonVO);
							fmffatherfirstname = afmffather.getString("firstName");
							System.out.println("father's great grandparents5 firstname：" + fmffatherfirstname);
						}

						// father's great grandparents6-fmfmother
						System.out.println("------a中的father's great grandparents6(字符串)----");
						String fmfmother = afmfatherpersonVO.getString("mother");
						System.out.println("father's great grandparents6的信息(包括personVO和ID)(字符串)：" + fmfmother);

						if (fmfmother.equals("null")) {
							System.out.println("------------");
							fmfmotherfirstname = "null";
						} else {
							afmfmotherpersonVO = JSONObject.fromObject(fmfmother);
							String fmfmotherpersonVO = afmfmotherpersonVO.getString("personVO");
							System.out.println("father's great grandparents6中的personVO(字符串)：" + fmfmotherpersonVO);
							afmfmother = JSONObject.fromObject(fmfmotherpersonVO);
							fmfmotherfirstname = afmfmother.getString("firstName");
							System.out.println("father's great grandparents6 firstname：" + fmfmotherfirstname);
						}

					}

					// father's grandparents4-fmmother
					System.out.println("------a中的father's grandparents4(字符串)----");
					String fmmother = afathermotherpersonVO.getString("father");
					System.out.println("father's grandparents4的信息(包括personVO和ID)(字符串)：" + fmmother);

					if (fmmother.equals("null")) {
						System.out.println("------------");
						fmmotherfirstname = "null";
					} else {
						afmmotherpersonVO = JSONObject.fromObject(fmfather);
						String fmmotherpersonVO = afmmotherpersonVO.getString("personVO");
						System.out.println("ffather's grandparents3中的personVO(字符串)：" + fmmotherpersonVO);
						afmmother = JSONObject.fromObject(fmmotherpersonVO);
						fmmotherfirstname = afmmother.getString("firstName");
						System.out.println("father's grandparents4 firstname：" + fmmotherfirstname);

						// father's great grandparents7-fmmfather
						System.out.println("------a中的father's great grandparents7(字符串)----");
						String fmmfather = afmmotherpersonVO.getString("father");
						System.out.println("father's great grandparents7的信息(包括personVO和ID)(字符串)：" + fmmfather);

						if (fmmfather.equals("null")) {
							System.out.println("------------");
							fmmfatherfirstname = "null";
						} else {
							afmmfatherpersonVO = JSONObject.fromObject(fmmfather);
							String fmmfatherpersonVO = afmmfatherpersonVO.getString("personVO");
							System.out.println("father's great grandparents7中的personVO(字符串)：" + fmmfatherpersonVO);
							afmmfather = JSONObject.fromObject(fmmfatherpersonVO);
							fmmfatherfirstname = afmmfather.getString("firstName");
							System.out.println("father's great grandparents7 firstname：" + fmmfatherfirstname);
						}

						// father's great grandparents8-fmmmother
						System.out.println("------a中的father's great grandparents8(字符串)----");
						String fmmmother = afmmotherpersonVO.getString("mother");
						System.out.println("father's great grandparents8的信息(包括personVO和ID)(字符串)：" + fmmmother);

						if (fmmmother.equals("null")) {
							System.out.println("------------");
							fmmmotherfirstname = "null";
						} else {
							afmmmotherpersonVO = JSONObject.fromObject(fmmmother);
							String fmmmotherpersonVO = afmmmotherpersonVO.getString("personVO");
							System.out.println("father's great grandparents8中的personVO(字符串)：" + fmmmotherpersonVO);
							afmmmother = JSONObject.fromObject(fmmmotherpersonVO);
							fmmmotherfirstname = afmmmother.getString("firstName");
							System.out.println("father's great grandparents8 firstname：" + fmmmotherfirstname);
						}

					}

				}

			}

			// mother
			System.out.println("-----a中的mother(字符串)+++++==----");
			String rrr = familytree.getString("mother");
			System.out.println(rrr);
			if (rrr.equals("null")) {
				System.out.println("------------");
				motherfirstname = "null";
			} else {

				amotherpersonvo = JSONObject.fromObject(rrr);
				String motherpersonVO = amotherpersonvo.getString("personVO");
				System.out.println("mother中的personVO(字符串)：" + motherpersonVO);
				amother = JSONObject.fromObject(motherpersonVO);
				motherfirstname = amother.getString("firstName");
				System.out.println("mother firstname：" + motherfirstname);

				// mother's father
				System.out.println("------a中的mother's father(字符串)----");
				String motherfather = amotherpersonvo.getString("father");
				System.out.println("mother's father的信息(包括personVO和ID)(字符串)：" + motherfather);

				if (motherfather.equals("null")) {
					System.out.println("------------");
					motherfatherfirstname = "null";
				} else {
					amotherfatherpersonVO = JSONObject.fromObject(motherfather);
					String motherfatherpersonVO = amotherfatherpersonVO.getString("personVO");
					System.out.println("mother's father中的personVO(字符串)：" + motherfatherpersonVO);
					amotherfather = JSONObject.fromObject(motherfatherpersonVO);
					motherfatherfirstname = amotherfather.getString("firstName");
					System.out.println("mother's father firstname：" + motherfatherfirstname);

					// mother's grandparents1-mffather
					System.out.println("------a中的mother's grandparents1(字符串)----");
					String mffather = amotherfatherpersonVO.getString("father");
					System.out.println("mother's grandparents1的信息(包括personVO和ID)(字符串)：" + mffather);

					if (mffather.equals("null")) {
						System.out.println("------------");
						mffatherfirstname = "null";
					} else {
						amffatherpersonVO = JSONObject.fromObject(mffather);
						String mffatherpersonVO = amffatherpersonVO.getString("personVO");
						System.out.println("mother's grandparents1中的personVO(字符串)：" + mffatherpersonVO);
						amffather = JSONObject.fromObject(mffatherpersonVO);
						mffatherfirstname = amffather.getString("firstName");
						System.out.println("mother's grandparents1 firstname：" + mffatherfirstname);

						// mother's great grandparents1-mfffather
						System.out.println("------a中的mother's great grandparents1(字符串)----");
						String mfffather = amffatherpersonVO.getString("father");
						System.out.println("mother's great grandparents1的信息(包括personVO和ID)(字符串)：" + mfffather);

						if (mfffather.equals("null")) {
							System.out.println("------------");
							mfffatherfirstname = "null";
						} else {
							amfffatherpersonVO = JSONObject.fromObject(mfffather);
							String mfffatherpersonVO = amfffatherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents1中的personVO(字符串)：" + mfffatherpersonVO);
							amfffather = JSONObject.fromObject(mfffatherpersonVO);
							mfffatherfirstname = amfffather.getString("firstName");
							System.out.println("mother's great grandparents1 firstname：" + mfffatherfirstname);
						}

						// mother's great grandparents2-mffmother
						System.out.println("------a中的mother's great grandparents2(字符串)----");
						String mffmother = amffatherpersonVO.getString("mother");
						System.out.println("mother's great grandparents2的信息(包括personVO和ID)(字符串)：" + mffmother);

						if (mffmother.equals("null")) {
							System.out.println("------------");
							mffmotherfirstname = "null";
						} else {
							amffmotherpersonVO = JSONObject.fromObject(mffmother);
							String mffmotherpersonVO = amffmotherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents2中的personVO(字符串)：" + mffmotherpersonVO);
							amffmother = JSONObject.fromObject(mffmotherpersonVO);
							mffmotherfirstname = amffmother.getString("firstName");
							System.out.println("mother's great grandparents2 firstname：" + mffmotherfirstname);
						}

					}

					// mother's grandparents2-mfmother
					System.out.println("------a中的mother's grandparents2(字符串)----");
					String mfmother = amotherfatherpersonVO.getString("mother");
					System.out.println("mother's grandparents2的信息(包括personVO和ID)(字符串)：" + mfmother);

					if (mfmother.equals("null")) {
						System.out.println("------------");
						mfmotherfirstname = "null";
					} else {
						amfmotherpersonVO = JSONObject.fromObject(mfmother);
						String mfmotherpersonVO = amfmotherpersonVO.getString("personVO");
						System.out.println("ffather's grandparents2中的personVO(字符串)：" + mfmotherpersonVO);
						amfmother = JSONObject.fromObject(mfmotherpersonVO);
						mfmotherfirstname = amfmother.getString("firstName");
						System.out.println("father's grandparents2 firstname：" + mfmotherfirstname);

						// mother's great grandparents3-mfmfather
						System.out.println("------a中的mother's great grandparents3(字符串)----");
						String mfmfather = amfmotherpersonVO.getString("father");
						System.out.println("mother's great grandparents3的信息(包括personVO和ID)(字符串)：" + mfmfather);

						if (mfmfather.equals("null")) {
							System.out.println("------------");
							mfmfatherfirstname = "null";
						} else {
							amfmfatherpersonVO = JSONObject.fromObject(mfmfather);
							String mfmfatherpersonVO = amfmfatherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents3中的personVO(字符串)：" + mfmfatherpersonVO);
							amfmfather = JSONObject.fromObject(mfmfatherpersonVO);
							mfmfatherfirstname = amfmfather.getString("firstName");
							System.out.println("mother's great grandparents3 firstname：" + mfmfatherfirstname);
						}

						// mother's great grandparents4-mfmmother
						System.out.println("------a中的mother great grandparents4(字符串)----");
						String mfmmother = amfmotherpersonVO.getString("mother");
						System.out.println("mother's great grandparents4的信息(包括personVO和ID)(字符串)：" + mfmmother);

						if (mfmmother.equals("null")) {
							System.out.println("------------");
							mfmmotherfirstname = "null";
						} else {
							amfmmotherpersonVO = JSONObject.fromObject(mfmmother);
							String mfmmotherpersonVO = amfmmotherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents4中的personVO(字符串)：" + mfmmotherpersonVO);
							amfmmother = JSONObject.fromObject(mfmmotherpersonVO);
							mfmmotherfirstname = amfmmother.getString("firstName");
							System.out.println("mother's great grandparents4 firstname：" + mfmmotherfirstname);
						}

					}

				}

				// mother's mother
				System.out.println("------a中的mother's mother(字符串)----");
				String mothermother = amotherpersonvo.getString("mother");
				System.out.println("mother's mother的信息(包括personVO和ID)(字符串)：" + mothermother);

				if (mothermother.equals("null")) {
					System.out.println("------------");
					mothermotherfirstname = "null";
				} else {
					amothermotherpersonVO = JSONObject.fromObject(mothermother);
					String mothermotherpersonVO = amothermotherpersonVO.getString("personVO");
					System.out.println("mother's father中的personVO(字符串)：" + mothermotherpersonVO);
					amothermother = JSONObject.fromObject(mothermotherpersonVO);
					mothermotherfirstname = amothermother.getString("firstName");
					System.out.println("mother's mother firstname：" + mothermotherfirstname);

					// mother's grandparents3-mmfather
					System.out.println("------a中的mother's grandparents3(字符串)----");
					String mmfather = amothermotherpersonVO.getString("father");
					System.out.println("mother's grandparents3的信息(包括personVO和ID)(字符串)：" + mmfather);

					if (mmfather.equals("null")) {
						System.out.println("------------");
						mmfatherfirstname = "null";
					} else {
						ammfatherpersonVO = JSONObject.fromObject(mmfather);
						String mmfatherpersonVO = ammfatherpersonVO.getString("personVO");
						System.out.println("mother's grandparents3中的personVO(字符串)：" + mmfatherpersonVO);
						ammfather = JSONObject.fromObject(mmfatherpersonVO);
						mmfatherfirstname = ammfather.getString("firstName");
						System.out.println("mother's grandparents3 firstname：" + mmfatherfirstname);

						// mother's great grandparents5-mmffather
						System.out.println("------a中的mother's great grandparents5(字符串)----");
						String mmffather = ammfatherpersonVO.getString("father");
						System.out.println("mother's great grandparents5的信息(包括personVO和ID)(字符串)：" + mmffather);

						if (mmffather.equals("null")) {
							System.out.println("------------");
							mmffatherfirstname = "null";
						} else {
							ammffatherpersonVO = JSONObject.fromObject(mmffather);
							String mmffatherpersonVO = ammffatherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents5中的personVO(字符串)：" + mmffatherpersonVO);
							ammffather = JSONObject.fromObject(mmffatherpersonVO);
							mmffatherfirstname = ammffather.getString("firstName");
							System.out.println("mother's great grandparents5 firstname：" + mmffatherfirstname);
						}

						// mother's great grandparents6-mmfmother
						System.out.println("------a中的mother's great grandparents6(字符串)----");
						String mmfmother = ammfatherpersonVO.getString("mother");
						System.out.println("mother's great grandparents6的信息(包括personVO和ID)(字符串)：" + mmfmother);

						if (mmfmother.equals("null")) {
							System.out.println("------------");
							mmfmotherfirstname = "null";
						} else {
							ammfmotherpersonVO = JSONObject.fromObject(mmfmother);
							String mmfmotherpersonVO = ammfmotherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents6中的personVO(字符串)：" + mmfmotherpersonVO);
							ammfmother = JSONObject.fromObject(mmfmotherpersonVO);
							mmfmotherfirstname = ammfmother.getString("firstName");
							System.out.println("mother's great grandparents6 firstname：" + mmfmotherfirstname);
						}

					}

					// mother's grandparents4-mmmother
					System.out.println("------a中的mother's grandparents4(字符串)----");
					String mmmother = amothermotherpersonVO.getString("mother");
					System.out.println("mother's grandparents4的信息(包括personVO和ID)(字符串)：" + mmmother);

					if (mmmother.equals("null")) {
						System.out.println("------------");
						mmmotherfirstname = "null";
					} else {
						ammmotherpersonVO = JSONObject.fromObject(mmmother);
						String mmmotherpersonVO = ammmotherpersonVO.getString("personVO");
						System.out.println("mother's grandparents4中的personVO(字符串)：" + mmmotherpersonVO);
						ammmother = JSONObject.fromObject(mmmotherpersonVO);
						mmmotherfirstname = ammmother.getString("firstName");
						System.out.println("mother's grandparents4 firstname：" + mmmotherfirstname);

						// mother's great grandparents7-mmmfather
						System.out.println("------a中的mother's great grandparents7(字符串)----");
						String mmmfather = ammmotherpersonVO.getString("father");
						System.out.println("mother's great grandparents7的信息(包括personVO和ID)(字符串)：" + mmmfather);

						if (mmmfather.equals("null")) {
							System.out.println("------------");
							mmmfatherfirstname = "null";
						} else {
							ammmfatherpersonVO = JSONObject.fromObject(mmmfather);
							String mmmfatherpersonVO = ammmfatherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents7中的personVO(字符串)：" + mmmfatherpersonVO);
							ammmfather = JSONObject.fromObject(mmmfatherpersonVO);
							mmmfatherfirstname = ammmfather.getString("firstName");
							System.out.println("mother's great grandparents7 firstname：" + mmmfatherfirstname);
						}

						// mother's great grandparents8-mmmmother
						System.out.println("------a中的father's great grandparents8(字符串)----");
						String mmmmother = ammmotherpersonVO.getString("mother");
						System.out.println("mother's great grandparents8的信息(包括personVO和ID)(字符串)：" + mmmmother);

						if (mmmmother.equals("null")) {
							System.out.println("------------");
							mmmmotherfirstname = "null";
						} else {
							ammmmotherpersonVO = JSONObject.fromObject(mmmmother);
							String mmmmotherpersonVO = ammmmotherpersonVO.getString("personVO");
							System.out.println("mother's great grandparents8中的personVO(字符串)：" + mmmmotherpersonVO);
							ammmmother = JSONObject.fromObject(mmmmotherpersonVO);
							mmmmotherfirstname = ammmmother.getString("firstName");
							System.out.println("mother's great grandparents8 firstname：" + mmmmotherfirstname);
						}

					}

				}

			}
			// System.out.println("+以上不该动++++++");

			personname = personfirstname;

			fathername = fatherfirstname;
			fatherfathername = fatherfatherfirstname;
			fffathername = fffatherfirstname;
			ffffathername = ffffatherfirstname;
			fffmothername = fffmotherfirstname;
			ffmothername = ffmotherfirstname;
			ffmfathername = ffmfatherfirstname;
			ffmmothername = ffmmotherfirstname;

			fathermothername = fathermotherfirstname;
			fmfathername = fmfatherfirstname;
			fmffathername = fmffatherfirstname;
			fmfmothername = fmfmotherfirstname;
			fmmfathername = fmmfathernamefirstname;
			fmmmothername = fmmmothernamefirstname;
			fmmothername = fmmotherfirstname;

			mothername = motherfirstname;
			motherfathername = motherfatherfirstname;
			mffathername = mffatherfirstname;
			mfffathername = mfffatherfirstname;
			mffmothername = mffmotherfirstname;
			mfmothername = mfmotherfirstname;
			mfmfathername = mfmfatherfirstname;
			mfmmothername = mfmmotherfirstname;

			mothermothername = mothermotherfirstname;
			mmfathername = mmfatherfirstname;
			mmffathername = mmffatherfirstname;
			mmfmothername = mmfmotherfirstname;
			mmmothername = mmmotherfirstname;
			mmmfathername = mmmotherfirstname;
			mmmmothername = mmmotherfirstname;

			System.out.println("personname:" + personname);
			System.out.println("++++++++++++++++++++++++++++++++++");

		}
		FamilyTree ft = new FamilyTree(this);
		System.out.println("++++++++——————————————————————————————————");
		ft.parent.setText(personname);
		ft.textField1.setText(fathername);
		ft.textField2.setText(mothername);
		ft.textField3.setText(fatherfathername);
		ft.textField4.setText(fathermothername);
		ft.textField5.setText(motherfathername);
		ft.textField6.setText(mothermothername);
		ft.textField7.setText(fffathername);
		ft.textField8.setText(ffmothername);
		ft.textField9.setText(fmfathername);
		ft.textField10.setText(fmmothername);
		ft.textField11.setText(mffathername);
		ft.textField12.setText(mfmothername);
		ft.textField13.setText(mmfathername);
		ft.textField14.setText(mmmothername);
		ft.textField15.setText(ffffathername);
		ft.textField16.setText(fffmothername);
		ft.textField17.setText(ffmfathername);
		ft.textField18.setText(ffmmothername);
		ft.textField19.setText(fmffathername);
		ft.textField20.setText(fmfmothername);
		ft.textField21.setText(fmmfathername);
		ft.textField22.setText(fmmmothername);
		ft.textField23.setText(mfffathername);
		ft.textField24.setText(mffmothername);
		ft.textField25.setText(mfmfathername);
		ft.textField26.setText(mfmmothername);
		ft.textField27.setText(mmffathername);
		ft.textField28.setText(mmfmothername);
		ft.textField29.setText(mmmfathername);
		ft.textField30.setText(mmmmothername);

		if (ft.exec()) {

		}

	}

}