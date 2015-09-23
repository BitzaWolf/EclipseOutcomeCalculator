package eclipsechacecalculator.gui;

import javax.swing.*;

/**
**	
**	@author Bitza Wolf
**/
public class ShipPartContainer extends JComboBox
{
	private static final Integer[] INT_ARRAY;
	
	static
	{
		INT_ARRAY = new Integer[4];
		for (int i = 0; i < INT_ARRAY.length; ++i)
		{
			INT_ARRAY[i] = new Integer(i);
		}
	}
	
	public ShipPartContainer()
	{
		super(INT_ARRAY);
		setRenderer(new ShipPartIcon());
	}
}
