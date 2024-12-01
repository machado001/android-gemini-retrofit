package com.machado001.doctranslator.di

import com.machado001.doctranslator.data.remote.GeminiRetrofitPdfTranslator
import com.machado001.doctranslator.data.remote.TranslationService
import com.machado001.doctranslator.data.remote.TranslationService.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DiContainer : Container {

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<TranslationService>()


    override val translator = GeminiRetrofitPdfTranslator(retrofit)
}