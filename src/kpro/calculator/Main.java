/**
 * Main.java
 * Author: Shinya Katayama
 * Copyright (C) 2014 Shintani & Ozono Lab. All Rights Reserved.
 */
package kpro.calculator;

public class Main {
	// 実行すると main 関数が最初に走る．
	public static void main(String args[]) {
		Calculator calculator = new Calculator();
		CalculatorFrame frame = new CalculatorFrame(calculator);
		calculator.addObserver(frame);
		frame.setVisible(true);
	}
}
