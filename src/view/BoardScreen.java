package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import model.world.Cover;
import model.world.Direction;

@SuppressWarnings("serial")
public class BoardScreen extends JPanel implements MouseListener, ActionListener {
	
	JPanel topPanel;
	JPanel centralPanel;
	JPanel leftPanel;
	JPanel rightPanel;
	
	
	JPanel buttonPanel;
	JPanel attackArrowPanel;
	JPanel moveArrowPanel;
	
	JButton moveUp = new BasicArrowButton(BasicArrowButton.NORTH);
	JButton moveDown = new BasicArrowButton(BasicArrowButton.SOUTH);
	JButton moveLeft = new BasicArrowButton(BasicArrowButton.WEST);
	JButton moveRight = new BasicArrowButton(BasicArrowButton.EAST);
	
	JButton attackUp = new BasicArrowButton(BasicArrowButton.NORTH);
	JButton attackDown = new BasicArrowButton(BasicArrowButton.SOUTH);
	JButton attackLeft = new BasicArrowButton(BasicArrowButton.WEST);
	JButton attackRight = new BasicArrowButton(BasicArrowButton.EAST);
	
	JButton doneMove = new JButton("Done");
	JButton doneAttack = new JButton("Done");
	
	JLabel player1;
	JLabel player2;
	JLabel currentChampion;
	JLabel firstLeader;
	JLabel secondLeader;
	
	JLabel[][] board = new JLabel[5][5];
	
	JButton attack;
	
	JButton move;
	
	JButton castAbility1;
	JButton castAbility2;
	JButton castAbility3;
	JButton castPunch;
	
	JButton useLeaderAbility;
	
	JButton endTurn;
	
	Controller listener;
	
