package com.example.Inmobiliaria_LH.ui.contratos;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Inmobiliaria_LH.modelo.Contrato;
import com.example.Inmobiliaria_LH.modelo.Inmueble;
import com.example.Inmobiliaria_LH.modelo.Inquilino;
import com.example.Inmobiliaria_LH.request.ApiClient;


public class ContratosViewModel extends AndroidViewModel
{

    private MutableLiveData<List<Inmueble>> inmueblesMutable;
    ArrayList<Inmueble> in;
    private Context context;

    public ContratosViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<List<Inmueble>> getInmueblesMutable()
    {
        if (inmueblesMutable == null)
        {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void mostrarInmueblesConContrato()
    {
        in = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        inmueblesMutable.setValue(in);

        //Toast.makeText(context, "Sin respuesta", Toast.LENGTH_LONG).show();

    }

}
