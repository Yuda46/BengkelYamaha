package com.example.yuda.bengkelyamaha;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Process;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]
    private static final String TAG = "MapsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
// Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     In this case,
     * we just add a marker near Kudus, Indonesia.
     * If Google Play services is not installed on the device, the user will be
     prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once
     the user has
     * installed Google Play services and returned to the app.
     */@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        Toast.makeText(getBaseContext(),"HOY",Toast.LENGTH_LONG).show();
// Add a marker in Kudus and move the camera
        LatLng kudus = new LatLng(-6.807260, 110.841371);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kudus, 10.7f));
//        if (ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED) {
//            return;
//        }

//        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        mDatabase = FirebaseDatabase.getInstance().getReference("bengkel");

        mDatabase.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
//                loading.hide();
// mMap.clear();
//iterasi tempat Bengkel
//                Toast.makeText(getBaseContext(),dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                for (DataSnapshot bengkel : dataSnapshot.getChildren()){
                    String latitude = (String) bengkel.child("latitude").getValue();
                    String longitude = (String) bengkel.child("longitude").getValue();
                    String nama  = (String) bengkel.child("nama").getValue();
                    Bengkel w = new Bengkel(latitude,longitude,nama);
                    w.showBengkel(w, mMap);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value." , databaseError.toException());
            }
        });
    }
    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
}