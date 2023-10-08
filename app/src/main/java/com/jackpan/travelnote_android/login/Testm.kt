package com.jackpan.travelnote_android.login

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query

interface Testm {

    suspend fun login(
        @Query("username") username: String?,
        @Query("password") password: String?,
    ): Response<UserData?>?
}