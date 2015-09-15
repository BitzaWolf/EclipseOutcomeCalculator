package eclipsechacecalculator.game;

/**
**	
**	@author Bitza Wolf
**/
public class Attributes
{
	public int hullPoints, plusHit, minusHit, powerSupply, powerConsumption, initiative;
	
	public Attributes()
	{
		this(0, 0, 0, 0, 0, 0);
	}
	
	public Attributes(int hp, int pHit, int mHit, int pSupply, int pConsume, int init)
	{
		hullPoints = hp;
		plusHit = pHit;
		minusHit = mHit;
		powerSupply = pSupply;
		powerConsumption = pConsume;
		initiative = init;
	}
	
	public Attributes add(Attributes other)
	{
		Attributes retMe = copy();
		
		retMe.hullPoints += other.hullPoints;
		retMe.plusHit += other.plusHit;
		retMe.minusHit += other.minusHit;
		retMe.powerSupply += other.powerSupply;
		retMe.powerConsumption += other.powerConsumption;
		retMe.initiative += other.initiative;
		
		return retMe;
	}
	
	public Attributes copy()
	{
		Attributes retMe = new Attributes();
		retMe.hullPoints = hullPoints;
		retMe.plusHit = plusHit;
		retMe.minusHit = minusHit;
		retMe.powerSupply = powerSupply;
		retMe.powerConsumption = powerConsumption;
		retMe.initiative = initiative;
		
		return retMe;
	}
	
	public boolean equals(Attributes other)
	{
		return (hullPoints == other.hullPoints &&
				initiative == other.initiative &&
				minusHit == other.minusHit &&
				plusHit == other.plusHit &&
				powerConsumption == other.powerConsumption &&
				powerSupply == other.powerSupply);
	}
	
	public Attributes subtract(Attributes other)
	{
		Attributes retMe = copy();
		
		retMe.hullPoints -= other.hullPoints;
		retMe.plusHit -= other.plusHit;
		retMe.minusHit -= other.minusHit;
		retMe.powerSupply -= other.powerSupply;
		retMe.powerConsumption -= other.powerConsumption;
		retMe.initiative -= other.initiative;
		
		return retMe;
	}
	
	@Override
	public String toString()
	{
		String retMe = "HP:" + hullPoints + " +H:" + plusHit + " -H:" + minusHit;
		retMe += " PS:" + powerSupply + " PC:" + powerConsumption + " I:" + initiative;
		return retMe;
	}
}
