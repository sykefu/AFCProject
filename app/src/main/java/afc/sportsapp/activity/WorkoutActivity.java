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
                    return true;
                case R.id.navigation_program:
                    return true;
                case R.id.navigation_challenge:
                    return true;
                case R.id.navigation_stats:
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
         UntrackedWorkoutButtonPress();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Handles logic of UntrackedWorkoutButton and the popup linked to it
     */
    private void UntrackedWorkoutButtonPress(){
        Button b = (Button) findViewById(R.id.UntrackedWorkoutButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkoutActivity.this, PopUpWorkoutActivity.class));
            }
        });
    }
}
