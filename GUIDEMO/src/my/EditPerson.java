package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;
//修改！！！！！！！！！！！！！！！！！
public class EditPerson extends JDialog{
	
//	AfPanel root = new AfPanel();
//	GridLayout layout = new GridLayout(1, 2);
//	JPanel mPanel = new JPanel(layout);
//	JButton findButton = new JButton("Edit");
//	
//	JTable table = null;
//	//表格模型 DefaultTableModel
//    DefaultTableModel tableModel = new DefaultTableModel();
//    
//    public JTextField firstnameField = new JTextField(20);
//	public JTextField lastnameField = new JTextField(20);
//	public JComboBox<String>  sexField = new JComboBox<>();
//	public JTextField birthdateField = new JTextField("Date",20);
//	public JTextField birthplaceField = new JTextField("Place",20);
   
	AfPanel root = new AfPanel();
	JPanel mPanel = new JPanel();
	
	JButton editButton = new JButton("Edit");
	
    public JTextField personField = new JTextField(20);//文本框中显示个人名字“Mengxi Shen Mercy”
	public JTextField fatherField = new JTextField(20);//show father name
	public JTextField motherField= new JTextField(20);//show mother name
	public JTextField sexField= new JTextField(20);//show person sex
	public JTextField birthdateField= new JTextField(20);//show mother name
	public JTextField deathdateField = new JTextField(20);
	public JTextField homeaddressField = new JTextField("Address",20);
    
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;
	
	public EditPerson(JFrame owner)
	{
		super(owner, "Edit Person", true);
		this.setSize(500,400);
		
		//AfPanel root = new AfPanel();
		this.setContentPane(root);
		//root.setLayout(new AfColumnLayout(10));
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);
		
		mPanel.setLayout(new AfColumnLayout(10));	
		mPanel.setBorder(BorderFactory.createEtchedBorder());
		
		if(true)
		{
			AfPanel upPanel = new AfPanel();
			root.add(upPanel,"24px");
			JLabel label = new JLabel("XXXXXXX(鼠标选中的人的名字):");//修改
			upPanel.add(label,BorderLayout.WEST);
		}
		
//		if(true)
//		{
//			AfPanel mbuttonPanel = new AfPanel();
//			root.add(mbuttonPanel,"24px");
//			mbuttonPanel.setLayout(new AfRowLayout(10));
//			mbuttonPanel.add(findButton, "auto");
//			
//		}
		
		if(true)
		{
			root.add(mPanel, "250px"); 
			
			//initTable();
			
		}
		
		
		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, "30px"); // 底部区域 30px
		buttonPanel.setLayout(new AfRowLayout(10));
		buttonPanel.add(new JLabel(), "1w"); // 占位		
		buttonPanel.add(editButton, "auto");
//		editButton.addActionListener( (e)->{
//			onAddIndividual();
//		});
		buttonPanel.add(cancelButton, "auto"); // 按钮靠右显示

		
		if(true)
		{
			AfPanel row = new AfPanel();
			mPanel.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Person:"), "70px");
			row.add(personField, "1w");
			
			
			
		}
		if(true)
		{
			AfPanel row = new AfPanel();
			mPanel.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Father:"), "70px");
			row.add(fatherField, "1w");
		}
		
		if(true)
		{
			AfPanel row = new AfPanel();
			mPanel.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Mother:"), "70px");
			row.add(motherField, "1w");
		}
		
		
		if(true)
		{
			AfPanel row = new AfPanel();
			mPanel.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex:"), "70px");
			row.add(sexField, "1w");
		}
		
		
		/////////
		
		if(true)
		{
			AfPanel row = new AfPanel();
			mPanel.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
			//row.add(birthplaceField, "1w");
		}
		

		if(true)
		{
			AfPanel row = new AfPanel();
			mPanel.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");
			//row.add(deathplaceField, "1w");

			
		}
		if(true)
		{
			AfPanel row = new AfPanel();
			mPanel.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");
			//row.add(deathplaceField, "1w");

			
		}
		
		
		
		
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
	
	
//	private void initTable()
//	{
//		// 创建 JTable，直接重写 isCellEditable()，设为不可编辑
//		table = new JTable(tableModel){
//			@Override
//			public boolean isCellEditable(int row, int column)
//			{
//				return false;
//			}			
//		};
//		
//		GridLayout layout1 = new GridLayout(2, 1);
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBorder(BorderFactory.createEtchedBorder());
//		
//		JPanel editPanel = new JPanel(layout1);//右上编辑信息面板
//		editPanel.setLayout(new AfColumnLayout(10));	
//		editPanel.setBorder(BorderFactory.createEtchedBorder());
//		
		
//		if(true)
//		{
//			AfPanel row = new AfPanel();
//			editPanel.add(row,"24px");
//			row.setLayout(new AfRowLayout(10));
//			row.add(new JLabel("First name"), "70px");
//			row.add(firstnameField, "1w");
//			
//			
//			
//		}
//		if(true)
//		{
//			AfPanel row = new AfPanel();
//			editPanel.add(row,"24px");
//			row.setLayout(new AfRowLayout(10));
//			row.add(new JLabel("Last name"), "70px");
//			row.add(lastnameField, "1w");
//		}
//		if(true)
//		{
//			AfPanel row = new AfPanel();
//			editPanel.add(row,"24px");
//			row.setLayout(new AfRowLayout(10));
//			row.add(new JLabel("Sex"), "70px");
//			row.add(sexField, "1w");
//			
//			sexField.addItem("Female");
//			sexField.addItem("Male");
//		}
//		
//		
//		/////////
//		
//		if(true)
//		{
//			AfPanel row = new AfPanel();
//			editPanel.add(row,"24px");
//			row.setLayout(new AfRowLayout(10));
//			row.add(new JLabel("Birth"), "50px");
//			row.add(birthdateField, "1w");
//			row.add(birthplaceField, "1w");
//		}
//
//		if(true)
//		{
//			AfPanel row = new AfPanel();
//			editPanel.add(row,"24px");
//			row.setLayout(new AfRowLayout(10));
//			row.add(new JLabel("Birth"), "50px");
//			row.add(birthdateField, "1w");
//			row.add(birthplaceField, "1w");
//		}


//		mPanel.add(scrollPane);
//		mPanel.add(editPanel);
//		scrollPane.setPreferredSize(new Dimension(300, 200));
//
//		
//		// 添加到主界面		
//		table.setFillsViewportHeight(true);		
//		table.setRowSelectionAllowed(true); // 整行选择
//		table.setRowHeight(30);	
//		
//		// 列设置：添加2列
//		tableModel.addColumn ("First name");
//		tableModel.addColumn ("Last name");
//		tableModel.addColumn ("Birth Date");
//		tableModel.addColumn ("Sex");
//		tableModel.addColumn ("Father");
//		tableModel.addColumn ("Mother");
//		
//		
//		// 列设置：自定义绘制
//		//table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
//		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
//		table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度		
//		
//
//	}
		


}
