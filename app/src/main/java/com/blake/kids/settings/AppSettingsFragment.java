package com.blake.kids.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.blake.kids.R;

/**
 * Created by Kfir Blake on 06/09/2020.
 */
public class AppSettingsFragment extends PreferenceFragmentCompat
{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
