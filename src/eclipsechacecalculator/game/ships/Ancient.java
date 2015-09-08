package eclipsechacecalculator.game.ships;

import eclipsechacecalculator.game.*;

/**
**	
**	@author Bitza Wolf
**/
public class Ancient extends Ship
{
	public Ancient()
	{
		super(new Attributes(2, 1, 0, 0, 2, 2), 2, "Ancient");
		super.shipParts.add(ShipPart.ION_CANNON);
		super.shipParts.add(ShipPart.ION_CANNON);
	}
}
