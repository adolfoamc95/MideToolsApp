package com.mide.adolf.socialmide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Clase adaptador ulitizado para cargar los MIDEs creados en la lista principal
 */
public class MideAdapter
    extends RecyclerView.Adapter<MideAdapter.MideViewHolder>
    implements View.OnClickListener {

    private ArrayList<MideObject> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class MideViewHolder
            extends RecyclerView.ViewHolder {

        private TextView TextView_nombre;
        private TextView TextView_ano;
        private TextView TextView_id;

        public MideViewHolder(View itemView) {
            super(itemView);

            TextView_id = (TextView) itemView.findViewById(R.id.tw_id);
            TextView_nombre = (TextView) itemView.findViewById(R.id.tw_nombre);
            TextView_ano = (TextView) itemView.findViewById(R.id.tw_ano);
        }

        public void MideBind(MideObject item) {

            TextView_id.setText("MIDE");
            TextView_nombre.setText(item.getNombre());
            TextView_ano.setText(item.getAño());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public MideAdapter(@NonNull ArrayList<MideObject> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección.
    // Infla la vista del layout y crea y devuelve el objeto ViewHolder
    @Override
    public MideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_mides_item, parent, false);
        row.setOnClickListener(this);
        MideViewHolder mvh = new MideViewHolder(row);
        return mvh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(MideViewHolder viewHolder, int position) {
        MideObject item = items.get(position);
        viewHolder.MideBind(item);
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
