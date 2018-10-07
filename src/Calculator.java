import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Calculator implements ActionListener {

	private final String numberOperator = "number";
	private final String mathOperator = "math";
	private final String otherOperator = "other";
	private final String comma = ".";
	private double leftValue = 0;
	private double rightValue = 0;
	private String currentOperator = "";
	private String lastOperator = "";
	String operationHistory = "";

	private JFrame frame;
	private JTextField valueField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 240, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btn1 = new JButton("1");
		btn1.setBounds(10, 235, 50, 50);
		btn1.setActionCommand(numberOperator);
		btn1.addActionListener(this);
		frame.getContentPane().add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setBounds(65, 235, 50, 50);
		btn2.setActionCommand(numberOperator);
		btn2.addActionListener(this);
		frame.getContentPane().add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setBounds(120, 235, 50, 50);
		btn3.setActionCommand(numberOperator);
		btn3.addActionListener(this);
		frame.getContentPane().add(btn3);

		JButton btn4 = new JButton("4");
		btn4.setBounds(10, 180, 50, 50);
		btn4.setActionCommand(numberOperator);
		btn4.addActionListener(this);
		frame.getContentPane().add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setBounds(65, 180, 50, 50);
		btn5.setActionCommand(numberOperator);
		btn5.addActionListener(this);
		frame.getContentPane().add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setBounds(120, 180, 50, 50);
		btn6.setActionCommand(numberOperator);
		btn6.addActionListener(this);
		frame.getContentPane().add(btn6);

		JButton btn7 = new JButton("7");
		btn7.setBounds(10, 125, 50, 50);
		btn7.setActionCommand(numberOperator);
		btn7.addActionListener(this);
		frame.getContentPane().add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setBounds(65, 125, 50, 50);
		btn8.setActionCommand(numberOperator);
		btn8.addActionListener(this);
		frame.getContentPane().add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setBounds(120, 125, 50, 50);
		btn9.setActionCommand(numberOperator);
		btn9.addActionListener(this);
		frame.getContentPane().add(btn9);

		JButton btn0 = new JButton("0");
		btn0.setBounds(10, 290, 50, 50);
		btn0.setActionCommand(numberOperator);
		btn0.addActionListener(this);
		frame.getContentPane().add(btn0);

		JButton btnComma = new JButton(".");
		btnComma.setBounds(65, 290, 50, 50);
		btnComma.setActionCommand(numberOperator);
		btnComma.addActionListener(this);
		frame.getContentPane().add(btnComma);

		JButton btnResult = new JButton("=");
		btnResult.setBounds(120, 290, 105, 50);
		btnResult.setActionCommand(mathOperator);
		btnResult.addActionListener(this);
		frame.getContentPane().add(btnResult);

		JButton btnPlus = new JButton("+");
		btnPlus.setBounds(175, 235, 50, 50);
		btnPlus.setActionCommand(mathOperator);
		btnPlus.addActionListener(this);
		frame.getContentPane().add(btnPlus);

		JButton btnMinus = new JButton("-");
		btnMinus.setBounds(175, 180, 50, 50);
		btnMinus.setActionCommand(mathOperator);
		btnMinus.addActionListener(this);
		frame.getContentPane().add(btnMinus);

		JButton btnMutliply = new JButton("*");
		btnMutliply.setBounds(175, 125, 50, 50);
		btnMutliply.setActionCommand(mathOperator);
		btnMutliply.addActionListener(this);
		frame.getContentPane().add(btnMutliply);

		JButton btnDivide = new JButton("/");
		btnDivide.setBounds(175, 70, 50, 50);
		btnDivide.setActionCommand(mathOperator);
		btnDivide.addActionListener(this);
		frame.getContentPane().add(btnDivide);

		JButton btnProcent = new JButton("%");
		btnProcent.setBounds(120, 70, 50, 50);
		btnProcent.setActionCommand(mathOperator);
		btnProcent.addActionListener(this);
		frame.getContentPane().add(btnProcent);

		JButton btnBack = new JButton("<-");
		btnBack.setBounds(65, 70, 50, 50);
		btnBack.setActionCommand(otherOperator);
		btnBack.addActionListener(this);
		frame.getContentPane().add(btnBack);

		JButton btnClear = new JButton("C");
		btnClear.setBounds(10, 70, 50, 50);
		btnClear.setActionCommand(otherOperator);
		btnClear.addActionListener(this);
		frame.getContentPane().add(btnClear);

		valueField = new JTextField();
		valueField.setBackground(Color.WHITE);
		valueField.setForeground(Color.BLACK);
		valueField.setEditable(false);
		valueField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		valueField.setHorizontalAlignment(SwingConstants.RIGHT);
		valueField.setBounds(10, 11, 215, 49);
		frame.getContentPane().add(valueField);
		valueField.setColumns(10);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get info about pressed button
		String actionType = e.getActionCommand();
		JButton pressedButton = ((JButton) e.getSource());
		String pressedButtonText = pressedButton.getText();
		// when pressed number
		if (actionType.equals(numberOperator)) {
			mergeValue(pressedButtonText);
			lastOperator = numberOperator;
			// when pressed math operator
		} else if (actionType.equals(mathOperator)) {
			setMathOperator(pressedButtonText);
			// when pressed other operator
		} else if (actionType.equals(otherOperator)) {
			setOtherOperator(pressedButtonText);
			lastOperator = otherOperator;
		}

	}

	// merge all inputed number
	private void mergeValue(String inputNumber) {
		String currentValue = valueField.getText();

		// condition to avoid few comma in user number
		if (inputNumber.equals(comma)) {
			if (currentValue.equals("")) {
				currentValue = "0";
			} else if (currentValue.contains(comma)) {
				return;
			}
		}

		valueField.setText(currentValue + inputNumber);
	}

	// set math operator (+, -, *, /, %, =)
	private void setMathOperator(String actualMathOperator) {

		double result = 0;
		String currentValue = valueField.getText();
		// condition to handle change only operator without operation when user use
		// standard command or set value when pressed =
		if (lastOperator.equals(mathOperator)) {
			if (actualMathOperator.equals("=")) {
				result = leftValue;
			} else {
				currentOperator = actualMathOperator;
				return;
			}
		}
		// try convert String to double - when error return 0;
		try {
			rightValue = Double.parseDouble(currentValue);
		} catch (Exception e) {
			rightValue = 0;
		}

		// switch to handle specified math operation
		switch (currentOperator) {
		case "+":
			result = leftValue + rightValue;
			operationHistory += " + " + Double.toString(rightValue);
			break;
		case "-":
			result = leftValue - rightValue;
			operationHistory += " - " + Double.toString(rightValue);
			break;
		case "*":
			result = leftValue * rightValue;
			operationHistory += " * " + Double.toString(rightValue);
			break;
		case "/":
			if (rightValue != 0) {
				result = leftValue / rightValue;
				operationHistory += " / " + Double.toString(rightValue);
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Nie wolno dzieliæ przez 0!", "B³¹d dzielenia",
						JOptionPane.ERROR_MESSAGE);
				setOtherOperator("C");
				return;
			}
		case "%":
			result = (leftValue * rightValue) / 100;
			operationHistory += " * " + Double.toString(rightValue) + "%";
			break;
		case "":
			result = rightValue;
			operationHistory += Double.toString(rightValue);
			break;
		}
		// set variable after do all calculation
		leftValue = result;
		rightValue = 0;

		// special condition when user want return result or want continue
		if (actualMathOperator.equals("=")) {
			valueField.setText(Double.toString(leftValue));
			operationHistory += " = " + Double.toString(leftValue);
			System.out.println(operationHistory);
			operationHistory = "";
			currentOperator = "";
			lastOperator = "";

		} else {
			valueField.setText("");
			currentOperator = actualMathOperator;
			lastOperator = mathOperator;
		}

	}

	// method to do others operation (backspace, clear all)
	private void setOtherOperator(String inputOperator) {
		switch (inputOperator) {
		case "C":
			// set all field to default
			valueField.setText("");
			leftValue = 0;
			rightValue = 0;
			currentOperator = "";
			operationHistory = "";
			break;
		case "<-":
			// substring last char from valueField
			String currentValue = valueField.getText();
			int valueLength = currentValue.length();
			if (valueLength > 0) {
				valueField.setText(currentValue.substring(0, valueLength - 1));
			}
			break;
		}
	}

}
