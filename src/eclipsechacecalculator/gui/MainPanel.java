package eclipsechacecalculator.gui;

import eclipsechacecalculator.combatlogic.*;
import eclipsechacecalculator.gui.teams.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.*;

/**
**	
**	@author Bitza Wolf
**/
public class MainPanel extends JPanel implements ActionListener
{
	private static final float MIN_SCENARIO_CHANCE = 0.00001f;
	
	private TeamPanel topPanel;
	private TeamPanel botPanel;
	
	private final JButton butt;
	private final JProgressBar outcomeBar;
	private final JLabel skippedLabel;
	private float skippedScenarios;
	
	public MainPanel()
	{
		super(new BorderLayout());
		
		topPanel = new PlayerPanel();
		botPanel = new TeamAncientsPanel();
		butt = new JButton("Run Simulation!");
		butt.addActionListener(this);
		outcomeBar = new JProgressBar(0, 100);
		outcomeBar.setStringPainted(true);
		skippedLabel = new JLabel("0%");
		
		JPanel teamPanels = new JPanel(new GridLayout(2, 1));
		teamPanels.add(new JScrollPane(topPanel));
		teamPanels.add(new JScrollPane(botPanel));
		super.add(teamPanels, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.add(outcomeBar);
		panel.add(butt);
		panel.add(skippedLabel);
		
		super.add(panel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		butt.setEnabled(false);
		Scenario baseScenario = buildBaseScenario();
		ArrayList<Scenario> possibleOutcomes = runScenarios(baseScenario);
		updateProgressBar(possibleOutcomes);
		butt.setEnabled(true);
	}
	
	public Scenario buildBaseScenario()
	{
		Team attackers = topPanel.getTeam();
		Team defenders = botPanel.getTeam();
		
		int attackersBestInit = attackers.getHighestInitiative();
		int defendersBestInit = defenders.getHighestInitiative();
		boolean defendersTurn = (defendersBestInit >= attackersBestInit);
		int initiative = defendersTurn ? defendersBestInit : attackersBestInit;
		
		return new Scenario(1.0f, defenders, attackers, initiative, defendersTurn);
	}
	
	private ArrayList<Scenario> runScenarios(Scenario baseScenario)
	{
		LinkedBlockingQueue<Scenario> scenariosToRun = new LinkedBlockingQueue<>();
		ArrayList<Scenario> completedScenarios = new ArrayList<>();
		skippedScenarios = 0;
		
		try
		{
			scenariosToRun.put(baseScenario);
		}
		catch (InterruptedException ie)
		{
			ie.printStackTrace();
			return null;
		}
		
		while (! scenariosToRun.isEmpty())
		{
			Scenario runMe = null;
			try
			{
				runMe = scenariosToRun.take();
			}
			catch (InterruptedException ie)
			{
				continue;
			}
			runMe.run();
			ArrayList<Scenario> nextScenarios = runMe.getNextScenarios();
			for (Scenario s : nextScenarios)
			{
				if (s.isOutcome())
				{
					completedScenarios.add(s);
					//updateProgressBar(completedScenarios);
					continue;
				}
				if (s.getProbability() < MIN_SCENARIO_CHANCE)
				{
					skippedScenarios += s.getProbability();
					continue;
				}
				try
				{
					scenariosToRun.put(s);
				}
				catch (InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		}
		
		Scenario.combineScenarios(completedScenarios);
		
		return completedScenarios;
	}
	
	private void updateProgressBar(ArrayList<Scenario> scenarios)
	{
		float defendersWinPercent = 0;
		float attackersWinPercent = 0;
		
		Team defendersTeam = botPanel.getTeam();
		Team attackersTeam = topPanel.getTeam();
		
		for (Scenario s : scenarios)
		{
			Team winningTeam = s.getWinningTeam();
			if (winningTeam.lightlyEquals(defendersTeam))
			{
				defendersWinPercent += s.getProbability();
			}
			else if (winningTeam.lightlyEquals(attackersTeam))
			{
				attackersWinPercent += s.getProbability();
			}
		}
		
		System.out.println("D: " + defendersWinPercent + "  A: " + attackersWinPercent);
		
		attackersWinPercent *= 100;
		int attackersWinAsInt = (int) attackersWinPercent;
		outcomeBar.setValue(attackersWinAsInt);
		skippedLabel.setText((skippedScenarios * 100) + "%");
	}
}
