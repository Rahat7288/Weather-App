package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//    activity_maincreating Gson object ==========
        val gson = Gson()
////        creating Employee object =============
//        val employee = Employee("Rahat", 27, "rahat@mail.com")
////converting our employee object to json
//        val json = gson.toJson(employee)
//        Log.d("json", json)

//        ===============converting json to Java/kotlin objects==============
        val json = "{\"firstName\":\"Rahat\",\"age\":23,\"mail\":\"rahat@mail.com\"}"
        val employee = gson.fromJson(json, Employee::class.java)
        Log.d("json", employee.toString())
    }
}