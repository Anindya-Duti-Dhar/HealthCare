package anindya.duti.com.bmi.bmiCalculation.fragment;

import android.app.Dialog;
import android.content.res.Configuration;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import anindya.duti.com.bmi.FontsOverride;
import anindya.duti.com.bmi.utils.BMIPrefManager;
import anindya.duti.com.bmi.R;

import static java.lang.Double.valueOf;

public class BmiHome extends Fragment {

    //Defining Variables
    Configuration config ;

    private AdView mAdMobAdView;
    TextView BMIResultStatus, BMIResult, Age, Weight, Height, CurrentDate, CurrentWeight, DesireWeight, WeightDifference;
    TextView CurrentWeightText, CurrentWeightUnitText, CurrentDateText, DesiredWeightText, DesiredWeightUnitText, DifferenceWeightText;
    TextView BMIMeterTitle, BMIDialogTitle, BMIDialogFooter, BMIDialog_height_title, BMIDialog_weight_title ;
    ImageView BMIMeterImage;
    Typeface font;

    double BMICalculation;
    double CurrentKg;
    double UserHeight;
    double UserDesiredKg;
    double UserWeightDifference;
    double Year;

    public static FloatingActionButton bmi_fab;

    private Dialog dialog;
    MaterialSpinner spinner2, spinner3;
    EditText inputFeet, inputInch, inputKg, inputGm;
    TextInputLayout inputLayoutFeet, inputLayoutInch, inputLayoutKg, inputLayoutGm;
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
    double Kegi;
    double KgFromGram;
    double MeterFromFeet;
    double MeterFromInch;
    double BMI;
    double StandardBMI;
    double DesiredKg;
    double Difference;
    double HeightInInch;

    String UserAge;
    String Gender;

    private static final String[] WEIGHT = {
            "কেজি", "কেঃ+গ্রাঃ", "গ্রাম"
    };

    private static final String[] HEIGHT = {
            "ইঞ্চি", "ফিট+ইঞ্চি", "ফিট"
    };

