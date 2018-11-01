package ru.yandex.dunaev.mick.radiostationcatalog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.yandex.dunaev.mick.radiostationcatalog.model.StationModel
import ru.yandex.dunaev.mick.radiostationcatalog.model.SyncResult

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
