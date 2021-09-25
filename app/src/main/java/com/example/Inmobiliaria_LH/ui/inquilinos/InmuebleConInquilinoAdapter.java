package com.example.Inmobiliaria_LH.ui.inquilinos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.Inmobiliaria_LH.R;
import com.example.Inmobiliaria_LH.modelo.Inmueble;

import java.util.List;

public class InmuebleConInquilinoAdapter extends RecyclerView.Adapter<InmuebleConInquilinoAdapter.ViewHolder>
{
    private List<Inmueble> inmuebles;
    private Context context;
    private LayoutInflater inflater;

    public InmuebleConInquilinoAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater inflater)
    {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.item_inquilino_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Inmueble unInm = inmuebles.get(position);

        holder.tvDireccion.setText(inmuebles.get(position).getDireccion());

        //setea la imagen....
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmueble);

        holder.btInquilino.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
				Toast.makeText(context, "MOSTRARA DATOS DEL INQUILINO", Toast.LENGTH_LONG).show();
				
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", unInm);
                Navigation.findNavController
                        ((Activity) context, R.id.nav_host_fragment).navigate(R.id.inquilinoFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDireccion;
        ImageView ivImagenInmueble;
        Button btInquilino;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btInquilino = itemView.findViewById(R.id.btInquilino);
        }
    }

}

