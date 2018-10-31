package ru.yandex.dunaev.mick.radiostationcatalog.model

import android.content.Context
import androidx.room.*
import androidx.room.OnConflictStrategy
import org.threeten.bp.LocalDateTime


@Entity(tableName = "stations")
data class StationModel(
    @PrimaryKey var id: Int,
    var changeuuid:String,
    var stationuuid:String,
    var name:String,
    var url:String,
    var favicon:String,
    var tags:String,
    var country:String,
    var language:String,
    var votes:String,
    var negativevotes:String,
    var ip:String,
    var codec:String,
    var bitrate:String,
    var sync:Boolean)

@Entity(tableName = "sync_result")
data class SyncResult(
    @PrimaryKey var id: Int,
    var date: String,
    var insert: Int,
    var update: Int,
    var delete: Int)

@Dao
interface StationModelDao{
    @Query("SELECT * FROM stations")
    fun getAllStations(): List<StationModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStations(stations: List<StationModel>)

    @Query("UPDATE stations SET sync=0")
    fun clearSync(): Int

    @Query("DELETE FROM stations WHERE sync=0")
    fun deleteUnsync(): Int
}

@Dao
interface SyncResultDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResult(result: SyncResult)

    @Query("SELECT * FROM sync_result WHERE id=1")
    fun getSyncResult(): List<SyncResult>
}

@Database(entities = arrayOf(StationModel::class, SyncResult::class), version = 1, exportSchema = false)
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
    getInstance().syncResultDao().saveResult(
        SyncResult(
            1,
            LocalDateTime.now().toString(),
            insert,
            update,
            delete
        )
    )
}



