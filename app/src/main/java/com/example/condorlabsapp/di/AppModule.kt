package com.example.condorlabsapp.di

import com.example.condorlabsapp.business.data.network.abstraction.TheSportDbApi
import com.example.condorlabsapp.util.NetworkConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson, client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .client(client)
    }

    @Provides
    fun provideTheSportDbApi(retrofitBuilder: Retrofit.Builder): TheSportDbApi {
        return retrofitBuilder
            .build()
            .create(TheSportDbApi::class.java)
    }

}