package com.configcar.weatherforecast.di

import com.configcar.weatherforecast.data.remote.ForecastRemoteDataSource
import com.configcar.weatherforecast.data.remote.ForecastService
import com.configcar.weatherforecast.data.repository.ForecastRepository
import com.configcar.weatherforecast.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Provides
    fun provideForecastService(retrofit: Retrofit) : ForecastService = retrofit.create(ForecastService::class.java)

    @Singleton
    @Provides
    fun provideForecastRemoteDataSource(forecastService: ForecastService) : ForecastRemoteDataSource = ForecastRemoteDataSource(forecastService)

    @Singleton
    @Provides
    fun provideForecastRepository(forecastRemoteDataSource: ForecastRemoteDataSource) : ForecastRepository = ForecastRepository(forecastRemoteDataSource)

}