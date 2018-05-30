package com.mide.adolf.socialmide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SocialMideAdapter
     extends RecyclerView.Adapter<SocialMideAdapter.SocialMideViewHolder>
    implements View.OnClickListener {

        private ArrayList<PostedMideObject> items;
        private View.OnClickListener listener;

        // Clase interna:
        // Se implementa el ViewHolder que se encargará
        // de almacenar la vista del elemento y sus datos
        public static class SocialMideViewHolder
                extends RecyclerView.ViewHolder {

            private TextView TextView_nombre;
            private TextView TextView_descripcion;

            public SocialMideViewHolder(View itemView) {
                super(itemView);

                TextView_descripcion = (TextView) itemView.findViewById(R.id.tw_descripcion);
                TextView_nombre = (TextView) itemView.findViewById(R.id.tw_nombre);
            }

            public void SocialMideBind(PostedMideObject item) {
                TextView_nombre.setText(item.getNombreRuta());
                TextView_descripcion.setText(item.getDescripcion());
            }
        }

        // Contruye el objeto adaptador recibiendo la lista de datos
    public SocialMideAdapter(@NonNull ArrayList<PostedMideObject> items) {
            this.items = items;
        }

        // Se encarga de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección.
        // Infla la vista del layout y crea y devuelve el objeto ViewHolder
        @Override
        public SocialMideAdapter.SocialMideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.social_list_mide_item, parent, false);
            row.setOnClickListener(this);
            SocialMideAdapter.SocialMideViewHolder smvh = new SocialMideAdapter.SocialMideViewHolder(row);
            return smvh;
        }

        // Se encarga de actualizar los datos de un ViewHolder ya existente.
        @Override
        public void onBindViewHolder(SocialMideAdapter.SocialMideViewHolder viewHolder, int position) {
            PostedMideObject item = items.get(position);
            viewHolder.SocialMideBind(item);
        }

        // Indica el número de elementos de la colección de datos.
        @Override
        public int getItemCount() {
            return items.size();
        }

        // Asigna un listener
        public void setOnClickListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onClick(view);
        }
}
