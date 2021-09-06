package com.jmm.brsap.fcmdemo.di

import android.content.Context
import com.jmm.brsap.fcmdemo.BuildConfig
import com.jmm.brsap.fcmdemo.repositories.UserPreferencesRepository
import com.jmm.brsap.fcmdemo.services.ApiService
import com.jmm.brsap.fcmdemo.util.Constants
import com.jmm.brsap.fcmdemo.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)


        return Retrofit.Builder().baseUrl(BuildConfig.FCM_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())

            .build()

    }
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserPreferencesRepository(@ApplicationContext context: Context): UserPreferencesRepository {
        return UserPreferencesRepository(context)
    }


}
