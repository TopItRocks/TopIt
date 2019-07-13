package rocks.topit.www.topit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudinary.android.MediaManager;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends android.support.v4.app.Fragment{
    TextView textView;
    Context context;
    String urlText;


    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.page_fragment_layout,container, false);



        //urlText = MediaManager.get().url().generate("5b7a2e5478832.jpg");

        ImageView imageView = (ImageView) view.findViewById(R.id.view_pager2);
        TextView textView = (TextView) view.findViewById(R.id.fragmentText);

        // get bundle from swipe adapter
        Bundle bundle = getArguments();
        String urlText = (String) bundle.get("urlText");

        // break string apart here
        String[] picDetails = urlText.split(":");
        textView.setText(picDetails[1]);
        // turn file name into url for cloudinary download
        urlText = MediaManager.get().url().generate(picDetails[0]);

        // load picture into page fragment Image View
        Picasso.get().load(urlText).into(imageView);


        //textView = (TextView)view.findViewById(R.id.view_pager2);
        //Bundle bundle = getArguments();
        //String message = bundle.getString("urlText");
        //textView.setText("this is the "+message+" swipe view page..");
        return view;
    }

}
