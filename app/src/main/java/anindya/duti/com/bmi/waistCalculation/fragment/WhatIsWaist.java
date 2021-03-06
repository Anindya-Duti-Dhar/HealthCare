package anindya.duti.com.bmi.waistCalculation.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import anindya.duti.com.bmi.FontsOverride;
import anindya.duti.com.bmi.R;

public class WhatIsWaist extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    Typeface font;

    public static WhatIsWaist newInstance() {
        WhatIsWaist fragment = new WhatIsWaist();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public WhatIsWaist() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.what_is_waist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.waist_toolbar));

        FontsOverride.setDefaultFont(getActivity(), "MONOSPACE", "android.ttf");

        // Initializing Google AdMob
        mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdMobAdView.loadAd(adRequest);


        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

    }

}
