package my;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PedigreePanel extends JPanel{
//	
//	public PedigreePanel()
//	{
//		
//	}
//	public void paintComponent(Graphics g){
//	        super.paintComponent(g);
//	        g.setColor(Color.gray);
//		    g.drawLine(500, 50, 500, 250);
//	    }

	public PedigreePanel(JPanel pedigreePanel)
	{
	
		JTextField parent;

		parent = new JTextField();
		pedigreePanel.add(parent, "cell 0 15");
		parent.setColumns(15);
		
		
		JTextField ptextField1 = new JTextField();
		pedigreePanel.add(ptextField1, "cell 1 7");
		ptextField1.setColumns(15);
		JTextField ptextField2 = new JTextField();
		pedigreePanel.add(ptextField2, "cell 1 23");
		ptextField2.setColumns(15);

		for (int i = 3; i < 28; i += 8) {
			JTextField textField = new JTextField();
			pedigreePanel.add(textField, "cell 2 " + i);
			textField.setColumns(15);
		}
		for (int i = 1; i < 30; i += 4) {
			JTextField textField = new JTextField();
			pedigreePanel.add(textField, "cell 3 " + i);
			textField.setColumns(15);
		}
		for (int i = 0; i < 31; i += 2) {
			JTextField textField = new JTextField();
			pedigreePanel.add(textField, "cell 4 " + i);
			textField.setColumns(15);
	    }
    }
	
	
//	public void paint(Graphics g) {
//		super.paintComponent(g); 
//	      g.setColor(Color.gray);
//	      g.drawLine(500, 50, 500, 250);
//	  }
//	
	
//	public void paint(Graphics g) {
//		Window pedigreePanel = null;
//		pedigreePanel.paint(g); 
//	    Graphics2D g2 = (Graphics2D) g;
//	    Line2D lin = new Line2D.Float(700, 100, 250, 260);
//	    g2.draw(lin);
//	  }
//
//	
//	
	
	
	
	
	
	
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		//画连接线,x1 y1 x2 y2分别代表线的两端点 左上角为0,0点,往右逐渐x增加,往下逐渐y增加
//		g.drawLine(100, 237, 300, 420);
//		g.drawLine(700, 440, 300, 623);
//		g.drawLine(300, 237, 300, 420);
//		g.drawLine(300, 440, 300, 623);
//	}
	
	
}