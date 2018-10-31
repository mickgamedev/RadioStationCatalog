package ru.yandex.dunaev.mick.radiostationcatalog

import android.content.Context
import androidx.room.*

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
    var bitrate:String
)

fun addStationsToCatalog(list: List<StationModel>) = CatalogDatabase.getInstance().stationModelDao().addStations(list)
fun getAllStations() = CatalogDatabase.getInstance().stationModelDao().getAllStations()

@Dao
interface StationModelDao{
    @Query("SELECT * FROM stations")
    fun getAllStations(): List<StationModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStations(stations: List<StationModel>)
}

@Database(entities = arrayOf(StationModel::class), version = 1, exportSchema = false)
abstract class CatalogDatabase : RoomDatabase(){
    abstract fun stationModelDao(): StationModelDao

    companion object {
        private var INSTANCE: CatalogDatabase? = null
        fun getInstance() = INSTANCE!!
        fun init(context: Context){
            INSTANCE = Room.databaseBuilder(context, CatalogDatabase::class.java, "catalog.db").build()
        }
    }
}