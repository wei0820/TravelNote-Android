package com.jackpan.travelnote_android.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.ktx.Firebase
import com.jackpan.travelnote_android.MainActivity
import io.realm.kotlin.internal.platform.freeze
import io.realm.kotlin.internal.platform.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class LoginDataModel {
    private  val TAG = "LoginDataModel"
    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    var stateFlow = MutableStateFlow<String>("")
    var sharedFlow = MutableSharedFlow<String>()


     var mAuth: FirebaseAuth? = null
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    interface loginStateResponse{
        fun  getResponseDate(user : FirebaseUser)

    }
    interface  GetPhoneNumberVerificationResponse{
        fun  getVerificationResponse(s:String)
    }

    interface  GetUserResponse{
        fun  getUser(user: FirebaseUser)

    }

   suspend fun main(){
       runBlocking {
           flow<String> {

           }.flowOn(Dispatchers.IO){}}
       }


        stateFlow.value
        stateFlow.collect{
            println(it)

        }
        val job =  GlobalScope.launch {

        }
      job.cancel()

       flow<String> {  }.retryWhen { cause, attempt ->
           return@retryWhen cause is Exception && attempt < 3
       }



    }



    fun  checkLoginState() = callbackFlow<
            String> {
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {

            trySend(currentUser.uid)

        }else{

        }
        awaitClose {

        }

    }
    fun coldFlow()  = coldFlow()

    fun  get(){
        runBlocking {
            checkLoginState().collect{

            }
        }
    }




//    fun checkLoginState() : String{
//        mAuth = FirebaseAuth.getInstance()
//        val currentUser = mAuth!!.currentUser
//        if (currentUser != null) {
//
//
//        }else{
//
//        }
//       return  currentUser!!.uid
//
//    }


//
//    fun checkLoginState(response: loginStateResponse){
//        mAuth = FirebaseAuth.getInstance()
//        val currentUser = mAuth!!.currentUser
//        if (currentUser != null) {
//            response.getResponseDate(currentUser)
//
//
//        }else{
//
//        }
//
//
//    }


    fun getPhoneNumberVerification(phoneNumber : String , activity: Activity,getPhoneNumberVerificationResponse: GetPhoneNumberVerificationResponse){
        mAuth = FirebaseAuth.getInstance()


        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // This callback will be invoked in two situations:
                    // 1 - Instant verification. In some cases the phone number can be instantly
                    //     verified without needing to send or enter a verification code.
                    // 2 - Auto-retrieval. On some devices Google Play services can automatically
                    //     detect the incoming verification SMS and perform verification without
                    //     user action.
                    Log.d(TAG, "onVerificationCompleted:$credential")

                    signInWithPhoneAuthCredential(credential,activity)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // This callback is invoked in an invalid request for verification is made,
                    // for instance if the the phone number format is not valid.
                    Log.w(TAG, "onVerificationFailed", e)

                    if (e is FirebaseAuthInvalidCredentialsException) {
                        // Invalid request
                    } else if (e is FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                    } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                        // reCAPTCHA verification attempted with null Activity
                    }

                    // Show a message and update the UI
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken,
                ) {
                    // The SMS verification code has been sent to the provided phone number, we
                    // now need to ask the user to enter the code and then construct a credential
                    // by combining the code with a verification ID.
                    Log.d(TAG, "onCodeSent:$verificationId")

                    // Save verification ID and resending token so we can use them later
                    storedVerificationId = verificationId
                    resendToken = token
                    getPhoneNumberVerificationResponse.getVerificationResponse(storedVerificationId!!)

                }

            }) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, activity: Activity) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d(TAG, "signInWithCredential:success")
                val user = it.result?.user

                activity.startActivity(Intent(activity,MainActivity::class.java))
                activity.finish()

            }else{
                Log.w(TAG, "signInWithCredential:failure", it.exception)
                if (it.exception is FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                }
            }
        }

    }

        fun verifyPhoneNumberWithCode(code: String,key : String,getUserResponse: GetUserResponse) {
            mAuth = FirebaseAuth.getInstance()
            // [START verify_with_code]

            val credential = PhoneAuthProvider.getCredential(key, code)
        // [END verify_with_code]
            mAuth!!.signInWithCredential(credential).addOnCompleteListener {
                if(it.isSuccessful){
                    Log.d(TAG, "signInWithCredential:success")
                    val user = it.result?.user
                    getUserResponse.getUser(user!!)



                }else{
                    Log.w(TAG, "signInWithCredential:failure", it.exception)
                    if (it.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }    }

}