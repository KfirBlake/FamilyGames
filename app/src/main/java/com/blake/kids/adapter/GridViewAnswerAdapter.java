package com.blake.kids.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by Kfir Blake on 14/02/2021.
 */
public class GridViewAnswerAdapter extends BaseAdapter
{
    private Context context;
    private char[] answerCharArray;

    public GridViewAnswerAdapter(Context context, char[] answerCharArray)
    {
        this.context = context;
        this.answerCharArray = answerCharArray;
    }


    @Override
    public int getCount()
    {
        return answerCharArray.length;
    }

    @Override
    public Object getItem(int position)
    {
        return answerCharArray[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Button button;
        if (convertView == null)
        {
            button = new Button(context);
            button.setLayoutParams(new GridView.LayoutParams(90, 90));
            button.setPadding(8,8,8, 8);
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.GREEN);
            button.setText(String.valueOf(answerCharArray[position]));
            button.setTextSize(20);
        }
        else
            button = (Button)convertView;

        return button;
    }
}
