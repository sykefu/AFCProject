package afc.sportsapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import afc.sportsapp.R;

public class GPSActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Chronometer chronometer;
    private Button start;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_workout:
                    startActivity(new Intent(GPSActivity.this, WorkoutActivity.class));
                    return true;
                case R.id.navigation_program:
                    startActivity(new Intent(GPSActivity.this, ProgramActivity.class));
                    return true;
                case R.id.navigation_challenge:
                    startActivity(new Intent(GPSActivity.this, ChallengeActivity.class));
                    return true;
                case R.id.navigation_stats:
                    startActivity(new Intent(GPSActivity.this, StatsActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(GPSActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.start = (Button) findViewById(R.id.begin_chrono);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //beginButtonPress();
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(start.getText() == getString(R.string.action_start)) {
                    chronometer.start();
                    start.setText(getString(R.string.action_stop));
                    return;
                }
                if(start.getText() == getString(R.string.action_stop)) {
                    chronometer.stop();
                    start.setText(getString(R.string.action_start));
                    return;
                }
            }
        });
    }

    /**private void beginButtonPress(){
        Button b = (Button) findViewById(R.id.begin_chrono);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.start();
            }
        });
    }**/

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
