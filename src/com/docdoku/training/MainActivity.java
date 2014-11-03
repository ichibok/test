package com.docdoku.training;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "MAIN";
    private TextView textViewMessage;
    private ProgressBar progressBar1;
    private Button button1;
    private EditText usernameText;
    private EditText passwordText;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "On Create");
        
        textViewMessage = (TextView) findViewById(R.id.textView1);
        button1 = (Button) findViewById(R.id.button1);
        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar1.setMax(Constant.MAX_PROGRESS);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    
   public void onClickButton(View v){
	   Log.d(TAG, "Button clicked");
	   
	   Toast.makeText(this, "message", Toast.LENGTH_LONG).show();
	   
	   String username = usernameText.getText().toString();
	   String password = passwordText.getText().toString();
	   button1.setEnabled(false);
	   
	   //Vérifier la chaîne de charactère
	   if(TextUtils.isEmpty(username)){
		   usernameText.setError("Pas de message vide");
	   } else{
		   button1.setEnabled(false);
		   new ConnexionTask().execute(username, password);
		   
	   }
	   
   }
   
   public class ConnexionTask extends AsyncTask<String, Integer, Boolean>{
	   
	@Override
	protected void onProgressUpdate(Integer... values) {
		// Mettre à jour le progrès
		super.onProgressUpdate(values);
		progressBar1.setProgress(values[0]);
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		for(int i=0; i<Constant.MAX_PROGRESS; i++){
			SystemClock.sleep(500);
			publishProgress(i+1);
		}
		// Vérifier le login et le mot de passe entréé par l'utilisateur
		return params[0].equals(params[1]);
	}
	   
	
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result){
			//Lancement d'une nouvelle activité avec l'explicit Intent
			Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
			intent.putExtra(Constant.KEY_TITLE, usernameText.getText().toString());
			startActivity(intent);
			
			
			//Lancement d'une activité dans un autre class
			/*
			Intent i = new Intent();
			i.setClassName("com.docdoku.test", "com.docdoku.test.MainActivity");
			startActivity(i);
			*/
			
			/*
			//Lancement d'un Intent pour appeler
			Intent intentCall = new Intent(Intent.ACTION_CALL);
			intentCall.setData(Uri.parse("tel:0231958374"));
			startActivity(intentCall);
			*/
			
		}else{
			((TextView) findViewById(R.id.textView1)).setText("Login error");
		}
		button1.setEnabled(true);
		
	};
   }

}
