package view;

import javax.swing.*;

public class JOptionPaneMultiInput {

	int x;
	int y;
	
	public JOptionPaneMultiInput() {
		
		  JTextField xField = new JTextField(5);
	      JTextField yField = new JTextField(5);

	      JPanel myPanel = new JPanel();
	      myPanel.add(new JLabel("x:"));
	      myPanel.add(xField);
	      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	      myPanel.add(new JLabel("y:"));
	      myPanel.add(yField);

	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	         x = Integer.parseInt(xField.getText());
	         y = Integer.parseInt(yField.getText());
	      }
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
