package eclipsechacecalculator.gui;

import eclipsechacecalculator.game.ShipPart;
import java.awt.Component;
import javax.swing.*;

/**
**	
**	@author Bitza Wolf
**/
public class ShipPartContainer extends JComboBox
{
	public static final ShipPartIcon[] ALL_PARTS;
	public static final ShipPartIcon[] SHOTS;
	public static final ShipPartIcon[] ENERGY_SOURCES;
	public static final ShipPartIcon[] DRIVES;
	
	static
	{
		ALL_PARTS = ShipPartIcon.values();
		
		SHOTS = new ShipPartIcon[3];
		SHOTS[0] = ShipPartIcon.ION_CANNON;
		SHOTS[1] = ShipPartIcon.PLASMA_CANNON;
		SHOTS[2] = ShipPartIcon.ANTIMATTER_CANNON;
		
		ENERGY_SOURCES = new ShipPartIcon[3];
		ENERGY_SOURCES[0] = ShipPartIcon.NUCLEAR_SOURCE;
		ENERGY_SOURCES[1] = ShipPartIcon.FUSION_SOURCE;
		ENERGY_SOURCES[2] = ShipPartIcon.TACHYON_SOURCE;
		
		DRIVES = new ShipPartIcon[3];
		DRIVES[0] = ShipPartIcon.NUCLEAR_DRIVE;
		DRIVES[1] = ShipPartIcon.FUSION_DRIVE;
		DRIVES[2] = ShipPartIcon.TACHYON_DRIVE;
	}
	
	public ShipPartContainer()
	{
		super(ALL_PARTS);
		setRenderer(new CustomRenderer());
	}
	
	public ShipPartContainer(ShipPartIcon[] icons)
	{
		super(icons);
		setRenderer(new CustomRenderer());
	}
	
	public ShipPart getSelectedShipPart()
	{
		ShipPartIcon icon = (ShipPartIcon) super.getSelectedItem();
		return icon.shipPart;
	}
	
	private class CustomRenderer extends JLabel implements ListCellRenderer
	{
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setIcon(((ShipPartIcon) value).getIcon());
			
			if (isSelected)
			{
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			}
			else
			{
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			
			return this;
		}
		
	}
}
