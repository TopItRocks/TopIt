package rocks.topit.www.topit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cloudinary.android.MediaManager;

import static android.app.PendingIntent.getActivity;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    String[] dailyB_StringResults = {MediaManager.get().url().generate("5b7a2e5478832.jpg"), MediaManager.get().url().generate("5b8dfb1c6241b.jpg")};
    SharedPreferences sp;
    String username;
    String user;
    String rank;
    String admiration;
    String affinity;
    String combat;
    String aura;
    String vanguard;
    String total_coins;
    String total_points;
    String packaged_result;
    String dailyB_String;
    TextView sb_title_field, sb_username_field, sb_total_points, sb_rank, sb_total_coins, sb_admiration_points, affinity_pts, combat_pts, aura_pts, vanguard_pts;
    Intent user_intent;
    Integer dailyB_StringCount;
    Context context;


    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("urlText", dailyB_StringResults[i]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }



}
