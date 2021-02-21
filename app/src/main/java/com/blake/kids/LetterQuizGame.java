package com.blake.kids;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;

import com.blake.kids.adapter.GridViewAnswerAdapter;
import com.blake.kids.adapter.GridViewSuggestAdapter;
import com.blake.kids.db.DBHelperLetterQuiz;
import com.blake.kids.model.QuestionInfo;
import com.blake.kids.util.GameHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.widget.Toast.makeText;

public class LetterQuizGame extends AppCompatActivity
{
    public Button btnSubmit;
    public GridView gridViewAnswer, gridViewSuggest;
    public ImageView imgViewQuestion;
    public TextView textViewQuestion;

    public DBHelperLetterQuiz dbHelperLetterQuiz;
    ArrayList<QuestionInfo> questionInfoArrayList;

    public List<String> suggestSource = new ArrayList<>();
    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;

    public char[] theCorrectAnswerCharArray;
    String correctAnswer;

    public char[] user_answer;
    public String[] lettersArrayAll = {   "א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט", "י",
            "כ", "ל", "מ", "נ", "ס", "ע", "פ", "צ", "ק", "ר", "ש", "ת",
            "ך", "ם", "ן", "ף", "ץ"};


    Boolean playByImage = true;
    int NumberOfLettersInSuggestTable = 15;
    int QuizLevel = 4;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.letterquiz_menu, menu);
        MenuCompat.setGroupDividerEnabled(menu, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_letterQuizPlayByImage:
                playByImage = true;
                item.setChecked(true);
                imgViewQuestion.setVisibility(View.VISIBLE);
                textViewQuestion.setText("");
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizPlayByQuestion:
                playByImage = false;
                item.setChecked(true);
                imgViewQuestion.setVisibility(View.INVISIBLE);
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizNumberOfLeeterInSuggest1:
                NumberOfLettersInSuggestTable = 10;
                item.setChecked(true);
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizNumberOfLeeterInSuggest2:
                NumberOfLettersInSuggestTable = 15;
                item.setChecked(true);
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizNumberOfLeeterInSuggest3:
                NumberOfLettersInSuggestTable = 20;
                item.setChecked(true);
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizLevel1:
                QuizLevel = 1;
                item.setChecked(true);
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizLevel2:
                QuizLevel = 2;
                item.setChecked(true);
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizLevel3:
                QuizLevel = 3;
                item.setChecked(true);
                loadNewPicture();
                return true;

            case R.id.menu_letterQuizLevel4:
                QuizLevel = 4;
                item.setChecked(true);
                loadNewPicture();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

            @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latter_quiz_game);

        dbHelperLetterQuiz = new DBHelperLetterQuiz(this);
        questionInfoArrayList = dbHelperLetterQuiz.createAllQuestions();
        initView();

    }

    private void initView()
    {
        gridViewAnswer= (GridView) findViewById(R.id.LetterQuizAnswers);
        gridViewSuggest = (GridView) findViewById(R.id.LetterQuizSuggests);
        imgViewQuestion = (ImageView) findViewById(R.id.LetterQuizPicture);
        textViewQuestion = (TextView) requireViewById(R.id.LetterQuizQuestion);

        loadNewPicture();

        btnSubmit = (Button)findViewById(R.id.LetterQuizSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String result = String.valueOf(user_answer);
                if(result.equals(correctAnswer))
                {
                    GameHelper.getInstance().playSound(LetterQuizGame.this, GameHelper.getInstance().getSuccessSound());
                    loadNewPicture();
                }
                else
                {
                    GameHelper.getInstance().playSound(LetterQuizGame.this, GameHelper.getInstance().getFailedSound());
                }
            }
        });
    }

    private void loadNewPicture()
    {
        Random random = new Random();

        QuestionInfo questionInfo = getRandomQuestionInfo();
        try
        {
            if (playByImage)
            {
                String fullPath = questionInfo.getAssetsName() + "/" + questionInfo.getFileName();
                imgViewQuestion.setImageDrawable(Drawable.createFromStream(getAssets().open(fullPath), fullPath));
            }
            else
            {
                textViewQuestion.setText(questionInfo.getQuestion());
            }
        }
        catch (Exception e)
        {
        }

        correctAnswer = questionInfo.getAnswer();
        theCorrectAnswerCharArray = correctAnswer.toCharArray();
        user_answer = new char[theCorrectAnswerCharArray.length];

        //Add answer char to suggest list
        suggestSource.clear();
        for(char item : theCorrectAnswerCharArray)
        {
            // Add correct letters of image
            suggestSource.add(String.valueOf(item));
        }

        // Add Random letters to fill the rest of suggest list
        for (int i = theCorrectAnswerCharArray.length; i < NumberOfLettersInSuggestTable; i++)
        {
            suggestSource.add(lettersArrayAll[random.nextInt(lettersArrayAll.length)]);
        }

        Collections.shuffle(suggestSource);

        answerAdapter = new GridViewAnswerAdapter(this, createEmptyArrayLetter());
        gridViewAnswer.setAdapter(answerAdapter);
        answerAdapter.notifyDataSetChanged();

        suggestAdapter = new GridViewSuggestAdapter(this, this, suggestSource);
        gridViewSuggest.setAdapter(suggestAdapter);
        suggestAdapter.notifyDataSetChanged();
    }

    private QuestionInfo getRandomQuestionInfo()
    {
        Random random = new Random();
        int imageSelected = questionInfoArrayList.size() - 1;
        QuestionInfo questionInfo = questionInfoArrayList.get(random.nextInt(imageSelected));

        try
        {
            questionInfoArrayList.remove(questionInfo);
            if (questionInfoArrayList.isEmpty())
                questionInfoArrayList = dbHelperLetterQuiz.createAllQuestions();
        }
        catch (Exception e)
        {
            makeText(LetterQuizGame.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return questionInfo;
    }

    private char[] createEmptyArrayLetter()
    {
        char result[] = new char[theCorrectAnswerCharArray.length];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = ' ';
        }
        return result;
    }
}