package com.jackpan.travelnote_android

import dagger.Module
import dagger.Provides

@Module
class UserModule (val type :Int) {

    companion object{
        const val  guest : Int = 0
        const val  member : Int = 1
    }


    @Provides
    fun  UserCrete():UserMethod{
        return when(type){
            guest ->  Guset()
            member -> Member()
            else -> {
                Member()
            }
        }
    }
}