package ru.yandex.dunaev.mick.radiostationcatalog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.yandex.dunaev.mick.radiostationcatalog.database.CatalogDatabase
import ru.yandex.dunaev.mick.radiostationcatalog.database.getSyncResult
import ru.yandex.dunaev.mick.radiostationcatalog.model.SyncResult
import ru.yandex.dunaev.mick.radiostationcatalog.model.syncDb

val TAG = "MainViewModel"

class MainViewModel: ViewModel() {
    val countries = "Country"
    val languages = "Language"
    val tags = "tags"

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val disp = CatalogDatabase.getSyncResult().subscribe{v -> onSyncComplete(v[0])}

    fun syncNow() = syncDb()

    fun onSyncComplete(sync: SyncResult){
        Log.v(TAG, "${sync.date} insert:${sync.insert} update:${sync.update} delete:${sync.delete}")
        uiScope.launch{
            withContext(Dispatchers.IO){
                delay(1000L)
                Log.v(TAG,"Hello form io")
            }
            Log.v(TAG,"Hello world")
        }
        Log.v(TAG,"Yahoo")

    }

    override fun onCleared() {
        disp.dispose()
        viewModelJob.cancel()
    }
}