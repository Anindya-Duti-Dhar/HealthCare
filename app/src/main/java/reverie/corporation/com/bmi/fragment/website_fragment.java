package reverie.corporation.com.bmi.fragment;

import android.app.ProgressDialog;
import reverie.corporation.com.bmi.MainActivity;
import reverie.corporation.com.bmi.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Angry_Birds on 10/14/2016.
 */
public class website_fragment extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    private WebView webView;

    public static website_fragment newInstance() {
        website_fragment fragment = new website_fragment();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public website_fragment() {
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
        return inflater.inflate(R.layout.website_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.menu_website));

        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        // Initializing Internet Check
        if (hasConnection(getActivity())){
            //Get webView
            webView = (WebView)view.findViewById(R.id.webView_website);
            startWebView("http://www.reveriegroup.com/rmo/");
        }
        else{
            showNetDisabledAlertToUser(getActivity());
        }

    }

    // Internet check method
    public boolean hasConnection(Context context){
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()){
            return true;
        }
        NetworkInfo mobileNetwork=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()){
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()){
            return true;
        }
        return false;
    }

    // Create Dialog popup for internet checking
    public void showNetDisabledAlertToUser(final Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_message))
                .setPositiveButton(getString(R.string.dialog_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(dialogIntent);
                    }
                });

        alertDialogBuilder.setNegativeButton(getString(R.string.dialog_negative), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                /*website.this.finish();*/
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                home_fragment fragment = new home_fragment();
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
               /* Intent i = new Intent(context, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(i);
                getActivity().finish();*/
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case .this
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);

        //Load url in webview
        webView.loadUrl(url);

    }

}
