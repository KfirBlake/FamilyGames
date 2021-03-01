package com.blake.kids;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blake.kids.adapter.GridViewImageAdapter;
import com.blake.kids.util.GameHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CountGame extends AppCompatActivity
{
    private ImageView number1ImageView;
    private ImageView number2ImageView;
    private ImageView number3ImageView;
    private ImageView number4ImageView;

    private Integer score = 0;
    TextView scoreTextView;
    ImageView[] scoreArrayImageList = new ImageView[5];

    Integer result = 0;
    public String imageFullPath;
    private List<String> picturesImageNameList = new LinkedList<>();

    public GridViewImageAdapter gridViewImageAdapter;
    public GridView gridViewImageNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_game);

        initView();
        newGame();
    }

    private void initView()
    {
        gridViewImageNumbers = findViewById(R.id.CountGridView);
        number2ImageView = findViewById(R.id.CountImageView2);
        number1ImageView = findViewById(R.id.CountImageView1);
        number3ImageView = findViewById(R.id.CountImageView3);
        number4ImageView = findViewById(R.id.CountImageView4);

        scoreTextView = findViewById(R.id.ScoreTextView);
        scoreArrayImageList[0] = findViewById(R.id.Score1_ImageView);
        scoreArrayImageList[1] = findViewById(R.id.Score2_ImageView);
        scoreArrayImageList[2] = findViewById(R.id.Score3_ImageView);
        scoreArrayImageList[3] = findViewById(R.id.Score4_ImageView);
        scoreArrayImageList[4] = findViewById(R.id.Score5_ImageView);

        picturesImageNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "totem_animals_pictures");
        GameHelper.getInstance().setScore(CountGame.this, scoreTextView, scoreArrayImageList, score);
    }

    public void newGame()
    {
        // Loading Number pictures to Answers
        Random random = new Random();
        result = random.nextInt(10) + 1;
        List<Integer> answers = getAnswersNumbers(result);
        setImageNumber(number1ImageView, answers.get(0));
        setImageNumber(number2ImageView, answers.get(1));
        setImageNumber(number3ImageView, answers.get(2));
        setImageNumber(number4ImageView, answers.get(3));


        imageFullPath = "totem_animals_pictures/" + getRandomPictureFileName();
        List<String> listOfNumber = new ArrayList<>();
        for (int i = 0; i < result ; i++)
        {
            listOfNumber.add(String.valueOf(i));
        }
        gridViewImageAdapter = new GridViewImageAdapter(this, this, listOfNumber);
        gridViewImageNumbers.setAdapter(gridViewImageAdapter);
        gridViewImageAdapter.notifyDataSetChanged();
    }

    private String getRandomPictureFileName()
    {
        Random random = new Random();
        int index = random.nextInt(picturesImageNameList.size());
        String pictureName = picturesImageNameList.get(index);

        try
        {
            picturesImageNameList.remove(index);
            if (picturesImageNameList.isEmpty())
                picturesImageNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "totem_animals_pictures");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return pictureName;
    }

    private void setImageNumber(ImageView image, Integer number)
    {
        image.setImageResource(GameHelper.getInstance().getImageByNumber(number));
        image.setClickable(true);
        image.setContentDescription(number.toString());

    }
    private List<Integer> getAnswersNumbers(Integer correctAnswer)
    {
        Random random = new Random();
        List<Integer> answers = new ArrayList<>();
        answers.add(correctAnswer);

        for (int i = 0; i < 3; i++)
        {
            Integer num = correctAnswer;
            while (answers.contains(num))
            {
                num = random.nextInt(9) + 1;
            }
            answers.add(num);
        }

        Collections.shuffle(answers);
        return answers;
    }


    public void clickOnImageCheckResult(View view)
    {
        String numberClicked = view.getContentDescription().toString();
        if (numberClicked.equals(result.toString()))
        {
            score = GameHelper.getInstance().correctAnswer(this, scoreTextView, scoreArrayImageList, score);
            newGame();
        }
        else
        {
            score = GameHelper.getInstance().wrongAnswer(this, scoreTextView, scoreArrayImageList, score);
            view.setClickable(false);
        }
    }
}