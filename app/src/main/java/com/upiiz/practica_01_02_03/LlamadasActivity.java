package com.upiiz.practica_01_02_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;
import com.upiiz.practica_01_02_03.models.Favorito;
import com.upiiz.practica_01_02_03.models.LlamadaReciente;

import java.util.ArrayList;

public class LlamadasActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, View.OnClickListener {

    private BottomNavigationView bottomNavigationView;
    private LinearLayout contenedorFavoritos;
    private LinearLayout contenedorRecientes;
    private ImageView btnMasFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_llamadas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contenedorFavoritos = findViewById(R.id.contenedorFavoritos);
        contenedorRecientes = findViewById(R.id.contenedorRecientes);
        btnMasFavoritos = findViewById(R.id.btnMasFavoritos);

        btnMasFavoritos.setOnClickListener(this);

        // Cargar listas
        cargarFavoritos();
        cargarRecientes();

        // Configurar navegación
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_llamadas); // Asegúrate de tener este id en tu menú
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    private void cargarFavoritos() {
        ArrayList<Favorito> listaFavoritos = new ArrayList<>();
        listaFavoritos.add(new Favorito("Carlos Gómez", R.drawable.user));
        listaFavoritos.add(new Favorito("María López", R.drawable.user));

        for (Favorito favorito : listaFavoritos) {
            View vistaFavorito = getLayoutInflater().inflate(R.layout.item_favorito, contenedorFavoritos, false);

            TextView tvNombre = vistaFavorito.findViewById(R.id.tvNombreFavorito);
            ShapeableImageView imgFoto = vistaFavorito.findViewById(R.id.imgFotoFavorito);
            ImageView btnAudio = vistaFavorito.findViewById(R.id.btnLlamarAudioFavorito);
            ImageView btnVideo = vistaFavorito.findViewById(R.id.btnLlamarVideoFavorito);

            tvNombre.setText(favorito.getNombre());
            imgFoto.setImageResource(favorito.getFotoResId());

            btnAudio.setOnClickListener(v ->
                    Toast.makeText(this, "Llamando por audio a " + favorito.getNombre(), Toast.LENGTH_SHORT).show()
            );

            btnVideo.setOnClickListener(v ->
                    Toast.makeText(this, "Llamando por video a " + favorito.getNombre(), Toast.LENGTH_SHORT).show()
            );

            contenedorFavoritos.addView(vistaFavorito);
        }
    }

    private void cargarRecientes() {
        ArrayList<LlamadaReciente> listaRecientes = new ArrayList<>();
        listaRecientes.add(new LlamadaReciente("Juan Pérez", "Ayer, 4:15 PM", R.drawable.user));
        listaRecientes.add(new LlamadaReciente("+52 492 123 4567", "3 de octubre, 10:30 AM", R.drawable.user));
        listaRecientes.add(new LlamadaReciente("Ana Torres", "2 de octubre, 8:00 PM", R.drawable.user));

        for (LlamadaReciente reciente : listaRecientes) {
            View vistaReciente = getLayoutInflater().inflate(R.layout.item_llamada_reciente, contenedorRecientes, false);

            TextView tvNombreTel = vistaReciente.findViewById(R.id.tvNombreTelefonoReciente);
            TextView tvFecha = vistaReciente.findViewById(R.id.tvFechaLlamada);
            ShapeableImageView imgFoto = vistaReciente.findViewById(R.id.imgFotoReciente);
            ImageView btnAudio = vistaReciente.findViewById(R.id.btnLlamarAudioReciente);
            ImageView btnVideo = vistaReciente.findViewById(R.id.btnLlamarVideoReciente);

            tvNombreTel.setText(reciente.getNombreOrTelefono());
            tvFecha.setText(reciente.getFecha());
            imgFoto.setImageResource(reciente.getFotoResId());

            btnAudio.setOnClickListener(v ->
                    Toast.makeText(this, "Llamando por audio a " + reciente.getNombreOrTelefono(), Toast.LENGTH_SHORT).show()
            );

            btnVideo.setOnClickListener(v ->
                    Toast.makeText(this, "Llamando por video a " + reciente.getNombreOrTelefono(), Toast.LENGTH_SHORT).show()
            );

            contenedorRecientes.addView(vistaReciente);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_chats) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (itemId == R.id.nav_novedades) {
            startActivity(new Intent(this, NovedadesActivity.class));
            return true;
        } else if (itemId == R.id.nav_llamadas) {
            Toast.makeText(this, "Ya estás en Llamadas", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMasFavoritos) {
            Toast.makeText(this, "Agregar a favoritos", Toast.LENGTH_SHORT).show();
        }
    }
}