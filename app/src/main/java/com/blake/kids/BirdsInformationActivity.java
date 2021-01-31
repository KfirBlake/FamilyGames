package com.blake.kids;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blake.kids.adapter.BirdsRecViewAdapter;
import com.blake.kids.util.BirdsUtil;

public class BirdsInformationActivity extends AppCompatActivity
{

    RecyclerView birdsRecView;
    BirdsRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_information);

        birdsRecView = findViewById(R.id.birdsRecView);

        adapter = new BirdsRecViewAdapter(this);
        adapter.setBirdsInfo(BirdsUtil.getInstance().getAllBirds());

        birdsRecView.setAdapter(adapter);
        //birdsRecView.setLayoutManager(new GridLayoutManager(this, 2));
        birdsRecView.setLayoutManager(new LinearLayoutManager(this));
        //birdsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //birdsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
