package com.example.jordi.project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import at.blogc.android.views.ExpandableTextView;

public class Contingut_Series extends AppCompatActivity {

    //Declaracio variables

    private static final String TAG = "ExpandableTextView";
    private TextView num_seasons, num_episodes;
    private ImageView portada_contigut_serie;
    Button mostrarmés;
    Toolbar titol_contigut_serie;
    RatingBar ratingBar;
    TextView puntuacio;
    ImageButton afegir;

    /////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contingut_series);
        mostrarmés = (Button) findViewById(R.id.boto_mes);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar_puntuacio);
        puntuacio = (TextView) findViewById(R.id.textview_puntuacio);

        tornar_enrrere();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                puntuacio.setText(String.valueOf(ratingBar.getRating()));


            }
        });


        afegir = (ImageButton) findViewById(R.id.boto_afegir);
        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Contingut_Series.this,"Botó no implementat" ,Toast.LENGTH_SHORT).show();

            }
        });

        final ExpandableTextView descripcio_contingut_serie = findViewById(R.id.Descripcio_contingut_serie);

        descripcio_contingut_serie.setInterpolator(new OvershootInterpolator());


        //Depen si esta extes o contret mostra un text o un altre
        mostrarmés.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarmés.setText(descripcio_contingut_serie.isExpanded() ? "Mostrar Més" : "Mostrar Menys");
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


        //Assignem l'ImageView i TextViews pertinent amb el seu objecte
        portada_contigut_serie = (ImageView) findViewById(R.id.portada_contigut_serie);
        num_seasons = (TextView) findViewById(R.id.TextView_Seasons);
        num_episodes = (TextView) findViewById(R.id.TextView_Capitols);
        ////////////////////////////////////////////////////////////////

        //Assignem els String i ints amb el resultat agafat del intent.
        // Per diferenciar un string d'un altre utilitzem una paraula clau per diferenciar.
        Intent intent = getIntent();
        String Titol = intent.getExtras().getString("Titol");
        String Descripcio = intent.getExtras().getString("Descripcio");
        int portada = intent.getExtras().getInt("Portada");
        int seasons = intent.getExtras().getInt("Temporades");
        int capitols = intent.getExtras().getInt("Capitols");
        //////////////////////////////////////////////////////////////////////////////////////


        //Assignem el contingut del intent en els objectes pertinents.
        descripcio_contingut_serie.setText(Descripcio);
        portada_contigut_serie.setImageResource(portada);
        num_seasons.setText(String.valueOf(seasons));
        num_episodes.setText(String.valueOf(capitols));

        ////////////////////////////////////////////////////////////////

        Toolbar titol_contigut_serie = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle(Titol);

    }

    public void tornar_enrrere(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}