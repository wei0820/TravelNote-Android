package com.jackpan.travelnote_android

import dagger.Component

@Component(modules =[UserModule::class])
interface CreatureComponent {
    fun inject(activity: MainActivity);
}