    public static BmiHome newInstance() {
        BmiHome fragment = new BmiHome();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public BmiHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bmi_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.bmi_home_toolbar));

        // Initializing Google AdMob
        mAdMobAdView = (AdView)view.findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                /*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)*/
                //.addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here
                .build();
        mAdMobAdView.loadAd(adRequest);

        font = Typeface.createFromAsset(getActivity().getAssets(), "android.ttf");

        FontsOverride.setDefaultFont(getActivity(), "MONOSPACE", "android.ttf");

        BMIResultStatus = (TextView) view.findViewById(R.id.bmi_meter_status);
        BMIResult = (TextView) view.findViewById(R.id.bmi_result);
        Age = (TextView) view.findViewById(R.id.age);
        Weight = (TextView) view.findViewById(R.id.weight);
        Height = (TextView) view.findViewById(R.id.height);
        CurrentDate = (TextView) view.findViewById(R.id.current_date);
        CurrentWeight = (TextView) view.findViewById(R.id.current_weight);
        DesireWeight = (TextView) view.findViewById(R.id.desired_weight);
        WeightDifference = (TextView) view.findViewById(R.id.weight_difference);
        BMIMeterImage = (ImageView) view.findViewById(R.id.bmi_meter_image);

        CurrentWeightText = (TextView) view.findViewById(R.id.weight_title_card);
        CurrentWeightText.setText(getString(R.string.current_weight_title));
        CurrentWeightText.setTypeface(font);

        CurrentWeightUnitText = (TextView) view.findViewById(R.id.weight_card_unit);
        CurrentWeightUnitText.setText(getString(R.string.weight_unit));
        CurrentWeightUnitText.setTypeface(font);

        CurrentDateText = (TextView) view.findViewById(R.id.current_date_title);
        CurrentDateText.setText(getString(R.string.current_date));
        CurrentDateText.setTypeface(font);

        DesiredWeightText = (TextView) view.findViewById(R.id.desired_weight_title_card);
        DesiredWeightText.setText(getString(R.string.desired_weight));
        DesiredWeightText.setTypeface(font);

        DesiredWeightUnitText = (TextView) view.findViewById(R.id.desired_weight_card_unit);
        DesiredWeightUnitText.setText(getString(R.string.weight_unit));
        DesiredWeightUnitText.setTypeface(font);

        DifferenceWeightText = (TextView) view.findViewById(R.id.weight_difference_text);
        DifferenceWeightText.setText(getString(R.string.weight_difference_title));
        DifferenceWeightText.setTypeface(font);

        BMIMeterTitle = (TextView) view.findViewById(R.id.bmi_meter_title);
        BMIMeterTitle.setText(getString(R.string.bmi_meter_title));
        BMIMeterTitle.setTypeface(font);

        // setting view content
        setView();

        // initializing floating action button
        bmi_fab = (FloatingActionButton) view.findViewById(R.id.bmi_fab);
        bmi_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                getBMI();
                // hide fab
                bmi_fab.hide();
            }
        });

    }

    public void getBMI() {
        // custom dialog
        dialog = new Dialog(getActivity());  // always give context of activity.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.add_bmi_data);

        dialog.show();

        BMIDialogTitle = (TextView) dialog.findViewById(R.id.bmi_dialog_title_text);
        BMIDialogTitle.setText(getString(R.string.bmi_dialog_title_text_hint));
        BMIDialogTitle.setTypeface(font);

        BMIDialogFooter = (TextView) dialog.findViewById(R.id.bmi_dialog_footer);
        BMIDialogFooter.setText(getString(R.string.bmi_dialog_footer_text_hint));
        BMIDialogFooter.setTypeface(font);

        BMIDialog_height_title = (TextView) dialog.findViewById(R.id.bmi_dialog_height_title);
        BMIDialog_height_title.setText(getString(R.string.bmi_dialog_height));
        BMIDialog_height_title.setTypeface(font);

        BMIDialog_weight_title = (TextView) dialog.findViewById(R.id.bmi_dialog_weight_title);
        BMIDialog_weight_title.setText(getString(R.string.bmi_dialog_weight));
        BMIDialog_weight_title.setTypeface(font);

        inputLayoutFeet = (TextInputLayout) dialog.findViewById(R.id.feet_text_input_layout);
        inputLayoutFeet.setTypeface(font);
        inputLayoutFeet.getEditText().setTypeface(font);
        inputLayoutInch = (TextInputLayout) dialog.findViewById(R.id.inch_text_input_layout);
        inputLayoutInch.setTypeface(font);
        inputLayoutInch.getEditText().setTypeface(font);
        inputLayoutKg = (TextInputLayout) dialog.findViewById(R.id.kg_text_input_layout);
        inputLayoutKg.setTypeface(font);
        inputLayoutKg.getEditText().setTypeface(font);
        inputLayoutGm = (TextInputLayout) dialog.findViewById(R.id.gm_text_input_layout);
        inputLayoutGm.setTypeface(font);
        inputLayoutGm.getEditText().setTypeface(font);

        inputFeet = (EditText) dialog.findViewById(R.id.etFeet);
        inputInch = (EditText) dialog.findViewById(R.id.etInch);
        inputKg = (EditText) dialog.findViewById(R.id.etkg);
        inputGm = (EditText) dialog.findViewById(R.id.etGm);

        btnStart = (Button) dialog.findViewById(R.id.btnBmiCalculate);
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
                    BMIDialog_height_title.setVisibility(View.VISIBLE);
                    inputLayoutFeet.setVisibility(View.VISIBLE);
                    inputLayoutInch.setVisibility(View.GONE);
                } else if (item.equals("ফিট+ইঞ্চি")) {
                    BMIDialog_height_title.setVisibility(View.GONE);
                    inputLayoutFeet.setVisibility(View.VISIBLE);
                    inputLayoutInch.setVisibility(View.VISIBLE);
                } else {
                    BMIDialog_height_title.setVisibility(View.VISIBLE);
                    inputLayoutFeet.setVisibility(View.GONE);
                    inputLayoutInch.setVisibility(View.VISIBLE);
                }
            }
        });
        spinner2.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                BMIDialog_height_title.setVisibility(View.VISIBLE);
                inputLayoutFeet.setVisibility(View.GONE);
                inputLayoutInch.setVisibility(View.VISIBLE);
            }
        });

        // Weight Spinner
        spinner3 = (MaterialSpinner) dialog.findViewById(R.id.weight_unit_spinner);
        spinner3.setItems(WEIGHT);
        spinner3.setTypeface(font);
        spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (item.equals("কেজি")) {
                    BMIDialog_weight_title.setVisibility(View.VISIBLE);
                    inputLayoutKg.setVisibility(View.VISIBLE);
                    inputLayoutGm.setVisibility(View.GONE);
                } else if (item.equals("কেঃ+গ্রাঃ")) {
                    BMIDialog_weight_title.setVisibility(View.GONE);
                    inputLayoutKg.setVisibility(View.VISIBLE);
                    inputLayoutGm.setVisibility(View.VISIBLE);
                } else {
                    BMIDialog_weight_title.setVisibility(View.VISIBLE);
                    inputLayoutKg.setVisibility(View.GONE);
                    inputLayoutGm.setVisibility(View.VISIBLE);
                }
            }
        });
        spinner3.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                BMIDialog_weight_title.setVisibility(View.VISIBLE);
                inputLayoutKg.setVisibility(View.VISIBLE);
                inputLayoutGm.setVisibility(View.GONE);
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
        if (inputLayoutKg.getVisibility() == View.VISIBLE) {
            inputKg.addTextChangedListener(new MyTextWatcher(inputKg));
        }
        if (inputLayoutGm.getVisibility() == View.VISIBLE) {
            inputGm.addTextChangedListener(new MyTextWatcher(inputGm));
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
                bmi_fab.show();
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
        if (inputLayoutKg.getVisibility() == View.VISIBLE) {
            if (!validateKg()) {
                return;
            }
        }
        if (inputLayoutGm.getVisibility() == View.VISIBLE) {
            if (!validateGm()) {
                return;
            }
        }

        // Total calculation method
        SaveData();
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
        if (inputLayoutKg.getVisibility() == View.VISIBLE && inputLayoutGm.getVisibility() == View.GONE) {
            Kg = Float.parseFloat(inputKg.getText().toString());
            Kegi = valueOf(Kg);
            BMIPrefManager.setCurrentWeight(getActivity(), String.valueOf(Kegi));
        }
        if (inputLayoutGm.getVisibility() == View.VISIBLE && inputLayoutKg.getVisibility() == View.GONE) {
            Gm = Float.parseFloat(inputGm.getText().toString());
            Kegi = Gm / 1000;
            BMIPrefManager.setCurrentWeight(getActivity(), String.valueOf(Kegi));
        }

        if (inputLayoutKg.getVisibility() == View.VISIBLE && inputLayoutGm.getVisibility() == View.VISIBLE) {
            Kg = Float.parseFloat(inputKg.getText().toString());
            Gm = Float.parseFloat(inputGm.getText().toString());
            KgFromGram = Gm / 1000;
            Kegi = Kg + KgFromGram;
            BMIPrefManager.setCurrentWeight(getActivity(), String.valueOf(Kegi));
        }

        // User Current Age
        int Boyos = Integer.parseInt(BMIPrefManager.getAge(getActivity()));

        // Current Date
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        BMIPrefManager.setDate(getActivity(), date);

        // Calculated BMI
        BMI = (Kegi) / ((Meter) * (Meter));
        BMIPrefManager.setBmi(getActivity(), String.valueOf(BMI));

        // Desired Weight
        if (Gender.equals("Male")) {
            if (Boyos < 20) {
                StandardBMI = 39.2;
            } else if ((Boyos >= 20) && (Boyos < 30)) {
                StandardBMI = 36.5;
            } else if ((Boyos >= 30) && (Boyos < 40)) {
                StandardBMI = 40.52;
            } else if ((Boyos >= 40) && (Boyos < 50)) {
                StandardBMI = 39.6;
            } else if ((Boyos >= 50) && (Boyos < 60)) {
                StandardBMI = 39.9;
            } else if ((Boyos >= 60) && (Boyos < 70)) {
                StandardBMI = 40.0;
            } else if ((Boyos >= 70) && (Boyos < 80)) {
                StandardBMI = 37.8;
            } else {
                StandardBMI = 34.5;
            }
        } else {
            if (Boyos < 20) {
                StandardBMI = 42.0;
            } else if ((Boyos >= 20) && (Boyos < 30)) {
                StandardBMI = 43.9;
            } else if ((Boyos >= 30) && (Boyos < 40)) {
                StandardBMI = 41.6;
            } else if ((Boyos >= 40) && (Boyos < 50)) {
                StandardBMI = 43.0;
            } else if ((Boyos >= 50) && (Boyos < 60)) {
                StandardBMI = 41.8;
            } else if ((Boyos >= 60) && (Boyos < 70)) {
                StandardBMI = 41.1;
            } else if ((Boyos >= 70) && (Boyos < 80)) {
                StandardBMI = 42.1;
            } else {
                StandardBMI = 35.2;
            }
        }
        DesiredKg = (StandardBMI) / ((Meter) * (Meter));
        BMIPrefManager.setDesiredWeight(getActivity(), String.valueOf(DesiredKg));

        // Weight Differences
        Difference = (Kegi) - (DesiredKg);
        BMIPrefManager.setDifference(getActivity(), String.valueOf(Difference));

        // Dialog finish
        dialog.dismiss();
        // showing fab
        bmi_fab.show();

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

    private boolean validateKg() {
        if (inputKg.getText().toString().trim().isEmpty()) {
            inputLayoutKg.setError(getString(R.string.hint_kg));
            requestFocus(inputKg);
            return false;
        } else {
            inputLayoutKg.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateGm() {
        if (inputGm.getText().toString().trim().isEmpty()) {
            inputLayoutGm.setError(getString(R.string.hint_gm));
            requestFocus(inputGm);
            return false;
        } else {
            inputLayoutGm.setErrorEnabled(false);
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
                case R.id.etkg:
                    validateKg();
                    break;
                case R.id.etGm:
                    validateGm();
                    break;
            }
        }
    }

    public void setView() {
        Year = Double.parseDouble(BMIPrefManager.getAge(getActivity()));
        UserHeight = Double.parseDouble(BMIPrefManager.getHeight(getActivity()));

        Age.setText(new DecimalFormat("##.##").format(Year) + " " + getString(R.string.age));
        Age.setTypeface(font);

        CurrentDate.setText(BMIPrefManager.getDate(getActivity()));
        CurrentDate.setTypeface(font);

        BMICalculation = Double.parseDouble(BMIPrefManager.getBmi(getActivity()));
        BMIResult.setText(new DecimalFormat("##.##").format(BMICalculation));
        BMIResult.setTypeface(font);

        CurrentKg = Double.parseDouble(BMIPrefManager.getCurrentWeight(getActivity()));
        Weight.setText(new DecimalFormat("##.##").format(CurrentKg) + " " + getString(R.string.weight_unit));
        Weight.setTypeface(font);
        CurrentWeight.setText(new DecimalFormat("##.##").format(CurrentKg));
        CurrentWeight.setTypeface(font);

        UserDesiredKg = Double.parseDouble(BMIPrefManager.getDifference(getActivity()));
        DesireWeight.setText(new DecimalFormat("##.##").format(UserDesiredKg));
        DesireWeight.setTypeface(font);

        Height.setText(new DecimalFormat("##.##").format(UserHeight) + " " + getString(R.string.height));
        Height.setTypeface(font);

        UserWeightDifference = Double.parseDouble(BMIPrefManager.getDesiredWeight(getActivity()));
        WeightDifference.setText(new DecimalFormat("##.##").format(UserWeightDifference).replaceAll("-", ""));
        WeightDifference.setTypeface(font);

        if (BMICalculation < 14.0) {
            BMIMeterImage.setImageResource(R.drawable.abnormal);
            BMIResultStatus.setText(getString(R.string.bmi_meter_abnormal_status));
            BMIResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.soft_red));
            BMIResultStatus.setTypeface(font);
        } else if ((BMICalculation >= 14.0) && (BMICalculation < 18.5)) {
            BMIMeterImage.setImageResource(R.drawable.underweight);
            BMIResultStatus.setText(getString(R.string.bmi_meter_underweight_status));
            BMIResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.dark_sky_blue));
            BMIResultStatus.setTypeface(font);
        } else if ((BMICalculation >= 18.5) && (BMICalculation < 25.0)) {
            BMIMeterImage.setImageResource(R.drawable.normal);
            BMIResultStatus.setText(getString(R.string.bmi_meter_normal_status));
            BMIResultStatus.setTypeface(font);
            BMIResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.YoColor));
        } else if ((BMICalculation >= 25.0) && (BMICalculation <= 30.0)) {
            BMIMeterImage.setImageResource(R.drawable.overweight);
            BMIResultStatus.setText(getString(R.string.bmi_meter_overweight_status));
            BMIResultStatus.setTypeface(font);
            BMIResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.yellow));
        } else if ((BMICalculation > 30.0) && (BMICalculation <= 40.0)) {
            BMIMeterImage.setImageResource(R.drawable.obesity);
            BMIResultStatus.setText(getString(R.string.bmi_meter_obesity_status));
            BMIResultStatus.setTypeface(font);
            BMIResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.soft_red));
        } else if (BMICalculation > 40.0) {
            BMIMeterImage.setImageResource(R.drawable.paranormal);
            BMIResultStatus.setText(getString(R.string.bmi_meter_abnormal_status));
            BMIResultStatus.setTypeface(font);
            BMIResultStatus.setTextColor(ContextCompat.getColor(getActivity(), R.color.soft_red));
        }

    }

}
