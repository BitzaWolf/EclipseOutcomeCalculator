/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsechacecalculator.combatlogic;

import eclipsechacecalculator.game.*;
import java.util.ArrayList;

/**
 *
 * @author Wolf
 */
public class ShipGroup
{
    private ArrayList<Ship> ships;
    
    public ShipGroup(Ship templateShip, int numOfShips)
    {
	for (int i = 0; i < numOfShips; ++i)
	{
	    ships.add(templateShip.copy());
	}
    }
    
    public Volley getVolley()
    {
	ArrayList<Shot> s = ships.get(0).getShots();
	Shot[] shots = new Shot[ships.size()];
	s.toArray(shots);
	return new Volley(ships.size(), shots, ships.get(0).getCurrentAttributes().plusHit);
    }
}
