package com.upiiz.practica_01_02_03.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.upiiz.practica_01_02_03.R;
import com.upiiz.practica_01_02_03.models.Usuario;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Usuario> listaUsuarios;

    private ArrayList<Usuario> listaFiltrada;

    public ChatAdapter(Context ctx, ArrayList<Usuario> listaUsuarios) {
        this.ctx = ctx;
        this.listaUsuarios = listaUsuarios;
        this.listaFiltrada = new ArrayList<>(listaUsuarios);
    }


    @Override
    public int getCount() {
        return listaFiltrada.size();
    }

    @Override
    public Object getItem(int position) {
        return listaFiltrada.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Comprobar que hay una instancia de convertView
        if(convertView==null){
            convertView = LayoutInflater.from(ctx).
                    inflate(R.layout.item_chat,parent,false);
        }
        TextView tvNombreUsuario = convertView.findViewById(R.id.tvNombreUsuario);
        TextView tvFechaUltimaConexion = convertView.findViewById(R.id.tvFechaUltimaConexion);
        TextView tvUltimoMensaje = convertView.findViewById(R.id.tvUltimoMensaje);
        ImageView imageView = convertView.findViewById(R.id.imgFotoPerfil);

        Usuario usuario = listaFiltrada.get(position);

        tvNombreUsuario.setText(usuario.getNombre());
        tvFechaUltimaConexion.setText(usuario.getHora());
        tvUltimoMensaje.setText(usuario.getUltimoMensaje());
        imageView.setImageResource(usuario.getFotoResId());
        return convertView;
    }

    public void filtrar(String texto){
        listaFiltrada.clear();

        if(texto.trim().isEmpty()){
            listaFiltrada.addAll(listaUsuarios);
        }else {
            String busqueda = texto.toLowerCase().trim();
            for(Usuario usuario : listaUsuarios){
                if(usuario.getNombre().toLowerCase().contains(busqueda)|| usuario.getUltimoMensaje().toLowerCase().contains(busqueda)){
                    listaFiltrada.add(usuario);
                }
            }
        }
        //Refrescar la vista en la pantalla
        notifyDataSetChanged();
    }
}
