package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

public class AddChild extends JDialog {

	// dataList: Maintain all records , tableModel: The record to be displayed
	List<Person> dataList = new ArrayList<>();

	DefaultTableModel tableModel = new DefaultTableModel();

	JList addchildlist = new JList();

	JButton addnewpersonButton = new JButton("Add New Person");
	JButton selectexistingpersonButton = new JButton("Select Existing Person");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public AddChild(JFrame owner) {
		super(owner, "Add Child", true);
		this.setSize(500, 300);

		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		root.padding(10);

		JLabel label = new JLabel("Add Child to:");
		root.add(label, BorderLayout.NORTH);

		String[] dd = { "XXX and XXX", "<< XXX and other spouse >>" };
		addchildlist = new JList(dd);
		addchildlist.setVisibleRowCount(2);

		JScrollPane scrollPane = new JScrollPane(addchildlist);
		root.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(300, 200));

		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new AfRowLayout(10));
		buttonPanel.add(addnewpersonButton, "auto"); // Button to the right
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
		// Relative to owner centered display
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		// Show window (blocked, direct dialog window is closed)
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

		tableModel.addRow(rowData); // Add a row
	}

	// Add a record to dataList
	private void addToDataList(Person s) {
		dataList.add(s);
	}

	// save data
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
