package com.example.Inmobiliaria_LH;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Inmobiliaria_LH.modelo.Propietario;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity 
{

    private AppBarConfiguration mAppBarConfiguration;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setearHeaderMenu();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflar el menú...
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setearHeaderMenu()
    {
        //referencia a componentes de la vista Header...
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        ImageView avatar = header.findViewById(R.id.imgAvatar);
        TextView usuario = header.findViewById(R.id.txtUsuario);
        TextView email = header.findViewById(R.id.txtMail);

        //recupera datos Propietario desde la otra activity...
        Propietario propietarioLogueado = (Propietario)getIntent().getBundleExtra("propietario").getSerializable("propietario");

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        viewModel.getUsuario().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                usuario.setText(s);

            }
        });

        viewModel.getMail().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                email.setText(s);
            }
        });

        viewModel.getAvatars().observe(this, new Observer<Integer>()
        {
            @Override
            public void onChanged(Integer i)
            {
                avatar.setImageResource(i);
            }
        });

        viewModel.setData(propietarioLogueado);


        //menu Navegable...
        mAppBarConfiguration = new AppBarConfiguration.Builder
                (
                        R.id.mapaInicioFragment,
                        R.id.perfilFragment,
                        R.id.inmueblesFragment,
                        R.id.inquilinosFragment,
                        R.id.contratosFragment,
                        R.id.logoutFragment
                )
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        
        NavigationUI.setupWithNavController(navigationView, navController);
    }
}

