package eclipsechacecalculator.combatlogic;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
**	
**	@author Bitza Wolf
**/
public class HeadScenarioRunner
{
	private static final float MIN_SCENARIO_CHANCE = 0.00001f;
	private static final int NUM_OF_THREADS = 5;
	
	private final Team defenders, attackers;
	private final Scenario baseScenario;
	private final LinkedBlockingQueue<Scenario> scenariosToRun;
	private final ArrayList<Scenario> completedScenarios;
	
	private int threadID = 0;
	private float ScenariosSkipped;
	private ThreadGroup scenarioThreads;
	
	public HeadScenarioRunner(Team defenders, Team attackers)
	{
		this.defenders = defenders;
		this.attackers = attackers;
		ScenariosSkipped = 0;
		
		int attackersBestInit = attackers.getHighestInitiative();
		int defendersBestInit = defenders.getHighestInitiative();
		boolean defendersTurn = (defendersBestInit >= attackersBestInit);
		int initiative = defendersTurn ? defendersBestInit : attackersBestInit;
		
		baseScenario = new Scenario(1.0f, defenders, attackers, initiative, defendersTurn);
		
		scenariosToRun = new LinkedBlockingQueue<>();
		completedScenarios = new ArrayList<>();
		
		try
		{
			scenariosToRun.put(baseScenario);
		}
		catch (InterruptedException ie)
		{
			
		}
		
		scenarioThreads = new ThreadGroup("Scenario Threads");
	}
	
	private synchronized void addCompletedScenario(Scenario s)
	{
		completedScenarios.add(s);
	}
	
	private synchronized void addScenariosSkipped(float p)
	{
		ScenariosSkipped += p;
	}
	
	public boolean hasRan()
	{
		return (! completedScenarios.isEmpty());
	}
	
	public void run()
	{
		for (int i = 0; i < NUM_OF_THREADS; ++i)
		{
			ScenarioRunnerThread t = new ScenarioRunnerThread(scenarioThreads);
			t.start();
		}
	}
	
	private class ScenarioRunnerThread extends Thread
	{
		public ScenarioRunnerThread(ThreadGroup group)
		{
			super(group, "ScenarioThread-" + threadID++);
		}
		
		@Override
		public void run()
		{
			while (! super.isInterrupted())
			{
				Scenario runMe;
				try
				{
					runMe = scenariosToRun.take();
				}
				catch (InterruptedException ie)
				{
					break;
				}
				runMe.run();
				ArrayList<Scenario> nextScenarios = runMe.getNextScenarios();
				for (Scenario s : nextScenarios)
				{
					if (s.isOutcome())
					{
						addCompletedScenario(s);
						continue;
					}
					if (s.getProbability() < MIN_SCENARIO_CHANCE)
					{
						addScenariosSkipped(s.getProbability());
						ScenariosSkipped += s.getProbability();
						continue;
					}
					try
					{
						scenariosToRun.put(s);
					}
					catch (InterruptedException ie)
					{
						break;
					}
				}
			}
		}
	}
}
