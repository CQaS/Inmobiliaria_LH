package com.example.Inmobiliaria_LH.ui.inquilinos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Inmobiliaria_LH.modelo.Inmueble;
import com.example.Inmobiliaria_LH.modelo.Inquilino;
import com.example.Inmobiliaria_LH.request.ApiClient;

public class InquilinoViewModel extends ViewModel
{

    private MutableLiveData<Inquilino> inquilinoMutable;

    public InquilinoViewModel()
    {
        super();
    }

    public LiveData<Inquilino> getInquilino()
    {
        if (inquilinoMutable == null)
        {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }

    
    public void cargarInquilino(Bundle bundle)
    {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient api = ApiClient.getApi();
		
		//obtenemos el inquilino de ese Inmueble que viene en bundle...
        Inquilino inquilino = api.obtenerInquilino(inmueble);
        inquilinoMutable.setValue(inquilino);

    }
}
