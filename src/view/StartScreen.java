package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class StartScreen extends JPanel implements ActionListener {
	
	JButton startButton;
	Controller listener;
	
	
	public StartScreen() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 1000, 650);
		
		startButton = new JButton("Start Game!");
		startButton.addActionListener(this);
		startButton.setFocusable(false);
		startButton.setBackground(Color.white);
		startButton.setBounds(450,300, 100, 50);
		
		this.add(startButton);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon image = new ImageIcon("bg.png");
		g.drawImage(image.getImage(),0,0,null);
	}
	
	
	public JButton getStartButton() {
		return startButton;
	}

	public Controller getListener() {
		return listener;
	}

	public void setListener(Controller listener) {
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton) {
			listener.nextScreen();
		}
	}
	
}
