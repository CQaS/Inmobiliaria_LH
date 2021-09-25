package com.example.Inmobiliaria_LH.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.Inmobiliaria_LH.R;
import com.example.Inmobiliaria_LH.modelo.Inmueble;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Inmueble> inmuebles;
    private LayoutInflater inflater;



    public InmuebleAdapter(Context context, ArrayList<Inmueble> inmuebles, LayoutInflater inflater)
    {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.item_inmueble_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Inmueble unInm = inmuebles.get(position);

        holder.tvDireccion.setText(inmuebles.get(position).getDireccion());
        holder.tvPrecio.setText("$" + inmuebles.get(position).getPrecio());

        //setea la imagen....
        Glide.with(context)
                .load(unInm.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmueble);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", unInm);

                //pasa UnInmueble al fragment.....
                Navigation.findNavController
                        ((Activity) context,R.id.nav_host_fragment).navigate(R.id.inmuebleFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }


    //inicializa el CardView...
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvPrecio;
        TextView tvDireccion;
        ImageView ivImagenInmueble;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
        }
    }
}
