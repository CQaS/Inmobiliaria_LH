package com.example.Inmobiliaria_LH.ui.mapaInicio;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Inmobiliaria_LH.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaInicio extends Fragment
{

    private GoogleMap map;
    private View mapaDetalle = null;

    private OnMapReadyCallback callback = new OnMapReadyCallback()
    {

        @Override
        public void onMapReady(GoogleMap googleMap)
        {

            map = googleMap;
            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
            {
                @Override
                public View getInfoWindow(Marker marker)
                {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker)
                {
                    if(mapaDetalle == null)
                    {
                        mapaDetalle = getLayoutInflater().inflate(R.layout.mapa_detalle,null);
                    }

                    //añade texto e imagen ala posicion en mapa....
                    TextView info = mapaDetalle.findViewById(R.id.txtMarka);
                    info.setText(R.string.aquiEstamos);
                    ImageView imageInfo = mapaDetalle.findViewById(R.id.ivMaps);
                    imageInfo.setImageResource(R.drawable.grecia);

                    return (mapaDetalle);
                }
            });

            LatLng Inmobiliaria = new LatLng(-32.342999, -65.013562);

            map.addMarker
                    (
                            new MarkerOptions()
                            .position(Inmobiliaria)
                            .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    );

            //añade circunferencia...
            CircleOptions circleOptions = new CircleOptions()
                    .center(Inmobiliaria)
                    .radius(40)
                    .strokeColor(Color.parseColor("#0D47A1"))
                    .strokeWidth(4)
                    .fillColor(Color.argb(32, 33, 150, 243));
            map.addCircle(circleOptions);

            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            map.getUiSettings().setZoomControlsEnabled(true);

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(Inmobiliaria)
                    .zoom(18.0f)
                    .bearing(45)
                    .tilt(90)
                    .build();

            CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);
            map.animateCamera(camUpd);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.mapa_inicio_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaInicio);


            mapFragment.getMapAsync(callback);

    }
}