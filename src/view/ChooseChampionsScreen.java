package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import engine.Game;
import model.world.Champion;


@SuppressWarnings("serial")
public class ChooseChampionsScreen extends JPanel implements ActionListener, MouseListener {
	
	JButton[] championButton = new JButton[15];
	JPanel centralPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	JLabel player1Name;
	JLabel player2Name;
	JLabel chooseChamps;
	
	ImagePanel leftPanel;
	ImagePanel rightPanel;
	ImageIcon captainamerica;
	ImageIcon deadpool;
	ImageIcon drstrange;
	ImageIcon electro;
	ImageIcon ghostrider;
	ImageIcon hela;
	ImageIcon hulk;
	ImageIcon iceman;
	ImageIcon ironman;
	ImageIcon loki;
	ImageIcon quicksilver;
	ImageIcon spiderman;
	ImageIcon thor;
	ImageIcon venom;
	ImageIcon yellowjacket;
	JButton nextButton;
	Controller listener;
	
	public ChooseChampionsScreen() {
		
		this.setLayout(new BorderLayout());
		
		centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(3,5));
		centralPanel.setPreferredSize(new Dimension(500,500));
		this.add(centralPanel, BorderLayout.CENTER);
		
		topPanel = new ImagePanel();
		bottomPanel = new JPanel();
		leftPanel = new ImagePanel();
		rightPanel = new ImagePanel();
		
		topPanel.setPreferredSize(new Dimension(0,50));
		//topPanel.setBackground(new Color(129,9,9));
		this.add(topPanel, BorderLayout.NORTH);
		
		topPanel.setLayout(new BorderLayout());
		
		
		player1Name = new JLabel();
		player1Name.setBackground(Color.white);
		player1Name.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
		player1Name.setOpaque(true);
		
		player2Name = new JLabel();
		player2Name.setBackground(Color.white);
		player2Name.setOpaque(true);
		
		chooseChamps = new JLabel(" Choose a champion: ");
		chooseChamps.setBackground(Color.white);
		chooseChamps.setOpaque(true);
		chooseChamps.setVisible(true);
		//topPanel.add(chooseChamps, BorderLayout.NORTH);
		
		
		nextButton = new JButton(" Next ");
		nextButton.setBackground(Color.white);
		nextButton.setOpaque(true);
		nextButton.setFocusable(false);
		nextButton.addActionListener(this);
		nextButton.setVisible(false);
		
		
		topPanel.add(player1Name, BorderLayout.WEST);
		topPanel.add(player2Name, BorderLayout.EAST);
		topPanel.add(nextButton, BorderLayout.CENTER);
		
		
		bottomPanel.setPreferredSize(new Dimension(0,150));
		bottomPanel.setBackground(new Color(255,217,55));
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		leftPanel.setPreferredSize(new Dimension(150,0));
		this.add(leftPanel, BorderLayout.WEST);
		
		rightPanel.setPreferredSize(new Dimension(150,0));
		this.add(rightPanel, BorderLayout.EAST);
		
		captainamerica = new ImageIcon("captainamerica.png");
		championButton[0] = new JButton();
		championButton[0].setIcon(captainamerica);
		championButton[0].setName("Captain America");
		championButton[0].setFocusable(false);
		championButton[0].setBackground(Color.white);
		championButton[0].addActionListener(this);
		
		deadpool = new ImageIcon("deadpool.png");
		championButton[1] = new JButton();
		championButton[1].setIcon(deadpool);
		championButton[1].setName("Deadpool");
		championButton[1].setFocusable(false);
		championButton[1].setBackground(Color.white);
		championButton[1].addActionListener(this);
		
		drstrange = new ImageIcon("drstrange.png");
		championButton[2] = new JButton();
		championButton[2].setIcon(drstrange);
		championButton[2].setName("Dr Strange");
		championButton[2].setFocusable(false);
		championButton[2].setBackground(Color.white);
		championButton[2].addActionListener(this);
		
		electro = new ImageIcon("electro.png");
		championButton[3] = new JButton();
		championButton[3].setIcon(electro);
		championButton[3].setName("Electro");
		championButton[3].setFocusable(false);
		championButton[3].setBackground(Color.white);
		championButton[3].addActionListener(this);
		
		ghostrider = new ImageIcon("ghostrider.png");
		championButton[4] = new JButton();
		championButton[4].setIcon(ghostrider);
		championButton[4].setName("Ghost Rider");
		championButton[4].setFocusable(false);
		championButton[4].setBackground(Color.white);
		championButton[4].addActionListener(this);
		
		hela = new ImageIcon("hela.png");
		championButton[5] = new JButton();
		championButton[5].setIcon(hela);
		championButton[5].setName("Hela");
		championButton[5].setFocusable(false);
		championButton[5].setBackground(Color.white);
		championButton[5].addActionListener(this);
		
