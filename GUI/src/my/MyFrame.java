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

	JButton addButton, deleteButton, editButton;
	JTextField searchField = new JTextField();

	public MyFrame(String title) {
		super(title);
		root.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Content Pane
		this.setContentPane(root);

		MigLayout ml = new MigLayout();
		root.setLayout(ml);
		root.add(tablePanel, "dock west");

		// pedigree
		JTextField parent;
		// Set the first column
		parent = new JTextField();
		root.add(parent, "cell 1 15");
		parent.setColumns(15);
		// Set the second column
		for (int i = 7; i < 30; i += 16) {
			JTextField textField = new JTextField();
			root.add(textField, "cell 2 " + i);
			textField.setColumns(15);
		}
		// Set the third column
		for (int i = 3; i < 30; i += 8) {
			JTextField textField = new JTextField();
			root.add(textField, "cell 3 " + i);
			textField.setColumns(15);
		}
		// Set the fourth column
		for (int i = 1; i < 30; i += 4) {
			JTextField textField = new JTextField();
			root.add(textField, "cell 4 " + i);
			textField.setColumns(15);
		}
		// Set the fifth column
		for (int i = 0; i < 31; i += 2) {
			JTextField textField = new JTextField();
			root.add(textField, "cell 5 " + i);
			textField.setColumns(15);
		}

		MigLayout ml1 = new MigLayout();
		tablePanel.setLayout(ml1);

		// Add menubar
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);

		// Menu-File
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

		// Menu-Edit
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);

		JMenu editDelete = new JMenu("Delete");// Sub-menu exists
		JMenu editUnlink = new JMenu("Unlink");// Sub-menu exists

		// Sub-menu
		JMenuItem editDeletePerson = new JMenuItem("Person");
		JMenuItem editDeleteFamily = new JMenuItem("Family");
		JMenuItem editUnlinkFromParent = new JMenuItem("From Parent");
		JMenuItem editUnlinkFromSpouse = new JMenuItem("From Spouse");

		editDelete.add(editDeletePerson);
		editDelete.add(editDeleteFamily);
		editUnlink.add(editUnlinkFromParent);
		editUnlink.add(editUnlinkFromSpouse);

		editMenu.add(editDelete);
		editMenu.add(editUnlink);

		editDeletePerson.addActionListener((e) -> {

			int[] rows = table.getSelectedRows();
			if (rows.length == 0)
				return;

			// Pop-up dialog to confirm
			int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "OK",
					JOptionPane.YES_NO_OPTION);
			if (select != 0)
				return; // Button 0 is the'OK' button

			for (int i = rows.length - 1; i >= 0; i--) {
				int row = rows[i];
				String condition = (String) tableModel.getValueAt(row, 0);
				testhttpdeleteperson(condition);
			}

		});
		editUnlinkFromParent.addActionListener((e) -> {
			onUnlinkFromParent();
		});
		editUnlinkFromSpouse.addActionListener((e) -> {
			onUnlinkFromSpouse();
		});

		// Menu-List
		JMenu listMenu = new JMenu("List");
		menubar.add(listMenu);

		JMenuItem listAddress = new JMenuItem("Address List");

		listMenu.add(listAddress);

		listAddress.addActionListener((e) -> {
			onListAddress();

		});

		// Menu-Add
		JMenu addMenu = new JMenu("Add");
		menubar.add(addMenu);
		JMenuItem addIndividual = new JMenuItem("Individual");
		JMenuItem addSpouse = new JMenuItem("Spouse");
		JMenuItem addParents = new JMenuItem("Parents");
		JMenuItem addChild = new JMenuItem("Child");

		addIndividual.addActionListener((e) -> {
			onAddIndividual();
		});

		addSpouse.addActionListener((e) -> {
			onAddSpouse();
		});

		addParents.addActionListener((e) -> {
			onAddFather();
			onAddMother();

		});
		addChild.addActionListener((e) -> {
			onAddChild();

		});

		addMenu.add(addIndividual);
		addMenu.add(addSpouse);
		addMenu.add(addParents);
		addMenu.add(addChild);

		// Menu-Search
		JMenu familyMenu = new JMenu("Family");
		menubar.add(familyMenu);

		JMenuItem FamilyList = new JMenuItem("Family List");

		familyMenu.add(FamilyList);

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

		// Load interface person list file
		testhttp();

	}

	public void paint(Graphics g) {
		super.paint(g);
		// Draw a connecting line, x1 y1 x2 y2 respectively represent the two ends of
		// the line. The upper left corner is 0, 0 points, gradually increasing x to the
		// right, and gradually increasing y down

		final BasicStroke wideStroke = new BasicStroke(2.0f);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(wideStroke);

		// 1
		g2.drawLine(580, 280, 580, 500);
		g2.drawLine(580, 280, 670, 280);

		g2.drawLine(580, 540, 580, 760);
		g2.drawLine(580, 760, 670, 760);

		// 2
		g2.drawLine(775, 160, 865, 160);
		g2.drawLine(775, 160, 775, 260);

		g2.drawLine(775, 395, 865, 395);
		g2.drawLine(775, 395, 775, 295);

		g2.drawLine(775, 640, 865, 640);
		g2.drawLine(775, 640, 775, 740);

		g2.drawLine(775, 880, 865, 880);
		g2.drawLine(775, 880, 775, 780);

		// 3
		g2.drawLine(970, 100, 1060, 100);
		g2.drawLine(970, 100, 970, 140);

		g2.drawLine(970, 175, 970, 215);
		g2.drawLine(970, 215, 1060, 215);

		g2.drawLine(970, 340, 1060, 340);
		g2.drawLine(970, 340, 970, 380);

		g2.drawLine(970, 415, 970, 455);
		g2.drawLine(970, 455, 1060, 455);

		g2.drawLine(970, 580, 1060, 580);
		g2.drawLine(970, 580, 970, 620);

		g2.drawLine(970, 655, 970, 695);
		g2.drawLine(970, 695, 1060, 695);

		g2.drawLine(970, 820, 1060, 820);
		g2.drawLine(970, 820, 970, 860);

		g2.drawLine(970, 895, 970, 935);
		g2.drawLine(970, 935, 1060, 935);

		// 4
		g2.drawLine(1165, 68, 1255, 68);
		g2.drawLine(1165, 68, 1165, 85);

		g2.drawLine(1165, 110, 1165, 130);
		g2.drawLine(1165, 130, 1255, 130);

		g2.drawLine(1165, 188, 1255, 188);
		g2.drawLine(1165, 188, 1165, 205);

		g2.drawLine(1165, 230, 1165, 250);
		g2.drawLine(1165, 250, 1255, 250);

		g2.drawLine(1165, 308, 1255, 308);
		g2.drawLine(1165, 308, 1165, 325);

		g2.drawLine(1165, 350, 1165, 370);
		g2.drawLine(1165, 370, 1255, 370);

		g2.drawLine(1165, 428, 1255, 428);
		g2.drawLine(1165, 428, 1165, 445);

		g2.drawLine(1165, 470, 1165, 490);
		g2.drawLine(1165, 490, 1255, 490);

		g2.drawLine(1165, 548, 1255, 548);
		g2.drawLine(1165, 548, 1165, 564);

		g2.drawLine(1165, 590, 1165, 610);
		g2.drawLine(1165, 610, 1255, 610);

		g2.drawLine(1165, 668, 1255, 668);
		g2.drawLine(1165, 668, 1165, 685);

		g2.drawLine(1165, 710, 1165, 730);
		g2.drawLine(1165, 730, 1255, 730);

		g2.drawLine(1165, 788, 1255, 788);
		g2.drawLine(1165, 788, 1165, 804);

		g2.drawLine(1165, 830, 1165, 850);
		g2.drawLine(1165, 850, 1255, 850);

		g2.drawLine(1165, 908, 1255, 908);
		g2.drawLine(1165, 908, 1165, 925);

		g2.drawLine(1165, 950, 1165, 970);
		g2.drawLine(1165, 970, 1255, 970);

	}

	private void initTable() {
		// Create a JTable, directly override isCellEditable() and set it as
		// non-editable
		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane);// miglagout
		scrollPane.setPreferredSize(new Dimension(475, 925));

		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true); // Whole line selection
		table.setRowHeight(30);

		// Column setting: add 7 columns
		tableModel.addColumn("Person ID");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Sex");
		tableModel.addColumn("Birth Date");
		tableModel.addColumn("Death Date");
		tableModel.addColumn("Home Address");

		// Column settings: custom drawing
		table.getColumnModel().getColumn(3).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110); // The width of the column
	}

	private void initToolBar() {
		JToolBar toolBar = new JToolBar();

		tablePanel.add(toolBar, "dock north");// miglagout

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

			// Pop-up dialog to confirm
			int select = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "OK",
					JOptionPane.YES_NO_OPTION);
			if (select != 0)
				return;

			for (int i = rows.length - 1; i >= 0; i--) {
				int row = rows[i];
				// Delete the record from the dataList by id
				String condition = (String) tableModel.getValueAt(row, 0);
				testhttpdeleteperson(condition);
			}
		});

		editButton = createToolButton("Edit", "ic_edit.png");
		toolBar.add(editButton);
		editButton.addActionListener((e) -> {
			// onEdit();
		});

		toolBar.addSeparator(new Dimension(40, 10));

		toolBar.add(new JLabel("Search"));
		toolBar.add(searchField);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {
			// Trigger the event when you press enter
			// onSearch();
			String condition = searchField.getText().trim();
			testsearchhttp(condition);
		});
	}

	protected JButton createToolButton(String text, String icon) {
		// icon
		String imagePath = "/icons/" + icon;
		URL imageURL = getClass().getResource(imagePath);

		// Create button
		JButton button = new JButton(text);

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

	// Set the value of a record in the table control
	private void setTableRow(Person v, int row) {
		tableModel.setValueAt(v.personid, row, 0);
		tableModel.setValueAt(v.firstname, row, 1);
		tableModel.setValueAt(v.lastname, row, 2);
		tableModel.setValueAt(v.sex, row, 3);
		tableModel.setValueAt(v.birthdate, row, 4);
		tableModel.setValueAt(v.deathdate, row, 5);
		tableModel.setValueAt(v.homeaddress, row, 6);
	}

	// Add a record to dataList
	private void addToDataList(Person s) {
		dataList.add(s);
	}

	// Modify a record
	private void updateToDataList(String firstname, Person s) {
		for (int i = 0; i < dataList.size(); i++) {
			Person item = dataList.get(i);
			if (item.firstname.equals(firstname)) {
				dataList.set(i, s);
			}
		}
	}

	// Delete a record from the dataList
	private void removeFromDataList(String firstname) {
		Iterator<Person> iter = dataList.iterator();
		while (iter.hasNext()) {
			Person s = iter.next();
			if (s.firstname.equals(firstname)) {
				iter.remove();
				break;
			}
		}
	}

	private void onAddIndividual() {
		AddIndividual ai = new AddIndividual(this);
		if (ai.exec()) {
			Person person = ai.getValue();
			String firstname = person.firstname;
			String lastname = person.lastname;
			boolean sex1 = person.sex;
			String sex = String.valueOf(sex1);
			String birth = person.birthdate;
			String death = person.deathdate;
			String address = person.homeaddress;
			ai.testhttpaddperson(firstname, lastname, sex, birth, death, address);

		}
	}

//	// 点 '删除' 按钮
//	private void onDelete() {
//		// 获取选中的行的索引
//		int[] rows = table.getSelectedRows();
//		if (rows.length == 0)
//			return;
//
//		// 弹出对话框确认
//		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
//		if (select != 0)
//			return; // 0号按钮是'确定'按钮
//
//		// 技巧：从后往前删除
//		for (int i = rows.length - 1; i >= 0; i--) {
//			int row = rows[i];
//
//			// 按id，从dataList中删除该条记录
//			String personid = (String) tableModel.getValueAt(row, 0);
//			removeFromDataList(personid);
//
//			// 从tableModel中删除该条记录
//			tableModel.removeRow(row);
//
//		}
//
//		saveData(); // 保存到文件
//	}
//
//	// 点 '编辑' 按钮
//	private void onEdit() {
//		// 获取选中的行的索引
//		int[] rows = table.getSelectedRows();
//		if (rows.length == 0)
//			return;
//
//		// 取得选中的行
//		int row = rows[0]; // 只编辑选中的第一行
//		Person s = getTableRow(row);
//
//		// 弹出编辑对话框
//		AddIndividual ai = new AddIndividual(this);
//		// 设置初始值
//		ai.setValue(s);
//		if (ai.exec()) {
//			Person os = ai.getValue();
//
//			// 更新到 Model
//			setTableRow(os, row);
//			// 更新到dataList
//			updateToDataList(os.personid, os);
//
//			saveData(); // 保存到文件
//		}
//	}
//
//	private void onSearch() {
//		// 获取用户输入的过滤条件
//		String filter = searchField.getText().trim();
//
//		if (filter.length() == 0) // 过滤条件为空
//		{
//			// 恢复原始数据
//			tableModel.setRowCount(0);// 清空
//			for (Person s : dataList) {
//				addTableRow(s);
//			}
//			this.addButton.setEnabled(true);
//			return;
//		}
//
//		// 把符合条件的记录显示在表格里
//		tableModel.setRowCount(0);// 清空
//		for (Person s : dataList) {
//			if (s.personid.indexOf(filter) >= 0) {
//				addTableRow(s);
//			}
//		}
//
//		// 把其他操作按钮禁用
//		this.addButton.setEnabled(false);
//
//	}

	private void onAddFather() {
		AddFather af = new AddFather(this);
		if (af.exec()) {

		}
	}

	private void onAddMother() {
		AddMother am = new AddMother(this);
		if (am.exec()) {

		}
	}

	private void onAddSpouse() {
		AddSpouse as = new AddSpouse(this);
		if (as.exec()) {

		}
	}

	private void onAddChild() {
		AddChild ac = new AddChild(this);
		if (ac.exec()) {

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

	private void onFamilyList() {
		FamilyList fl = new FamilyList(this);
		if (fl.exec()) {

		}
	}

	private void onUnlinkFromParent() {
		EditUnlinkFromParent eufp = new EditUnlinkFromParent(this);
		if (eufp.exec()) {

		}
	}

	private void onUnlinkFromSpouse() {
		EditUnlinkFromSpouse eufs = new EditUnlinkFromSpouse(this);
		if (eufs.exec()) {

		}
	}

//	// 保存数据
//	private void saveData() {
//		// 构造一个 JSON 数组
//		JSONArray array = new JSONArray();

//		for(int i=0; i<dataList.size(); i++)
//		{
//			Person s = dataList.get(i);
//			JSONObject j1 = new JSONObject();
//			j1.put("personid", s.personid);
//			j1.put("firstname", s.firstname);
//			j1.put("lastname", s.lastname);
//			//j1.put("nickname", s.nickname);
//			j1.put("sex", s.sex);
//			j1.put("birthdate", s.birthdate);
//			j1.put("deathdate", s.deathdate);
//			j1.put("homeaddress", s.homeaddress);
////			
//			array.put( j1 );
//
//		}
//
//		// 将JSON对象保存到文件
//		File file = new File("personlist.json");
//		try {
////			AfJSON.toFile(array, file, "UTF-8");
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	private void loadData() {
//		// 加载数据
//		File file = new File("personlist.json");
//		if (!file.exists())
//			return;
//
//		JSONArray array = null;
//		try {
////			array = (JSONArray) AfJSON.fromFile(file, "UTF-8");
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, e.getMessage());
//			e.printStackTrace();
//			return;
//		}
//		// 显示到表格
//		dataList.clear();
//		tableModel.setRowCount(0); // 清空
//		for (int i = 0; i < array.size(); i++) {
//			JSONObject j1 = array.getJSONObject(i);
//			Person s = new Person();
//
//			s.personid = Integer.toString(j1.getInt("personId"));
//			s.firstname = j1.getString("firstName");
//			s.lastname = j1.getString("lastName");
//			// s.nickname = j1.getString("nickname");
//			String str = j1.getString("sex");
//			s.sex = "male".equals(str) ? true : false;
//			// s.sex = j1.getBoolean("sex");
//			s.birthdate = Integer.toString(j1.getInt("birth"));
//			s.deathdate = Integer.toString(j1.getInt("death"));
//			s.homeaddress = j1.getString("address");
//
//			addToDataList(s);
//			addTableRow(s);
//		}
//	}

	// Display all Person data
	public void testhttp() {
		TestCallHttpList tchpl = new TestCallHttpList();
		// Get server response result
		JSONArray array = tchpl.httpURLGETCase("http://3.9.172.108:8090/api/person/list");
		// Display the returned person list information
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
				// Add person to the table
				addTableRow(person);
			}
		}
	}

	// Find person (according to id)
	public void testsearchhttp(String filter) {
		// Filter condition is empty
		if (filter.length() == 0) {
			// Display all data of the interface
			TestCallHttpList tchpl = new TestCallHttpList();
			tchpl.httpURLGETCase(null);
		} else {
			tableModel.setRowCount(0);
			TestCallHttpPersonSearch tchps = new TestCallHttpPersonSearch();
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
				// Add person to the table
				addTableRow(person);
			}
		}

	}

	// delete person

	public void testhttpdeleteperson(String filter) {
		// TODO Auto-generated method stub

		TestCallHttpPersonDelect tchpd = new TestCallHttpPersonDelect();
		tchpd.httpURLGETCase(filter);
	}

}