package com.idestore.services;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://estore.amrsof.tech";
    //https://estore.amrsof.tech/assets/images/thumbnails/
    public static final String IMG_URL ="https://estore.amrsof.tech/assets/images/thumbnails/";
    //https://estore.amrsof.tech/assets/images/galleries
    public static final String IMG_GALLERY ="https://estore.amrsof.tech/assets/images/galleries/";

    //https://estore.amrsof.tech/assets/images/products
    public static final String IMG_PRODUCTS ="https://estore.amrsof.tech/assets/images/products/";

    public static GetDataService getRetrofitInstance() {
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(logging).
                connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        GetDataService api = retrofit.create(GetDataService.class);
        return api;
    }
}
