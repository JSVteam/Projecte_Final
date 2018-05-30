package com.example.jordi.project;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class PaginaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Declaracio de variables

    public static int page = 1;
    public static String Api_Key = "df86154eff229c28b09d4617fe11786d";
    public static String Language = "en-US";
    public static String Category = "popular";
    //private TVResults series;
    List<Serie> primeraSerie;
    private static final String message = "http://80.211.40.68/ProjecteFinal/nom_cognoms.php";
    TextView mytext;
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
        //Mostrar_Usuari();

        //Afegir en l'Arraylist les series
        primeraSerie = new ArrayList<>();
        primeraSerie.add(new Serie(1, "La casa de Papel", Constantes.DESCRIPCIO_CASAPAPEL, 2, 9, R.drawable.lacasadepapel, 10));
        primeraSerie.add(new Serie(2, "Black Mirror", Constantes.DESCRIPCIO_BLACKMIRROR, 4, 4, R.drawable.blackmirror, 10));
        primeraSerie.add(new Serie(3, "The Walking Dead", Constantes.DESCRIPCIO_TWD, 8, 16, R.drawable.twd, 10));
        primeraSerie.add(new Serie(4, "WestWorld", Constantes.DESCRIPCIO_WESTWORLD, 2, 12, R.drawable.westworld, 10));
        primeraSerie.add(new Serie(5, "Altered Carbon", Constantes.DESCRIPCIO_ALTEREDCARBON, 1, 10, R.drawable.alteredcarbon, 10));
        primeraSerie.add(new Serie(6, "Rick Y Morty", Constantes.DESCRIPCIO_RICKMORTY, 3, 11, R.drawable.rickymorty, 10));
        primeraSerie.add(new Serie(7, "Orange is the new Black", Constantes.DESCRIPCIO_ORANGEBLACK, 5, 13, R.drawable.orangeblack, 10));
        primeraSerie.add(new Serie(8, "Luke Cage", Constantes.DESCRIPCIO_LUKECAGE, 1, 13, R.drawable.lukecage, 10));
        primeraSerie.add(new Serie(9, "Breaking Bad", Constantes.DESCRIPCIO_BREAKINGBAD, 5, 13, R.drawable.breakingbad, 10));
        primeraSerie.add(new Serie(10, "Juego de Tronos", Constantes.DESCRIPCIO_GOT, 7, 10, R.drawable.juegodetronos, 10));
        primeraSerie.add(new Serie(11, "Jessica Jones", Constantes.DESCRIPCIO_JESSICA, 2, 10, R.drawable.jessicajones, 10));
        primeraSerie.add(new Serie(12, "Narcos", Constantes.DESCRIPCIO_NARCOS, 3, 10, R.drawable.narcos, 10));
        primeraSerie.add(new Serie(13, "Los Simpsons", Constantes.DESCRIPCIO_SIMPSONS, 29, 21, R.drawable.simpsons, 10));
        primeraSerie.add(new Serie(14, "The Defenders", Constantes.DESCRIPCIO_THEDEFENDERS, 1, 8, R.drawable.thedefenders, 10));
        primeraSerie.add(new Serie(15, "Fear The Walking Dead", Constantes.DESCRIPCIO_FTWD, 4, 16, R.drawable.ftwd, 10));
        primeraSerie.add(new Serie(16, "House of Cards", Constantes.DESCRIPCIO_HOUSEOFCARDS, 5, 13, R.drawable.houseofcards, 10));
        primeraSerie.add(new Serie(17, "3%", Constantes.DESCRIPCIO_3, 2, 8, R.drawable.tresporciento, 10));
        primeraSerie.add(new Serie(18, "Los 100", Constantes.DESCRIPCIO_LOS100, 5, 13, R.drawable.los100, 10));
        primeraSerie.add(new Serie(19, "Agentes of SHIELD", Constantes.DESCRIPCIO_AGENTES, 5, 22, R.drawable.agentes, 10));
        primeraSerie.add(new Serie(20, "The Flash", Constantes.DESCRIPCIO_FLASH, 4, 24, R.drawable.flash, 10));
        ////////////////////////////////////////////////////////////////////



        //Assignar i Mostrar el RecyclerView
        RecyclerView myReciclerView = (RecyclerView) findViewById(R.id.recyclerView_Portadas);
        RecyclerViewAdapterr adapter = new RecyclerViewAdapterr(PaginaPrincipal.this, primeraSerie);
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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);

        mytext = view.findViewById(R.id.nom_cognoms);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        getMenuInflater().inflate(R.menu.pagina_principal, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_logout) {

            shared_Preferences.setSharedPreferences(getApplicationContext(), false);
            Intent logout = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logout);
            finish();
        } else if (id == R.id.nav_series) {
            //fragment = new Menu_Series();
        } else if (id == R.id.nav_settings) {
            //fragment = new Menu_Configuracio();
        } else if (id == R.id.nav_home) {
            Intent pagina_principal = new Intent(getApplicationContext(), PaginaPrincipal.class);
            startActivity(pagina_principal);
        }


        /*if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.content_main, fragment);

            ft.commit();
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    /*
    public void Mostrar_Usuari() {

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

                        mytext.setText(nombre+" "+apellido);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    } */

}


