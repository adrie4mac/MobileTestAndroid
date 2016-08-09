package com.adriesavana.mobileappstest.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adri on 4/26/16.
 */
public class NetworkManager {

    public static NetworkService instance;
    public static Retrofit retrofit;

    private static final int CONNECT_TIME_OUT = 60 * 1000;
    private static final int READ_TIME_OUT = 60 * 1000;

    public static synchronized NetworkService getInstance() {

        if (instance == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS); // connect timeout
            httpClient.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS); // socket timeout
            //httpClient.addNetworkInterceptor(new HttpLoggingInterceptor());
            httpClient.addNetworkInterceptor(interceptor);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("content-type", "application/x-www-form-urlencoded")
                            .header("App-ID", "mobile")
                            .header("cache-control", "no-cache")
                            .build();

                    Response response = chain.proceed(request);

                    return response;
                }
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://52.76.85.10")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();

            instance = retrofit.create(NetworkService.class);
        }

        return instance;
    }

}
