package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	JButton okButton = new JButton("OK");

	JTable informationtable = new JTable();
	DefaultTableModel informationmodel = new DefaultTableModel();
	String[] informationcolumnNames = { "Facts", "Informations" };

	JTable relevanttable = new JTable();
	DefaultTableModel relevantmodel = new DefaultTableModel();
	String[] relevantcolumnNames = { "Facts", "Informations", "  " };

	static String fatherid = null;
	static String motherid = null;
	static String spouseid = null;
	static String personid1 = null;
	static String personid2 = null;
	static String personid3 = null;
	String fatheridfilter = null;
	String motheridfilter = null;
	String spouseidfilter = null;

	String personidfilter = null;

	String personfirstname = null;
	String personlastname = null;
	String personbirth = null;
	String persondeath = null;
	String personaddress = null;

	String personfatherfirstname = null;
	String personfatherlastname = null;
	String personmotherfirstname = null;
	String personmotherlastname = null;
	String personspousefirstname = null;
	String personspouselastname = null;

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

		jpanel1.setLayout(new BorderLayout());// 左侧表格
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");

		JScrollPane informationlistscrollPane = new JScrollPane(informationtable);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		// String[] informationcolumnNames = {"Facts", "Informations"};
		Object[][] informationdata = { { "First name", "First name" }, { "Last name", "Last name" },
				{ "Birth", "Birth" }, { "death", "death" }, { "Address", "Address" } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true); // 整行选择
		informationtable.setRowHeight(30);

		JLabel relevantlabel = new JLabel("Relevant personnel:");

		JScrollPane relevantlistscrollPane = new JScrollPane(relevanttable);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		Object[][] relevantdata = { { "Father", "", "" }, { "Mother", " ", "" }, { "BrothersAndSisters", "", "" },
				{ "Spouse", "", "" }, { "Children", "", "" } };

		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true); // 整行选择
		relevanttable.setRowHeight(30);

		// 初始化工具栏 Initialize the toolbar
		initToolBarSpouse();

		// 表格初始化（主页面左侧） Form initialization (left side of main page)
		initTable();

