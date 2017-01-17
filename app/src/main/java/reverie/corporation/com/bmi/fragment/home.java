package reverie.corporation.com.bmi.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import reverie.corporation.com.bmi.Langauge;
import reverie.corporation.com.bmi.MainActivity;
import reverie.corporation.com.bmi.R;
import reverie.corporation.com.bmi.bmiCalculation.fragment.BmiHome;
import reverie.corporation.com.bmi.exercise.fragment.ExerciseHome;
import reverie.corporation.com.bmi.waistCalculation.fragment.WaistHome;

/**
 * Created by Administrator on 12/27/2016.
 */

public class home extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    TextView txtAboutTitle, txtAboutMessage, txtAboutMainText;
    Typeface font;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    String CurntStatus;

    Handler handler;

    public static home newInstance() {
        home fragment = new home();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public home() {
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
        // Change the Language
        Langauge.setLocaleBn(getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_toolbar));

        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        handler = new Handler();

        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        //txtAboutTitle = (TextView) view.findViewById(R.id.aboutTitle);
        //txtAboutTitle.setText(getString(R.string.about_title));
        //txtAboutTitle.setTypeface(font);

        // initialize tab layout with tab icon
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.bmi_tab_selector));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.waist_tab_selector));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.exercise_tab_selector));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // initialize view pager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);         // for smooth transition between tabs
        // initialize view pager adapter and setting that adapter
        final PagerAdapter adapter = new PageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        // view pager listener
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // tab listener
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // get the position which tab is selected
                viewPager.setCurrentItem(tab.getPosition());
                int Status = tab.getPosition();
                CurntStatus = String.valueOf(Status);
                // setting different toolbar title for individual tab
                if (tab.getPosition() == 0) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_toolbar));
                    BmiHome.bmi_fab.hide();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            BmiHome.bmi_fab.show();
                        }
                    }, 200);
                } else if (tab.getPosition() == 1) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.waist_home_toolbar));
                    WaistHome.fab.hide();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            WaistHome.fab.show();
                        }
                    }, 200);
                } else if (tab.getPosition() == 2) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.exercise_toolbar));
                }  else {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_title));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // get the position which tab is selected
                viewPager.setCurrentItem(tab.getPosition());
                int Status = tab.getPosition();
                CurntStatus = String.valueOf(Status);
                // setting different toolbar title for individual tab
                if (tab.getPosition() == 0) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_toolbar));
                    BmiHome.bmi_fab.hide();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            BmiHome.bmi_fab.show();
                        }
                    }, 200);
                } else if (tab.getPosition() == 1) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.waist_home_toolbar));
                    WaistHome.fab.hide();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            WaistHome.fab.show();
                        }
                    }, 200);
                } else if (tab.getPosition() == 2) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.exercise_toolbar));
                }  else {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_title));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // get the position which tab is selected
                viewPager.setCurrentItem(tab.getPosition());
                int Status = tab.getPosition();
                CurntStatus = String.valueOf(Status);
                // setting different toolbar title for individual tab
                if (tab.getPosition() == 0) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_toolbar));
                    BmiHome.bmi_fab.hide();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            BmiHome.bmi_fab.show();
                        }
                    }, 200);
                } else if (tab.getPosition() == 1) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.waist_home_toolbar));
                    WaistHome.fab.hide();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            WaistHome.fab.show();
                        }
                    }, 200);
                } else if (tab.getPosition() == 2) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.exercise_toolbar));
                }  else {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_title));
                }
            }
        });

    }

    // view pager adapter class
    class PageAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PageAdapter(FragmentManager fm, int numTabs) {
            super(fm);
            this.mNumOfTabs = numTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    BmiHome BmiHome = new BmiHome();
                    return BmiHome;
                case 1:
                    WaistHome WaistHome = new WaistHome();
                    return WaistHome;
                case 2:
                    ExerciseHome ExerciseHome = new ExerciseHome();
                    return ExerciseHome;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

}
