package com.cerme.vibecodingairport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PasajeroAdapter extends RecyclerView.Adapter<PasajeroAdapter.PasajeroViewHolder> {
    private List<Pasajero> pasajeros;

    public PasajeroAdapter(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    @NonNull
    @Override
    public PasajeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new PasajeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasajeroViewHolder holder, int position) {
        Pasajero pasajero = pasajeros.get(position);
        holder.text1.setText(pasajero.getNombre() + " (" + pasajero.getPasaporte() + ")");
        holder.text2.setText("Nacionalidad: " + pasajero.getNacionalidad() + (pasajero.isHuellaRegistrada() ? " | Huella: Sí" : " | Huella: No"));
    }

    @Override
    public int getItemCount() {
        return pasajeros.size();
    }

    public static class PasajeroViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        public PasajeroViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}

