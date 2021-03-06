package com.example.jordi.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.jordi.project.Constants.Constantes;
import com.example.jordi.project.RecyclerView.RecyclerViewAdapterr;
import com.example.jordi.project.RecyclerView.Serie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 23/05/2018.
 */

public class Contingut_PaginaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int page = 1;
    public static String Api_Key = "df86154eff229c28b09d4617fe11786d";
    public static String Language = "en-US";
    public static String Category = "popular";
    //private TVResults series;
    ArrayList<Serie> primeraSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pagina_principal);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        primeraSerie = new ArrayList<>();
        primeraSerie.add(new Serie(1, "La casa de Papel", Constantes.DESCRIPCIO_CASAPAPEL, 2342, 12, R.drawable.lacasadepapel, 10));
        primeraSerie.add(new Serie(2, "Black Mirror", Constantes.DESCRIPCIO_BLACKMIRROR, 2, 12, R.drawable.blackmirror, 10));
        primeraSerie.add(new Serie(3, "The Walking Dead", Constantes.DESCRIPCIO_TWD, 2, 12, R.drawable.twd, 10));
        primeraSerie.add(new Serie(4, "WestWorld", Constantes.DESCRIPCIO_WESTWORLD, 2, 12, R.drawable.westworld, 10));
        primeraSerie.add(new Serie(5, "Altered Carbon", Constantes.DESCRIPCIO_ALTEREDCARBON, 2, 12, R.drawable.alteredcarbon, 10));
        primeraSerie.add(new Serie(6, "Rick Y Morty", Constantes.DESCRIPCIO_RICKMORTY, 2, 12, R.drawable.rickymorty, 10));
        primeraSerie.add(new Serie(7, "Orange is the new Black", Constantes.DESCRIPCIO_ORANGEBLACK, 2, 12, R.drawable.orangeblack, 10));
        primeraSerie.add(new Serie(8, "Luke Cage", Constantes.DESCRIPCIO_LUKECAGE, 2, 12, R.drawable.lukecage, 10));
        primeraSerie.add(new Serie(9, "Breaking Bad", Constantes.DESCRIPCIO_BREAKINGBAD, 2, 12, R.drawable.breakingbad, 10));
        primeraSerie.add(new Serie(10, "Juego de Tronos", Constantes.DESCRIPCIO_GOT, 2, 12, R.drawable.juegodetronos, 10));
        primeraSerie.add(new Serie(11, "Jessica Jones", Constantes.DESCRIPCIO_JESSICA, 2, 12, R.drawable.jessicajones, 10));
        primeraSerie.add(new Serie(12, "Narcos", Constantes.DESCRIPCIO_NARCOS, 2, 12, R.drawable.narcos, 10));
        primeraSerie.add(new Serie(13, "Los Simpsons", Constantes.DESCRIPCIO_SIMPSONS, 2, 12, R.drawable.simpsons, 10));
        primeraSerie.add(new Serie(14, "The Defenders", Constantes.DESCRIPCIO_THEDEFENDERS, 2, 12, R.drawable.thedefenders, 10));
        primeraSerie.add(new Serie(15, "Fear The Walking Dead", Constantes.DESCRIPCIO_FTWD, 2, 12, R.drawable.ftwd, 10));
        primeraSerie.add(new Serie(16, "House of Cards", Constantes.DESCRIPCIO_HOUSEOFCARDS, 2, 12, R.drawable.houseofcards, 10));
        primeraSerie.add(new Serie(17, "3%", Constantes.DESCRIPCIO_3, 2, 12, R.drawable.tresporciento, 10));
        primeraSerie.add(new Serie(18, "Los 100", Constantes.DESCRIPCIO_LOS100, 2, 12, R.drawable.los100, 10));
        primeraSerie.add(new Serie(19, "Agentes of SHIELD", Constantes.DESCRIPCIO_AGENTES, 2, 12, R.drawable.agentes, 10));
        primeraSerie.add(new Serie(20, "The Flash", Constantes.DESCRIPCIO_FLASH, 2, 12, R.drawable.flash, 10));


        //LinearLayoutManager llm = new LinearLayoutManager(this);
        RecyclerView myReciclerView = (RecyclerView) findViewById(R.id.recyclerView_Portadas);
        RecyclerViewAdapterr adapter = new RecyclerViewAdapterr(Contingut_PaginaPrincipal.this, primeraSerie);
        myReciclerView.setLayoutManager(new GridLayoutManager(Contingut_PaginaPrincipal.this, 3));
        myReciclerView.setAdapter(adapter);


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        android.support.v4.app.Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_logout) {

            shared_Preferences.setSharedPreferences(getApplicationContext(), false);
            Intent logout = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logout);
            finish();
        } else if (id == R.id.nav_series) {
            fragment = new Menu_Series();
            /* } else if (id == R.id.nav_settings) {
            fragment = new Menu_Configuracio();*/
        } else if (id == R.id.nav_home) {
            Intent pagina_principal = new Intent(getApplicationContext(), PaginaPrincipal.class);
            startActivity(pagina_principal);
        }


        /*
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.content_main, fragment);

            ft.commit();
        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}