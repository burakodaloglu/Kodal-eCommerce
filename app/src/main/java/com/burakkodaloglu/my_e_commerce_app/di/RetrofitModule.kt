package com.burakkodaloglu.my_e_commerce_app.di

import android.util.Log
import com.burakkodaloglu.my_e_commerce_app.data.source.remote.ProductService
import com.burakkodaloglu.my_e_commerce_app.domain.model.AppResponse
import com.burakkodaloglu.my_e_commerce_app.util.constants.Constants.BASE_URL
import com.burakkodaloglu.my_e_commerce_app.util.constants.Constants.STORE
import com.burakkodaloglu.my_e_commerce_app.util.retrofit.ResultCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("store", STORE).build()

            val response = it.proceed(request)
            val responseBody: String = response.body?.string().orEmpty()
            val appResponse = GsonBuilder().create().fromJson(responseBody, AppResponse::class.java)
            if (appResponse.isSuccess) {
                it.proceed(request)
            } else {
                val contentJson = Gson().toJson(appResponse)
                Log.e("Interceptor", "ContentJson = $contentJson")
                Response.Builder()
                    .code(appResponse.status ?: -1)
                    .message(appResponse.message.orEmpty())
                    .protocol(response.protocol)
                    .request(response.request)
                    .body(ResponseBody.create(response.body?.contentType(), responseBody))
                    .build()
            }
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApi(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(getOkHttpClient(getInterceptor()))
        .build()

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}