package com.blake.kids;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.blake.kids.adapter.ImageAdapter;
import com.blake.kids.model.BirdInfo;
import com.blake.kids.util.BirdsUtil;

import java.io.IOException;

public class BirdDetailedInformation extends AppCompatActivity
{
    public static final String BIRD_NAME_KEY = "birdName";

    private ImageView imgBirdBig, imgBirdSmall1, imgBirdSmall2, imgBirdSmall3, imgBirdSmall4, imgBirdSmall5;
    private TextView txtBirdInfoName, txtBirdInformation;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brid_detailed_information);

        initViews();

        Intent intent = getIntent();
        if (null != intent)
        {
            String name = intent.getStringExtra(BIRD_NAME_KEY);
            if (name != null && !name.equals(""))
            {
                BirdInfo currentBird = BirdsUtil.getInstance().getBirdByName(name);
                if (null != currentBird)
                {
                    setData(currentBird);
                }
            }
        }
    }

    private void initViews()
    {
        imgBirdBig = findViewById(R.id.imgBirdDetailBig);
        imgBirdSmall1 = findViewById(R.id.imgBirdDetailSmall1);
        imgBirdSmall2 = findViewById(R.id.imgBirdDetailSmall2);
        imgBirdSmall3 = findViewById(R.id.imgBirdDetailSmall3);
        imgBirdSmall4 = findViewById(R.id.imgBirdDetailSmall4);
        imgBirdSmall5 = findViewById(R.id.imgBirdDetailSmall5);
        txtBirdInfoName = findViewById(R.id.txtBirdDetailName);
        txtBirdInformation = findViewById(R.id.txtBirdDetailedInfo);

        imgBirdSmall1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                imgBirdBig.setImageDrawable(imgBirdSmall1.getDrawable());
            }
        });
        imgBirdSmall2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                imgBirdBig.setImageDrawable(imgBirdSmall2.getDrawable());
            }
        });
        imgBirdSmall3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                imgBirdBig.setImageDrawable(imgBirdSmall3.getDrawable());
            }
        });
        imgBirdSmall4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                imgBirdBig.setImageDrawable(imgBirdSmall4.getDrawable());
            }
        });
        imgBirdSmall5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                imgBirdBig.setImageDrawable(imgBirdSmall5.getDrawable());
            }
        });

    }

    private void setData(BirdInfo bird)
    {
        txtBirdInfoName.setText(bird.getName());
        txtBirdInformation.setText(bird.getDescription());
        String imagePathInAsset = "birds_picture/" + bird.getNameOfFilesInEnglish() + ".jpg";
        String imagePathInAsset1 = "birds_picture/" + bird.getNameOfFilesInEnglish() + "1.jpg";
        String imagePathInAsset2 = "birds_picture/" + bird.getNameOfFilesInEnglish() + "2.jpg";
        String imagePathInAsset3 = "birds_picture/" + bird.getNameOfFilesInEnglish() + "3.jpg";
        String imagePathInAsset4 = "birds_picture/" + bird.getNameOfFilesInEnglish() + "4.jpg";
        String imagePathInAsset5 = "birds_picture/" + bird.getNameOfFilesInEnglish() + "5.jpg";
        try
        {
            imgBirdBig.setImageDrawable(Drawable.createFromStream(this.getAssets().open(imagePathInAsset), imagePathInAsset));
            imgBirdSmall1.setImageDrawable(Drawable.createFromStream(this.getAssets().open(imagePathInAsset1), imagePathInAsset1));
            imgBirdSmall2.setImageDrawable(Drawable.createFromStream(this.getAssets().open(imagePathInAsset2), imagePathInAsset2));
            imgBirdSmall3.setImageDrawable(Drawable.createFromStream(this.getAssets().open(imagePathInAsset3), imagePathInAsset3));
            imgBirdSmall4.setImageDrawable(Drawable.createFromStream(this.getAssets().open(imagePathInAsset4), imagePathInAsset4));
            imgBirdSmall5.setImageDrawable(Drawable.createFromStream(this.getAssets().open(imagePathInAsset5), imagePathInAsset5));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        viewPager = findViewById(R.id.viewPagerBirdDetails);
        Drawable[] imageDrawable = new Drawable[]{imgBirdSmall1.getDrawable(), imgBirdSmall2.getDrawable(), imgBirdSmall3.getDrawable(), imgBirdSmall4.getDrawable(), imgBirdSmall5.getDrawable()};
        ImageAdapter imageAdapter = new ImageAdapter(this, imageDrawable);
        viewPager.setAdapter(imageAdapter);
    }


}
