package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class ChooseLeaderScreen extends JPanel implements ActionListener{

	
	Controller listener;
	ImagePanel centralPanel;
	JLabel player1Name;
	JLabel player2Name;
	JPanel topPanel;
	JPanel firstPanel;
	JPanel secondPanel;
	JLabel chooseLeader;
	JButton playButton;
	ArrayList<JButton> firstTeam = new ArrayList<JButton>();
	ArrayList<JButton> secondTeam = new ArrayList<JButton>();
	
	public ChooseLeaderScreen() {
		this.setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		player1Name = new JLabel();
		player2Name = new JLabel();
		
		firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(1,3));
		
		secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(1,3));
		
		centralPanel = new ImagePanel();
		centralPanel.setLayout(null);
		
		topPanel.add(firstPanel, BorderLayout.CENTER);
		topPanel.add(player1Name, BorderLayout.NORTH);
		
		chooseLeader = new JLabel(" Choose Your Leaders:");
		chooseLeader.setBounds(100,100,400,100);
		chooseLeader.setBackground(new Color(129,9,9));
		chooseLeader.setOpaque(true);
		chooseLeader.setForeground(Color.white);
		chooseLeader.setFont( new Font("Arial Black",Font.BOLD,30));
		
		playButton = new JButton("Play!");
		playButton.setBounds(700,125,100,50);
		playButton.addActionListener(this);
		playButton.setFocusable(false);
		playButton.setBackground(Color.white);
		playButton.setVisible(false);
		
		centralPanel.add(playButton);
		centralPanel.add(chooseLeader);
		
		this.add(centralPanel,BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);
		this.add(secondPanel, BorderLayout.SOUTH); 
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == playButton) {
			listener.nextBoardScreen();
		}
		
		
		for(JButton b: firstTeam) {
			if(e.getSource() == b) {
				listener.addFirstLeader(b);
				b.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
				
				if(listener.getPlay()) {
					playButton.setVisible(true);
				}
			}else {
				if(firstTeam.contains(e.getSource())) {
					b.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				}
				
			}
		}
		
		for(JButton b: secondTeam) {
			if(e.getSource() == b) {
				listener.addSecondLeader(b);
				b.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
				if(listener.getPlay()) {
					playButton.setVisible(true);
				}
			}else {
				if(secondTeam.contains(e.getSource()))
					b.setBorder(BorderFactory.createLineBorder(Color.white, 5));
			}
		}
		
	}
	
	public ArrayList<JButton> getFirstTeam() {
		return firstTeam;
	}


	public void setFirstTeam(ArrayList<JButton> firstTeam) {
		this.firstTeam = firstTeam;
	}


	public ArrayList<JButton> getSecondTeam() {
		return secondTeam;
	}


	public void setSecondTeam(ArrayList<JButton> secondTeam) {
		this.secondTeam = secondTeam;
	}
	
	public Controller getListener() {
		return listener;
	}



	public void setListener(Controller listener) {
		this.listener = listener;
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon image = new ImageIcon("bg.png");
		g.drawImage(image.getImage(),0,0,null);
	}

}
