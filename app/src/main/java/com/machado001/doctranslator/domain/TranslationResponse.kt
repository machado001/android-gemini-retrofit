package com.machado001.doctranslator.domain

data class TranslationResponse(
    val candidates: List<Candidate>,
    val usageMetadata: UsageMetadata,
    val modelVersion: String
)