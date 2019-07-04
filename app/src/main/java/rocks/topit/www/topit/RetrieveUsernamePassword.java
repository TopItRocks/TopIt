package rocks.topit.www.topit;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RetrieveUsernamePassword extends AppCompatActivity {
    EditText ret_recoverInfo;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_username_password);
        ret_recoverInfo = (EditText)findViewById(R.id.ret_recoverInfo);

    }

    public void RetrieveUsernamePassword (View view){
        String recoverInfo = ret_recoverInfo.getText().toString();
        String type = "recover";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, recoverInfo);
    }
}
