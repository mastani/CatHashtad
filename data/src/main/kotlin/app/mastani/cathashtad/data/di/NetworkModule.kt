package app.mastani.cathashtad.data.di

import app.mastani.cathashtad.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val timeout = 30L
        val client = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            client.addNetworkInterceptor(loggingInterceptor)

        client.addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest
                .newBuilder()
                .apply {
                    addHeader("Content-Type", "application/json")
                    addHeader("Accept", "application/json")
                    addHeader("Accept-Language", "en-US")
                    addHeader("x-api-key", BuildConfig.API_KEY)
                }

            chain.proceed(requestBuilder.build())
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}