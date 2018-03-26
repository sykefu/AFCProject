package afc.sportsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import afc.sportsapp.Communication.ProgramQuery;
import afc.sportsapp.R;
import afc.sportsapp.model.Program;

public class ProgramActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_workout:
                    startActivity(new Intent(ProgramActivity.this, WorkoutActivity.class));
                    return true;
                case R.id.navigation_program:
                    startActivity(new Intent(ProgramActivity.this, ProgramActivity.class));
                    return true;
                case R.id.navigation_challenge:
                    startActivity(new Intent(ProgramActivity.this, ChallengeActivity.class));
                    return true;
                case R.id.navigation_stats:
                    startActivity(new Intent(ProgramActivity.this, StatsActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(ProgramActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        //select right menu button
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_program);
        //get program list
        Programs();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Load programs known by the app and create buttons for the activity.
     */
    private void Programs(){
        ProgramQuery aQuery = new ProgramQuery(getApplicationContext());
        final ArrayList<Program> programList = aQuery.getPrograms();
        //LinearLayout ll = findViewById(R.id.ProgramList);
        /*for(int i = 0; i < programList.size(); i++){
            Button b = new Button(ProgramActivity.this);
            b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            b.setText(programList.get(i).getName());
            b.setId(i);
            ll.addView(b);


            //Connecting to pop-up activity
            final int finalI = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent send = new Intent(ProgramActivity.this, PopProgramActivity.class);
                    send.putExtra("programName", programList.get(finalI).getName());
                    send.putExtra("programDescription", programList.get(finalI).getDescription());
                    startActivity(send);
                    //recuperer le nom de l'entrainement choisis pour pouvoir le sauvegarder
                }
            });
        }*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        for(int i = 0; i < programList.size(); i++){
            adapter.add(programList.get(i).getName());
        }

        ListView listView = findViewById(R.id.list_program);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent send = new Intent(ProgramActivity.this, PopProgramActivity.class);
                send.putExtra("programName", programList.get(i).getName());
                send.putExtra("programDescription", programList.get(i).getDescription());
                startActivity(send);
                //recuperer le nom de l'entrainement choisis pour pouvoir le sauvegarder
            }
        });
    }

}
