package com.upiiz.practica_01_02_03;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.upiiz.practica_01_02_03.adapters.ChatAdapter;
import com.upiiz.practica_01_02_03.models.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationBarView.OnItemSelectedListener {
    ListView lvUsuarios;
    ArrayList<Usuario> listaUsuarios;
    BottomNavigationView bottomNavigationView;
    ImageButton btnCamara, btnMasOpciones;
    FloatingActionButton btnNuevoChat;
    EditText editText;

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

        // 1. Enlazar vistas
        lvUsuarios = findViewById(R.id.lvUsuarios);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        editText = findViewById(R.id.etBuscar);
        btnCamara = findViewById(R.id.btnCamara);
        btnMasOpciones = findViewById(R.id.btnMasOpciones);
        btnNuevoChat = findViewById(R.id.fabNuevoChat);

        cargarUsuarios();

        // 2. Asignar el adaptador al ListView
        ChatAdapter adapter = new ChatAdapter(this, listaUsuarios);
        lvUsuarios.setAdapter(adapter);

        // 3. Clics sobre botones y navegación
        bottomNavigationView.setOnItemSelectedListener(this);
        btnCamara.setOnClickListener(this);
        btnMasOpciones.setOnClickListener(this);
        btnNuevoChat.setOnClickListener(this);

        // Configurar clic en los ítems de la lista de chats
        lvUsuarios.setOnItemClickListener((parent, view, position, id) -> {
            // Prueba para ver si entra al listener
            //Toast.makeText(MainActivity.this, "Clic en posición: " + position, Toast.LENGTH_SHORT).show();
            // Obtener el usuario mediante parent.getItemAtPosition para soportar filtros de búsqueda
            Usuario usuarioSeleccionado = (Usuario) parent.getItemAtPosition(position);

            if (usuarioSeleccionado != null) {
                Intent intent = new Intent(MainActivity.this, ChatDetailActivity.class);
                intent.putExtra("NOMBRE_USUARIO", usuarioSeleccionado.getNombre());
                startActivity(intent);
            }
        });

        // 4. Escuchar lo que se escribe en el EditText de búsqueda
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }
        });
    }

    public void cargarUsuarios() {
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Carlos Gómez", "¡Hola! ¿Cómo estás?", "10:45 a. m.", R.drawable.user));
        listaUsuarios.add(new Usuario("María López", "Nos vemos en la clase.", "09:30 a. m.", R.drawable.user));
        listaUsuarios.add(new Usuario("Grupo Práctica", "Ya envié el repositorio.", "Ayer", R.drawable.user));
        listaUsuarios.add(new Usuario("Ana Martínez", "Ok, gracias.", "Ayer", R.drawable.user));
    }

    public void botonCamara() {
        Toast.makeText(this, "Botón de la cámara", Toast.LENGTH_LONG).show();
    }

    public void botonNuevoChat() {
        Toast.makeText(this, "Nuevo chat", Toast.LENGTH_LONG).show();
    }

    public void botonMasOpciones(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.bottom_mas_opciones, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();

            if (id == R.id.action_nuevo_grupo) {
                Toast.makeText(MainActivity.this, "Nuevo Grupo", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nuevaComunidad) {
                Toast.makeText(MainActivity.this, "Nueva Comunidad", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.listaDifusion) {
                Toast.makeText(MainActivity.this, "Lista de difusión", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.dispositivosVinculados) {
                Toast.makeText(MainActivity.this, "Dispositivos vinculados", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.marcarLeidos) {
                Toast.makeText(MainActivity.this, "Marcar como leídos", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.ajustes) {
                Toast.makeText(MainActivity.this, "Ajustes", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        popup.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_chats) {
            iniciarChats();
            return true;
        } else if (itemId == R.id.nav_novedades) {
            iniciarNovedades();
            return true;
        } else if (itemId == R.id.nav_llamadas) {
            startActivity(new Intent(this, LlamadasActivity.class));
            return true;
        }
        return false;
    }

    public void iniciarChats() {
        Toast.makeText(this, "Ya estás en Chats", Toast.LENGTH_SHORT).show();
    }

    public void iniciarNovedades() {
        Intent intent = new Intent(this, NovedadesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCamara) {
            botonCamara();
        } else if (v.getId() == R.id.btnMasOpciones) {
            botonMasOpciones(v);
        } else if (v.getId() == R.id.fabNuevoChat) {
            botonNuevoChat();
        }
    }
}