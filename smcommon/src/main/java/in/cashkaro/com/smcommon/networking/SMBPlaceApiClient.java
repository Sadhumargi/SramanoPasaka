package in.cashkaro.com.smcommon.networking;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.ConcurrentHashMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by techjini on 14/4/16.
 */
public class SMBPlaceApiClient {
    final ConcurrentHashMap<Class, Object> services;
    Retrofit retrofit;
    private static SMBPlaceApiClient sInstance;


    public static SMBPlaceApiClient getInstance() {
        if (sInstance == null) {
            sInstance = new SMBPlaceApiClient();
        }
        return sInstance;
    }

    private SMBPlaceApiClient() {
        services = new ConcurrentHashMap<>();
        retrofit = new Retrofit.Builder().client(new OkHttpClient()).baseUrl("https://maps.googleapis.com").addConverterFactory(GsonConverterFactory.create()).build();
    }

    public <T> T getService(Class<T> cls) {
        return this.getRetrofitService(this.retrofit, cls);
    }

    public <T> T getRetrofitService(Retrofit retrofit, Class<T> cls) {
        if (!this.services.contains(cls)) {
            this.services.putIfAbsent(cls, retrofit.create(cls));
        }
        return (T) this.services.get(cls);
    }

}
