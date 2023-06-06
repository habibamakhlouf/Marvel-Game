package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;

@SuppressWarnings("serial")
public class WinScreen extends JPanel {
	
	JLabel win;
	
	public WinScreen() {
		this.setLayout(new BorderLayout());
		win = new JLabel("Player Won");
		win.setPreferredSize(new Dimension(500,500));
		win.setFont(new Font("Arial Black",Font.BOLD,60));
		win.setForeground(Color.white);
		win.setHorizontalAlignment(JLabel.CENTER);
		win.setVerticalTextPosition(JLabel.CENTER);
		
		
		this.add(win);
		
		
	}
//	public static void main(String []args) {
//		JFrame frame = new JFrame();
//		
//		frame.setTitle("Marvel: Ultimate War");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(1000,650);
//		frame.setVisible(true);
//		//frame.setResizable(false);
//		frame.add(new WinScreen());
//		frame.pack();
//		
//		ImageIcon icon = new ImageIcon("appicon.jpg");
//		frame.setIconImage(icon.getImage());
//		frame.getContentPane().setBackground(new Color(64,85,98));
//		frame.setLocationRelativeTo(null);
//	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon image = new ImageIcon("firework.png");
		g.drawImage(image.getImage(),0,0,null);
	}
}
