package rocks.topit.www.topit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdmirationScoreboard extends AppCompatActivity {
    String username, user, rank, total_admiration, affinity, combat, aura, vanguard, total_coins, total_points, packaged_result;
    TextView ui_sb_admiration_title, ui_sb_admiration_username, sb_total_points, sb_rank, sb_total_coins, ui_sb_admiration_points, affinity_pts, combat_pts, aura_pts, vanguard_pts;
    Intent user_intent;

    //Packaged string from server with user info and scores
    /* Result order:
     * 0 - username
     * 1 - rank
     * 2 - topitScore
     * 3 - admiration
     * 4 - affinity
     * 5 - combat
     * 6 - aura
     * 7 - vanguard
     * 8 - totalCoins
     * 9 - profilePicture;
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admiration_scoreboard);

        user_intent = getIntent();
        packaged_result = user_intent.getStringExtra("user_data");

        String[] results = packaged_result.split(",");
        user = results[0];
        total_admiration = results[3];

        ui_sb_admiration_username = (TextView)findViewById(R.id.sb_admiration_username);
        ui_sb_admiration_title = (TextView)findViewById(R.id.sb_admiration_title);
        ui_sb_admiration_points = (TextView)findViewById(R.id.sb_admiration_points);

        updateAdmirationScore();

    }

    private void updateAdmirationScore(){
        ui_sb_admiration_username.setText(user);
        ui_sb_admiration_points.setText(total_admiration);
    }

    public void openAffinitySB(View view) {
        String type = "sb_affinity";
        Scoreboard_BTS sb_bts = new Scoreboard_BTS(this);
        sb_bts.execute(type, user);
    }
}
