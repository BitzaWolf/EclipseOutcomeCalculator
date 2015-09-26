package eclipsechacecalculator.gui.ships;

import eclipsechacecalculator.game.*;
import eclipsechacecalculator.game.ships.*;
import eclipsechacecalculator.gui.*;
import java.awt.*;
import javax.swing.JLabel;

/**
**	
**	@author Bitza Wolf
**/
public class AncientsPanel extends ShipPanel
{
	public AncientsPanel()
	{
		countPanel.setMaxCount(2);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel label = new JLabel(ShipPartIcon.ION_CANNON.getIcon());
		gbc.gridx = 0;
		gbc.gridy = 0;
		super.add(label, gbc);
		
		label = new JLabel(ShipPartIcon.ION_CANNON.getIcon());
		gbc.gridx = 1;
		super.add(label, gbc);
		
		label = new JLabel(ShipPartIcon.ELECTRON_COMPUTER.getIcon());
		gbc.gridx = 0;
		gbc.gridy = 1;
		super.add(label, gbc);
		
		label = new JLabel(ShipPartIcon.HULL.getIcon());
		gbc.gridx = 1;
		super.add(label, gbc);
	}
	
	@Override
	public Ship getShip()
	{
		return new Ancient();
	}
}
