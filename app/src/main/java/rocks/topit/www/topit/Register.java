package rocks.topit.www.topit;


import android.content.BroadcastReceiver;
import android.content.Context;
//import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;

public class Register extends AppCompatActivity {
   EditText userName, firstName, lastName, password, password2, email, email2, birthdate;
    Context context;
    AlertDialog alertDialog;
    //BroadcastReceiver broadcastReceiver;

    //Register (Context ctx){context = ctx;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = (EditText)findViewById(R.id.reg_username);
        firstName = (EditText)findViewById(R.id.reg_firstName);
        lastName = (EditText)findViewById(R.id.reg_lastName);
        password = (EditText)findViewById(R.id.reg_password);
        password2 = (EditText)findViewById(R.id.reg_password2);
        email = (EditText)findViewById(R.id.reg_email);
        email2 = (EditText)findViewById(R.id.reg_email2);
        birthdate = (EditText)findViewById(R.id.reg_birthdate);


       /* broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter("data")); */
    }



    public void OnReg(View view){
        String str_userName = userName.getText().toString();
        String str_firstName = firstName.getText().toString();
        String str_lastName = lastName.getText().toString();
        String str_password = password.getText().toString();
        String str_password2 = password2.getText().toString();
        String str_email = email.getText().toString();
        String str_email2 = email2.getText().toString();
        String str_birthdate = birthdate.getText().toString();
        String type = "register";

        alertDialog = new android.app.AlertDialog.Builder(Register.this).create();
        alertDialog.setTitle("Register Status");

        if (str_userName.length() >= 3) {
            if (str_password.equals(str_password2) && str_password.length() >= 8) {
                if (str_email.equals(str_email2) && str_email.contains("@")) {
                    if(str_firstName.isEmpty()){
                        alertDialog.setMessage("Please enter your first name.");
                        alertDialog.show();
                    }else if(str_lastName.isEmpty()){
                        alertDialog.setMessage("Please enter your last name.");
                        alertDialog.show();
                    }else if(str_birthdate.isEmpty()){
                        alertDialog.setMessage("Please enter your birthdate.");
                        alertDialog.show();
                    }else{
                        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                        backgroundWorker.execute(type, str_userName, str_firstName, str_lastName, str_password, str_email, str_birthdate);
                    }
                } else {
                    //email message here
                    if(str_email.contains("@")) {
                        alertDialog.setMessage("The emails must match");
                        alertDialog.show();
                    }else{
                        alertDialog.setMessage("Please Enter a valid email address");
                        alertDialog.show();
                    }
                }
            } else {
                // password message here
                if(str_password.length() < 8){
                    alertDialog.setMessage("The password must be at least 8 characters long.");
                    alertDialog.show();
                }else {
                    alertDialog.setMessage("The passwords must match");
                    alertDialog.show();
                }
            }
        }else{
            alertDialog.setMessage("The username must be at least 3 characters long.");
            alertDialog.show();
        }
    }
}
