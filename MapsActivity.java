package com.example.jaime.eventosjjr;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;// el mapa de google
    //marcadores, zoom

    Marker marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        agregarMarcadores(-0.189344, -78.448512, "titulo marcador", "otro texto");
        obtenerUbicacion();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                agregarMarcadores(latLng.latitude,latLng.longitude,"","");
            }
        });

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                agregarMarcadores(cameraPosition.target.latitude,
                        cameraPosition.target.longitude,"","");
            }
        });

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));//10-20

    }

    public void agregarMarcadores(double lat, double lng, String titulo, String snippet) {

        LatLng posicion = new LatLng(lat, lng);
        CameraUpdate posActual = CameraUpdateFactory.newLatLngZoom(posicion, 16);

        if(marcador!=null) marcador.remove();

        marcador = mMap.addMarker(new MarkerOptions()
                .position(posicion)
                .title(titulo)
                .icon(BitmapDescriptorFactory.defaultMarker())
                .draggable(true)
                .snippet(snippet));

        mMap.animateCamera(posActual);
    }

    //sacar del gps
    public void actualizarPosicion(Location location) {
        if (location != null) {
            location.getLatitude();
            location.getLongitude();

            agregarMarcadores(location.getLatitude(), location.getLongitude(), "Posicion Actual", "Aqui estoy");
        }

    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarPosicion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void obtenerUbicacion() {
        //obtener ubicacion por gps

        if (ActivityCompat.checkSelfPermission(this, android.Manifest
                .permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            //solicitar permisos en tiempo de ejecucion
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location locationActual = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        actualizarPosicion(locationActual);
        //PROVEEDOR,TIEMPOACTUALIZA,MILLAS,
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0,locationListener);


    }
}
