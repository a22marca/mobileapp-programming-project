package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Island> islands;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Testing RecyclerView
        islands = new ArrayList<>(Arrays.asList(
                new Island("Tristan da Cuhna", 123,"United kingdom", "Atlantic Ocean", "","Edinburgh of the Seven Seas",120,""),
                new Island("Easter Island", 7750,"Chile", "Pacific Ocean", "https://en.wikipedia.org/wiki/Easter_Island","",164,""), //163.6 area
                new Island("Pitcairn Islands", 47,"United kingdom", "Pacific Ocean", "","Adamstown",47,""),
                new Island("Bouvet Island", 0,"Norway", "Atlantic Ocean", "","",49,""),
                new Island("Kerguelen Islands", 45,"France", "Atlantic Ocean", "","Port-aux-Français",7125,"")
        ));

        recyclerViewAdapter = new RecyclerViewAdapter(this, islands, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Island island) {
                Log.d("Island:", island.getName());
            }
        });

        RecyclerView view = findViewById(R.id.recyclerview_islands);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(recyclerViewAdapter);
    }
}