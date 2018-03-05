package afc.sportsapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import afc.sportsapp.R;

/**
 * Created by Ludovic PARRA on 3/5/2018.
 * Group AFC.
 */

public class PopChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_challenge_activity);

        //make smaller
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        //get data

        Bundle extras = getIntent().getExtras();
        TextView tv = findViewById(R.id.pcaChallengeDesc);
        tv.setText(extras.getString("challengeDescription"));
    }
}
