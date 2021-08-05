package com.haneet.assignment.data.data_model

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locationMaster" + "")
data class LocationTable(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "User_Id")
    var User_Id: String,

    @ColumnInfo(name = "Lat")
    var Lat: String,

    @ColumnInfo(name = "Lng")
    var Lng: String,

    @ColumnInfo(name = "Loc_Time")
    var Loc_Time: String,

    @ColumnInfo(name = "bearing")
    var bearing: Double = 0.0,

    @ColumnInfo(name = "accuracy")
    var accuracy: Double = 0.0,

    @ColumnInfo(name = "altitute")
    var altitute: Double = 0.0,

    @ColumnInfo(name = "mockLocation")
    var mockLocation: Boolean = false,

    @ColumnInfo(name = "deviceType")
    var deviceType: Int = 0,

    @ColumnInfo(name = "deltaDistance")
    var deltaDistance: Float = 0.0f,

    @ColumnInfo(name = "deltaTime")
    var deltaTime: Int = 0,

    @ColumnInfo(name = "currentTimeStamp")
    var currentTimeStamp: Long = 0


) : BaseObservable() {
    constructor() : this(
        0, "", "", "", "", 0.0, 0.0, 0.0, false, 0, 0.0f, 0, 0
    )

}