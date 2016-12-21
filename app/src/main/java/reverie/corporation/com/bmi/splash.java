package reverie.corporation.com.bmi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    //TextView txtAppTitle;
    //Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Intent i = new Intent(splash.this, MainActivity.class);
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




		/*setContentView(R.layout.splash);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawableResource(R.drawable.splash_text) ;

        font = Typeface.createFromAsset(splash.this.getAssets(), "android.ttf");

        //txtAppTitle = (TextView) findViewById(R.id.appTitle);
       // txtAppTitle.setText(getString(R.string.app_name));
        //txtAppTitle.setTypeface(font);

		// METHOD Splash   
        
        *//****** Create Thread that will sleep for 1 seconds *************//*
       Thread background = new Thread() {
           public void run() {
                
               try {
                   // Thread will sleep for 1 seconds
                   sleep(1*1000);
                    
                   // After 1 seconds redirect to another intent
                   Intent i=new Intent(getBaseContext(),MainActivity.class);
                   startActivity(i);
                    
                   //Remove activity
                   finish();
                    
               } catch (Exception e) {
                
               }
           }
       };
        
       // start thread
       background.start();
        
   }
    
   @Override
   protected void onDestroy() {
        
       super.onDestroy();
       }
    */
   }