//		// 加载文件 Load file
		testhttp();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				if (clickCount == 2) {
					System.out.println("clickCount2");
					onPersonInformation();
					System.out.println("-——++++++++-");
				}
			}
		});

	}

	private void onAddExistingSpouse() {
		// String fatheridfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add this person as spouse?", "ok",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			spouseidfilter = (String) tableModel.getValueAt(row, 0);// id
			SelectExistPerson.spouseid = spouseidfilter;
			System.out.println("spouseid:" + spouseidfilter);
			TestCallHttpSelectExistingSpouse.httpURLPOSTCase(personid3, spouseid);
		}

	}

	public void testhttpselectexistspouse(String personid) {
		// TODO Auto-generated method stub
		personid3 = personid;

	}

	private void addTableRow(Person item) {
		// java.util.Vector 是个范型 ，表示数组
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

	public void testhttp() {
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

		jpanel1.setLayout(new BorderLayout());// 左侧表格
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");

		// JList informationlist = new JList();
//		String[] dd = { "XXX(information)" };
//		informationlist = new JList(dd);
//		informationlist.setVisibleRowCount(2);
		JScrollPane informationlistscrollPane = new JScrollPane(informationtable);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		// String[] informationcolumnNames = {"Facts", "Informations"};
		Object[][] informationdata = { { "First name", "First name" }, { "Last name", "Last name" },
				{ "Birth", "Birth" }, { "death", "death" }, { "Address", "Address" } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true); // 整行选择
		informationtable.setRowHeight(30);

		JLabel relevantlabel = new JLabel("Relevant personnel:");
//		JList relevantlist = new JList();
//		String[] dd1 = { "XXX(relevant)" };
//		relevantlist = new JList(dd1);
//		relevantlist.setVisibleRowCount(2);
		JScrollPane relevantlistscrollPane = new JScrollPane(relevanttable);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		Object[][] relevantdata = { { "Father", "", "" }, { "Mother", " ", "" }, { "BrothersAndSisters", "", "" },
				{ "Spouse", "", "" }, { "Children", "", "" } };

		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true); // 整行选择
		relevanttable.setRowHeight(30);

		// 初始化工具栏
		initToolBarfather();

		// 表格初始化（主页面左侧）
		initTable();

		// 加载文件
		testhttp();
		// System.out.println("-----------");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				if (clickCount == 2) {
					System.out.println("clickCount2");
					onPersonInformation();
					System.out.println("-——++++++++-");
				}
			}
		});

	}

	private void onAddExistingFather() {
		// String fatheridfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认添加此人为父亲?", "确认", JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			fatheridfilter = (String) tableModel.getValueAt(row, 0);// id
			SelectExistPerson.fatherid = fatheridfilter;
			System.out.println("fatherid:" + fatheridfilter);
			TestCallHttpSelectExistingFather.httpURLPOSTCase(personid1, fatherid);
			// TestCallHttpAllRelevantPeople.httpURLGETCase(fatheridfilter);
		}

	}

	public void testhttpselectexistfather(String personid) {
		// TODO Auto-generated method stub
		personid1 = personid;

	}

	private void onPersonInformation() {
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

//		// 弹出对话框确认
//		int select = JOptionPane.showConfirmDialog(this, "是否确认添加此人为父亲?", "确认", JOptionPane.YES_NO_OPTION);
//		if(select != 0) return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id

			JSONObject personObject = null;
			JSONObject fatherObject = null;
			JSONObject motherObject = null;
			JSONObject spouseObject = null;

			JSONObject informationlist = TestCallHttpAllRelevantPeople.httpURLGETCase(personidfilter);// informationlist=a(TestCallHttpAllRelevantPeople)personidfilter:id
			System.out.println("personid:" + personidfilter);
			System.out.println("--———————a中的person(选中的person information)(字符串)++++++------");
			String person = informationlist.getString("person");
			System.out.println("person:" + person);

			personObject = JSONObject.fromObject(person);

			String firstname = personObject.getString("firstName");
			System.out.println("person firstname：" + firstname);
			String lastname = personObject.getString("lastName");
			System.out.println("person lastname：" + lastname);
			String birth = personObject.getString("birth");
			System.out.println("person birth：" + birth);
			String death = personObject.getString("death");
			System.out.println("person death：" + death);
			String address = personObject.getString("address");
			System.out.println("person address：" + address);

			personfirstname = firstname;
			personlastname = lastname;
			personbirth = birth;
			persondeath = death;
			personaddress = address;

			System.out.println("--———————a中的father(选中的person的father information)(字符串)++++++------");
			String father = informationlist.getString("father");
			System.out.println("father:" + father);
			fatherObject = JSONObject.fromObject(father);
			String fatherfirstname = fatherObject.getString("firstName");
			System.out.println("person‘s father firstname：" + fatherfirstname);
			String fatherlastname = fatherObject.getString("lastName");
			System.out.println("person‘s father lastname：" + fatherlastname);

			personfatherfirstname = fatherfirstname;
			personfatherlastname = fatherlastname;

			System.out.println("--———————a中的mother(选中的person的mother information)(字符串)++++++------");
			String mother = informationlist.getString("mother");
			System.out.println("mother:" + mother);
			motherObject = JSONObject.fromObject(mother);
			String motherfirstname = motherObject.getString("firstName");
			System.out.println("person‘s mother firstname：" + motherfirstname);
			String motherlastname = motherObject.getString("lastName");
			System.out.println("person‘s mother lastname：" + motherlastname);

			personmotherfirstname = motherfirstname;
			personmotherlastname = motherlastname;

			System.out.println("--———————a中的spouse(选中的person的spouse information)(字符串)++++++------");
			String spouse = informationlist.getString("spouse");
			System.out.println("spouse:" + spouse);
			spouseObject = JSONObject.fromObject(spouse);
			String spousefirstname = spouseObject.getString("firstName");
			System.out.println("person‘s spouse firstname：" + spousefirstname);
			String spouselastname = spouseObject.getString("lastName");
			System.out.println("person‘s spouse lastname：" + spouselastname);

			personspousefirstname = spousefirstname;
			personspouselastname = spouselastname;

		}
		Test t1 = new Test();
		t1.ttest(personfirstname, personlastname, personbirth, persondeath, personaddress);
		Object[][] informationdata = { { "First name", personfirstname }, { "Last name", personlastname },
				{ "Birth", personbirth }, { "death", persondeath }, { "Address", personaddress } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true); // 整行选择
		informationtable.setRowHeight(30);

		t1.test2(personfatherfirstname, personfatherlastname);
		t1.test2(personmotherfirstname, personmotherlastname);
		t1.test2(personspousefirstname, personspouselastname);
		Object[][] relevantdata = { { "Father", personfatherfirstname, personfatherlastname },
				{ "Mother", personmotherfirstname, personmotherlastname }, { "BrothersAndSisters", "", "" },
				{ "Spouse", personspousefirstname, personspouselastname }, { "Children", "", "" } };
		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true); // 整行选择
		relevanttable.setRowHeight(30);

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

		jpanel1.setLayout(new BorderLayout());// 左侧表格
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");
		// JList informationlist = new JList();
