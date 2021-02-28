package com.blake.kids.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.blake.kids.LetterQuizGame;

import java.util.List;

/**
 * Created by Kfir Blake on 18/02/2021.
 */
public class GridViewSuggestAdapter extends BaseAdapter
{

    private Context context;
    private LetterQuizGame letterQuizGame;
    private List<String> suggestSource;

    public GridViewSuggestAdapter(Context context, LetterQuizGame letterQuizGame, List<String> suggestSource)
    {
        this.context = context;
        this.letterQuizGame = letterQuizGame;
        this.suggestSource = suggestSource;
    }

    @Override
    public int getCount()
    {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position)
    {
        return suggestSource.get(position);
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
        if(convertView == null)
        {
            if (suggestSource.get(position).contains("empty"))
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(120, 120));
                button.setPadding(8,8,8, 8);
                button.setBackgroundColor(Color.DKGRAY);
            }
            else
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(120, 120));
                button.setPadding(16,16,16, 16);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.RED);
                button.setText(suggestSource.get(position));
                button.setTextSize(20);
                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        char letterToAddToAnswer = suggestSource.get(position).charAt(0);
                        Boolean addLetter = false;
                        for (int i = 0; i < letterQuizGame.user_answer.length; i++)
                        {
                            if(String.valueOf(letterQuizGame.user_answer[i]).trim().equals(""))
                            {
                                letterQuizGame.user_answer[i] = letterToAddToAnswer;
                                addLetter = true;
                                break;
                            }
                        }

                        if(addLetter)
                        {
                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(context, letterQuizGame, letterQuizGame.user_answer);
                            letterQuizGame.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            letterQuizGame.suggestSource.set(position, "empty_" + String.valueOf(letterToAddToAnswer));
                            letterQuizGame.suggestAdapter = new GridViewSuggestAdapter(context, letterQuizGame, letterQuizGame.suggestSource);
                            letterQuizGame.gridViewSuggest.setAdapter(letterQuizGame.suggestAdapter);
                            letterQuizGame.suggestAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }
        else
            button = (Button)convertView;

        return button;
    }
}
