package com.example.Inmobiliaria_LH.ui.contratos;

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
import com.example.Inmobiliaria_LH.modelo.Pago;

import java.util.ArrayList;

public class PagosFragment extends Fragment
{

    private PagosViewModel pagosViewModel;
    private RecyclerView rvInmuebles;
    private Context context;
    private InmuebleConPagosAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.pagos_fragment, container, false);
        context = root.getContext();

        inicializar(root);
        return root;
    }

    private void inicializar(View view)
    {
        pagosViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
        rvInmuebles = view.findViewById(R.id.rvInmuebles);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, RecyclerView.VERTICAL, false);
        rvInmuebles.setLayoutManager(gridLayoutManager);

        pagosViewModel.getPagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>()
        {
            @Override
            public void onChanged(ArrayList<Pago> pagos)
            {
                adapter = new InmuebleConPagosAdapter(pagos, context, getLayoutInflater());
                rvInmuebles.setAdapter(adapter);
            }
        });

        pagosViewModel.cargarInmueblesConPagos(getArguments());
    }
}