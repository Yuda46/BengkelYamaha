package com.example.yuda.bengkelyamaha;

/**
 * Created by Y U D A on 12/25/2017.
 */
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Bengkel {
    public String latitude;
    public String longitude;
    public String nama;

    public Bengkel(String latitude, String longitude, String nama) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nama = nama;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void showBengkel(Bengkel bengkel, GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(Double.valueOf(bengkel.getLatitude()),
                        Double.valueOf(bengkel.getLongitude())))
                .title(bengkel.getNama())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        );
    }
}