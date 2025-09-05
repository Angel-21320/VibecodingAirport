package com.cerme.vibecodingairport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Pasajero> pasajeros = new ArrayList<>();
    private List<Vuelo> vuelos = new ArrayList<>();
    private PasajeroAdapter pasajeroAdapter;
    private VueloAdapter vueloAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvPassengers = findViewById(R.id.rvPassengers);
        RecyclerView rvFlights = findViewById(R.id.rvFlights);
        rvPassengers.setLayoutManager(new LinearLayoutManager(this));
        rvFlights.setLayoutManager(new LinearLayoutManager(this));
        pasajeroAdapter = new PasajeroAdapter(pasajeros);
        vueloAdapter = new VueloAdapter(vuelos);
        rvPassengers.setAdapter(pasajeroAdapter);
        rvFlights.setAdapter(vueloAdapter);

        findViewById(R.id.btnRegisterPassenger).setOnClickListener(v -> showRegisterPassengerDialog());
        findViewById(R.id.btnAddFlight).setOnClickListener(v -> showAddFlightDialog());
        findViewById(R.id.btnSearchFlight).setOnClickListener(v -> searchFlight());
    }

    private void showRegisterPassengerDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_register_passenger, null);
        EditText etNombre = dialogView.findViewById(R.id.etNombre);
        EditText etPasaporte = dialogView.findViewById(R.id.etPasaporte);
        EditText etNacionalidad = dialogView.findViewById(R.id.etNacionalidad);
        View btnHuella = dialogView.findViewById(R.id.btnHuella);

        final boolean[] huellaRegistrada = {false};
        btnHuella.setOnClickListener(v -> {
            huellaRegistrada[0] = true;
            Toast.makeText(this, "Huella registrada (simulada)", Toast.LENGTH_SHORT).show();
        });

        new AlertDialog.Builder(this)
            .setTitle("Registrar pasajero")
            .setView(dialogView)
            .setPositiveButton("Registrar", (dialog, which) -> {
                String nombre = etNombre.getText().toString().trim();
                String pasaporte = etPasaporte.getText().toString().trim();
                String nacionalidad = etNacionalidad.getText().toString().trim();
                if (nombre.isEmpty() || pasaporte.isEmpty() || nacionalidad.isEmpty()) {
                    Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                pasajeros.add(new Pasajero(nombre, pasaporte, nacionalidad, huellaRegistrada[0]));
                pasajeroAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Pasajero registrado", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }

    private void showAddFlightDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_flight, null);
        EditText etNumeroVuelo = dialogView.findViewById(R.id.etNumeroVuelo);
        EditText etAerolinea = dialogView.findViewById(R.id.etAerolinea);
        EditText etDestino = dialogView.findViewById(R.id.etDestino);
        EditText etHoraSalida = dialogView.findViewById(R.id.etHoraSalida);

        new AlertDialog.Builder(this)
            .setTitle("Agregar vuelo")
            .setView(dialogView)
            .setPositiveButton("Agregar", (dialog, which) -> {
                String numeroVuelo = etNumeroVuelo.getText().toString().trim();
                String aerolinea = etAerolinea.getText().toString().trim();
                String destino = etDestino.getText().toString().trim();
                String horaSalida = etHoraSalida.getText().toString().trim();
                if (numeroVuelo.isEmpty() || aerolinea.isEmpty() || destino.isEmpty() || horaSalida.isEmpty()) {
                    Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (Vuelo v : vuelos) {
                    if (v.getNumeroVuelo().equalsIgnoreCase(numeroVuelo)) {
                        Toast.makeText(this, "El número de vuelo ya existe", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                vuelos.add(new Vuelo(numeroVuelo, aerolinea, destino, horaSalida));
                vueloAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Vuelo agregado", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }

    private void searchFlight() {
        EditText etSearchFlight = findViewById(R.id.etSearchFlight);
        String query = etSearchFlight.getText().toString().trim().toLowerCase();
        List<Vuelo> resultados = new ArrayList<>();
        for (Vuelo v : vuelos) {
            if (v.getNumeroVuelo().toLowerCase().contains(query) ||
                v.getAerolinea().toLowerCase().contains(query) ||
                v.getDestino().toLowerCase().contains(query)) {
                resultados.add(v);
            }
        }
        vueloAdapter = new VueloAdapter(resultados);
        RecyclerView rvFlights = findViewById(R.id.rvFlights);
        rvFlights.setAdapter(vueloAdapter);
        vueloAdapter.notifyDataSetChanged();
    }
}