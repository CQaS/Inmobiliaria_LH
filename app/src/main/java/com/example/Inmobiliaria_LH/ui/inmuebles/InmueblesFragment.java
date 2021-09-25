package com.example.Inmobiliaria_LH.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Inmobiliaria_LH.R;
import com.example.Inmobiliaria_LH.modelo.Inmueble;

import java.util.ArrayList;
import java.util.List;


public class InmueblesFragment extends Fragment
{
    private RecyclerView rvInmuebles;
    private InmueblesViewModel inmueblesViewModel;
    private InmuebleAdapter adapter;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

        View root = inflater.inflate(R.layout.inmuebles_fragment, container, false);
        context = root.getContext();

        inicializar(root);

        return root;
    }

    private void inicializar(View view)
    {
        rvInmuebles = view.findViewById(R.id.rvInmuebles);

        inmueblesViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);

        inmueblesViewModel.getInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>()
        {
            @Override
            public void onChanged( ArrayList<Inmueble> inmuebles)
            {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                rvInmuebles.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleAdapter(context, inmuebles, getLayoutInflater());
                rvInmuebles.setAdapter(adapter);
            }
        });

        inmueblesViewModel.mostrarInmuebles();
    }

}

