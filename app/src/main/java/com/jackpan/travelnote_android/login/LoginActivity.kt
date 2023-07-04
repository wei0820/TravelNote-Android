package com.jackpan.travelnote_android.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.jackpan.travelnote_android.R

class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModelFactory : LoginViewModelFactory
    lateinit var mPhoneEdt : EditText
    lateinit var mVerificationEdt : EditText
    lateinit var mLoginButton: Button
    lateinit var mGetButton: Button

    val loginViewModel : LoginViewModel by  lazy {
        ViewModelProvider(this,loginViewModelFactory).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModelFactory = LoginViewModelFactory()
        initLayout()
        mGetButton.setOnClickListener {
            var phone = mPhoneEdt.text.toString()
           var new =  phone.replaceFirst("0","+886")
            loginViewModel.getPhoneNumberVerification(new,this)

        }
        mLoginButton.setOnClickListener {
            var code = mVerificationEdt.text.toString()
            loginViewModel.setVerifyPhoneNumberWithCode(code,this)

        }
    }

    fun initLayout(){
        mPhoneEdt = findViewById(R.id.edt)
        mVerificationEdt = findViewById(R.id.edt2)
        mLoginButton = findViewById(R.id.loginbtn)
        mGetButton =  findViewById(R.id.getbtn)



    }
}