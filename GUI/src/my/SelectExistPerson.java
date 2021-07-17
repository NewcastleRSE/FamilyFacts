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

import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smx.common.json.AfJSON;
import smx.swing.AfPanel;

public class SelectExistPerson extends JDialog {

	AfPanel root = new AfPanel();
	JPanel jpanel1 = new JPanel();
	JPanel jpanel2 = new JPanel();
	JPanel jpanel3 = new JPanel();
	JPanel jpanel4 = new JPanel();
	JPanel jpanel5 = new JPanel();
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

	DefaultTableModel childrentableModel = new DefaultTableModel();
	DefaultTableModel brosistableModel = new DefaultTableModel();

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
		jpanel2.setPreferredSize(new Dimension(500, 200));
		jpanel3.setPreferredSize(new Dimension(500, 200));
		jpanel4.setPreferredSize(new Dimension(500, 200));
		jpanel5.setPreferredSize(new Dimension(500, 200));

		root.add(jpanel1, "dock west");
		root.add(jpanel2, "wrap");
		root.add(jpanel3, "wrap");
		root.add(jpanel4, "wrap");
		root.add(jpanel5);

		jpanel1.setLayout(new BorderLayout());
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());
		jpanel4.setLayout(new BorderLayout());
		jpanel5.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");

		JScrollPane informationlistscrollPane = new JScrollPane(informationtable);
		informationlistscrollPane.setPreferredSize(new Dimension(500, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		Object[][] informationdata = { { "First name", "" }, { "Last name", "" }, { "Birth", "" }, { "death", "" },
				{ "Address", "" } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true);
		informationtable.setRowHeight(30);

		JLabel relevantlabel = new JLabel("Relevant personnel:");

		JScrollPane relevantlistscrollPane = new JScrollPane(relevanttable);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		Object[][] relevantdata = { { "Father", "", "" }, { "Mother", " ", "" },

				{ "Spouse", "", "" },

		};

		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true);
		relevanttable.setRowHeight(30);

		// brother and sister
		JLabel brosislabel = new JLabel("brother and sister:");
		JTable brosistable = new JTable(brosistableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane brosisscrollPane = new JScrollPane(brosistable);
		brosisscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel4.add(brosislabel, BorderLayout.NORTH);
		jpanel4.add(brosisscrollPane, BorderLayout.CENTER);

		brosistable.setFillsViewportHeight(true);
		brosistable.setRowSelectionAllowed(true);
		brosistable.setRowHeight(30);

		brosistableModel.addColumn("First Name");
		brosistableModel.addColumn("Last Name");

		brosistable.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		brosistable.getColumnModel().getColumn(0).setPreferredWidth(110);

		// children
		JLabel childrenlabel = new JLabel("children:");
		JTable childrentable = new JTable(childrentableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane childrenscrollPane = new JScrollPane(childrentable);
		childrenscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel5.add(childrenlabel, BorderLayout.NORTH);
		jpanel5.add(childrenscrollPane, BorderLayout.CENTER);

		childrentable.setFillsViewportHeight(true);
		childrentable.setRowSelectionAllowed(true);
		childrentable.setRowHeight(30);

		childrentableModel.addColumn("First Name");
		childrentableModel.addColumn("Last Name");

		childrentable.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		childrentable.getColumnModel().getColumn(0).setPreferredWidth(110);

		initToolBarSpouse();

		initTable();

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

	private void addTableRowbrosisList(Person item) {

		Vector<Object> rowData = new Vector<>();

		rowData.add(item.firstname);
		rowData.add(item.lastname);

		brosistableModel.addRow(rowData);
	}

	private void addTableRowchildrenList(Person item) {

		Vector<Object> rowData = new Vector<>();

		rowData.add(item.firstname);
		rowData.add(item.lastname);

		childrentableModel.addRow(rowData); // 添加一行
	}

	private void onAddExistingSpouse() {

		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add this person as spouse?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

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

	public void testhttp() {
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

		JScrollPane informationlistscrollPane = new JScrollPane(informationtable);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		Object[][] informationdata = { { "First name", "" }, { "Last name", "" }, { "Birth", "" }, { "death", "" },
				{ "Address", "" } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true);
		informationtable.setRowHeight(30);

		JLabel relevantlabel = new JLabel("Relevant personnel:");

		JScrollPane relevantlistscrollPane = new JScrollPane(relevanttable);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		Object[][] relevantdata = { { "Father", "", "" }, { "Mother", " ", "" },

				{ "Spouse", "", "" },

		};

		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true);
		relevanttable.setRowHeight(30);

		initToolBarfather();

		initTable();

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

	private void onAddExistingFather() {

		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add this person as father?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			fatheridfilter = (String) tableModel.getValueAt(row, 0);// id
			SelectExistPerson.fatherid = fatheridfilter;
			System.out.println("fatherid:" + fatheridfilter);
			TestCallHttpSelectExistingFather.httpURLPOSTCase(personid1, fatherid);

		}

	}

	public void testhttpselectexistfather(String personid) {

		personid1 = personid;

	}

	private void onPersonInformation() {
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			personidfilter = (String) tableModel.getValueAt(row, 0);// id

			JSONObject personObject = null;
			JSONObject fatherObject = null;
			JSONObject motherObject = null;
			JSONObject spouseObject = null;

			String fatherfirstname;
			String fatherlastname;
			String motherfirstname;
			String motherlastname;
			String spousefirstname;
			String spouselastname;

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
			if (father.equals("null")) {
				System.out.println("！！！！！！！！！！！！");
				fatherfirstname = "null";
				fatherlastname = "null";
				System.out.println("father" + father);
			} else {
				System.out.println("father:" + father);
				fatherObject = JSONObject.fromObject(father);
				fatherfirstname = fatherObject.getString("firstName");
				System.out.println("person‘s father firstname：" + fatherfirstname);
				fatherlastname = fatherObject.getString("lastName");
				System.out.println("person‘s father lastname：" + fatherlastname);

			}
			personfatherfirstname = fatherfirstname;
			personfatherlastname = fatherlastname;

			System.out.println("--———————a中的mother(选中的person的mother information)(字符串)++++++------");
			String mother = informationlist.getString("mother");
			if (mother.equals("null")) {
				System.out.println("！！！！！！！！！！！！");
				motherfirstname = "null";
				motherlastname = "null";
				System.out.println("mother" + mother);
			} else {
				System.out.println("mother:" + mother);
				motherObject = JSONObject.fromObject(mother);
				motherfirstname = motherObject.getString("firstName");
				System.out.println("person‘s mother firstname：" + motherfirstname);
				motherlastname = motherObject.getString("lastName");
				System.out.println("person‘s mother lastname：" + motherlastname);

			}

			personmotherfirstname = motherfirstname;
			personmotherlastname = motherlastname;

			System.out.println("--———————a中的spouse(选中的person的spouse information)(字符串)++++++------");
			String spouse = informationlist.getString("spouse");
			if (spouse.equals("null")) {
				System.out.println("！！！！！！！！！！！！");
				spousefirstname = "null";
				spouselastname = "null";
				System.out.println("spouse" + spouse);
			} else {
				System.out.println("spouse:" + spouse);
				spouseObject = JSONObject.fromObject(spouse);
				spousefirstname = spouseObject.getString("firstName");
				System.out.println("person‘s spouse firstname：" + spousefirstname);
				spouselastname = spouseObject.getString("lastName");
				System.out.println("person‘s spouse lastname：" + spouselastname);

			}

			personspousefirstname = spousefirstname;
			personspouselastname = spouselastname;

			System.out.println(
					"--———————a中的brothersAndSisters(选中的person的brothersAndSisters information)(字符串)++++++------");
			String brothersAndSisters = informationlist.getString("brothersAndSisters");
			System.out.println("brothersAndSisters:" + brothersAndSisters);
			if (brothersAndSisters.equals("null") || brothersAndSisters.equals("[]")) {
				brosistableModel.setRowCount(0);// 清空
				JOptionPane.showMessageDialog(null, "The person has no brothers and sisters", "prompt",
						JOptionPane.PLAIN_MESSAGE);
				System.out.println("00000000000000000000000000000000000000");
			} else {
				JSONArray arraybrothersAndSisters = null;
				if (brothersAndSisters.length() != 0) {
					AfJSON afJson = new AfJSON();
					arraybrothersAndSisters = afJson.jsonToArray(brothersAndSisters);
				}
				brosistableModel.setRowCount(0);
				if (!arraybrothersAndSisters.isEmpty()) {
					for (int j = 0; j < arraybrothersAndSisters.size(); j++) {
						JSONObject jsonObject = arraybrothersAndSisters.getJSONObject(j);
						Person person1 = new Person();

						person1.firstname = jsonObject.getString("firstName");
						person1.lastname = jsonObject.getString("lastName");

						addTableRowbrosisList(person1);
					}
				}
			}

			System.out.println("--———————a中的children(选中的person的children information)(字符串)++++++------");
			String children = informationlist.getString("children");
			System.out.println("children:" + children);
			if (children.equals("null") || children.equals("[]")) {
				childrentableModel.setRowCount(0);
				JOptionPane.showMessageDialog(null, "The person has no children", "prompt", JOptionPane.PLAIN_MESSAGE);
				System.out.println("00000000000000000000000000000000000000");
			} else {
				JSONArray arraychildren = null;
				if (children.length() != 0) {
					AfJSON afJson = new AfJSON();
					arraychildren = afJson.jsonToArray(children);
				}
				childrentableModel.setRowCount(0);
				if (!arraychildren.isEmpty()) {
					for (int j = 0; j < arraychildren.size(); j++) {
						JSONObject jsonObject = arraychildren.getJSONObject(j);
						Person person1 = new Person();

						person1.firstname = jsonObject.getString("firstName");
						person1.lastname = jsonObject.getString("lastName");

						addTableRowchildrenList(person1);
					}
				}
			}

		}
		Test t1 = new Test();
		t1.ttest(personfirstname, personlastname, personbirth, persondeath, personaddress);
		Object[][] informationdata = { { "First name", personfirstname }, { "Last name", personlastname },
				{ "Birth", personbirth }, { "death", persondeath }, { "Address", personaddress } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true);
		informationtable.setRowHeight(30);

		t1.test2(personfatherfirstname, personfatherlastname);
		t1.test2(personmotherfirstname, personmotherlastname);
		t1.test2(personspousefirstname, personspouselastname);
		Object[][] relevantdata = { { "Father", personfatherfirstname, personfatherlastname },
				{ "Mother", personmotherfirstname, personmotherlastname },

				{ "Spouse", personspousefirstname, personspouselastname },

		};
		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true);
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

		jpanel1.setLayout(new BorderLayout());
		jpanel2.setLayout(new BorderLayout());
		jpanel3.setLayout(new BorderLayout());

		JLabel informationlabel = new JLabel("Information:");

		JScrollPane informationlistscrollPane = new JScrollPane(informationtable);
		informationlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel2.add(informationlabel, BorderLayout.NORTH);
		jpanel2.add(informationlistscrollPane, BorderLayout.CENTER);

		Object[][] informationdata = { { "First name", "" }, { "Last name", "" }, { "Birth", "" }, { "death", "" },
				{ "Address", "" } };
		informationmodel.setDataVector(informationdata, informationcolumnNames);
		informationtable.setModel(informationmodel);
		informationtable.setFillsViewportHeight(true);
		informationtable.setRowSelectionAllowed(true);
		informationtable.setRowHeight(30);

		JLabel relevantlabel = new JLabel("Relevant personnel:");

		JScrollPane relevantlistscrollPane = new JScrollPane(relevanttable);
		relevantlistscrollPane.setPreferredSize(new Dimension(600, 180));
		jpanel3.add(relevantlabel, BorderLayout.NORTH);
		jpanel3.add(relevantlistscrollPane, BorderLayout.CENTER);

		Object[][] relevantdata = { { "Father", "", "" }, { "Mother", " ", "" },

				{ "Spouse", "", "" },

		};

		relevantmodel.setDataVector(relevantdata, relevantcolumnNames);
		relevanttable.setModel(relevantmodel);
		relevanttable.setFillsViewportHeight(true);
		relevanttable.setRowSelectionAllowed(true);
		relevanttable.setRowHeight(30);

		initToolBarmother();

		initTable();

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

		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		int select = JOptionPane.showConfirmDialog(this, "Are you sure to add this person as mother?", "OK",
				JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return;

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

	private void initToolBarSpouse() {
		JToolBar toolBar = new JToolBar();
		jpanel1.add(toolBar, BorderLayout.NORTH);

		toolBar.setFloatable(false);

		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		toolBar.add(okButton);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");
			if (str1.length() == 0) {

				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);

				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {

						String str = tt[i];

						if (str.indexOf(" ") != -1) {
							System.out.println("There are spaces!");

							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("There are no spaces");
							first_name = str;
						}

					} else {
						String str = tt[i];
						last_name = str;
					}

				}
				System.out.println(first_name + "-----------" + last_name);
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
		toolBar.add(okButton);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");
			if (str1.length() == 0) {

				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);

				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {

						String str = tt[i];

						if (str.indexOf(" ") != -1) {
							System.out.println("There are spaces!");

							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("There are no spaces");
							first_name = str;
						}

					} else {
						String str = tt[i];
						last_name = str;
					}

				}
				System.out.println(first_name + "-----------" + last_name);
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
		toolBar.add(okButton);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {

			String condition = searchField.getText().trim();

			String str1 = condition;
			str1 = str1.replaceAll("[0-9]", "");
			if (str1.length() == 0) {

				testsearchhttp(condition);
			} else {
				String fullname = condition;
				System.out.println(fullname);

				String[] tt = fullname.split(",");
				String last_name = null;
				String first_name = null;
				int i;
				for (i = 0; i < tt.length; i++) {
					if (i == 0) {

						String str = tt[i];

						if (str.indexOf(" ") != -1) {
							System.out.println("There are spaces!");

							String s = str.replace(" ", "%20");
							first_name = s;
						} else {
							System.out.println("There are no spaces");
							first_name = str;
						}

//Gustav Carl Diederich,Henning

					} else {
						String str = tt[i];
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

		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		jpanel1.add(scrollPane, BorderLayout.CENTER);// ml1 miglagout
		scrollPane.setPreferredSize(new Dimension(475, 785));

		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
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

	public void testsearchhttp(String filter) {

		if (filter.length() == 0) {

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

}
