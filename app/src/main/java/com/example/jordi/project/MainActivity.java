package com.example.jordi.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jordi.project.API.ApiInterface;
import com.example.jordi.project.API.TVResults;
import com.example.jordi.project.Constants.Constantes;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;

    public static int page = 1;
    public static String Api_Key = "df86154eff229c28b09d4617fe11786d";
    public static String Language = "en-US";
    public static String Category = "popular";
    public static ImageView imatge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button3);

        imatge = (ImageView) findViewById(R.id.imageLogo);
        //String url = "https://logo.clearbit.com/www.jobs.netflix.com";
        // Picasso.with(this).load(url).into(imatge);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface interficie = retrofit.create(ApiInterface.class);

        Call<TVResults> Resultats = interficie.getSeries(Category, Api_Key, Language, page);

        Resultats.enqueue(new Callback<TVResults>() {
            @Override
            public void onResponse(Call<TVResults> call, Response<TVResults> response) {
                TVResults series = response.body();
                List<TVResults.ResultsBean> llistatSeries = series.getResults();
                TVResults.ResultsBean firstSerie = llistatSeries.get(1);

                button.setText(firstSerie.getFirst_air_date());
                button2.setText(firstSerie.getName());
                carregarImageURL(Constantes.BASE_URL_IMAGE + firstSerie.getPoster_path(), imatge);
            }

            @Override
            public void onFailure(Call<TVResults> call, Throwable t) {
                t.printStackTrace();
            }
        });

        if (shared_Preferences.getSharedPreferences(this)) {

            Intent intent = new Intent(getApplicationContext(), Contingut_PaginaPrincipal.class);
            startActivity(intent);
            finish();
        } else {


            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(MainActivity.this, Entrar_Usuari.class);
                    startActivity(intent2);
                }
            });

//Hola
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Registre.class);
                    startActivity(intent);

                }
            });
        }
    }
    public void carregarImageURL(String url, ImageView imageView){
        Picasso.with(this).load(url).into(imageView);
    }
}
