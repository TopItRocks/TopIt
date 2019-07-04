package rocks.topit.www.topit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AffinityScoreboard extends AppCompatActivity {
    String username, user, rank, total_admiration, total_affinity, combat, aura, vanguard, total_coins, total_points, packaged_result;
    TextView ui_sb_affinity_title, ui_sb_affinity_username, sb_total_points, sb_rank, sb_total_coins, ui_sb_affinity_points, affinity_pts, combat_pts, aura_pts, vanguard_pts;
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
        setContentView(R.layout.activity_affinity_scoreboard);

        user_intent = getIntent();
        packaged_result = user_intent.getStringExtra("user_data");

        String[] results = packaged_result.split(",");
        user = results[0];
        total_affinity = results[4];

        ui_sb_affinity_username = (TextView)findViewById(R.id.sb_affinity_username);
        ui_sb_affinity_title = (TextView)findViewById(R.id.sb_affinity_title);
        ui_sb_affinity_points = (TextView)findViewById(R.id.sb_affinity_points);

        updateAffinityScore();

    }

    private void updateAffinityScore(){
        ui_sb_affinity_username.setText(user);
        ui_sb_affinity_points.setText(total_affinity);
    }
}
