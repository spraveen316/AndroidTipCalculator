package com.project.androidtipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class TipCalculateActivity extends Activity {
	
	Button ten;
	Button fifteen;
	Button twenty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculate);
	}

	public void onButtonClick(View v) {
		EditText etBillAmountWidget = (EditText)findViewById(R.id.etBillAmount);
		Editable etBillEditable = etBillAmountWidget.getText();
		final EditText etTip = (EditText) findViewById(R.id.eTTipAmount);
		
		InputMethodManager imm = (InputMethodManager)getSystemService(
			    Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(etBillAmountWidget, 0);
		
		if (etBillEditable.toString() == null || etBillEditable.toString().equals("")) {
			etBillAmountWidget.setError(getResources().getString(R.string.errorNotANumber));
			etTip.setKeyListener(null);
			etTip.setText("");
			return;
		}
		
		double billAmount = 0.0;
		
		billAmount = Double.valueOf(etBillEditable.toString()).doubleValue();
		
		if (billAmount <= 0) {
			etBillAmountWidget.setError(getResources().getString(R.string.errorLessThanZero));
			etTip.setKeyListener(null);
			etTip.setText("");
			return;
		}
		
		String tip = "0.0";
		
		switch (v.getId()) {
		case R.id.b10:
			tip = calculateTipAmount(billAmount, 10);
			break;
		case R.id.b15:
			tip = calculateTipAmount(billAmount, 15);
			break;
		case R.id.b20:
			tip = calculateTipAmount(billAmount, 20);
			break;
		default:
			etBillAmountWidget.setError(getResources().getString(R.string.errorInternal));
			etTip.setKeyListener(null);
			etTip.setText("");
			return;
		}
		
		StringBuffer result = new StringBuffer(getResources().getString(R.string.dollar)).append(tip);
		etTip.setText(result);
		etTip.setKeyListener(null);
		
	}
	
	private String calculateTipAmount(double billAmount, int tipPercent) {
		DecimalFormat df = new DecimalFormat(getResources().getString(R.string.decimalFormat));
		return df.format((billAmount * tipPercent) / 100);
	}

}
