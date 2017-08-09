package com.sramanopasaka.sipanionline.sadhumargi.cms.task;

import android.util.Log;

import com.google.gson.JsonObject;
import com.sramanopasaka.sipanionline.sadhumargi.AppConstants;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.listener.EndPointApi;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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



        EndPointApi valYouAPI = RetrofitClient.getClient(AppConstants.BASE_URL).create(EndPointApi.class);

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
