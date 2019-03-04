package anindya.duti.com.bmi;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import anindya.duti.com.bmi.R;
import anindya.duti.com.bmi.bmiCalculation.fragment.WhatIsBmi;
import anindya.duti.com.bmi.fragment.EditProfile;
import anindya.duti.com.bmi.fragment.about_fragment;
import anindya.duti.com.bmi.fragment.apps_fragment;
import anindya.duti.com.bmi.fragment.home;
import anindya.duti.com.bmi.waistCalculation.fragment.WhatIsWaist;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BMI_MAIN_PAGE";
    //Defining Variables
    Toolbar toolbar;
    private DrawerLayout drawerLayout;

    final Context mContext = this;

    Typeface font;

    String currentVersion, latestVersion;
    Dialog dialog;

    String mPlayStoreURL;

    ProgressDialog progressDialog;

    TextView mNavHeaderTitle, mNavHeaderMessage;

    private Boolean firstTime = null;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent alarmIntent;
    private Calendar alarmStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        tintManager.setTintColor(Color.parseColor("#1a746b"));

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainActivity.this.getSupportActionBar().setTitle(getString(R.string.bmi_home_toolbar));

        FontsOverride.setDefaultFont(MainActivity.this, "MONOSPACE", "android.ttf");

        font = Typeface.createFromAsset(getAssets(), "android.ttf");

        mPlayStoreURL = "https://play.google.com/store/apps/details?id=reverie.corporation.com.bmi";

        progressDialog = new ProgressDialog(MainActivity.this);

        // Initializing Internet Check
        if (hasConnection(MainActivity.this)){
            // Check App Version
            //CheckAppUpdateVersion();
        }

        else {
            // nothing to do
        }

        // set notification
        setNotification();

        initNavigationDrawer();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        home fragment = new home();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {

                    //Replacing the main content with about us
                    case R.id.about_us:
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        about_fragment fragment = new about_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with bmi
                    case R.id.what_is_bmi:
                        WhatIsBmi fragment1 = new WhatIsBmi();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment1);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with waist
                    case R.id.what_is_waist:
                        WhatIsWaist fragment2 = new WhatIsWaist();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment2);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with more apps
                    case R.id.more_apps:
                        apps_fragment fragment3 = new apps_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment3);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with Profile
                    case R.id.profile:
                        EditProfile fragment4 = new EditProfile();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment4);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with home
                    case R.id.home:
                        home fragment5 = new home();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment5);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        MainActivity.this.getSupportActionBar().setTitle(getString(R.string.bmi_home_title));
                        break;

                    //Rest of the case just show toast
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        mNavHeaderMessage = (TextView)header.findViewById(R.id.nav_header_message);
        mNavHeaderMessage.setText(getString(R.string.nav_header_Message));
        mNavHeaderMessage.setTypeface(font);
        mNavHeaderTitle = (TextView)header.findViewById(R.id.nav_header_title);
        mNavHeaderTitle.setText(getString(R.string.nav_header_title));
        mNavHeaderTitle.setTypeface(font);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        showExitDialogToUser(mContext);
        /*super.onBackPressed();
        this.finish();*/
    }

    // Create Dialog popup for Google Play store
    public void showExitDialogToUser(final Context context){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder
                .setMessage(getString(R.string.dialog3_message))
                .setPositiveButton(getString(R.string.dialog3_button), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawers(); //CLOSE Nav Drawer!
                            MainActivity.this.finish();
                        }else{
                            MainActivity.this.finish();
                        }
                    }
                });

        alertDialogBuilder.setNegativeButton(getString(R.string.dialog3_negative), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                home fragment6 = new home();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment6);
                fragmentTransaction.commit();
                drawerLayout.closeDrawers(); //CLOSE Nav Drawer!

            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
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

    private String getCurrentVersion(){
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;

        try {
            pInfo =  pm.getPackageInfo(this.getPackageName(),0);

        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        String currentVersion = pInfo.versionName;

        return currentVersion;
    }

    private class GetLatestVersion extends AsyncTask<String, String, String> {
        String latestVersion;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getString(R.string.please_wait));
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                //It retrieves the latest version by scraping the content of current version from play store at runtime
                String urlOfAppFromPlayStore = mPlayStoreURL;
                Document  doc = Jsoup.connect(urlOfAppFromPlayStore).get();
                latestVersion = doc.getElementsByAttributeValue("itemprop","softwareVersion").first().text();

            }catch (Exception e){
                e.printStackTrace();

            }

            return latestVersion;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.hide();
        }
    }

    private void CheckAppUpdateVersion() {
        String latestVersion = "";
        String currentVersion = getCurrentVersion();
        try {
            latestVersion = new GetLatestVersion().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //If the versions are not the same
        if(!currentVersion.equals(latestVersion)){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(getString(R.string.app_update_title));
            builder.setMessage(getString(R.string.app_update_available));

            //final AlertDialog ad = builder.show();

            builder.setPositiveButton(getString(R.string.update_app), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Click button action
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=reverie.corporation.com.bmi")));
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton(getString(R.string.cancel_update), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Cancel button action
                    dialog.dismiss();
                }
            });

            builder.setCancelable(false);
            builder.show();
        }

        else {
            // nothing to do
        }
    }

    public void setNotification() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        alarmIntent.addCategory("android.intent.category.DEFAULT");
        pendingIntent = PendingIntent.getBroadcast(this, 100, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmStartTime = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 21);
        alarmStartTime.set(Calendar.MINUTE, 0);
        alarmStartTime.set(Calendar.SECOND, 0);
        alarmStartTime.set(Calendar.AM_PM,Calendar.PM);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), getInterval(), pendingIntent);
    }

    private int getInterval() {
        int days = 1;
        int hours = 24;
        int minutes = 60;
        int seconds = 60;
        int milliseconds = 1000;
        int repeatMS = 15*(days * hours * minutes * seconds * milliseconds);
        return repeatMS;
    }

}