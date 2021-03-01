import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JPanel resultPanel = new JPanel();
	JLabel result = new JLabel();
	String resultStr = "";
	JButton[] buttons = new JButton[16];
	String buttonInput = "";
	String[] buttonText = {
				"7","8","9","/",
				"4","5","6","*",
				"1","2","3","-",
				"C","0","=","+"
				};
	
	public Calculator(){
		frame.setTitle("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,700);
		frame.setBackground(Color.BLACK);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		result.setBackground(Color.GRAY);
		result.setForeground(Color.BLACK);
		result.setFont(new Font("MV Boli", Font.BOLD, 70));
		result.setHorizontalAlignment(JLabel.RIGHT);
		result.setOpaque(true);
		
		resultPanel.setLayout(new BorderLayout());
		resultPanel.setBounds(0, 0, 585, 100);
		resultPanel.add(result);
		
		panel.setLayout(new GridLayout(4, 4));
		panel.setBackground(new Color(150,150,150));
		panel.setBounds(0, 100, 600, 700);
		
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setFont(new Font("MV Boli", Font.BOLD,80));
			buttons[i].setText(buttonText[i]);
			panel.add(buttons[i]);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		
		frame.add(resultPanel, BorderLayout.NORTH);
		frame.add(panel);
				
		start();
	}

	public void start() {
		result.setText(" ");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0 ; i < buttons.length; i++) {
			if (e.getSource() == buttons[14]) {
				calculation();
				result.setText(resultStr);
				break;
			}
			else if (e.getSource() == buttons[12]) {
				resultStr = " ";
				result.setText(resultStr);
				buttonInput = "";
			}
			else if (e.getSource() == buttons[i]) {
				resultStr += buttonText[i];
				result.setText(resultStr);
				buttonInput += buttonText[i] + " ";
			}
		}
	}
	
	public void calculation() {
		String[] temp = new String[buttonInput.split(" ").length];
		temp = buttonInput.split(" ");
		int[] numbers = new int[temp.length];
		String numStr = "";
		int r = 0; //the first integer of calculator input
		int j = 0; //counter for numbers[]
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].matches("[^\\D]")) {
				numStr += temp[i];
				numbers[j] = Integer.parseInt(numStr);
			}
			else {
				j++;
				numStr = "";
			}
		}
		r = numbers[0];
		j = 1;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].matches("\\D")) {
				
				if (temp[i].equals("+")) {
					r += numbers[j];
					j++;
				}
				else if (temp[i].equals("-")) {
					r -= numbers[j];
					j++;
				}
				else if (temp[i].equals("*")) {
					r *= numbers[j];
					j++;
				}
				else if (temp[i].equals("/")) {
					r /= numbers[j];
					j++;
				}
				
			}
		
		}
		resultStr = String.valueOf(r);
		buttonInput = "";
		for (int i = 0; i < resultStr.length(); i++) {
			buttonInput += resultStr.charAt(i) + " ";
		}
		
	}
}
