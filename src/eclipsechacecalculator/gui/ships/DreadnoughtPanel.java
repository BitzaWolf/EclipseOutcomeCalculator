package eclipsechacecalculator.gui.ships;

import eclipsechacecalculator.game.*;
import eclipsechacecalculator.game.ships.*;
import eclipsechacecalculator.gui.*;
import java.awt.*;
/**
**	
**	@author Bitza Wolf
**/
public class DreadnoughtPanel extends ShipPanel
{
	public DreadnoughtPanel()
	{
		countPanel.setMaxCount(2);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		ShipPartContainer spc = new ShipPartContainer();
		spc.setSelectedItem(ShipPartIcon.ION_CANNON);
		gbc.gridx = 0;
		gbc.gridy = 0;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer(ShipPartContainer.SHOTS);
		gbc.gridx = 2;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		spc.setSelectedItem(ShipPartIcon.HULL);
		gbc.gridx = 0;
		gbc.gridy = 1;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		spc.setSelectedItem(ShipPartIcon.HULL);
		gbc.gridx = 1;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		spc.setSelectedItem(ShipPartIcon.ELECTRON_COMPUTER);
		gbc.gridx = 2;
		gbc.insets.top = 0;
		super.add(spc, gbc);
		partContainers.add(spc);
		
		spc = new ShipPartContainer();
		gbc.gridx = 0;
		gbc.gridy = 2;
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
		Ship retMe = new Dreadnought();
		retMe.replaceShipPart(ShipPart.ION_CANNON, partContainers.get(0).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.ION_CANNON, partContainers.get(1).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.HULL, partContainers.get(2).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.HULL, partContainers.get(3).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.ELECTRON_COMPUTER, partContainers.get(4).getSelectedShipPart());
		retMe.addShipPart(partContainers.get(5).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.NUCLEAR_SOURCE, partContainers.get(6).getSelectedShipPart());
		retMe.replaceShipPart(ShipPart.NUCLEAR_DRIVE, partContainers.get(7).getSelectedShipPart());
		
		return retMe;
	}
}
