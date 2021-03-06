package com.blake.kids;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.blake.kids.util.GameHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static android.widget.Toast.makeText;

public class LetterGame extends AppCompatActivity
{
    public static final String SAVE_SCORE = "SCORE";

    private List<String> lettersImageNameList = new ArrayList<>();
    private List<String> picturesImageNameList = new LinkedList<>();
    private String pictureIndex;
    private boolean boolLetterFirst = true;

    private ImageView pictureImageView;
    private ImageView letter1ImageView;
    private ImageView letter2ImageView;
    private ImageView letter3ImageView;
    private ImageView letter4ImageView;

    private Integer score = 0;
    TextView scoreTextView;
    ImageView[] scoreArrayImageList = new ImageView[5];
    TextView timeLeftTextView;
    TextView titleTextView;
    CountDownTimer countDownTimer;
    int timeInterval;


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (GameHelper.getInstance().getTimeOutFlag())
            countDownTimer.cancel();
    }


    @Override
    public void onBackPressed()
    {
        if (GameHelper.getInstance().getTimeOutFlag())
            countDownTimer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(LetterGame.this);
        //builder.setTitle("אתה בטוח שאתה רוצה לצאת");
        builder.setMessage("האם אתה בטוח שאתה רוצה לצאת?");
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                LetterGame.super.onBackPressed();
                if (GameHelper.getInstance().getTimeOutFlag())
                    countDownTimer.cancel();
            }
        });
        builder.setNegativeButton("לא", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if (GameHelper.getInstance().getTimeOutFlag())
                    countDownTimer.start();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_SCORE, score);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt(SAVE_SCORE, 0);
        try
        {
            scoreTextView.setText(String.valueOf(score));
        }
        catch (Exception e)
        {
            makeText(LetterGame.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_game);

        pictureImageView = findViewById(R.id.PictureToFindFirstLetterImageView);
        letter1ImageView = findViewById(R.id.Letter1ImageView);
        letter2ImageView = findViewById(R.id.Letter2ImageView);
        letter3ImageView = findViewById(R.id.Letter3ImageView);
        letter4ImageView = findViewById(R.id.Letter4ImageView);

        scoreTextView = findViewById(R.id.ScoreTextView);
        scoreArrayImageList[0] = findViewById(R.id.Score1_ImageView);
        scoreArrayImageList[1] = findViewById(R.id.Score2_ImageView);
        scoreArrayImageList[2] = findViewById(R.id.Score3_ImageView);
        scoreArrayImageList[3] = findViewById(R.id.Score4_ImageView);
        scoreArrayImageList[4] = findViewById(R.id.Score5_ImageView);
        timeLeftTextView = findViewById(R.id.TimeLeftTextView);
        titleTextView = findViewById(R.id.TitleTextView);

        GameHelper.getInstance().setScore(LetterGame.this, scoreTextView, scoreArrayImageList, score);

        if (GameHelper.getInstance().getTimeOutFlag())
        {
            countDownTimer = new CountDownTimer(GameHelper.getInstance().getNumberOfSecondForTimeout() * 1000, 1000)
            {
                public void onTick(long millisUntilFinished)
                {

                    //here you can have your logic to set text to timeText
                    timeLeftTextView.setText(String.valueOf(timeInterval));
                    if (timeInterval <= 4)
                    {
                        timeLeftTextView.setTextColor(Color.RED);
                        timeLeftTextView.setTypeface(null, Typeface.BOLD);
                    }
                    else if (timeInterval < 10)
                    {
                        timeLeftTextView.setTextColor(ContextCompat.getColor(LetterGame.this, R.color.colorOrange));
                        timeLeftTextView.setTypeface(null, Typeface.NORMAL);
                    }
                    else
                    {
                        timeLeftTextView.setTextColor(Color.GREEN);
                        timeLeftTextView.setTypeface(null, Typeface.NORMAL);
                    }
                    timeInterval--;
                }

                public void onFinish()
                {
                    if (GameHelper.getInstance().getNumberOfSecondForTimeout() > 0)
                    {
                        timeLeftTextView.setText("הזמן נגמר!!");
                        GameHelper.getInstance().timeOut(LetterGame.this, "הזמן עבר !", R.drawable.boom);
                    }
                }
            }.start();
        }

        initListsOfFiles();
        loadNewPicture();
    }

    private void initListsOfFiles()
    {
        lettersImageNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "letters");
        picturesImageNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "pictures");
    }

    public void loadNewPicture()
    {
        String pictureFileName = getRandomPictureFileName();
        loadImageToImageView("pictures", pictureFileName, pictureImageView);

        //pictureIndex = pictureFileName.substring(pictureFileName.indexOf("_") + 1, pictureFileName.indexOf("."));
        String[] letterNumber = pictureFileName.split("_");
        if (boolLetterFirst)
        {
            pictureIndex = letterNumber[1];
        }
        else
        {
            pictureIndex = letterNumber[2].substring(0, letterNumber[2].indexOf("."));
        }
        String letterFileNameOfCorrect = "letter_" + pictureIndex + ".jpg";
        List<String> answers = getAnswersLettersName(letterFileNameOfCorrect);

        loadImageToImageView("letters", answers.get(0), letter1ImageView);
        loadImageToImageView("letters", answers.get(1), letter2ImageView);
        loadImageToImageView("letters", answers.get(2), letter3ImageView);
        loadImageToImageView("letters", answers.get(3), letter4ImageView);
        letter1ImageView.setClickable(true);
        letter2ImageView.setClickable(true);
        letter3ImageView.setClickable(true);
        letter4ImageView.setClickable(true);
        timeInterval = GameHelper.getInstance().getNumberOfSecondForTimeout();
        if (GameHelper.getInstance().getTimeOutFlag())
        {
            countDownTimer.cancel();
            countDownTimer.start();
        }
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
                picturesImageNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "pictures");
        }
        catch (Exception e)
        {
            makeText(LetterGame.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return pictureName;
    }

    private void loadImageToImageView(String assetName, String imageName, ImageView imageView)
    {
        String imagePath = assetName + "/" + imageName;
        try
        {
            imageView.setImageDrawable(Drawable.createFromStream(getAssets().open(imagePath), imagePath));
            imageView.setContentDescription(imageName);
        }
        catch (IOException e)
        {
            makeText(LetterGame.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private List<String> getAnswersLettersName(String correctAnswer)
    {
        List<String> answers = new ArrayList<>();
        answers.add(correctAnswer);

        for (int i = 0; i < 3; i++)
        {
            String name = correctAnswer;
            while (answers.contains(name))
            {
                name = getRandomLetterFileName();
            }
            answers.add(name);
        }

        Collections.shuffle(answers);
        return answers;
    }

    private String getRandomLetterFileName()
    {
        Random random = new Random();
        return lettersImageNameList.get(random.nextInt(lettersImageNameList.size()));
    }

    public void clickOnImageCheckResult(View view)
    {
        String nameOfLetterClicked = view.getContentDescription().toString();
        String letterIndex = nameOfLetterClicked.substring(nameOfLetterClicked.indexOf("_") + 1, nameOfLetterClicked.indexOf("."));
        if (letterIndex.equals(pictureIndex))
        {
            score = GameHelper.getInstance().correctAnswer(this, scoreTextView, scoreArrayImageList, score);
            loadNewPicture();
        }
        else
        {
            score = GameHelper.getInstance().wrongAnswer(this, scoreTextView, scoreArrayImageList, score);
            view.setClickable(false);
        }
    }

    public void onRadioButtonClickedLetters(View view)
    {
        if (((RadioButton) view).isChecked())
        {
            switch (view.getId())
            {
                case R.id.LetterFindFirst:
                    boolLetterFirst = true;
                    titleTextView.setText("באיזה אות מתחיל");
                    break;
                case R.id.LetterFindLast:
                    boolLetterFirst = false;
                    titleTextView.setText("באיזה אות מסתיים");
                    break;
            }
            loadNewPicture();
        }
    }

}
