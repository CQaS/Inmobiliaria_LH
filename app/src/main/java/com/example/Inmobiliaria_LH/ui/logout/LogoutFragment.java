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
        new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme)
                .setTitle("Cerrar Sesión")
                .setMessage("Salir de Tù espacio de Negocios?")
                .setPositiveButton
                        ("Aceptar", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        System.exit(0);
                                    }
                                }
                        )
                .setNegativeButton
                        ("Cancelar", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.perfilFragment);
                                    }
                                }
                        )
                .show();
    }

}
