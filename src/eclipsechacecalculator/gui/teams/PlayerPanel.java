package eclipsechacecalculator.gui.teams;

import eclipsechacecalculator.combatlogic.Team;
import eclipsechacecalculator.game.Ship;
import eclipsechacecalculator.gui.ships.*;
import java.awt.Color;

/**
**	
**	@author Bitza Wolf
**/
public class PlayerPanel extends TeamPanel
{
	private final InterceptorPanel interceptors;
	private final CruiserPanel cruisers;
	private final DreadnoughtPanel dreadnoughts;
	private final StarbasePanel starbases;
	
	public PlayerPanel()
	{
		interceptors = new InterceptorPanel();
		cruisers = new CruiserPanel();
		dreadnoughts = new DreadnoughtPanel();
		starbases = new StarbasePanel();
		
		super.addShipPanel(interceptors);
		super.addShipPanel(cruisers);
		super.addShipPanel(dreadnoughts);
		super.addShipPanel(starbases);
		
		super.setBackground(new Color(193, 218, 238));
	}
	
	@Override
	public Team getTeam()
	{
		Team retMe = new Team("Player");
		
		Ship s = interceptors.getShip();
		int count = interceptors.getCount();
		retMe.addShips(s, count);
		
		s = cruisers.getShip();
		count = cruisers.getCount();
		retMe.addShips(s, count);
		
		s = dreadnoughts.getShip();
		count = dreadnoughts.getCount();
		retMe.addShips(s, count);
		
		s = starbases.getShip();
		count = starbases.getCount();
		retMe.addShips(s, count);
		
		return retMe;
	}
}
