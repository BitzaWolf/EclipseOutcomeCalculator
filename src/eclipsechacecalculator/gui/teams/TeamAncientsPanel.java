package eclipsechacecalculator.gui.teams;

import eclipsechacecalculator.combatlogic.Team;
import eclipsechacecalculator.game.Ship;
import eclipsechacecalculator.gui.ships.AncientsPanel;
import java.awt.Color;

/**
**	
**	@author Bitza Wolf
**/
public class TeamAncientsPanel extends TeamPanel
{
	private final AncientsPanel ancients;
	
	public TeamAncientsPanel()
	{
		ancients = new AncientsPanel();
		super.addShipPanel(ancients);
		
		super.setBackground(new Color(238, 175, 175));
	}
	
	@Override
	public Team getTeam()
	{
		Team retMe = new Team("Ancients");
		
		Ship s = ancients.getShip();
		int count = ancients.getCount();
		retMe.addShips(s, count);
		
		return retMe;
	}
}
