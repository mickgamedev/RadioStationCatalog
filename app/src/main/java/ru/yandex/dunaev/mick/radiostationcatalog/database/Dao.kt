package ru.yandex.dunaev.mick.radiostationcatalog.database

import androidx.room.*
import io.reactivex.Observable
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

    @Query("SELECT * FROM stations WHERE country=:filter ORDER BY votes DESC")
    fun getStationsWithFilterCountry(filter: String): List<StationModel>

    @Query("SELECT * FROM stations WHERE language LIKE :filter ORDER BY votes DESC")
    fun getStationsWithFilterLanguage(filter: String): List<StationModel>

    @Query("SELECT * FROM stations WHERE tags LIKE :filter ORDER BY votes DESC")
    fun getStationsWithFilterTags(filter: String): List<StationModel>
}

@Dao
interface SyncResultDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResult(result: SyncResult)

    @Query("SELECT * FROM sync_result WHERE id=1 LIMIT 1")
    fun getSyncResult(): Observable<List<SyncResult>>
}

@Dao
interface AdditionalTablesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllCountries(list: List<Country>)

    @Query("DELETE FROM countries")
    fun deleteAllCountries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllLanguages(list: List<Language>)

    @Query("DELETE FROM languages")
    fun deleteAllLanguages()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTags(list: List<Tag>)

    @Query("DELETE FROM tags")
    fun deleteAllTags()

    @Transaction
    fun clearAllTables(){
        deleteAllCountries()
        deleteAllLanguages()
        deleteAllTags()
    }

    @Transaction
    fun addTables(countries: List<Country>, languages: List<Language>, tags: List<Tag>){
        addAllCountries(countries)
        addAllLanguages(languages)
        addAllTags(tags)
    }

    @Query("SELECT * FROM countries ORDER BY stationcount DESC")
    fun getAllCountries(): List<Country>

    @Query("SELECT * FROM languages ORDER BY stationcount DESC")
    fun getAllLanguages(): List<Language>

    @Query("SELECT * FROM tags ORDER BY stationcount DESC")
    fun getAllTags(): List<Tag>
}