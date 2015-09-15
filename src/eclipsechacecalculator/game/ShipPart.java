package eclipsechacecalculator.game;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
**	
**	@author Bitza Wolf of Convivial Communism
**/
public enum ShipPart
{
	ELECTRON_COMPUTER(null, new Attributes(0, 1, 0, 0, 0, 0)),
	HULL(null, new Attributes(1, 0, 0, 0, 0, 0)),
	GAUSS_SHIELD(null, new Attributes(0, 0, 1, 0, 0, 0)),
	ION_CANNON(null, new Attributes(0, 0, 0, 0, 1, 0), Shot.YELLOW),
	NUCLEAR_DRIVE(null, new Attributes(0, 0, 0, 0, 1, 1)),
	NUCLEAR_SOURCE(null, new Attributes(0, 0, 0, 3, 0, 0));
	
	public final Attributes attributes;
	public final ImageIcon icon;
	public final Shot[] shots;
	
	private ShipPart(ImageIcon icn, Attributes attr, Shot... shots)
	{
		attributes = attr;
		icon = icn;
		this.shots = shots;
	}
	
	public void addShotsToList(ArrayList<Shot> shotList)
	{
		for (int i = 0; i < shots.length; ++i)
		{
			shotList.add(shots[i]);
		}
	}
}