//		String[] dd = { "XXX(information)" };
//		informationlist = new JList(dd);
//		informationlist.setVisibleRowCount(2);
		JScrollPane informationlistscrollPane = new JScrollPane(informationtable);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		// String[] informationcolumnNames = {"Facts", "Informations"};
		Object[][] informationdata = { { "First name", "First name" }, { "Last name", "Last name" },
				{ "Birth", "Birth" }, { "death", "death" }, { "Address", "Address" } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true); // 整行选择
		informationtable.setRowHeight(30);

		JLabel relevantlabel = new JLabel("Relevant personnel:");
//		JList relevantlist = new JList();
//		String[] dd1 = { "XXX(relevant)" };
//		relevantlist = new JList(dd1);
//		relevantlist.setVisibleRowCount(2);
		JScrollPane relevantlistscrollPane = new JScrollPane(relevanttable);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		Object[][] relevantdata = { { "Father", "", "" }, { "Mother", " ", "" }, { "BrothersAndSisters", "", "" },
				{ "Spouse", "", "" }, { "Children", "", "" } };

		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true); // 整行选择
		relevanttable.setRowHeight(30);

		// 初始化工具栏
		initToolBarmother();

		// 表格初始化（主页面左侧）
		initTable();

		// 加载文件
		testhttp();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				if (clickCount == 2) {
					System.out.println("clickCount2");
					onPersonInformation();
					System.out.println("-——++++++++-");
				}
			}
		});

	}

	private void onAddExistingMother() {
		// String fatheridfilter = null;
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认添加此人为mother?", "确认", JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			motheridfilter = (String) tableModel.getValueAt(row, 0);// id
			SelectExistPerson.motherid = motheridfilter;
			System.out.println("motherid:" + motheridfilter);
			TestCallHttpSelectExistingMother.httpURLPOSTCase(personid2, motherid);
		}

	}

	public void testhttpselectexistmother(String personid) {
		// TODO Auto-generated method stub
		personid2 = personid;

	}

