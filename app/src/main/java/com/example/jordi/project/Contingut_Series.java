package com.example.jordi.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import at.blogc.android.views.ExpandableTextView;

public class Contingut_Series extends AppCompatActivity {

    private static final String TAG = "ExpandableTextView";
    private TextView titol_contigut_serie,num_seasons,num_episodes;
    private ImageView portada_contigut_serie;
    Button mostrarmés;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contingut_series);
        mostrarmés=(Button)findViewById(R.id.boto_mes);

        final ExpandableTextView descripcio_contingut_serie = findViewById(R.id.Descripcio_contingut_serie) ;

        descripcio_contingut_serie.setInterpolator(new OvershootInterpolator());


        mostrarmés.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarmés.setText(descripcio_contingut_serie.isExpanded() ? "Mostrar Més": "Mostrar Menys");
                descripcio_contingut_serie.toggle();
            }
        });

        descripcio_contingut_serie.addOnExpandListener(new ExpandableTextView.OnExpandListener() {
            @Override
            public void onExpand(@NonNull ExpandableTextView view) {
                Log.d(TAG, "ExpandableTextView expanded");
            }

            @Override
            public void onCollapse(@NonNull ExpandableTextView view) {

                Log.d(TAG, "ExpandableTextView collapsed");

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);


       // titol_contigut_serie = (TextView) findViewById(R.id.Titol_contingut_serie);
        portada_contigut_serie = (ImageView) findViewById(R.id.portada_contigut_serie);
        num_seasons = (TextView) findViewById(R.id.TextView_Seasons);
        num_episodes = (TextView) findViewById(R.id.TextView_Capitols);

        Intent intent = getIntent();
        String Titol = intent.getExtras().getString("Titol");
        String Descripcio = intent.getExtras().getString("Descripcio");
        int portada = intent.getExtras().getInt("Portada");
        int seasons = intent.getExtras().getInt("Temporades");
        int capitols = intent.getExtras().getInt("Capitols");




        //titol_contigut_serie.setText(Titol);
        descripcio_contingut_serie.setText(Descripcio);
        portada_contigut_serie.setImageResource(portada);
        num_seasons.setText(String.valueOf(seasons));
        num_episodes.setText(String.valueOf(capitols));




    }

}