package com.jackpan.travelnote_android.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider

class LoginViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    var userLiveData = MutableLiveData<FirebaseUser>()
    val text: LiveData<String> = _text
    var loginDataModel  = LoginDataModel()

    fun getFirebaseUser() : LiveData<FirebaseUser>{
        loginDataModel.checkLoginState(object : LoginDataModel.loginStateResponse {
            override fun getResponseDate(user: FirebaseUser) {
                userLiveData.value = user

            }

        })
        return  userLiveData

    }

    fun getPhoneNumberVerification(phoneNumber : String , activity: Activity){
        loginDataModel.getPhoneNumberVerification(phoneNumber,activity)

    }
    fun setVerifyPhoneNumberWithCode(code : String, activity: Activity){
        loginDataModel.verifyPhoneNumberWithCode(code,activity)
    }







}