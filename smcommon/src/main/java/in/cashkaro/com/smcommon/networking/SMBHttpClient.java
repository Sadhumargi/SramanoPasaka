package in.cashkaro.com.smcommon.networking;

import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.stylemybody.SMBConstant;
import com.stylemybody.common.Session;
import com.stylemybody.utils.Log;
import com.stylemybody.utils.SecurityUtils;
import com.stylemybody.utils.Util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by techjini on 31/12/15.
 */
public class SMBHttpClient extends OkHttpClient {

    private Context mContext;

    public SMBHttpClient(final Session session, Context context, final String key, final String publicKey) {
        super();
        // Application context
        mContext = context;

        setReadTimeout(SMBConstant.HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();


                Request.Builder builder = original.newBuilder();

                if (session.getAuthToken() != null) {
                    builder.header("Token", session.getAuthToken());
                }
                String timeStamp = String.valueOf(System.currentTimeMillis() / 1000L);


                Request request = builder
                        .header("Public-Key", publicKey)
                        .header("Signature", SecurityUtils.getSignature(key, timeStamp + key))
                        .header("Timestamp", timeStamp)
                        .method(original.method(), original.body())
                        .build();
                Log.e("ttoken "+new Gson().toJson(request));
                if (!Util.networkCheck(mContext)) {
                    throw new IOException("Please check your internet connection.");
                }
Log.e(request.urlString());
                return chain.proceed(request);
            }
        });
    }

}