package eclipsechacecalculator.game;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
**	
**	@author Bitza Wolf of Convivial Communism
**/
public enum ShipPart
{
	ELECTRON_COMPUTER(new Attributes(0, 1, 0, 0, 0, 0)),
	POSITRON_COMPUTER(new Attributes(0, 2, 0, 0, 1, 1)),
	GLUON_COMPUTER(new Attributes(0, 3, 0, 0, 2, 2)),
	HULL(new Attributes(1, 0, 0, 0, 0, 0)),
	IMPROVED_HULL(new Attributes(2, 0, 0, 0, 0, 0)),
	GAUSS_SHIELD(new Attributes(0, 0, 1, 0, 0, 0)),
	PHASE_SHIELD(new Attributes(0, 0, 2, 0, 1, 0)),
	ION_CANNON(new Attributes(0, 0, 0, 0, 1, 0), Shot.YELLOW),
	PLASMA_CANNON(new Attributes(0, 0, 0, 0, 2, 0), Shot.ORANGE),
	ANTIMATTER_CANNON(new Attributes(0, 0, 0, 0, 4, 0), Shot.RED),
	//PLASMA_MISSILE(new Attributes(0, 0, 0, 0, 4, 0), Shot.RED), missiles are not programmed in.
	NUCLEAR_DRIVE(new Attributes(0, 0, 0, 0, 1, 1)),
	FUSION_DRIVE(new Attributes(0, 0, 0, 0, 2, 2)),
	TACHYON_DRIVE(new Attributes(0, 0, 0, 0, 3, 3)),
	NUCLEAR_SOURCE(new Attributes(0, 0, 0, 3, 0, 0)),
	FUSION_SOURCE(new Attributes(0, 0, 0, 6, 0, 0)),
	TACHYON_SOURCE(new Attributes(0, 0, 0, 9, 0, 0));
	
	public final Attributes attributes;
	public final Shot[] shots;
	
	private ShipPart(Attributes attr, Shot... shots)
	{
		attributes = attr;
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