		yellowjacket = new ImageIcon("yellowjacket.png");
		championButton[6] = new JButton();
		championButton[6].setIcon(yellowjacket);
		championButton[6].setName("Yellow Jacket");
		championButton[6].setFocusable(false);
		championButton[6].setBackground(Color.white);
		championButton[6].addActionListener(this);
		
		hulk = new ImageIcon("hulk.png");
		championButton[7] = new JButton();
		championButton[7].setIcon(hulk);
		championButton[7].setName("Hulk");
		championButton[7].setFocusable(false);
		championButton[7].setBackground(Color.white);
		championButton[7].addActionListener(this);
		
		iceman = new ImageIcon("iceman.png");
		championButton[8] = new JButton();
		championButton[8].setIcon(iceman);
		championButton[8].setName("Iceman");
		championButton[8].setFocusable(false);
		championButton[8].setBackground(Color.white);
		championButton[8].addActionListener(this);
		
		ironman = new ImageIcon("ironman.png");
		championButton[9] = new JButton();
		championButton[9].setIcon(ironman);
		championButton[9].setName("Ironman");
		championButton[9].setFocusable(false);
		championButton[9].setBackground(Color.white);
		championButton[9].addActionListener(this);
		
		loki = new ImageIcon("loki.png");
		championButton[10] = new JButton();
		championButton[10].setIcon(loki);
		championButton[10].setName("Loki");
		championButton[10].setFocusable(false);
		championButton[10].setBackground(Color.white);
		championButton[10].addActionListener(this);
		
		quicksilver = new ImageIcon("quicksilver.png");
		championButton[11] = new JButton();
		championButton[11].setIcon(quicksilver);
		championButton[11].setName("Quicksilver");
		championButton[11].setFocusable(false);
		championButton[11].setBackground(Color.white);
		championButton[11].addActionListener(this);
		
		spiderman = new ImageIcon("spiderman.png");
		championButton[12] = new JButton();
		championButton[12].setIcon(spiderman);
		championButton[12].setName("Spiderman");
		championButton[12].setFocusable(false);
		championButton[12].setBackground(Color.white);
		championButton[12].addActionListener(this);
		
		thor = new ImageIcon("thor.png");
		championButton[13] = new JButton();
		championButton[13].setIcon(thor);
		championButton[13].setName("Thor");
		championButton[13].setFocusable(false);
		championButton[13].setBackground(Color.white);
		championButton[13].addActionListener(this);
		
		venom = new ImageIcon("venom.png");
		championButton[14] = new JButton();
		championButton[14].setIcon(venom);
		championButton[14].setName("Venom");
		championButton[14].setFocusable(false);
		championButton[14].setBackground(Color.white);
		championButton[14].addActionListener(this);
		
		for(int i =0; i<15;i++) {
			championButton[i].addMouseListener(this);
			centralPanel.add(championButton[i]);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i =0; i<15;i++) {
			if(e.getSource()== championButton[i] && (listener.getFirstPlayer().getTeam().size() + listener.getSecondPlayer().getTeam().size()<6)) {
				
				if(listener.isFirstPlayer()) {
					player1Name.setBorder(BorderFactory.createLineBorder(Color.white, 5));
					player2Name.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
				}else {
					player1Name.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
					player2Name.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				}
				
				listener.selectChampion(championButton[i]);
				championButton[i].setEnabled(false);
				
				if(listener.getFirstPlayer().getTeam().size() + listener.getSecondPlayer().getTeam().size()>=6) {
					nextButton.setVisible(true);
				}
			}
		}
		
		if(e.getSource()== nextButton) {
			listener.nextLeaderScreen();
		}
		
	}
	
	public Controller getListener() {
		return listener;
	}

	public void setListener(Controller listener) {
		this.listener = listener;
	}
	
	public JLabel getPlayer1Name() {
		return player1Name;
	}

	public JLabel getPlayer2Name() {
		return player2Name;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(int i=0;i<15;i++) {
			if(e.getSource()== championButton[i]) {
				listener.printChampionDetails(championButton[i],bottomPanel);
				
				Champion c = null;
				ArrayList<Champion> champions = Game.getAvailableChampions();
				
				for(Champion curr : champions) {
					if(championButton[i].getName().equals(curr.getName())) {
						c = curr;
						break;
					}
				}
				
				for(int j = 1; j<=c.getAbilities().size();j++) {
					listener.printAbilityDetails(j, bottomPanel, c);
				}
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(int i=0;i<15;i++) {
			if(e.getSource()== championButton[i]) {
				bottomPanel.removeAll();
				bottomPanel.revalidate();
			}
		}
		
	}
}
