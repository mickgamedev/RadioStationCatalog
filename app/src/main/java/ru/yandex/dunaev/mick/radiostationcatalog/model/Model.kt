package ru.yandex.dunaev.mick.radiostationcatalog.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "stations")
data class StationModel(
    @PrimaryKey val id: Int,
    val changeuuid:String,
    val stationuuid:String,
    val name:String,
    val url:String,
    val favicon:String,
    val tags:String,
    val country:String,
    val language:String,
    val votes:String,
    val negativevotes:String,
    val ip:String,
    val codec:String,
    val bitrate:String,
    val unsync:Boolean)

@Entity(tableName = "sync_result")
data class SyncResult(
    @PrimaryKey
    val id: Int,
    val date: LocalDateTime,
    val insert: Int,
    val update: Int,
    val delete: Int)

open class BaseEntity{
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name = ""
    var value = ""
    var stationcount = 0
}

@Entity(tableName = "countries")
class  Country: BaseEntity()

@Entity(tableName = "languages")
class  Language: BaseEntity()

@Entity(tableName = "tags")
class  Tag: BaseEntity()
