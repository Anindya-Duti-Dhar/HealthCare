package reverie.corporation.com.bmi.fragment;

import reverie.corporation.com.bmi.R;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Angry_Birds on 10/14/2016.
 */
public class about_fragment extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    TextView txtAboutTitle, txtAboutMessage, txtAboutMainText;
    Typeface font;

    public static about_fragment newInstance() {
        about_fragment fragment = new about_fragment();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public about_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fragment screen orientation normal both portait and landscape
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.about_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.menu_about));

        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/


        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        txtAboutTitle = (TextView) view.findViewById(R.id.aboutTitle);
        txtAboutTitle.setText(getString(R.string.about_title));
        txtAboutTitle.setTypeface(font);

        txtAboutMessage = (TextView) view.findViewById(R.id.aboutMessage);
        txtAboutMessage.setText(getString(R.string.about_message));
        txtAboutMessage.setTypeface(font);

        txtAboutMainText = (TextView) view.findViewById(R.id.aboutMainText);
        txtAboutMainText.setText(getString(R.string.about_text));
        txtAboutMainText.setTypeface(font);



    }

}
