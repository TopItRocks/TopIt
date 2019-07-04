package rocks.topit.www.topit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class Update_UI_Scores extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        //Intent returnIntent = getIntent();
        //returnIntent.putExtra("result","hello");
        //setResult(Activity.RESULT_OK,returnIntent);
        //finish();
        returnScores();

    }

    public void returnScores(){
        //TextView sb_total_points = (TextView) findViewById(R.id.sb_total_points);
        String stringToPassBack = "hello";

        // put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putExtra("result", stringToPassBack);
        setResult(RESULT_OK, intent);
        finish();
    }


}
