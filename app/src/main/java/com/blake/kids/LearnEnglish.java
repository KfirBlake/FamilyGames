package com.blake.kids;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blake.kids.util.GameHelper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static android.widget.Toast.makeText;

public class LearnEnglish extends AppCompatActivity
{
    private List<String> wordsSoundNameList = new LinkedList<>();
    private String wordSoundFileName;
    private String category = "english_animals";
    private Integer index = 0;

    ImageView imgPicture;
    ImageView imgPrevPicture;
    ImageView imgPlay;
    ImageView imgNextPicture;


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
        setContentView(R.layout.activity_learn_english);

        initView();
        initListsOfFiles();
    }

    private void initView()
    {
        imgPicture = findViewById(R.id.imgLearnEnglishPicture);
        imgPrevPicture = findViewById(R.id.imgLearnEnglishPrev);
        imgPlay = findViewById(R.id.imgLearnEnglishPlay);
        imgNextPicture = findViewById(R.id.imgLearnEnglishNext);
    }

    private void initListsOfFiles()
    {
        wordsSoundNameList = GameHelper.getInstance().loadFromAssetsNameList(this, category + "_sounds");
        index = 0;
        loadNewWordSound();
    }

    public void loadNewWordSound()
    {
        wordSoundFileName = wordsSoundNameList.get(index);
        GameHelper.getInstance().playAssetSound(this, category + "_sounds/" + wordSoundFileName);

        String wordFileNameOfCorrect = wordSoundFileName.substring(0, wordSoundFileName.indexOf(".")) + ".png";
        loadImageToImageView(category + "_pictures", wordFileNameOfCorrect, imgPicture);
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
            makeText(LearnEnglish.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void playSound(View view)
    {
        GameHelper.getInstance().playAssetSound(this, category + "_sounds/" + wordSoundFileName);
    }

    public void prevImage(View view)
    {
        index--;
        if(index < 0)
            index = wordsSoundNameList.size() -1;
        loadNewWordSound();
    }

    public void nextImage(View view)
    {
        index++;
        if (index >= wordsSoundNameList.size())
            index = 0;
        loadNewWordSound();
    }
}