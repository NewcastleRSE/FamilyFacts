package my;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;
import af.swing.layout.AfYLayout;


public class AddNewAddress extends JDialog{
	
	
	
	public JTextField nameField = new JTextField(20);
	public JTextField streetaddressField = new JTextField(20);
	public JTextField cityField = new JTextField(20);
	public JTextField postalcodeField = new JTextField(20);
	public JTextField countryField = new JTextField(20);
	public JTextField phoneField = new JTextField(20);

	
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");

	
	private boolean retValue = false;
	
	public AddNewAddress(ListAddress listAddress)
	{
		super(listAddress, "Edit Address", true);
		this.setSize(800,500);

		// 设置一个容器
		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);
		
		// 中间面板
		AfPanel main = new AfPanel();
		root.add(main, "1w"); // 占居中间区域
		main.setLayout(new AfColumnLayout(10));		
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);
		
		
		if(true)
		{
			AfPanel row = new AfPanel();
			main.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Name"), "100px");
			row.add(nameField, "1w");
			
			
			
		}
		if(true)
		{
			AfPanel row = new AfPanel();
			main.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Street Address"), "100px");
			row.add(streetaddressField, "1w");
		}
		if(true)
		{
			AfPanel row = new AfPanel();
			main.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("City"), "100px");
			row.add(cityField, "1w");
		}
		
		
		/////////
		
		if(true)
		{
			AfPanel row = new AfPanel();
			main.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Postal Code"), "100px");
			row.add(postalcodeField, "1w");
			
		}
		

		if(true)
		{
			AfPanel row = new AfPanel();
			main.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Country"), "100px");
			row.add(countryField, "1w");
		

			
		}
		
		if(true)
		{
			AfPanel row = new AfPanel();
			main.add(row,"24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Phone"), "100px");
			row.add(phoneField, "1w");
			
			
		}
		
		
		
		// 底下
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px"); // 底部区域 30px		
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w"); // 占位
		bottom.add(okButton, "auto"); // 按钮靠右显示
		bottom.add(cancelButton, "auto");

		
		
		// 此处 使用 Lambda 表达式 的写法，参考3.5 节
		okButton.addActionListener( (e)->{

//			if (checkValid ())
//			{
//				retValue = true; // 设置对话框 的返回值
//				setVisible(false); // MyDialog.this.setVisible(false)
//			}
		
		});	
	
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

//	
//	// 设置初始值
//	public void setValue(Person v)
//	{
//		firstnameField.setText(v.firstname);
//		lastnameField.setText(v.lastname);
//		sexField.setSelectedIndex(v.sex ? 1: 0); // 条件表达式 
//		birthdateField.setText(v.birthdate);
//	}
//	
//	
//	// 检查输入有效性
//	public boolean checkValid()
//		{
//			Person v = getValue();
//			if(v.firstname.isEmpty())
//			{
//				JOptionPane.showMessageDialog(this, "firstname不得为空!");
//				return false;
//			}
//			if(v.lastname.isEmpty())
//			{
//				JOptionPane.showMessageDialog(this, "lastname不得为空!");
//				return false;
//			}
//			return true;
//		}
//		// 获取用户的输入 
//		public Person getValue()
//		{
//			Person v = new Person();
//			v.firstname = firstnameField.getText().trim();
//			v.lastname = lastnameField.getText().trim();
//			v.sex = sexField.getSelectedIndex() == 1;
//			v.birthdate = birthdateField.getText().trim();
//			v.birthplace = birthplaceField.getText().trim();
//			v.deathdate = deathdateField.getText().trim();
//			v.deathplace = deathplaceField.getText().trim();
//			v.burialdate = burialdateField.getText().trim();
//			v.burialplace = burialplaceField.getText().trim();
//			
//			return v;
//		}
//	
//	
//	
//	
//	
	
	
	

	
	
}
