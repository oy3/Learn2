package com.example.learn

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signInBtn = findViewById(R.id.signIn) as TextView

        signInBtn.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        val vSignInBtn = findViewById(R.id.vsignIn) as TextView

        vSignInBtn.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        val nextBtn = findViewById(R.id.next) as Button

        nextBtn.setOnClickListener {
            val intent = Intent(baseContext, SetPassword::class.java)
            startActivity(intent)
        }



    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://52.2.153.12:50055/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideGitHubApi(): ApiClient {
        return provideRetrofit().create(ApiClient::class.java)
    }

}
