/**
 * DigitButton.java
 * Author: Shinya Katayama
 * Copyright (C) 2014 Shintani & Ozono Lab. All Rights Reserved.
 */
package kpro.calculator;

import javax.swing.JButton;

public class DigitButton extends JButton {

	private static final long serialVersionUID = 1L;

	int digit;

	DigitButton(int digit) {
		this.digit = digit;
		this.setText(Integer.toString(digit));
	}
	
	int getDigit() {
		return this.digit;
	}
}
