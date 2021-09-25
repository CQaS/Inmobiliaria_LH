package com.example.Inmobiliaria_LH.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Inmobiliaria_LH.modelo.Propietario;
import com.example.Inmobiliaria_LH.request.ApiClient;

public class PerfilViewModel extends ViewModel
{

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> mensajeMutable;


    public PerfilViewModel()
    {    }

    public LiveData<Propietario> getPropietarioMutable()
    {

        if (propietarioMutable == null)
        {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }
    

    public LiveData<String> getMensajeMutable()
    {
        if (mensajeMutable == null)
        {
            mensajeMutable = new MutableLiveData<>();
        }
        return mensajeMutable;
    }

    //usuario Logueado
    public void obtenerDatos()
    {
        Propietario p = ApiClient.getApi().obtenerUsuarioActual();
        propietarioMutable.setValue(p);

    }

    public void editarPropietario(Propietario propietario)
    {
        ApiClient api = ApiClient.getApi();
        api.actualizarPerfil(propietario);
        propietarioMutable.setValue(propietario);
        mensajeMutable.setValue("Sus datos fueron editados exitosamente!");

    }


}