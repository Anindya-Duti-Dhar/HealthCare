package reverie.corporation.com.bmi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import reverie.corporation.com.bmi.start.activity.WelcomeActivity;

public class splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;
    private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        tintManager.setTintColor(Color.parseColor("#1a746b"));

        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        //go to home page
        GoToNext();
    }

    // Go to Home Page
    public void GoToNext(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Intent i = new Intent(splash.this, WelcomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
   }

