package com.machado001.doctranslator.di

import android.app.Application
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader

class Application : Application() {

    lateinit var container: Container
    override fun onCreate() {
        super.onCreate()

        container = DiContainer()
        PDFBoxResourceLoader.init(applicationContext);
    }
}