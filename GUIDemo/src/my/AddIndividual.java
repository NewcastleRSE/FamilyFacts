package my;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

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
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;
import af.swing.layout.AfYLayout;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*
 * Add Individual window
 * 
 */

public class AddIndividual extends JDialog {

	public JTextField personidField = new JTextField(20);
	public JTextField firstnameField = new JTextField(20);
	public JTextField lastnameField = new JTextField(20);
	public JComboBox<String> sexField = new JComboBox<>();
	public JTextField birthdateField = new JTextField("Date", 20);
	public JTextField deathdateField = new JTextField("Date", 20);
	public JTextField homeaddressField = new JTextField("Address", 20);

	JButton okButton = new JButton("OK");

	DefaultTableModel tableModel = new DefaultTableModel();

	String ssss = null;

	private boolean retValue = false;

	public AddIndividual(JFrame owner) {
		super(owner, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

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

			sexField.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});

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

	public boolean checkValid() {
		Person v = getValue();
		if (v.firstname.isEmpty()) {
			JOptionPane.showMessageDialog(this, "firstname must not be empty!");
			return false;
		}
		if (v.lastname.isEmpty()) {
			JOptionPane.showMessageDialog(this, "lastname must not be empty!");
			return false;
		}
		return true;
	}

	public Person getValue() {
		Person v = new Person();
		v.firstname = firstnameField.getText().trim();
		v.lastname = lastnameField.getText().trim();
		if (ssss.equals("Male")) {
			v.sex = false;
		} else {
			v.sex = true;
		}

		v.birthdate = birthdateField.getText().trim();
		v.deathdate = deathdateField.getText().trim();
		v.homeaddress = homeaddressField.getText().trim();

		return v;
	}

	public void testhttpaddperson(String firstname, String lastname, String sex, String birth, String death,
			String address) {
		// TODO Auto-generated method stub

		// TestCallHttpPersonAdd tchpa = new TestCallHttpPersonAdd();
		TestCallHttpPersonAdd.httpURLPOSTCase(firstname, lastname, sex, birth, death, address);
	}

	public void testhttpaddspouse(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {
		// TODO Auto-generated method stub

		TestCallHttpAddSpouse.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}

	public void testhttpaddfather(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {
		// TODO Auto-generated method stub

		TestCallHttpAddFather.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}

	public void testhttpaddmother(String personid, String firstname, String lastname, String sex, String birth,
			String death, String address) {
		// TODO Auto-generated method stub

		TestCallHttpAddMother.httpURLPOSTCase(personid, firstname, lastname, sex, birth, death, address);
	}

	public void testhttp() {

		TestCallHttpList tchpl = new TestCallHttpList();
		// Get the server response result
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
				// add person to the table
				addTableRow(person);

			}
		}
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

	public AddIndividual(AddSpouse addSpouse) {
		super(addSpouse, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		AfPanel main = new AfPanel();
		root.add(main, "1w");
		main.setLayout(new AfColumnLayout(10));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);
//
//		
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

			sexField.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});
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

	public AddIndividual(AddFather addFather) {
		super(addFather, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

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

			sexField.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});
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

	public AddIndividual(AddMother addMother) {
		super(addMother, "Add Person", true);
		this.setSize(500, 400);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

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

			sexField.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out
								.println("select: " + sexField.getSelectedIndex() + " = " + sexField.getSelectedItem());
						ssss = (String) sexField.getSelectedItem();
						System.out.println(ssss);

					}
				}

			});
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

}
