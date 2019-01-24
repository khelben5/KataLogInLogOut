package com.eduardodev.kataloginlogout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupButtonListener()
    }

    private fun setupButtonListener() {
        loginButton.setOnClickListener { checkForm() }
    }

    private fun checkForm() {
        val userNameText = userName.text.toString()
        val passwordText = password.text.toString()

        if (userNameText == "admin" && passwordText == "admin") {
            Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
        }
    }
}
