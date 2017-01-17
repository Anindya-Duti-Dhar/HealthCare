package reverie.corporation.com.bmi.waistCalculation.fragment;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import reverie.corporation.com.bmi.FontsOverride;
import reverie.corporation.com.bmi.R;
import reverie.corporation.com.bmi.utils.BMIPrefManager;

/**
 * Created by Administrator on 12/29/2016.
 */

public class WaistHome extends Fragment {

    //Defining Variables
    private AdView mAdMobAdView;

    TextView WaistResult, WaistResultStatus, Age, UserWaist, Height, CurrentDate, CurrentWaist, DesireWaist, UserWaistDifferenceText;
    ImageView WaistMeterImage;
    Typeface font;

    double WaistCalculation;
    double CurrentWaistCm;
    double UserHeight;
    double UserDesiredWaist;
    double UserWaistDifference;
    double Year;


    public static FloatingActionButton fab;

    private Dialog dialog;

    TextView waistTitle, heightTitle;
    MaterialSpinner spinner2, spinner4;
    EditText inputFeet, inputInch, inputInchCm, inputWaistCm;
    TextInputLayout inputLayoutFeet, inputLayoutInch, inputLayoutInchCm, inputLayoutWaistCm;
    Button btnStart;
    ImageButton cancelButton;

    String oper = "";
    float Kg;
    float Inch;
    float Feet;
    float Gm;
    float InchCm;
    float Cm;
    double Meter;
    double MeterFromFeet;
    double MeterFromInch;
    double HeightInInch;

    double CalculatedWaist;
    double Waist;
    double StandardWaist;
    double DesiredWaist;
    double WaistDifference;

    String Gender;

    private static final String[] HEIGHT = {
            "ইঞ্চি", "ফিট+ইঞ্চি", "ফিট"
    };
    private static final String[] WAIST = {
            "ইঞ্চি", "সেমিঃ"
    };

