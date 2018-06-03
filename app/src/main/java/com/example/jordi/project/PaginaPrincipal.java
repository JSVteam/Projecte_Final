package com.example.jordi.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jordi.project.Constants.Constantes;
import com.example.jordi.project.RecyclerView.RecyclerViewAdapterr;
import com.example.jordi.project.RecyclerView.Serie;

import java.util.ArrayList;
import java.util.List;


public class PaginaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    //Declaracio de variables

    public static int page = 1;
    public static String Api_Key = "df86154eff229c28b09d4617fe11786d";
    public static String Language = "en-US";
    public static String Category = "popular";
    //private TVResults series;
    List<Serie> primeraSerie;
    SearchView searchview;
    private static final String message = "http://80.211.40.68/ProjecteFinal/nom_cognoms.php";
    TextView mytext;
    public static RecyclerViewAdapterr adapter;
    public static RecyclerView myReciclerView;
    /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Cridar a la funció Mostrar Usuari
       // Mostrar_Usuari();

        //Afegir en l'Arraylist les series
        primeraSerie = new ArrayList<>();
        primeraSerie.add(new Serie(1, "La Casa de Papel", Constantes.DESCRIPCIO_CASAPAPEL, 2, 9, R.drawable.lacasadepapel, 8));
        primeraSerie.add(new Serie(2, "Black Mirror", Constantes.DESCRIPCIO_BLACKMIRROR, 4, 4, R.drawable.blackmirror, 8.2));
        primeraSerie.add(new Serie(3, "The Walking Dead", Constantes.DESCRIPCIO_TWD, 8, 16, R.drawable.twd, 7.3));
        primeraSerie.add(new Serie(4, "WestWorld", Constantes.DESCRIPCIO_WESTWORLD, 2, 12, R.drawable.westworld, 8.2));
        primeraSerie.add(new Serie(5, "Altered Carbon", Constantes.DESCRIPCIO_ALTEREDCARBON, 1, 10, R.drawable.alteredcarbon, 7.9));
        primeraSerie.add(new Serie(6, "Rick y Morty", Constantes.DESCRIPCIO_RICKMORTY, 3, 11, R.drawable.rickymorty, 8.5));
        primeraSerie.add(new Serie(7, "Orange is the new Black", Constantes.DESCRIPCIO_ORANGEBLACK, 5, 13, R.drawable.orangeblack, 7.4));
        primeraSerie.add(new Serie(8, "Luke Cage", Constantes.DESCRIPCIO_LUKECAGE, 1, 13, R.drawable.lukecage, 6.9));
        primeraSerie.add(new Serie(9, "Breaking Bad", Constantes.DESCRIPCIO_BREAKINGBAD, 5, 13, R.drawable.breakingbad, 8.3));
        primeraSerie.add(new Serie(10, "Juego de Tronos", Constantes.DESCRIPCIO_GOT, 7, 10, R.drawable.juegodetronos, 8.1));
        primeraSerie.add(new Serie(11, "Jessica Jones", Constantes.DESCRIPCIO_JESSICA, 2, 10, R.drawable.jessicajones, 7.5));
        primeraSerie.add(new Serie(12, "Narcos", Constantes.DESCRIPCIO_NARCOS, 3, 10, R.drawable.narcos, 8));
        primeraSerie.add(new Serie(13, "Los Simpsons", Constantes.DESCRIPCIO_SIMPSONS, 29, 21, R.drawable.simpsons, 7.1));
        primeraSerie.add(new Serie(14, "The Defenders", Constantes.DESCRIPCIO_THEDEFENDERS, 1, 8, R.drawable.thedefenders, 7.1));
        primeraSerie.add(new Serie(15, "Fear The Walking Dead", Constantes.DESCRIPCIO_FTWD, 4, 16, R.drawable.ftwd, 6.4));
        primeraSerie.add(new Serie(16, "House of Cards", Constantes.DESCRIPCIO_HOUSEOFCARDS, 5, 13, R.drawable.houseofcards, 8.1));
        primeraSerie.add(new Serie(17, "3%", Constantes.DESCRIPCIO_3, 2, 8, R.drawable.tresporciento, 7.3));
        primeraSerie.add(new Serie(18, "Los 100", Constantes.DESCRIPCIO_LOS100, 5, 13, R.drawable.los100, 6.4));
        primeraSerie.add(new Serie(19, "Agentes of SHIELD", Constantes.DESCRIPCIO_AGENTES, 5, 22, R.drawable.agentes, 6.6));
        primeraSerie.add(new Serie(20, "The Flash", Constantes.DESCRIPCIO_FLASH, 4, 24, R.drawable.flash, 6.7));
        ////////////////////////////////////////////////////////////////////


        //Assignar i Mostrar el RecyclerView
        myReciclerView = (RecyclerView) findViewById(R.id.recyclerView_Portadas);
        adapter = new RecyclerViewAdapterr(PaginaPrincipal.this, primeraSerie);
        myReciclerView.setLayoutManager(new GridLayoutManager(PaginaPrincipal.this, 3));
        myReciclerView.setAdapter(adapter);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSeach);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);

        mytext = view.findViewById(R.id.nom_cognoms);


        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_logout) {

            shared_Preferences.setSharedPreferences(getApplicationContext(), false);
            Intent logout = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logout);
            finish();
       /* } else if (id == R.id.nav_series) {
            fragment = new Menu_Series();*/
        } else if (id == R.id.nav_home) {
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String text = newText.toLowerCase();
        List<Serie> novalista = new ArrayList<>();

        for (Serie nom : primeraSerie) {
            if (nom.getSerieTitol().toLowerCase().contains(text)) {
                novalista.add(nom);
            }
        }

        adapter.updateList(novalista);
        return true;
    }


 /*private void filter(String text){
        ArrayList<Serie> series = new ArrayList<>();

        for(Serie serie : primeraSerie) {
            if (serie.getSerieTitol().toLowerCase().contains(text.toLowerCase()))
            {
                series.add(serie);
            }
        }
        adapter.listseries(series);
    }*/


   /* public void Mostrar_Usuari() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://80.211.40.68/ProjecteFinal/nom_cognoms.php?id=", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        JSONArray jsonArray = new JSONArray(new String(responseBody));
                        String apellido;
                        String nombre;


                        apellido = jsonArray.getJSONObject(0).getString("cognom");
                        nombre = jsonArray.getJSONObject(0).getString("nom");

                        mytext.setText(nombre + " " + apellido);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }*/

}


