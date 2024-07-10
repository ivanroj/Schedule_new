package com.example.schedule_new

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    var pref : SharedPreferences? = null
    lateinit var group : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

        val groupEditText = findViewById<EditText>(R.id.groupEditText)
        val groupTextView = findViewById<TextView>(R.id.groupTextView)
        val confirmButton = findViewById<Button>(R.id.confirmButton)

        isFirstAppOpen()

        pref = getSharedPreferences("DATA", Context.MODE_PRIVATE)

        confirmButton.setOnClickListener {
            group = groupEditText.text.toString()
            if (isItGroup(group)){
                saveData(group)
                groupTextView.text = group
                val intentToMain = Intent(this,MainActivity::class.java)
                startActivity(intentToMain)
            }
            else{
                groupTextView.text = "Неверная группа"
            }
        }
    }
    fun saveData(dataToSave : String){
        val editor = pref?.edit()
        editor?.putString("group",dataToSave)
        editor?.apply()
    }
    fun isItGroup(group:String) : Boolean{
        val patternRegexGroup = """[Мм][1-9иИсС][01234]?О-\d\d\d[А-Яа-я]-2\d"""
        val regex = Regex(patternRegexGroup)
        return regex.matches(group.trim())
    }
    fun isFirstAppOpen(){
        if(pref?.getString("group","none") != "none"){
            val intentToMain = Intent(this,MainActivity::class.java)
            startActivity(intentToMain)
        }
    }
}