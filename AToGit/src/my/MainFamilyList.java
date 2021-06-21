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
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

public class MainFamilyList extends JDialog{

	AfPanel root = new AfPanel();
	GridLayout layout = new GridLayout(1, 2);
	JPanel mPanel = new JPanel(layout);
	//AfPanel mbuttonPanel = new AfPanel();
	
	
	JButton findButton = new JButton("Find");
	
	
	JTable table = null;
	JTable tableEN = null;
	//表格模型 DefaultTableModel
    DefaultTableModel tableModel = new DefaultTableModel();
    DefaultTableModel tableENModel = new DefaultTableModel();
	
	
	JButton selectButton = new JButton("Select");
	JButton cancelButton = new JButton("Cancel");
	
	private boolean retValue = false;
	
	public MainFamilyList(JFrame owner)
	{
		super(owner, "Search Person List", true);
		this.setSize(1400,800);
		
		//AfPanel root = new AfPanel();
		this.setContentPane(root);
		//root.setLayout(new AfColumnLayout(10));
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);
		
		if(true)
		{
			AfPanel upPanel = new AfPanel();
			root.add(upPanel,"24px");
			JLabel label = new JLabel("Person List:");
			upPanel.add(label,BorderLayout.WEST);
		}
		
		if(true)
		{
			AfPanel mbuttonPanel = new AfPanel();
			root.add(mbuttonPanel,"24px");
			mbuttonPanel.setLayout(new AfRowLayout(10));
			mbuttonPanel.add(findButton, "auto");
			
		}
		
		if(true)
		{
			root.add(mPanel, "500px"); 
			
			initTable();
			//initENTable();
		}
		
		
		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, "30px"); // 底部区域 30px
		buttonPanel.setLayout(new AfRowLayout(10));
		buttonPanel.add(new JLabel(), "1w"); // 占位		
		buttonPanel.add(selectButton, "auto"); // 按钮靠右显示
		buttonPanel.add(cancelButton, "auto"); // 按钮靠右显示

		
	}
	
	public boolean exec()
	{
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width)/2;
		int y = frmRect.y + (frmRect.height - height)/2;
		this.setBounds(x,y, width, height);
		
		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);
		
		return retValue;
	}
	
	private void initTable()
	{
		// 创建 JTable，直接重写 isCellEditable()，设为不可编辑
		table = new JTable(tableModel){
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}			
		};
		tableEN = new JTable(tableENModel){
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}			
		};
		GridLayout layout1 = new GridLayout(2, 1);
		JScrollPane scrollPane = new JScrollPane(table);
		JScrollPane scrollPaneEN = new JScrollPane(tableEN);
		JList eslist = new JList();
		String []dd= {"XXX and XXX","<< XXX and other spouse >>"};	//还不确定需要修改
		eslist=new JList(dd);		
		eslist.setVisibleRowCount(2);
		JScrollPane scrollPaneES= new JScrollPane(eslist);
		JPanel mEPanel = new JPanel(layout1);
		mPanel.add(scrollPane);
		mPanel.add(mEPanel);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		mEPanel.add(scrollPaneEN);
		mEPanel.add(scrollPaneES);
		
		// 添加到主界面		
		table.setFillsViewportHeight(true);		
		table.setRowSelectionAllowed(true); // 整行选择
		table.setRowHeight(30);	
		
		// 列设置：添加2列
		tableModel.addColumn ("Name");
		tableModel.addColumn ("Birth Date");
		
		
		// 列设置：自定义绘制
		//table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度		
		
		// 添加到主界面		
		tableEN.setFillsViewportHeight(true);		
		tableEN.setRowSelectionAllowed(true); // 整行选择
		tableEN.setRowHeight(30);	
		
		// 列设置：添加2列
		tableENModel.addColumn ("Facts");
		tableENModel.addColumn ("Content");
		
		
		// 列设置：自定义绘制
		//table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
		tableEN.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		tableEN.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度
		

		

	}
	
	private void initENTable()
	{
		// 创建 JTable，直接重写 isCellEditable()，设为不可编辑
		tableEN = new JTable(tableENModel){
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}			
		};
		AfPanel mEPanel = new AfPanel();
		JScrollPane scrollPaneEN = new JScrollPane(tableEN);
		JScrollPane scrollPaneES= new JScrollPane(tableEN);
		mPanel.add(mEPanel,BorderLayout.CENTER);
		mEPanel.add(scrollPaneEN, BorderLayout.NORTH);
		mEPanel.add(scrollPaneES, BorderLayout.CENTER);
		scrollPaneEN.setPreferredSize(new Dimension(300, 200));
		scrollPaneES.setPreferredSize(new Dimension(300, 200));
		
		
		// 添加到主界面		
		tableEN.setFillsViewportHeight(true);		
		tableEN.setRowSelectionAllowed(true); // 整行选择
		tableEN.setRowHeight(30);	
		
		// 列设置：添加2列
		tableENModel.addColumn ("Facts");
		tableENModel.addColumn ("Content");
		
		
		// 列设置：自定义绘制
		//table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
		tableEN.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		tableEN.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
