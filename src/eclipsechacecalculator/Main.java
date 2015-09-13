package eclipsechacecalculator;

import eclipsechacecalculator.combatlogic.DiceRoll;
import eclipsechacecalculator.game.Shot;
import java.util.ArrayList;

/**
**	
**	@author Bitza Wolf of Convivial Communism
**/
public class Main
{
	/**
	** @param args Yarrrg!
	**/
    public static void main(String[] args)
	{
		//UnitTests.runTests();
		
		Shot[] shots = new Shot[2];
		shots[0] = Shot.YELLOW;
		shots[1] = Shot.YELLOW;
		ArrayList<DiceRoll> rolls = DiceRoll.getRolls(shots, 1);
		float sumChance = 0;
		for (DiceRoll roll : rolls)
		{
			System.out.println(roll);
			sumChance += roll.getProbability();
		}
		System.out.println("Sum chance: " + (sumChance * 100) + "%");
	}
}
