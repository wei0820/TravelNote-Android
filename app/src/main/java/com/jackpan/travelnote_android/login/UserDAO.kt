package com.jackpan.travelnote_android.login

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUserData();

}