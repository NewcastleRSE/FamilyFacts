package my;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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

public class FileOpen extends JDialog {

	JTextField textField = new JTextField(20);

	JButton button2 = new JButton("OK");

	private boolean retValue = false;

	public FileOpen(JFrame owner) {

		super(owner, "Open", true);
		this.setSize(500, 100);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());

		root.add(new JLabel("File"));
		root.add(textField);

		root.add(button2);

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				test2();
			}
		});

	}

	public void test2() {

		TestCallHttp.httpURLPOSTCase();
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
