package reverie.corporation.com.bmi.fragment;

import reverie.corporation.com.bmi.FontsOverride;
import reverie.corporation.com.bmi.MainActivity;
import reverie.corporation.com.bmi.R;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

/**
 * Created by Angry_Birds on 10/14/2016.
 */
public class about_fragment extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    TextView txtAboutTitle, txtAboutMessage, txtAboutMainText, txtweb, txtgplus, txtemail, txtphone, txtfb, txttwitter;
    Typeface font;
    ImageButton cancelButton;

    public static FloatingActionButton about_fab;

    private Dialog dialog;

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

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.about_us_toolbar));

        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        FontsOverride.setDefaultFont(getActivity(), "MONOSPACE", "android.ttf");

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

        // initializing floating action button
        about_fab = (FloatingActionButton) view.findViewById(R.id.about_fab);
        about_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(), "BMI Added", Toast.LENGTH_SHORT).show();
                // custom dialog
                getContactDialog();
                // hide fab
                about_fab.hide();
            }
        });

    }

    private void getContactDialog() {

        // custom dialog
        dialog = new Dialog(getActivity());  // always give context of activity.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.setContentView(R.layout.contact_dialog);

        dialog.show();

        txtweb = (TextView) dialog.findViewById(R.id.website);
        txtweb.setText(getString(R.string.website));


        txtphone = (TextView) dialog.findViewById(R.id.phone);
        txtphone.setText(getString(R.string.mobile));
        txtphone.setTypeface(font);

        txtemail = (TextView) dialog.findViewById(R.id.email);
        txtemail.setText(getString(R.string.email));

        txtfb = (TextView) dialog.findViewById(R.id.facebook);
        txtfb.setText(getString(R.string.fb));
        txtfb.setTypeface(font);

        txttwitter = (TextView) dialog.findViewById(R.id.twitter);
        txttwitter.setText(getString(R.string.twitter));
        txttwitter.setTypeface(font);

        txtgplus = (TextView) dialog.findViewById(R.id.gplus);
        txtgplus.setText(getString(R.string.gplus));
        txtgplus.setTypeface(font);

        cancelButton = (ImageButton) dialog.findViewById(R.id.cancel_btn);

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

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dismiss dialog
                dialog.dismiss();
                // showing fab
                about_fab.show();
            }
        });

    }

}
