package com.jackpan.travelnote_android.ui.dbhelper

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(UserInfo::class)], version = 1)
abstract class  RoomHelper  : RoomDatabase(){
    abstract fun userDao(): UserDao
    companion object{
        private  var instance : RoomHelper? = null
        private  var DB_NAME = ""
        fun getInstance(context: Context ) : RoomHelper{
            return  instance ?: Room.databaseBuilder(context,RoomHelper::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build().also { instance = it }
        }
    }

}