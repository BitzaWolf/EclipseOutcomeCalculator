package eclipsechacecalculator;

import eclipsechacecalculator.gui.MainPanel;
import javax.swing.*;

/**
**	
**	@author Bitza Wolf of Convivial Communism
**/
public class Main
{
	/**
	** @param args Yarrrg!
	**/
    public static void main(String[] args)
	{
		JFrame frame = new JFrame("Eclipse Battle Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 600);
		
		frame.add(new MainPanel());
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
