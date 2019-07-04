package rocks.topit.www.topit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Scoreboard extends AppCompatActivity {
    SharedPreferences sp;
    String username, user, rank, admiration, affinity, combat, aura, vanguard, total_coins, total_points, packaged_result;
    TextView sb_title_field, sb_username_field, sb_total_points, sb_rank, sb_total_coins, sb_admiration_points, affinity_pts, combat_pts, aura_pts, vanguard_pts;
    Intent user_intent;

    AlertDialog alertDialog;
    Context context;
    //Packaged string from server with user info and scores
    /* Result order:
    * 0 - username
    * 1 - verified
    * 2 - rank
    * 3 - topitScore
    * 4 - admiration
    * 5 - affinity
    * 6 - combat
    * 7 - aura
    * 8 - vanguard
    * 9 - totalCoins
    * 10 - profilePicture;
    * 11 - dailyTopItString
    * 12 - weeklyTopItString
    * 13 - monthlyTopItString*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        username =  sp.getString("username", "");

        user_intent = getIntent();
        packaged_result = user_intent.getStringExtra("user");

        String[] results = packaged_result.split(",");
        user = results[0];
        rank = results[2];
        total_points = results[3];
        total_coins = results[9];


        sb_username_field = (TextView)findViewById(R.id.sb_username);
        sb_title_field = (TextView)findViewById(R.id.sb_title);
        sb_total_points = (TextView)findViewById(R.id.sb_total_points);
        sb_rank = (TextView)findViewById(R.id.sb_rank);
        sb_total_coins = (TextView)findViewById(R.id.sb_total_coins);

        setTitle();
        updateScores();

    }

    private void setTitle() {
       if(user.equals(username)){
            sb_username_field.setText(username);
            sb_title_field.setText("Your Top It Score!");
       }else{
            sb_username_field.setText(user);
            sb_title_field.setText("Top It Score!");
       }

    }

    private void updateScores (){
        sb_total_points.setText(total_points);
        sb_rank.setText(rank);
        sb_total_coins.setText(total_coins);
    }


    public void openAdmirationSB(View view) {
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Scoreboard Status");
        String type = "sb_admiration";
        Scoreboard_BTS sb_bts = new Scoreboard_BTS(this);
        sb_bts.execute(type, user);
    }
}
