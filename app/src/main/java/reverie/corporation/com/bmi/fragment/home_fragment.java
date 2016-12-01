package reverie.corporation.com.bmi.fragment;

import android.app.Dialog;
import reverie.corporation.com.bmi.DatabaseHelpher;
import reverie.corporation.com.bmi.FontsOverride;
import reverie.corporation.com.bmi.MainActivity;
import reverie.corporation.com.bmi.R;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Angry_Birds on 10/14/2016.
 */
public class home_fragment extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;

    EditText etWeight,etHeight;
    Typeface font;
    Button btnSubmit, tipsButton, dietButton;
    TextView txtWelcome, txtWeightTitle, txtHeightTitle,resultText;
    private Dialog dialog;
    ImageView cartoon;
    ImageButton cancelButton;
    Context mContext;
    DatabaseHelpher helpher;
    String oper = "";


    public static home_fragment newInstance() {
        home_fragment fragment = new home_fragment();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public home_fragment() {
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
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.menu_home));

        /*dbList= new ArrayList<DatabaseModel>();*/

        // Initializing Google AdMob
        /*mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        FontsOverride.setDefaultFont(getActivity(), "MONOSPACE", "android.ttf");

        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        txtWelcome = (TextView) view.findViewById(R.id.welcome);
        txtWelcome.setText(getString(R.string.welcome_text));
        txtWelcome.setTypeface(font);

        txtWeightTitle = (TextView) view.findViewById(R.id.weight_text);
        txtWeightTitle.setText(getString(R.string.hint_weight));
        txtWeightTitle.setTypeface(font);

        txtHeightTitle = (TextView) view.findViewById(R.id.height_text);
        txtHeightTitle.setText(getString(R.string.hint_height));
        txtHeightTitle.setTypeface(font);

        etWeight = (EditText)view.findViewById(R.id.etWeight);
        etHeight = (EditText)view.findViewById(R.id.etHeight);

        btnSubmit  =(Button)view.findViewById(R.id.calculate);
        btnSubmit.setText(getString(R.string.submit_button));
        btnSubmit.setTypeface(font);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

    }

    /**
     * Validating form
     */
    private void submitForm() {

        int created_at = 0;
        double result = 0;

        if (etWeight.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), getString(R.string.err_msg_weight), Toast.LENGTH_SHORT).show();
        }

        else if (etHeight.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), getString(R.string.err_msg_height), Toast.LENGTH_SHORT).show();
        }

        else {
            // custom dialog
            dialog = new Dialog(getActivity());  // always give context of activity.
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                public void onClick(View v)
                {
                    dialog.dismiss();
                }
            });

            //dietButton.setOnClickListener(new View.OnClickListener() {
               // @Override
                //public void onClick(View v)
                //{
               //     dialog.dismiss();
                //}
            //});

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
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
            }
            else if ((KgInch >= 15.0) && (KgInch < 16.0)) {
                resultText.setText(getString(R.string.range_15_16));
            }
            else if ((KgInch >= 16.0) && (KgInch < 18.5)) {
                resultText.setText(getString(R.string.range_16_18));
            }
            else if ((KgInch >= 18.5) && (KgInch < 25.0)) {
                resultText.setText(getString(R.string.range_18_25));
            }
            else if ((KgInch >= 25.0) && (KgInch < 30.0)) {
                resultText.setText(getString(R.string.range_25_30));
            }
            else if ((KgInch >= 30.0) && (KgInch < 35.0)) {
                resultText.setText(getString(R.string.range_30_35));
            }
            else if ((KgInch >= 35.0) && (KgInch < 40.0)) {
                resultText.setText(getString(R.string.range_35_40));
            }
            else {
                resultText.setText(getString(R.string.range_40));
            }

        }
        helpher = new DatabaseHelpher(getActivity());
        helpher.insertIntoDB(result, created_at);

    }

}
