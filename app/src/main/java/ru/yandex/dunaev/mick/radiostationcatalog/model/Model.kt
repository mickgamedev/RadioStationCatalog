package ru.yandex.dunaev.mick.radiostationcatalog.model

import androidx.room.*
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
    @PrimaryKey
    var id: Int,
    var date: LocalDateTime,
    var insert: Int,
    var update: Int,
    var delete: Int)
