package iori.basecore.network;

import android.content.Context;
import android.os.Environment;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import iori.basecore.constant.ModelConstantCode;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shendawei on 17/2/8.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RetrofitBuilder {

    @RootContext
    Context context;
    private static final long TIMEOUT_CONNECT = 30 * 1000;
    private static Retrofit mRetrofit;

    public Retrofit build() {
        if (mRetrofit == null) {
            File cacheDirectory = new File(Environment.getExternalStorageDirectory(), "/neo/cache");
            long maxSize = 1024 * 1024 * 1024 ;
            Cache cache = new Cache(cacheDirectory, maxSize);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .retryOnConnectionFailure(true);

            // Add token authentication interceptor.
            String token = "accessToken";
//            httpClient.addInterceptor(new AppInterceptor(token));
            // Add http log interceptor for debug.

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);


            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ModelConstantCode.BASE_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return mRetrofit;
    }


    /**
     * 注册gson解析类型，此处注册的是question类
     * @return
     */



    private class AppInterceptor implements Interceptor {

        private String agent;
        private String token;
        private int cache;

        AppInterceptor(String token) {
            this.agent = "agent";
            this.token = token;
            this.cache = 60;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();

//            String token = ;
            builder.addHeader("Authorization", "token " + token);
            if (!request.method().equals("GET")) {
                builder.addHeader("Content-Type", "application/json");
            }
            builder.addHeader("charset", "utf-8");
            builder.addHeader("connection","close");
//            builder.addHeader("User-Agent","SKAPP-" + DeviceUtil.getAppVersion()+" android " + DeviceUtil.getSdkVersion());
//            try {
//                String nonce= SignUtil.getNonce();
//                String secret=SignUtil.encryptHMAC(nonce);
//                builder.addHeader("X-Signature",(nonce+":"+secret).trim());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            try {

                return chain.proceed(builder.build());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Request.Builder defBuilder = request.newBuilder();
            defBuilder.addHeader("Authorization", "token " + token);
            return chain.proceed(defBuilder.build());
        }
    }

}