//	public SelectExistPerson(AddChild addChild)
//	{
//		super(addChild, "Select Exist Person", true);
//		this.setSize(1000,800);
//		this.setContentPane(root);
//		root.setLayout(layout);
//		
//		jpanel1.setPreferredSize(new Dimension(500, 800));
//		jpanel2.setPreferredSize(new Dimension(500, 400));
//		jpanel3.setPreferredSize(new Dimension(500, 400));
//		
//		
//		root.add(jpanel1,"dock west");
//		root.add(jpanel2,"wrap");
//		root.add(jpanel3);
//		
//		jpanel1.setLayout(new BorderLayout());//左侧表格
//		jpanel2.setLayout(new BorderLayout());
//		jpanel3.setLayout(new BorderLayout());
//		
//		JLabel informationlabel = new JLabel("Information:");
//		JList informationlist = new JList();
//		String[] dd = { "XXX(information)" };
//		informationlist = new JList(dd);
//		informationlist.setVisibleRowCount(2);
//		JScrollPane informationlistscrollPane = new JScrollPane(informationlist);
//		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
//		jpanel2.add(informationlabel, BorderLayout.NORTH);
//		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);
//		
//		JLabel relevantlabel = new JLabel("Relevant personnel:");
//		JList relevantlist = new JList();
//		String[] dd1 = { "XXX(relevant)" };
//		relevantlist = new JList(dd1);
//		relevantlist.setVisibleRowCount(2);
//		JScrollPane relevantlistscrollPane = new JScrollPane(relevantlist);
//		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
//		jpanel3.add(relevantlabel, BorderLayout.NORTH);
//		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);
//		
//		// 初始化工具栏
//		initToolBar();
//
//		// 表格初始化（主页面左侧）
//		initTable();
//		
////		// 加载文件
////		testhttp();
//		
//	}

	private void initToolBarSpouse() {
		JToolBar toolBar = new JToolBar();
		jpanel1.add(toolBar, BorderLayout.NORTH);

		toolBar.setFloatable(false);

		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		toolBar.add(okButton); // 按钮靠右显示
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {
			// 按回车时触发事件
			// onSearch();

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");// 将所有的数字型字符替换为空
			if (str1.length() == 0) {
				// System.out.println(“文本框中的值是int类型”);//文本框中的值是int类型
				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);
				// 逗号分隔，逗号前是firstname，后是lastname
				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {
						// 当成数组的写法
						String str = tt[i];
						// 判断是否存在空格
						if (str.indexOf(" ") != -1) {
							System.out.println("存在空格!");
							// 空格替换为%20
							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("bu存在空格");
							first_name = str;
						}

//Gustav Carl Diederich,Henning

						// first_name=str;
					} else {
						String str = tt[i]; // 当成数组的写法
						last_name = str;
					}

				}
				System.out.println(first_name + "-----------" + last_name);// 文本框中的值是int类型
				System.out.println("qqqqqqqqqqqqqqqqqqqqqq");
				testsearchfullnamehttp(first_name, last_name);

			}

		});

		okButton.addActionListener((e) -> {
			onAddExistingSpouse();

		});

	}

	private void initToolBarmother() {
		JToolBar toolBar = new JToolBar();
		jpanel1.add(toolBar, BorderLayout.NORTH);

		toolBar.setFloatable(false);

		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		toolBar.add(okButton); // 按钮靠右显示
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {
			// 按回车时触发事件
			// onSearch();

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");// 将所有的数字型字符替换为空
			if (str1.length() == 0) {
				// System.out.println(“文本框中的值是int类型”);//文本框中的值是int类型
				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);
				// 逗号分隔，逗号前是firstname，后是lastname
				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {
						// 当成数组的写法
						String str = tt[i];
						// 判断是否存在空格
						if (str.indexOf(" ") != -1) {
							System.out.println("存在空格!");
							// 空格替换为%20
							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("bu存在空格");
							first_name = str;
						}

//Gustav Carl Diederich,Henning

						// first_name=str;
					} else {
						String str = tt[i]; // 当成数组的写法
						last_name = str;
					}

				}
				System.out.println(first_name + "-----------" + last_name);// 文本框中的值是int类型
				System.out.println("qqqqqqqqqqqqqqqqqqqqqq");
				testsearchfullnamehttp(first_name, last_name);

			}

		});

		okButton.addActionListener((e) -> {
			onAddExistingMother();

		});

	}

	private void initToolBarfather() {
		JToolBar toolBar = new JToolBar();
		jpanel1.add(toolBar, BorderLayout.NORTH);

		toolBar.setFloatable(false);

		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		toolBar.add(okButton); // 按钮靠右显示
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {
			// 按回车时触发事件
			// onSearch();

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");// 将所有的数字型字符替换为空
			if (str1.length() == 0) {
				// System.out.println(“文本框中的值是int类型”);//文本框中的值是int类型
				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);
				// 逗号分隔，逗号前是firstname，后是lastname
				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {
						// 当成数组的写法
						String str = tt[i];
						// 判断是否存在空格
						if (str.indexOf(" ") != -1) {
							System.out.println("存在空格!");
							// 空格替换为%20
							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("bu存在空格");
							first_name = str;
						}

//Gustav Carl Diederich,Henning

						// first_name=str;
					} else {
						String str = tt[i]; // 当成数组的写法
						last_name = str;
					}

				}
				System.out.println(first_name + "-----------" + last_name);// 文本框中的值是int类型
				System.out.println("qqqqqqqqqqqqqqqqqqqqqq");
				testsearchfullnamehttp(first_name, last_name);

			}

		});

		okButton.addActionListener((e) -> {
			onAddExistingFather();

		});

	}

	private void initTable() {
		// 创建 JTable，直接重写 isCellEditable()，设为不可编辑
		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// root.add(tablePanel, BorderLayout.WEST);
		// tablePanel.setPreferredSize(new Dimension(500, 0));//nice
		JScrollPane scrollPane = new JScrollPane(table);
		jpanel1.add(scrollPane, BorderLayout.CENTER);// ml1 miglagout
		scrollPane.setPreferredSize(new Dimension(475, 785));

		// 添加到主界面
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true); // 整行选择
		table.setRowHeight(30);

		// 列设置：添加5列
		tableModel.addColumn("Person ID");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Sex");
		tableModel.addColumn("Birth Date");
		tableModel.addColumn("Death Date");
		tableModel.addColumn("Home Address");

		// 列设置：自定义绘制
		table.getColumnModel().getColumn(3).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度
	}

	public boolean exec() {
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);

		return retValue;
	}

	// 查找person（按照fullname）
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

	// 查找person（按照id）
	public void testsearchhttp(String filter) {

		// 过滤条件为空
		if (filter.length() == 0) {
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

//			TestCallHttpList tchpl = new TestCallHttpList();
//			tchpl.httpURLGETCase(null);
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
				// 在表格中添加person
				addTableRow(person);
			}
		}

	}

}
