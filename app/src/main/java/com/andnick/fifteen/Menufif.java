package com.andnick.fifteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class Menufif extends Activity {
    EditText etsize;
    int minSize = 1, maxSize = 8;
    private static final String TAG = "MenuLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menufif);
        etsize = (EditText) findViewById(R.id.etsize);
        final String st = "4";
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etsize.getText().length()==0){
                    Log.d(TAG, "If 1");
                    Log.d(TAG, String.valueOf(etsize.getText().toString()==""));
                    Intent intent = new Intent(Menufif.this, GameAct.class);
                    intent.putExtra("size", st);
                    startActivity(intent);
                }
                else if(minSize<Integer.parseInt(String.valueOf(etsize.getText()))&&
                        Integer.parseInt(String.valueOf(etsize.getText()))<maxSize){
                    Log.d(TAG, "If 2");
                    Intent intent = new Intent(Menufif.this, GameAct.class);
                    intent.putExtra("size", etsize.getText().toString());
                    startActivity(intent);
                }
                else{
                    Log.d(TAG, "Value" + String.valueOf(etsize.getText().toString()) + "is");
                Toast.makeText(Menufif.this, "Неее... от 2 до 7.", LENGTH_SHORT).show();}
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menufif, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}