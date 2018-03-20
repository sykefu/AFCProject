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
import com.google.android.gms.maps.model.CameraPosition;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.start = (Button) findViewById(R.id.begin_chrono);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);


        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
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
                    afficheStat();
                    //calculDistancePolyline();
                    return;
                }
            }
        });
    }

    private void afficheStat(){
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
        /*Toast.makeText(GPSActivity.this,"Distance Parcouru : "+ distanceFinal + " m en : " + getElapsedTime() + " ms" ,Toast.LENGTH_LONG).show();
        Toast.makeText(GPSActivity.this,"Vitesse en m/s : " + getSpeedInMpS(),Toast.LENGTH_LONG).show();
        Toast.makeText(GPSActivity.this,"Vitesse en km/h : " + getSpeedInKMpS(),Toast.LENGTH_LONG).show();*/
    }

    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        Toast.makeText(GPSActivity.this, "Elapsed milliseconds: " + elapsedMillis,
                Toast.LENGTH_SHORT).show();
    }

    private long getElapsedTime(){
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    private double getSpeedInMpS(){
        return calculDistancePolyline() / getElapsedTime() * 1000;
    }

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

        loadMap();

    }
    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
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
                //googleMap.moveCamera(CameraUpdateFactory.zoomBy(15));
                //modifier et mettre en france
                LatLng mapCenter = new LatLng(48.872808, 2.33517);
                if(lm.getLastKnownLocation(lm.GPS_PROVIDER) != null)
                    mapCenter = new LatLng(lm.getLastKnownLocation(lm.GPS_PROVIDER).getLatitude(),lm.getLastKnownLocation(lm.GPS_PROVIDER).getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));
                googleMap.setMyLocationEnabled(true);
                polyline = googleMap.addPolyline(new PolylineOptions().geodesic(true));

                CameraPosition cameraPosition = CameraPosition.builder()
                        .target(mapCenter)
                        .zoom(13)
                        .bearing(90)
                        .build();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        if(googleMap != null)
        {
            LatLng googleLocation = new LatLng( latitude, longitude);

            //zoom de la caméra sur la position qu'on désire afficher
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(googleLocation, 16));
            if(isStarted) {
                listPoints.add(googleLocation);
                polyline.setPoints(listPoints);
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        if(s.equals(lm.GPS_PROVIDER))
        {
            Toast.makeText(GPSActivity.this, "La localisation est bien activée",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onProviderDisabled(String s) {
        if(s.equals(lm.GPS_PROVIDER))
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
            //Toast.makeText(GPSActivity.this, "activez la localisation svp",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
