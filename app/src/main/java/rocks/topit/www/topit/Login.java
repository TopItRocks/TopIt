package rocks.topit.www.topit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;

public class Login extends AppCompatActivity {
    AlertDialog alertDialog;
    EditText logIn_username, logIn_password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        if(sp.getBoolean("logged", true)){
            String username = sp.getString("username", "");
            String type = "logged";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username);
        }



        logIn_username = (EditText)findViewById(R.id.logIn_username);
        logIn_password = (EditText)findViewById(R.id.logIn_password);
    }

    public void OnLogin(View view){
        String username = logIn_username.getText().toString();
        String password = logIn_password.getText().toString();
        String type = "login";

        if(username.equals("")){
            alertDialog = new android.app.AlertDialog.Builder(Login.this).create();
            alertDialog.setTitle("Login Status");
            alertDialog.setMessage("Please Enter a Username.");
            alertDialog.show();
        }else if(password.equals("")){
            alertDialog = new android.app.AlertDialog.Builder(Login.this).create();
            alertDialog.setTitle("Login Status");
            alertDialog.setMessage("Please Enter a Password.");
            alertDialog.show();
        }else{
            String fromActivity = "main";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password, fromActivity);
        }
    }

    public void OpenReg(View view){
        startActivity(new Intent(this,Register.class));
    }

    public void RetrieveUsernamePasswordLink(View view){
        startActivity(new Intent(this,RetrieveUsernamePassword.class));
    }
}
