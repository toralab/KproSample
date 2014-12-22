/**
 * Calculator.java
 * Author: Shinya Katayama
 * Copyright (C) 2014 Shintani & Ozono Lab. All Rights Reserved.
 */
package kpro.calculator;

import java.util.Observable;

// Observable を継承することで，変更を GUI に通知することができる
public class Calculator extends Observable {
	int buffer; // 電卓に表示する数字
	int result; // 確定した計算結果
	OperatorType operatorType; // 選択されている演算子
	
	// コンストラクタ．インスタンスが生成される時に呼び出される．
	Calculator() {
		this.clear();
	}
	
	public void clear() {
		this.result = this.buffer = 0;
		this.operatorType = OperatorType.ADD; // デフォルトは 0 に何かを足す処理とみなす
		this.setChanged(); // buffer が変更されたことをマーク
		this.notifyObservers(this.buffer); // GUI へ通知
	}
	
	public void appendDigit(int digit) {
		this.buffer = this.buffer * 10 + digit;
		this.setChanged(); // buffer が変更されたことをマーク
		this.notifyObservers(this.buffer); // GUI へ通知
	}
	
	public void setOperator(OperatorType type) {
		// 前のバッファを確定して計算しておく
		if (this.buffer != 0) {
			this.calculate();
		}
		
		this.operatorType = type;
	}
	
	// buffer に入った数字を計算して結果を格納する
	public void calculate() {
		switch (this.operatorType) {
		case ADD:
			this.result += this.buffer;
			break;
		case SUB:
			this.result -= this.buffer;
			break;
		default:
			break;
		}
		this.buffer = this.result;
		this.setChanged(); // buffer が変更されたことをマーク
		this.notifyObservers(this.buffer); // GUI へ通知
		
		this.buffer = 0; // ここで 0 にすると，次の数字入力でバッファが初期化される
	}
}
