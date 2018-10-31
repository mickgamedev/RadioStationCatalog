package ru.yandex.dunaev.mick.radiostationcatalog

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
        val list: List<StationModel>?
        try {
            list = RadioBrowserApi.create().getStationList().execute().body()
            addStationsToCatalog(list!!)
        } catch (err: Exception) {
            Log.e(TAG,err.message)
            return Result.FAILURE
        }
        return Result.SUCCESS
    }
}

