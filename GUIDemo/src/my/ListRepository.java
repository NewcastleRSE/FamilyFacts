package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import af.swing.layout.AfRowLayout;

public class ListRepository extends JDialog{

	AfPanel root = new AfPanel();
	AfPanel mPanel = new AfPanel();
	//AfPanel mbuttonPanel = new AfPanel();
	
	JButton addButton = new JButton("Add");
	JButton editButton = new JButton("Edit");
	JButton deleteButton = new JButton("Delete");
	
	JTable table = null;
	//表格模型 DefaultTableModel
    DefaultTableModel tableModel = new DefaultTableModel();
	
	
	JButton closeButton = new JButton("Close");
	
	private boolean retValue = false;
	
	public ListRepository(JFrame owner)
	{
		super(owner, "Repository", true);
		this.setSize(500,400);
		
		//AfPanel root = new AfPanel();
		this.setContentPane(root);
		//root.setLayout(new AfColumnLayout(10));
		root.setLayout(new BorderLayout());
		root.padding(10);
		
		JLabel label = new JLabel("Repository List:");
		root.add(label,BorderLayout.NORTH);

		//AfPanel mPanel = new AfPanel();
		root.add(mPanel, BorderLayout.CENTER); 
		
		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, BorderLayout.SOUTH); // 底部区域 30px
		buttonPanel.setLayout(new AfRowLayout(10));
		buttonPanel.add(new JLabel(), "1w"); // 占位		
		buttonPanel.add(closeButton, "auto"); // 按钮靠右显示
		
		
		
		
		AfPanel mbuttonPanel = new AfPanel();
		mPanel.add(mbuttonPanel,BorderLayout.NORTH);
		mbuttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
		mbuttonPanel.add(addButton, "auto");
		mbuttonPanel.add(editButton, "auto");
		mbuttonPanel.add(deleteButton, "auto");
		
		initTable();
		

		
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
		JScrollPane scrollPane = new JScrollPane(table);
		mPanel.add(scrollPane, BorderLayout.WEST);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		
		
		// 添加到主界面		
		table.setFillsViewportHeight(true);		
		table.setRowSelectionAllowed(true); // 整行选择
		table.setRowHeight(30);	
		
		// 列设置：添加5列
		tableModel.addColumn ("Name");
		tableModel.addColumn ("City");
		tableModel.addColumn ("Country");
		
		
		// 列设置：自定义绘制
		//table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度		
	}
	
	
	
	


	
	
	
}
