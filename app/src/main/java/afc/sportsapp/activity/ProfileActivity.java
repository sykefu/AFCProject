package afc.sportsapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import afc.sportsapp.Communication.ProfileQuery;
import afc.sportsapp.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                /*case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        LoadProfile();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //dunno what it does
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Loads and prints the profile based on data recovered from ProfileQuery
     */
    private void LoadProfile(){
        ArrayList<String> profileData;
        profileData = new ProfileQuery().getData();
        System.out.println("derp");
        TextView aTextView;
        aTextView = findViewById(R.id.Surname);
        aTextView.setText(profileData.get(0));
        aTextView = findViewById(R.id.LastName);
        aTextView.setText(profileData.get(1));
        aTextView = findViewById(R.id.Height);
        aTextView.setText(profileData.get(2));
        aTextView = findViewById(R.id.Weight);
        aTextView.setText(profileData.get(3));
        aTextView = findViewById(R.id.BMI);
        float Height = Float.parseFloat(profileData.get(2));
        float Weight = Float.parseFloat(profileData.get(3));
        float BMI = Weight/(Height*Height);
        aTextView.setText(Float.toString(BMI));
        aTextView = findViewById(R.id.Email);
        aTextView.setText(profileData.get(4));
    }
}
