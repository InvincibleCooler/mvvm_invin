package invin.mvvm_invin.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import invin.mvvm_invin.db.ApiDao
import invin.mvvm_invin.db.ApiDataBase
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ApiDataBase {
        return ApiDataBase.getInstance(context)
    }

    @Provides
    fun provideApiDao(apiDataBase: ApiDataBase): ApiDao {
        return apiDataBase.apiDao()
    }
}