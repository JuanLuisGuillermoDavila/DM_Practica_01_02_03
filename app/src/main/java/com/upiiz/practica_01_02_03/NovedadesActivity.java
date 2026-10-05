package com.upiiz.practica_01_02_03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;
import com.upiiz.practica_01_02_03.models.Canal;
import com.upiiz.practica_01_02_03.models.CanalSugerido;
import com.upiiz.practica_01_02_03.models.Estado;

import java.util.ArrayList;

public class NovedadesActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, View.OnClickListener {

    BottomNavigationView bottomNavigationView;
    LinearLayout contenedorEstado;
    LinearLayout contenedorCanales;
    LinearLayout contenedorBuscarCanales;
    MaterialButton btnExplorarMas;
    ImageButton btnMasOpciones;
    FloatingActionButton btnNuevoEstado;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_novedades);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Contenedores
        contenedorEstado = findViewById(R.id.contenedorEstados);
        contenedorCanales = findViewById(R.id.contenedorCanales);
        contenedorBuscarCanales = findViewById(R.id.contenedorBuscarCanales);
        btnExplorarMas = findViewById(R.id.btnExplorarMas);
        btnMasOpciones = findViewById(R.id.btnMasOpcionesNovedades);
        btnNuevoEstado = findViewById(R.id.fabNuevoEstado);

        // Cargar datos en pantalla
        cargarEstadoUsuarioPrincipal();
        cargarEstadosContactos();
        cargarCanalesSeguidos();
        cargarCanalesSugeridos();

        // Barra de navegación
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_novedades);
        bottomNavigationView.setOnItemSelectedListener(this);
        //boton de mas opciones
        btnMasOpciones.setOnClickListener(this);
        btnNuevoEstado.setOnClickListener(this);
        btnExplorarMas.setOnClickListener(this);
    }

    private void cargarEstadoUsuarioPrincipal() {
        View estadoUsuario = getLayoutInflater().inflate(R.layout.item_estado_usuario, contenedorEstado, false);

        TextView tvNombre = estadoUsuario.findViewById(R.id.tvNombreUsuarioEstado);
        ImageView fondo = estadoUsuario.findViewById(R.id.imgFondoEstadoUsuario);
        ShapeableImageView fotoPerfil = estadoUsuario.findViewById(R.id.imgUsuarioEstado);

        tvNombre.setText("Mi Estado");
        fondo.setImageResource(R.drawable.estado);
        fotoPerfil.setImageResource(R.drawable.user);

        // Borde verde para estado propio sin ver / activo
        fotoPerfil.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#25D366")));

        estadoUsuario.setOnClickListener(v ->
                Toast.makeText(this, "Ver mi estado", Toast.LENGTH_SHORT).show()
        );

        contenedorEstado.addView(estadoUsuario);
    }

    private void cargarEstadosContactos() {
        ArrayList<Estado> listaEstados = new ArrayList<>();
        listaEstados.add(new Estado("Carlos Gómez", R.drawable.user, R.drawable.estado2, false));
        listaEstados.add(new Estado("María López", R.drawable.user, R.drawable.estado, false));
        listaEstados.add(new Estado("Juan Pérez", R.drawable.user, R.drawable.estado2, true));  // Visto (Gris)
        listaEstados.add(new Estado("Ana Torres", R.drawable.user, R.drawable.estado, true));   // Visto (Gris)

        for (Estado estado : listaEstados) {
            View vistaEstado = getLayoutInflater().inflate(R.layout.item_estado_usuario, contenedorEstado, false);

            TextView tvNombre = vistaEstado.findViewById(R.id.tvNombreUsuarioEstado);
            ImageView imgFondo = vistaEstado.findViewById(R.id.imgFondoEstadoUsuario);
            ShapeableImageView imgPerfil = vistaEstado.findViewById(R.id.imgUsuarioEstado);

            tvNombre.setText(estado.getNombre());
            imgFondo.setImageResource(estado.getFotoEstadoResId());
            imgPerfil.setImageResource(estado.getFotoPerfilResId());

            // Verde si no se ha visto, Gris si ya fue visto
            if (estado.isVisto()) {
                imgPerfil.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));
            } else {
                imgPerfil.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#25D366")));
            }

            vistaEstado.setOnClickListener(v ->
                    Toast.makeText(this, "Viendo estado de " + estado.getNombre(), Toast.LENGTH_SHORT).show()
            );

            contenedorEstado.addView(vistaEstado);
        }
    }

    private void cargarCanalesSeguidos() {
        ArrayList<Canal> listaCanales = new ArrayList<>();
        // Canal con 2 mensajes sin leer
        listaCanales.add(new Canal("WhatsApp", "New: Support your teen with options...", "30/9/2026", R.drawable.user, 2));

        for (Canal canal : listaCanales) {
            View vistaCanal = getLayoutInflater().inflate(R.layout.item_canal, contenedorCanales, false);

            TextView tvTitulo = vistaCanal.findViewById(R.id.tvTituloCanal);
            TextView tvMensaje = vistaCanal.findViewById(R.id.tvUltimoMensajeCanal);
            TextView tvFecha = vistaCanal.findViewById(R.id.tvFechaCanal);
            TextView tvSinLeer = vistaCanal.findViewById(R.id.tvMensajesSinLeerCanal);
            ShapeableImageView imgFoto = vistaCanal.findViewById(R.id.imgFotoCanal);

            tvTitulo.setText(canal.getTitulo());
            tvMensaje.setText(canal.getUltimoMensaje());
            tvFecha.setText(canal.getFecha());
            imgFoto.setImageResource(canal.getFotoResId());

            // Mostrar u ocultar el contador de mensajes sin leer
            if (canal.getMensajesSinLeer() > 0) {
                tvSinLeer.setVisibility(View.VISIBLE);
                tvSinLeer.setText(String.valueOf(canal.getMensajesSinLeer()));
            } else {
                tvSinLeer.setVisibility(View.GONE);
            }

            vistaCanal.setOnClickListener(v ->
                    Toast.makeText(this, "Abriendo canal: " + canal.getTitulo(), Toast.LENGTH_SHORT).show()
            );

            contenedorCanales.addView(vistaCanal);
        }
    }

    private void cargarCanalesSugeridos() {
        ArrayList<CanalSugerido> listaSugeridos = new ArrayList<>();
        listaSugeridos.add(new CanalSugerido("Grupo de Fede", "10.1 mill. seguidores", R.drawable.user));
        listaSugeridos.add(new CanalSugerido("Kimberly Loaiza", "3.8 mill. seguidores", R.drawable.user));
        listaSugeridos.add(new CanalSugerido("STICKERS", "1.6 mill. seguidores", R.drawable.user));
        listaSugeridos.add(new CanalSugerido("CHIVAS", "2.2 mill. seguidores", R.drawable.user));
        listaSugeridos.add(new CanalSugerido("FRASES INFINITAS", "329 mil seguidores", R.drawable.user));

        for (CanalSugerido canal : listaSugeridos) {
            View vistaSugerido = getLayoutInflater().inflate(R.layout.item_canal_sugerido, contenedorBuscarCanales, false);

            TextView tvTitulo = vistaSugerido.findViewById(R.id.tvTituloCanalSugerido);
            TextView tvSeguidores = vistaSugerido.findViewById(R.id.tvSeguidoresCanal);
            ShapeableImageView imgFoto = vistaSugerido.findViewById(R.id.imgFotoCanalSugerido);
            MaterialButton btnSeguir = vistaSugerido.findViewById(R.id.btnSeguirCanal);
            ImageView btnDescartar = vistaSugerido.findViewById(R.id.btnDescartarCanal);

            tvTitulo.setText(canal.getTitulo());
            tvSeguidores.setText(canal.getSeguidores());
            imgFoto.setImageResource(canal.getFotoResId());

            btnSeguir.setOnClickListener(v ->
                    Toast.makeText(this, "Siguiendo a " + canal.getTitulo(), Toast.LENGTH_SHORT).show()
            );

            btnDescartar.setOnClickListener(v ->
                    contenedorBuscarCanales.removeView(vistaSugerido)
            );

            contenedorBuscarCanales.addView(vistaSugerido);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_chats) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (itemId == R.id.nav_novedades) {
            Toast.makeText(this, "Ya estás en Novedades", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.nav_llamadas) {
            startActivity(new Intent(this,LlamadasActivity.class));
        }
        return false;
    }

    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.btnMasOpcionesNovedades) {
           botonMasOpciones(v);
       } else if (v.getId()==R.id.fabNuevoEstado) {
           Toast.makeText(this, "Nuevo estado", Toast.LENGTH_SHORT).show();
       } else if (v.getId()==R.id.btnExplorarMas) {
           Toast.makeText(this, "Explorando más canales...", Toast.LENGTH_SHORT).show();
       }

    }
    public void botonMasOpciones(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.bottom_mas_opciones_novedades,popup.getMenu());
        // Manejar el clic en cada opción del menú
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_crear_canal) {
                    // Acción para Nuevo Grupo
                    Toast.makeText(NovedadesActivity.this, "Nuevo Canal", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.action_privacidad_estados) {
                    // Acción para Nueva Comunidad
                    Toast.makeText(NovedadesActivity.this, "Privacidad de estados", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.action_destacado) {
                    // Acción para Lista de difusión
                    Toast.makeText(NovedadesActivity.this, "Destacado", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.action_preferencia_anuncios) {
                    // Acción para Dispositivos vinculados
                    Toast.makeText(NovedadesActivity.this, "Preferencia de anuncios", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.action_ajustes) {
                    // Acción para Ajustes
                    Toast.makeText(NovedadesActivity.this, "Ajustes", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        // Mostrar el menú flotante
        popup.show();
    }
}