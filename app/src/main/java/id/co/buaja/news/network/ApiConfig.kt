package id.co.buaja.news.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import id.co.buaja.news.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object {
        fun config(): ApiInterface {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor {
                    val original = it.request()
                    val builder = original.newBuilder()
                    builder.addHeader("Authorization", BuildConfig.API_KEY)
                    val request = builder.build()
                    return@addInterceptor it.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}