package invin.mvvm_invin.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 나중에 di방식으로 base url 나눠보자.
object RetrofitClient {
    private var BASE_URL = "https://api.itbook.store/1.0/"
    // singleton 방식
    // For Singleton instantiation.
    @Volatile
    private var instance: Retrofit? = null

    fun getInstance(okHttpClient: OkHttpClient): Retrofit =
        instance ?: synchronized(this) {
            instance ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

    // 되는지 확인 필요함.
    fun setBaseUrl(baseUrl: String = BASE_URL) {
        if (instance != null) {
            (instance as Retrofit).newBuilder().baseUrl(baseUrl)
        }
    }


    // Interceptor 방식으로 바꾸는게 더 좋을듯.
    // https://gist.github.com/swankjesse/8571a8207a5815cca1fb
//    // baseUrl 변경가능한 방식
//    fun setBaseUrl(baseUrl: String = BASE_URL) {
//        BASE_URL = baseUrl
//    }
//
//    fun getInstance(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//    }
}