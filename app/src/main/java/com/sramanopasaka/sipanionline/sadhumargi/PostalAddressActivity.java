package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CityListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BasicDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;
import com.sramanopasaka.sipanionline.sadhumargi.model.BasicDetailsData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.CityPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostalAddressActivity extends BaseActivity implements GUICallback {

    @Bind(R.id.postaladdresstool)
    Toolbar postaladdresstool;
    @Bind(R.id.address1)
    EditText address1;
    @Bind(R.id.address2)
    EditText address2;
    @Bind(R.id.post)
    EditText post;
    @Bind(R.id.district)
    EditText district;
    @Bind(R.id.city)
    EditText city;
    @Bind(R.id.pin)
    EditText pin;
    @Bind(R.id.state)
    EditText state;
    @Bind(R.id.country)
    EditText country;
    @Bind(R.id.addresstype)
    Spinner addresstype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ButterKnife.bind(this);

        setSupportActionBar(postaladdresstool);

        postaladdresstool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>पत्र व्यवहार पता</font>"));

        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCountryPicker();
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityListResponse cityListResponse = OfflineData.getCityList();
                if (cityListResponse != null) {
                    final CityPickerDialog statePickerDialog = new CityPickerDialog(PostalAddressActivity.this, cityListResponse.getCityList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String state) {
                            city.setText(state);
                            city.setError(null);
                            statePickerDialog.dismiss();
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(PostalAddressActivity.this);
                    processor.getCityList();
                }
            }
        });
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StateListResponse stateListResponse = OfflineData.getStateList();
                if (stateListResponse != null) {
                    final StatePickerDialog statePickerDialog = new StatePickerDialog(PostalAddressActivity.this, stateListResponse.getStateList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String stateString) {
                            state.setText(stateString);
                            state.setError(null);
                            statePickerDialog.dismiss();
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(PostalAddressActivity.this);
                    processor.getStateList();
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.address_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addresstype.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.address_type_selection,
                        this));


    }

    private void showCountryPicker() {
        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                country.setText(name);
                country.setError(null);
                picker.dismiss();
            }
        });
        picker.show(PostalAddressActivity.this.getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    @OnClick(R.id.addAddress)
    public void addAddressListner() {
        boolean callAPi = true;
        if (address1.getText().toString().length() == 0) {
            address1.setError("Address line 1 is required");
            address1.requestFocus();
            callAPi = false;
        }
        if (address2.getText().toString().length() == 0) {
            address2.setError("Address line 2 is required");
            address2.requestFocus();
            callAPi = false;
        }
        if (post.getText().toString().length() == 0) {
            post.setError("Post is required");
            post.requestFocus();
            callAPi = false;
        }

        if (district.getText().toString().length() == 0) {
            district.setError("District is required");
            district.requestFocus();
            callAPi = false;
        }
        if (city.getText().toString().length() == 0) {
            city.setError("City is required");
            city.requestFocus();
            callAPi = false;
        }
        if (state.getText().toString().length() == 0) {
            state.setError("State is required");
            state.requestFocus();
            callAPi = false;
        }
        if (country.getText().toString().length() == 0) {
            country.setError("Country is required");
            country.requestFocus();
            callAPi = false;
        }
        if (pin.getText().toString().length() == 0) {
            pin.setError("Country is required");
            pin.requestFocus();
            callAPi = false;
        }
        if (addresstype.getSelectedItem() == null) {
            Toast.makeText(PostalAddressActivity.this, "Address type is required", Toast.LENGTH_SHORT).show();
            addresstype.requestFocus();
            addresstype.requestFocusFromTouch();
            callAPi = false;
        }

        if (callAPi) {
            showLoadingDialog();
            Address address = new Address(address1.getText().toString(), address2.getText().toString(), post.getText().toString(),
                    district.getText().toString(), city.getText().toString(), pin.getText().toString(), state.getText().toString(),
                    country.getText().toString(), addresstype.getSelectedItem().toString());
            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {


                RequestProcessor requestProcessor = new RequestProcessor(PostalAddressActivity.this);
                requestProcessor.addAddress(loginResponse.getId(), loginResponse.getAppToken(), address);
            }
        }
    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof AddAddressResponse) {
                    AddAddressResponse response = (AddAddressResponse) guiResponse;
                    if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(PostalAddressActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostalAddressActivity.this, "Address added successfully!", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    } else {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(PostalAddressActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostalAddressActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (guiResponse instanceof StateListResponse) {
                    StateListResponse stateListResponse = (StateListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveStateResponse(stateListResponse);
                        final StatePickerDialog statePickerDialog = new StatePickerDialog(PostalAddressActivity.this, stateListResponse.getStateList());
                        statePickerDialog.setStateChangeListner(new StateChangeListner() {
                            @Override
                            public void onStateSelected(String stateString) {
                                state.setText(stateString);
                                state.setError(null);
                                statePickerDialog.dismiss();
                            }
                        });
                        statePickerDialog.show();
                    }
                } else if (guiResponse instanceof CityListResponse) {
                    CityListResponse stateListResponse = (CityListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveCityResponse(stateListResponse);
                        final CityPickerDialog statePickerDialog = new CityPickerDialog(PostalAddressActivity.this, stateListResponse.getCityList());
                        statePickerDialog.setStateChangeListner(new StateChangeListner() {
                            @Override
                            public void onStateSelected(String state) {
                                city.setText(state);
                                city.setError(null);
                                statePickerDialog.dismiss();
                            }
                        });
                        statePickerDialog.show();
                    }
                }
            }
        }
    }
}
