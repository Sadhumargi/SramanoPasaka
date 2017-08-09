package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.zip.Inflater;

public class CreateAccountFragment extends Fragment implements StateChangeListner  {

    EditText fName;
    EditText mName;
    EditText lName;
    EditText bDate;
    EditText pNumber;
    EditText emailId;
    EditText sCountry;
    EditText sState;
    EditText sCity;
    EditText pinCode;
    EditText password;
    EditText reTypepassword;
    CheckBox chkbox;
    Button   btnCreateProfile;
    TextView countryCode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_create_account_fragment,container,false);


        fName= (EditText) view.findViewById(R.id.first_name);
        mName= (EditText) view.findViewById(R.id.middle_name);
        lName= (EditText) view.findViewById(R.id.last_name);
        bDate= (EditText) view.findViewById(R.id.birth_date);
        pNumber= (EditText) view.findViewById(R.id.mobile_number);
        emailId= (EditText) view.findViewById(R.id.email);
        sCountry= (EditText) view.findViewById(R.id.country);
        sState= (EditText) view.findViewById(R.id.state);
        sCity= (EditText) view.findViewById(R.id.city);
        pinCode= (EditText) view.findViewById(R.id.pincode);
        password= (EditText) view.findViewById(R.id.password);
        reTypepassword= (EditText) view.findViewById(R.id.retype_password);
        chkbox= (CheckBox) view.findViewById(R.id.chkbox);
        btnCreateProfile= (Button) view.findViewById(R.id.create_profile);
        countryCode = (TextView) view.findViewById(R.id.countryCodeTxt);

        final Calendar myCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }


                    public void updateLabel() {
                        String myFormat = "dd/mm/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        bDate.setText(sdf.format(myCalendar.getTime()));
                    }

                };

                bDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new DatePickerDialog(getActivity(), date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                    }
                });

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fName.getText().toString().length() == 0) {
                    fName.setError("First name is required");
                    fName.requestFocus();
                }

                if (fName.getText().toString().length() <50 ) {}

                if (mName.getText().toString().length() == 0) {
                    mName.setError("Middle name is required");
                    mName.requestFocus();
                }

                if (mName.getText().toString().length() <50 ) {}

                if (lName.getText().toString().length() == 0) {
                    lName.setError("Last name is required");
                    lName.requestFocus();
                }

                if (lName.getText().toString().length() <50 ) {}

                if (bDate.getText().toString().length() == 0) {
                    bDate.setError("Birthdate is required");
                    bDate.requestFocus();
                }

                if (pNumber.getText().toString().length() == 0) {
                    pNumber.setError("Mobile number is required");
                    pNumber.requestFocus();
                }

                if (fName.getText().toString().length() <10 ) {}

                if (emailId.getText().toString().length() == 0) {
                    emailId.setError("Email id is required");
                    emailId.requestFocus();
                }

                if (sCountry.getText().toString().length() == 0) {
                    sCountry.setError("Coutry name is required");
                    sCountry.requestFocus();
                }
                if (sState.getText().toString().length() == 0) {
                    sState.setError("State name is required");
                    sState.requestFocus();
                }

                if (sCity.getText().toString().length() == 0) {
                    sCity.setError("City name is required");
                    sCity.requestFocus();
                }

                if (pinCode.getText().toString().length() == 0) {
                    pinCode.setError("Pincode is required");
                    pinCode.requestFocus();
                }
                if (password.getText().toString().length() == 0) {
                    password.setError("Password is required");
                    password.requestFocus();
                }
                if (reTypepassword.getText().toString().length() == 0) {
                    reTypepassword.setError("Please confirm password");
                    reTypepassword.requestFocus();
                }
                if (!password.getText().toString().equals(reTypepassword.getText().toString())) {
                    reTypepassword.setError("Password Not matched");
                    reTypepassword.requestFocus();
                }
                if ((password.getText().toString().length()) < 8) {
                    password.setError("Password should be atleast of 8 charactors");
                    password.requestFocus();
                } else {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getActivity(), Profile.class);
                    startActivity(i);
                }

               /* Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), Profile.class);
                startActivity(i);*/
            }
        });

        sCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });

        countryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
        sState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatePickerDialog statePickerDialog = new StatePickerDialog(getActivity(),CreateAccountFragment.this);
                statePickerDialog.show();
            }
        });

        return view;
    }


    private void showPicker(){
        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                sCountry.setText(name);
                countryCode.setText(dialCode);
                picker.dismiss();
            }
        });
        picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    @Override
    public void onStateSelected(String state) {
        sState.setText(state);
    }



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_fragment);
    }*/
}
