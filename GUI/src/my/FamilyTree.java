package my;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sf.json.JSONObject;

public class FamilyTree extends JDialog {

	JPanel root = new JPanel();

	JTextField parent;
	JTextField textField1 = new JTextField();// fathername
	JTextField textField2 = new JTextField();// mothername
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

	public FamilyTree(JFrame owner) {
		super(owner, "Family Tree", true);
		this.setSize(1000, 1000);

		root.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Content Pane
		this.setContentPane(root);

		MigLayout ml = new MigLayout();
		root.setLayout(ml);

		// Set the first column
		// 设置第一列
		parent = new JTextField();
		root.add(parent, "cell 1 15");

		parent.setColumns(15);
		// Set the second column
		// 设置第二列

		root.add(textField1, "cell 2 7");

		textField1.setColumns(15);

		root.add(textField2, "cell 2 23");

		textField2.setColumns(15);

		// Set the third column
		// 设置第三列

		root.add(textField3, "cell 3 3");
		textField3.setColumns(15);

		root.add(textField4, "cell 3 11");
		textField4.setColumns(15);

		root.add(textField5, "cell 3 19");
		textField5.setColumns(15);

		root.add(textField6, "cell 3 27");
		textField6.setColumns(15);

		// Set the fourth column
		// 设置第四列

		root.add(textField7, "cell 4 1");
		textField7.setColumns(15);

		root.add(textField8, "cell 4 5");
		textField8.setColumns(15);

		root.add(textField9, "cell 4 9");
		textField9.setColumns(15);

		root.add(textField10, "cell 4 13");
		textField10.setColumns(15);

		root.add(textField11, "cell 4 17");
		textField11.setColumns(15);

		root.add(textField12, "cell 4 21");
		textField12.setColumns(15);

		root.add(textField13, "cell 4 25");
		textField13.setColumns(15);

		root.add(textField14, "cell 4 29");
		textField14.setColumns(15);

		// Set the fifth column
		// 设置第五列

		root.add(textField15, "cell 5 0");
		textField15.setColumns(15);

		root.add(textField16, "cell 5 2");
		textField16.setColumns(15);

		root.add(textField17, "cell 5 4");
		textField17.setColumns(15);

		root.add(textField18, "cell 5 6");
		textField18.setColumns(15);

		root.add(textField19, "cell 5 8");
		textField19.setColumns(15);

		root.add(textField20, "cell 5 10");
		textField20.setColumns(15);

		root.add(textField21, "cell 5 12");
		textField21.setColumns(15);

		root.add(textField22, "cell 5 14");
		textField22.setColumns(15);

		root.add(textField23, "cell 5 16");
		textField23.setColumns(15);

		root.add(textField24, "cell 5 18");
		textField24.setColumns(15);

		root.add(textField25, "cell 5 20");
		textField25.setColumns(15);

		root.add(textField26, "cell 5 22");
		textField26.setColumns(15);

		root.add(textField27, "cell 5 24");
		textField27.setColumns(15);

		root.add(textField28, "cell 5 26");
		textField28.setColumns(15);

		root.add(textField29, "cell 5 28");
		textField29.setColumns(15);

		root.add(textField30, "cell 5 30");
		textField30.setColumns(15);
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

	public void paint(Graphics g) {
		super.paint(g);

		// Draw connecting lines, x1 y1 x2 y2 respectively represent the two ends of the
		// line. The upper left corner is 0, 0 points, and gradually x increases to the
		// right, and y increases gradually.
		// 画连接线,x1 y1 x2 y2分别代表线的两端点 左上角为0,0点,往右逐渐x增加,往下逐渐y增加

		final BasicStroke wideStroke = new BasicStroke(2.0f);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(wideStroke);
		// 1
		g2.drawLine(120, 274, 120, 494);
		g2.drawLine(120, 274, 210, 274);

		g2.drawLine(120, 535, 120, 754);
		g2.drawLine(120, 754, 210, 754);
		// 2
		g2.drawLine(315, 154, 405, 154);
		g2.drawLine(315, 154, 315, 254);

		g2.drawLine(315, 389, 405, 389);
		g2.drawLine(315, 389, 315, 289);

		g2.drawLine(315, 634, 405, 634);
		g2.drawLine(315, 634, 315, 734);

		g2.drawLine(315, 874, 405, 874);
		g2.drawLine(315, 874, 315, 774);

		// 3
		g2.drawLine(510, 94, 600, 94);
		g2.drawLine(510, 94, 510, 134);

		g2.drawLine(510, 169, 510, 209);
		g2.drawLine(510, 209, 600, 209);

		g2.drawLine(510, 334, 600, 334);
		g2.drawLine(510, 334, 510, 374);

		g2.drawLine(510, 409, 510, 449);
		g2.drawLine(510, 449, 600, 449);

		g2.drawLine(510, 574, 600, 574);
		g2.drawLine(510, 574, 510, 614);

		g2.drawLine(510, 649, 510, 689);
		g2.drawLine(510, 689, 600, 689);

		g2.drawLine(510, 814, 600, 814);
		g2.drawLine(510, 814, 510, 854);

		g2.drawLine(510, 889, 510, 929);
		g2.drawLine(510, 929, 600, 929);

		// 4
		g2.drawLine(705, 62, 795, 62);
		g2.drawLine(705, 62, 705, 79);

		g2.drawLine(705, 104, 705, 124);
		g2.drawLine(705, 124, 795, 124);

		g2.drawLine(705, 182, 795, 182);
		g2.drawLine(705, 182, 705, 199);

		g2.drawLine(705, 224, 705, 244);
		g2.drawLine(705, 244, 795, 244);

		g2.drawLine(705, 302, 795, 302);
		g2.drawLine(705, 302, 705, 319);

		g2.drawLine(705, 344, 705, 364);
		g2.drawLine(705, 364, 795, 364);

		g2.drawLine(705, 422, 795, 422);
		g2.drawLine(705, 422, 705, 439);

		g2.drawLine(705, 464, 705, 484);
		g2.drawLine(705, 484, 795, 484);

		g2.drawLine(705, 542, 795, 542);
		g2.drawLine(705, 542, 705, 558);

		g2.drawLine(705, 584, 705, 604);
		g2.drawLine(705, 604, 795, 604);

		g2.drawLine(705, 662, 795, 662);
		g2.drawLine(705, 662, 705, 679);

		g2.drawLine(705, 704, 705, 724);
		g2.drawLine(705, 724, 795, 724);

		g2.drawLine(705, 782, 795, 782);
		g2.drawLine(705, 782, 705, 798);

		g2.drawLine(705, 824, 705, 844);
		g2.drawLine(705, 844, 795, 844);

		g2.drawLine(705, 902, 795, 902);
		g2.drawLine(705, 902, 705, 919);

		g2.drawLine(705, 944, 705, 964);
		g2.drawLine(705, 964, 795, 964);

	}

}