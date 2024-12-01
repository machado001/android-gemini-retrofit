package com.machado001.doctranslator.domain

data class CitationSource(
    val startIndex: Int,
    val endIndex: Int,
    val uri: String
)