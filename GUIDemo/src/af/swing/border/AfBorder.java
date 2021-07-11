package af.swing.border;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;



public class AfBorder
{
	
	public static void addPadding(JComponent c, int size)
	{
		addPadding(c, size, size, size, size);
	}
	
	
	public static void addPadding(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		Border oldBorder = c.getBorder();
		if(oldBorder != null)
		{
			
			border = BorderFactory.createCompoundBorder(oldBorder, border);
		}
		c.setBorder(border);
	}
	
	
	public static void addMargin(JComponent c, int size)
	{
		addMargin(c, size, size, size, size);
	}
	
	
	public static void addMargin(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		Border oldBorder = c.getBorder();
		if(oldBorder != null)
		{
			
			border = BorderFactory.createCompoundBorder(border, oldBorder);
		}
		c.setBorder(border);
	}
	
	
	public static JComponent wrap(JComponent c, int gap)
	{
		return wrap(c, gap,gap, gap, gap);
	}
	
	public static JComponent wrap(JComponent c, int top, int left, int bottom, int right)
	{
		Box box = Box.createHorizontalBox();
		box.setOpaque(true);
		box.setBackground(Color.YELLOW);
		box.add(c);
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		box.setBorder(border);
		return box;
	}
}
