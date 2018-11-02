package ru.yandex.dunaev.mick.radiostationcatalog.database

import android.content.Context
import androidx.room.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDateTime
import ru.yandex.dunaev.mick.radiostationcatalog.model.*

@Database(entities = arrayOf(StationModel::class,
    SyncResult::class,
    Country::class,
    Language::class,
    Tag::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CatalogDatabase : RoomDatabase(){
    abstract fun stationModelDao(): StationModelDao
    abstract fun syncResultDao(): SyncResultDao
    abstract fun additionalTables(): AdditionalTablesDao

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
fun CatalogDatabase.Companion.clearAdditionalTables() = getInstance().additionalTables().clearAllTables()
fun CatalogDatabase.Companion.addAdditionalTables(countries: List<Country>, languages: List<Language>, tags: List<Tag>)
        = getInstance().additionalTables().addTables(countries, languages, tags)
fun CatalogDatabase.Companion.getSyncResult() = getInstance().syncResultDao().getSyncResult().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun CatalogDatabase.Companion.getAllCountries() = getInstance().additionalTables().getAllCountries()
fun CatalogDatabase.Companion.getAllLanguages() = getInstance().additionalTables().getAllLanguages()
fun CatalogDatabase.Companion.getAllTags() = getInstance().additionalTables().getAllTags()
fun CatalogDatabase.Companion.getStationsWithFilter(filter: String): List<StationModel>{
    val type = filter.substringBefore('=')
    val value = filter.substringAfter('\'').substringBefore('\'')
    when(type){
        "country" -> return getInstance().stationModelDao().getStationsWithFilterCountry(value)
        "language" -> return getInstance().stationModelDao().getStationsWithFilterLanguage("%$value%")
        "tags" -> return getInstance().stationModelDao().getStationsWithFilterTags("%$value%")
    }
    return listOf()
}

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
