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

public class AddIndividual extends JDialog {

	public JTextField personidField = new JTextField(20);
	public JTextField firstnameField = new JTextField(20);
	public JTextField lastnameField = new JTextField(20);
	// public JTextField nicknameField = new JTextField(20);
	public JComboBox<String> sexField = new JComboBox<>();
	public JTextField birthdateField = new JTextField("Date", 20);
	public JTextField deathdateField = new JTextField("Date", 20);
	public JTextField homeaddressField = new JTextField("Address", 20);

	JButton okButton = new JButton("OK");

	private boolean retValue = false;

	public AddIndividual(JFrame owner) {
		super(owner, "Add Person", true);
		this.setSize(500, 400);

		// container
		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		// Middle panel
		AfPanel main = new AfPanel();
		root.add(main, "1w");
		main.setLayout(new AfColumnLayout(10));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}
		// 底下
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(okButton, "auto");

		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true;
				setVisible(false);
			}

		});

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

	public void setValue(Person v) {
		personidField.setText(v.personid);
		firstnameField.setText(v.firstname);
		lastnameField.setText(v.lastname);
		sexField.setSelectedIndex(v.sex ? 1 : 0);

	}

	public boolean checkValid() {
		Person v = getValue();
		if (v.firstname.isEmpty()) {
			JOptionPane.showMessageDialog(this, "firstname不得为空!");
			return false;
		}
		if (v.lastname.isEmpty()) {
			JOptionPane.showMessageDialog(this, "lastname不得为空!");
			return false;
		}
		return true;
	}

	public Person getValue() {
		Person v = new Person();
		v.firstname = firstnameField.getText().trim();
		v.lastname = lastnameField.getText().trim();
		v.sex = sexField.getSelectedIndex() == 1;
		v.birthdate = birthdateField.getText().trim();
		v.deathdate = deathdateField.getText().trim();
		v.homeaddress = homeaddressField.getText().trim();

		return v;
	}

	public void testhttpaddperson(String firstname, String lastname, String sex, String birth, String death,
			String address) {
		// TODO Auto-generated method stub

		TestCallHttpPersonAdd.httpURLPOSTCase(firstname, lastname, sex, birth, death, address);
	}

	public AddIndividual(AddSpouse addSpouse) {
		super(addSpouse, "Add Person", true);
		this.setSize(500, 400);

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
//
//			if(true)
//			{
//				AfPanel row = new AfPanel();
//				main.add(row,"24px");
//				row.setLayout(new AfRowLayout(10));
//				row.add(new JLabel("Person ID"), "70px");
//				row.add(personidField, "1w");
//			}
//			

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}
		// 底下
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px"); // 底部区域 30px
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w"); // 占位
		bottom.add(okButton, "auto"); // 按钮靠右显示

		// 此处 使用 Lambda 表达式 的写法，参考3.5 节
		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true; // 设置对话框 的返回值
				setVisible(false); // MyDialog.this.setVisible(false)
			}

		});

	}

	public AddIndividual(AddFather addFather) {
		super(addFather, "Add Person", true);
		this.setSize(500, 400);

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
		//
//				if(true)
//				{
//					AfPanel row = new AfPanel();
//					main.add(row,"24px");
//					row.setLayout(new AfRowLayout(10));
//					row.add(new JLabel("Person ID"), "70px");
//					row.add(personidField, "1w");
//				}
//				

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}
		// 底下
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px"); // 底部区域 30px
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w"); // 占位
		bottom.add(okButton, "auto"); // 按钮靠右显示

		// 此处 使用 Lambda 表达式 的写法，参考3.5 节
		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true; // 设置对话框 的返回值
				setVisible(false); // MyDialog.this.setVisible(false)
			}

		});

	}

	public AddIndividual(AddMother addMother) {
		super(addMother, "Add Person", true);
		this.setSize(500, 400);

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
		//
//				if(true)
//				{
//					AfPanel row = new AfPanel();
//					main.add(row,"24px");
//					row.setLayout(new AfRowLayout(10));
//					row.add(new JLabel("Person ID"), "70px");
//					row.add(personidField, "1w");
//				}
//				

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}
		// 底下
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px"); // 底部区域 30px
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w"); // 占位
		bottom.add(okButton, "auto"); // 按钮靠右显示

		// 此处 使用 Lambda 表达式 的写法，参考3.5 节
		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true; // 设置对话框 的返回值
				setVisible(false); // MyDialog.this.setVisible(false)
			}

		});

	}

	public AddIndividual(AddChild addChild) {
		super(addChild, "Add Person", true);
		this.setSize(500, 400);

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
		//
//				if(true)
//				{
//					AfPanel row = new AfPanel();
//					main.add(row,"24px");
//					row.setLayout(new AfRowLayout(10));
//					row.add(new JLabel("Person ID"), "70px");
//					row.add(personidField, "1w");
//				}
//				

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("First name"), "70px");
			row.add(firstnameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Last name"), "70px");
			row.add(lastnameField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex"), "70px");
			row.add(sexField, "1w");

			sexField.addItem("Female");
			sexField.addItem("Male");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");

		}
		// 底下
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px"); // 底部区域 30px
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w"); // 占位
		bottom.add(okButton, "auto"); // 按钮靠右显示

		// 此处 使用 Lambda 表达式 的写法，参考3.5 节
		okButton.addActionListener((e) -> {

			if (checkValid()) {
				retValue = true; // 设置对话框 的返回值
				setVisible(false); // MyDialog.this.setVisible(false)
			}

		});

	}

}
