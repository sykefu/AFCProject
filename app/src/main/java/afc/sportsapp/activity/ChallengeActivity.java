package afc.sportsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import afc.sportsapp.Communication.ChallengeQuery;
import afc.sportsapp.R;
import afc.sportsapp.model.Challenge;

public class ChallengeActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_workout:
                    startActivity(new Intent(ChallengeActivity.this, WorkoutActivity.class));
                    return true;
                case R.id.navigation_program:
                    startActivity(new Intent(ChallengeActivity.this, ProgramActivity.class));
                    return true;
                case R.id.navigation_challenge:
                    startActivity(new Intent(ChallengeActivity.this, ChallengeActivity.class));
                    return true;
                case R.id.navigation_stats:
                    startActivity(new Intent(ChallengeActivity.this, StatsActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(ChallengeActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        //select right menu button
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_challenge);
        Challenges();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void Challenges(){
        ChallengeQuery aQuery = new ChallengeQuery();
        final ArrayList<Challenge> challengeList = aQuery.getChallenges();
        /*LinearLayout ll = findViewById(R.id.ChallengeList);
        for(int i = 0; i < challengeList.size(); i++){
            Button b = new Button(ChallengeActivity.this);
            b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            b.setText(challengeList.get(i).getName());
            b.setId(i);
            ll.addView(b);


            //connecting to pop-up activity

            final int finalI = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent send = new Intent(ChallengeActivity.this, PopChallengeActivity.class);
                    send.putExtra("challengeName", challengeList.get(finalI).getName());
                    send.putExtra("challengeDescription", challengeList.get(finalI).getDescription());
                    startActivity(send);
                }
            });
        }*/

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        for(int i = 0; i < challengeList.size(); i++){
            adapter.add(challengeList.get(i).getName());
        }

        ListView listView = findViewById(R.id.list_challenge);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent send = new Intent(ChallengeActivity.this, PopChallengeActivity.class);
                send.putExtra("challengeName", challengeList.get(i).getName());
                send.putExtra("challengeDescription", challengeList.get(i).getDescription());
                startActivity(send);
            }
        });
    }
}
