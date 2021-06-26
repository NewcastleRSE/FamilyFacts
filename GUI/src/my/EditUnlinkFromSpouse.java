package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;
import net.miginfocom.swing.MigLayout;

//Edit-Unlink-FromSpouse
public class EditUnlinkFromSpouse extends JDialog {

	AfPanel root = new AfPanel();
	JPanel jpanel1 = new JPanel();
	JPanel jpanel2 = new JPanel();
	AfPanel jpanel3 = new AfPanel();
	MigLayout layout = new MigLayout();
	JButton UnlinkButton = new JButton("Unlink from Spouse");
	JButton cancelButton = new JButton("Cancel");

	private boolean retValue = false;

	public EditUnlinkFromSpouse(JFrame owner) {
		super(owner, "Edit Unlink From SpouseS", true);
		this.setSize(500, 400);

		this.setContentPane(root);
		// root.setLayout(layout);
		root.setLayout(new AfColumnLayout(10));

		jpanel1.setPreferredSize(new Dimension(500, 100));
		jpanel2.setPreferredSize(new Dimension(500, 200));
		jpanel3.setPreferredSize(new Dimension(500, 50));

//		root.add(jpanel1,"dock north");
//		root.add(jpanel2,"wrap");
		root.add(jpanel1);
		root.add(jpanel2);

		JLabel label = new JLabel("Unlink from Spouse?");
		JLabel label1 = new JLabel("Do you want to Unlink the hightlighted person as a parent in this family?");
		jpanel1.add(label);
		jpanel1.add(label1);

		JList list = new JList();
		String[] dd1 = { "XXX(unlink Spouse)" };
		list = new JList(dd1);
		list.setVisibleRowCount(2);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(480, 180));
		jpanel2.add(scrollPane, BorderLayout.CENTER);

		root.add(jpanel3, "30px");
		jpanel3.setLayout(new AfRowLayout(10));
		jpanel3.add(new JLabel(), "1w");
		jpanel3.add(UnlinkButton, "auto");
		jpanel3.add(cancelButton, "auto");

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
