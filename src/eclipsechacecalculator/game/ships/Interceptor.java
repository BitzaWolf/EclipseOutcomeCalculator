package eclipsechacecalculator.game.ships;

import eclipsechacecalculator.game.*;

/**
**	
**	@author Bitza Wolf
**/
public class Interceptor extends Ship
{
	public Interceptor()
	{
		super(new Attributes(1, 0, 0, 0, 0, 2), 4, "Interceptor");
		addShipPart(ShipPart.ION_CANNON);
		addShipPart(ShipPart.NUCLEAR_SOURCE);
		addShipPart(ShipPart.NUCLEAR_DRIVE);
	}
}
