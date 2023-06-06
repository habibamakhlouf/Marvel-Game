package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{

	public ImagePanel() {
		super();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon image = new ImageIcon("bg.png");
		g.drawImage(image.getImage(),0,0,null);
	}
	
}
