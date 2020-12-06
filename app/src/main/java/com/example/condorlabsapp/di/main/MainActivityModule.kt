package com.example.condorlabsapp.di.main

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.condorlabsapp.R
import com.example.condorlabsapp.business.data.network.abstraction.SoccerTeamsNetworkRepository
import com.example.condorlabsapp.business.data.network.abstraction.TheSportDbApi
import com.example.condorlabsapp.business.data.network.implementation.SoccerTeamsNetworkRepositoryImpl
import com.example.condorlabsapp.framework.data_source.network.mapers.SoccerTeamMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    fun provideSoccerTeamsRepository(
        theSportDbApi: TheSportDbApi,
        soccerTeamMapper: SoccerTeamMapper
    ): SoccerTeamsNetworkRepository {
        return SoccerTeamsNetworkRepositoryImpl(
            theSportDbApi = theSportDbApi,
            soccerTeamMapper = soccerTeamMapper
        )
    }

    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)
    }

    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }
}