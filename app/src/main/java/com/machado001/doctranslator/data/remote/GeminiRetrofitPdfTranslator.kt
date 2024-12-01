package com.machado001.doctranslator.data.remote

import android.util.Log
import com.machado001.doctranslator.BuildConfig
import com.machado001.doctranslator.TAG
import com.machado001.doctranslator.domain.Content
import com.machado001.doctranslator.domain.DocumentTranslator
import com.machado001.doctranslator.domain.Part
import com.machado001.doctranslator.domain.RequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class GeminiRetrofitPdfTranslator(private val service: TranslationService) : DocumentTranslator {
    override suspend fun translate(text: String) {
        withContext(Dispatchers.IO) {
            try {
                Log.d(
                    TAG,
                    "translate: ${
                        service.translate(
                            key = BuildConfig.API_KEY,
                            requestBody = RequestBody(
                                contents = listOf(
                                    Content(
                                        parts = listOf(Part(text = "Translate the following to Portuguese: $text"))
                                    )
                                )
                            )
                        ).candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
                    }"
                )
            } catch (e: HttpException) {
                Log.d(TAG, "translate has exception. $e")
            }
        }
    }
}