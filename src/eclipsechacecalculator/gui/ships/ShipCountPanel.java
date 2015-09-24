/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsechacecalculator.gui.ships;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Wolf
 */
public class ShipCountPanel extends JPanel implements ActionListener
{
	private static ImageIcon plusIcon;
	private static ImageIcon minusIcon;
	
	static
	{
		try
		{
			plusIcon = new ImageIcon(ImageIO.read(new File("res/PlusIcon.png")));
			minusIcon = new ImageIcon(ImageIO.read(new File("res/MinusIcon.png")));
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	private final JButton plusButt, minusButt;
	private final JLabel shipCountLabel;
	private int maximumCount;
	private int count;
	
	public ShipCountPanel()
	{
		minusButt = new JButton(minusIcon);
		minusButt.addActionListener(this);
		super.add(minusButt);
		
		shipCountLabel = new JLabel("0");
		shipCountLabel.setPreferredSize(new Dimension(50, 20));
		shipCountLabel.setHorizontalAlignment(JLabel.CENTER);
		super.add(shipCountLabel);
		
		plusButt = new JButton(plusIcon);
		plusButt.addActionListener(this);
		super.add(plusButt);
		
		maximumCount = 1;
		count = 0;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setMaxCount(int count)
	{
		maximumCount = count;
		validateButtons();
	}
	
	private void validateButtons()
	{
		plusButt.setEnabled(count < maximumCount);
		minusButt.setEnabled(count > 0);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == plusButt)
		{
			++count;
		}
		else if (e.getSource() == minusButt)
		{
			--count;
			
		}
		validateButtons();
		shipCountLabel.setText("" + count);
	}
}
