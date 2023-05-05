package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Island> islands;
    private RecyclerViewAdapter recyclerViewAdapter;

    private Intent islandActivityIntent;
    private Intent aboutActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutActivityIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutActivityIntent);
            }
        });

        // Testing RecyclerView
        islands = new ArrayList<>(Arrays.asList(
                new Island("Tristan da Cunha", 238,"United kingdom", "Atlantic Ocean", "https://en.wikipedia.org/wiki/Tristan_da_Cunha","Edinburgh of the Seven Seas",120,""),
                new Island("Easter Island", 7750,"Chile", "Pacific Ocean", "https://en.wikipedia.org/wiki/Easter_Island","",164,""), //163.6 area
                new Island("Pitcairn Islands", 47,"United kingdom", "Pacific Ocean", "","Adamstown",47,""),
                new Island("Bouvet Island", 0,"Norway", "Atlantic Ocean", "","",49,""),
                new Island("Kerguelen Islands", 45,"France", "Atlantic Ocean", "","Port-aux-Français",7125,"")
        ));

        recyclerViewAdapter = new RecyclerViewAdapter(this, islands, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Island island) {
                Log.d("Island:", island.getWikiUrl());
                islandActivityIntent = new Intent(MainActivity.this,IslandActivity.class);
                islandActivityIntent.putExtra("Island_name",island.getName());
                islandActivityIntent.putExtra("Island_population",String.valueOf(island.getPopulation()));
                islandActivityIntent.putExtra("Island_government",island.getGovernment());
                islandActivityIntent.putExtra("Island_ocean",island.getOcean());
                islandActivityIntent.putExtra("Island_wikiUrl",island.getWikiUrl());
                islandActivityIntent.putExtra("Island_area",String.valueOf(island.getArea()));
                islandActivityIntent.putExtra("Island_imageUrl",island.getImageUrl());
                startActivity(islandActivityIntent);
            }
        });

        RecyclerView view = findViewById(R.id.recyclerview_islands);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(recyclerViewAdapter);
    }
}