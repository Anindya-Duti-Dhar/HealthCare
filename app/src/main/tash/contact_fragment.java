package reverie.corporation.com.bmi.fragment;

import reverie.corporation.com.bmi.R;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

/**
 * Created by Angry_Birds on 10/14/2016.
 */
public class contact_fragment extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    TextView txtweb, txtgplus, txtContactMessage, txtContactTitle, txtemail, txtphone, txtfb, txttwitter;
    Typeface font;

    public static contact_fragment newInstance() {
        contact_fragment fragment = new contact_fragment();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public contact_fragment() {
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
        return inflater.inflate(R.layout.contact_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.menu_bmi));

        // Initializing Google AdMob
        /*mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
               *//* .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/


        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        txtContactTitle = (TextView) view.findViewById(R.id.contactTitle);
        txtContactTitle.setText(getString(R.string.about_title));
        txtContactTitle.setTypeface(font);

        txtContactMessage = (TextView) view.findViewById(R.id.contactMessage);
        txtContactMessage.setText(getString(R.string.about_message));
        txtContactMessage.setTypeface(font);

        txtweb = (TextView) view.findViewById(R.id.website);
        txtweb.setText(getString(R.string.website));


        txtphone = (TextView) view.findViewById(R.id.phone);
        txtphone.setText(getString(R.string.mobile));
        txtphone.setTypeface(font);

        txtemail = (TextView) view.findViewById(R.id.email);
        txtemail.setText(getString(R.string.email));

        txtfb = (TextView) view.findViewById(R.id.facebook);
        txtfb.setText(getString(R.string.fb));
        txtfb.setTypeface(font);

        txttwitter = (TextView) view.findViewById(R.id.twitter);
        txttwitter.setText(getString(R.string.twitter));
        txttwitter.setTypeface(font);

        txtgplus = (TextView) view.findViewById(R.id.gplus);
        txtgplus.setText(getString(R.string.gplus));
        txtgplus.setTypeface(font);

        txtweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://reveriegroup.com/"));
                startActivity(intent);
            }
        });

        txtphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "01865444419";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);

            }
        });

        txtemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","hello@reveriegroup.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));


            }
        });

        txtfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String urlFb = "fb://page/180941008684739";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlFb));

                // If a Facebook app is installed, use it. Otherwise, launch
                // a browser
                final PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> list =
                        packageManager.queryIntentActivities(intent,
                                PackageManager.MATCH_DEFAULT_ONLY);
                if (list.size() == 0) {
                    final String urlBrowser = "https://www.facebook.com/ReveireLabMimosa";
                    intent.setData(Uri.parse(urlBrowser));
                }

                startActivity(intent);

            }
        });

        txttwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    // Check if the Twitter app is installed on the phone.
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setClassName("com.twitter.android", "com.twitter.android.ProfileActivity");
                    // Don't forget to put the "L" at the end of the id.
                    intent.putExtra("user_id", 493896474);
                    startActivity(intent);
                }
                catch (PackageManager.NameNotFoundException e)
                {
                    // If Twitter app is not installed, start browser.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/mimosalab")));
                }

            }
        });

        txtgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://plus.google.com/+LabMimosa"));
                startActivity(intent);

            }
        });


    }
}
