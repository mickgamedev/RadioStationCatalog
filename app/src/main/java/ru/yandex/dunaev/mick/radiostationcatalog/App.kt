package ru.yandex.dunaev.mick.radiostationcatalog

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import ru.yandex.dunaev.mick.radiostationcatalog.database.CatalogDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        CatalogDatabase.init(applicationContext)
        AndroidThreeTen.init(this);
    }
}