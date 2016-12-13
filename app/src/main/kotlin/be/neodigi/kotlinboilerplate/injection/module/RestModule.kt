package be.neodigi.kotlinboilerplate.injection.module

import be.neodigi.kotlinboilerplate.BuildConfig
import be.neodigi.kotlinboilerplate.data.remote.RestService
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return f?.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }
        })
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRestService(gson: Gson, okHttpClient: OkHttpClient): RestService {
        val httpClientBuilder = okHttpClient.newBuilder()

        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        httpClientBuilder.addInterceptor(logging).build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(RestService::class.java)
    }
}