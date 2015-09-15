package eclipsechacecalculator.game;

import java.util.ArrayList;

/**
**	
**	@author Bitza Wolf
**/
public class Ship
{
	protected Attributes baseAttributes;
	protected ArrayList<ShipPart> shipParts;
	protected int maxShipParts;
	protected String name;
	private int damage;
	
	public Ship()
	{
		this(new Attributes(), 0, "UFO");
	}
	
	public Ship(Attributes baseAttr, int maxShipParts, String name)
	{
		baseAttributes = baseAttr.copy();
		shipParts = new ArrayList<>();
		this.maxShipParts = maxShipParts;
		this.name = name;
		damage = 0;
	}
	
	public Ship copy()
	{
		Ship retMe = new Ship();
		
		retMe.baseAttributes = baseAttributes.copy();
		retMe.maxShipParts = maxShipParts;
		retMe.name = name;
		for(int i = 0; i < shipParts.size(); ++i)
		{
			retMe.shipParts.add(shipParts.get(i));
		}
		
		return retMe;
	}
	
	public Attributes getCurrentAttributes()
	{
		Attributes retMe = baseAttributes.copy();
		for (int i = 0; i < shipParts.size(); ++i)
		{
			retMe = retMe.add(shipParts.get(i).attributes);
		}
		return retMe;
	}
	
	public ArrayList<Shot> getShots()
	{
		ArrayList<Shot> shots = new ArrayList<>();
		for (int i = 0; i < shipParts.size(); ++i)
		{
			ShipPart part = shipParts.get(i);
			part.addShotsToList(shots);
		}
		return shots;
	}
	
	public int getMaxShipParts()
	{
		return maxShipParts;
	}
	
	public void addShipPart(ShipPart part)
	{
		shipParts.add(part);
	}
	
	public void removeShipPart(ShipPart part)
	{
		shipParts.remove(part);
	}
	
	public void replaceShipPart(ShipPart remove, ShipPart replace)
	{
		shipParts.remove(remove);
		shipParts.add(replace);
	}
	
	public void takeDamage(int amount)
	{
		if (amount < 0)
		{
			throw (new IllegalArgumentException("Damage taken is negative: " + amount));
		}
		
		damage += amount;
	}
	
	public boolean isOverloadedShipParts()
	{
		return (shipParts.size() > maxShipParts);
	}
	
	public boolean isOverConsumingPower()
	{
		Attributes current = getCurrentAttributes();
		return (current.powerConsumption > current.powerSupply);
	}
	
	public boolean isDead()
	{
		return (getCurrentAttributes().hullPoints <= damage);
	}
	
	@Override
	public String toString()
	{
		String retMe = "SHIP : " + name + " STATS: " + getCurrentAttributes();
		if (damage != 0)
		{
			Attributes att = getCurrentAttributes();
			int hpRemaining = att.hullPoints - damage;
			retMe += "(" + hpRemaining + "/" + att.hullPoints + " HP)";
		}
		retMe += " SHOTS: ";
		ArrayList<Shot> shots = getShots();
		if (shots.isEmpty())
		{
			retMe += " <NONE>";
		}
		else
		{
			retMe += shots.get(0);
			for (int i = 1; i < shots.size(); ++i)
			{
				retMe += ", " + shots.get(i);
			}
		}
		
		return retMe;
	}
}
