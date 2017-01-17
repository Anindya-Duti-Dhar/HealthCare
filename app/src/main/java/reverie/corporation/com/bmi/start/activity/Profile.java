package reverie.corporation.com.bmi.start.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import reverie.corporation.com.bmi.MainActivity;
import reverie.corporation.com.bmi.R;
import reverie.corporation.com.bmi.utils.BMIPrefManager;
import reverie.corporation.com.bmi.utils.CustomRangeInputFilter;

import static java.lang.Double.valueOf;

/**
 * Created by Administrator on 12/28/2016.
 */

public class Profile extends AppCompatActivity {

    TextView weightTitle, heightTitle, mAgeTitle, mWaistTitle, mProfileTitle;
    MaterialSpinner spinner, spinner2, spinner3, spinner4;
    EditText inputAge, inputFeet, inputInch, inputKg, inputGm, inputInchCm, inputWaistCm;
    TextInputLayout inputLayoutAge, inputLayoutFeet, inputLayoutInch, inputLayoutKg, inputLayoutGm, inputLayoutInchCm, inputLayoutWaistCm;
    Button btnStart;

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

    double CalculatedWaist;
    double Waist;
    double StandardWaist;
    double DesiredWaist;
    double WaistDifference;

    Handler handler;

    String Age;
    String Gender;

    Typeface font;

    private static final String[] GENDER = {
            "নারী", "পুরুষ"
    };

    private static final String[] WEIGHT = {
            "কেজি", "কেঃ+গ্রাঃ", "গ্রাম"
    };

