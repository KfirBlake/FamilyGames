package com.blake.kids;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blake.kids.util.GameHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static android.widget.Toast.makeText;

public class EnglishGame extends AppCompatActivity
{
    public static final String SAVE_SCORE = "SCORE";

    private List<String> wordsPicturesNameList = new ArrayList<>();
    private List<String> wordsSoundNameList = new LinkedList<>();
    private String wordSoundFileName;
    private String category = "english_animals";

    private ImageView english1ImageView;
    private ImageView english2ImageView;
    private ImageView english3ImageView;
    private ImageView english4ImageView;

    private Integer score = 0;
    TextView scoreTextView;
    ImageView[] scoreArrayImageList = new ImageView[5];

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(EnglishGame.this);
        builder.setMessage("האם אתה בטוח שאתה רוצה לצאת?");
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EnglishGame.super.onBackPressed();
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
            makeText(EnglishGame.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.english_catagory_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_english_catagory_animals:
                category = "english_animals";
                item.setChecked(true);
                initListsOfFiles();
                return true;

            case R.id.menu_english_catagory_colors:
                category = "english_colors";
                item.setChecked(true);
                initListsOfFiles();
                return true;

            case R.id.menu_english_catagory_numbers:
                category = "english_numbers";
                item.setChecked(true);
                initListsOfFiles();
                return true;

            case R.id.menu_english_catagory_food:
                category = "english_food";
                item.setChecked(true);
                initListsOfFiles();
                return true;
            case R.id.menu_english_catagory_nature:
                category = "english_nature";
                item.setChecked(true);
                initListsOfFiles();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_game);

        english1ImageView = findViewById(R.id.English1ImageView);
        english2ImageView = findViewById(R.id.English2ImageView);
        english3ImageView = findViewById(R.id.English3ImageView);
        english4ImageView = findViewById(R.id.English4ImageView);

        scoreTextView = findViewById(R.id.ScoreTextView);
        scoreArrayImageList[0] = findViewById(R.id.Score1_ImageView);
        scoreArrayImageList[1] = findViewById(R.id.Score2_ImageView);
        scoreArrayImageList[2] = findViewById(R.id.Score3_ImageView);
        scoreArrayImageList[3] = findViewById(R.id.Score4_ImageView);
        scoreArrayImageList[4] = findViewById(R.id.Score5_ImageView);

        score = 0;
        GameHelper.getInstance().setScore(EnglishGame.this, scoreTextView, scoreArrayImageList, score);

        initListsOfFiles();
    }

    private void initListsOfFiles()
    {
        wordsPicturesNameList = GameHelper.getInstance().loadFromAssetsNameList(this, category + "_pictures");
        wordsSoundNameList = GameHelper.getInstance().loadFromAssetsNameList(this, category + "_sounds");
        loadNewWordSound();
    }

    public void playSoundAgain(View view)
    {
        GameHelper.getInstance().playAssetSound(this, category + "_sounds/" + wordSoundFileName);
    }

    public void loadNewWordSound()
    {
        wordSoundFileName = getRandomSoundFileName();
        GameHelper.getInstance().playAssetSound(this, category + "_sounds/" + wordSoundFileName);

        String wordFileNameOfCorrect = wordSoundFileName.substring(0, wordSoundFileName.indexOf(".")) + ".png";
        List<String> answers = getAnswersWordPictureName(wordFileNameOfCorrect, wordSoundFileName.substring(0, wordSoundFileName.indexOf(".")));

        loadImageToImageView(category + "_pictures", answers.get(0), english1ImageView);
        loadImageToImageView(category + "_pictures", answers.get(1), english2ImageView);
        loadImageToImageView(category + "_pictures", answers.get(2), english3ImageView);
        loadImageToImageView(category + "_pictures", answers.get(3), english4ImageView);
        english1ImageView.setClickable(true);
        english2ImageView.setClickable(true);
        english3ImageView.setClickable(true);
        english4ImageView.setClickable(true);
    }

    private String getRandomSoundFileName()
    {
        Random random = new Random();
        int index = random.nextInt(wordsSoundNameList.size());
        String wordFileName = wordsSoundNameList.get(index);

        try
        {
            wordsSoundNameList.remove(index);
            if (wordsSoundNameList.isEmpty())
                wordsSoundNameList = GameHelper.getInstance().loadFromAssetsNameList(this, category + "_sounds");
        }
        catch (Exception e)
        {
            makeText(EnglishGame.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return wordFileName;
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
            makeText(EnglishGame.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private List<String> getAnswersWordPictureName(String correctAnswer, String word)
    {
        List<String> answers = new ArrayList<>();
        answers.add(correctAnswer);

        for (int i = 0; i < 3; i++)
        {
            String name = correctAnswer;
            while (answers.contains(name) || name.contains(word))
            {
                name = getRandomWordPictureFileName();
            }
            answers.add(name);
        }

        Collections.shuffle(answers);
        return answers;
    }

    private String getRandomWordPictureFileName()
    {
        Random random = new Random();
        return wordsPicturesNameList.get(random.nextInt(wordsPicturesNameList.size()));
    }

    public void clickOnImageCheckResult(View view)
    {
        String wordFileNameClicked = view.getContentDescription().toString();
        String word = wordFileNameClicked.substring(0, wordFileNameClicked.indexOf("."));
        if (wordSoundFileName.contains(word))
        {
            score = GameHelper.getInstance().correctAnswer(this, scoreTextView, scoreArrayImageList, score);
            loadNewWordSound();
        }
        else
        {
            score = GameHelper.getInstance().wrongAnswer(this, scoreTextView, scoreArrayImageList, score);
            view.setClickable(false);
        }
    }

}