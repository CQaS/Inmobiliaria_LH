package com.example.Inmobiliaria_LH;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Inmobiliaria_LH.modelo.Propietario;
import com.example.Inmobiliaria_LH.request.ApiClient;

public class MainActivityViewModel extends ViewModel
{
    private MutableLiveData<String> usuario;
    private MutableLiveData<String> mail;
    private MutableLiveData<Integer> avatars;

    public LiveData<String> getUsuario()
    {
        if (usuario == null)
            usuario = new MutableLiveData<>();
        return usuario;
    }

    public LiveData<String> getMail()
    {
        if(mail == null)
            mail = new MutableLiveData<>();
        return mail;
    }

    public LiveData<Integer> getAvatars()
    {
        if(avatars == null)
            avatars = new MutableLiveData<>();
        return avatars;
    }

    public void setData(Propietario p)
    {
        if(p != null)
        {
            // Alternativa al no usar Bundle...
            ApiClient api = ApiClient.getApi();
            Propietario propietarioLogueado = api.obtenerUsuarioActual();

            usuario.setValue(propietarioLogueado.getApellido());
            mail.setValue(propietarioLogueado.getEmail());
            avatars.setValue(propietarioLogueado.getAvatar());
        }
    }


}
