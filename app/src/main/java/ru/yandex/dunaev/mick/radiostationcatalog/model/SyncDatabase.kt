package ru.yandex.dunaev.mick.radiostationcatalog.model

import android.content.Context
import android.util.Log
import androidx.work.*
import java.lang.Exception

internal val workName = "sync database"
internal val TAG = "SYNC DATABASE"

fun syncDb(){
    val syncWorker = OneTimeWorkRequest.Builder(SyncDatabaseWorker::class.java).build()
    WorkManager.getInstance()
        .beginUniqueWork(workName, ExistingWorkPolicy.KEEP, syncWorker)
        .enqueue()
}

class SyncDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        try {
            val list = RadioBrowserApi.create().getStationList().execute().body()!!
            list.forEach { it.sync = true }
            with(CatalogDatabase) {
                clearStationsSync()
                addStationsToCatalog(list)
                deleteUnsync()
                saveSyncResult()
            }
        } catch (err: Exception) {
            Log.e(TAG,err.message)
            return Result.FAILURE
        }
        return Result.SUCCESS
    }
}

