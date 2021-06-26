package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

//Edit button-Need to be modified
public class EditPerson extends JDialog {

	AfPanel root = new AfPanel();
	JPanel mPanel = new JPanel();

	JButton editButton = new JButton("Edit");

	public JTextField personField = new JTextField(20);// The personal name "Mengxi Shen Mercy" is displayed in the text
														// box”
	public JTextField fatherField = new JTextField(20);// show father name
	public JTextField motherField = new JTextField(20);// show mother name
	public JTextField sexField = new JTextField(20);// show person sex
	public JTextField birthdateField = new JTextField(20);// show mother name
	public JTextField deathdateField = new JTextField(20);
	public JTextField homeaddressField = new JTextField("Address", 20);

	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public EditPerson(JFrame owner) {
		super(owner, "Edit Person", true);
		this.setSize(500, 400);

		// AfPanel root = new AfPanel();
		this.setContentPane(root);
		// root.setLayout(new AfColumnLayout(10));
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);

		mPanel.setLayout(new AfColumnLayout(10));
		mPanel.setBorder(BorderFactory.createEtchedBorder());

		if (true) {
			AfPanel upPanel = new AfPanel();
			root.add(upPanel, "24px");
			JLabel label = new JLabel("XXXXXXX(鼠标选中的人的名字):");
			upPanel.add(label, BorderLayout.WEST);
		}

		if (true) {
			root.add(mPanel, "250px");

		}

		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, "30px");
		buttonPanel.setLayout(new AfRowLayout(10));
		buttonPanel.add(new JLabel(), "1w");
		buttonPanel.add(editButton, "auto");

		buttonPanel.add(cancelButton, "auto");

		if (true) {
			AfPanel row = new AfPanel();
			mPanel.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Person:"), "70px");
			row.add(personField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			mPanel.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Father:"), "70px");
			row.add(fatherField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			mPanel.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Mother:"), "70px");
			row.add(motherField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			mPanel.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Sex:"), "70px");
			row.add(sexField, "1w");
		}

		/////////

		if (true) {
			AfPanel row = new AfPanel();
			mPanel.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Birth"), "70px");
			row.add(birthdateField, "1w");
			// row.add(birthplaceField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			mPanel.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Death"), "70px");
			row.add(deathdateField, "1w");
			// row.add(deathplaceField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			mPanel.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Home Address"), "70px");
			row.add(homeaddressField, "1w");
			// row.add(deathplaceField, "1w");

		}

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
