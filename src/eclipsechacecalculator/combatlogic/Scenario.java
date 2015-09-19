package eclipsechacecalculator.combatlogic;

import java.util.*;

/**
 * 
 * @author Wolf
 */
public class Scenario
{
	private static final int MAX_INITIATIVE = 8;
	
	private float probability;
	private final Team defensiveTeam, offensiveTeam;
	private final int startingInitiative;
	private final boolean defendersTurn;
	private final ArrayList<Scenario> resultingScenarios;
	private boolean alreadyRan;
	
	public Scenario(float prob, Team def, Team off, int initiative, boolean defendersTurn)
	{
		probability = prob;
		defensiveTeam = def;
		offensiveTeam = off;
		startingInitiative = initiative;
		this.defendersTurn = defendersTurn;
		
		alreadyRan = false;
		resultingScenarios = new ArrayList<>();
	}
	
	public void run()
	{
		if (alreadyRan)
		{
			return;
		}
		
		alreadyRan = true;
		Team firingTeam = defendersTurn ? defensiveTeam : offensiveTeam;
		Team receivingTeam = defendersTurn ? offensiveTeam : defensiveTeam;
		
		int initiative = startingInitiative;
		ArrayList<ShipGroup> ships;
		ships = firingTeam.getShips(initiative);
		if (defendersTurn)
		{
			while (receivingTeam.getShips(initiative).isEmpty())
			{
				reduceInitiative(initiative);
				ships.addAll(firingTeam.getShips(initiative));
			}
		}
		else
		{
			reduceInitiative(initiative);
			while (receivingTeam.getShips(initiative).isEmpty())
			{
				ships.addAll(firingTeam.getShips(initiative));
				reduceInitiative(initiative);
			}
		}
		
		ArrayList<Volley> vollies = new ArrayList<>();
		for (ShipGroup sg : ships)
		{
			vollies.add(sg.getVolley());
		}
		
		ArrayList<DiceRoll> rolls = DiceRoll.getRolls(vollies);
		ArrayList<Team> resultingTeams = new ArrayList<>();
		for (DiceRoll roll : rolls)
		{
			Team resultingTeam = receivingTeam.copy();
			resultingTeam.disperseHits(roll.getHits());
			resultingTeams.add(resultingTeam);
		}
		
		for (int i = 0; i < resultingTeams.size(); ++i)
		{
			float resultProbability = probability * rolls.get(i).getProbability();
			Team resultingTeam = resultingTeams.get(i);
			Team resultingDefensiveTeam = defendersTurn ? defensiveTeam : resultingTeam;
			Team resultingOffensiveTeam = defendersTurn ? resultingTeam : offensiveTeam;
			Scenario addMe = new Scenario(resultProbability, resultingDefensiveTeam,
					resultingOffensiveTeam, initiative, ! defendersTurn);
			
			resultingScenarios.add(addMe);
		}
		
		for (int i = 1; i < resultingScenarios.size(); ++i)
		{
			Scenario a = resultingScenarios.get(i - 1);
			Scenario b = resultingScenarios.get(i);
			if (a.equals(b))
			{
				a.probability += b.probability;
				resultingScenarios.remove(i);
				--i;
			}
		}
	}
	
	private int reduceInitiative(int initiative)
	{
		--initiative;
		if (initiative < 0)
		{
			initiative = MAX_INITIATIVE;
		}
		return initiative;
	}
	
	public boolean equals(Scenario other)
	{
		return (defendersTurn == other.defendersTurn &&
			defensiveTeam.equals(other.defensiveTeam) &&
			offensiveTeam.equals(other.offensiveTeam) &&
			startingInitiative == other.startingInitiative);
	}
}
