package com.jackpan.travelnote_android.login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1)
abstract class UserDB :RoomDatabase() {
    abstract fun userDao() : UserDAO
    companion object{
        private var instance : UserDB? = null
        private var DB_NAME = "db_name"
        fun getInstance(context :Context) : UserDB{
            return instance ?: Room.databaseBuilder(context,UserDB::class.java, DB_NAME).fallbackToDestructiveMigration().build().also {
                instance = it 
            }
        }

    }




}