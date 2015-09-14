package eclipsechacecalculator.combatlogic;

import eclipsechacecalculator.game.Shot;

/**
**	
**	@author Bitza Wolf
**/
public class Hit
{
	public final int strength;
	public final Shot shot;
	
	public Hit(Shot shot, int str)
	{
		this.shot = shot;
		strength = str;
	}
	
	public Hit copy()
	{
		Hit retMe = new Hit(shot, strength);
		return retMe;
	}
	
	@Override
	public String toString()
	{
		return "Hit: " + strength + shot;
	}
	
	public boolean equals(Hit other)
	{
		return (strength == other.strength && shot == other.shot);
	}

	public int compare(Hit other)
	{
		if (strength == other.strength)
		{
			return shot.compare(other.shot);
		}
		return (strength - other.strength);
	}
}
