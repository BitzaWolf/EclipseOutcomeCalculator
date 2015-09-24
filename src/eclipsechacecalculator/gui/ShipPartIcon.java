/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsechacecalculator.gui;

import eclipsechacecalculator.game.ShipPart;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Wolf
 */
public enum ShipPartIcon
{
	BLANK("Blank.png", null),
	ELECTRON_COMPUTER("ElectronComputer.png", ShipPart.ELECTRON_COMPUTER),
	POSITRON_COMPUTER("PositronComputer.png", ShipPart.POSITRON_COMPUTER),
	GLUON_COMPUTER("GluonComputer.png", ShipPart.GLUON_COMPUTER),
	HULL("Hull.png", ShipPart.HULL),
	IMPROVED_HULL("ImprovedHull.png", ShipPart.IMPROVED_HULL),
	GAUSS_SHIELD("GaussShield.png", ShipPart.GAUSS_SHIELD),
	PHASE_SHIELD("PhaseShield.png", ShipPart.PHASE_SHIELD),
	ION_CANNON("IonCannon.png", ShipPart.ION_CANNON),
	PLASMA_CANNON("PlasmaCannon.png", ShipPart.PLASMA_CANNON),
	ANTIMATTER_CANNON("AntimatterCannon.png", ShipPart.ANTIMATTER_CANNON),
	PLASMA_MISSILE("PlasmaMissile.png", null),
	NUCLEAR_DRIVE("NuclearDrive.png", ShipPart.NUCLEAR_DRIVE),
	FUSION_DRIVE("FusionDrive.png", ShipPart.FUSION_DRIVE),
	TACHYON_DRIVE("TachyonDrive.png", ShipPart.TACHYON_DRIVE),
	NUCLEAR_SOURCE("NuclearSource.png", ShipPart.NUCLEAR_SOURCE),
	FUSION_SOURCE("FusionSource.png", ShipPart.FUSION_SOURCE),
	TACHYON_SOURCE("TachyonSource.png", ShipPart.TACHYON_SOURCE);
	
	private ImageIcon icon;
	public final ShipPart shipPart;
	
	private ShipPartIcon(String fileName, ShipPart part)
	{
		shipPart = part;
		
		String imagePath = "res/upgrades/" + fileName;
		BufferedImage buff;
		try
		{
			buff = ImageIO.read(new File(imagePath));
			BufferedImage smaller = new BufferedImage(58, 58, BufferedImage.TYPE_INT_ARGB);
			AffineTransform at = new AffineTransform();
			at.scale(0.2, 0.2);
			AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			smaller = scaleOp.filter(buff, smaller);
			icon = new ImageIcon(smaller);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public ImageIcon getIcon()
	{
		return icon;
	}
}
