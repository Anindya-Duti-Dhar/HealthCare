package reverie.corporation.com.bmi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    //TextView txtAppTitle;
    //Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        //****** Create Thread that will sleep for 1.5 seconds *************//
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 1.5 seconds
                    sleep(1*1500);

                    // After 1 seconds redirect to another intent
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

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

