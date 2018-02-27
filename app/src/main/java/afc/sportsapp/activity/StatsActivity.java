package afc.sportsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import afc.sportsapp.R;

public class StatsActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_workout:
                    startActivity(new Intent(StatsActivity.this, WorkoutActivity.class));
                    return true;
                case R.id.navigation_program:
                    startActivity(new Intent(StatsActivity.this, ProgramActivity.class));
                    return true;
                case R.id.navigation_challenge:
                    startActivity(new Intent(StatsActivity.this, ChallengeActivity.class));
                    return true;
                case R.id.navigation_stats:
                    startActivity(new Intent(StatsActivity.this, StatsActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(StatsActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        //select right menu button
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_stats);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
