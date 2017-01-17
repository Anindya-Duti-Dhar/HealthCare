package reverie.corporation.com.bmi.exercise.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import reverie.corporation.com.bmi.R;

/**
 * Created by Administrator on 12/26/2016.
 */

public class ExerciseDetails extends AppCompatActivity {

    //Defining Variables
    private static final String TAG = "ExerciseDetails";
    String mItemTitleText;
    TextView mExercise_details_TootBar_Text, mExerciseDetailsText, mExerciseDetailsTitleText, mDialogHeader;
    String mToolbarItemTitle;
    String mItemHint;

    ImageView view, mExerciseDetailsImage, mAnimationStart;
    private AnimationDrawable frameAnimation;

    //Defining Variables
    private AdView mAdMobAdView;
    Typeface font;

    Button mStart, mPlay;
    ImageButton mCancelDialog;

    private Dialog dialog;

    FloatingActionButton mExerciseFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // animation for activity entry
        //this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
        setContentView(R.layout.exercise_details);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        tintManager.setTintColor(Color.parseColor("#1a746b"));

        // Set up the toolbar.
        Toolbar start_exercise_toolbar = (Toolbar) findViewById(R.id.exercise_details_toolbar);
        setSupportActionBar(start_exercise_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar sextb = getSupportActionBar();                       //scntb: start_exercise_toolbar
        sextb.setDisplayHomeAsUpEnabled(true);
        sextb.setDisplayShowHomeEnabled(true);

        // getting data from intent
        Bundle bundle = getIntent().getExtras();
        mToolbarItemTitle = bundle.getString("toolbar_item_name");
        mItemHint = bundle.getString("item_hint");

        font = Typeface.createFromAsset(ExerciseDetails.this.getAssets(), "android.ttf");

        mExercise_details_TootBar_Text = (TextView) findViewById(R.id.exercise_details_toolbar_title);
        mExercise_details_TootBar_Text.setText(mToolbarItemTitle);
        mExercise_details_TootBar_Text.setTypeface(font);
        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        mExerciseDetailsText = (TextView) findViewById(R.id.exercise_details_text);
        mExerciseDetailsImage = (ImageView) findViewById(R.id.exercise_details_imageView);

        mExerciseDetailsTitleText = (TextView) findViewById(R.id.exercise_details_text_title);
        mExerciseDetailsTitleText.setText(getString(R.string.exerciseDetailsTitle));
        mExerciseDetailsTitleText.setTypeface(font);

        setView();

        // initializing floating action button
        mExerciseFab = (FloatingActionButton) findViewById(R.id.exercise_details_fab);
        mExerciseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(), "BMI Added", Toast.LENGTH_SHORT).show();
                // custom dialog
                PlayExerciseDialog();
                // hide fab
                mExerciseFab.hide();
            }
        });

    }

    public void setView(){

        mExerciseDetailsText.setTypeface(font);

        if (mItemHint.equals("abdominal_crunches")) {
            mExerciseDetailsImage.setImageResource(R.drawable.abdominal_crunches_img);
            mExerciseDetailsText.setText(getString(R.string.ab_crunches_details));
        } else if (mItemHint.equals("high_stepping")) {
            mExerciseDetailsImage.setImageResource(R.drawable.high_stepping_img);
            mExerciseDetailsText.setText(getString(R.string.high_step_details));
        } else if (mItemHint.equals("jumping_jacks")) {
            mExerciseDetailsImage.setImageResource(R.drawable.jumping_jacks_img);
            mExerciseDetailsText.setText(getString(R.string.jumping_details));
        } else if (mItemHint.equals("lunges")) {
            mExerciseDetailsImage.setImageResource(R.drawable.lunges_img);
            mExerciseDetailsText.setText(getString(R.string.lunges_details));
        } else if (mItemHint.equals("plank")) {
            mExerciseDetailsImage.setImageResource(R.drawable.plank_img);
            mExerciseDetailsText.setText(getString(R.string.plank_details));
        } else if (mItemHint.equals("push_up")) {
            mExerciseDetailsImage.setImageResource(R.drawable.push_ups_img);
            mExerciseDetailsText.setText(getString(R.string.push_up_details));
        } else if (mItemHint.equals("push_up_and_rotation")) {
            mExerciseDetailsImage.setImageResource(R.drawable.push_up_and_rotation_img);
            mExerciseDetailsText.setText(getString(R.string.push_rotate_details));
        } else if (mItemHint.equals("side_plank")) {
            mExerciseDetailsImage.setImageResource(R.drawable.side_plank_img);
            mExerciseDetailsText.setText(getString(R.string.side_plank_details));
        } else if (mItemHint.equals("squats")) {
            mExerciseDetailsImage.setImageResource(R.drawable.squats_img);
            mExerciseDetailsText.setText(getString(R.string.squats_details));
        } else if (mItemHint.equals("step_up_onto_chair")) {
            mExerciseDetailsImage.setImageResource(R.drawable.step_up_onto_chair_img);
            mExerciseDetailsText.setText(getString(R.string.step_up_details));
        } else if (mItemHint.equals("triceps_dips_on_chair")) {
            mExerciseDetailsImage.setImageResource(R.drawable.triceps_dips_on_chair_img);
            mExerciseDetailsText.setText(getString(R.string.triceps_details));
        } else if (mItemHint.equals("wall_sit")) {
            mExerciseDetailsImage.setImageResource(R.drawable.wall_sit_img);
            mExerciseDetailsText.setText(getString(R.string.wall_sit_details));
        }
    }

    public void PlayExerciseDialog() {
        dialog = new Dialog(ExerciseDetails.this);  // always give context of activity.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.play_exercise);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialog.show();

        // Type casting the Image View
        view = (ImageView) dialog.findViewById(R.id.imageView);
        mDialogHeader = (TextView) dialog.findViewById(R.id.animationHeader);
        mDialogHeader.setTypeface(font);
        mAnimationStart = (ImageView) dialog.findViewById(R.id.start);

        if (mItemHint.equals("abdominal_crunches")) {
            // Setting animation_list.xml as the background of the image view
            view.setBackgroundResource(R.drawable.abdominal_crunches_frame);
            mDialogHeader.setText(getString(R.string.ab_crunches_title));
        } else if (mItemHint.equals("high_stepping")) {
            view.setBackgroundResource(R.drawable.high_stepping_frame);
            mDialogHeader.setText(getString(R.string.high_step_title));
        } else if (mItemHint.equals("jumping_jacks")) {
            view.setBackgroundResource(R.drawable.jumping_jacks_frame);
            mDialogHeader.setText(getString(R.string.jumping_title));
        } else if (mItemHint.equals("lunges")) {
            view.setBackgroundResource(R.drawable.lunges_frame);
            mDialogHeader.setText(getString(R.string.lunges_title));
        } else if (mItemHint.equals("plank")) {
            view.setBackgroundResource(R.drawable.plank_frame);
            mDialogHeader.setText(getString(R.string.plank_title));
        } else if (mItemHint.equals("push_up")) {
            view.setBackgroundResource(R.drawable.push_up_frame);
            mDialogHeader.setText(getString(R.string.push_up_title));
        } else if (mItemHint.equals("push_up_and_rotation")) {
            view.setBackgroundResource(R.drawable.push_up_and_rotation_frame);
            mDialogHeader.setText(getString(R.string.push_rotate_title));
        } else if (mItemHint.equals("side_plank")) {
            view.setBackgroundResource(R.drawable.side_plank_frame);
            mDialogHeader.setText(getString(R.string.side_plank_title));
        } else if (mItemHint.equals("squats")) {
            view.setBackgroundResource(R.drawable.squats_frame);
            mDialogHeader.setText(getString(R.string.squats_title));
        } else if (mItemHint.equals("step_up_onto_chair")) {
            view.setBackgroundResource(R.drawable.step_up_onto_chair_frame);
            mDialogHeader.setText(getString(R.string.step_up_title));
        } else if (mItemHint.equals("triceps_dips_on_chair")) {
            view.setBackgroundResource(R.drawable.triceps_dips_on_chair_frame);
            mDialogHeader.setText(getString(R.string.triceps_title));
        } else if (mItemHint.equals("wall_sit")) {
            view.setBackgroundResource(R.drawable.wall_sit_frame);
            mDialogHeader.setText(getString(R.string.wall_sit_title));
        }

        // Type casting the Animation drawable
        frameAnimation = (AnimationDrawable) view.getBackground();

        //set true if you want to animate only once
        frameAnimation.setOneShot(false);

        mAnimationStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frameAnimation.isRunning()){
                    frameAnimation.stop();
                    mAnimationStart.setImageResource(R.drawable.ic_av_play_circle_fill);
                }
                else {
                    frameAnimation.start();
                    mAnimationStart.setImageResource(R.drawable.ic_av_pause_circle_fill);
                }
            }
        });

        // set the custom dialog components - text, image and button
        //image.setImageResource(R.drawable.app_icon);

        mCancelDialog = (ImageButton) dialog.findViewById(R.id.cancel_btn_play);

        mCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mExerciseFab.show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    // back arrow action
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // back button press method
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // animation for activity exit
        //overridePendingTransition(R.anim.animation_exit, R.anim.animation_enter);
    }

}

