package br.com.fundatec.fundatecheroesti21.network

import br.com.fundatec.fundatecheroesti21.BuildConfig
import br.com.fundatec.fundatecheroesti21.character.data.repository.CharacterService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 60L

object RetrofitNetworkClient {

    private const val BASE_URL = "https://fundatec.herokuapp.com/"

    val instance: CharacterService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(CharacterService::class.java)
    }

    fun createNetworkClient(baseUrl: String = "https://fundatec.herokuapp.com") =
        retrofitClient(
            baseUrl,
            httpClint(),
            moshi()
        )

    private fun moshi() = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    private fun httpClint(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logginInterceptor())
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()

    private fun logginInterceptor() =
        HttpLoggingInterceptor().apply {
            level = if (com.squareup.picasso.BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun retrofitClient(
        baseUrl: String,
        htttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(htttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

}
