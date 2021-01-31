package com.blake.kids.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AppSettingsActivity extends AppCompatActivity
{

    public static final String PREF_KEY_PLAY_SOUND = "pref_play_sound";
    public static final String PREF_KEY_NUMBER_OF_POINT_FOR_ERROR = "pref_number_of_point_for_error";
    public static final String PREF_KEY_TIMEOUT_FLAG = "pref_timeout_flag";
    public static final String PREF_KEY_TIMEOUT_SECOUNDS = "pref_timeout_secounds";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AppSettingsFragment())
                .commit();
    }
}