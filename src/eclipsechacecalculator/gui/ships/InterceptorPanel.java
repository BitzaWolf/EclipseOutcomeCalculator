/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsechacecalculator.gui.ships;

import eclipsechacecalculator.game.Ship;
import eclipsechacecalculator.game.ShipPart;
import eclipsechacecalculator.game.ships.Interceptor;
import eclipsechacecalculator.gui.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Wolf
 */
public class InterceptorPanel extends ShipPanel
{
	public InterceptorPanel()
	{
		countPanel.setMaxCount(8);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		ShipPartContainer spc = new ShipPartContainer(ShipPartContainer.SHOTS);
		gbc.gridx = 1;
		gbc.gridy = 0;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		gbc.gridx = 0;
		gbc.gridy = 1;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer(ShipPartContainer.ENERGY_SOURCES);
		gbc.gridx = 1;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer(ShipPartContainer.DRIVES);
		gbc.gridx = 2;
		super.add(spc, gbc);
		partContainers.add(spc);
	}
	
	@Override
	public Ship getShip()
	{
		Ship retMe = new Interceptor();
		retMe.replaceShipPart(ShipPart.ION_CANNON, partContainers.get(0).getSelectedShipPart());
		retMe.addShipPart(partContainers.get(1).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.NUCLEAR_SOURCE, partContainers.get(2).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.NUCLEAR_DRIVE, partContainers.get(3).getSelectedShipPart());
		
		return retMe;
	}
}
