package com.example.bootcampapp3

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bootcampapp3.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


import android.Manifest
import com.example.bootcampapp3.RetrofitClient.getInstance

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the root view

        binding.button.setOnClickListener {
            val retrofit = RetrofitClient.getInstance()

            val apiInterface = retrofit.create(GenAiAPI::class.java)

            val message = binding.inputet.text.toString()

            val call: Call<JsonObject> = apiInterface.getResponse(binding.inputet.text.toString())

            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        Log.d("MainActivity", "Response: $responseBody")
                        Toast.makeText(this@MainActivity, "Result: $responseBody", Toast.LENGTH_SHORT)
                            .show()
                        binding.outputtv.text = responseBody.toString()
                    } else {
                        Log.d("MainActivity", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    try {
                        t.printStackTrace()
                        binding.outputtv.text = t.toString()
                        Log.d("MainActivity", "Error: ${t.message}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.d("MainActivity", "Exception in error handling: ${e.message}")
                    }

                }
            })
        }

    }


}