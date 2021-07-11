package af.swing.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class AfImageView extends JPanel
{
	
	public static final int FIT_XY = 0;
	public static final int FIT_CENTER = 1;
	public static final int FIT_CENTER_INSIDE = 2;
	
	private Image image;
	private int scaleType = FIT_CENTER;
	private Color bgColor = Color.WHITE; // 背景色
	
	public AfImageView()
	{
		this.setOpaque(false);
	}
	
	public Image getImage()
	{
		return image;
	}

	public void setImage(Image image)
	{
		this.image = image;
		repaint();
	}

	public int getScaleType()
	{
		return scaleType;		
	}

	public void setScaleType(int scaleType)
	{
		this.scaleType = scaleType;
		repaint();
	}

	public Color getBgColor()
	{
		return bgColor;		
	}

	public void setBgColor(Color bgColor)
	{
		this.bgColor = bgColor;
		repaint();
	}
	
	
	@Override
	protected void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		
		int width = this.getWidth();
		int height = this.getHeight();

		
		g.setColor(bgColor);
		g.fillRect(0, 0, width,height);
		
		/////////////////////////
		if(image != null)
		{
			int imgW = image.getWidth(null);
			int imgH = image.getHeight(null);
			
			
			AfImageScaler scaler = new AfImageScaler(imgW, imgH, width,height);
			
			
			Rectangle fit = scaler.fitXY();
			if(scaleType == FIT_CENTER)
				fit = scaler.fitCenter();
			else if(scaleType == FIT_CENTER_INSIDE)
				fit = scaler.fitCenterInside();

			g.drawImage(image, fit.x, fit.y, fit.width, fit.height,	null);
		}
	}

}
