package com.example.androidapptpcours

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var titleWelcome: TextView
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var contentWelcome: TextView
    private lateinit var imageContent: ImageView
    private lateinit var contentWelcome2: TextView
    private lateinit var placeholder: TextView
    private lateinit var placeholder2: TextView
    private lateinit var placeholder3: TextView
    private lateinit var placeholder4: TextView
    private lateinit var placeholder5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)
        titleWelcome = findViewById(R.id.titleWelcome)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        contentWelcome = findViewById(R.id.contentWelcome)
        imageContent = findViewById(R.id.imageContent)
        contentWelcome2 = findViewById(R.id.contentWelcome2)
        placeholder = findViewById(R.id.placeholder)
        placeholder2 = findViewById(R.id.placeholder2)
        placeholder3 = findViewById(R.id.placeholder3)
        placeholder4 = findViewById(R.id.placeholder4)
        placeholder5 = findViewById(R.id.placeholder5)

        loginButton.setOnClickListener {
            onLoginButtonClicked()
        }

        registerButton.setOnClickListener {
            onRegisterButtonClicked()
        }
    }

    private fun onLoginButtonClicked() {
        val intent = Intent(
            this,
            LoginActivity::class.java
        )
        startActivity(intent);
    }
    private fun onRegisterButtonClicked() {
        val intent = Intent(
            this,
            RegisterActivity::class.java
        )
        startActivity(intent);
    }
}