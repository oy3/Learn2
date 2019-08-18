package com.example.learn

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInBtn = findViewById(R.id.signIn) as TextView

        signInBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val signUpBtn = findViewById(R.id.signUp) as TextView

        signUpBtn.setOnClickListener {
            val intent = Intent(baseContext, SignUpActivity::class.java)
            startActivity(intent)
        }




        next.setOnClickListener {
            val password = verificationCode.text.toString()
            val email = email.text.toString()
            val user = User(email, password)

            provideGitHubApi().createAccount(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    Toast.makeText(this@MainActivity, "Success: Email is ${email} and Password is ${password}", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<User>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })

        }

  /*      provideGitHubApi().reposForUser("user").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                liveData.value = response.body()
            }
            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error: ", Toast.LENGTH_SHORT).show()
            }
        })

*/
    }

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://52.2.153.12:50055/v1/")
            .addConverterFactory(GsonConverterFactory.create())
//                .client(provideOkHttpClient())
            .build()
    }

    fun provideGitHubApi(): ApiClient {
        return provideRetrofit().create(ApiClient::class.java)
    }
}

