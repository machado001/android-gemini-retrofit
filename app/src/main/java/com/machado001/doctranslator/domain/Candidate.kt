package com.machado001.doctranslator.domain

data class Candidate(
    val content: Content,
    val finishReason: String,
    val citationMetadata: CitationMetadata,
    val avgLogprobs: Double
)