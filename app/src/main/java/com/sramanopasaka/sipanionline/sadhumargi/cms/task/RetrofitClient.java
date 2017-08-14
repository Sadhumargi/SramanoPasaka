package com.sramanopasaka.sipanionline.sadhumargi.cms.task;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sramanopasaka.sipanionline.sadhumargi.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit authRetrofit = null;

    private static Retrofit memberRetrofit = null;

    public static Retrofit getAuthClient() {


        if (authRetrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor).build();
            Gson gson = new GsonBuilder().create();
            authRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.AUTH_API)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            Log.e("--Base Url--",""+BuildConfig.AUTH_API);
        }
        return authRetrofit;
    }



    public static Retrofit getMemberClient() {


        if (memberRetrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor).build();
            Gson gson = new GsonBuilder().create();
            memberRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.MEMBER_API)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            Log.e("--Base Url--",""+BuildConfig.MEMBER_API);
        }
        return memberRetrofit;
    }


}