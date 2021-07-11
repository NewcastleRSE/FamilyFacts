package af.swing.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

public class AfColumnLayout implements LayoutManager2
{
	private List<Item> items = new ArrayList<>();
	private int gap = 2;
	private boolean usePerferredSize = false; 
	
	public AfColumnLayout()
	{		
	}
	public AfColumnLayout(int gap)
	{		
		this.gap = gap;
	}
	public AfColumnLayout(int gap, boolean usePerferredSize)
	{	
		this.gap = gap;
		this.usePerferredSize = usePerferredSize;
	}
		
	@Override
	public void addLayoutComponent(String name, Component comp)
	{
		Item item = new Item();
		item.comp = comp;
		item.constraints = "auto";
		items.add(item);
	}

	@Override
	public void removeLayoutComponent(Component comp)
	{
		Iterator<Item> iter = items.iterator();
		while(iter.hasNext())
		{
			Item item = iter.next();
			if(item.comp == comp)
			{
				iter.remove();
			}
		}
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints)
	{
		Item item = new Item();
		item.comp = comp;
		item.constraints = (String) constraints;
		items.add(item);
	}
	
	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return new Dimension(30,30);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return new Dimension(30,30);
	}

	@Override
	public Dimension maximumLayoutSize(Container target)
	{
		return new Dimension(30,30);
	}

	
	@Override
	public void layoutContainer(Container parent)
	{
		
		Rectangle rect = new Rectangle(parent.getWidth(), parent.getHeight());
		
		Insets insets = parent.getInsets();
		rect.x += insets.left;
		rect.y += insets.top;
		rect.width -= (insets.left + insets.right);
		rect.height -= (insets.top + insets.bottom);
		
		
		List<Item> validItems = new ArrayList<>();
		for(Item it: items )
		{
			if(it.comp.isVisible())
				validItems.add(it);
		}
		
		
		int totalGapSize = gap * (validItems.size() - 1);
		int validSize = rect.height - totalGapSize;
		int totalSize = 0;
		int totalWeight = 0;
		for(Item it : validItems)
		{
			Dimension preferred = it.comp.getPreferredSize();
			it.width = usePerferredSize ? preferred.width : rect.width;
			it.height = preferred.height;
			it.weight = 0;
			
			
			String cstr = it.constraints;
			if( cstr == null || cstr.length() == 0)
			{
				//System.out.println("(AfColumnLayout) Warn: Must define constraints when added to container!");
			}
			else if( cstr.equals("auto"))
			{
			}
			else if(cstr.endsWith("%")) 
			{
				int num = Integer.valueOf(cstr.substring(0,cstr.length()-1));
				it.height = validSize * num / 100;
			}
			else if(cstr.endsWith("w")) 
			{
				int num = Integer.valueOf(cstr.substring(0,cstr.length()-1));
				it.height = 0;
				it.weight = num;
			}
			else if(cstr.endsWith("px")) 
			{
				int num = Integer.valueOf(cstr.substring(0,cstr.length()-2));
				it.height = num;
			}
			else 
			{
				int num = Integer.valueOf(cstr);
				it.height = num;
			}
			
			totalSize += it.height;
			totalWeight += it.weight;
			
			//System.out.println("计算值：width=" + it.width + ",weight=" + it.weight);
		}
		
		
		if( totalWeight > 0)
		{
			int remainSize = validSize - totalSize;
			double unit = (double) remainSize / totalWeight;
			for(Item it : validItems)
			{
				if(it.weight > 0)
				{
					it.height = (int)( unit * it.weight );
				}
			}			
		}

		//System.out.println("总宽度: " + rect.width);
		
		int y = 0;
		for(Item it : validItems)
		{
			int x = 0; 
			if(y + it.height > rect.height)
				it.height = rect.height - y;
			if(it.height <= 0) break;
			
			it.comp.setBounds(rect.x + x, rect.y + y, it.width, it.height);
			
			//System.out.println("宽度: " + it.width);
			y += it.height;
			y += gap;
		}
	}

	@Override
	public float getLayoutAlignmentX(Container target)
	{
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target)
	{
		return 0;
	}

	@Override
	public void invalidateLayout(Container target)
	{
		
	}

	///////////////////////
	private static class Item
	{
		Component comp;
		String constraints = "auto";
		int width = 0;
		int height = 0;
		int weight = 0;  
	}
	

}
