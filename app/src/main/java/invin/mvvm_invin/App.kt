package invin.mvvm_invin

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        const val TAG = "App"

        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }

        fun getContext(): Context {
            return instance
        }
    }

    init {
        instance = this
    }
}