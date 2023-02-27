package com.jackpan.travelnote_android.ui.dbhelper

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [(UserInfo::class)], version = 1)
abstract class  RoomHelper  : RoomDatabase(){
    abstract fun userDao(): UserDao

}