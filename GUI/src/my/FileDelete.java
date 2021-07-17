package my;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import smx.swing.AfPanel;
import smx.swing.layout.AfColumnLayout;
import smx.swing.layout.AfRowLayout;

public class FileDelete extends JDialog {

	JTextField filedeleteField = new JTextField(20);
	JButton button = new JButton("OK");
	private boolean retValue = false;

	public FileDelete(JFrame owner) {

		super(owner, "Delete File", true);
		this.setSize(300, 150);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));

		AfPanel main = new AfPanel();
		root.add(main, "1w");
		main.setLayout(new AfColumnLayout(10));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		main.padding(10);

		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("Database Path:"));
			row.add(filedeleteField, "1w");

		}
		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(button, "auto");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String condition = filedeleteField.getText().trim();
				testhttpfiledelete(condition);
			}
		});

	}

	public void testhttpfiledelete(String database_path) {

		TestCallHttpFileDelete.httpURLPOSTCase(database_path);
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