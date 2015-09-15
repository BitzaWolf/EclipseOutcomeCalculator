package eclipsechacecalculator.combatlogic;

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
		name = "Unknown Team";
		defending = false;
		shipGroups = new ArrayList<>();
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
	
	public void disperseHits(ArrayList<Hit> hits)
	{
		/*
		The AI here is fairly simple:
		sort ships by part-size
		for each hit
			boolean hitUsed = false;
			for each ship starting with biggest ship
				if sg.doesHit(h) && (sg.hitCausesKill(h))
					sg.applyHit(h)
					hitUsed = true;
					break
			if (! hitUsed)
				for each ship starting with biggest ship
					if sg.doesHit(h)
						sg.applyHit(h)
						break;
		}
		This means that low-strength hits may miss even though it's a 6.
		Prioritizes kills over damage (as per rule book)
		Uses up weakest shots first (yellow, oranges, then reds; low hits to 6s)
		
		A better strat would be to use up reds first, then oranges, then yellows.?
		*/
	}
	
	public boolean isDefending()
	{
		return defending;
	}
	
	public boolean isDefeated()
	{
		return shipGroups.isEmpty();
	}
}
