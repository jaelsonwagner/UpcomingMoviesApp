package jaelson.wagner.silva.upcomingmovies.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import dagger.Module;
import dagger.Provides;
import jaelson.wagner.silva.upcomingmovies.ApplicationConfiguration;
import jaelson.wagner.silva.upcomingmovies.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.annotation.Nullable;
import javax.inject.Singleton;

/**
 * Created by jaelsonwagner@gmail.com.
 */

@Module
public class NetworkModule {

  @Provides
  @Nullable
  HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    if (BuildConfig.DEBUG) {
      return new HttpLoggingInterceptor((String message) -> {
        // we can emit event here to a client listener handle that data, like persist
        // requests
        // into a database to future query, i.e., a debug drawer screen.
      }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    return null;
  }

  @Provides
  @Nullable
  Interceptor provideInterceptor() {
    return BuildConfig.DEBUG ? new StethoInterceptor() : null;
  }

  @Provides
  OkHttpClient provideOkHttpClient(@Nullable HttpLoggingInterceptor httpLoggingInterceptor,
                                   @Nullable Interceptor networkInterceptor) {

    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    if (httpLoggingInterceptor != null) {
      builder.addInterceptor(httpLoggingInterceptor);
    }

    if (networkInterceptor != null) {
      builder.addNetworkInterceptor(networkInterceptor);
    }

    return builder.build();
  }

  @Provides
  Converter.Factory provideConverterFactory() {
    return MoshiConverterFactory.create().withNullSerialization().failOnUnknown();
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(OkHttpClient okHttpClient,
                           Converter.Factory converterFactory) {
    return new Retrofit.Builder().baseUrl(ApplicationConfiguration.getBaseUrl())
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }
}
