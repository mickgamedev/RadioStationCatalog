package ru.yandex.dunaev.mick.radiostationcatalog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.yandex.dunaev.mick.radiostationcatalog.database.CatalogDatabase
import ru.yandex.dunaev.mick.radiostationcatalog.database.getSyncResult
import ru.yandex.dunaev.mick.radiostationcatalog.model.SyncResult
import ru.yandex.dunaev.mick.radiostationcatalog.model.syncDb

val TAG = "MainViewModel"

class MainViewModel: ViewModel() {
    val countries = "Countries"
    val languages = "Languages"
    val tags = "tags"

    val disp = CatalogDatabase.getSyncResult().subscribe{v -> onSyncComplete(v[0])}

    fun syncNow() = syncDb()

    fun onSyncComplete(sync: SyncResult){
        Log.v(TAG, "${sync.date} insert:${sync.insert} update:${sync.update} delete:${sync.delete}")
    }

    override fun onCleared() {
        disp.dispose()
    }
}