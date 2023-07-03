package com.jackpan.travelnote_android.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jackpan.travelnote_android.R

class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModelFactory : LoginViewModelFactory

    val loginViewModel : LoginViewModel by  lazy {
        ViewModelProvider(this,loginViewModelFactory).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModelFactory = LoginViewModelFactory()
        loginViewModel.initPhoneCallback()

        loginViewModel.getPhoneNumberVerification("+886911325323",this)
    }
}