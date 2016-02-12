package com.andnick.fifteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameAct extends AppCompatActivity {

    TableLayout gameLay;
    TableRow newRow;
    static int size;
    int value = 1;
    static TextView aBScore;
    MyButton[][] buttons;
    private static final String TAG = "MyLog";
    BarleyBreak logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLay = (TableLayout) findViewById(R.id.gameLay);
        Intent intent = getIntent();
        aBScore = (TextView) findViewById(R.id.aBScore);
        aBScore.setText("");
        size = Integer.parseInt(intent.getStringExtra("size"));
        logic = new BarleyBreak(size);
        logic.score = 0;
        buttons = new MyButton[size][size];
        for (int i = 0; i < size; i++) {
            newRow = new TableRow(this);
            gameLay.addView(newRow, new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT, 1));
            for (int j = 0; j < size; j++) {
                if (value != size * size) {
                    newbutton(newRow, String.valueOf(value), i, j);
                } else {
                    newbutton(newRow, "", i, j);
                }
            }
        }
        logic.sort(size, buttons);
        logic.setNeighbourForAll(buttons);
    }
    void newbutton(TableRow row, String butVal, final int i, final int j) {
        final MyButton newbut = new MyButton(this, i, j, butVal);
        newbut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logic.click(newbut, i, j, buttons, GameAct.this);

            }
        });

        newbut.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1));
        final ViewGroup.MarginLayoutParams lpt = (ViewGroup.MarginLayoutParams) newbut.getLayoutParams();
        lpt.setMargins(2, 2, 2, 2);
        newbut.setLayoutParams(lpt);
        buttons[i][j] = newbut;
        row.addView(buttons[i][j]);
        value++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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