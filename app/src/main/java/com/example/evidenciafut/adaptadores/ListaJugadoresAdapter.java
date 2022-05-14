package com.example.evidenciafut.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evidenciafut.R;
import com.example.evidenciafut.entidades.Jugadores;

import java.util.ArrayList;

public class ListaJugadoresAdapter extends RecyclerView.Adapter<ListaJugadoresAdapter.JugadoresViewHolder> {

    ArrayList<Jugadores> listaJugadores;

    public ListaJugadoresAdapter(ArrayList<Jugadores> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    @NonNull
    @Override
    public JugadoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_jugador,null,false);
        return new JugadoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JugadoresViewHolder holder, int position) {

        holder.viewNombre.setText(listaJugadores.get(position).getNombre());
        holder.viewApellido.setText(listaJugadores.get(position).getApellido());
        holder.viewNumero.setText(listaJugadores.get(position).getNumero());

    }

    @Override
    public int getItemCount() {
       return listaJugadores.size();
    }

    public class JugadoresViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewApellido, viewNumero;

        public JugadoresViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNomre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewNumero = itemView.findViewById(R.id.viewNumero);
        }
    }
}
