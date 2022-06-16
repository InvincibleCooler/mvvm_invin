package invin.mvvm_invin

import android.app.Application
import android.content.Context


class App : Application() {
    companion object {
        const val TAG = "RenewApplication"

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