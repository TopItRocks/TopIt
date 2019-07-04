package rocks.topit.www.topit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;

        /*
        From your class java file to communicate with server -
        String ET_post_data should look like this example of two parameters:
        URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"+URLEncoder.encode("password")+"="+URLEncoder.encode(password, "UTF-8");
        */

public class VerifyEmail extends AppCompatActivity {
    Context context;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
    }

    public void ver_logIn(View view){
        String type = "login";
        String fromActivity = "verify";
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password, fromActivity);
    }

    public void resendVerification(View view){
        String type = "ver_resend";
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        //String password = intent.getStringExtra("password");
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username);
    }


}
