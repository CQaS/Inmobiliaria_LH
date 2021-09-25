package com.example.Inmobiliaria_LH.ui.inquilinos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Inmobiliaria_LH.modelo.Inmueble;
import com.example.Inmobiliaria_LH.modelo.Inquilino;
import com.example.Inmobiliaria_LH.request.ApiClient;

import java.util.ArrayList;

public class InquilinosViewModel extends ViewModel
{
    private Context context;
    ArrayList<Inmueble> in;
    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;

    public LiveData<ArrayList<Inmueble>> getInmuebles()
    {
        if (inmueblesMutable == null)
        {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    //inmuebles del Inquilino...
    public void cargarInmueblesConInquilino()
    {
        in = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        inmueblesMutable.setValue(in);
    }

}