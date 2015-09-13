package eclipsechacecalculator.combatlogic;

import eclipsechacecalculator.game.Shot;
import java.util.ArrayList;

/**
**	
**	@author Bitza Wolf
**/
public class DiceRoll
{
	private final ArrayList<Hit> hits;
	private float probability;
	
	private DiceRoll()
	{
		hits = new ArrayList<>();
		probability = 0;
	}
	
	public float getProbability()
	{
		return probability;
	}
	
	public DiceRoll copy()
	{
		DiceRoll retMe = new DiceRoll();
		for (int i = 0; i < hits.size(); ++i)
		{
			retMe.hits.add(hits.get(i).copy());
		}
		retMe.probability = probability;
		return retMe;
	}
	
	public boolean equals(DiceRoll other)
	{
		if (hits.size() != other.hits.size() || probability != other.probability)
		{
			return false;
		}
		
		for (int i = 0; i < hits.size(); ++i)
		{
			if (! hits.get(i).equals(other.hits.get(i)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public void combine(DiceRoll other)
	{
		if (! this.equals(other))
		{
			return;
		}
		this.probability += other.probability;
	}
	
	public void sortHits()
	{
		for (int i = 1; i < hits.size(); ++i)
		{
			Hit temp = hits.get(i);
			int j = i;
			while (j > 0 && hits.get(j - 1).compare(temp) > 0)
			{
				hits.set(j, hits.get(j - 1));
				 --j;
			}
			hits.set(j, temp);
		}
	}
	
	public static ArrayList<DiceRoll> getRolls(Shot[] shots, int plusHit)
	{
		ArrayList<DiceRoll> rolls = new ArrayList<>();
		recurseRolls(shots, plusHit, 0, 1.0f, new DiceRoll(), rolls);
		
		for (int i = 1; i < rolls.size(); ++i)
		{
			DiceRoll roll = rolls.get(i);
			for (int k = i + 1; k < rolls.size(); ++k)
			{
				DiceRoll otherRoll = rolls.get(k);
				if (roll.equals(otherRoll))
				{
					roll.combine(otherRoll);
					rolls.remove(k);
					--k;
				}
			}
		}
		
		return rolls;
	}
	
	private static void recurseRolls(Shot[] shots, int plusHit, int index, float probability, DiceRoll roll, ArrayList<DiceRoll> rolls)
	{
		if (index >= shots.length)
		{
			DiceRoll storeMe = roll.copy();
			
			storeMe.probability = probability;
			storeMe.sortHits();
			rolls.add(storeMe);
			return;
		}
		
		int lowestHit = 6 - plusHit;
		if (lowestHit < 2)
		{
			lowestHit = 2;
		}
		float missChance = (lowestHit - 1) / 6.0f;
		roll.hits.add(new Hit(Shot.MISS, 0));
		recurseRolls(shots, plusHit, index + 1, missChance * probability, roll, rolls);
		roll.hits.remove(roll.hits.size() - 1);
		
		Shot shot = shots[index];
		for (int i = lowestHit; i <= 6; ++i)
		{
			int strength = i + plusHit;
			if (i == 6)
			{
				strength = 99;
			}
			roll.hits.add(new Hit(shot, strength));
			recurseRolls(shots, plusHit, index + 1, probability * (1 / 6.0f), roll, rolls);
			roll.hits.remove(roll.hits.size() - 1);
		}
	}
	
	@Override
	public String toString()
	{
		String retMe = "Roll. " + (probability * 100) + "% : ";
		for (Hit h : hits)
		{
			retMe += h + " ";
		}
		return retMe;
	}
}
