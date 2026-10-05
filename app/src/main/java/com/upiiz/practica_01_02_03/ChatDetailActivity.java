package com.upiiz.practica_01_02_03;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChatDetailActivity extends AppCompatActivity {

    private TextView tvNombreContacto, tvEstadoContacto;
    private ImageView btnRegresar, btnVideoLlamada, btnLlamada, btnEnviar;
    private EditText etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Enlazar las vistas del XML
        tvNombreContacto = findViewById(R.id.tvNombreContactoDetail);
        tvEstadoContacto = findViewById(R.id.tvEstadoContactoDetail);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnVideoLlamada = findViewById(R.id.btnVideoLlamadaChat);
        btnLlamada = findViewById(R.id.btnLlamadaChat);
        btnEnviar = findViewById(R.id.btnEnviar);
        etMensaje = findViewById(R.id.etMensaje);

        // 2. Recibir el Extra enviado desde MainActivity (Nombre del usuario)
        if (getIntent() != null && getIntent().hasExtra("NOMBRE_USUARIO")) {
            String nombreUsuario = getIntent().getStringExtra("NOMBRE_USUARIO");
            tvNombreContacto.setText(nombreUsuario);
        }

        // 3. Acción para el botón de regresar
        btnRegresar.setOnClickListener(v -> finish());

        // 4. Acciones opcionales para los demás botones
        btnLlamada.setOnClickListener(v ->
                Toast.makeText(this, "Llamando a " + tvNombreContacto.getText(), Toast.LENGTH_SHORT).show()
        );

        btnVideoLlamada.setOnClickListener(v ->
                Toast.makeText(this, "Videollamada con " + tvNombreContacto.getText(), Toast.LENGTH_SHORT).show()
        );

        btnEnviar.setOnClickListener(v -> {
            String mensaje = etMensaje.getText().toString().trim();
            if (!mensaje.isEmpty()) {
                Toast.makeText(this, "Mensaje enviado: " + mensaje, Toast.LENGTH_SHORT).show();
                etMensaje.setText("");
            }
        });
    }
}