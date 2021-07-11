package af.swing.image;

import java.awt.Rectangle;




public class AfImageScaler
{
	
	private int imgW,imgH; 
	private Rectangle rect; 
	
	
	public AfImageScaler(int imgW, int imgH, Rectangle rect)
	{
		this.imgW = imgW;
		this.imgH = imgH;
		this.rect = rect;	
	}
	public AfImageScaler(int imgW, int imgH, int dstW, int dstH)
	{
		this(imgW, imgH, new Rectangle(dstW, dstH));	
	}
	
	
	public Rectangle fitXY()
	{		
		return this.rect;
	}
	
	
	public Rectangle fitCenter()
	{
		int width = rect.width;
		int height = rect.height;
		
		int fitW = width;
		int fitH = width * imgH / imgW;
		if( fitH > height )
		{
			fitH = height;
			fitW = height * imgW / imgH;
		}
		int x = (width - fitW ) /2;
		int y = (height - fitH ) /2;
		
		
		return new Rectangle(rect.x + x, rect.y + y, fitW, fitH);
	}
	
	
	public Rectangle fitCenterInside()
	{
		int width = rect.width;
		int height = rect.height;
		int fitW, fitH;
		
		if(imgW <= width && imgH <= height)
		{
			fitW = imgW;
			fitH = imgH;
			
			int x = (width - fitW ) /2;
			int y = (height - fitH ) /2;
			return new Rectangle(rect.x + x, rect.y + y, fitW, fitH);
		}
		else
		{
			return fitCenter();
		}
	}
	
}
