package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;


public class IslandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back arrow

        TextView nameTextView = findViewById(R.id.name_textview);
        TextView populationTextView = findViewById(R.id.population_textview);
        TextView governmentTextView = findViewById(R.id.government_textview);
        TextView areaTextView = findViewById(R.id.area_textview);
        TextView wikiUrlTextView = findViewById(R.id.wikiurl_textview);
        TextView locationTextView = findViewById(R.id.ocean_textview);

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            nameTextView.setText(intentExtras.getString("Island_name"));
            populationTextView.setText(intentExtras.getString("Island_population") + " inhabitants");
            governmentTextView.setText(intentExtras.getString("Island_government"));
            areaTextView.setText(intentExtras.getString("Island_area") + " km\u00B2");
            wikiUrlTextView.setText(intentExtras.getString("Island_wikiUrl"));
            locationTextView.setText(intentExtras.getString("Island_ocean"));
        }


    }
}