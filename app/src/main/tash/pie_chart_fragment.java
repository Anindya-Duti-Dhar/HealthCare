package reverie.corporation.com.bmi.fragment;

import reverie.corporation.com.bmi.R;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


/**
 * Created by Angry_Birds on 10/14/2016.
 */
public class pie_chart_fragment extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;
    Typeface font;
    TextView mFirstColumnTitle, mSecondColumnTitle, mThirdColumnTitle;

/*    TextView txtResultView, txtDate;*/

/*    DatabaseHelpher helper;*/

    public static pie_chart_fragment newInstance() {
        pie_chart_fragment fragment = new pie_chart_fragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public pie_chart_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fragment locked in landscape screen orientation
       // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pie_chart_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.menu_bmi));

        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        mFirstColumnTitle = (TextView) view.findViewById(R.id.first_column);
        mFirstColumnTitle.setText(getString(R.string.first_column));
        mFirstColumnTitle.setTypeface(font);

        mSecondColumnTitle = (TextView) view.findViewById(R.id.second_column);
        mSecondColumnTitle.setText(getString(R.string.second_column));
        mSecondColumnTitle.setTypeface(font);

        mThirdColumnTitle = (TextView) view.findViewById(R.id.third_column);
        mThirdColumnTitle.setText(getString(R.string.third_column));
        mThirdColumnTitle.setTypeface(font);

        // Initializing Google AdMob
        /*mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
               *//* .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        //bar chart
        BarChart barChart = (BarChart) view.findViewById(R.id.chart);
        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(25f, 0));
        entries.add(new BarEntry(25.5f, 1));
        entries.add(new BarEntry(25.1f, 2));

        BarDataSet dataset = new BarDataSet(entries, getString(R.string.menu_bmi));

        ArrayList<String> labels = new ArrayList<String>();

        labels.add("");
        labels.add("");
        labels.add("");

        // for create Grouped Bar chart
     /*   ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.add(new BarEntry(4f, 0));
        group1.add(new BarEntry(8f, 1));
        group1.add(new BarEntry(6f, 2));
        group1.add(new BarEntry(12f, 3));
        group1.add(new BarEntry(18f, 4));
        group1.add(new BarEntry(9f, 5));

        ArrayList<BarEntry> group2 = new ArrayList<>();
        group2.add(new BarEntry(6f, 0));
        group2.add(new BarEntry(7f, 1));
        group2.add(new BarEntry(8f, 2));
        group2.add(new BarEntry(12f, 3));
        group2.add(new BarEntry(15f, 4));
        group2.add(new BarEntry(10f, 5));

        BarDataSet barDataSet1 = new BarDataSet(group1, "Group 1");
        //barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet2 = new BarDataSet(group2, "Group 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<BarDataSet> dataset = new ArrayList<>();
        dataset.add(barDataSet1);
        dataset.add(barDataSet2);*/


        BarData data = new BarData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
        barChart.animateY(3000);

    }


}
