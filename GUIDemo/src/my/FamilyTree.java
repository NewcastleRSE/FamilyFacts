package my;

import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONObject;

public class FamilyTree extends JDialog{
	
	JPanel root = new JPanel();
//	
//	String personnameshow= null;
//	String fathernameshow=null;
//	String mothernameshow=null;
	
	JTextField parent;
	JTextField textField1 = new JTextField();
	JTextField textField2 = new JTextField();
	JTextField textField3 = new JTextField();
	JTextField textField4 = new JTextField();
	JTextField textField5 = new JTextField();
	JTextField textField6 = new JTextField();
	JTextField textField7 = new JTextField();
	JTextField textField8 = new JTextField();
	JTextField textField9 = new JTextField();
	JTextField textField10 = new JTextField();
	JTextField textField11 = new JTextField();
	JTextField textField12 = new JTextField();
	JTextField textField13 = new JTextField();
	JTextField textField14 = new JTextField();
	JTextField textField15 = new JTextField();
	JTextField textField16 = new JTextField();
	JTextField textField17 = new JTextField();
	JTextField textField18 = new JTextField();
	JTextField textField19 = new JTextField();
	JTextField textField20 = new JTextField();
	JTextField textField21 = new JTextField();
	JTextField textField22 = new JTextField();
	JTextField textField23 = new JTextField();
	JTextField textField24 = new JTextField();
	JTextField textField25 = new JTextField();
	JTextField textField26 = new JTextField();
	JTextField textField27 = new JTextField();
	JTextField textField28 = new JTextField();
	JTextField textField29 = new JTextField();
	JTextField textField30 = new JTextField();
	
	private boolean retValue = false;
	
	public FamilyTree(JFrame owner)
	{
		super(owner, "Family Tree", true);
		this.setSize(1000,1000);
		
		root.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Content Pane
		this.setContentPane(root);
	
		//JTextField textField = new JTextField();
		MigLayout ml=new MigLayout();
		root.setLayout(ml);
		
		//testhttpfamilytree(personnameshow, fathernameshow, mothernameshow);
		
		//JTextField parent;
		//设置第一列
		parent = new JTextField();
		root.add(parent, "cell 1 15");
	//	parent.setText(personnameshow);
		parent.setColumns(15);
		//设置第二列
		//JTextField textField1 = new JTextField();
		root.add(textField1, "cell 2 7");
	//	textField1.setText(fathernameshow);
		textField1.setColumns(15);
		//JTextField textField2 = new JTextField();
		root.add(textField2, "cell 2 23");
	//	textField2.setText(mothernameshow);
		textField2.setColumns(15);
		
		

		//设置第三列
		
		//JTextField textField3 = new JTextField();
		root.add(textField3, "cell 3 3");
		textField3.setColumns(15);
		//JTextField textField4 = new JTextField();
		root.add(textField4, "cell 3 11");
		textField4.setColumns(15);
		//JTextField textField5 = new JTextField();
		root.add(textField5, "cell 3 19");
		textField5.setColumns(15);
		//JTextField textField6 = new JTextField();
		root.add(textField6, "cell 3 27");
		textField6.setColumns(15);
		

		//设置第四列
		
		//JTextField textField7 = new JTextField();
		root.add(textField7, "cell 4 1");
		textField7.setColumns(15);
		//JTextField textField8 = new JTextField();
		root.add(textField8, "cell 4 5");
		textField8.setColumns(15);
		//JTextField textField9 = new JTextField();
		root.add(textField9, "cell 4 9");
		textField9.setColumns(15);
		//JTextField textField10 = new JTextField();
		root.add(textField10, "cell 4 13");
		textField10.setColumns(15);
		//JTextField textField11 = new JTextField();
		root.add(textField11, "cell 4 17");
		textField11.setColumns(15);
		//JTextField textField12 = new JTextField();
		root.add(textField12, "cell 4 21");
		textField12.setColumns(15);
		//JTextField textField13 = new JTextField();
		root.add(textField13, "cell 4 25");
		textField13.setColumns(15);
		//JTextField textField14 = new JTextField();
		root.add(textField14, "cell 4 29");
		textField14.setColumns(15);
		

		//设置第五列
		
		//JTextField textField15 = new JTextField();
		root.add(textField15, "cell 5 0");
		textField15.setColumns(15);
		//JTextField textField16 = new JTextField();
		root.add(textField16, "cell 5 2");
		textField16.setColumns(15);
		//JTextField textField17 = new JTextField();
		root.add(textField17, "cell 5 4");
		textField17.setColumns(15);
		//JTextField textField18 = new JTextField();
		root.add(textField18, "cell 5 6");
		textField18.setColumns(15);
		//JTextField textField19 = new JTextField();
		root.add(textField19, "cell 5 8");
		textField19.setColumns(15);
		//JTextField textField20 = new JTextField();
		root.add(textField20, "cell 5 10");
		textField20.setColumns(15);
		//JTextField textField21 = new JTextField();
		root.add(textField21, "cell 5 12");
		textField21.setColumns(15);
		//JTextField textField22 = new JTextField();
		root.add(textField22, "cell 5 14");
		textField22.setColumns(15);
		//JTextField textField23 = new JTextField();
		root.add(textField23, "cell 5 16");
		textField23.setColumns(15);
		//JTextField textField24 = new JTextField();
		root.add(textField24, "cell 5 18");
		textField24.setColumns(15);
		//JTextField textField25 = new JTextField();
		root.add(textField25, "cell 5 20");
		textField25.setColumns(15);
		//JTextField textField26 = new JTextField();
		root.add(textField26, "cell 5 22");
		textField26.setColumns(15);
		//JTextField textField27 = new JTextField();
		root.add(textField27, "cell 5 24");
		textField27.setColumns(15);
		//JTextField textField28 = new JTextField();
		root.add(textField28, "cell 5 26");
		textField28.setColumns(15);
		//JTextField textField29 = new JTextField();
		root.add(textField29, "cell 5 28");
		textField29.setColumns(15);
		//JTextField textField30 = new JTextField();
		root.add(textField30, "cell 5 30");
		textField30.setColumns(15);
	}
	
	public boolean exec()
	{
		
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width)/2;
		int y = frmRect.y + (frmRect.height - height)/2;
		this.setBounds(x,y, width, height);
		
		
		this.setVisible(true);
		
		return retValue;
	}

	
}
