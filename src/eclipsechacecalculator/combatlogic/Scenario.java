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
		
		resultingScenarios = new ArrayList<>();
		alreadyRan = false;
		
		if (defensiveTeam.isDefeated() || offensiveTeam.isDefeated())
		{
			alreadyRan = true;
			if (defensiveTeam.isDefeated())
			{
				offensiveTeam.healShips();
			}
			else
			{
				defensiveTeam.healShips();
			}
		}
	}
	
	public boolean equals(Scenario other)
	{
		return (defendersTurn == other.defendersTurn &&
			defensiveTeam.equals(other.defensiveTeam) &&
			offensiveTeam.equals(other.offensiveTeam) &&
			startingInitiative == other.startingInitiative);
	}
	
	public ArrayList<Scenario> getNextScenarios()
	{
		return resultingScenarios;
	}
	
	public float getProbability()
	{
		return probability;
	}
	
	public boolean isOutcome()
	{
		return (defensiveTeam.isDefeated() || offensiveTeam.isDefeated());
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
				initiative = reduceInitiative(initiative);
				ships.addAll(firingTeam.getShips(initiative));
			}
		}
		else
		{
			initiative = reduceInitiative(initiative);
			while (receivingTeam.getShips(initiative).isEmpty())
			{
				ships.addAll(firingTeam.getShips(initiative));
				initiative = reduceInitiative(initiative);
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
		
		combineScenarios(resultingScenarios);
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
	
	@Override
	public String toString()
	{
		String retMe = "SCENARIO\n";
		retMe += "----------------\n";
		retMe += "prb: " + (probability * 100) + "% ran: " + alreadyRan;
		retMe += " defTurn: " + defendersTurn + " startInit: " + startingInitiative + "\n";
		retMe += "----------------\n";
		retMe += "Defensive Team\n" + defensiveTeam + "\n";
		retMe += "Offensive Team\n" + offensiveTeam + "\n";
		retMe += resultingScenarios.size() + "possible scenarios/outcomes.\n";
		retMe += "----------------\nEND SCENARIO\n\n";
		return retMe;
	}
	
	public static void combineScenarios(ArrayList<Scenario> scenarios)
	{
		for (int i = 0; i < scenarios.size() - 1; ++i)
		{
			Scenario a = scenarios.get(i);
			for (int j = i + 1; j < scenarios.size(); ++j)
			{
				Scenario b = scenarios.get(j);
				if (a.equals(b))
				{
					a.probability += b.probability;
					scenarios.remove(j);
					--j;
				}
			}
		}
	}
}
