package invin.mvvm_invin.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import invin.mvvm_invin.net.RequestManager
import invin.mvvm_invin.net.ServiceApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideServiceApi(): ServiceApi {
        return RequestManager.getServiceApi()
    }
}