    public static WaistHome newInstance() {
        WaistHome fragment = new WaistHome();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public WaistHome() {
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
        return inflater.inflate(R.layout.waist_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.waist_home_toolbar));

        FontsOverride.setDefaultFont(getActivity(), "MONOSPACE", "android.ttf");

        // Initializing Google AdMob
       /* mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                *//*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*//*
                .build();
        mAdMobAdView.loadAd(adRequest);*/

        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        WaistResult = (TextView) view.findViewById(R.id.waist_result);
        WaistResultStatus = (TextView) view.findViewById(R.id.waist_meter_status);
        Age = (TextView) view.findViewById(R.id.waist_year);
        UserWaist = (TextView) view.findViewById(R.id.waist);
        Height = (TextView) view.findViewById(R.id.waist_height);
        CurrentDate = (TextView) view.findViewById(R.id.waist_current_date);
        CurrentWaist = (TextView) view.findViewById(R.id.waist_card);
        DesireWaist = (TextView) view.findViewById(R.id.desired_waist_card);
        UserWaistDifferenceText = (TextView) view.findViewById(R.id.waist_difference);
        WaistMeterImage = (ImageView) view.findViewById(R.id.waist_meter_image);

        // setting view content
        setView();

        // initializing floating action button
        fab = (FloatingActionButton) view.findViewById(R.id.waist_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(), "BMI Added", Toast.LENGTH_SHORT).show();
                // custom dialog
                getWaist();
                // hide fab
                fab.hide();
            }
        });

    }

    public void getWaist() {
        // custom dialog
        dialog = new Dialog(getActivity());  // always give context of activity.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.setContentView(R.layout.add_waist_data);

        dialog.show();

        waistTitle = (TextView) dialog.findViewById(R.id.waist_dialog_waist_title);
        heightTitle = (TextView) dialog.findViewById(R.id.waist_dialog_height_title);

        inputLayoutFeet = (TextInputLayout) dialog.findViewById(R.id.feet_text_input_layout);
        inputLayoutFeet.setTypeface(font);
        inputLayoutFeet.getEditText().setTypeface(font);
        inputLayoutInch = (TextInputLayout) dialog.findViewById(R.id.inch_text_input_layout);
        inputLayoutInch.setTypeface(font);
        inputLayoutInch.getEditText().setTypeface(font);
        inputLayoutInchCm = (TextInputLayout) dialog.findViewById(R.id.inch_cm_text_input_layout);
        inputLayoutInchCm.setTypeface(font);
        inputLayoutInchCm.getEditText().setTypeface(font);
        inputLayoutWaistCm = (TextInputLayout) dialog.findViewById(R.id.waist_cm_text_input_layout);
        inputLayoutWaistCm.setTypeface(font);
        inputLayoutWaistCm.getEditText().setTypeface(font);

        inputFeet = (EditText) dialog.findViewById(R.id.etFeet);
        inputInch = (EditText) dialog.findViewById(R.id.etInch);

        inputInchCm = (EditText) dialog.findViewById(R.id.etInchCm);
        inputWaistCm = (EditText) dialog.findViewById(R.id.etWaistCm);

        btnStart = (Button) dialog.findViewById(R.id.btnWaistCalculate);
        btnStart.setText(getString(R.string.bmi_dialog_button));
        btnStart.setTypeface(font);

        cancelButton = (ImageButton) dialog.findViewById(R.id.cancel_btn);

        // By default Gender
        Gender = (BMIPrefManager.getGender(getActivity()));

        // Height Spinner
        spinner2 = (MaterialSpinner) dialog.findViewById(R.id.height_unit_spinner);
        spinner2.setItems(HEIGHT);
        spinner2.setTypeface(font);
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                if (item.equals("ফিট")) {
                    heightTitle.setVisibility(View.VISIBLE);
                    inputLayoutFeet.setVisibility(View.VISIBLE);
                    inputLayoutInch.setVisibility(View.GONE);
                } else if (item.equals("ফিট+ইঞ্চি")) {
                    heightTitle.setVisibility(View.GONE);
                    inputLayoutFeet.setVisibility(View.VISIBLE);
                    inputLayoutInch.setVisibility(View.VISIBLE);
                } else {
                    heightTitle.setVisibility(View.VISIBLE);
                    inputLayoutFeet.setVisibility(View.GONE);
                    inputLayoutInch.setVisibility(View.VISIBLE);
                }
            }
        });
        spinner2.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                heightTitle.setVisibility(View.VISIBLE);
                inputLayoutFeet.setVisibility(View.GONE);
                inputLayoutInch.setVisibility(View.VISIBLE);
            }
        });

        // Waist spinner
        spinner4 = (MaterialSpinner) dialog.findViewById(R.id.waist_unit_spinner);
        spinner4.setItems(WAIST);
        spinner4.setTypeface(font);
        spinner4.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                if (item.equals("কোমর")) {
                    inputLayoutInchCm.setVisibility(View.VISIBLE);
                    inputLayoutWaistCm.setVisibility(View.GONE);
                } else if (item.equals("ইঞ্চি")) {
                    inputLayoutInchCm.setVisibility(View.VISIBLE);
                    inputLayoutWaistCm.setVisibility(View.GONE);
                } else if (item.equals("সেমিঃ")) {
                    inputLayoutInchCm.setVisibility(View.GONE);
                    inputLayoutWaistCm.setVisibility(View.VISIBLE);
                } else {
                    inputLayoutInchCm.setVisibility(View.VISIBLE);
                    inputLayoutWaistCm.setVisibility(View.GONE);
                }
            }
        });
        spinner4.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                inputLayoutInchCm.setVisibility(View.VISIBLE);
                inputLayoutWaistCm.setVisibility(View.GONE);
            }
        });

        // Check Validity by text watcher
        if (inputLayoutFeet.getVisibility() == View.VISIBLE) {
            // Its visible
            inputFeet.addTextChangedListener(new MyTextWatcher(inputFeet));
        }
        if (inputLayoutInch.getVisibility() == View.VISIBLE) {
            inputInch.addTextChangedListener(new MyTextWatcher(inputInch));
        }
        if (inputLayoutInchCm.getVisibility() == View.VISIBLE) {
            inputInchCm.addTextChangedListener(new MyTextWatcher(inputInchCm));
        }
        if (inputLayoutWaistCm.getVisibility() == View.VISIBLE) {
            inputWaistCm.addTextChangedListener(new MyTextWatcher(inputWaistCm));
        }

        // Button Action
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dismiss dialog
                dialog.dismiss();

                // showing fab
                fab.show();

            }
        });

    }

    private void submitForm() {
        if (inputLayoutFeet.getVisibility() == View.VISIBLE) {
            // Its visible
            if (!validateFeet()) {
                return;
            }
        }
        if (inputLayoutInch.getVisibility() == View.VISIBLE) {
            if (!validateInch()) {
                return;
            }
        }
        if (inputLayoutInchCm.getVisibility() == View.VISIBLE) {
            if (!validateInchCm()) {
                return;
            }
        }
        if (inputLayoutWaistCm.getVisibility() == View.VISIBLE) {
            if (!validateWaistCm()) {
                return;
            }
        }

        // Total calculation method
        SaveData();
        //Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();   // Result or what I want to do onclick function
    }

    private void SaveData() {
// method to save data
        Kg = 0;
        Meter = 0;
        Inch = 0;
        Feet = 0;
        Gm = 0;
        InchCm = 0;
        Cm = 0;
        oper = "/";
        oper = "*";
        oper = "+";
        oper = "-";

        if (inputLayoutFeet.getVisibility() == View.VISIBLE && inputLayoutInch.getVisibility() == View.GONE) {
            // Its visible
            Feet = Float.parseFloat(inputFeet.getText().toString());
            Meter = Feet / 3.28084;
            HeightInInch = Meter * 39.3701;
            BMIPrefManager.setHeight(getActivity(), String.valueOf(HeightInInch));
        }
        if (inputLayoutInch.getVisibility() == View.VISIBLE && inputLayoutFeet.getVisibility() == View.GONE) {
            Inch = Float.parseFloat(inputInch.getText().toString());
            Meter = Inch / 39.3701;
            HeightInInch = Meter * 39.3701;
            BMIPrefManager.setHeight(getActivity(), String.valueOf(HeightInInch));
        }
        if (inputLayoutFeet.getVisibility() == View.VISIBLE && inputLayoutInch.getVisibility() == View.VISIBLE) {
            Feet = Float.parseFloat(inputFeet.getText().toString());
            MeterFromFeet = Feet / 3.28084;
            Inch = Float.parseFloat(inputInch.getText().toString());
            MeterFromInch = Inch / 39.3701;
            Meter = MeterFromFeet + MeterFromInch;
            HeightInInch = Meter * 39.3701;
            BMIPrefManager.setHeight(getActivity(), String.valueOf(HeightInInch));
        }

        if (inputLayoutInchCm.getVisibility() == View.VISIBLE) {
            InchCm = Float.parseFloat(inputInchCm.getText().toString());
            Waist = InchCm;
            BMIPrefManager.setCurrentWaist(getActivity(), String.valueOf(Waist));
        }
        if (inputLayoutWaistCm.getVisibility() == View.VISIBLE) {
            Cm = Float.parseFloat(inputWaistCm.getText().toString());
            Waist = Cm * 0.393701;     // waist in inch from cm
            BMIPrefManager.setCurrentWaist(getActivity(), String.valueOf(Waist));
        }

        // Current Date
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        BMIPrefManager.setWaistDate(getActivity(), date);

        // Waist Calculation
        CalculatedWaist = (Waist) / (HeightInInch);
        BMIPrefManager.setWaist(getActivity(), String.valueOf(CalculatedWaist));

        // Desire Waist
        StandardWaist = 0.50;
        DesiredWaist = (StandardWaist) * (HeightInInch);
        BMIPrefManager.setDesiredWaist(getActivity(), String.valueOf(DesiredWaist));

        // Waist Difference
        WaistDifference = DesiredWaist - Waist;
        BMIPrefManager.setWaistDifference(getActivity(), String.valueOf(WaistDifference));

        // Dialog finish
        dialog.dismiss();

        // showing fab
        fab.show();

        // view re-set
        setView();

    }

    private boolean validateFeet() {
        if (inputFeet.getText().toString().trim().isEmpty()) {
            inputLayoutFeet.setError(getString(R.string.hint_feet));
            requestFocus(inputFeet);
            return false;
        } else {
            inputLayoutFeet.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateInch() {
        if (inputInch.getText().toString().trim().isEmpty()) {
            inputLayoutInch.setError(getString(R.string.hint_inch));
            requestFocus(inputInch);
            return false;
        } else {
            inputLayoutInch.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateInchCm() {
        if (inputInchCm.getText().toString().trim().isEmpty()) {
            inputLayoutInchCm.setError(getString(R.string.hint_inch));
            requestFocus(inputInchCm);
            return false;
        } else {
            inputLayoutInchCm.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateWaistCm() {
        if (inputWaistCm.getText().toString().trim().isEmpty()) {
            inputLayoutWaistCm.setError(getString(R.string.hint_cm));
            requestFocus(inputWaistCm);
            return false;
        } else {
            inputLayoutWaistCm.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.etFeet:
                    validateFeet();
                    break;
                case R.id.etInch:
                    validateInch();
                    break;
                case R.id.etInchCm:
                    validateInchCm();
                    break;
                case R.id.etWaistCm:
                    validateInchCm();
                    break;
            }
        }
    }

    public void setView() {
        Year = Double.parseDouble(BMIPrefManager.getAge(getActivity()));
        UserHeight = Double.parseDouble(BMIPrefManager.getHeight(getActivity()));


        Age.setText(new DecimalFormat("##.##").format(Year) + " " + getString(R.string.age));
        Age.setTypeface(font);

        CurrentDate.setText(BMIPrefManager.getWaistDate(getActivity()));
        CurrentDate.setTypeface(font);

        WaistCalculation = Double.parseDouble(BMIPrefManager.getWaist(getActivity()));
        WaistResult.setText(new DecimalFormat("##.##").format(WaistCalculation));
        WaistResult.setTypeface(font);

        CurrentWaistCm = Double.parseDouble(BMIPrefManager.getCurrentWaist(getActivity()));
        UserWaist.setText(new DecimalFormat("##.##").format(CurrentWaistCm) + " " + getString(R.string.waist_unit));
        UserWaist.setTypeface(font);
        CurrentWaist.setText(new DecimalFormat("##.##").format(CurrentWaistCm));
        CurrentWaist.setTypeface(font);

        UserDesiredWaist = Double.parseDouble(BMIPrefManager.getDesiredWaist(getActivity()));
        DesireWaist.setText(new DecimalFormat("##.##").format(UserDesiredWaist));
        DesireWaist.setTypeface(font);

        Height.setText(new DecimalFormat("##.##").format(UserHeight) + " " + getString(R.string.waist_unit));
        Height.setTypeface(font);

        UserWaistDifference = Double.parseDouble(BMIPrefManager.getWaistDifference(getActivity()));
        UserWaistDifferenceText.setText(new DecimalFormat("##.##").format(UserWaistDifference).replaceAll("-", ""));
        UserWaistDifferenceText.setTypeface(font);

        if (WaistCalculation < 0.35) {
            WaistMeterImage.setImageResource(R.drawable.abnormalhealthyslim);
            WaistResultStatus.setText(getString(R.string.waist_meter_abnormal_status));
            WaistResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.soft_red));
            WaistResultStatus.setTypeface(font);
        } else if ((WaistCalculation >= 0.35) && (WaistCalculation < 0.43)) {
            WaistMeterImage.setImageResource(R.drawable.healthyslim);
            WaistResultStatus.setText(getString(R.string.waist_meter_underweight_status));
            WaistResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.dark_sky_blue));
            WaistResultStatus.setTypeface(font);
        } else if ((WaistCalculation >= 0.43) && (WaistCalculation < 0.51)) {
            WaistMeterImage.setImageResource(R.drawable.healthy);
            WaistResultStatus.setText(getString(R.string.waist_meter_normal_status));
            WaistResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.YoColor));
            WaistResultStatus.setTypeface(font);
        } else if ((WaistCalculation >= 0.51) && (WaistCalculation < 0.56)) {
            WaistMeterImage.setImageResource(R.drawable.waistoverweight);
            WaistResultStatus.setText(getString(R.string.waist_meter_overweight_status));
            WaistResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.yellow));
            WaistResultStatus.setTypeface(font);
        } else if ((WaistCalculation >= 0.56) && (WaistCalculation < 0.61)) {
            WaistMeterImage.setImageResource(R.drawable.veryoverweight);
            WaistResultStatus.setText(getString(R.string.waist_meter_obesity_status));
            WaistResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.soft_red));
            WaistResultStatus.setTypeface(font);
        } else if (WaistCalculation > 0.61) {
            WaistMeterImage.setImageResource(R.drawable.paraveryoverweight);
            WaistResultStatus.setText(getString(R.string.waist_meter_abnormal_status));
            WaistResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.soft_red));
            WaistResultStatus.setTypeface(font);
        }
    }
}
