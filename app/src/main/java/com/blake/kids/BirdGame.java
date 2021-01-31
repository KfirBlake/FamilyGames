package com.blake.kids;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blake.kids.util.GameHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static android.widget.Toast.makeText;

public class BirdGame extends AppCompatActivity
{
    private List<String> birdsPicturesNameList = new ArrayList<>();
    private List<String> birdsNameSoundNameList = new LinkedList<>();
    private String birdNameSoundName;
    private String birdSoundGame = "birds_sounds_name/";

    //private ImageView pictureImageView;
    private ImageView bird1ImageView;
    private ImageView bird2ImageView;
    private ImageView bird3ImageView;
    private ImageView bird4ImageView;

    private Integer score = 0;
    TextView scoreTextView;
    ImageView[] scoreArrayImageList = new ImageView[5];

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(BirdGame.this);
        builder.setMessage("האם אתה בטוח שאתה רוצה לצאת?");
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                BirdGame.super.onBackPressed();
            }
        });
        builder.setNegativeButton("לא", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_game);

        //pictureImageView = findViewById(R.id.PicturePlaySoundAgain);
        bird1ImageView = findViewById(R.id.Bird1ImageView);
        bird2ImageView = findViewById(R.id.Bird2ImageView);
        bird3ImageView = findViewById(R.id.Bird3ImageView);
        bird4ImageView = findViewById(R.id.Bird4ImageView);

        scoreTextView = findViewById(R.id.ScoreTextView);
        scoreArrayImageList[0] = findViewById(R.id.Score1_ImageView);
        scoreArrayImageList[1] = findViewById(R.id.Score2_ImageView);
        scoreArrayImageList[2] = findViewById(R.id.Score3_ImageView);
        scoreArrayImageList[3] = findViewById(R.id.Score4_ImageView);
        scoreArrayImageList[4] = findViewById(R.id.Score5_ImageView);

        score = 0;
        GameHelper.getInstance().setScore(BirdGame.this, scoreTextView, scoreArrayImageList, score);

        initListsOfFiles();
        loadNewBirdSound();
    }

    private void initListsOfFiles()
    {
        birdsPicturesNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "birds_picture");
        birdsNameSoundNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "birds_sounds_name");
    }

    public void playSoundAgain(View view)
    {
        GameHelper.getInstance().playAssetSound(this, birdSoundGame + birdNameSoundName);
    }

    public void loadNewBirdSound()
    {
        birdNameSoundName = getRandomSoundFileName();
        GameHelper.getInstance().playAssetSound(this, birdSoundGame + birdNameSoundName);

        String birdFileNameOfCorrect = birdNameSoundName.substring(0, birdNameSoundName.indexOf(".")) + ".jpg";
        List<String> answers = getAnswersBirdPictureName(birdFileNameOfCorrect, birdNameSoundName.substring(0, birdNameSoundName.indexOf(".")));

        loadImageToImageView("birds_picture", answers.get(0), bird1ImageView);
        loadImageToImageView("birds_picture", answers.get(1), bird2ImageView);
        loadImageToImageView("birds_picture", answers.get(2), bird3ImageView);
        loadImageToImageView("birds_picture", answers.get(3), bird4ImageView);
        bird1ImageView.setClickable(true);
        bird2ImageView.setClickable(true);
        bird3ImageView.setClickable(true);
        bird4ImageView.setClickable(true);
    }

    private String getRandomSoundFileName()
    {
        Random random = new Random();
        int index = random.nextInt(birdsNameSoundNameList.size());
        String birdName = birdsNameSoundNameList.get(index);

        try
        {
            birdsNameSoundNameList.remove(index);
            if (birdsNameSoundNameList.isEmpty())
                birdsNameSoundNameList = GameHelper.getInstance().loadFromAssetsNameList(this, "birds_sounds_name");
        }
        catch (Exception e)
        {
            makeText(BirdGame.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return birdName;
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
            makeText(BirdGame.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private List<String> getAnswersBirdPictureName(String correctAnswer, String birdName)
    {
        List<String> answers = new ArrayList<>();
        answers.add(correctAnswer);

        for (int i = 0; i < 3; i++)
        {
            String name = correctAnswer;
            while (answers.contains(name) || name.contains(birdName))
            {
                name = getRandomBirdPictureFileName();
            }
            answers.add(name);
        }

        Collections.shuffle(answers);
        return answers;
    }

    private String getRandomBirdPictureFileName()
    {
        Random random = new Random();
        return birdsPicturesNameList.get(random.nextInt(birdsPicturesNameList.size()));
    }

    public void clickOnImageCheckResult(View view)
    {
        String nameOfBirdClicked = view.getContentDescription().toString();
        String birdName = nameOfBirdClicked.substring(0, nameOfBirdClicked.indexOf("."));
        if (birdNameSoundName.contains(birdName))
        {
            score = GameHelper.getInstance().correctAnswer(this, scoreTextView, scoreArrayImageList, score);
            loadNewBirdSound();
        }
        else
        {
            score = GameHelper.getInstance().wrongAnswer(this, scoreTextView, scoreArrayImageList, score);
            view.setClickable(false);
        }
    }

    public void onRadioButtonClickedBirdGame(View view)
    {
        if (((RadioButton) view).isChecked())
        {
            switch (view.getId())
            {
                case R.id.BirdGameByName:
                    birdSoundGame = "birds_sounds_name/";
                    break;
                case R.id.BirdGameBySound:
                    birdSoundGame = "birds_sounds_voice/";
                    break;
            }
            loadNewBirdSound();
        }
    }

}
