package eclipsechacecalculator;

import eclipsechacecalculator.combatlogic.*;
import eclipsechacecalculator.game.*;
import eclipsechacecalculator.game.ships.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
**	
**	@author Bitza Wolf of Convivial Communism
**/
public class Main
{
	/**
	** @param args Yarrrg!
	**/
    public static void main(String[] args) throws InterruptedException
	{
		LinkedBlockingQueue<Scenario> scenariosToRun = new LinkedBlockingQueue<>();
		ArrayList<Scenario> completedScenarios = new ArrayList<>();
		final float MIN_SCENARIO_CHANCE = 0.001f;
		
		Scenario baseScenario = buildStartingScenario();
		scenariosToRun.put(baseScenario);
		
		while (! scenariosToRun.isEmpty())
		{
			Scenario runMe = scenariosToRun.take();
			runMe.run();
			ArrayList<Scenario> nextScenarios = runMe.getNextScenarios();
			for (Scenario s : nextScenarios)
			{
				if (s.isOutcome())
				{
					completedScenarios.add(s);
					continue;
				}
				if (s.getProbability() < MIN_SCENARIO_CHANCE)
				{
					continue;
				}
				scenariosToRun.put(s);
			}
		}
		
		Scenario.combineScenarios(completedScenarios);
		for (Scenario s : completedScenarios)
		{
			System.out.println(s);
		}
	}
	
	public static Scenario buildStartingScenario()
	{
		Ship playerShip = new Interceptor();
		playerShip.addShipPart(ShipPart.ELECTRON_COMPUTER);
		Ship ancient = new Ancient();
		
		Team playerTeam = new Team("Player", false);
		playerTeam.addShips(playerShip, 2);
		
		Team neutralTeam = new Team("Neutrals", true);
		neutralTeam.addShips(ancient, 1);
		
		int playersBestInit = playerTeam.getHighestInitiative();
		int ancientsBestInit = neutralTeam.getHighestInitiative();
		boolean defendersTurn = (ancientsBestInit >= playersBestInit);
		int initiative = defendersTurn ? ancientsBestInit : playersBestInit;
		
		return new Scenario(1.0f, neutralTeam, playerTeam, initiative, defendersTurn);
	}
}
