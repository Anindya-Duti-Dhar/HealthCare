package reverie.corporation.com.bmi.exercise.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import reverie.corporation.com.bmi.R;
import reverie.corporation.com.bmi.exercise.adapter.Exercise1ListAdapter;
import reverie.corporation.com.bmi.exercise.model.Exercise1ListItem;

/**
 * Created by Administrator on 12/23/2016.
 */

public class ExerciseHome extends Fragment {

    //Defining Variables
    private static final String TAG = "Exercise";
    String mItemTitleText;
    TextView mExercise_TootBar_Text;
    String mToolbarItemTitle;

    //Defining Variables
    private AdView mAdMobAdView;
    Typeface font;
    Intent intent;
    // ArrayList
    ArrayList<Exercise1ListItem> exerciseListItem;
    RecyclerView mRecyclerView;
    Exercise1ListAdapter mExerciseAdapter;
    String mItemName;
    String mItemHint;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    public static ExerciseHome newInstance() {
        ExerciseHome fragment = new ExerciseHome();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public ExerciseHome() {
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
        return inflater.inflate(R.layout.exercise_1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.exercise_toolbar));

        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        exerciseListItem = new ArrayList<Exercise1ListItem>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.exercise_1_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mExerciseAdapter = new Exercise1ListAdapter(getActivity(), exerciseListItem);
        mRecyclerView.setAdapter(mExerciseAdapter);

        // Select item on listclick
  /*      ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("search", "here---------------- listener");
                        Exercise1ListItem data = exerciseListItem.get(position);
                        mItemName = data.getTitle();
                        Log.d("ItemMAinName: ", mItemName);
                        Toast.makeText(getActivity(), "Item Main Name: "+mItemName, Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getActivity(), Conversation.class);
                        //intent.putExtra("toolbar_display_name", mDisplayName);
                        //getActivity().startActivity(intent);

                    }
                }
        );*/

    }

}