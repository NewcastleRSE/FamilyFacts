package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import af.swing.AfPanel;
import af.swing.layout.AfAnyWhere;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfMargin;
import af.swing.layout.AfRowLayout;
import af.swing.layout.AfXLayout;

public class AddFather extends JDialog{

	JButton addnewpersonButton = new JButton("Add New Person");
	JButton selectexistingpersonButton = new JButton("Select Existing Person");
	JButton cancelButton = new JButton("Cancel");
	
	private boolean retValue = false;
	
	
	public AddFather(JFrame owner)
	{
		super(owner, "Add Father", true);
		this.setSize(500,300);
		
		// 设置一个容器
		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);
		
		// 中间面板
		AfPanel mainPanel = new AfPanel();
		root.add(mainPanel, "1w"); // 占居中间区域
		mainPanel.setLayout(new AfColumnLayout(10));		
		mainPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mainPanel.padding(10);
		
		mainPanel.add(new JLabel("Add a father to XXXXX"), "200px");
		
		
		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, "30px"); // 底部区域 30px
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
