package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

public class AddChild extends JDialog{

	JList addchildlist = new JList();
	
	
	JButton addnewpersonButton = new JButton("Add New Person");
	JButton selectexistingpersonButton = new JButton("Select Existing Person");
	JButton cancelButton = new JButton("Cancel");
	
	private boolean retValue = false;
	
	public AddChild(JFrame owner)
	{
		super(owner, "Add Child", true);
		this.setSize(500,300);
		
		AfPanel root = new AfPanel();
		this.setContentPane(root);
		//root.setLayout(new AfColumnLayout(10));
		root.setLayout(new BorderLayout());
		root.padding(10);
		
		JLabel label = new JLabel("Add Child to:");
		root.add(label,BorderLayout.NORTH);

		String []dd= {"XXX and XXX","<< XXX and other spouse >>"};	
		addchildlist=new JList(dd);		
		addchildlist.setVisibleRowCount(2);
		
		JScrollPane scrollPane = new JScrollPane(addchildlist);
		root.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(300, 200));
	
		
		
		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, BorderLayout.SOUTH); // 底部区域 30px
		buttonPanel.setLayout(new AfRowLayout(10));
		//buttonPanel.add(new JLabel(), "1w"); // 占位
		buttonPanel.add(addnewpersonButton, "auto"); // 按钮靠右显示
		buttonPanel.add(selectexistingpersonButton, "auto"); // 按钮靠右显示
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
	
	
}
