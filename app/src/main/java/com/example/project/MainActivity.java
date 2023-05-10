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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private ArrayList<Island> islands;
    private RecyclerViewAdapter recyclerViewAdapter;

    private Intent islandActivityIntent;
    private Intent aboutActivityIntent;

    private final String ISLANDS_JSON_URL = "https://mobprog.webug.se/json-api?login=a22marca";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new JsonTask(this).execute(ISLANDS_JSON_URL);

        Button aboutButton = findViewById(R.id.about_button);

        islands = new ArrayList<>();
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutActivityIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutActivityIntent);
            }
        });

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

    @Override
    public void onPostExecute(String json){
        try {
            JSONArray jsonIslandsArray = new JSONArray(json);

            for (int i = 0; i < jsonIslandsArray.length(); i++){
                JSONObject islandObject = jsonIslandsArray.getJSONObject(i);
                JSONObject islandAuxDataObject = islandObject.getJSONObject("auxdata");

                islands.add(new Island(
                        islandObject.getString("name"),
                        islandObject.getInt("cost"),
                        islandObject.getString("company"),
                        islandObject.getString("location"),
                        islandAuxDataObject.getString("wikiUrl"),
                        islandObject.getString("category"),
                        islandObject.getInt("size"),
                        islandAuxDataObject.getString("imageUrl")
                ));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        recyclerViewAdapter.notifyDataSetChanged();

    }
}