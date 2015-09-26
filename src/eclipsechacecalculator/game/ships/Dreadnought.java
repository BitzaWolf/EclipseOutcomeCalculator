package eclipsechacecalculator.game.ships;

import eclipsechacecalculator.game.*;

/**
**	
**	@author Bitza Wolf
**/
public class Dreadnought extends Ship
{
	public Dreadnought()
	{
		super(new Attributes(1, 0, 0, 0, 0, 0), 8, "Dreadnought");
		addShipPart(ShipPart.ION_CANNON);
		addShipPart(ShipPart.ION_CANNON);
		addShipPart(ShipPart.NUCLEAR_SOURCE);
		addShipPart(ShipPart.NUCLEAR_DRIVE);
		addShipPart(ShipPart.ELECTRON_COMPUTER);
		addShipPart(ShipPart.HULL);
		addShipPart(ShipPart.HULL);
	}
}
