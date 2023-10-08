package com.jackpan.travelnote_android

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.jackpan.travelnote_android.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    var m = MainScope()

    @Inject lateinit var  userMethod: UserMethod
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var array = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_map
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        DaggerCreatureComponent.builder().userModule(UserModule(0)).build().inject(this)
//        userMethod.create()

        runBlocking {
          val a =   async(start = CoroutineStart.LAZY){}
            a.start()
            a.await()
        }
        GlobalScope.launch {



        }
        runBlocking {
            var list  = listOf(666,777,888)
            list.asFlow().filter {
                it > 777
            }
            f().collect{

            }
            val a = flowOf('a', 'b', 'c', 'd')

            val strs = flowOf('a', 'b', 'c', 'd')

            a.zip(strs){
                a,b->
            }
        }
        var f = flowOf(array).fi

    }

    fun getUser(){
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.localizedMessage
        }
        GlobalScope.launch(Dispatchers.Main+handler){}
        GlobalScope.launch {
            get()
            set()
        }
    }

    suspend fun get(): String{
        return withContext(Dispatchers.IO){
            this.toString()

        }


    }
    fun set(){

    }

    fun f () = flow<String> {
        emit()


    }
    fun getIn(i : Int) = flow {
        emit(i)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun main() = runBlocking {
        var job = GlobalScope.launch {

        }
    }
}