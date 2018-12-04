package luisdomlop.tenerife;


import android.location.Location;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button ball,sele;
    Spinner spin;
    MapView map;
    ArrayAdapter textosp;
    GoogleMap mapita;
    LocationProvider loc;
    Location clocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ball = findViewById(R.id.button);

        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sele = findViewById(R.id.button2);
        sele.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        ArrayList<String> myList = new ArrayList<String> ();
        myList.add("ONE");       // add items to List
        myList.add("TWO");
        myList.add("THREE");
        myList.add("FOUR");
        ArrayAdapter<String> ad = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, myList);  // pass List to ArrayAdapter
        spin = findViewById(R.id.spinner);
        spin.setAdapter(ad);

        
        map = findViewById(R.id.mapView);
    }
}
