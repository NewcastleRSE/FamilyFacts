package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONObject;
import smx.swing.AfPanel;
import smx.swing.layout.AfColumnLayout;
import smx.swing.layout.AfRowLayout;

public class EditUnlinkFromParentMother extends JDialog {

	AfPanel root = new AfPanel();
	JPanel jpanel1 = new JPanel();
	JPanel jpanel2 = new JPanel();
	AfPanel jpanel3 = new AfPanel();
	MigLayout layout = new MigLayout();

	JTable informationtableMother = new JTable();
	DefaultTableModel informationmodelMother = new DefaultTableModel();
	String[] informationcolumnNamesMother = { "Facts", "Informations" };

	String motherfirstname = null;
	String motherlastname = null;
	String motherbirth = null;
	String motherdeath = null;
	String motheraddress = null;

	String motherid = null;

	static String personid = null;

	JButton UnlinkButton = new JButton("Unlink from Parents");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public EditUnlinkFromParentMother(JFrame owner) {
		super(owner, "Edit Unlink From Mother", true);
		this.setSize(500, 400);

		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));

		jpanel1.setPreferredSize(new Dimension(500, 100));
		jpanel2.setPreferredSize(new Dimension(500, 200));
		jpanel3.setPreferredSize(new Dimension(500, 50));

		root.add(jpanel1);
		root.add(jpanel2);

		JLabel label = new JLabel("Unlink from Parents?");
		JLabel label1 = new JLabel("Do you want to Unlink the hightlighted person as a child in this family?");
		jpanel1.add(label);
		jpanel1.add(label1);

		JScrollPane scrollPane = new JScrollPane(informationtableMother);
		scrollPane.setPreferredSize(new Dimension(480, 180));
		jpanel2.add(scrollPane, BorderLayout.CENTER);

		Object[][] informationdataMother = { { "First name", "" }, { "Last name", "" }, { "Birth", "" },
				{ "death", "" }, { "Address", "" } };
		informationmodelMother.setDataVector(informationdataMother, informationcolumnNamesMother);
		informationtableMother.setModel(informationmodelMother);
		informationtableMother.setFillsViewportHeight(true);
		informationtableMother.setRowSelectionAllowed(true);
		informationtableMother.setRowHeight(30);

		root.add(jpanel3, "30px");
		jpanel3.setLayout(new AfRowLayout(10));
		jpanel3.add(new JLabel(), "1w");
		jpanel3.add(UnlinkButton, "auto");

		UnlinkButton.addActionListener((e) -> {
			onunlinkmother();

		});

		jpanel3.add(cancelButton, "auto");

		onshowmother();

	}

	private void onunlinkmother() {

		TestCallHttpUnlinkMother.httpURLPOSTCase(personid, motherid);

	}

	private void onshowmother() {

		JSONObject motherObject = null;

		JSONObject informationmother = TestCallHttpAllRelevantPeople.httpURLGETCase(personid);

		System.out.println("personid:" + personid);
		System.out.println("--———————a中的mother(选中的person's mother information)(字符串)++++++------");
		String mother = informationmother.getString("mother");
		System.out.println("mother:" + mother);

		motherObject = JSONObject.fromObject(mother);

		String id = motherObject.getString("personId");
		System.out.println("mother personId：" + id);
		motherid = id;
		String firstname = motherObject.getString("firstName");
		System.out.println("mother firstname：" + firstname);
		String lastname = motherObject.getString("lastName");
		System.out.println("mother lastname：" + lastname);
		String birth = motherObject.getString("birth");
		System.out.println("mother birth：" + birth);
		String death = motherObject.getString("death");
		System.out.println("mother death：" + death);
		String address = motherObject.getString("address");
		System.out.println("mother address：" + address);

		motherfirstname = firstname;
		motherlastname = lastname;
		motherbirth = birth;
		motherdeath = death;
		motheraddress = address;

		Test t1 = new Test();
		t1.ttest(motherfirstname, motherlastname, motherbirth, motherdeath, motheraddress);
		Object[][] informationdataMother = { { "First name", motherfirstname }, { "Last name", motherlastname },
				{ "Birth", motherbirth }, { "death", motherdeath }, { "Address", motheraddress } };
		informationmodelMother.setDataVector(informationdataMother, informationcolumnNamesMother);
		informationtableMother.setModel(informationmodelMother);
		informationtableMother.setFillsViewportHeight(true);
		informationtableMother.setRowSelectionAllowed(true);
		informationtableMother.setRowHeight(30);

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

}
