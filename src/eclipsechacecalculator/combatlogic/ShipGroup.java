/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsechacecalculator.combatlogic;

import eclipsechacecalculator.game.*;
import java.util.ArrayList;

/**
 *
 * @author Wolf
 */
public class ShipGroup
{
    private ArrayList<Ship> ships;
    
	private ShipGroup()
	{
		ships = new ArrayList<>();
	}
	
    public ShipGroup(Ship templateShip, int numOfShips)
    {
		ships = new ArrayList<>();
		
		for (int i = 0; i < numOfShips; ++i)
		{
			ships.add(templateShip.copy());
		}
    }
	
	public void addShip(int amount)
	{
		Ship temp = ships.get(0);
		for (int i = 0; i < amount; ++i)
		{
			ships.add(temp.copy());
		}
	}
	
	public void applyHit(Hit h)
	{
		if (isEmpty())
		{
			return;
		}
		ships.get(0).takeDamage(h.shot.damagePerHit);
		if (ships.get(0).isDead())
		{
			ships.remove(0);
		}
	}
	
	public ShipGroup copy()
	{
		ShipGroup retMe = new ShipGroup();
		for (int i = 0; i < ships.size(); ++i)
		{
			retMe.ships.add(i, ships.get(i).copy());
		}
		return retMe;
	}
	
	public boolean doesHit(Hit h)
	{
		if (isEmpty())
		{
			return false;
		}
		int hitStrength = h.strength - ships.get(0).getCurrentAttributes().minusHit;
		return (hitStrength >= 6);
	}
	
	public boolean doesHitCauseKill(Hit h)
	{
		if (isEmpty())
		{
			return false;
		}
		return (ships.get(0).getHealthRemaining() <= h.shot.damagePerHit);
	}
	
	public boolean equals(ShipGroup other)
	{
		if (ships.size() != other.ships.size())
		{
			return false;
		}
		if (! ships.get(0).equals(other.ships.get(0)))
		{
			return false;
		}
		return true;
	}
	
	public int getInitiative()
	{
		if (isEmpty())
		{
			return 0;
		}
		return ships.get(0).getCurrentAttributes().initiative;
	}
    
    public Volley getVolley()
    {
		if (isEmpty())
		{
			return null;
		}
		ArrayList<Shot> s = ships.get(0).getShots();
		Shot[] shots = new Shot[s.size()];
		s.toArray(shots);
		return new Volley(ships.size(), shots, ships.get(0).getCurrentAttributes().plusHit);
    }
	
	public boolean isEmpty()
	{
		return ships.isEmpty();
	}
	
	public void removeShip(int amount)
	{
		for (int i = 0; i < amount; ++i)
		{
			ships.remove(ships.size() - 1);
		}
	}
	
	@Override
	public String toString()
	{
		if (ships.isEmpty())
		{
			return "SHIP GROUP: <empty>";
		}
		String retMe = "SHIP GROUP: " + ships.get(0) + " x" + ships.size() + " ships.";
		return retMe;
	}
	
	public int sortByInitiative(ShipGroup other)
	{
		return (getInitiative() - other.getInitiative());
	}
	
	public int sortByMaxParts(ShipGroup other)
	{
		if (isEmpty())
		{
			return 0;
		}
		int thisMaxParts = ships.get(0).getMaxShipParts();
		int otherMaxParts = other.ships.get(0).getMaxShipParts();
		return (thisMaxParts - otherMaxParts);
	}
}
