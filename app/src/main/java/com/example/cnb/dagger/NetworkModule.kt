package com.example.cnb.dagger

import android.content.Context
import com.example.cnb.dogapi.DogBreedApiInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File

@Module(includes = [ContextModule::class])
class NetworkModule {

    @Provides
    @ApplicationScope
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        val interceptor = HttpLoggingInterceptor { message: String? ->
            Timber.i(
                message
            )
        }
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @ApplicationScope
    fun getFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @ApplicationScope
    fun getCache(cacheFile: File): Cache {
        cacheFile.mkdirs()
        return Cache(cacheFile, 10 * 1000 * 1000) //10mb
    }

    @Provides
    @ApplicationScope
    fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor?, cache: Cache?): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @ApplicationScope
    fun getDogBreedApiInterface(okHttpClient: OkHttpClient?): DogBreedApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(DogBreedApiInterface::class.java)
    }
}