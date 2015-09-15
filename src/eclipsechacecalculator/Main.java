package eclipsechacecalculator;

import eclipsechacecalculator.combatlogic.*;
import eclipsechacecalculator.game.*;
import eclipsechacecalculator.game.ships.Interceptor;
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
		
		Ship ship = new Interceptor();
		ship.addShipPart(ShipPart.GAUSS_SHIELD);
		ShipGroup sg = new ShipGroup(ship, 2);
		int init = sg.getInitiative();
		
		Hit h = new Hit(Shot.YELLOW, 6);
		if (sg.doesHit(h))
		{
			sg.applyHit(h);
		}
		
		testRolls();
	}
	
	public static void testRolls()
	{
		Ship ship = new Interceptor();
		ship.addShipPart(ShipPart.ELECTRON_COMPUTER);
		ShipGroup sg = new ShipGroup(ship, 2);
		
		ArrayList<Volley> vollies = new ArrayList<>();
		vollies.add(sg.getVolley());
		
		ArrayList<DiceRoll> rolls = DiceRoll.getRolls(vollies);
		float sumChance = 0;
		for (DiceRoll roll : rolls)
		{
			System.out.println(roll);
			sumChance += roll.getProbability();
		}
		System.out.println("Sum chance: " + (sumChance * 100) + "%");
	}
}
