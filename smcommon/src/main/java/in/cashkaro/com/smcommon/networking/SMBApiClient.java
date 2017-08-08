package in.cashkaro.com.smcommon.networking;

import android.content.Context;
import android.support.annotation.Nullable;

import com.stylemybody.common.Session;

import java.util.concurrent.ConcurrentHashMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by techjini on 31/12/15.
 */
public class SMBApiClient {
    final ConcurrentHashMap<Class, Object> services;
    Retrofit retrofit;
    private static SMBApiClient sInstance;


    public static SMBApiClient getInstance(Session session, Context context, String key, String publicKey) {
        if (sInstance == null) {
            sInstance = new SMBApiClient(session, context, key, publicKey);
        }
        return sInstance;
    }

    private SMBApiClient(@Nullable Session session, Context context, String key, String publicKey) {
        services = new ConcurrentHashMap<>();
        retrofit = new Retrofit.Builder().client(new SMBHttpClient(session, context, key, publicKey)).baseUrl(new SMBApi().getBaseHostUrl()).addConverterFactory(GsonConverterFactory.create()).build();
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
