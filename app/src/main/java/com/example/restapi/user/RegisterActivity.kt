package com.example.restapi.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.restapi.R
import com.example.restapi.api.ApiServices
import com.example.restapi.api.RegisRequest
import com.example.restapi.api.Retrofit
import com.example.restapi.api.UserResponse
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var c_password: EditText

    private lateinit var linkLogin: TextView

    private lateinit var btnRegis: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        supportActionBar?.hide()

        linkLogin = findViewById(R.id.linkLogin)
        linkLogin.setOnClickListener{
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        email = findViewById(R.id.emailRegis)
        name = findViewById(R.id.nameRegis)
        password = findViewById(R.id.passwordRegis)
        c_password = findViewById(R.id.cPasswordRegis)

        btnRegis = findViewById(R.id.btnRegister)
        btnRegis.setOnClickListener {
            register()

        }
    }

    private fun register() {
        val userRegister = RegisRequest()
        userRegister.email = email.text.toString().trim()
        userRegister.name = name.text.toString().trim()
        userRegister.password = password.text.toString().trim()
        userRegister.c_password = c_password.text.toString().trim()

        val retroRegis = Retrofit().getRetroClientInstance().create(ApiServices::class.java)
        retroRegis.register(userRegister).enqueue(object : retrofit2.Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (userRegister.email!!.isEmpty()) {
                    email.error = "email is required!"
                    email.requestFocus()
                } else if(userRegister.name!!.isEmpty()) {
                    name.error = "name is required!"
                    name.requestFocus()
                } else if (userRegister.password!!.isEmpty()) {
                    password.error = "password is required!"
                    password.requestFocus()

                } else if (userRegister.c_password!!.isEmpty()) {
                    c_password.error = "confirm password is required!"
                    c_password.requestFocus()

                } else if(response.isSuccessful){
                    val regis = response.body()
                    Log.d("name", regis!!.data?.name.toString())
                    Log.d("token", regis!!.data?.token.toString())
                    Toast.makeText(this@RegisterActivity, "successfully registered!", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@RegisterActivity, "failed to register!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "please, try again!", Toast.LENGTH_SHORT).show()
            }

        })
    }
}