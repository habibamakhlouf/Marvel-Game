package view;
 
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	
	JPanel panelContainer;
	CardLayout cl;
	StartScreen ss;
	EnterPlayerScreen eps;
	ChooseChampionsScreen ccs;
	ChooseLeaderScreen cls;
	BoardScreen bs;
	WinScreen ws;
	
	
	
	public MyFrame() {
		super();
		cl = new CardLayout();
		panelContainer = new JPanel();
		panelContainer.setLayout(cl);
		panelContainer.setPreferredSize(new Dimension(1000,650));
		panelContainer.setBounds(0, 0, 1000, 650);
		
		ss = new StartScreen();
		eps = new EnterPlayerScreen();
		ccs = new ChooseChampionsScreen();
		cls = new ChooseLeaderScreen();
		bs = new BoardScreen();
		ws = new WinScreen();
		
		
		panelContainer.add(ss,"Start Screen");
		panelContainer.add(eps, "Enter Player Screen");
		panelContainer.add(ccs, "Choose Champions Screen");
		panelContainer.add(cls, "Choose Leader Screen");
		panelContainer.add(bs, "Board Screen");
		panelContainer.add(ws, "Win Screen");
		
		cl.show(panelContainer, "Start Screen");
		
		this.setTitle("Marvel: Ultimate War");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,650);
		this.setVisible(true);
		//this.setResizable(false);
		this.add(panelContainer);
		this.pack();
		
		ImageIcon icon = new ImageIcon("appicon.jpg");
		this.setIconImage(icon.getImage());
		this.getContentPane().setBackground(new Color(64,85,98));
		this.setLocationRelativeTo(null);
		
	}



	public JPanel getPanelContainer() {
		return panelContainer;
	}

	public CardLayout getCl() {
		return cl;
	}

	public StartScreen getSs() {
		return ss;
	}

	public EnterPlayerScreen getEps() {
		return eps;
	}

	public ChooseChampionsScreen getCcs() {
		return ccs;
	}

	public ChooseLeaderScreen getCls() {
		return cls;
	}


	public BoardScreen getBs() {
		return bs;
	}


	public WinScreen getWs() {
		return ws;
	}

	
	






	
	
	
}

