/**
 * CommandButton.java
 * Author: Shinya Katayama
 * Copyright (C) 2014 Shintani & Ozono Lab. All Rights Reserved.
 */
package kpro.calculator;

import javax.swing.JButton;

enum CommandType {
	CLEAR, ADD, SUB, EQUAL
}

public class CommandButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	CommandType commandType;
	
	CommandButton(CommandType type, String text) {
		this.commandType = type;
		this.setText(text);
	}
	
	CommandType getCommandType() {
		return this.commandType;
	}
}
