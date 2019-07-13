package rocks.topit.www.topit;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;

import com.cloudinary.android.MediaManager;
import com.squareup.picasso.Picasso;

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
import java.util.HashMap;
import java.util.Map;

import org.cloudinary.json.JSONString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import rocks.topit.www.topit.BackgroundWorkers.FragmentLoader;
import rocks.topit.www.topit.viewmodel.MainActivityViewModel;

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
 * */

public class MainActivity extends FragmentActivity {
    SharedPreferences sp;
    String username, user, rank, admiration, affinity, combat, aura, vanguard, total_coins, total_points, packaged_result, urlText, dailyB_String, textBack, result;
    TextView sb_title_field, sb_username_field, sb_total_points, sb_rank, sb_total_coins, sb_admiration_points, affinity_pts, combat_pts, aura_pts, vanguard_pts;
    Intent user_intent;
    AlertDialog alertDialog;
    Context context;
    ViewPager viewPager;
    ViewModel mViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //get logged in user info
        sp = getSharedPreferences("login", MODE_PRIVATE);
        username =  sp.getString("username", "");
            //get string from background worker
        user_intent = getIntent();
        packaged_result = user_intent.getStringExtra("user");
            //break string apart
        String[] results = packaged_result.split(",");
        user = results[0];
        rank = results[2];
        total_points = results[3];
        total_coins = results[9];
        String dailyB_FromServer = results[11];

        String[] dailyPics = dailyB_FromServer.split("-");
        String[] line2 = dailyPics[1].split(":");



        //init viewmodel
        //mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
            //Send info to view model
        //((MainActivityViewModel) mViewModel).message = dailyB_FromServer;
            //This gets the "message" string from view model
        //dailyB_String = ((MainActivityViewModel) mViewModel).message;

        //Set up Cloudinary MediaManager
        Map config = new HashMap();
        config.put("cloud_name", "lsid22exf");
        MediaManager.init(this, config);

        // talk to FragmentLoader
        //String type = "daily";
        //FragmentLoader fragmentLoader = new FragmentLoader(this);
        //fragmentLoader.execute(type, username);

        //alertDialog = new android.app.AlertDialog.Builder(this).create();
        //alertDialog.setTitle("DailyB Status");
        //alertDialog.setMessage("here is user name from bg to check if working: " + line2[1]);
        //alertDialog.show();


        viewPager = (ViewPager)findViewById(R.id.view_pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("urlText", dailyB_FromServer);
        swipeAdapter.setArguments(bundle);
        viewPager.setAdapter(swipeAdapter);

    }
}
