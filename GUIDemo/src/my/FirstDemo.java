package my;

import javax.swing.JFrame;

public class FirstDemo	
{
	private static void createGUI()
	{
		
		JFrame frame = new MyFrame("First Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		frame.setSize(1800, 1100);
		
		
		frame.setVisible(true);
		frame.setTitle("Main page");
	}
	
	public static void main(String[] args)
	{
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createGUI();
			}
		});

	}
}
