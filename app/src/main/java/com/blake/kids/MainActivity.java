package com.blake.kids;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.blake.kids.settings.AppSettingsActivity;
import com.blake.kids.util.GameHelper;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity
{
    //public static final String EXTRA_MESSAGE = "com.blake.kids.MESSAGE";
    public static final String SHARED_PROGRAM_NAME = "com.blake.kids";
    public static final String SHARED_PROGRAM_NUMBER_OF_POINT_FOR_ERROR = "NUMBER_OF_POINT_FOR_ERROR";
    public static final String SHARED_PROGRAM_NUMBER_OF_SECOND_FOR_TIMEOUT = "NUMBER_OF_SECOND_FOR_TIMEOUT";
    //private static int SPLASH_SCREEN_TIME = 3000;


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_records:
                Toast.makeText(MainActivity.this, "Show Records table", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_settings_new:
                Intent intentSettings = new Intent(this, AppSettingsActivity.class);
                startActivity(intentSettings);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        GameHelper.getInstance().releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = this.getSharedPreferences(SHARED_PROGRAM_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        GameHelper.getInstance().setNumberOfErrorToReduce(sharedPreferences.getInt(SHARED_PROGRAM_NUMBER_OF_POINT_FOR_ERROR, 2));
        GameHelper.getInstance().setNumberOfSecondForTimeout(sharedPreferences.getInt(SHARED_PROGRAM_NUMBER_OF_SECOUND_FOR_TIMEOUT, 2));
*/
        //Set - Read Settings
        try
        {
            PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
            SharedPreferences sharedPreferencesSettings = PreferenceManager.getDefaultSharedPreferences(this);
            GameHelper.getInstance().setNumberOfErrorToReduce(Integer.parseInt(sharedPreferencesSettings.getString(AppSettingsActivity.PREF_KEY_NUMBER_OF_POINT_FOR_ERROR, "2")));
            GameHelper.getInstance().setNumberOfSecondForTimeout(Integer.parseInt(sharedPreferencesSettings.getString(AppSettingsActivity.PREF_KEY_TIMEOUT_SECOUNDS, "20)")));
            GameHelper.getInstance().setPlaySoundFlag(sharedPreferencesSettings.getBoolean(AppSettingsActivity.PREF_KEY_PLAY_SOUND, true));
            GameHelper.getInstance().setTimeOutFlag(sharedPreferencesSettings.getBoolean(AppSettingsActivity.PREF_KEY_TIMEOUT_FLAG, false));
        }
        catch (Exception e)
        {
            makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    // Start Games
    public void startNumberGame(View view)
    {
        Intent intent = new Intent(this, NumberGame.class);
        startActivity(intent);
    }

    public void startLettersGame(View view)
    {
        Intent intent = new Intent(this, LetterGame.class);
        startActivity(intent);
    }

    public void startBirdsGame(View view)
    {
        Intent intent = new Intent(this, BirdGame.class);
        startActivity(intent);
    }

    public void startBirdsInformation(View view)
    {
        Intent intent = new Intent(this, BirdsInformationActivity.class);
        startActivity(intent);
    }

    public void startTotemPoleGame(View view)
    {
        Intent intent = new Intent(this, TotemPolePictureGame.class);
        intent.putExtra(TotemPolePictureGame.TOTEM_POLE_GAME, "birds");
        startActivity(intent);
    }

    public void startLetterQuizGame(View view)
    {
        Intent intent = new Intent(this, LetterQuizGame.class);
        startActivity(intent);
    }

    public void startCountGame(View view)
    {
        Intent intent = new Intent(this, CountGame.class);
        startActivity(intent);
    }

    public void startEnglishGame(View view)
    {
        Intent intent = new Intent(this, EnglishGame.class);
        startActivity(intent);
    }

    public void startLearnEnglish(View view)
    {
        Intent intent = new Intent(this, LearnEnglish.class);
        startActivity(intent);
    }
    public void startTicTacToeGame(View view)
    {
        Intent intent = new Intent(this, TicTacToeHome.class);
        startActivity(intent);
    }
}
