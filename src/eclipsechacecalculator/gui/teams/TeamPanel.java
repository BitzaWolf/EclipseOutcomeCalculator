/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsechacecalculator.gui.teams;

import eclipsechacecalculator.combatlogic.Team;
import eclipsechacecalculator.gui.ships.ShipPanel;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Wolf
 */
public abstract class TeamPanel extends JPanel
{
	private final GridBagConstraints gbc;
	
	public TeamPanel()
	{
		super(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.insets = new Insets(5, 40, 5, 40);
	}
	
	public void addShipPanel(ShipPanel panel)
	{
		gbc.gridx += 1;
		super.add(panel, gbc);
	}
	
	public abstract Team getTeam();
}
