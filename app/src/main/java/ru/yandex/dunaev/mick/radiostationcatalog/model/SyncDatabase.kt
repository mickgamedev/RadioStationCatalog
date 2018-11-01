package ru.yandex.dunaev.mick.radiostationcatalog.model

import android.content.Context
import android.util.Log
import androidx.work.*
import ru.yandex.dunaev.mick.radiostationcatalog.database.*
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
            val list = RadioBrowserApi.getApi().getStationList().execute().body()!!
            val countries = RadioBrowserApi.getApi().getCountiesList().execute().body()!!
            val languages = RadioBrowserApi.getApi().getLanguagesList().execute().body()!!
            val tags = RadioBrowserApi.getApi().getTagsList().execute().body()!!

            with(CatalogDatabase) {
                clearAdditionalTables()
                clearStationsSync()
                addStationsToCatalog(list)
                deleteUnsync()
                addAdditionalTables(countries, languages, tags)
                saveSyncResult()
            }
        } catch (err: Exception) {
            Log.e(TAG,err.message)
            return Result.FAILURE
        }
        return Result.SUCCESS
    }
}

