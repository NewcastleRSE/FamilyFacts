package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.table.DefaultTableModel;

import af.swing.layout.AfColumnLayout;
import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyFrame extends JFrame {

	JPanel root = new JPanel();
	JTable table = null;// 左侧表格
	JPanel tablePanel = new JPanel();
	// dataList: 维护所有记录 , tableModel: 要显示出来的记录
	List<Person> dataList = new ArrayList<>();
	// 表格模型 DefaultTableModel
	DefaultTableModel tableModel = new DefaultTableModel();

	JButton addButton, deleteButton, editButton;
	JTextField searchField = new JTextField();
	// static Graphics g;

//	选项卡窗体
//	选项卡面板 JTabbedPane
//	选项卡面板组件
	static GridLayout layout = new GridLayout(2, 1);// familyPanel

	static JTable familytable = null;
	static DefaultTableModel familytableModel = new DefaultTableModel();

	static MigLayout ml = new MigLayout();

	static JPanel pedigreePanel = new JPanel(ml);

	static JPanel familyPanel = new JPanel(layout);
	static JTabbedPane tabbedPane = buildJTabbedPane(pedigreePanel, familyPanel);

	public MyFrame(String title) {
		super(title);

		// Content Pane
		this.setContentPane(root);
		root.setLayout(new BorderLayout(5, 5));
		root.add(tabbedPane);

		root.add(tablePanel, BorderLayout.WEST);
		tablePanel.setPreferredSize(new Dimension(500, 0));// nice
		tablePanel.setLayout(new BorderLayout());

		// 添加菜单
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);

		// 菜单 File
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

		// Edit 菜单
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);

		JMenu editDelete = new JMenu("Delete");// 存在子菜单
		JMenu editUnlink = new JMenu("Unlink");// 存在子菜单
		JMenuItem editSwap = new JMenuItem("Swap Husband and wife");

		// 子菜单
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
		editMenu.add(editSwap);

		editDeletePerson.addActionListener((e) -> {
			JOptionPane.showConfirmDialog(this, "请选择一个人", "消息标题", JOptionPane.CLOSED_OPTION);
			onDelete();

		});

		editSwap.addActionListener((e) -> {
			oneditswap();

		});

		// List 菜单
		JMenu listMenu = new JMenu("List");
		menubar.add(listMenu);

		JMenuItem listAddress = new JMenuItem("Address List");
		JMenuItem listRepository = new JMenuItem("Repository List");

		listMenu.add(listAddress);
		listMenu.add(listRepository);

		listAddress.addActionListener((e) -> {
			onListAddress();

		});

		listRepository.addActionListener((e) -> {
			onListRepository();

		});

		// Add 菜单
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

		// View 菜单
		JMenu viewMenu = new JMenu("View");
		menubar.add(viewMenu);
		JMenuItem viewPedigree = new JMenuItem("Pedigree");
		JMenuItem viewFamily = new JMenuItem("Family");
		viewMenu.add(viewPedigree);
		viewMenu.add(viewFamily);

		viewFamily.addActionListener((e) -> {
			// buildJTabbedPane

		});

		// Search 菜单
		JMenu searchMenu = new JMenu("Search");
		menubar.add(searchMenu);
		JMenuItem searchPersonList = new JMenuItem("Person List");
		JMenuItem searchFamilyList = new JMenuItem("Family List");
		searchMenu.add(searchPersonList);
		searchMenu.add(searchFamilyList);

		searchPersonList.addActionListener((e) -> {
			onSearchPersonList();

		});

		searchFamilyList.addActionListener((e) -> {
			onSearchFamilyList();

		});

		// 菜单事件响应
		fileExitCmd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// 初始化工具栏
		initToolBar();

		// 表格初始化（主页面左侧）
		initTable();

		// 加载文件
		// loadData();

		testhttp();

		// testsearchhttp();//查找测试

	}

	private static JTabbedPane buildJTabbedPane(JPanel pedigreePanel, JPanel familyPanel) {

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Pedigree", pedigreePanel);
		tabbedPane.add("Family", familyPanel);

		PedigreePanel p = new PedigreePanel(pedigreePanel);
//        PedigreePanel p=new PedigreePanel();
//        pedigreePanel.add(p);
//        pedigreePanel.add(new PedigreePanel());
//        pedigreePanel.setVisible(true);

//		JTextField parent;
//
//		parent = new JTextField();
//		pedigreePanel.add(parent, "cell 0 15");
//		parent.setColumns(15);
//		
//		
//		JTextField ptextField1 = new JTextField();
//		pedigreePanel.add(ptextField1, "cell 1 7");
//		ptextField1.setColumns(15);
//		JTextField ptextField2 = new JTextField();
//		pedigreePanel.add(ptextField2, "cell 1 23");
//		ptextField2.setColumns(15);
//
//		for (int i = 3; i < 28; i += 8) {
//			JTextField textField = new JTextField();
//			pedigreePanel.add(textField, "cell 2 " + i);
//			textField.setColumns(15);
//		}
//		for (int i = 1; i < 30; i += 4) {
//			JTextField textField = new JTextField();
//			pedigreePanel.add(textField, "cell 3 " + i);
//			textField.setColumns(15);
//		}
//		for (int i = 0; i < 31; i += 2) {
//			JTextField textField = new JTextField();
//			pedigreePanel.add(textField, "cell 4 " + i);
//			textField.setColumns(15);
//		}
//		

//		JTextField gtextField1 = new JTextField();
//		pedigreePanel.add(gtextField1, "cell 2 3");
//		gtextField1.setColumns(15);
//		JTextField gtextField2 = new JTextField();
//		pedigreePanel.add(gtextField2, "cell 2 11");
//		gtextField2.setColumns(15);
//		JTextField gtextField3 = new JTextField();
//		pedigreePanel.add(gtextField3, "cell 2 19");
//		gtextField3.setColumns(15);
//		JTextField gtextField4 = new JTextField();
//		pedigreePanel.add(gtextField4, "cell 2 27");
//		gtextField4.setColumns(15);
//		
//		
//		JTextField ggtextField1 = new JTextField();
//		pedigreePanel.add(ggtextField1, "cell 3 1");
//		ggtextField1.setColumns(15);
//		JTextField ggtextField2 = new JTextField();
//		pedigreePanel.add(ggtextField2, "cell 3 5");
//		ggtextField2.setColumns(15);
//		JTextField ggtextField3 = new JTextField();
//		pedigreePanel.add(ggtextField3, "cell 3 9");
//		ggtextField3.setColumns(15);
//		JTextField ggtextField4 = new JTextField();
//		pedigreePanel.add(ggtextField4, "cell 3 13");
//		ggtextField4.setColumns(15);
//		JTextField ggtextField5 = new JTextField();
//		pedigreePanel.add(ggtextField5, "cell 3 17");
//		ggtextField5.setColumns(15);
//		JTextField ggtextField6 = new JTextField();
//		pedigreePanel.add(ggtextField6, "cell 3 21");
//		ggtextField6.setColumns(15);
//		JTextField ggtextField7 = new JTextField();
//		pedigreePanel.add(ggtextField7, "cell 3 25");
//		ggtextField7.setColumns(15);
//		JTextField ggtextField8 = new JTextField();
//		pedigreePanel.add(ggtextField8, "cell 3 29");
//		ggtextField8.setColumns(15);
//		
//		JTextField gggtextField1 = new JTextField();
//		pedigreePanel.add(gggtextField1, "cell 4 0");
//		gggtextField1.setColumns(15);
//		JTextField gggtextField2 = new JTextField();
//		pedigreePanel.add(gggtextField2, "cell 4 2");
//		gggtextField2.setColumns(15);
//		JTextField gggtextField3 = new JTextField();
//		pedigreePanel.add(gggtextField3, "cell 4 4");
//		gggtextField3.setColumns(15);
//		JTextField gggtextField4 = new JTextField();
//		pedigreePanel.add(gggtextField4, "cell 4 6");
//		gggtextField4.setColumns(15);
//		JTextField gggtextField5 = new JTextField();
//		pedigreePanel.add(gggtextField5, "cell 4 8");
//		gggtextField5.setColumns(15);
//		JTextField gggtextField6 = new JTextField();
//		pedigreePanel.add(gggtextField6, "cell 4 10");
//		gggtextField6.setColumns(15);
//		JTextField gggtextField7 = new JTextField();
//		pedigreePanel.add(gggtextField7, "cell 4 12");
//		gggtextField7.setColumns(15);
//		JTextField gggtextField8 = new JTextField();
//		pedigreePanel.add(gggtextField8, "cell 4 14");
//		gggtextField8.setColumns(15);
//		JTextField gggtextField9 = new JTextField();
//		pedigreePanel.add(gggtextField9, "cell 4 16");
//		gggtextField9.setColumns(15);
//		JTextField gggtextField10 = new JTextField();
//		pedigreePanel.add(gggtextField10, "cell 4 18");
//		gggtextField10.setColumns(15);
//		JTextField gggtextField11 = new JTextField();
//		pedigreePanel.add(gggtextField11, "cell 4 20");
//		gggtextField11.setColumns(15);
//		JTextField gggtextField12 = new JTextField();
//		pedigreePanel.add(gggtextField12, "cell 4 22");
//		gggtextField12.setColumns(15);
//		JTextField gggtextField13 = new JTextField();
//		pedigreePanel.add(gggtextField13, "cell 4 24");
//		gggtextField13.setColumns(15);
//		JTextField gggtextField14 = new JTextField();
//		pedigreePanel.add(gggtextField14, "cell 4 26");
//		gggtextField14.setColumns(15);
//		JTextField gggtextField15 = new JTextField();
//		pedigreePanel.add(gggtextField15, "cell 4 28");
//		gggtextField15.setColumns(15);
//		JTextField gggtextField16 = new JTextField();
//		pedigreePanel.add(gggtextField16, "cell 4 30");
//		gggtextField16.setColumns(15);

		GridLayout nfamilyPanellayout = new GridLayout(2, 2);// nfamilyPanellayout
		JPanel nfamilyPanel = new JPanel(nfamilyPanellayout);
		familyPanel.add(nfamilyPanel);
		JPanel nfamilyPanel1 = new JPanel();// z1
		JPanel nfamilyPanel2 = new JPanel();// y2
		JPanel nfamilyPanel3 = new JPanel();// z3
		JPanel nfamilyPanel4 = new JPanel();// y4
		nfamilyPanel.add(nfamilyPanel1);
		nfamilyPanel.add(nfamilyPanel2);
		nfamilyPanel.add(nfamilyPanel3);
		nfamilyPanel.add(nfamilyPanel4);

		nfamilyPanel1.setLayout(new BorderLayout());
		nfamilyPanel3.setLayout(new BorderLayout());

		JPanel ntfamilyPanel1 = new JPanel();// zs1
		JPanel ntfamilyPanel2 = new JPanel();// zx3
		nfamilyPanel1.add(ntfamilyPanel1, BorderLayout.NORTH);
		nfamilyPanel3.add(ntfamilyPanel2, BorderLayout.NORTH);

		JButton buttontest1 = new JButton("1");

		JButton buttontest3 = new JButton("3");

		JLabel label1 = new JLabel("Father:");
		JLabel label2 = new JLabel("Father's Parents:");
		JLabel label3 = new JLabel("Mother:");
		JLabel label4 = new JLabel("Mother's Parents:");

		ntfamilyPanel1.add(label1);// z1
		ntfamilyPanel1.add(buttontest1);// z1
		ntfamilyPanel1.add(label3);// z3
		ntfamilyPanel1.add(buttontest3);// z3

		JList addnFamilylist1 = new JList();
		String[] dd = { "XXX(name)" };
		addnFamilylist1 = new JList(dd);
		addnFamilylist1.setVisibleRowCount(2);
		JScrollPane nscrollPane1 = new JScrollPane(addnFamilylist1);
		nscrollPane1.setPreferredSize(new Dimension(300, 100));
		nfamilyPanel1.add(nscrollPane1, BorderLayout.CENTER);

		JList addnFamilylist2 = new JList();
		String[] ddd = { "XXX(name)!!" };
		addnFamilylist2 = new JList(ddd);
		addnFamilylist2.setVisibleRowCount(2);
		JScrollPane nscrollPane2 = new JScrollPane(addnFamilylist2);
		nscrollPane2.setPreferredSize(new Dimension(300, 100));
		nfamilyPanel3.add(nscrollPane2, BorderLayout.CENTER);

		nfamilyPanel2.setLayout(new AfColumnLayout(10));
		JTextField ffatherfield = new JTextField("+ Click to add Father", 20);
		JTextField mfatherfield = new JTextField("+ Click to add Mother", 20);
		nfamilyPanel2.add(label2);// y2
		nfamilyPanel2.add(ffatherfield);
		nfamilyPanel2.add(mfatherfield);

		nfamilyPanel4.setLayout(new AfColumnLayout(10));
		JTextField fmotherfield = new JTextField("+ Click to add Father", 20);
		JTextField mmotherfield = new JTextField("+ Click to add Mother", 20);
		nfamilyPanel4.add(label4);// y2
		nfamilyPanel4.add(fmotherfield);
		nfamilyPanel4.add(mmotherfield);

		// Family List 下半部分表格
		JPanel sfamilyPanel = new JPanel();
		familytable = new JTable(familytableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane sscrollfamilyPane = new JScrollPane(familytable);
		familyPanel.add(sfamilyPanel.add(sscrollfamilyPane));
		// 添加到主界面
		familytable.setFillsViewportHeight(true);
		familytable.setRowSelectionAllowed(true); // 整行选择
		familytable.setRowHeight(30);

		// 列设置：添加5列
		familytableModel.addColumn("Children");
		familytableModel.addColumn("Sex");
		familytableModel.addColumn("Birth Date");
		familytableModel.addColumn("Birth Place");
		familytableModel.addColumn("Death Date");
		familytableModel.addColumn("Death Place");
		familytableModel.addColumn("Relation");

		// 列设置：自定义绘制
		// table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
		familytable.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		familytable.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度

		return tabbedPane;

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
		tablePanel.add(scrollPane);
		// scrollPane.setPreferredSize(new Dimension(475, 925));

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

	private void initToolBar() {
		JToolBar toolBar = new JToolBar();
		// tablePanel.add(toolBar);
		tablePanel.add(toolBar, BorderLayout.PAGE_START);
		toolBar.setFloatable(false);

		// 按钮
		addButton = createToolButton("添加", "ic_add.png");
		toolBar.add(addButton);
		addButton.addActionListener((e) -> {
			onAddIndividual();
		});

		// 按钮
		deleteButton = createToolButton("删除", "ic_delete.png");
		toolBar.add(deleteButton);
		deleteButton.addActionListener((e) -> {
			// onDelete();

//			int[] rows = table.getSelectedRows();
//
//			String s = "";
//			for (int i = 0; i < rows.length; i++) {
//				s = s + rows[i];// 拼接成字符串，最终放在变量s中
//			}
//			int i = Integer.valueOf(s).intValue() + 1;
//			
//			System.out.println("++++++++++++++++++");
//			System.out.println(i);
			
			int[] rows = table.getSelectedRows();
			if(rows.length == 0)return;
					
			// 弹出对话框确认
			int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
			if(select != 0) return; // 0号按钮是'确定'按钮

			for(int i= rows.length-1; i>=0; i--)
			{
				int row = rows[i];
				// 按学号，从dataList中删除该条记录
//				String id = (String)tableModel.getValueAt(row, 0);
//				removeFromDataList(id);
			
			String condition = (String)tableModel.getValueAt(row, 0);
			testhttpdeleteperson(condition);
			}
		});

		// 按钮
		editButton = createToolButton("编辑", "ic_edit.png");
		toolBar.add(editButton);
		editButton.addActionListener((e) -> {
			onEdit();
		});

		// 查询
		toolBar.addSeparator(new Dimension(40, 10));
		toolBar.add(new JLabel("查询"));
		toolBar.add(searchField);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener((e) -> {
			// 按回车时触发事件
			// onSearch();
			String condition = searchField.getText().trim();
			testsearchhttp(condition);
		});
	}

	protected JButton createToolButton(String text, String icon) {
		// 图标
		String imagePath = "/icons/" + icon;
		URL imageURL = getClass().getResource(imagePath);

		// 创建按钮
		JButton button = new JButton(text);
		// button.setActionCommand(action);
		button.setToolTipText(text);
		button.setIcon(new ImageIcon(imageURL));
		button.setFocusPainted(false);
		return button;
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

	// 获取 表格控件中的一条记录的值
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

	// 设置 表格控件中的一条记录的值
	private void setTableRow(Person v, int row) {
		tableModel.setValueAt(v.personid, row, 0);
		tableModel.setValueAt(v.firstname, row, 1);
		tableModel.setValueAt(v.lastname, row, 2);
		tableModel.setValueAt(v.sex, row, 3);
		tableModel.setValueAt(v.birthdate, row, 4);
		tableModel.setValueAt(v.deathdate, row, 5);
		tableModel.setValueAt(v.homeaddress, row, 6);
	}

	// 向dataList添加一条记录
	private void addToDataList(Person s) {
		dataList.add(s);
	}

	// 修改一条记录
	private void updateToDataList(String firstname, Person s) {
		for (int i = 0; i < dataList.size(); i++) {
			Person item = dataList.get(i);
			if (item.firstname.equals(firstname)) {
				dataList.set(i, s);
			}
		}
	}

	// 从dataList中删除一条记录
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

			addToDataList(person); // 添加到 dataList
			addTableRow(person); // 添加到 tableModel
			saveData(); // 保存到文件
		}
	}

	// 点 '删除' 按钮
	private void onDelete() {
		// 获取选中的行的索引
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
		if (select != 0)
			return; // 0号按钮是'确定'按钮

		// 技巧：从后往前删除
		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];

			// 按id，从dataList中删除该条记录
			String personid = (String) tableModel.getValueAt(row, 0);
			removeFromDataList(personid);

			// 从tableModel中删除该条记录
			tableModel.removeRow(row);

		}

		saveData(); // 保存到文件
	}

	// 点 '编辑' 按钮
	private void onEdit() {
		// 获取选中的行的索引
		int[] rows = table.getSelectedRows();
		if (rows.length == 0)
			return;

		// 取得选中的行
		int row = rows[0]; // 只编辑选中的第一行
		Person s = getTableRow(row);

		// 弹出编辑对话框
		AddIndividual ai = new AddIndividual(this);
		// 设置初始值
		ai.setValue(s);
		if (ai.exec()) {
			Person os = ai.getValue();

			// 更新到 Model
			setTableRow(os, row);
			// 更新到dataList
			updateToDataList(os.personid, os);

			saveData(); // 保存到文件
		}
	}

	private void onSearch() {
		// 获取用户输入的过滤条件
		String filter = searchField.getText().trim();

		if (filter.length() == 0) // 过滤条件为空
		{
			// 恢复原始数据
			tableModel.setRowCount(0);// 清空
			for (Person s : dataList) {
				addTableRow(s);
			}
			this.addButton.setEnabled(true);
			return;
		}

		// 把符合条件的记录显示在表格里
		tableModel.setRowCount(0);// 清空
		for (Person s : dataList) {
			if (s.personid.indexOf(filter) >= 0) {
				addTableRow(s);
			}
		}

		// 把其他操作按钮禁用
		this.addButton.setEnabled(false);

	}

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

	private void oneditswap() {
		Swap s = new Swap(this);
		if (s.exec()) {

		}
	}

	private void onSearchPersonList() {
		SearchPersonList spl = new SearchPersonList(this);
		if (spl.exec()) {

		}
	}

	private void onSearchFamilyList() {
		SearchFamilyList sfl = new SearchFamilyList(this);
		if (sfl.exec()) {

		}
	}

	private void onListAddress() {
		ListAddress la = new ListAddress(this);
		if (la.exec()) {

		}
	}

	private void onListRepository() {
		ListRepository lr = new ListRepository(this);
		if (lr.exec()) {

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

	// 保存数据
	private void saveData() {
		// 构造一个 JSON 数组
		JSONArray array = new JSONArray();

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

		// 将JSON对象保存到文件
		File file = new File("personlist.json");
		try {
//			AfJSON.toFile(array, file, "UTF-8");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadData() {
		// 加载数据
		File file = new File("personlist.json");
		if (!file.exists())
			return;

		JSONArray array = null;
		try {
//			array = (JSONArray) AfJSON.fromFile(file, "UTF-8");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
			return;
		}
		// 显示到表格
		dataList.clear();
		tableModel.setRowCount(0); // 清空
		for (int i = 0; i < array.size(); i++) {
			JSONObject j1 = array.getJSONObject(i);
			Person s = new Person();

			s.personid = Integer.toString(j1.getInt("personId"));
			s.firstname = j1.getString("firstName");
			s.lastname = j1.getString("lastName");
			// s.nickname = j1.getString("nickname");
			String str = j1.getString("sex");
			s.sex = "male".equals(str) ? true : false;
			// s.sex = j1.getBoolean("sex");
			s.birthdate = Integer.toString(j1.getInt("birth"));
			s.deathdate = Integer.toString(j1.getInt("death"));
			s.homeaddress = j1.getString("address");

			addToDataList(s);
			addTableRow(s);
		}
	}

	// 显示全部Person数据
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

	// 查找person（按照id）
	public void testsearchhttp(String filter) {
		// 过滤条件为空
		if (filter.length() == 0) {
			// 显示接口所有数据
			TestCallHttpList tchpl = new TestCallHttpList();
			tchpl.httpURLGETCase(null);
		} else {
			tableModel.setRowCount(0);// 清空
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
					// 在表格中添加person
					addTableRow(person);
				}
			}
		

	}	

	// 删除接口

	public void testhttpdeleteperson(String filter) {
		// TODO Auto-generated method stub
		
//		// 获取选中的行的索引
//		if (filter.length() == 0)
//			return;
//		// 弹出对话框确认
//		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
//		if (select != 0)
//			return; // 0号按钮是'确定'按钮
//		
//		for (int i = rows.length - 1; i >= 0; i--) {
//			int row = rows[i];

		TestCallHttpPersonDelect tchpd = new TestCallHttpPersonDelect();
		tchpd.httpURLGETCase(filter);
	}
		
//	// 技巧：从后往前删除
//			for (int i = rows.length - 1; i >= 0; i--) {
//				int row = rows[i];
//
//				// 按id，从dataList中删除该条记录
//				String personid = (String) tableModel.getValueAt(row, 0);
//				removeFromDataList(personid);
//
//				// 从tableModel中删除该条记录
//				tableModel.removeRow(row);
//
//			}
//	
//	
	
	
	
//	public void testhttpaddperson(String filter) {
//		// TODO Auto-generated method stub
//		// 获取选中的行的索引
//		if (filter.length() == 0)
//			return;
//		// 弹出对话框确认
//		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
//		if (select != 0)
//			return; // 0号按钮是'确定'按钮
//
//		TestCallHttpPersonDelect tchpd = new TestCallHttpPersonDelect();
//		tchpd.httpURLGETCase(filter);
//	}

}