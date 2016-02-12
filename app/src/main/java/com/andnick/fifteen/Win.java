package com.andnick.fifteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Win extends AppCompatActivity {
    TextView score, dbscore, sizeGame;
    Button newGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        DBRecords reckords = new DBRecords(this);
        score = (TextView) findViewById(R.id.score);
        sizeGame = (TextView) findViewById(R.id.sizeGame);
        dbscore = (TextView) findViewById(R.id.dbscore);
        newGame = (Button) findViewById(R.id.newGame);
        sizeGame.setText("Игра "+BarleyBreak.size+"X"+BarleyBreak.size);
        reckords.addScore(String.valueOf(BarleyBreak.score));
        score.setText(String.valueOf(BarleyBreak.score));
        dbscore.setText(reckords.getScore());


        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Win.this, Menufif.class));

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_win, menu);
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
