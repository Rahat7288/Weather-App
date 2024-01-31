package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.weatherapp.model.Comments
import com.example.weatherapp.model.JsonPlaceHolderApi
import com.example.weatherapp.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi
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


//        =========POST request with retrofit===============

        textViewResult = findViewById(R.id.text_view_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

//       getPosts(retrofit)
//        getComments(retrofit)
//        createPost()
        updatepost()

//        =========POST request with retrofit ends ====================
    }
    private fun getPosts() {
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call = jsonPlaceHolderApi.getPost(2, "id", "desc")

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

    private fun getComments(){
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call = jsonPlaceHolderApi.getComments(4)

        call.enqueue(object : Callback<MutableList<Comments>>{
            override fun onResponse(
                call: Call<MutableList<Comments>>,
                response: Response<MutableList<Comments>>
            ) {
                if(response.isSuccessful){
                    val comments = response.body()
                    if(comments != null){
                        for(comment in comments){
                            var content = ""
                            content += "ID: " + comment.id + "\n"
                            content += "Post Id" + comment.postId + "\n"
                            content += "Name" + comment.name + "\n"
                            content += "Email" + comment.emil + "\n"
                            content += "Text" + comment.text + "\n\n"

                            textViewResult.append(content)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<Comments>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun createPost(){
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val post = Post(userId = 20, "new title","next Text")
        val call = jsonPlaceHolderApi.createPost(post)

        call.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(response.isSuccessful){
                    var content = ""
                    content += "Code: " + response.code() + "\n"
                    content += "ID: " + "${post.id}" + "\n"
                    content += "User ID: " + "${post.userId}" + "\n"
                    content += "Title: " + post.title + "\n"
                    content += "Body Text: " + post.bodyText + "\n\n"
                    textViewResult.append(content)
                }else{
                    textViewResult.text = response.code().toString()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun updatepost(){
        val post = Post(25, null, "how are your?")
        val call = jsonPlaceHolderApi.putPost(5, post)

        call.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(response.isSuccessful){
                    var content = ""
                    content += "Code: " + response.code() + "\n"
                    content += "ID: " + "${post.id}" + "\n"
                    content += "User ID: " + "${post.userId}" + "\n"
                    content += "Title: " + post.title + "\n"
                    content += "Body Text: " + post.bodyText + "\n\n"
                    textViewResult.append(content)
                }else{
                    textViewResult.text = response.code().toString()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun deletePost(){
        val call = jsonPlaceHolderApi.deletePost(25)
        call.enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful){
                    textViewResult.text = response.code().toString()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

        })
    }



}