package reverie.corporation.com.bmi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class BMIPrefManager {
    SharedPreferences pref;
    Editor editor;
    Context mContext;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "talkremit";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    //update and get user preference for BMI
    private static final String PREFERENCE_DATE = "date";
    private static final String APPLICATION_DATE = "ap_date";

    private static final String PREFERENCE_HEIGHT = "height";
    private static final String APPLICATION_HEIGHT = "ap_height";

    private static final String PREFERENCE_CURRENTWEIGHT = "current_weight";
    private static final String APPLICATION_CURRENTWEIGHT = "ap_current_weight";

    private static final String PREFERENCE_DESIREDWEIGHT = "desired_weight";
    private static final String APPLICATION_DESIREDWEIGHT = "ap_desired_weight";

    private static final String PREFERENCE_DIFFERENCE = "difference";
    private static final String APPLICATION_DIFFERENCE = "ap_difference";

    private static final String PREFERENCE_AGE= "age";
    private static final String APPLICATION_AGE = "ap_age";

    private static final String PREFERENCE_GENDER= "Gender";
    private static final String APPLICATION_GENDER = "ap_Gender";

    private static final String PREFERENCE_BMI = "bmi";
    private static final String APPLICATION_BMI = "ap_bmi";


    //update and get user preference for BMI
    private static final String PREFERENCE_WAIST_DATE = "waist_date";
    private static final String APPLICATION_WAIST_DATE = "ap_waist_date";

    private static final String PREFERENCE_CURRENTWAIST = "current_waist";
    private static final String APPLICATION_CURRENTWAIST = "ap_current_waist";

    private static final String PREFERENCE_DESIREDWAIST = "desired_waist";
    private static final String APPLICATION_DESIREDWAIST = "ap_desired_waist";

    private static final String PREFERENCE_WAIST_DIFFERENCE = "waist_difference";
    private static final String APPLICATION_WAIST_DIFFERENCE = "ap_waist_difference";

    private static final String PREFERENCE_WAIST = "waist";
    private static final String APPLICATION_WAIST = "ap_waist";

    // Profile information
    private static final String PREFERENCE_FEET = "feet";
    private static final String APPLICATION_FEET = "ap_feet";

    public BMIPrefManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public static void setDate(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DATE, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_DATE, user);
        editor.commit();
    }

    public static String getDate(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DATE, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_DATE, "");
    }

    public static void setHeight(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_HEIGHT, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_HEIGHT, user);
        editor.commit();
    }

    public static String getHeight(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_HEIGHT, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_HEIGHT, "");
    }

    public static void setCurrentWeight(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_CURRENTWEIGHT, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_CURRENTWEIGHT, user);
        editor.commit();
    }

    public static String getCurrentWeight(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_CURRENTWEIGHT, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_CURRENTWEIGHT, "");
    }

    public static void setDesiredWeight(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DESIREDWEIGHT, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_DESIREDWEIGHT, user);
        editor.commit();
    }

    public static String getDesiredWeight(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DESIREDWEIGHT, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_DESIREDWEIGHT, "");
    }

    public static void setDifference(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DIFFERENCE, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_DIFFERENCE, user);
        editor.commit();
    }

    public static String getDifference(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DIFFERENCE, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_DIFFERENCE, "");
    }

    public static void setAge(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_AGE, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_AGE, user);
        editor.commit();
    }

    public static String getAge(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_AGE, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_AGE, "");
    }

    public static void setGender(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_GENDER, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_GENDER, user);
        editor.commit();
    }

    public static String getGender(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_GENDER, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_GENDER, "");
    }

    public static void setBmi(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_BMI, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_BMI, user);
        editor.commit();
    }

    public static String getBmi(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_BMI, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_BMI, "");
    }

    // Waist

    public static void setWaistDate(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_WAIST_DATE, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_WAIST_DATE, user);
        editor.commit();
    }

    public static String getWaistDate(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_WAIST_DATE, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_WAIST_DATE, "");
    }

    public static void setCurrentWaist(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_CURRENTWAIST, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_CURRENTWAIST, user);
        editor.commit();
    }

    public static String getCurrentWaist(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_CURRENTWAIST, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_CURRENTWAIST, "");
    }

    public static void setDesiredWaist(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DESIREDWAIST, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_DESIREDWAIST, user);
        editor.commit();
    }

    public static String getDesiredWaist(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_DESIREDWAIST, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_DESIREDWAIST, "");
    }

    public static void setWaistDifference(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_WAIST_DIFFERENCE, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_WAIST_DIFFERENCE, user);
        editor.commit();
    }

    public static String getWaistDifference(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_WAIST_DIFFERENCE, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_WAIST_DIFFERENCE, "");
    }

    public static void setWaist(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_WAIST, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_WAIST, user);
        editor.commit();
    }

    public static String getWaist(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_WAIST, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_WAIST, "");
    }

    public static void setFeet(final Context ctx, final String user) {
        final SharedPreferences prefs = ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_FEET, Context.MODE_PRIVATE);
        final Editor editor = prefs.edit();
        editor.putString(BMIPrefManager.APPLICATION_FEET, user);
        editor.commit();
    }

    public static String getFeet(final Context ctx) {
        return ctx.getSharedPreferences(
                BMIPrefManager.PREFERENCE_FEET, Context.MODE_PRIVATE)
                .getString(BMIPrefManager.APPLICATION_FEET, "");
    }
}
