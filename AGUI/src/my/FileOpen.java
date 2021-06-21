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

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;

public class FileOpen extends JDialog{
	
	JTextField textField = new JTextField(20);
	JButton button = new JButton("选择");
	JButton button2 = new JButton("确定");
	
	private boolean retValue = false;
	
	public FileOpen(JFrame owner)
	{
		
		
		
		super(owner, "Open", true);
		this.setSize(500,100);
		
		// 设置一个容器
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
				
		
		root.add(new JLabel("File"));
		root.add(textField);
		root.add(button);
		root.add(button2);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				test1();
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				test2();
			}
		});
		
		
	
	}
	
	// 选择打开文件
	public void test1()
	{		
		JFileChooser chooser = new JFileChooser();
		
		//FileNameExtensionFilter: 文件名后缀过滤器
		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		
		// 显示对话框
		int ret = chooser.showOpenDialog(this);
		// 获取用户选择的结果
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			// 结果为：已经存在的一个文件
			File file = chooser.getSelectedFile();
			textField.setText(file.getAbsolutePath());
		}
	}
	
	


	public void test2() 
	{
		TestCallHttp t=new TestCallHttp();
		t.httpURLPOSTCase();
	}
	
	public boolean exec()
	{
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width)/2;
		int y = frmRect.y + (frmRect.height - height)/2;
		this.setBounds(x,y, width, height);
		
		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);
		
		return retValue;
	}
	
	
}


