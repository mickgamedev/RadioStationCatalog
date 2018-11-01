package ru.yandex.dunaev.mick.radiostationcatalog.database

import android.content.Context
import androidx.room.*
import org.threeten.bp.LocalDateTime
import ru.yandex.dunaev.mick.radiostationcatalog.model.StationModel
import ru.yandex.dunaev.mick.radiostationcatalog.model.SyncResult

@Database(entities = arrayOf(StationModel::class, SyncResult::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CatalogDatabase : RoomDatabase(){
    abstract fun stationModelDao(): StationModelDao
    abstract fun syncResultDao(): SyncResultDao

    companion object {
        var oldSize = 0
        var delete = 0
        var newSize = 0
        private var INSTANCE: CatalogDatabase? = null
        fun getInstance() = INSTANCE!!
        fun init(context: Context){
            INSTANCE = Room.databaseBuilder(context, CatalogDatabase::class.java, "catalog.db").build()
        }
    }
}

fun CatalogDatabase.Companion.addStationsToCatalog(list: List<StationModel>) {
    getInstance().stationModelDao().addStations(list)
    newSize = list.size
}

fun CatalogDatabase.Companion.getAllStations() = getInstance().stationModelDao().getAllStations()
fun CatalogDatabase.Companion.clearStationsSync() { oldSize = getInstance().stationModelDao().clearSync() }
fun CatalogDatabase.Companion.deleteUnsync() { delete = getInstance().stationModelDao().deleteUnsync() }

fun CatalogDatabase.Companion.saveSyncResult() {
    val update = oldSize - delete
    val insert = newSize - update
    getInstance().syncResultDao().saveResult(SyncResult(1,LocalDateTime.now(),insert,update,delete))
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? = LocalDateTime.parse(value)
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? = date.toString()
}
