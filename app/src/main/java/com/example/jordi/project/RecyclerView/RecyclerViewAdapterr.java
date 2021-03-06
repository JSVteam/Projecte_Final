package com.example.jordi.project.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jordi.project.API.TVResults;
import com.example.jordi.project.Contingut_Series;
import com.example.jordi.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 22/05/2018.
 */

public class RecyclerViewAdapterr extends RecyclerView.Adapter<RecyclerViewAdapterr.MyViewHolder> {

    //Declaracio objectes
    private Context mContext;
    private List<Serie> mSerie;
    ///////////////////////////////

    //Constructor
    public RecyclerViewAdapterr(Context mContext, List<Serie> mSerie) {
        this.mContext = mContext;
        this.mSerie = mSerie;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        mview = mInflater.inflate(R.layout.cardview_targeta, parent, false);
        return new MyViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        //Obtenim el contingut de l'arraylist. En aquest cas nomes la portada
        holder.imagen_serie.setImageResource(mSerie.get(position).getPortada());


        //Quan fan click a la cardview
        holder.targeta_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Crea un nou intent, i obte les dades del Arraylist i els posa una paraula clau per diferenciar-ho
                Intent intent = new Intent(mContext, Contingut_Series.class);
                intent.putExtra("Titol", mSerie.get(position).getSerieTitol());
                intent.putExtra("Descripcio", mSerie.get(position).getDesc());
                intent.putExtra("Portada", mSerie.get(position).getPortada());
                intent.putExtra("Temporades",mSerie.get(position).getNum_seasons());
                intent.putExtra("Capitols",mSerie.get(position).getNum_episodes());
                intent.putExtra("Puntuacio",mSerie.get(position).getPuntuacio());

                mContext.startActivity(intent);

                //////////////////////////////////////
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSerie.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {


        //Declaracio d'objectes
        TextView titulo_serie;
        ImageView imagen_serie;
        CardView targeta_series;
        TextView num_seasons;
        TextView num_episodes;
        TextView puntuacio;
        ////////////////////////////

        public MyViewHolder(View itemView) {
            super(itemView);

            //Assignem els objectes
            imagen_serie = (ImageView) itemView.findViewById(R.id.targeta_img);
            targeta_series = (CardView) itemView.findViewById(R.id.targeta_id);
            num_seasons = (TextView) itemView.findViewById(R.id.TextView_Seasons);
            num_episodes = (TextView) itemView.findViewById(R.id.TextView_Capitols);
            puntuacio = (TextView) itemView.findViewById(R.id.textview_puntuacio);
            /////////////////////////////////////////////////////////////////////////////
        }
    }

    public void updateList(List<Serie> llistatseries){

        mSerie = new ArrayList<>();
        mSerie.addAll(llistatseries);
        notifyDataSetChanged();
    }
}