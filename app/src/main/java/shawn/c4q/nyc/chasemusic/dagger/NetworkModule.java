package shawn.c4q.nyc.chasemusic.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shawn.c4q.nyc.chasemusic.api.ItunesAPI;

/**
 * Created by shawnspeaks on 10/7/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Cache providesOkHttpCache(Application application){
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Cache cache){
        return new OkHttpClient.Builder().cache(cache).build();
    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory providesRxJavaAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonFactory, RxJava2CallAdapterFactory rxFactory, OkHttpClient httpClient){
        return new Retrofit.Builder()
                .baseUrl(ItunesAPI.BASE_URL)
                .addConverterFactory(gsonFactory)
                .addCallAdapterFactory(rxFactory)
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    ItunesAPI provideItunesClient(Retrofit retrofit){
        return retrofit.create(ItunesAPI.class);
    }


}

