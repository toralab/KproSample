/**
 * CalculatorFrame.java
 * Author: Shinya Katayama
 * Copyright (C) 2014 Shintani & Ozono Lab. All Rights Reserved.
 */
package kpro.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalculatorFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	
	Calculator calculator;
	JLabel buffer;
	
	CalculatorFrame(Calculator calculator) {
		this.calculator = calculator;
		
		this.buffer = this.createBuffer(); 
		JPanel digitButtons = this.createDigitButtonPanel();
		JPanel operatorButtons = this.createOperatorButtonPanel();
		
		// GUI 上にボタンなどを配置する
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(this.buffer, BorderLayout.NORTH);
		contentPane.add(digitButtons, BorderLayout.CENTER);
		contentPane.add(operatorButtons, BorderLayout.EAST);
		
		// 電卓の大きさの設定
		this.setSize(400, 300);
	}
	
	private JLabel createBuffer() {
		JLabel buffer = new JLabel("0", JLabel.RIGHT);
		buffer.setBackground(Color.WHITE);
		return buffer;
	}
	
	private JPanel createDigitButtonPanel() {
		int[] digits = {
				7, 8, 9,
				4, 5, 6,
				1, 2, 3,
				0
				};
		CalculatorListener listener = new DigitButtonListener(this.calculator);
		
		// 数字ボタンのインスタンスを生成し，パネル上にボタンを配置
		JPanel digitPanel = new JPanel(new GridLayout(4, 3));
		for(int i = 0; i < digits.length ; i++){
			JButton button = new DigitButton(digits[i]);
			button.addActionListener(listener);
			digitPanel.add(button);
		}
		
		return digitPanel;
	}
	
	private JPanel createOperatorButtonPanel() {
		final String[] buttonTexts = {"C", "+", "-", "="};
		final CommandType[] commands = {CommandType.CLEAR, CommandType.ADD, CommandType.SUB, CommandType.EQUAL};
		
		CalculatorListener listener = new CommandButtonListener(this.calculator);
		
		// 演算子ボタンのインスタンスを生成し，パネルに配置
		JPanel commandPanel = new JPanel(new GridLayout(4, 1));
		for(int i = 0; i < buttonTexts.length; i++) {
			JButton button = new CommandButton(commands[i], buttonTexts[i]);
			button.addActionListener(listener);
			commandPanel.add(button);
		}
		
		return commandPanel;
	}

	@Override
	// Calculator クラスからの更新通知が起きた時に呼ばれる
	public void update(Observable o, Object arg) {
		this.buffer.setText(Integer.toString((int)arg));
	}
}

// 電卓のアクションを受け取るための抽象クラス．継承必須．
abstract class CalculatorListener implements ActionListener {
	
	Calculator calculator;
	
	CalculatorListener(Calculator calculator) {
		this.calculator = calculator;
	}
}

// 電卓の数字ボタンが押された時の動作を定義するクラス．
class DigitButtonListener extends CalculatorListener {

	DigitButtonListener(Calculator calculator) {
		super(calculator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int input = ((DigitButton)e.getSource()).digit;
		this.calculator.appendDigit(input);
	}
}

// コマンドを実行する電卓のボタンが押された時の動作を定義するクラス．
class CommandButtonListener extends CalculatorListener {
	
	CommandButtonListener(Calculator calculator) {
		super(calculator);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CommandType command = ((CommandButton)e.getSource()).commandType;
		switch (command) {
		case ADD:
			this.calculator.setOperator(OperatorType.ADD);
			break;
		case SUB:
			this.calculator.setOperator(OperatorType.SUB);
			break;
		case CLEAR:
			this.calculator.clear();
			break;
		case EQUAL:
			this.calculator.calculate();
			break;
		}
	}
}
