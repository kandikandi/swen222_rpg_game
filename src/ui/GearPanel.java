package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GearPanel extends JPanel {

	private JLabel armourSlot = new JLabel();
	private JLabel weaponSlot = new JLabel();

	public GearPanel() {
		this.setPreferredSize(new Dimension(200,100));
		this.setLayout(new BorderLayout());;
		this.setLayout(new GridLayout(1,2));

		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		armourSlot.setBorder(border);
		weaponSlot.setBorder(border);

		this.add(armourSlot);
		this.add(weaponSlot);

	}


	public void updateArmour(){
		// Takes the players currently
		// equipped armour and displays
		// the equivalent JLabel (armourSlot)
	}

	public void updateWeapon(){
		// Takes the players currently
		// equipped weapon and displays
		// the equivalent JLabel (weapon Slot)
	}


}