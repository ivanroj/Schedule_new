package com.example.schedule_new

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.schedule_new.data.DataManager
import com.example.schedule_new.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var pref : SharedPreferences? = null
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        pref = getSharedPreferences("DATA", Context.MODE_PRIVATE)
        DataManager.groupNumber = pref?.getString("group","none").toString() // запись группы в DataManager

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }
    fun saveData(dataToSave : String){
        val editor = pref?.edit()
        editor?.putString("group",dataToSave)
        editor?.apply()
    }
}