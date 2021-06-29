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

//File-New（Need to be modified）
public class FileNew extends JDialog {

	JTextField textField = new JTextField(20);
	JButton button = new JButton("Choose");

	private boolean retValue = false;

	public FileNew(JFrame owner) {

		super(owner, "New", true);
		this.setSize(500, 100);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());

		root.add(new JLabel("File"));
		root.add(textField);
		root.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				test2();
			}
		});

	}

	// 选择保存文件
	private void test2() {
		JFileChooser chooser = new JFileChooser();

		// FileNameExtensionFilter: 文件名后缀过滤器
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML文件", "xml");
		chooser.setFileFilter(filter);

		// 显示对话框 showSaveDialog
		int ret = chooser.showSaveDialog(this);
		// 获取用户选择的结果
		if (ret == JFileChooser.APPROVE_OPTION) {
			// 结果为：用户要保存的文件的路径
			File file = chooser.getSelectedFile();
			textField.setText(file.getAbsolutePath());
		}
	}

	public boolean exec() {
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);

		return retValue;
	}

}
