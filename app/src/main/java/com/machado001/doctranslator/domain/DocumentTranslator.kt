package com.machado001.doctranslator.domain

interface DocumentTranslator {
    suspend fun translate(text: String)
}