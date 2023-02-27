package com.jackpan.travelnote_android.ui.dbhelper

import android.media.tv.TvTrackInfo
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey var id : Int  = 0,
    var name : String = ""
    

)
