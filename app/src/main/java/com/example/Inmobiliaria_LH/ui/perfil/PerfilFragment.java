package com.example.Inmobiliaria_LH.ui.perfil;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.Inmobiliaria_LH.modelo.Propietario;
import com.example.Inmobiliaria_LH.R;

public class PerfilFragment extends Fragment
{

    private PerfilViewModel perfilViewModel;
    private EditText etDNI, etNombre, etApellido, etEmail, etContraseña, etTelefono;
    private TextView etId;
    private ImageView avatar;
    private Button btEditar,btGuardar;
    Propietario p;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @NonNull ViewGroup container, @NonNull Bundle savedInstanceState)
    {

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        View vistaPerfil= inflater.inflate(R.layout.perfil_fragment, container, false);

        inicializar(vistaPerfil);

        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>()
        {

            @Override
            public void onChanged(Propietario propietario)
            {
                //seteamos campos de Fragment perfil...
                p = propietario;

                etId.setText(""+propietario.getId());
                etDNI.setText(propietario.getDni().toString());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etTelefono.setText(propietario.getTelefono());
                etContraseña.setText(propietario.getContraseña());
                avatar.setImageResource(propietario.getAvatar());

            }
        });

        perfilViewModel.getMensajeMutable().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String mensaje)
            {

                AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);
                dialogo.setTitle("Atención");
                dialogo.setMessage(mensaje);

                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    { }
                });
                dialogo.show();
            }
        });

        perfilViewModel.getEditar().observe(getViewLifecycleOwner(), new Observer<Boolean>()
        {
            @Override
            public void onChanged(Boolean estado)
            {
                //habilita o deshabilita el estado de los editTex(true, false)...
                etDNI.setEnabled(estado);
                etNombre.setEnabled(estado);
                etApellido.setEnabled(estado);
                etEmail.setEnabled(estado);
                etContraseña.setEnabled(estado);
                etTelefono.setEnabled(estado);
            }
        });

        perfilViewModel.obtenerDatos();

        return vistaPerfil;
    }


    private void inicializar(View vistaPerfil)
    {

        etId = vistaPerfil.findViewById(R.id.etId);
        etDNI = vistaPerfil.findViewById(R.id.tvDNI);
        etNombre = vistaPerfil.findViewById(R.id.tvNombre);
        etApellido = vistaPerfil.findViewById(R.id.tvApellido);
        etEmail = vistaPerfil.findViewById(R.id.tvEmail);
        etContraseña = vistaPerfil.findViewById(R.id.etContraseña);
        etTelefono = vistaPerfil.findViewById(R.id.tvTelefono);
        avatar = vistaPerfil.findViewById(R.id.ivPerfil);
        btEditar = vistaPerfil.findViewById(R.id.btEditar);
        btGuardar = vistaPerfil.findViewById(R.id.btGuardar);

        //habilita la edicion
        btEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                btEditar.setVisibility(View.INVISIBLE);
                btGuardar.setVisibility(View.VISIBLE);
                //habilta ediText...
                perfilViewModel.habilitar();
            }
        });

        //Guardo los cambios y deshabilito
        btGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                p.setId(Integer.parseInt(etId.getText().toString()));
                p.setDni(Long.parseLong(etDNI.getText().toString()));
                p.setNombre(etNombre.getText().toString());
                p.setApellido(etApellido.getText().toString());
                p.setEmail(etEmail.getText().toString());
                p.setContraseña(etContraseña.getText().toString());
                p.setTelefono(etTelefono.getText().toString());

                perfilViewModel.editarPropietario(p);

                btEditar.setVisibility(View.VISIBLE);
                btGuardar.setVisibility(View.INVISIBLE);
                //deshabilta ediText...
                perfilViewModel.deshabilitar();
            }
        });

    }

}





