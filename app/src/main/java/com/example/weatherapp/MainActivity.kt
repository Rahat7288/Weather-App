package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.weatherapp.model.JsonPlaceHolderApi
import com.example.weatherapp.model.Post
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//    activity_maincreating Gson object ==========
//        val gson = Gson()
////        creating Employee object =============
//        val employee = Employee("Rahat", 27, "rahat@mail.com")
////converting our employee object to json
//        val json = gson.toJson(employee)
//        Log.d("json", json)

//        ===============converting json to Java/kotlin objects==============
//        val json = "{\"firstName\":\"Rahat\",\"age\":23,\"mail\":\"rahat@mail.com\"}"
//        val employee = gson.fromJson(json, Employee::class.java)
//        Log.d("json", employee.toString())

//        =============working with Api============

        textViewResult = findViewById(R.id.text_view_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call = jsonPlaceHolderApi.getPost()

        call.enqueue(object : Callback<MutableList<Post>>{
            override fun onResponse(
                call: Call<MutableList<Post>>,
                response: Response<MutableList<Post>>
            ) {
                if(response.isSuccessful){
                    val posts = response.body()
                    if (posts != null) {
                        for(post in posts){
                            var content = ""
                            content += "ID: " + "${post.id}" + "\n"
                            content += "User ID: " + "${post.userId}" + "\n"
                            content += "Title: " + post.title + "\n"
                            content += "Body Text: " + post.bodyText + "\n\n"

                            textViewResult.append(content)
                        }
                    }else{
                        textViewResult.text = response.code().toString()
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}