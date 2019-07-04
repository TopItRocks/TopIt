package rocks.topit.www.topit;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

import com.cloudinary.android.MediaManager;

import rocks.topit.www.topit.viewmodel.MainActivityViewModel;

import static android.app.PendingIntent.getActivity;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    String[] dailyB_StringResults; //{MediaManager.get().url().generate("5b7a2e5478832.jpg"), MediaManager.get().url().generate("5b8dfb1c6241b.jpg")};
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
    ViewModel mViewModel;
    String urlText;



    public SwipeAdapter(FragmentManager fm) {
        super(fm);


    }


    public void setArguments(Bundle bundle) {
        urlText = (String) bundle.get("urlText");
        dailyB_StringResults = urlText.split("-");
        dailyB_StringCount = dailyB_StringResults.length - 1;

    }



    @Override
    public Fragment getItem(int i) {

        //dailyB_String = ((MainActivityViewModel) mViewModel).message;
        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("urlText", dailyB_StringResults[i+1]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        //dailyB_String = ((MainActivityViewModel) mViewModel).message;
        return dailyB_StringCount;
    }



}
