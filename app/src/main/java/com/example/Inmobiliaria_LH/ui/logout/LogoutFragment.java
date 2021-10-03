package com.example.Inmobiliaria_LH.ui.logout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.Inmobiliaria_LH.R;

public class LogoutFragment extends Fragment
{

    public static LogoutFragment newInstance()
    {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root =  inflater.inflate(R.layout.logout_fragment, container, false);

        cerrarSesión();

        return root;
    }

    public void cerrarSesión()
    {

        AlertDialog.Builder singnOut = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);

        singnOut.setIcon(R.drawable.logout);
        singnOut.setTitle(R.string.alertaSalir);
        singnOut.setMessage(R.string.alertaCerrar);



        //boton ACEPTAR....
        singnOut.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int i)
            {
                System.exit(0);
            }
        });


        //boton CANCELAR.....
        singnOut.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.mapaInicioFragment);
            }
        });

        //mostrar dialogo....
        AlertDialog salir = singnOut.create();
        salir.show();
    }

}
