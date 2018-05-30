package com.example.jordi.project.RecyclerView;

/**
 * Created by david on 28/05/2018.
 */

public class Serie {

    //Declaracio variables
    private int SerieID;
    private String SerieTitol;
    private String Desc;
    private int num_seasons;
    private int num_episodes;
    private int portada;
    private int puntuacio;
    ////////////////////////////

    public Serie() {
    }

    //Constructor
    public Serie(int serieID, String serieTitol, String desc, int num_seasons, int num_episodes, int portada, int puntuacio) {
        SerieID = serieID;
        SerieTitol = serieTitol;
        Desc = desc;
        this.num_seasons = num_seasons;
        this.num_episodes = num_episodes;
        this.portada = portada;
        this.puntuacio = puntuacio;
    }
    //////////////////////////////////////////

    //Gets
    public Serie(int serieID) {
        SerieID = serieID;
    }

    public int getSerieID() {
        return SerieID;
    }

    public String getSerieTitol() {
        return SerieTitol;
    }

    public String getDesc() {
        return Desc;
    }

    public int getNum_seasons() {
        return num_seasons;
    }

    public int getNum_episodes() {
        return num_episodes;
    }

    public int getPortada() {
        return portada;
    }

    public int getPuntuacio() {
        return puntuacio;
    }
    /////////////////////////////////////////////////////


}