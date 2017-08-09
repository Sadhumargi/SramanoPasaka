package in.cashkaro.com.smcommon.networking;

import com.google.gson.Gson;
import com.stylemybody.utils.Log;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by techjini on 8/1/16.
 */
public class SMBResponseWrapper<T> {

    private Call<T> mRequest;


    public SMBResponseWrapper(Call<T> service) {
        mRequest = service;
    }

    public void execute(final int apiId, final ResponseHandler handler, final Type type) {


        mRequest.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Response<T> response, Retrofit retrofit) {
                String errorBody;
                switch (response.code()) {
                    case 200:
                        Log.e(new Gson().toJson(response.body()));
                        handler.handle200(apiId, response.body());

                        break;
                    default:

                        try {
                            errorBody = response.errorBody().string();
                            Log.e(new Gson().toJson(errorBody));
                            handler.handleError(apiId, response.code(), new Gson().fromJson(errorBody, type));
                        } catch (Exception e) {
                            e.printStackTrace();
                            handler.handleException(apiId, new Exception(getErrorMsg(e)));
                        }

//                    case 400:
//                        try {
//                            errorBody = response.errorBody().string();
//                            handler.handle400(new Gson().fromJson(errorBody, type));
//                        } catch (IOException e) {
//                            e.printStackTrace(); 9901721897 maha
//
//                        }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                String str = "Unable to process response.Please try again.";
                if (t.getMessage() != null) {
                    str = str + " " + t.getMessage();
                }
                handler.handleException(apiId, new Exception(str));

            }
        });
    }

    public interface ResponseHandler<U, V> {

        void handle200(int apiId, U response);

        //        void handle400(V error);
//
//        void handle401(V error);
//
//        void handle500(V error);
        void handleError(int apiId, int code, V error);

        void handleException(int apiId, Exception e);
    }

    private String getErrorMsg(Exception e) {
        String error = "";
        if (e instanceof SocketTimeoutException) {
            error = "Unable to connect to server.Please check your internet connection.";
        } else if (e instanceof UnknownHostException) {
            error = "Unable to connect to server.Please check your internet connection.";
        } else if (e instanceof IOException) {
            error = "Unable to connect to server.Please check your internet connection.";
        } else if (e instanceof TimeoutException) {
            error = "Unable to connect to server.Please check your internet connection.";
        } else if (e instanceof SocketException) {
            error = "Unable to connect to server.Please check your internet connection.";
        } else {
            error = e.getLocalizedMessage();
        }
        return error;
    }
}