    private static final String[] HEIGHT = {
            "ইঞ্চি", "ফিট+ইঞ্চি", "ফিট"
    };
    private static final String[] WAIST = {
            "ইঞ্চি", "সেমিঃ"
    };

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.profile);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        tintManager.setTintColor(Color.parseColor("#1a746b"));

        font = Typeface.createFromAsset(Profile.this.getAssets(), "android.ttf");

        weightTitle = (TextView) findViewById(R.id.weight_title);
        weightTitle.setText(getString(R.string.bmi_dialog_weight));
        weightTitle.setTypeface(font);
        heightTitle = (TextView) findViewById(R.id.height_title);
        heightTitle.setText(getString(R.string.bmi_dialog_height));
        heightTitle.setTypeface(font);
        mAgeTitle = (TextView) findViewById(R.id.age_title);
        mAgeTitle.setText(getString(R.string.profile_age_title));
        mAgeTitle.setTypeface(font);
        mWaistTitle = (TextView) findViewById(R.id.waist_title);
        mWaistTitle.setText(getString(R.string.profile_waist_title));
        mWaistTitle.setTypeface(font);
        mProfileTitle = (TextView) findViewById(R.id.profile_title);
        mProfileTitle.setText(getString(R.string.profile_title));
        mProfileTitle.setTypeface(font);

        inputLayoutAge = (TextInputLayout) findViewById(R.id.age_text_input_layout);
        inputLayoutAge.setTypeface(font);
        inputLayoutAge.getEditText().setTypeface(font);
        inputLayoutFeet = (TextInputLayout) findViewById(R.id.feet_text_input_layout);
        inputLayoutFeet.setTypeface(font);
        inputLayoutFeet.getEditText().setTypeface(font);
        inputLayoutInch = (TextInputLayout) findViewById(R.id.inch_text_input_layout);
        inputLayoutInch.setTypeface(font);
        inputLayoutInch.getEditText().setTypeface(font);
        inputLayoutKg = (TextInputLayout) findViewById(R.id.kg_text_input_layout);
        inputLayoutKg.setTypeface(font);
        inputLayoutKg.getEditText().setTypeface(font);
        inputLayoutGm = (TextInputLayout) findViewById(R.id.gm_text_input_layout);
        inputLayoutGm.setTypeface(font);
        inputLayoutGm.getEditText().setTypeface(font);
        inputLayoutInchCm = (TextInputLayout) findViewById(R.id.inch_cm_text_input_layout);
        inputLayoutInchCm.setTypeface(font);
        inputLayoutInchCm.getEditText().setTypeface(font);
        inputLayoutWaistCm = (TextInputLayout) findViewById(R.id.waist_cm_text_input_layout);
        inputLayoutWaistCm.setTypeface(font);
        inputLayoutWaistCm.getEditText().setTypeface(font);

        inputAge = (EditText) findViewById(R.id.etAge);
        //Define Min, Max range value using custom input filter
        //inputAge.setFilters(new InputFilter[]{new CustomRangeInputFilter(1.0f, 120.0f)});
        inputFeet = (EditText) findViewById(R.id.etFeet);
        inputInch = (EditText) findViewById(R.id.etInch);
        inputKg = (EditText) findViewById(R.id.etkg);
        inputGm = (EditText) findViewById(R.id.etGm);
        inputInchCm = (EditText) findViewById(R.id.etInchCm);
        inputWaistCm = (EditText) findViewById(R.id.etWaistCm);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setText(getString(R.string.profile_button));
        btnStart.setTypeface(font);

        // By default Gender
        Gender = "নারী";

        // Gender Spinner
        spinner = (MaterialSpinner) findViewById(R.id.gender_spinner);
        spinner.setItems(GENDER);
        spinner.setTypeface(font);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (item.equals("নারী")){
                    //Snackbar.make(view, "Clicked Female: " + item, Snackbar.LENGTH_LONG).show();
                    Gender = "Female";
                    BMIPrefManager.setGender(Profile.this, Gender);
                }
                else if (item.equals("পুরুষ")){
                    Gender = "Male";
                    BMIPrefManager.setGender(Profile.this, Gender);
                }
                else {
                    Gender = "Male";
                    BMIPrefManager.setGender(Profile.this, Gender);
                }
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(MaterialSpinner spinner) {
                Gender = "Female";
                BMIPrefManager.setGender(Profile.this, Gender);
            }
        });

        // Height Spinner
        spinner2 = (MaterialSpinner) findViewById(R.id.height_unit_spinner);
        spinner2.setItems(HEIGHT);
        spinner2.setTypeface(font);
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                if (item.equals("ফিট")){
                    heightTitle.setVisibility(View.VISIBLE);
                    inputLayoutFeet.setVisibility(View.VISIBLE);
                    inputLayoutInch.setVisibility(View.GONE);
                }

                else if (item.equals("ফিট+ইঞ্চি")){
                    heightTitle.setVisibility(View.GONE);
                    inputLayoutFeet.setVisibility(View.VISIBLE);
                    inputLayoutInch.setVisibility(View.VISIBLE);
                }

                else {
                    heightTitle.setVisibility(View.VISIBLE);
                    inputLayoutFeet.setVisibility(View.GONE);
                    inputLayoutInch.setVisibility(View.VISIBLE);
                }
            }
        });
        spinner2.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(MaterialSpinner spinner) {
                heightTitle.setVisibility(View.VISIBLE);
                inputLayoutFeet.setVisibility(View.GONE);
                inputLayoutInch.setVisibility(View.VISIBLE);
            }
        });

        // Weight Spinner
        spinner3 = (MaterialSpinner) findViewById(R.id.weight_unit_spinner);
        spinner3.setItems(WEIGHT);
        spinner3.setTypeface(font);
        spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               if (item.equals("কেজি")){
                    weightTitle.setVisibility(View.VISIBLE);
                    inputLayoutKg.setVisibility(View.VISIBLE);
                    inputLayoutGm.setVisibility(View.GONE);
                }

               else if (item.equals("কেঃ+গ্রাঃ")){
                   weightTitle.setVisibility(View.GONE);
                   inputLayoutKg.setVisibility(View.VISIBLE);
                   inputLayoutGm.setVisibility(View.VISIBLE);
               }

                else {
                   weightTitle.setVisibility(View.VISIBLE);
                   inputLayoutKg.setVisibility(View.GONE);
                   inputLayoutGm.setVisibility(View.VISIBLE);
                }
            }
        });
        spinner3.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(MaterialSpinner spinner) {
                weightTitle.setVisibility(View.VISIBLE);
                inputLayoutKg.setVisibility(View.VISIBLE);
                inputLayoutGm.setVisibility(View.GONE);
            }
        });

        // Waist spinner
        spinner4 = (MaterialSpinner) findViewById(R.id.waist_unit_spinner);
        spinner4.setItems(WAIST);
        spinner4.setTypeface(font);
        spinner4.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                if (item.equals("কোমর")){
                    inputLayoutInchCm.setVisibility(View.VISIBLE);
                    inputLayoutWaistCm.setVisibility(View.GONE);
                }

                else if (item.equals("ইঞ্চি")){
                    inputLayoutInchCm.setVisibility(View.VISIBLE);
                    inputLayoutWaistCm.setVisibility(View.GONE);
                }

                else if (item.equals("সেমিঃ")){
                    inputLayoutInchCm.setVisibility(View.GONE);
                    inputLayoutWaistCm.setVisibility(View.VISIBLE);
                }
                else {
                    inputLayoutInchCm.setVisibility(View.VISIBLE);
                    inputLayoutWaistCm.setVisibility(View.GONE);
                }
            }
        });
        spinner4.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(MaterialSpinner spinner) {
                inputLayoutInchCm.setVisibility(View.VISIBLE);
                inputLayoutWaistCm.setVisibility(View.GONE);
            }
        });

        // Check Validity by text watcher
        inputAge.addTextChangedListener(new MyTextWatcher(inputAge));
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

    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateAge()) {
            return;
        }
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
            BMIPrefManager.setHeight(Profile.this, String.valueOf(HeightInInch));
        }
        if (inputLayoutInch.getVisibility() == View.VISIBLE && inputLayoutFeet.getVisibility() == View.GONE) {
            Inch = Float.parseFloat(inputInch.getText().toString());
            Meter = Inch / 39.3701;
            HeightInInch = Meter * 39.3701;
            BMIPrefManager.setHeight(Profile.this, String.valueOf(HeightInInch));
        }
        if (inputLayoutFeet.getVisibility() == View.VISIBLE && inputLayoutInch.getVisibility() == View.VISIBLE) {
            Feet = Float.parseFloat(inputFeet.getText().toString());
            MeterFromFeet = Feet / 3.28084;
            Inch = Float.parseFloat(inputInch.getText().toString());
            MeterFromInch = Inch / 39.3701;
            Meter = MeterFromFeet + MeterFromInch;
            HeightInInch = Meter * 39.3701;
            BMIPrefManager.setHeight(Profile.this, String.valueOf(HeightInInch));
        }
        if (inputLayoutKg.getVisibility() == View.VISIBLE && inputLayoutGm.getVisibility() == View.GONE) {
            Kg = Float.parseFloat(inputKg.getText().toString());
            Kegi = valueOf(Kg);
            BMIPrefManager.setCurrentWeight(Profile.this, String.valueOf(Kegi));
        }
        if (inputLayoutGm.getVisibility() == View.VISIBLE && inputLayoutKg.getVisibility() == View.GONE) {
            Gm = Float.parseFloat(inputGm.getText().toString());
            Kegi = Gm / 1000;
            BMIPrefManager.setCurrentWeight(Profile.this, String.valueOf(Kegi));
        }

        if (inputLayoutKg.getVisibility() == View.VISIBLE && inputLayoutGm.getVisibility() == View.VISIBLE) {
            Kg = Float.parseFloat(inputKg.getText().toString());
            Gm = Float.parseFloat(inputGm.getText().toString());
            KgFromGram = Gm / 1000;
            Kegi = Kg + KgFromGram;
            BMIPrefManager.setCurrentWeight(Profile.this, String.valueOf(Kegi));
        }

        if (inputLayoutInchCm.getVisibility() == View.VISIBLE) {
            InchCm = Float.parseFloat(inputInchCm.getText().toString());
            Waist = InchCm;
            BMIPrefManager.setCurrentWaist(Profile.this, String.valueOf(Waist));
        }
        if (inputLayoutWaistCm.getVisibility() == View.VISIBLE) {
            Cm = Float.parseFloat(inputWaistCm.getText().toString());;
            Waist = Cm * 0.393701;     // waist in inch from cm
            BMIPrefManager.setCurrentWaist(Profile.this, String.valueOf(Waist));
        }

        // User Current Age
        int Boyos = Integer.parseInt(inputAge.getText().toString());
        Age = String.valueOf(Boyos);
        BMIPrefManager.setAge(Profile.this, Age);

        // Current Date
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        BMIPrefManager.setDate(Profile.this, date);
        BMIPrefManager.setWaistDate(Profile.this, date);

        // Calculated BMI
        BMI = (Kegi) / ((Meter) * (Meter));
        BMIPrefManager.setBmi(Profile.this, String.valueOf(BMI));

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
            }else if ((Boyos >= 70) && (Boyos < 80)) {
                StandardBMI = 37.8;
            }else if (Boyos > 80){
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
            }else if ((Boyos >= 70) && (Boyos < 80)) {
                StandardBMI = 42.1;
            }else if (Boyos > 80){
                StandardBMI = 35.2;
            }
        }
        DesiredKg = (StandardBMI) / ((Meter) * (Meter));
        BMIPrefManager.setDesiredWeight(Profile.this, String.valueOf(DesiredKg));

        // Weight Differences
        Difference = (Kegi) - (DesiredKg);
        BMIPrefManager.setDifference(Profile.this, String.valueOf(Difference));

        // Waist Calculation
        CalculatedWaist = (Waist) / (HeightInInch);
        BMIPrefManager.setWaist(Profile.this, String.valueOf(CalculatedWaist));

        // Desire Waist
        StandardWaist = 0.50;
        DesiredWaist = (StandardWaist) * (HeightInInch);
        BMIPrefManager.setDesiredWaist(Profile.this, String.valueOf(DesiredWaist));

        // Waist Difference
        WaistDifference = DesiredWaist - Waist;
        BMIPrefManager.setWaistDifference(Profile.this, String.valueOf(WaistDifference));

        // Go to Main Page
        handler = new Handler();
        final SweetAlertDialog savedDialog = new SweetAlertDialog(Profile.this, SweetAlertDialog.SUCCESS_TYPE);
        savedDialog.setTitleText(getString(R.string.first_success_title))
                .setContentText(getString(R.string.first_success_message))
                .setConfirmText(getString(R.string.second_success_button))
                .show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 1000ms
                savedDialog.dismissWithAnimation();
                startActivity(new Intent(Profile.this, MainActivity.class));
                finish();
            }
        }, 2500);
    }

    private boolean validateAge() {
        if (inputAge.getText().toString().trim().isEmpty()) {
            inputLayoutAge.setError(getString(R.string.hint_age));
            requestFocus(inputAge);
            return false;
        } else {
            inputLayoutAge.setErrorEnabled(false);
        }

        return true;
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
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
                case R.id.etAge:
                    validateAge();
                    break;
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
                case R.id.etInchCm:
                    validateInchCm();
                    break;
                case R.id.etWaistCm:
                    validateInchCm();
                    break;
            }
        }
    }

}
