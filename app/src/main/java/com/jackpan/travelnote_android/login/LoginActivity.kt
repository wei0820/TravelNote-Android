package com.jackpan.travelnote_android.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jackpan.travelnote_android.R

class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModelFactory : LoginViewModelFactory
    lateinit var mPhoneEdt : EditText
    lateinit var mLoginButton: Button

    val loginViewModel : LoginViewModel by  lazy {
        ViewModelProvider(this,loginViewModelFactory).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModelFactory = LoginViewModelFactory()
        initLayout()
        mLoginButton.setOnClickListener {
            closeKeyBoard()
            var phone = mPhoneEdt.text.toString()
            var new =  phone.replaceFirst("0","+886")
            loginViewModel.getPhoneNumberVerification(new,this).observe(this, Observer {
                Log.d("Jack",it.toString())
                var intent = Intent()
                var bundle = Bundle()
                bundle.putString("key",it)
                intent.putExtras(bundle)
                intent.setClass(this,VerifyPhoneNumberWithCodeActivity::class.java)
                startActivity(intent)
                this.finish()
            })

        }
    }

    fun initLayout(){
        mPhoneEdt = findViewById(R.id.edt)
        mLoginButton = findViewById(R.id.loginbtn)

      UserData().apply {
            this.name = ""
            this.age = 20
        }.also {

      }.getAge()

        }




    }
    private fun closeKeyBoard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}