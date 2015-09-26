package eclipsechacecalculator.game.ships;

import eclipsechacecalculator.game.*;

/**
**	
**	@author Bitza Wolf
**/
public class Cruiser extends Ship
{
	public Cruiser()
	{
		super(new Attributes(1, 0, 0, 0, 0, 1), 6, "Cruiser");
		addShipPart(ShipPart.ION_CANNON);
		addShipPart(ShipPart.NUCLEAR_SOURCE);
		addShipPart(ShipPart.NUCLEAR_DRIVE);
		addShipPart(ShipPart.ELECTRON_COMPUTER);
		addShipPart(ShipPart.HULL);
	}
}
