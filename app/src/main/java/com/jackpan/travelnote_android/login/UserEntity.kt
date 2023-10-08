package com.jackpan.travelnote_android.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "user_id")  var id :String,
    @ColumnInfo(name = "user_name") var userName : String,


)