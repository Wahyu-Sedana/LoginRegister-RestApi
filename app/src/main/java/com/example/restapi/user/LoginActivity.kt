package com.example.restapi.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.restapi.MainActivity
import com.example.restapi.R
import com.example.restapi.api.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity(){

    private lateinit var btnLogin: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var linkRegis: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        linkRegis = findViewById(R.id.linkRegis)
        linkRegis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
            login()
        }
    }

    private fun login() {
        val request = UserRequest()
        request.email = email.text.toString().trim()
        request.password = password.text.toString().trim()

        val retro = Retrofit().getRetroClientInstance().create(ApiServices::class.java)
        retro.login(request).enqueue(object : retrofit2.Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                val userResponse = response.body()
//                Log.d("name", userResponse!!.data?.name.toString())
//                Log.d("token", userResponse!!.data?.token.toString())

                // validasi untuk setiap field
                if(request.email!!.isEmpty()){
                    email.error = "email is required!"
                    email.requestFocus()
                }else if(request.password!!.isEmpty()){
                    password.error = "password is required!"
                    password.requestFocus()
                }else if(response.isSuccessful){
                    val user = response.body()
                    // melihat data lewat logcat
                    Log.d("name", user!!.data?.name.toString())
                    Log.d("token", user!!.data?.token.toString())
                    // jika berhasil login maka ada pesan login berhasil, dan otomatis pindah activity
                    Toast.makeText(this@LoginActivity, "login successfull!", Toast.LENGTH_SHORT).show()
                    intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    // pesan jika ada kesalahan login
                    Toast.makeText(this@LoginActivity, "login failed!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "please, try again!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}