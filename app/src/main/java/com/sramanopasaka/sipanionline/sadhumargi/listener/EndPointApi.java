package com.sramanopasaka.sipanionline.sadhumargi.listener;

import com.google.gson.JsonObject;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public interface EndPointApi {

    @POST("members/auth")
    Call<LoginResponse> doLogin(@Body JsonObject profiledata);

    @POST("members/create")
    Call<RegisterResponse> doRegister(@Body JsonObject profiledata);

}
