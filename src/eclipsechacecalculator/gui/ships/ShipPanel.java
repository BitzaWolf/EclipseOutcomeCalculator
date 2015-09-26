/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsechacecalculator.gui.ships;

import eclipsechacecalculator.game.*;
import eclipsechacecalculator.gui.ShipPartContainer;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Wolf
 */
public abstract class ShipPanel extends JPanel
{
	protected ArrayList<ShipPartContainer> partContainers;
	protected ShipCountPanel countPanel;
	
	public ShipPanel()
	{
		super(new GridBagLayout());
		super.setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
		countPanel = new ShipCountPanel();
		gbc.gridwidth = 3;
		gbc.gridy = 99;
		super.add(countPanel, gbc);
		
		partContainers = new ArrayList<>();
	}
	
	public abstract Ship getShip();
	
	public int getCount()
	{
		return countPanel.getCount();
	}
	
	public ArrayList<ShipPart> getShipParts()
	{
		ArrayList<ShipPart> parts = new ArrayList<>();
		for (ShipPartContainer spc : partContainers)
		{
			ShipPart part = spc.getSelectedShipPart();
			if (part != null)
			{
				parts.add(part);
			}
		}
		return parts;
	}
}
