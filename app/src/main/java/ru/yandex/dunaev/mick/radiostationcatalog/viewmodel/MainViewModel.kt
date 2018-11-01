package ru.yandex.dunaev.mick.radiostationcatalog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.yandex.dunaev.mick.radiostationcatalog.model.SyncResult
import ru.yandex.dunaev.mick.radiostationcatalog.model.syncDb

val TAG = "MainViewModel"

class MainViewModel: ViewModel() {

    fun syncNow() = syncDb()

    fun onSyncComplete(sync: SyncResult){
        Log.v(TAG, "${sync.date} insert:${sync.insert} update:${sync.update} delete:${sync.delete}")
        
    }
}