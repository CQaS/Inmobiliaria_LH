package com.example.Inmobiliaria_LH.ui.inmuebles;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Inmobiliaria_LH.modelo.Inmueble;
import com.example.Inmobiliaria_LH.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InmueblesViewModel extends AndroidViewModel
{

    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;
    ArrayList<Inmueble> in;
    private Context context;

    public InmueblesViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmueblesMutable()
    {
        if (inmueblesMutable == null)
        {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;

    }
	
	//inmuebeles del propietario....
    public void mostrarInmuebles()
    {
        in = ApiClient.getApi().obtenerPropiedades();
        inmueblesMutable.setValue(in);

    }



}
