package com.cerme.vibecodingairport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VueloAdapter extends RecyclerView.Adapter<VueloAdapter.VueloViewHolder> {
    private List<Vuelo> vuelos;

    public VueloAdapter(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    @NonNull
    @Override
    public VueloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new VueloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VueloViewHolder holder, int position) {
        Vuelo vuelo = vuelos.get(position);
        holder.text1.setText(vuelo.getNumeroVuelo() + " - " + vuelo.getAerolinea());
        holder.text2.setText("Destino: " + vuelo.getDestino() + " | Salida: " + vuelo.getHoraSalida());
    }

    @Override
    public int getItemCount() {
        return vuelos.size();
    }

    public static class VueloViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        public VueloViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}