	public BoardScreen() {
		
		this.setLayout(new BorderLayout());
		
		topPanel = new ImagePanel();
		topPanel.setPreferredSize(new Dimension(0,60));
		
		currentChampion = new JLabel();
		currentChampion.setBackground(Color.white);
		currentChampion.setOpaque(true);
		
		player1 = new JLabel();
		player2 = new JLabel();
		player1.setBackground(new Color(129,9,9));
		player2.setBackground(new Color(64,85,98));
		player1.setForeground(Color.white);
		player2.setForeground(Color.white);
		player1.setOpaque(true);
		player2.setOpaque(true);
		
		firstLeader = new JLabel();
		firstLeader.setBackground(Color.white);
		firstLeader.setBackground(new Color(129,9,9));
		firstLeader.setForeground(Color.white);
		firstLeader.setOpaque(true);
		
		secondLeader = new JLabel();
		secondLeader.setBackground(Color.white);
		secondLeader.setBackground(new Color(64,85,98));
		secondLeader.setForeground(Color.white);
		secondLeader.setOpaque(true);
		
		topPanel.add(player1);
		topPanel.add(firstLeader);
		topPanel.add(player2);
		topPanel.add(secondLeader);
		topPanel.add(currentChampion);
		
		this.add(topPanel, BorderLayout.NORTH);
		
		centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(5,5));
		centralPanel.setBackground(Color.white);
		centralPanel.setPreferredSize(new Dimension(500,500));
		this.add(centralPanel, BorderLayout.CENTER);
		
		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255,217,55));
		leftPanel.setPreferredSize(new Dimension(250,0));
		this.add(leftPanel, BorderLayout.WEST);
		
		
		buttonPanel = new ImagePanel();
		buttonPanel.setPreferredSize(new Dimension(100,250));
		
		move = new JButton("Move");
		move.setFocusable(false);
		move.addActionListener(this);
		move.setBackground(Color.white);
		
		attack = new JButton("Attack");
		attack.setFocusable(false);
		attack.addActionListener(this);
		attack.setBackground(Color.white);
		
		generateCastAbilityButtons();
		
		useLeaderAbility = new JButton("Use Leader Ability");
		useLeaderAbility.setFocusable(false);
		useLeaderAbility.addActionListener(this);
		useLeaderAbility.addMouseListener(this);
		useLeaderAbility.setBackground(Color.white);
		//useLeaderAbility.setVisible(false);
		
		endTurn = new JButton("End Turn");
		endTurn.setFocusable(false);
		endTurn.addActionListener(this);
		endTurn.setBackground(Color.white);
		
		buttonPanel.add(move);
		
		buttonPanel.add(attack);
		
		buttonPanel.add(castAbility1);
		buttonPanel.add(castAbility2);
		buttonPanel.add(castAbility3);
		buttonPanel.add(castPunch);
		
		buttonPanel.add(useLeaderAbility);
		
		buttonPanel.add(endTurn);
		
		doneMove.addActionListener(this);
		doneMove.setFocusable(false);
		doneMove.setBackground(Color.white);
		
		
		moveUp.setName("DOWN");
		moveDown.setName("UP");
		moveLeft.setName("LEFT");
		moveRight.setName("RIGHT");
		
		moveUp.addActionListener(this);
		moveDown.addActionListener(this);
		moveLeft.addActionListener(this);
		moveRight.addActionListener(this);
		
		moveArrowPanel = new JPanel();
		moveArrowPanel.setLayout(new BorderLayout());
		moveArrowPanel.setPreferredSize(new Dimension(110, 110));
		moveArrowPanel.add(doneMove, BorderLayout.CENTER);
		moveArrowPanel.add(moveUp, BorderLayout.NORTH);
		moveArrowPanel.add(moveDown, BorderLayout.SOUTH);
		moveArrowPanel.add(moveLeft, BorderLayout.WEST);
		moveArrowPanel.add(moveRight, BorderLayout.EAST);
		
		
		doneAttack.addActionListener(this);
		doneAttack.setFocusable(false);
		doneAttack.setBackground(Color.white);
		
		attackUp.setName("DOWN");
		attackDown.setName("UP");
		attackLeft.setName("LEFT");
		attackRight.setName("RIGHT");
		
		attackUp.addActionListener(this);
		attackDown.addActionListener(this);
		attackLeft.addActionListener(this);
		attackRight.addActionListener(this);
		
		attackArrowPanel = new JPanel();
		attackArrowPanel.setLayout(new BorderLayout());
		attackArrowPanel.setPreferredSize(new Dimension(110, 110));
		attackArrowPanel.add(doneAttack, BorderLayout.CENTER);
		attackArrowPanel.add(attackUp, BorderLayout.NORTH);
		attackArrowPanel.add(attackDown, BorderLayout.SOUTH);
		attackArrowPanel.add(attackLeft, BorderLayout.WEST);
		attackArrowPanel.add(attackRight, BorderLayout.EAST);
		
		rightPanel = new ImagePanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(120,0));
		
		rightPanel.add(buttonPanel, BorderLayout.NORTH);
		
		this.add(rightPanel, BorderLayout.EAST);
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				board[i][j]= new JLabel("", JLabel.CENTER);
				board[i][j].setBorder(new LineBorder(Color.BLACK));
				centralPanel.add(board[i][j]);
			}
		}
		
	}
	
	public void generateCastAbilityButtons() {
		
		castAbility1 = new JButton("Cast Ability 1");
		castAbility1.setFocusable(false);
		castAbility1.addActionListener(this);
		castAbility1.addMouseListener(this);
		castAbility1.setBackground(Color.white);
		
		castAbility2 = new JButton("Cast Ability 2");
		castAbility2.setFocusable(false);
		castAbility2.addActionListener(this);
		castAbility2.addMouseListener(this);
		castAbility2.setBackground(Color.white);
		
		castAbility3 = new JButton("Cast Ability 3");
		castAbility3.setFocusable(false);
		castAbility3.addActionListener(this);
		castAbility3.addMouseListener(this);
		castAbility3.setBackground(Color.white);
		
		castPunch = new JButton("Cast Punch");
		castPunch.setFocusable(false);
		castPunch.addActionListener(this);
		castPunch.addMouseListener(this);
		castPunch.setBackground(Color.white);
		castPunch.setEnabled(false);
	}
	
	
	public JPanel getCentralPanel() {
		return centralPanel;
	}


	public JLabel[][] getBoard() {
		return board;
	}




	public Controller getListener() {
		return listener;
	}

	public void setListener(Controller listener) {
		this.listener = listener;
	}




	@Override
	public void mouseClicked(MouseEvent e) {}




	@Override
	public void mousePressed(MouseEvent e) {}




	@Override
	public void mouseReleased(MouseEvent e) {}




	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == castAbility1) {
			listener.printAbilityDetails(1, leftPanel, listener.getGame().getCurrentChampion());
		}
		if(e.getSource() == castAbility2) {
			listener.printAbilityDetails(2, leftPanel, listener.getGame().getCurrentChampion());
		}
		if(e.getSource() == castAbility3) {
			listener.printAbilityDetails(3, leftPanel, listener.getGame().getCurrentChampion());
		}
		if(e.getSource() == castPunch) {
			if(listener.getGame().getCurrentChampion().getAbilities().size() == 4) {
				listener.printAbilityDetails(4, leftPanel, listener.getGame().getCurrentChampion());
			}
		}
		
		if(e.getSource() == useLeaderAbility ) {
			listener.printLeaderAbilityDetails(leftPanel);
		}
		
		for(int i=0;i<board.length;i++) {
			for(int j=0; j<board[i].length;j++) {
				if(e.getSource()== board[i][j]) {
					if(listener.getGame().getBoard()[i][j] instanceof Cover) {
						listener.printCoverDetails(i,j, leftPanel);
					}else
						listener.printChampionDetails(board[i][j],leftPanel);
				}
			}
		}
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
			leftPanel.removeAll();
			leftPanel.revalidate();
			leftPanel.repaint();
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == move) {
			move.setEnabled(false);
			attack.setEnabled(false);
			castAbility1.setEnabled(false);
			castAbility2.setEnabled(false);
			castAbility3.setEnabled(false);
			castPunch.setEnabled(false);
			useLeaderAbility.setEnabled(false);
			endTurn.setEnabled(false);
			
			rightPanel.add(moveArrowPanel, BorderLayout.SOUTH);
			
			rightPanel.revalidate();
			rightPanel.repaint();
		}
		
		if(e.getSource() == attack) {
			move.setEnabled(false);
			attack.setEnabled(false);
			castAbility1.setEnabled(false);
			castAbility2.setEnabled(false);
			castAbility3.setEnabled(false);
			castPunch.setEnabled(false);
			useLeaderAbility.setEnabled(false);
			endTurn.setEnabled(false);
			
			rightPanel.add(attackArrowPanel, BorderLayout.SOUTH);
			
			rightPanel.revalidate();
			rightPanel.repaint();
		}
		
		if(e.getSource() == moveUp || e.getSource() == moveDown|| e.getSource()== moveLeft|| e.getSource()== moveRight) {
			Direction d = Direction.valueOf(((JButton) e.getSource()).getName());
			listener.moveChampion(d);
			
		}
		
		if(e.getSource() == attackUp || e.getSource() == attackDown|| e.getSource()== attackLeft|| e.getSource()== attackRight) {
			Direction d = Direction.valueOf(((JButton) e.getSource()).getName());
			listener.attackChampion(d);
		}
		
		if(e.getSource()== doneMove) {
			move.setEnabled(true);
			attack.setEnabled(true);
			castAbility1.setEnabled(true);
			castAbility2.setEnabled(true);
			castAbility3.setEnabled(true);
			useLeaderAbility.setEnabled(true);
			endTurn.setEnabled(true);
			
			rightPanel.remove(moveArrowPanel);
			
			rightPanel.revalidate();
			rightPanel.repaint();
		}
		
		if(e.getSource()== doneAttack) {
			move.setEnabled(true);
			attack.setEnabled(true);
			castAbility1.setEnabled(true);
			castAbility2.setEnabled(true);
			castAbility3.setEnabled(true);
			castPunch.setEnabled(true);
			useLeaderAbility.setEnabled(true);
			endTurn.setEnabled(true);
			
			rightPanel.remove(attackArrowPanel);
			
			rightPanel.revalidate();
			rightPanel.repaint();
		}
		
		if(e.getSource() == endTurn) {
			listener.endChampionTurn();
			if(listener.getGame().callHasEffect(listener.getGame().getCurrentChampion(), "Disarm")) {
				castPunch.setEnabled(true);
			}else {
				castPunch.setEnabled(false);
			}
		}
		
		if(e.getSource() == castAbility1) {
			listener.castAbilities(1);
		}
		if(e.getSource() == castAbility2) {
			listener.castAbilities(2);
		}
		if(e.getSource() == castAbility3) {
			listener.castAbilities(3);
		}
		if(e.getSource() == castPunch) {
			if(listener.getGame().callHasEffect(listener.getGame().getCurrentChampion(), "Disarm")) {
				listener.castAbilities(4);
			}	
		}
		
		if(e.getSource() == useLeaderAbility) {
			listener.useCurrentLeaderAbility();
		}
		
	
	}

	
}
