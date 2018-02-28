package afc.sportsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import afc.sportsapp.R;

public class WorkoutActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_workout:
                    startActivity(new Intent(WorkoutActivity.this, WorkoutActivity.class));
                    return true;
                case R.id.navigation_program:
                    startActivity(new Intent(WorkoutActivity.this, ProgramActivity.class));
                    return true;
                case R.id.navigation_challenge:
                    startActivity(new Intent(WorkoutActivity.this, ChallengeActivity.class));
                    return true;
                case R.id.navigation_stats:
                    startActivity(new Intent(WorkoutActivity.this, StatsActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(WorkoutActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        //select right menu button
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_workout);

        untrackedWorkoutButtonPress();
        freeWorkoutButtonPress();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Handles logic of UntrackedWorkoutButton and the popup linked to it
     */
    private void untrackedWorkoutButtonPress(){
        Button b = (Button) findViewById(R.id.UntrackedWorkoutButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkoutActivity.this, PopUpWorkoutActivity.class));
            }
        });
    }

    private void freeWorkoutButtonPress(){
        Button b = (Button) findViewById(R.id.FreeWorkoutButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkoutActivity.this, GPSActivity.class));
            }
        });
    }
}
