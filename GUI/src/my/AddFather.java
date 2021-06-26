package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import af.swing.AfPanel;
import af.swing.layout.AfAnyWhere;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfMargin;
import af.swing.layout.AfRowLayout;
import af.swing.layout.AfXLayout;

public class AddFather extends JDialog {

	List<Person> dataList = new ArrayList<>();

	DefaultTableModel tableModel = new DefaultTableModel();

	JButton addnewpersonButton = new JButton("Add New Person");
	JButton selectexistingpersonButton = new JButton("Select Existing Person");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public AddFather(JFrame owner) {
		super(owner, "Add Father", true);
		this.setSize(500, 300);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		AfPanel mainPanel = new AfPanel();
		root.add(mainPanel, "1w");
		mainPanel.setLayout(new AfColumnLayout(10));
		mainPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mainPanel.padding(10);

		mainPanel.add(new JLabel("Add a father to XXXXX"), "200px");

		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, "30px");
		buttonPanel.setLayout(new AfRowLayout(10));

		buttonPanel.add(addnewpersonButton, "auto");
		addnewpersonButton.addActionListener((e) -> {
			onAddIndividual();//

		});

		buttonPanel.add(selectexistingpersonButton, "auto");
		buttonPanel.add(selectexistingpersonButton, "auto");
		selectexistingpersonButton.addActionListener((e) -> {
			onSelectExist();//

		});
		buttonPanel.add(cancelButton, "auto");

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

	private void addToDataList(Person s) {
		dataList.add(s);
	}

	private void saveData() {

		JSONArray array = new JSONArray();
		for (int i = 0; i < dataList.size(); i++) {
			Person s = dataList.get(i);
			JSONObject j1 = new JSONObject();
			j1.put("personid", s.personid);
			j1.put("firstname", s.firstname);
			j1.put("lastname", s.lastname);
			j1.put("sex", s.sex);
			j1.put("birthdate", s.birthdate);
			j1.put("deathdate", s.deathdate);
			j1.put("homeaddress", s.homeaddress);

			array.put(j1);
		}

		File file = new File("person.json");
		try {
//			AfJSON.toFile(array, file, "UTF-8");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

	private void onAddIndividual() {
		AddIndividual ai = new AddIndividual(this);
		if (ai.exec()) {
			Person person = ai.getValue();

			addToDataList(person);
			addTableRow(person);
			saveData();
		}
	}

	private void onSelectExist() {
		SelectExistPerson sep = new SelectExistPerson(this);
		if (sep.exec()) {

		}

	}
}
