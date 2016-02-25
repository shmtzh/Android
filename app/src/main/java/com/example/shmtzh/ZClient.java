package com.example.shmtzh;

import android.text.TextUtils;
import android.util.Log;

import com.example.shmtzh.myapplication.bus.EventBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by shmtzh on 2/25/16.
 */
public class ZClient {

    private static final int CACHE_SIZE_MB = 10;
    private static final int CONNECTION_TIMEOUT_SECONDS = 6;
    private final EventBus mBus;
    private final ZClient.RestInterface restInterface;
    private static final String key = "87f8b91c254047d781b0c929f0bf150f";


    public static ZClient getRestWithCache(final EventBus bus, String cacheAbsolutePath,
                                                    final RequestInterceptor interceptor, String apiUrl, boolean logActive) {
        int cacheSize = CACHE_SIZE_MB * 1024 * 1024; // 10 MiB

        final OkHttpClient client = getUnsafeOkHttpClient();
        client.setConnectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        final Client okClient = new OkClient(client);
        return new ZClient(bus, okClient, interceptor, apiUrl, logActive);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.networkInterceptors().add(logging);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static ZClient getRestWithNoCache(
            final EventBus bus, String apiUrl, boolean logActive) {
        return new ZClient(bus, apiUrl, logActive);
    }

    public ZClient(final EventBus bus, String apiUrl, boolean logActive) {
        this(bus, new OkClient(getUnsafeOkHttpClient()), apiUrl, logActive);
    }

    public ZClient(final EventBus bus, final Client client,
                            String apiUrl, boolean logActive) {
        this(bus, client, null, apiUrl, logActive);
    }

    RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.addHeader("Accept", "application/json");
        }
    };

    public ZClient(final EventBus bus, final Client client,
                            final RequestInterceptor interceptor, String apiUrl, boolean logActive) {
        mBus = bus;
        bus.register(this);

        // Create an instance of our AsynchronousApi interface.
        RestAdapter.Builder builder = new RestAdapter.Builder();

        if (logActive) {
            builder.setEndpoint(apiUrl).setClient(client).setLogLevel(RestAdapter.LogLevel.FULL);
        } else {
            builder.setEndpoint(apiUrl).setClient(client).setLogLevel(RestAdapter.LogLevel.NONE);
        }

        if (interceptor != null) {
            builder.setRequestInterceptor(interceptor);
        }

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        final RestAdapter restAdapter = builder.setConverter(new GsonConverter(gson)).build();
        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        // Create an instance of our API interface.
        restInterface = restAdapter.create(ZClient.RestInterface.class);

    }




    public interface RestInterface {


    }

}
