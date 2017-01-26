package reverie.corporation.com.bmi.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import reverie.corporation.com.bmi.FontsOverride;
import reverie.corporation.com.bmi.MainActivity;
import reverie.corporation.com.bmi.R;


public class apps_fragment extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    private String YourDeveloperName = "Lab Mimosa";

    Button StartStore;

    public static apps_fragment newInstance() {
        apps_fragment fragment = new apps_fragment();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public apps_fragment() {
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
        return inflater.inflate(R.layout.apps_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.more_apps_toolbar));

        // Initializing Google AdMob
        mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdMobAdView.loadAd(adRequest);

        FontsOverride.setDefaultFont(getActivity(), "MONOSPACE", "android.ttf");

        StartStore = (Button) view.findViewById(R.id.btnStartStore);
        // Button Action
        StartStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGplayDialogToUser(getActivity());
            }
        });

        showGplayDialogToUser(getActivity());

    }

    // Create Dialog popup for Google Play store
    public void showGplayDialogToUser(final Context context){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder
                .setTitle(getString(R.string.dialog2_title))
                .setMessage(getString(R.string.dialog2_message))
                .setPositiveButton(getString(R.string.dialog2_button), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Uri uri = Uri.parse("market://search?q=pub:" + YourDeveloperName);
                        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);

                        try {

                            startActivity(myAppLinkToMarket);

                        } catch (ActivityNotFoundException e) {

                            //the device hasn't installed Google Play
                            Toast.makeText(getActivity(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            getActivity().startActivity(i);
                            getActivity().finish();
                        }
                    }
                });

        alertDialogBuilder.setNegativeButton(getString(R.string.dialog2_negative), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                home fragment = new home();
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}
