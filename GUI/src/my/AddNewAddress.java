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

//Menu-list: address list-add button to open the window	
public class AddNewAddress extends JDialog {

	public JTextField nameField = new JTextField(20);
	public JTextField streetaddressField = new JTextField(20);
	public JTextField cityField = new JTextField(20);
	public JTextField postalcodeField = new JTextField(20);
	public JTextField countryField = new JTextField(20);
	public JTextField phoneField = new JTextField(20);

	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public AddNewAddress(ListAddress listAddress) {
		super(listAddress, "Edit Address", true);
		this.setSize(800, 500);

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
			row.add(new JLabel("Name"), "100px");
			row.add(nameField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Street Address"), "100px");
			row.add(streetaddressField, "1w");
		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("City"), "100px");
			row.add(cityField, "1w");
		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Postal Code"), "100px");
			row.add(postalcodeField, "1w");

		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Country"), "100px");
			row.add(countryField, "1w");

		}

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Phone"), "100px");
			row.add(phoneField, "1w");

		}

		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(okButton, "auto");
		bottom.add(cancelButton, "auto");

		okButton.addActionListener((e) -> {

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

}
