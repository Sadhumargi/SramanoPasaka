package com.sramanopasaka.sipanionline.sadhumargi.cms.task;

import android.util.Log;

import com.google.gson.JsonObject;
import com.sramanopasaka.sipanionline.sadhumargi.AppConstants;
import com.sramanopasaka.sipanionline.sadhumargi.PasswordChangeResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddressListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.listener.EndPointApi;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;
import com.sramanopasaka.sipanionline.sadhumargi.model.BasicDetailsData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public class RequestProcessor {
    private GUICallback guiCallback = null;

    public RequestProcessor(GUICallback guiCallback) {
        this.guiCallback = guiCallback;

    }

    public void doLogin(JsonObject jsonObject) {



        EndPointApi valYouAPI = RetrofitClient.getAuthClient().create(EndPointApi.class);

        valYouAPI.doLogin(jsonObject).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                Log.e("Response message=", "" + response.message());

               /* LoginResponse response1 = new LoginResponse();
                if (response.body() != null) {
                    response1.setLoginModel(response.body());

                    response1.setStatus(true);

                } else {
                    response1.setStatus(false);
                    if (!TextUtils.isEmpty(response.errorBody().toString())) {
                        try {
                            JSONObject jObjError = new JSONObject(convertStreamToString(response.errorBody().byteStream()));
                            response1.setMessage(jObjError.getString("message"));
                        } catch (Exception e) {
                            response1.setMessage("Invalid Username or Password");
                        }
                    } else {
                        response1.setMessage("Invalid Username or Password");

                    }


                }*/

                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void doRegister(JsonObject jsonObject) {



        EndPointApi valYouAPI = RetrofitClient.getAuthClient().create(EndPointApi.class);

        valYouAPI.doRegister(jsonObject).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {


                Log.e("Response message=", "" + response.message());

               /* LoginResponse response1 = new LoginResponse();
                if (response.body() != null) {
                    response1.setLoginModel(response.body());

                    response1.setStatus(true);

                } else {
                    response1.setStatus(false);
                    if (!TextUtils.isEmpty(response.errorBody().toString())) {
                        try {
                            JSONObject jObjError = new JSONObject(convertStreamToString(response.errorBody().byteStream()));
                            response1.setMessage(jObjError.getString("message"));
                        } catch (Exception e) {
                            response1.setMessage("Invalid Username or Password");
                        }
                    } else {
                        response1.setMessage("Invalid Username or Password");

                    }


                }*/

                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getBasicDetails(String memberId,String token) {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);

        valYouAPI.getBasicDetails(memberId,token).enqueue(new Callback<BasicDetailsResponse>() {
            @Override
            public void onResponse(Call<BasicDetailsResponse> call, Response<BasicDetailsResponse> response) {


                Log.e("Response message=", "" + response.message());

               /* LoginResponse response1 = new LoginResponse();
                if (response.body() != null) {
                    response1.setLoginModel(response.body());

                    response1.setStatus(true);

                } else {
                    response1.setStatus(false);
                    if (!TextUtils.isEmpty(response.errorBody().toString())) {
                        try {
                            JSONObject jObjError = new JSONObject(convertStreamToString(response.errorBody().byteStream()));
                            response1.setMessage(jObjError.getString("message"));
                        } catch (Exception e) {
                            response1.setMessage("Invalid Username or Password");
                        }
                    } else {
                        response1.setMessage("Invalid Username or Password");

                    }


                }*/

                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<BasicDetailsResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getAddressList(String memberId,String token) {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);

        valYouAPI.getAddressList(memberId,token).enqueue(new Callback<AddressListResponse>() {
            @Override
            public void onResponse(Call<AddressListResponse> call, Response<AddressListResponse> response) {


                Log.e("Response message=", "" + response.message());
                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddressListResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void updateBasicDetails(String memberId, String token, BasicDetailsData data) {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);



        valYouAPI.updateBasicDetails(memberId,token,"update",data.getSalution(),data.getFirstName(),data.getMiddleName(),data.getLastName(),
        data.getGuardianType(),data.getGuardianName(),data.getMotherName(),data.getAddress(),data.getCity(),data.getPincode(),data.getState(),data.getCountry(),data.getMobile(),data.getAlternateNumber(),
                data.getWhatsappNumber(),data.getBirthDay(),data.getGender(),data.getBloodGroup(),data.getMaritalStatus(),data.getMarriageDate(),data.getChildCount(),data.getEmailAddress(),data.getIsHeadOfFamily()).enqueue(new Callback<UpdateBasicDetailsResponse>() {
            @Override
            public void onResponse(Call<UpdateBasicDetailsResponse> call, Response<UpdateBasicDetailsResponse> response) {


                Log.e("Response message=", "" + response.message());

               /* LoginResponse response1 = new LoginResponse();
                if (response.body() != null) {
                    response1.setLoginModel(response.body());

                    response1.setStatus(true);

                } else {
                    response1.setStatus(false);
                    if (!TextUtils.isEmpty(response.errorBody().toString())) {
                        try {
                            JSONObject jObjError = new JSONObject(convertStreamToString(response.errorBody().byteStream()));
                            response1.setMessage(jObjError.getString("message"));
                        } catch (Exception e) {
                            response1.setMessage("Invalid Username or Password");
                        }
                    } else {
                        response1.setMessage("Invalid Username or Password");

                    }


                }*/

                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<UpdateBasicDetailsResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void addAddress(String memberId, String token, Address data) {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);



        valYouAPI.addAddress(memberId,token,"add",data.getAddress1(),data.getAddress2(),data.getPost(),data.getDistrict(),
                data.getCity(),data.getPincode(),data.getState(),data.getCountry(),data.getAddress_type()).enqueue(new Callback<AddAddressResponse>() {
            @Override
            public void onResponse(Call<AddAddressResponse> call, Response<AddAddressResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddAddressResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }
    public void removeAddress(String memberId, String token, Address data) {


        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);


        valYouAPI.removeAddress(memberId, token, "delete", data.getId()).enqueue(new Callback<DeleteAddressResponse>() {
            @Override
            public void onResponse(Call<DeleteAddressResponse> call, Response<DeleteAddressResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DeleteAddressResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });

    }

    public void passwordChange(String memberId, String token, String currentPassword,String newPassword) {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);



        valYouAPI.passwordChange(memberId,token,currentPassword,newPassword).enqueue(new Callback<PasswordChangeResponse>() {
            @Override
            public void onResponse(Call<PasswordChangeResponse> call, Response<PasswordChangeResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<PasswordChangeResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
        }

        String finallyError = sb.toString();
        Log.e("finallyError=", "" + finallyError);
        return finallyError;
    }
}
