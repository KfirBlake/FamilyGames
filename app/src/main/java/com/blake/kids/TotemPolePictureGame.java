package com.blake.kids;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.blake.kids.adapter.ImageAdapter;
import com.blake.kids.util.GameHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TotemPolePictureGame extends AppCompatActivity
{
    public static final String TOTEM_POLE_GAME = "totemPoleGame";
    public static int TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM = 4;
    private static final String TOTEM_POLE_STRING = "totem_";
    private static final String TOTEM_POLE_STRING_PICTURE = "_pictures";

    private ImageView imgMainPicture;
    Button cmdCheck;
    private ViewPager viewPagerHead, viewPagerMiddle, viewPagerBottom;
    private TextView txtSubjectOfGame;

    private String totemPoleGame;
    private List<String> picturesImageNameList = new LinkedList<>();
    private List<String> picturesImageNameListNotRemove = new LinkedList<>();
    private int correctIndexHead;
    private int correctIndexMiddle;
    private int correctIndexBottom;
    private Map<String, String> gameTitle = new HashMap<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.totem_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.totem_menu_birds:
                totemPoleGame = "birds";
                loadGameData();
                newGame();
                return true;

            case R.id.totem_menu_trees:
                totemPoleGame = "trees";
                loadGameData();
                newGame();
                return true;

            case R.id.totem_menu_flowers:
                return true;

            case R.id.totem_menu_animals:
                totemPoleGame = "animals";
                loadGameData();
                newGame();
                return true;

            case R.id.totem_menu_number_of_picture4:
                TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM = 4;
                item.setChecked(true);
                newGame();
                return true;

            case R.id.totem_menu_number_of_picture5:
                TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM = 5;
                item.setChecked(true);
                newGame();
                return true;

            case R.id.totem_menu_number_of_picture6:
                TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM = 6;
                item.setChecked(true);
                newGame();
                return true;

            case R.id.totem_menu_number_of_picture7:
                TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM = 7;
                item.setChecked(true);
                newGame();
                return true;

            case R.id.totem_menu_number_of_picture8:
                TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM = 8;
                item.setChecked(true);
                newGame();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totem_pole_picture_game);

        Intent intent = getIntent();
        if (null != intent)
        {
            totemPoleGame = intent.getStringExtra(TOTEM_POLE_GAME);
            if (totemPoleGame != null && !totemPoleGame.equals(""))
            {
                //TODO exit the Activity
            }
        }
        initViews();
        loadGameData();
        newGame();
    }


    private void initViews()
    {
        imgMainPicture = findViewById(R.id.totemPoleMainPicute);
        cmdCheck = findViewById(R.id.totemPoleCheckButton);
        viewPagerHead = findViewById(R.id.totemPoleHeadViewPager);
        viewPagerMiddle = findViewById(R.id.totemPoleMiddleViewPager);
        viewPagerBottom = findViewById(R.id.totemPoleBottomViewPager);
        txtSubjectOfGame = findViewById(R.id.totem_subject_title);

        cmdCheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                totemPoleCheckResult();
            }
        });

    }

    private void loadGameData()
    {
        picturesImageNameList = GameHelper.getInstance().loadFromAssetsNameList(this, TOTEM_POLE_STRING + totemPoleGame);
        picturesImageNameListNotRemove = GameHelper.getInstance().loadFromAssetsNameList(this, TOTEM_POLE_STRING + totemPoleGame);

        gameTitle.put("africa", "חיות מאפריקה");
        gameTitle.put("australia", "חיות מאוסטרליה");
        gameTitle.put("bird_watter", "עופות מים");
        gameTitle.put("dures_day", "עופות דורסים יום");
        gameTitle.put("dures_night", "עופות דורסים לילה");
        gameTitle.put("eat_grass", "חיות חווה");
        gameTitle.put("insect", "חרקים");
        gameTitle.put("meharem", "מכרסמים");
        gameTitle.put("sea", "חיות ים");
        gameTitle.put("sing_bird", "ציפורים");
        gameTitle.put("toki", "תוקים");
        gameTitle.put("turef_cat", "טורפים ממשפחת החתולים");
        gameTitle.put("turef_dog", "טורפים ממשפחת הכלבים");
    }

    private void newGame()
    {
        String pictureFileName = getRandomPictureFileName();
        String fullPath = TOTEM_POLE_STRING + totemPoleGame + "/" + pictureFileName;
        try
        {
            imgMainPicture.setImageDrawable(Drawable.createFromStream(getAssets().open(fullPath), fullPath));
            if (totemPoleGame.equals("animals"))
            {
                txtSubjectOfGame.setText(gameTitle.get(pictureFileName.substring(0, pictureFileName.indexOf("."))));
            }
            else
            {
                txtSubjectOfGame.setText("");
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        List<String> namesOfPicturesHeads = getListOfNamesShuffled(pictureFileName);
        correctIndexHead = getCorrectIndex(namesOfPicturesHeads, pictureFileName);
        List<String> namesOfPicturesMiddle = getListOfNamesShuffled(pictureFileName);
        correctIndexMiddle = getCorrectIndex(namesOfPicturesMiddle, pictureFileName);
        List<String> namesOfPicturesBottom = getListOfNamesShuffled(pictureFileName);
        correctIndexBottom = getCorrectIndex(namesOfPicturesBottom, pictureFileName);


        ImageAdapter imageAdapterHead = new ImageAdapter(this, getDrawable(namesOfPicturesHeads, "head"));
        viewPagerHead.setAdapter(imageAdapterHead);
        ImageAdapter imageAdapterMiddle = new ImageAdapter(this, getDrawable(namesOfPicturesMiddle, "middle"));
        viewPagerMiddle.setAdapter(imageAdapterMiddle);
        ImageAdapter imageAdapterButtom = new ImageAdapter(this, getDrawable(namesOfPicturesBottom, "bottom"));
        viewPagerBottom.setAdapter(imageAdapterButtom);

    }

    private List<String> getListOfNamesShuffled(String correctAnswer)
    {
        List<String> answers = new ArrayList<>();
        answers.add(correctAnswer);

        for (int i = 0; i < TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM - 1; i++)
        {
            String name = correctAnswer;
            while (answers.contains(name))
            {
                name = getRandomFileName();
            }
            answers.add(name);
        }

        Collections.shuffle(answers);
        return answers;
    }

    private String getRandomFileName()
    {
        Random random = new Random();
        return picturesImageNameListNotRemove.get(random.nextInt(picturesImageNameListNotRemove.size()));
    }

    private int getCorrectIndex(List<String> list, String correctAnswer)
    {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(correctAnswer))
                return i;
        return 0;
    }

    private Drawable[] getDrawable(List<String> list, String totemLevel)
    {
        Drawable[] imageDrawable = new Drawable[TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM];
        try
        {
            for (int i = 0; i < TOTEM_POLE_NUMBER_OF_PICTURES_IN_TOTEM; i++)
            {
                String imagePathInAsset = TOTEM_POLE_STRING + totemPoleGame + TOTEM_POLE_STRING_PICTURE + "/" + list.get(i).replace(".jpg", "") + "_" + totemLevel + ".jpg";
                imageDrawable[i] = Drawable.createFromStream(this.getAssets().open(imagePathInAsset), imagePathInAsset);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return imageDrawable;
        //family game
    }

    private Drawable[] getDrawable(String name)
    {
        //TODO  Create a folder under assets, that will hold all the pictures of the Totem, Head, Middle, Bottom
        String imagePathInAsset = "birds_picture/" + name + ".jpg";
        String imagePathInAsset1 = "birds_picture/" + name + "1.jpg";
        String imagePathInAsset2 = "birds_picture/" + name + "2.jpg";
        String imagePathInAsset3 = "birds_picture/" + name + "3.jpg";
        String imagePathInAsset4 = "birds_picture/" + name + "4.jpg";
        String imagePathInAsset5 = "birds_picture/" + name + "5.jpg";
        try
        {
            Drawable[] imageDrawable = new Drawable[]{
                    Drawable.createFromStream(this.getAssets().open(imagePathInAsset), imagePathInAsset),
                    Drawable.createFromStream(this.getAssets().open(imagePathInAsset1), imagePathInAsset1),
                    Drawable.createFromStream(this.getAssets().open(imagePathInAsset2), imagePathInAsset2),
                    Drawable.createFromStream(this.getAssets().open(imagePathInAsset3), imagePathInAsset3),
                    Drawable.createFromStream(this.getAssets().open(imagePathInAsset4), imagePathInAsset4),
                    Drawable.createFromStream(this.getAssets().open(imagePathInAsset5), imagePathInAsset5),

            };
            return imageDrawable;
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return new Drawable[]{};
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
                picturesImageNameList = GameHelper.getInstance().loadFromAssetsNameList(this, TOTEM_POLE_STRING + totemPoleGame);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return pictureName;
    }


    private void totemPoleCheckResult()
    {
        int indexHeader = viewPagerHead.getCurrentItem();
        int indexMiddle = viewPagerMiddle.getCurrentItem();
        int indexBottom = viewPagerBottom.getCurrentItem();

        if (indexHeader == correctIndexHead && indexMiddle == correctIndexMiddle && indexBottom == correctIndexBottom)
        {
            GameHelper.getInstance().playSound(this, GameHelper.getInstance().getSuccessSound());
            newGame();
        }
    }

}