package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class EnterPlayerScreen extends JPanel implements ActionListener {

	JLabel enterPlayer1;
	JLabel enterPlayer2;
	JTextField player1Name;
	JTextField player2Name;
	JButton submitButton;
	Controller listener;
	
	public Controller getListener() {
		return listener;
	}

	public void setListener(Controller listener) {
		this.listener = listener;
	}

	public EnterPlayerScreen() {
		super();
		
		this.setLayout(null);
		
		enterPlayer1 = new JLabel(" Enter Player 1:");
		enterPlayer1.setBounds(335, 300, 100, 40);
		enterPlayer1.setBackground(Color.white);
		enterPlayer1.setOpaque(true);
		
		enterPlayer2 = new JLabel(" Enter Player 2:");
		enterPlayer2.setBounds(335, 350, 100, 40);
		enterPlayer2.setBackground(Color.white);
		enterPlayer2.setOpaque(true);
		
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(600, 400, 100, 50);
		submitButton.addActionListener(this);
		submitButton.setFocusable(false);
		
		player1Name = new JTextField();
		player1Name.setBounds(450, 300, 250, 40);
		
		player2Name = new JTextField();
		player2Name.setBounds(450, 350, 250, 40);
		
		this.add(submitButton);
		this.add(enterPlayer1);
		this.add(player1Name);
		this.add(enterPlayer2);
		this.add(player2Name);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon image = new ImageIcon("bg.png");
		g.drawImage(image.getImage(),0,0,null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()== submitButton) {
			if(!player1Name.getText().isBlank() && !player2Name.getText().isBlank()) {

				listener.createGame(player1Name.getText(),player2Name.getText());
				listener.nextScreen();
				
			}
		}
		
	}

	
	
}
