package com.docdoku.training;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class WelcomeActivity extends Activity {

	private static final int REQUEST_CODE_CREATION = 0;
	private TableLayout tableLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String title = getIntent().getStringExtra(Constant.KEY_TITLE);
		setTitle(title);
		
		setContentView(R.layout.activity_welcome);
		tableLayout = (TableLayout) findViewById(R.id.tableLayout);
		if(savedInstanceState == null){
			Log.d("cycle", "onCreate");
		} else{
			Log.d("cycle", "onCreate bundle not null");
		}

	}
	
	//Quand le bouton "Creation" est créé
	public void onCreate(View v){
		Intent intent = new Intent(this, CreateInvoiceActivity.class);
		startActivityForResult(intent, REQUEST_CODE_CREATION);
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		//Regarder l'étape 5
		switch (requestCode){
		case REQUEST_CODE_CREATION:
			if (resultCode == RESULT_OK){
				/*
				//traitement nominal
				Toast.makeText(this, data.getStringExtra(Constant.KEY_SUPPLIER), Toast.LENGTH_LONG);
				*/
				addInvoice(data);
			}
			else {
				//cancel
				Toast.makeText(this, "Cancel Task", Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}
	

	private void addInvoice(Intent data) {
		
		TextView textViewDate = new TextView(this);
		TextView textViewNumber = new TextView(this);
		TextView textViewSupplier = new TextView(this);
		TextView textViewAmount = new TextView(this);
		
		//Récupèrer la date format 
		String strDate = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
		textViewDate.setText(strDate);
		textViewNumber.setText(data.getStringExtra(Constant.KEY_NUMBER));
		textViewSupplier.setText(data.getStringExtra(Constant.KEY_SUPPLIER));
		textViewAmount.setText(data.getStringExtra(Constant.KEY_AMOUNT));
		
		//Formater les numéros
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		textViewAmount.setText(formatter.format(Float.parseFloat(data.getStringExtra(Constant.KEY_AMOUNT))));		
		
		//Mettre la valeur dans la table row
		TableRow row =  new TableRow(this);
		row.addView(textViewDate);
		row.addView(textViewNumber);
		row.addView(textViewSupplier);
		row.addView(textViewAmount);
		
		//ajouter tous dans un row
		tableLayout.addView(row);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
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

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("cycle", "on start");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("cycle", "on resume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("cycle", "on pause");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("cycle", "on destroy");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("cycle", "onSaveInstante");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d("cycle", "onRestoreInstance");
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.d("cycle", "onNewIntent");
	}
}
