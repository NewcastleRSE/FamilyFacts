package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONObject;
import smx.swing.AfPanel;
import smx.swing.layout.AfColumnLayout;
import smx.swing.layout.AfRowLayout;

public class EditUnlinkFromSpouse extends JDialog {

	AfPanel root = new AfPanel();
	JPanel jpanel1 = new JPanel();
	JPanel jpanel2 = new JPanel();
	AfPanel jpanel3 = new AfPanel();
	MigLayout layout = new MigLayout();

	JTable informationtableSpouse = new JTable();
	DefaultTableModel informationmodelSpouse = new DefaultTableModel();
	String[] informationcolumnNamesSpouse = { "Facts", "Informations" };

	String spousefirstname = null;
	String spouselastname = null;
	String spousebirth = null;
	String spousedeath = null;
	String spouseaddress = null;

	String spouseid = null;

	static String personid = null;

	JButton UnlinkButton = new JButton("Unlink from Spouse");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public EditUnlinkFromSpouse(JFrame owner) {
		super(owner, "Edit Unlink From Spouse", true);
		this.setSize(500, 400);

		this.setContentPane(root);

		root.setLayout(new AfColumnLayout(10));

		jpanel1.setPreferredSize(new Dimension(500, 100));
		jpanel2.setPreferredSize(new Dimension(500, 200));
		jpanel3.setPreferredSize(new Dimension(500, 50));

		root.add(jpanel1);
		root.add(jpanel2);

		JLabel label = new JLabel("Unlink from Spouse?");
		JLabel label1 = new JLabel("Do you want to Unlink the hightlighted person as a parent in this family?");
		jpanel1.add(label);
		jpanel1.add(label1);

		JScrollPane scrollPane = new JScrollPane(informationtableSpouse);
		scrollPane.setPreferredSize(new Dimension(480, 180));
		jpanel2.add(scrollPane, BorderLayout.CENTER);

		Object[][] informationdataSpouse = { { "First name", "" }, { "Last name", "" }, { "Birth", "" },
				{ "death", "" }, { "Address", "" } };
		informationmodelSpouse.setDataVector(informationdataSpouse, informationcolumnNamesSpouse);
		informationtableSpouse.setModel(informationmodelSpouse);
		informationtableSpouse.setFillsViewportHeight(true);
		informationtableSpouse.setRowSelectionAllowed(true);
		informationtableSpouse.setRowHeight(30);

		root.add(jpanel3, "30px");
		jpanel3.setLayout(new AfRowLayout(10));
		jpanel3.add(new JLabel(), "1w");
		jpanel3.add(UnlinkButton, "auto");

		UnlinkButton.addActionListener((e) -> {
			onunlinkspouse();

		});

		jpanel3.add(cancelButton, "auto");

		onshowspouse();

	}

	private void onunlinkspouse() {

		TestCallHttpUnlinkSpouse.httpURLPOSTCase(personid, spouseid);

	}

	private void onshowspouse() {

		JSONObject spouseObject = null;

		JSONObject informationspouse = TestCallHttpAllRelevantPeople.httpURLGETCase(personid);

		System.out.println("personid:" + personid);
		System.out.println("--———————a中的spouse(选中的person's spouse information)(字符串)++++++------");
		String spouse = informationspouse.getString("spouse");
		System.out.println("spouse:" + spouse);

		spouseObject = JSONObject.fromObject(spouse);

		String id = spouseObject.getString("personId");
		System.out.println("spouse personId：" + id);
		spouseid = id;
		String firstname = spouseObject.getString("firstName");
		System.out.println("spouse firstname：" + firstname);
		String lastname = spouseObject.getString("lastName");
		System.out.println("spouse lastname：" + lastname);
		String birth = spouseObject.getString("birth");
		System.out.println("spouse birth：" + birth);
		String death = spouseObject.getString("death");
		System.out.println("spouse death：" + death);
		String address = spouseObject.getString("address");
		System.out.println("spouse address：" + address);

		spousefirstname = firstname;
		spouselastname = lastname;
		spousebirth = birth;
		spousedeath = death;
		spouseaddress = address;

		Test t1 = new Test();
		t1.ttest(spousefirstname, spouselastname, spousebirth, spousedeath, spouseaddress);
		Object[][] informationdataSpouse = { { "First name", spousefirstname }, { "Last name", spouselastname },
				{ "Birth", spousebirth }, { "death", spousedeath }, { "Address", spouseaddress } };
		informationmodelSpouse.setDataVector(informationdataSpouse, informationcolumnNamesSpouse);
		informationtableSpouse.setModel(informationmodelSpouse);
		informationtableSpouse.setFillsViewportHeight(true);
		informationtableSpouse.setRowSelectionAllowed(true);
		informationtableSpouse.setRowHeight(30);

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
