package eclipsechacecalculator;

import eclipsechacecalculator.combatlogic.*;
import eclipsechacecalculator.game.*;
import eclipsechacecalculator.game.ships.*;
import java.util.ArrayList;

/**
**	
**	@author Bitza Wolf
**/
public class UnitTests
{
	public static void runTests()
	{
		testAttributes();
		testShots();
		testShip();
		testShipGroups();
		testRolls();
		testTeams();
		testScenarios();
	}
	
	public static void testScenarios()
	{
		Ship playerShip = new Interceptor();
		playerShip.addShipPart(ShipPart.PLASMA_CANNON);
		Ship ancient = new Ancient();
		
		Team playerTeam = new Team("Player");
		playerTeam.addShips(playerShip, 2);
		
		Team neutralTeam = new Team("Neutrals");
		neutralTeam.addShips(ancient, 1);
		
		int playersBestInit = playerTeam.getHighestInitiative();
		int ancientsBestInit = neutralTeam.getHighestInitiative();
		boolean defendersTurn = (ancientsBestInit >= playersBestInit);
		int initiative = defendersTurn ? ancientsBestInit : playersBestInit;
		
		Scenario baseScenario = new Scenario(1.0f, neutralTeam, playerTeam, initiative,
				defendersTurn);
		
		baseScenario.run();
		System.out.println(baseScenario);
		for (Scenario s : baseScenario.getNextScenarios())
		{
			System.out.println(s);
		}
	}
	
	public static void testTeams()
	{
		Ship playerShip = new Interceptor();
		Ship ancient = new Ancient();
		
		Team player = new Team("Player");
		player.addShips(playerShip, 3);
		
		Team ancients = new Team("Ancients");
		ancients.addShips(ancient, 1);
		
		int playersBestInit = player.getHighestInitiative();
		int ancientsBestInit = ancients.getHighestInitiative();
		boolean checkingDefenders = (ancientsBestInit >= playersBestInit);
		int initiative = checkingDefenders ? ancientsBestInit : playersBestInit;
		
		ArrayList<ShipGroup> ships;
		Team firingTeam = checkingDefenders ? ancients : player;
		Team receivingTeam = checkingDefenders ? player : ancients;

		ships = firingTeam.getShips(initiative);
		while (receivingTeam.getShips(initiative).isEmpty())
		{
			--initiative;
			if (initiative < 0)
			{
				initiative = 8;
			}
			ships.addAll(firingTeam.getShips(initiative));
		}

		ArrayList<Volley> vollies = new ArrayList<>();
		for (ShipGroup sg : ships)
		{
			vollies.add(sg.getVolley());
		}
		ArrayList<DiceRoll> rolls = DiceRoll.getRolls(vollies);

		for (DiceRoll roll : rolls)
		{
			Team resultingTeam = receivingTeam.copy();
			System.out.println("Running roll : " + roll);
			resultingTeam.disperseHits(roll.getHits());
			System.out.println(resultingTeam + "\n");
		}
	}
	
	public static void testAttributes()
	{
		System.out.println("TESTING ATTRIBUTES...");
		Attributes a = new Attributes();
		Attributes b = new Attributes(1, 2, 3, 4, 5, 6);
		Attributes c = a.add(b);
		if (c.hullPoints != 1 && c.plusHit != 2 && c.minusHit != 3 &&
			c.powerConsumption != 4 && c.powerSupply != 5 && c.initiative != 6)
		{
			throw new AssertionError("Attributes adding does not work. Expected: " + b + " got: " + c);
		}
		
		Attributes d = c.subtract(new Attributes(6, 5, 4, 3, 2, 1));
		Attributes expected = new Attributes(-5, -3, -1, 1, 3, 5);
		if (d.hullPoints != -5 && d.plusHit != -3 && d.minusHit != -1 &&
			d.powerConsumption != 1 && d.powerSupply != 3 && d.initiative != 5)
		{
			throw new AssertionError("Attributes adding does not work. Expected: " + expected + " got: " + d);
		}
		System.out.println("Test successful.\n");
	}
	
	public static void testShots()
	{
		System.out.println("TESTING SHOTS...");
		Shot y = Shot.YELLOW, o = Shot.ORANGE, r = Shot.RED;
		if (y.damagePerHit != 1 && o.damagePerHit != 2 && o.damagePerHit != 4)
		{
			throw new AssertionError("Shots failed. Not returning the correct values. " + y.damagePerHit + " " + o.damagePerHit + " " + r.damagePerHit);
		}
		System.out.println("Test successful.\n");
	}
	
	public static void testShip()
	{
		System.out.println("TESTING SHIPS...");
		ArrayList<Ship> ships = new ArrayList<>();
		ships.add(new Interceptor());
		ships.add(new Interceptor());
		ships.add(new Interceptor());
		ships.get(1).addShipPart(ShipPart.HULL);
		ships.get(2).addShipPart(ShipPart.ION_CANNON);
		
		ships.get(1).removeShipPart(ShipPart.HULL);
		ships.get(2).replaceShipPart(ShipPart.ION_CANNON, ShipPart.ELECTRON_COMPUTER);
		System.out.println("Add, Remove, Replace parts... 0 & 1 unchanged, 2 Has Electron computer");
		boolean passed = true;
		for (int i = 0; i < ships.size(); ++i)
		{
			System.out.println(ships.get(i));
		}
		System.out.println("\n");
		ships.get(1).addShipPart(ShipPart.HULL);
		ships.get(0).takeDamage(1);
		ships.get(1).takeDamage(1);
		System.out.println("Ships 0 and 1 take 1 damage. 1 has a Hull.");
		for (int i = 0; i < ships.size(); ++i)
		{
			System.out.print( i + " isDead: " + ships.get(i).isDead());
			System.out.print(" overlaodedParts: " + ships.get(i).isOverloadedShipParts());
			System.out.println(" overConsumingPower: " + ships.get(i).isOverConsumingPower());
		}
		System.out.println("\n");
		ships.get(1).addShipPart(ShipPart.HULL);
		ships.get(2).replaceShipPart(ShipPart.ELECTRON_COMPUTER, ShipPart.ION_CANNON);
		ships.get(2).addShipPart(ShipPart.NUCLEAR_DRIVE);
		System.out.println("Ship 1 has 2 hulls and 5 parts. Ship 2 has 2 cannons and another drive.");
		for (int i = 0; i < ships.size(); ++i)
		{
			System.out.print( i + " overlaodedParts: " + ships.get(i).isOverloadedShipParts());
			System.out.println(" overConsumingPower: " + ships.get(i).isOverConsumingPower());
		}
		System.out.println("\n");
		ships.add(ships.get(0).copy());
		System.out.println("Copy 0 ship into a new ship, slot 4.");
		for (int i = 0; i < ships.size(); ++i)
		{
			System.out.println(ships.get(i));
		}
		System.out.println("\n");
		ships.get(3).addShipPart(ShipPart.NUCLEAR_SOURCE);
		System.out.println("Copied ship (4) adds nuclear source");
		for (int i = 0; i < ships.size(); ++i)
		{
			System.out.println(ships.get(i));
		}
		System.out.println("Test maybe successful. Read output and check.\n");
	}
	
	public static void testShipGroups()
	{
		Ship ship = new Interceptor();
		ship.addShipPart(ShipPart.GAUSS_SHIELD);
		ShipGroup sg = new ShipGroup(ship, 2);
		int init = sg.getInitiative();
		
		Hit h = new Hit(Shot.YELLOW, 6);
		if (sg.doesHit(h))
		{
			sg.applyHit(h);
		}
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
