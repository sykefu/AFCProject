package afc.sportsapp.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import afc.sportsapp.R;

public class PopUpWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_workout);

        ValidateWorkout();
    }

    private void ValidateWorkout(){
        Button b = (Button) findViewById(R.id.ValidateUntrackedSportButton);
        Log.i("button check", b.toString());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("button click", "clicked");

                EditText et = (EditText) findViewById(R.id.EnterSportsNamePUW);
                String checker = et.getText().toString();
                if(checker.length() > 0){
                    Toast.makeText(getBaseContext(), "Tentative d'envoi...", Toast.LENGTH_SHORT).show();
                    //TODO : send to db, send OK or KO depending if it manages to send data

                }
                else{
                    Toast.makeText(getBaseContext(), "Veuillez entrer un sport.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
