package eclipsechacecalculator.game.ships;

import eclipsechacecalculator.game.*;

/**
**	
**	@author Bitza Wolf
**/
public class Starbase extends Ship
{
	public Starbase()
	{
		super(new Attributes(1, 0, 0, 3, 0, 4), 5, "Starbase");
		addShipPart(ShipPart.ION_CANNON);
		addShipPart(ShipPart.HULL);
		addShipPart(ShipPart.HULL);
		addShipPart(ShipPart.ELECTRON_COMPUTER);
	}
}
