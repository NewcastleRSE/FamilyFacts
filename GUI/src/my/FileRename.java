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

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

//File-Rename
public class FileRename extends JDialog {

	JTextField textField = new JTextField(20);
	JTextField textField1 = new JTextField(20);
	JButton button = new JButton("OK");

	private boolean retValue = false;

	public FileRename(JFrame owner) {

		super(owner, "Rename", true);
		this.setSize(300, 200);

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
			row.add(new JLabel("File Name"));
			row.add(textField, "1w");

		}
		if (true) {
			AfPanel row = new AfPanel();
			main.add(row, "24px");
			row.setLayout(new AfRowLayout(10));
			row.add(new JLabel("File Rename"));
			row.add(textField1, "1w");

		}

		AfPanel bottom = new AfPanel();
		root.add(bottom, "30px");
		bottom.setLayout(new AfRowLayout(10));
		bottom.add(new JLabel(), "1w");
		bottom.add(button, "auto");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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

}
