package luisdomlop.tenerife;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.security.Permission;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button ball, sele;
    Spinner spin;
    GoogleMap mapita;

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISION_REQUEST_CODE = 1234;
    private Boolean mlocationPermisiongrant = false;
    private FusedLocationProviderClient mFusedlocationclient;

    //oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ball = findViewById(R.id.button);

        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarMarcadores();
            }
        });

        sele = findViewById(R.id.button2);
        sele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ArrayList<String> myList = new ArrayList<String>();
        myList.add("ONE");       // add items to List
        myList.add("TWO");
        myList.add("THREE");
        myList.add("FOUR");
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);  // pass List to ArrayAdapter
        spin = findViewById(R.id.spinner);
        spin.setAdapter(ad);

        ///mapa
        if(isServiceOk()){
            getlocationPermision();
            initMap();
        }



    }


    // Tema GOOGLE MAPS
    //permisos de localización
    private void getlocationPermision() {
        String[] permision = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mlocationPermisiongrant = true;
            } else {
                ActivityCompat.requestPermissions(this, permision, LOCATION_PERMISION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,permision,LOCATION_PERMISION_REQUEST_CODE);
        }
    }

    // control O funcion para regular permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mlocationPermisiongrant = false;
        switch (requestCode) {
            case LOCATION_PERMISION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mlocationPermisiongrant = false;
                            return;
                        }

                    }
                    mlocationPermisiongrant = true;
                    initMap();
                }
            }
        }
    }

    // comp de los servicios de google
    public Boolean isServiceOk() {
        Log.d(TAG, "is:serivceOK: Checking version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "is:serivceOK:google play service is Working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "is:serivceOK:google play service is Working");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "No puede hacer la solicitud del mapa", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //iniciar mapa
    private void initMap() {
        SupportMapFragment mapFr = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFr.getMapAsync(MainActivity.this);
    }

    private void getDeviceLocation(){
        mFusedlocationclient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        try {
            if(mlocationPermisiongrant){
                Task location = mFusedlocationclient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Location currentLocation = (Location) task.getResult();
                            moveCamara(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),15f);
                        }else{
                            Toast.makeText(MainActivity.this,"imposible obtener posicion actual", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }catch( SecurityException e ){
            Toast.makeText(MainActivity.this,"imposible obtener posicion actual", Toast.LENGTH_SHORT).show();
        }

    }

    private void moveCamara(LatLng lat,float zoom){
        mapita.moveCamera(CameraUpdateFactory.newLatLngZoom(lat,zoom));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapita = googleMap;
        Toast.makeText(MainActivity.this, " pre posconseguida", Toast.LENGTH_SHORT).show();
        mlocationPermisiongrant=true;
        // aqui no entra porque no pilla los permisos bien
        if (mlocationPermisiongrant) {
            getDeviceLocation();
            Toast.makeText(MainActivity.this, "posconseguida", Toast.LENGTH_SHORT).show();
            mapita.getUiSettings().setMyLocationButtonEnabled(true);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                return;
            }
            mapita.setMyLocationEnabled(true);
            // añadir todas las coordenadas
            double [] coordenadas = {40.454099, -3.778916};
            anadirMarcadores(coordenadas);
        }
    }

    // permite añadir marcadores a granel
    public void anadirMarcadores(double [] coordenadas){
        for (int i = 0; i < coordenadas.length; i++){
            LatLng punto1 = new LatLng(coordenadas[i], coordenadas[i+1]);
            mapita.addMarker(new MarkerOptions().position(punto1));
            i++;
        }

    }

    // Borrar los Marcadores
    public void borrarMarcadores(){
        mapita.clear();
    }


    // Funciones de Jena







}
