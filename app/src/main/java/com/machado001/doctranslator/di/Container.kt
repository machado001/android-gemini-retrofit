package com.machado001.doctranslator.di

import com.machado001.doctranslator.domain.DocumentTranslator

interface Container {
    val translator: DocumentTranslator
}