package com.andnick.fifteen;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Бондаренко on 01.10.2015.
 */
public class BarleyBreak {
    static int size;
    int[][] kurs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int score;
    private static final String TAG = "BARLEY ";
    public BarleyBreak(int size){
        this.size = size;
    }

    void sort(int size, MyButton[][] buttons) {
        for (int s = 0; s < size * size*size*size; s++) {
            int[] emptyIndx = new int[2];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (buttons[i][j].getText().toString() == "") {
                        emptyIndx[0] = i;
                        emptyIndx[1] = j;
                    }
                }
            }
            int cours = randomWithRange(0, 3);
            try {
                buttons[emptyIndx[0]][emptyIndx[1]].setText(buttons[emptyIndx[0] + kurs[cours][0]][emptyIndx[1] + kurs[cours][1]].getText());
                buttons[emptyIndx[0] + kurs[cours][0]][emptyIndx[1] + kurs[cours][1]].setText("");
            } catch (ArrayIndexOutOfBoundsException ex) {
                Log.d(TAG, "Catch");
            }
        }
    }


    void change(final int x, final int y, final MyButton[][] buttons, final Context context) {
        Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.but_anim_1);
        final Animation animation2 = AnimationUtils.loadAnimation(context, R.anim.but_anim_2);
        final int[] nI = buttons[x][y].emptyNeighbour;
        buttons[x][y].startAnimation(animation1);
        buttons[x + nI[0]][y + nI[1]].startAnimation(animation1);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                buttons[x + nI[0]][y + nI[1]].setText(buttons[x][y].getText().toString());
                buttons[x][y].setText("");
                buttons[x][y].startAnimation(animation2);
                buttons[x + nI[0]][y + nI[1]].startAnimation(animation2);
                if(endGame(buttons)){
                    context.startActivity(new Intent(context, Win.class));
                }
                setNeighbourForAll(buttons);
                GameAct.aBScore.setText(String.valueOf(score));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    void click(MyButton button, int i, int j, MyButton[][] buttons, Context context){
        if (button.isNeighbour) {
            change(i, j, buttons, context);
            score++;
        }

    }
    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    void setNeighbourForAll(MyButton[][] buttons) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setNeighbour(buttons);
            }
        }
    }
    boolean endGame(MyButton[][] buttons) {//Проверка на конец игры
        boolean isEnd=false;
        int number = 1;
        int end = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(buttons[i][j].getText() != "") {
                    if (buttons[i][j].getText() == String.valueOf(number)) {
                        number++;
                        end++;
                        Log.d(TAG, "END" + String.valueOf(number) + "  " +String.valueOf(end));
                    }
                    else end = 1;
                }else end = 1;
                if (end == size * size) {
                    isEnd = true;
                }
            }
        }
        return isEnd;
    }
}
