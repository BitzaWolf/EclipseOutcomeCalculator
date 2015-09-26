package eclipsechacecalculator.gui.ships;

import eclipsechacecalculator.game.*;
import eclipsechacecalculator.game.ships.*;
import eclipsechacecalculator.gui.*;
import java.awt.*;

/**
**	
**	@author Bitza Wolf
**/
public class CruiserPanel extends ShipPanel
{
	public CruiserPanel()
	{
		countPanel.setMaxCount(4);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		ShipPartContainer spc = new ShipPartContainer(ShipPartContainer.SHOTS);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		spc.setSelectedItem(ShipPartIcon.HULL);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.top = 40;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		spc.setSelectedItem(ShipPartIcon.ELECTRON_COMPUTER);
		gbc.gridx = 2;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer(ShipPartContainer.ENERGY_SOURCES);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets.top = 0;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		gbc.gridx = 0;
		gbc.gridy = 3;
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
		Ship retMe = new Cruiser();
		retMe.replaceShipPart(ShipPart.ION_CANNON, partContainers.get(0).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.HULL, partContainers.get(1).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.ELECTRON_COMPUTER, partContainers.get(2).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.NUCLEAR_SOURCE, partContainers.get(3).getSelectedShipPart());
		retMe.addShipPart(partContainers.get(4).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.NUCLEAR_DRIVE, partContainers.get(5).getSelectedShipPart());
		
		return retMe;
	}
}
