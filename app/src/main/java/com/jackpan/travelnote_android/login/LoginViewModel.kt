package com.jackpan.travelnote_android.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    var userLiveData = MutableLiveData<FirebaseUser>()
    val text: LiveData<String> = _text
    var loginDataModel = LoginDataModel()

    var cehckBoolean = MutableLiveData<String>()
    var userData = MutableLiveData<FirebaseUser>()

    fun getFirebaseUser(): LiveData<FirebaseUser> {
        loginDataModel.checkLoginState(object : LoginDataModel.loginStateResponse {
            override fun getResponseDate(user: FirebaseUser) {
                viewModelScope.launch {
                    userLiveData.value = user

                }

            }

        })
        return userLiveData

    }

    fun getPhoneNumberVerification(phoneNumber: String, activity: Activity): LiveData<String> {
        viewModelScope.launch {
            loginDataModel.getPhoneNumberVerification(
                phoneNumber,
                activity,
                object : LoginDataModel.GetPhoneNumberVerificationResponse {
                    override fun getVerificationResponse(s: String) {
                        cehckBoolean.value = s

                    }

                })

        }
        return cehckBoolean

    }

    fun setVerifyPhoneNumberWithCode(code: String,key:String): LiveData<FirebaseUser> {
        loginDataModel.verifyPhoneNumberWithCode(code, key,object : LoginDataModel.GetUserResponse {
            override fun getUser(user: FirebaseUser) {
                viewModelScope.launch {
                    if (user != null) {
                        userData.value = user

                    }

                }
            }
        })

        return  userData
    }

}