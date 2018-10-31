package ru.yandex.dunaev.mick.radiostationcatalog

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        CatalogDatabase.init(applicationContext)
    }
}