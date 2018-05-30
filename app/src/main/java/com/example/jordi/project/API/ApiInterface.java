package com.example.jordi.project.API;

import com.example.jordi.project.Constants.Constantes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


//Creem una interficie per recuperar  les dades de la API
public interface ApiInterface {
    @GET("/3/tv/{category}")
    Call<TVResults> getSeries(
            @Path(Constantes.CATEGORY)String category,
            @Query(Constantes.API_KEY)String api_Key,
            @Query(Constantes.LANGUAGE) String language,
            @Query(Constantes.PAGE)int page
    );
}
