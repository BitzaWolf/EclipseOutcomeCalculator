package eclipsechacecalculator.gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
**	
**	@author Bitza Wolf
**/
public class ShipPartIcon extends JLabel implements ListCellRenderer
{
	private static ImageIcon[] IMAGES;
	private static boolean initialized = false;
	
	private void initialize()
	{
		initialized = true;
		String[] iconNames = {
			"AntimatterCannon.png",
			"Blank.png",
			"ElectronComputer.png",
			"FusionDrive.png",
			"FusionSource.png",
			"GaussShield.png",
			"GluonComputer.png",
			"Hull.png",
			"ImprovedHull.png",
			"IonCannon.png",
			"NuclearDrive.png",
			"NuclearSource.png",
			"PhaseShield.png",
			"PlasmaCannon.png",
			"PlasmaMissile.png",
			"PositronComputer.png",
			"TachyonDrive.png",
			"TachyonSource.png"
		};
		
		IMAGES = new ImageIcon[iconNames.length];
		for (int i = 0; i < IMAGES.length; ++i)
		{
			String imagePath = "res/upgrades/" + iconNames[i];
			BufferedImage buff;
			try
			{
				buff = ImageIO.read(new File(imagePath));
				BufferedImage smaller = new BufferedImage(72, 72, BufferedImage.TYPE_INT_ARGB);
				AffineTransform at = new AffineTransform();
				at.scale(0.25, 0.25);
				AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
				smaller = scaleOp.filter(buff, smaller);
				IMAGES[i] = new ImageIcon(smaller);
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
	
	public ShipPartIcon()
	{
		if (! initialized)
		{
			initialize();
		}
		setPreferredSize(new Dimension(72, 72));
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		setIcon(IMAGES[0]);
		return this;
	}
}
