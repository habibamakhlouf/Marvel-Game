package view;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class Controller {

	MyFrame view;
	Game game;
	Player firstPlayer;
	Player secondPlayer;
	boolean isFirstPlayer = true;
	ArrayList<JButton> firstTeam = new ArrayList<JButton>();
	ArrayList<JButton> secondTeam = new ArrayList<JButton>();

	public Controller() {
		view = new MyFrame();
		view.getSs().setListener(this);
		view.getEps().setListener(this);
		view.getCcs().setListener(this);
		view.getCls().setListener(this);
		view.getBs().setListener(this);
		
		
	}
	
	public static void main(String [] args) {
		new Controller();
	}
	
	//TODO
	
	public ArrayList<Champion> turnOrderList() {
		ArrayList<Champion> temp = new ArrayList<Champion>();
		ArrayList<String> name = new ArrayList<String>();
		while (!game.getTurnOrder().isEmpty()) {
				Champion c =(Champion) game.getTurnOrder().remove();
				temp.add(c);
				name.add(c.getName());
		}
		for(Champion c: temp) {
			game.getTurnOrder().insert(c);
		}
		return temp;
	}
	
	public ArrayList<String> printOrder(ArrayList<Champion> turns) {
		ArrayList<String> name = new ArrayList<String>();
		for(Champion c : turns) {
			name.add(c.getName());
		}
		return name;
	}
	
	//
	
	public void createGame(String player1, String player2) {

		firstPlayer = new Player(player1);
		secondPlayer = new Player(player2);
		game = new Game(firstPlayer,secondPlayer);
		try {
			Game.loadAbilities("Abilities.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//I don't like what I did here
		view.getCcs().getPlayer1Name().setText(" Player 1: " + firstPlayer.getName() + " ");
		view.getCcs().getPlayer2Name().setText(" Player 2: " + secondPlayer.getName() + " ");
		//
		
		
	}
	
	public void nextScreen() {
		view.cl.next(view.getPanelContainer());
	}
	
	public void nextLeaderScreen() {
		
		//I don't like what I did here
		for(JButton b: firstTeam) {
			b.setEnabled(true);
			b.addActionListener(view.getCls());
			view.getCls().firstPanel.add(b);
		}
		
		for(JButton b: secondTeam) {
			b.setEnabled(true);
			b.addActionListener(view.getCls());
			view.getCls().secondPanel.add(b);
		}
		
		
		view.getCls().setFirstTeam(firstTeam);
		view.getCls().setSecondTeam(secondTeam);
		
		nextScreen();
		
	}
	
	public void nextBoardScreen() {
		
		game.placeChampions();
		game.callPrepareChampionTurns();
		
		view.getBs().firstLeader.setText(" First Player Leader: " + firstPlayer.getLeader().getName() + ".  ");
		view.getBs().secondLeader.setText(" Second Player Leader: " + secondPlayer.getLeader().getName() + ".  ");
		
		updateBoard();
		
		nextScreen();
	}
	
	public void updateBoard() {
		view.getBs().player1.setText("  Player 1: " +firstPlayer.getName()+",  First Player Ability Used: " + game.isFirstLeaderAbilityUsed()+ ".  ");
		view.getBs().player2.setText("  Player 2: " +secondPlayer.getName()+",  Second Player Ability Used: " + game.isSecondLeaderAbilityUsed()+ ".  ");
		
		//TODO printTurnOrder is for printing for me can be removed
		//System.out.println(printTurnOrder());
		//
		
		ArrayList<Champion> turns = turnOrderList();
		
		for(int i = 0; i< turns.size();i++) {
			if(game.callHasEffect(turns.get(i), "Stun")) {
				turns.remove(i);
				i--;
			}
		}
		
		view.getBs().currentChampion.setText("  Turns: "+ printOrder(turns) +".  ");
		
//		if(!game.getTurnOrder().isEmpty()) {
//			Champion next = (Champion) game.getTurnOrder().peekMin();
//			view.getBs().currentChampion.setText("  Current Champion: "+ curr+ ",  Next Champion: " + next.getName() +".  ");
//		}else {
//			view.getBs().currentChampion.setText("  Current Champion: "+ curr+ ". ");
//		}
		
		//TODO
		
		
		if(firstPlayer.getTeam().contains(game.getCurrentChampion())) {
			view.getBs().player1.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
			isFirstPlayer = true;
			view.getBs().player2.setBorder(BorderFactory.createEmptyBorder());
		}else
			if(secondPlayer.getTeam().contains(game.getCurrentChampion())){
				view.getBs().player2.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
				isFirstPlayer = false;
				view.getBs().player1.setBorder(BorderFactory.createEmptyBorder());
			}
		
		for( int i =0; i< Game.getBoardheight(); i++) {
			for(int j=0; j<Game.getBoardwidth();j++) {
				JLabel label = view.getBs().getBoard()[i][j];
				if(game.getBoard()[i][j]!= null) {
					if(game.getBoard()[i][j] instanceof Champion) {
						Champion c = (Champion) game.getBoard()[i][j];
						label.setIcon(new ImageIcon(c.getName()+" pixel.png"));
						label.setName(c.getName());
						if(label.getMouseListeners().length == 0) {
							label.addMouseListener(view.getBs());
						}
						
						//changes to specific teams
						
						if(firstPlayer.getTeam().contains(c)) {
							label.setBackground(new Color(129,9,9));
							label.setOpaque(true);
						}else {
							label.setBackground(new Color(64,85,98));
							label.setOpaque(true);
						}
						
						
						if(c.equals(game.getCurrentChampion())) {
							label.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
						}else {
							label.setBorder(new LineBorder(Color.BLACK));
						}
						
//						if(game.getCurrentChampion().equals(firstPlayer.getLeader()) || game.getCurrentChampion().equals(secondPlayer.getLeader())) {
//							view.getBs().useLeaderAbility.setVisible(true);
//						}else {
//							view.getBs().useLeaderAbility.setVisible(false);
//						}
						
					}
					else {
						label.setIcon(new ImageIcon("cover.png"));
						if(label.getMouseListeners().length == 0) {
							label.addMouseListener(view.getBs());
						}
					}
						
				}else {
					label.setIcon(null);
					label.setBorder(new LineBorder(Color.BLACK));
					label.setOpaque(false);
					label.removeMouseListener(view.getBs());
				}
			}
		}
		view.getBs().revalidate();
		view.getBs().repaint();
	}
	
	public void addFirstLeader(JButton button) {
		
		Champion c = null;
		
			 
		for(Champion curr: firstPlayer.getTeam() ) {
			if(curr.getName().equals(button.getName())) {
				c = curr;
			}	
		}
		
		firstPlayer.setLeader(c);
		
	}
	
	public void addSecondLeader(JButton button) {
		
		Champion c = null;
		for(Champion curr: secondPlayer.getTeam() ) {
			if(curr.getName().equals(button.getName())) {
				c = curr;
			}
		}
		secondPlayer.setLeader(c);
		
	}
	
	public boolean getPlay() {
		return (firstPlayer.getLeader()!= null && secondPlayer.getLeader()!=null);
	}

	public void selectChampion(JButton b) {
		
		Champion c = null;
		ArrayList<Champion> champions = Game.getAvailableChampions();
		
		for(Champion curr : champions) {
			if(b.getName().equals(curr.getName())) {
				c = curr;
				break;
			}
		}
		
		if(isFirstPlayer) {
			if(firstPlayer.getTeam().size()<3) {
				firstPlayer.getTeam().add(c);
				firstTeam.add(b);
				isFirstPlayer = false;
			}
		}else {
			if(secondPlayer.getTeam().size()<3) {
				secondPlayer.getTeam().add(c);
				secondTeam.add(b);
				isFirstPlayer = true;
			}
		}
		
	}
	
	public void printCoverDetails(int i, int j, JPanel panel) {
		if(game.getBoard()[i][j] instanceof Cover) {
			panel.add(new JLabel("  Current HP: " + ( (Cover)game.getBoard()[i][j]).getCurrentHP() ));
			panel.revalidate();
			panel.repaint();
		}
		
	}
	
	public void printChampionDetails(Component b, JPanel panel) {
		
		Champion c = null;
		ArrayList<Champion> champions = Game.getAvailableChampions();
		
		for(Champion curr : champions) {
			if(b.getName().equals(curr.getName())) {
				c = curr;
				break;
			}
		}
		
		panel.add(new JLabel(" Name: " + c.getName()));
		panel.add(new JLabel(", Type: " + c.getClass().getSimpleName()));
		panel.add(new JLabel(", Current HP: " + c.getCurrentHP()));
		panel.add(new JLabel(", Mana: " + c.getMana()));
		panel.add(new JLabel(", Action Points: "+c.getCurrentActionPoints()));
		panel.add(new JLabel(", Attack Damage: "+ c.getAttackDamage()));
		panel.add(new JLabel(", Attack Range: "+ c.getAttackRange()));
		panel.add(new JLabel(", Speed: "+ c.getSpeed() + ". "));
		
		if(!c.getAppliedEffects().isEmpty()) {
		panel.add(new JLabel(" Applied Effects:- "));
		}
		for(Effect e : c.getAppliedEffects()) {
			panel.add(new JLabel(" Effect Name: "+ e.getName()));
			panel.add(new JLabel(" Effect Duration: "+ e.getDuration()+". "));
		}
		
		panel.revalidate();
		panel.repaint();
		
	}
	
	public Game getGame() {
		return game;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}
	
	public boolean isFirstPlayer() {
		return isFirstPlayer;
	}

	public ArrayList<JButton> getFirstTeam() {
		return firstTeam;
	}

	public ArrayList<JButton> getSecondTeam() {
		return secondTeam;
	}

	public void moveChampion(Direction d) {
		
		
		try {
			game.move(d);
			
		} catch (NotEnoughResourcesException | UnallowedMovementException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		updateBoard();
		
		
	}

	public void attackChampion(Direction d) {
		
		try {
			game.attack(d);
		} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		checkWinner();
		
	}

	public void endChampionTurn() {
		game.endTurn();
		updateBoard();
		
	}

	public void castAbilities(int i) {
		Ability a = game.getCurrentChampion().getAbilities().get(i-1);
		if(a.getCastArea().equals(AreaOfEffect.TEAMTARGET) || a.getCastArea().equals(AreaOfEffect.SURROUND) || a.getCastArea().equals(AreaOfEffect.SELFTARGET)) {
			try {
				game.castAbility(a);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}else if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)){
			String direction = JOptionPane.showInputDialog("Enter a direction: (UP,DOWN,LEFT,RIGHT)");
			direction = direction.toUpperCase();
			
			if(direction.equals("UP")||direction.equals("DOWN")||direction.equals("LEFT")||direction.equals("RIGHT")) {
				if(direction.equals("UP")) {
					direction = "DOWN";
				}else
					if(direction.equals("DOWN")) {
						direction = "UP";
					}
				Direction d = Direction.valueOf(direction);
				
				try {
					game.castAbility(a, d);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
			
			
		}else if(a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
			
			JOptionPaneMultiInput input = new JOptionPaneMultiInput();
			int x = input.getX();
			int y = input.getY();
			
			try {
				game.castAbility(a, x, y);
			} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
					| CloneNotSupportedException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
	
		checkWinner();
		
	}

	public void printAbilityDetails(int i, JPanel panel, Champion c) {
		Ability a = c.getAbilities().get(i-1);
		panel.add(new JLabel( " Ability " +i+":- "));
		panel.add(new JLabel(" Name: "+ a.getName()));
		panel.add(new JLabel(", Type: "+ a.getClass().getSimpleName()));
		panel.add(new JLabel(", Area of effect: "+ a.getCastArea()));
		panel.add(new JLabel(", Cast Range: "+ a.getCastRange()));
		panel.add(new JLabel(", Mana Cost: "+ a.getManaCost()));
		panel.add(new JLabel(", Action Point Cost: "+ a.getRequiredActionPoints()));
		panel.add(new JLabel(", Current Cooldown: "+ a.getCurrentCooldown()));
		panel.add(new JLabel(", Base Cooldown: "+ a.getBaseCooldown()));
		if(a instanceof DamagingAbility) {
			panel.add(new JLabel(", Damage Amount: "+ ((DamagingAbility)a).getDamageAmount() + ". "));
		}else
			if(a instanceof HealingAbility) {
				panel.add(new JLabel(", Heal Amount: "+ ((HealingAbility)a).getHealAmount() + ". "));
			}else
				if(a instanceof CrowdControlAbility) {
					panel.add(new JLabel(", Effect Name: "+ ((CrowdControlAbility)a).getEffect().getName()));
					panel.add(new JLabel(", Effect Duration: "+ ((CrowdControlAbility)a).getEffect().getDuration() + ". "));
				}
		panel.revalidate();
		panel.repaint();
		
	}
	
	public void useCurrentLeaderAbility() {
		
		try {
			game.useLeaderAbility();
		} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		checkWinner();
	}
	
	public void checkWinner() {
		updateBoard();
		if(game.checkGameOver() !=null) {
			//view.dispose();
//			view.getBs().attackDown.removeActionListener(view.getBs());
//			view.getBs().attackUp.removeActionListener(view.getBs());
//			view.getBs().attackLeft.removeActionListener(view.getBs());
//			view.getBs().attackRight.removeActionListener(view.getBs());
//			view.getBs().moveDown.removeActionListener(view.getBs());
//			view.getBs().moveUp.removeActionListener(view.getBs());
//			view.getBs().moveLeft.removeActionListener(view.getBs());
//			view.getBs().moveRight.removeActionListener(view.getBs());
//			view.getBs().castAbility1.removeActionListener(view.getBs());
//			view.getBs().castAbility2.removeActionListener(view.getBs());
//			view.getBs().castAbility3.removeActionListener(view.getBs());
//			view.getBs().useLeaderAbility.removeActionListener(view.getBs());
//			view.getBs().endTurn.removeActionListener(view.getBs());
			
			view.getWs().win.setText(game.checkGameOver().getName()+ " Won!");
			nextScreen();
			
			//JOptionPane.showMessageDialog(null, game.checkGameOver().getName() + " Won! ");
		}
	}


	public void printLeaderAbilityDetails(JPanel panel) {
		Champion c = game.getCurrentChampion();
		if(c.getClass().getSimpleName().equals("AntiHero")) {
			panel.add(new JLabel("Stun every non-leader champion for 2 turns"));
		}else if(c.getClass().getSimpleName().equals("Hero")) {
			panel.add(new JLabel("Remove negative effects from team"));
			panel.add(new JLabel("and adds Embrace effect for 2 turns"));
		}else if(c.getClass().getSimpleName().equals("Villain")) {
			panel.add(new JLabel("Knock out all enemy champions"));
			panel.add(new JLabel("with less than 30% health"));
		}
		
		panel.revalidate();
		panel.repaint();
	}

}


