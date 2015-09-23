package eclipsechacecalculator.combatlogic;

import eclipsechacecalculator.game.Ship;
import java.util.ArrayList;

/**
**	
**	@author Bitza Wolf
**/
public class Team
{
	private String name;
	private boolean defending;
	private ArrayList<ShipGroup> shipGroups;
	
	public Team()
	{
		this("Unknown Team", false);
	}
	
	public Team(String name, boolean isDefending)
	{
		
		this.name = name;
		defending = isDefending;
		shipGroups = new ArrayList<>();
	}
	
	public void addShips(Ship template, int count)
	{
		shipGroups.add(new ShipGroup(template, count));
		sortShipsBySize();
	}
	
	private void cleanupDeadShips()
	{
		for (int i = 0; i < shipGroups.size(); ++i)
		{
			if (shipGroups.get(i).isEmpty())
			{
				shipGroups.remove(i);
				--i;
			}
		}
	}
	
	public Team copy()
	{
		Team retMe = new Team(name, defending);
		for (ShipGroup sg : shipGroups)
		{
			retMe.shipGroups.add(sg.copy());
		}
		
		return retMe;
	}
	
	public void disperseHits(ArrayList<Hit> hits)
	{
		for (Hit hit : hits)
		{
			boolean hitUsed = false;
			for (int i = 0; i < shipGroups.size(); ++i)
			{
				ShipGroup group = shipGroups.get(i);
				if (group.doesHit(hit) && group.doesHitCauseKill(hit))
				{
					group.applyHit(hit);
					hitUsed = true;
					break;
				}
			}
			if (! hitUsed)
			{
				for (int i = 0; i < shipGroups.size(); ++i)
				{
					ShipGroup group = shipGroups.get(i);
					if (group.doesHit(hit))
					{
						group.applyHit(hit);
						break;
					}
				}
			}
		}
		cleanupDeadShips();
		/*
		This means that low-strength hits may miss even though it's a 6.
		Prioritizes kills over damage (as per rule book)
		Uses up weakest shots first (yellow, oranges, then reds; low hits to 6s)
		
		A better strat would be to pool all shots together,
		try to kill the biggest target possible using the least number of shots
		and then just damage the biggest ship last.
		*/
	}
	
	public boolean equals(Team other)
	{
		if (shipGroups.size() != other.shipGroups.size())
		{
			return false;
		}
		for (int i = 0; i < shipGroups.size(); ++i)
		{
			if (! shipGroups.get(i).equals(other.shipGroups.get(i)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public int getHighestInitiative()
	{
		int bestInitiative = 0;
		for (ShipGroup sg : shipGroups)
		{
			if (sg.getInitiative() > bestInitiative)
			{
				bestInitiative = sg.getInitiative();
			}
		}
		return bestInitiative;
	}
	
	public ArrayList<ShipGroup> getShips(int initiative)
	{
		ArrayList<ShipGroup> groups = new ArrayList<>();
		for (ShipGroup sg : shipGroups)
		{
			if (sg.getInitiative() == initiative)
			{
				groups.add(sg);
			}
		}
		return groups;
	}
	
	public void healShips()
	{
		for (ShipGroup sg : shipGroups)
		{
			sg.healShips();
		}
	}
	
	public boolean isDefending()
	{
		return defending;
	}
	
	public boolean isDefeated()
	{
		return shipGroups.isEmpty();
	}
	
	public void sortShipsBySize()
	{
		for (int i = 1; i < shipGroups.size(); ++i)
		{
			ShipGroup temp = shipGroups.get(i);
			int j = i;
			while (j > 0 && shipGroups.get(j - 1).sortByMaxParts(temp) > 0)
			{
				shipGroups.set(j, shipGroups.get(j - 1));
				 --j;
			}
			shipGroups.set(j, temp);
		}
	}
	
	@Override
	public String toString()
	{
		String retMe = "----------------\nTEAM: " + name + "\nSHIP GROUPS:";
		for (ShipGroup sg : shipGroups)
		{
			retMe += sg.toString() + "\n";
		}
		if (shipGroups.isEmpty())
		{
			retMe += "\n";
		}
		retMe += "----------------";
		return retMe;
	}
}
