/*
 * Represents a series of shots by multiple ships, thus a volley of shots.
 * A volley comes from 1 or more ships of the same type, so the shots all have the 
 */
package eclipsechacecalculator.combatlogic;

import eclipsechacecalculator.game.Shot;

/**
 *
 * @author Wolf
 */
public class Volley
{
    public final Shot[] shots;
    public final int plusHit;
    
    public Volley(int numOfShips, Shot[] shotsPerShip, int plusHit)
    {
        int totalShots = shotsPerShip.length * numOfShips;
        shots = new Shot[totalShots];
		for (int i = 0; i < shots.length; ++i)
		{
			shots[i] = shotsPerShip[i % shotsPerShip.length];
		}
        this.plusHit = plusHit;
    }
}
