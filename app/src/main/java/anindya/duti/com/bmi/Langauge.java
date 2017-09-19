package anindya.duti.com.bmi;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;



public class Langauge extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void setLocaleBn (Context context){
        Locale locale = new Locale("bn");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    public static void setLocaleEn (Context context){
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

}