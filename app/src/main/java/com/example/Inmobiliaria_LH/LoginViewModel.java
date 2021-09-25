package com.example.Inmobiliaria_LH;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Inmobiliaria_LH.modelo.Propietario;
import com.example.Inmobiliaria_LH.request.ApiClient;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class LoginViewModel extends AndroidViewModel
{
    Context context;
    private MutableLiveData<String> mensajeMutable;
    private MutableLiveData<Propietario> verificarPro;

    public LoginViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<String> getMensajeMutable()
    {
        if (mensajeMutable == null)
            mensajeMutable = new MutableLiveData<>();
        return mensajeMutable;
    }

    public LiveData<Propietario> getPropietario()
    {
        if(verificarPro == null)
            verificarPro = new MutableLiveData<>();
        return verificarPro;
    }

    public void autenticar(String mail, String password)
    {
        if (mail != null && password != null && password.length() > 0 && mail.length() > 0)
        {
            Propietario propietario = ApiClient.getApi().login(mail,password);

            if (propietario == null)
            {
                mensajeMutable.setValue("Datos incorrectos");
            }
            else
            {
                verificarPro.setValue(propietario);
            }

        }
        else
        {
            mensajeMutable.setValue("Datos insuficientes");
        }

    }

    public void usuarioLogueado(Propietario p)
    {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("propietario", p);
        intent.putExtra("propietario", bundle);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

