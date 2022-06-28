package invin.mvvm_invin.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class RequestClient(private val _headers: HashMap<String, String>? = null) {
    companion object {
        private const val TAG = "RequestClient"

        private const val CONNECT_TIME_OUT = 10L
        private const val READ_TIME_OUT = 10L
        private const val WRITE_TIME_OUT = 10L
    }

    var client: OkHttpClient

    init {
        client = OkHttpClient().newBuilder().apply {
            followRedirects(true)
            followSslRedirects(true)
            connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            addInterceptor(HeaderInterceptor())
            addInterceptor(ParamInterceptor())
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    private inner class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder().apply {
                header("User-Agent", "AS40; Android 12; 6.4.8.1-DEV; SM-S908N")
                header("Accept-Charset", "utf-8")
//                header("Accept-Encoding", "gzip,deflate")
            }

            if (_headers.isNullOrEmpty().not()) {
                val headers = _headers!!
                for (key in headers.keys) {
                    val value = headers[key]
                    Log.d(TAG, "$key : $value")
                    if (value != null) {
                        builder.header(key, value)
                    }
                }
            }
            return chain.proceed(builder.build())
        }
    }

    private inner class ParamInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val builder = request.newBuilder()

            builder.url(request.url.newBuilder().apply {
                addQueryParameter("cpId", "AS40")
                addQueryParameter("cpKey", "14LNC3")
            }.build())
            return chain.proceed(builder.build())
        }
    }
}