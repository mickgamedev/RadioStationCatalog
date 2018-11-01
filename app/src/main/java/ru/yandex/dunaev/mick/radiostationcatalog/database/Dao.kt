package ru.yandex.dunaev.mick.radiostationcatalog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.yandex.dunaev.mick.radiostationcatalog.model.*

@Dao
interface StationModelDao{
    @Query("SELECT * FROM stations")
    fun getAllStations(): List<StationModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStations(stations: List<StationModel>)

    @Query("UPDATE stations SET unsync=1")
    fun clearSync(): Int

    @Query("DELETE FROM stations WHERE unsync=1")
    fun deleteUnsync(): Int
}

@Dao
interface SyncResultDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResult(result: SyncResult)

    @Query("SELECT * FROM sync_result WHERE id=1 LIMIT 1")
    fun getSyncResult(): LiveData<List<SyncResult>>
}

@Dao
interface AdditionalTablesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllCountries(list: List<Countries>)

    @Query("DELETE FROM countries")
    fun deleteAllCountries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllLanguages(list: List<Languages>)

    @Query("DELETE FROM languages")
    fun deleteAllLanguages()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTags(list: List<Tags>)

    @Query("DELETE FROM tags")
    fun deleteAllTags()

    @Transaction
    fun clearAllTables(){
        deleteAllCountries()
        deleteAllLanguages()
        deleteAllTags()
    }

    @Transaction
    fun addTables(countries: List<Countries>, languages: List<Languages>, tags: List<Tags>){
        addAllCountries(countries)
        addAllLanguages(languages)
        addAllTags(tags)
    }
}