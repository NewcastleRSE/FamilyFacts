package my;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import smx.swing.AfPanel;
import smx.swing.layout.AfColumnLayout;

public class FileNew extends JDialog {

	JTextField filenewField = new JTextField(20);
	JButton button = new JButton("OK");

	private boolean retValue = false;

	public FileNew(JFrame owner) {

		super(owner, "New", true);
		this.setSize(500, 100);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());

		root.add(new JLabel("File"));
		root.add(filenewField);
		root.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String condition = filenewField.getText().trim();
				testhttpfilenew(condition);
			}
		});

	}

	public void testhttpfilenew(String database_path) {
		// TODO Auto-generated method stub

		TestCallHttpFileNew.httpURLPOSTCase(database_path);
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
