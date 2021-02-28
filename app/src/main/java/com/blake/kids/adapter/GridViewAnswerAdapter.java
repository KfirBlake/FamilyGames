package com.blake.kids.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.blake.kids.LetterQuizGame;

/**
 * Created by Kfir Blake on 14/02/2021.
 */
public class GridViewAnswerAdapter extends BaseAdapter
{
    private Context context;
    private LetterQuizGame letterQuizGame;
    private char[] answerCharArray;

    public GridViewAnswerAdapter(Context context, LetterQuizGame letterQuizGame, char[] answerCharArray)
    {
        this.context = context;
        this.letterQuizGame = letterQuizGame;
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        Button button;
        if (convertView == null)
        {
            button = new Button(context);
            button.setLayoutParams(new GridView.LayoutParams(90, 90));
            button.setPadding(8, 8, 8, 8);
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.GREEN);
            button.setText(String.valueOf(answerCharArray[position]));
            button.setTextSize(20);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    char letterToRemoveFromAnswer = answerCharArray[position]; // Get Char

                    for (int i = 0; i < letterQuizGame.suggestSource.size(); i++)
                    {
                        if (letterQuizGame.suggestSource.get(i).equals("empty_" + String.valueOf(letterToRemoveFromAnswer)))
                        {
                            letterQuizGame.user_answer[position] = Character.MIN_VALUE;
                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(context, letterQuizGame, letterQuizGame.user_answer);
                            letterQuizGame.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            letterQuizGame.suggestSource.set(i, String.valueOf(letterToRemoveFromAnswer));
                            letterQuizGame.suggestAdapter = new GridViewSuggestAdapter(context, letterQuizGame, letterQuizGame.suggestSource);
                            letterQuizGame.gridViewSuggest.setAdapter(letterQuizGame.suggestAdapter);
                            letterQuizGame.suggestAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            });
        }
        else
            button = (Button) convertView;

        return button;
    }
}
