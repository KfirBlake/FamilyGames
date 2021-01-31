package com.blake.kids;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SdarotGame extends AppCompatActivity
{
    private static final String TAG = "SdarotGame";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdarot_game);

        Log.d(TAG, "Started");

    }
}
