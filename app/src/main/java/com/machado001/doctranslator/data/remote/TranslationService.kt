package com.machado001.doctranslator.data.remote

import com.machado001.doctranslator.domain.RequestBody
import com.machado001.doctranslator.domain.TranslationResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface TranslationService {
    @POST("models/gemini-1.5-flash-latest:generateContent")
    suspend fun translate(
        @Query("key") key: String,
        @Body requestBody: RequestBody
    ): TranslationResponse
}