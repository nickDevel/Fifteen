package com.andnick.fifteen;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

/**
 * Created by Бондаренко on 30.09.2015.
 */
public class MyButton extends Button{
    int[] myIndexes = new int[2];
    boolean isNeighbour;//Если true-то эта кнопка сосед с пустой
    public int[] emptyNeighbour = {0, 0};//Это значения на которые нужно увеличить индекс массива кнопки для получения пустой
    int[][] kurs = {{1,0},{-1,0},{0,1},{0,-1}};
    float textSize = 40;
    private static final String TAG = "MyLog";

    public MyButton(Context context, final int i, final int j, final String text) {
        super(context);
        myIndexes[0] = i;
        myIndexes[1] = j;
        isNeighbour = false;
        this.setText(text);
        this.setBackgroundResource(R.drawable.button);
        this.setTextSize(textSize);

    }

    void setNeighbour(MyButton[][] m) {//Устанавливаем значение для isNeighbour
        isNeighbour=false;
        for (int i =0; i<4; i++){
            try {if (m[myIndexes[0] + kurs[i][0]][myIndexes[1]+kurs[i][1]].getText().toString() == "") {
                emptyNeighbour[0] = kurs[i][0];
                emptyNeighbour[1] = kurs[i][1];
                isNeighbour=true;}
            } catch (ArrayIndexOutOfBoundsException exc) {}
        }
        Log.d(TAG, String.valueOf(isNeighbour));
    }
}
