package com.example.evidenciafut.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evidenciafut.R;
import com.example.evidenciafut.entidades.Partidos;

import java.util.ArrayList;

public class ListaPartidosAdapter extends RecyclerView.Adapter<ListaPartidosAdapter.PartidosViewHolder> {
    ArrayList<Partidos> listaPartidos;

    public ListaPartidosAdapter(ArrayList<Partidos> listaPartidos){
        this.listaPartidos = listaPartidos;
    }
    @NonNull
    @Override

    public PartidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_partidos,null,false);
        return new PartidosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidosViewHolder holder, int position) {

        holder.viewID.setText(listaPartidos.get(position).getId());
        holder.viewRival.setText(listaPartidos.get(position).getRival());
        holder.viewGolesFavor.setText(listaPartidos.get(position).getGolesFavor());
        holder.viewGolesContra.setText(listaPartidos.get(position).getGolesContra());


    }

    @Override
    public int getItemCount() {
        return  listaPartidos.size();

    }

    public class PartidosViewHolder extends RecyclerView.ViewHolder {
        TextView viewRival, viewGolesFavor, viewGolesContra, viewID;
        public PartidosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewID = itemView.findViewById(R.id.viewID);
            viewRival = itemView.findViewById(R.id.viewRival);
            viewGolesFavor = itemView.findViewById(R.id.viewGolesFavor);
            viewGolesContra = itemView.findViewById(R.id.viewGolesContra);

        }
    }
}
