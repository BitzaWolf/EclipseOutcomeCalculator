package eclipsechacecalculator.gui;

import eclipsechacecalculator.gui.teams.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
**	
**	@author Bitza Wolf
**/
public class MainPanel extends JPanel implements ActionListener
{
	private TeamPanel topPanel;
	private TeamPanel botPanel;
	
	public MainPanel()
	{
		super(new BorderLayout());
		
		topPanel = new PlayerPanel();
		botPanel = new TeamAncientsPanel();
		JButton butt = new JButton("Run Simulation!");
		butt.addActionListener(this);
		
		JPanel teamPanels = new JPanel(new GridLayout(2, 1));
		teamPanels.add(new JScrollPane(topPanel));
		teamPanels.add(new JScrollPane(botPanel));
		super.add(teamPanels, BorderLayout.CENTER);
		
		super.add(butt, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println(topPanel.getTeam());
		System.out.println(botPanel.getTeam() + "\n\n");
	}
}
