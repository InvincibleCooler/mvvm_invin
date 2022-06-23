package invin.mvvm_invin.net


class RequestManager {
    companion object {
        private const val TAG = "RequestManager"

        fun getServiceApi(headers: HashMap<String, String>? = null): ServiceApi {
            val okHttpClient = RequestClient(headers).client
            val retrofit = RetrofitClient.getInstance(okHttpClient)
            return retrofit.create(ServiceApi::class.java)
        }
    }
}