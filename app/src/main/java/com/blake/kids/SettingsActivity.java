package com.blake.kids;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blake.kids.util.GameHelper;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    String[] numberOfErrors = {"0", "1", "2", "3", "4", "5"};
    String[] numberOfSecondsForTimeout = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activiy);

        Spinner spinner = findViewById(R.id.spnSettingNumberOfError);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numberOfErrors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(GameHelper.getInstance().getNumberOfErrorToReduce());

        Spinner spinnerTimeout = findViewById(R.id.spnSettingTimerTimeout);
        ArrayAdapter<String> adapterTimeout = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numberOfSecondsForTimeout);
        adapterTimeout.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeout.setAdapter(adapterTimeout);
        spinnerTimeout.setOnItemSelectedListener(this);
        spinnerTimeout.setSelection(GameHelper.getInstance().getNumberOfSecondForTimeout());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //Toast.makeText(getApplicationContext(), "Selected value: " + numberOfErrors[position] ,Toast.LENGTH_SHORT).show();

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spnSettingNumberOfError)
        {
            Toast.makeText(getApplicationContext(), "Clicked on Number Of Error, Selected value: " + numberOfErrors[position], Toast.LENGTH_SHORT).show();
            GameHelper.getInstance().setNumberOfErrorToReduce(position);
            SharedPreferences sharedPreferences = this.getSharedPreferences(MainActivity.SHARED_PROGRAM_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(MainActivity.SHARED_PROGRAM_NUMBER_OF_POINT_FOR_ERROR, position);
            //editor.commit();
            editor.apply();
            Toast.makeText(getApplicationContext(), "Number of Points to reduce: " + position, Toast.LENGTH_SHORT).show();
        }
        if (spinner.getId() == R.id.spnSettingTimerTimeout)
        {
            Toast.makeText(getApplicationContext(), "Clicked on Timer, Selected value: " + numberOfSecondsForTimeout[position], Toast.LENGTH_SHORT).show();
            GameHelper.getInstance().setNumberOfSecondForTimeout(position);
            SharedPreferences sharedPreferences = this.getSharedPreferences(MainActivity.SHARED_PROGRAM_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(MainActivity.SHARED_PROGRAM_NUMBER_OF_SECOND_FOR_TIMEOUT, position);
            editor.apply();
            Toast.makeText(getApplicationContext(), "Timer: " + position, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
