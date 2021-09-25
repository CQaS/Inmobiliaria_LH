package com.example.Inmobiliaria_LH.ui.inquilinos;

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

public class InquilinosFragment extends Fragment
{
    private InquilinosViewModel inquilinosViewModel;
    private RecyclerView rvInquilinos;
    private InmuebleConInquilinoAdapter adapter;
    Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.inquilinos_fragment, container, false);
        context = root.getContext();

        inicializar(root);
        return root;
    }



    private void inicializar(View view)
    {
        rvInquilinos = view.findViewById(R.id.rvInquilinos);

        inquilinosViewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);

        inquilinosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>()
        {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles)
            {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);
                rvInquilinos.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleConInquilinoAdapter(context, inmuebles, getLayoutInflater());
                rvInquilinos.setAdapter(adapter);
            }
        });
        inquilinosViewModel.cargarInmueblesConInquilino();
    }


}
