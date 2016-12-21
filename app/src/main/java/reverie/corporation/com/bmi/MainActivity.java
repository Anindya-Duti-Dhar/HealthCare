package reverie.corporation.com.bmi;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;

import reverie.corporation.com.bmi.fragment.about_fragment;
import reverie.corporation.com.bmi.fragment.apps_fragment;
import reverie.corporation.com.bmi.fragment.contact_fragment;
import reverie.corporation.com.bmi.fragment.home_fragment;
import reverie.corporation.com.bmi.fragment.pie_chart_fragment;
import reverie.corporation.com.bmi.fragment.website_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    MenuItem mPreviousMenuItem;
    private AdView mAdMobAdView;

    private Dialog dialog, tipsDialog;

    final Context mContext = this;

    EditText etWeight, etHeight;
    ImageView cartoon;
    Typeface font;
    Button btnSubmit, tipsButton, dietButton;
    ImageButton cancelButton, tipsCancelButton;
    TextView txtWelcome, txtWeightTitle, txtHeightTitle, txtNav_Header_Title, txtNav_Header_Message, resultText;
    TextView txtTips1, txtTips2, txtTips3, txtTips4, txtTips5 ;
    DatabaseHelpher helpher;

    String oper = "";

    private Boolean firstTime = null;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent alarmIntent;
    private Calendar alarmStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        isFirstTime();

        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                   *//* .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        FontsOverride.setDefaultFont(this, "MONOSPACE", "android.ttf");

        font = Typeface.createFromAsset(getAssets(), "android.ttf");

        txtWelcome = (TextView) findViewById(R.id.welcome);
        txtWelcome.setText(getString(R.string.welcome_text));
        txtWelcome.setTypeface(font);

        txtWeightTitle = (TextView) findViewById(R.id.weight_text);
        txtWeightTitle.setText(getString(R.string.hint_weight));
        txtWeightTitle.setTypeface(font);

        txtHeightTitle = (TextView) findViewById(R.id.height_text);
        txtHeightTitle.setText(getString(R.string.hint_height));
        txtHeightTitle.setTypeface(font);

        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight = (EditText) findViewById(R.id.etHeight);

        txtNav_Header_Title = (TextView) findViewById(R.id.nav_header_title);
        txtNav_Header_Message = (TextView) findViewById(R.id.nav_header_message);

        btnSubmit = (Button) findViewById(R.id.calculate);
        btnSubmit.setText(getString(R.string.submit_button));
        btnSubmit.setTypeface(font);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        initNavigationDrawer();

    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {

                    //Replacing the main content with about_fragment
                    case R.id.about:
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        about_fragment fragment = new about_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with contact_fragment
                    case R.id.contact:
                        contact_fragment fragment1 = new contact_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment1);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with website_fragment
                    case R.id.website:
                        website_fragment fragment2 = new website_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment2);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with apps_fragment
                    case R.id.apps:
                        apps_fragment fragment3 = new apps_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment3);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with pie_chart_fragment
                    case R.id.pie_chart:
                        pie_chart_fragment fragment4 = new pie_chart_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment4);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with home_fragment
                    case R.id.home:
                        home_fragment fragment5 = new home_fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment5);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
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

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
                setNotification();
            }
        }
        return firstTime;
    }

    /**
     * Validating form
     */
    private void submitForm() {

        int created_at = 0;
        double result = 0;

        if (etWeight.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.err_msg_weight), Toast.LENGTH_SHORT).show();
        } else if (etHeight.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.err_msg_height), Toast.LENGTH_SHORT).show();
        } else if (!etWeight.getText().toString().trim().isEmpty()){
            String value= etWeight.getText().toString();
            int number=Integer.parseInt(value);
            Log.d("value", value);
            if (number >= 2 && number <= 130 ){
                Toast.makeText(getApplicationContext(), getString(R.string.text_validity), Toast.LENGTH_SHORT).show();
            }
            else {
                Log.d("test", "test");
                // custom dialog
                dialog = new Dialog(MainActivity.this);  // always give context of activity.
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_popup);

                dialog.show();

                // set the custom dialog components - text, image and button
                resultText = (TextView) dialog.findViewById(R.id.result_text);
                cartoon = (ImageView) dialog.findViewById(R.id.cartoon);
                //image.setImageResource(R.drawable.app_icon);

                tipsButton = (Button) dialog.findViewById(R.id.tipsButton);
                tipsButton.setText(getString(R.string.tips_button));
                tipsButton.setTypeface(font);
                //dietButton = (Button) dialog.findViewById(R.id.dietButton);
                //dietButton.setText(getString(R.string.diet_button));
                //dietButton.setTypeface(font);
                cancelButton = (ImageButton) dialog.findViewById(R.id.cancel_btn);

                tipsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        // custom dialog
                        tipsDialog = new Dialog(MainActivity.this);  // always give context of activity.
                        tipsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        tipsDialog.setContentView(R.layout.tips_dialog);

                        tipsDialog.show();

                        // set the custom dialog components - text, image and button
                        txtTips1 = (TextView) tipsDialog.findViewById(R.id.tipsOne);
                        txtTips1.setText(getString(R.string.err_msg_height));
                        txtTips1.setTypeface(font);
                        txtTips2 = (TextView) tipsDialog.findViewById(R.id.tipsTwo);
                        txtTips1.setText(getString(R.string.err_msg_height));
                        txtTips1.setTypeface(font);
                        txtTips3 = (TextView) tipsDialog.findViewById(R.id.tipsThree);
                        txtTips1.setText(getString(R.string.err_msg_height));
                        txtTips1.setTypeface(font);
                        txtTips4 = (TextView) tipsDialog.findViewById(R.id.tipsFour);
                        txtTips1.setText(getString(R.string.err_msg_height));
                        txtTips1.setTypeface(font);
                        txtTips5 = (TextView) tipsDialog.findViewById(R.id.tipsFive);
                        txtTips1.setText(getString(R.string.err_msg_height));
                        txtTips1.setTypeface(font);

                        tipsCancelButton = (ImageButton) tipsDialog.findViewById(R.id.cancel_btn_tips);

                        tipsCancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tipsDialog.dismiss();
                            }
                        });
                    }
                });

                //dietButton.setOnClickListener(new View.OnClickListener() {
                   // @Override
                    //public void onClick(View v) {
                     //   dialog.dismiss();
                   // }
                //});

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                float Kg = 0;
                float Inch = 0;
                oper = "/";
                oper = "*";
                Kg = Float.parseFloat(etWeight.getText().toString());
                Inch = Float.parseFloat(etHeight.getText().toString());

                double Meter = Inch / 39.3701;
                double KgInch = (Kg) / ((Meter) * (Meter));
                result = KgInch;

                if (KgInch < 15.0) {
                    resultText.setText(getString(R.string.range_15));
                    cartoon.setImageResource(R.drawable.figurestage1);
                } else if ((KgInch >= 15.0) && (KgInch < 16.0)) {
                    resultText.setText(getString(R.string.range_15_16));
                    cartoon.setImageResource(R.drawable.figurestage2);
                } else if ((KgInch >= 16.0) && (KgInch < 18.5)) {
                    resultText.setText(getString(R.string.range_16_18));
                    cartoon.setImageResource(R.drawable.figurestage3);
                } else if ((KgInch >= 18.5) && (KgInch < 25.0)) {
                    resultText.setText(getString(R.string.range_18_25));
                    cartoon.setImageResource(R.drawable.figurestage4);
                } else if ((KgInch >= 25.0) && (KgInch < 30.0)) {
                    resultText.setText(getString(R.string.range_25_30));
                    cartoon.setImageResource(R.drawable.figurestage5);
                } else if ((KgInch >= 30.0) && (KgInch < 35.0)) {
                    resultText.setText(getString(R.string.range_30_35));
                    cartoon.setImageResource(R.drawable.figurestage6);
                } else if ((KgInch >= 35.0) && (KgInch < 40.0)) {
                    resultText.setText(getString(R.string.range_35_40));
                    cartoon.setImageResource(R.drawable.figurestage7);
                } else {
                    resultText.setText(getString(R.string.range_40));
                    cartoon.setImageResource(R.drawable.figurestage8);
                }

            }

        }

        else if(!etHeight.getText().toString().trim().isEmpty()){
            String value2= etHeight.getText().toString();
            int number2=Integer.parseInt(value2);
             if (number2 > 170 && number2 <32){
            Toast.makeText(getApplicationContext(), getString(R.string.text_validity), Toast.LENGTH_SHORT).show();
            }
            else {
                // custom dialog
                dialog = new Dialog(MainActivity.this);  // always give context of activity.
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_popup);

                dialog.show();

                // set the custom dialog components - text, image and button
                resultText = (TextView) dialog.findViewById(R.id.result_text);
                cartoon = (ImageView) dialog.findViewById(R.id.cartoon);
                //image.setImageResource(R.drawable.app_icon);

                tipsButton = (Button) dialog.findViewById(R.id.tipsButton);
                tipsButton.setText(getString(R.string.tips_button));
                tipsButton.setTypeface(font);
                //dietButton = (Button) dialog.findViewById(R.id.dietButton);
                //dietButton.setText(getString(R.string.diet_button));
                //dietButton.setTypeface(font);
                cancelButton = (ImageButton) dialog.findViewById(R.id.cancel_btn);

                tipsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

               // dietButton.setOnClickListener(new View.OnClickListener() {
                    //@Override
                  //  public void onClick(View v) {
                    //    dialog.dismiss();
                   // }
              //  });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                float Kg = 0;
                float Inch = 0;
                oper = "/";
                oper = "*";
                Kg = Float.parseFloat(etWeight.getText().toString());
                Inch = Float.parseFloat(etHeight.getText().toString());

                double Meter = Inch / 39.3701;
                double KgInch = (Kg) / ((Meter) * (Meter));
                result = KgInch;

                if (KgInch < 15.0) {
                    resultText.setText(getString(R.string.range_15));
                } else if ((KgInch >= 15.0) && (KgInch < 16.0)) {
                    resultText.setText(getString(R.string.range_15_16));
                } else if ((KgInch >= 16.0) && (KgInch < 18.5)) {
                    resultText.setText(getString(R.string.range_16_18));
                } else if ((KgInch >= 18.5) && (KgInch < 25.0)) {
                    resultText.setText(getString(R.string.range_18_25));
                } else if ((KgInch >= 25.0) && (KgInch < 30.0)) {
                    resultText.setText(getString(R.string.range_25_30));
                } else if ((KgInch >= 30.0) && (KgInch < 35.0)) {
                    resultText.setText(getString(R.string.range_30_35));
                } else if ((KgInch >= 35.0) && (KgInch < 40.0)) {
                    resultText.setText(getString(R.string.range_35_40));
                } else {
                    resultText.setText(getString(R.string.range_40));
                }

            }

        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.text_validity), Toast.LENGTH_SHORT).show();
        }

        //helpher = new DatabaseHelpher(MainActivity.this);
        //helpher.insertIntoDB(result, created_at);

    }

    public void setNotification() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(MainActivity.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(  MainActivity.this, 0, alarmIntent, 0);

        alarmStartTime = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 21);
        alarmStartTime.set(Calendar.MINUTE, 0);
        alarmStartTime.set(Calendar.SECOND, 0);
        alarmManager.setRepeating(AlarmManager.RTC, alarmStartTime.getTimeInMillis(), getInterval(), pendingIntent);

        Log.d("Alarm Set: ", "Start");
        Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    private int getInterval() {
        int days = 7;
        int hours = 24;
        int minutes = 60;
        int seconds = 60;
        int milliseconds = 1000;
        int repeatMS = days * hours * minutes * seconds * milliseconds;
        return repeatMS;
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
                home_fragment fragment6 = new home_fragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment6);
                fragmentTransaction.commit();
                drawerLayout.closeDrawers(); //CLOSE Nav Drawer!

            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


}