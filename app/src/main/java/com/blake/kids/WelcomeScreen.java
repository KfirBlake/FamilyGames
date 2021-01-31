package com.blake.kids;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity
{
    static int SPLASH_SCREEN_TIME = 2000;

    Animation topAnim, bottomAnim;
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.imageWelcom);
        text = findViewById(R.id.txtWelcome);

        image.setAnimation(topAnim);
        text.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent splashIntent = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_SCREEN_TIME);
    }
}
