package com.example.shmtzh.myapplication.rest;

import android.util.Log;

import com.example.shmtzh.myapplication.callback.AbstractCallback;
import com.example.shmtzh.myapplication.callback.ActiveFeedCallback;
import com.example.shmtzh.myapplication.callback.AllStatisticCallback;
import com.example.shmtzh.myapplication.callback.DayStatisticCallback;
import com.example.shmtzh.myapplication.callback.HistoryFeedCallback;
import com.example.shmtzh.myapplication.callback.LoginCallback;
import com.example.shmtzh.myapplication.callback.MonthStatisticCallback;
import com.example.shmtzh.myapplication.callback.NewFeedCallback;
import com.example.shmtzh.myapplication.callback.SupportNumberCallback;
import com.example.shmtzh.myapplication.callback.WeekStatisticCallback;
import com.example.shmtzh.myapplication.model.CompleteStatistic;
import com.example.shmtzh.myapplication.model.FeedModel;
import com.example.shmtzh.myapplication.model.LoginCredentials;
import com.example.shmtzh.myapplication.model.LoginModel;
import com.example.shmtzh.myapplication.model.RegularStatistic;
import com.example.shmtzh.myapplication.model.SupportNumber;
import com.example.shmtzh.myapplication.bus.EventBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

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
private final String TAG= getClass().getSimpleName();

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

    public void getTheNumber() {
        SupportNumberCallback callback = new SupportNumberCallback(mBus);
        restInterface.getTheNumber(callback);
    }

    public void login(LoginCredentials login) {
        LoginCallback callback = new LoginCallback(mBus);
        restInterface.login(login.getPhone(), login.getPassword(), login.getDeviceId(), callback);
    }

    public void getFeedNew(String token) {
        NewFeedCallback callback = new NewFeedCallback(mBus);
        restInterface.getFeedNew(1, 50, token, callback);
    }

    public void getFeedActive(String token) {
        ActiveFeedCallback callback = new ActiveFeedCallback(mBus);
        restInterface.getFeedActive(1, 50, token, callback);
    }

    public void getFeedHistory(String token) {
        HistoryFeedCallback callback = new HistoryFeedCallback(mBus);
        Log.d("test", "getHistory: " + token);
        restInterface.getFeedHistory(1, 50, token, callback);
    }

    public void getStatistic(String token, String period) {
        AbstractCallback callback = null;
        Log.d("test", "getStatistic: " + token);
        switch (period) {
            case "day":
                callback = new DayStatisticCallback(mBus);
                Log.d(TAG, "callbackZ - day");
                break;
            case "week":
                callback = new WeekStatisticCallback(mBus);
                Log.d(TAG, "callbackZ - week");
                break;
            case "month":
                callback = new MonthStatisticCallback(mBus);
                Log.d(TAG, "callbackZ - month");
                break;
        }
        restInterface.getStatistic(period, token, callback);
    }

    public void getCompleteStatistic(String token) {
        AllStatisticCallback callback = new AllStatisticCallback(mBus);
        restInterface.getAllStatistic(token, callback);
    }

    public interface RestInterface {

        @GET("/support")
        void getTheNumber(
                Callback<SupportNumber> callback);

        @FormUrlEncoded
        @POST("/login")
        void login(
                @Field("phone") String phone,
                @Field("password") String password,
                @Field("device_id") String id,
                Callback<LoginModel> callback
        );

        @GET("/orders/new/{page}/{limit}")
        void getFeedNew(
                @Path("page") int page,
                @Path("limit") int limit,
                @Header("token") String token,
                Callback<FeedModel> callback
        );

        @GET("/orders/active/{page}/{limit}")
        void getFeedActive(
                @Path("page") int page,
                @Path("limit") int limit,
                @Header("token") String token,
                Callback<FeedModel> callback
        );

        @GET("/orders/history/{page}/{limit}")
        void getFeedHistory(
                @Path("page") int page,
                @Path("limit") int limit,
                @Header("token") String token,
                Callback<FeedModel> callback
        );

        @GET("/statistic/{period}")
        void getStatistic(
                @Path("period") String period,
                @Header("token") String token,
                Callback<RegularStatistic> callback
        );

        @GET("/statistic/all")
        void getAllStatistic(
                @Header("token") String token,
                Callback<CompleteStatistic> callback
        );
    }

}
