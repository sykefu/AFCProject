package afc.sportsapp.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

import afc.sportsapp.R;

public class GPSActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMS_CALL_ID = 12345;

    private LocationManager lm;
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private Chronometer chronometer;
    private Button start;
    private Polyline polyline;
    private List<LatLng> listPoints = new ArrayList<>();
    private boolean isStarted = false;
    private LatLng currentLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.chronometer = findViewById(R.id.chronometer);
        this.start = findViewById(R.id.begin_chrono);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);


        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (start.getText() == getString(R.string.action_start)) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    start.setText(getString(R.string.action_stop));
                    isStarted = true;
                    return;
                }
                if (start.getText() == getString(R.string.action_stop)) {
                    chronometer.stop();
                    start.setText(getString(R.string.action_start));
                    isStarted = false;
                    displayStat();
                }
            }
        });
    }

    private void displayStat(){
        // PopUp tu save the result
        AlertDialog.Builder popUp = new AlertDialog.Builder(GPSActivity.this);
        popUp.setTitle("Fin de la course");
        popUp.setMessage("Distance parcourue : " + calculDistancePolyline() + " m\n"
                + "Temps : " + getElapsedTime()/1000 + " s\n"
                + "Vitesse : " + getSpeedInKMpS() + " Km/h\n"
                + "voulez vous enregistrer votre parcours ?");
        popUp.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User save the result
            }
        });
        popUp.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User cancelled the dialog
            }
        });

        popUp.show();
    }

    private double calculDistancePolyline(){
        double distanceFinal = 0;
        if(listPoints.size()<2)
            return 0;
        for(int i=1; i<listPoints.size(); i++)
        {
            distanceFinal += SphericalUtil.computeDistanceBetween(listPoints.get(i-1),listPoints.get(i));
        }
        return distanceFinal;
    }


    private long getElapsedTime(){
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    // Can be used in the future
    /*private double getSpeedInMpS(){
        return calculDistancePolyline() / getElapsedTime() * 1000;
    }*/

    private double getSpeedInKMpS(){
        return calculDistancePolyline() / getElapsedTime() * 1000 * 3.6;
    }

    private void checkPermissions() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
        loadMap();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(lm != null){
            lm.removeUpdates(this );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }

    @SuppressWarnings("MissingPermission")
    private void loadMap(){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                GPSActivity.this.googleMap = googleMap;
                LatLng mapCenter = new LatLng(48.872808, 2.33517);
                googleMap.setMyLocationEnabled(true);
                if(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null )
                    mapCenter = new LatLng(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude(),lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
                if(currentLocation != null)
                    mapCenter = currentLocation;
                polyline = googleMap.addPolyline(new PolylineOptions().geodesic(true));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        currentLocation = new LatLng( latitude, longitude);
        if(googleMap != null)
        {

            //zoom de la caméra sur la position qu'on désire afficher
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13));
            if(isStarted) {
                listPoints.add(currentLocation);
                polyline.setPoints(listPoints);
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        if(s.equals(LocationManager.GPS_PROVIDER))
        {
            Toast.makeText(GPSActivity.this, "La localisation est bien activée",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onProviderDisabled(String s) {
        if(s.equals(LocationManager.GPS_PROVIDER))
        {
            AlertDialog.Builder popUp = new AlertDialog.Builder(GPSActivity.this);
            popUp.setTitle("Localisation désactivée");
            popUp.setMessage("Votre localisation semble désactivée,\nveuillez l'activer dans les paramètres de votre téléphone SVP");
            popUp.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // User cancelled the dialog
                }
            });
            popUp.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
