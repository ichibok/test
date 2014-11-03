package com.docdoku.training;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class CreateInvoiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_invoice);

	}

	public void onCreateInvoice(View v){
		
		Log.i("cycle", "on create invoice");
		//Récupèrer le contenu de champ
		EditText editTextNumber = (EditText) findViewById(R.id.editTextNumber);
		EditText editTextSupplier = (EditText) findViewById(R.id.editTextSupplier);
		EditText editTextAmount = (EditText) findViewById(R.id.editTextAmount);
		
		Intent intent = new Intent();
		intent.putExtra(Constant.KEY_NUMBER, editTextNumber.getText().toString());
		intent.putExtra(Constant.KEY_SUPPLIER, editTextSupplier.getText().toString());
		intent.putExtra(Constant.KEY_AMOUNT, editTextAmount.getText().toString());
		setResult(RESULT_OK, intent);
		
		Log.i("data", "number = "+editTextNumber.getText().toString()+" supplier= "+ editTextSupplier.getText().toString()+" amount= "+ editTextAmount.getText().toString());
		
		finish();
	}
	
	//Si jamais l'utilisateur annule où faire le bouton Back
	
	@Override
	public void onBackPressed() {
		// le super de onBackPressed() fait finish(), donc on le fait pas
		setResult(RESULT_CANCELED);
		finish();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_invoice, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
