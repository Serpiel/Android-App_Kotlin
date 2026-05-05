package com.example.androidapptpcours

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val buttonDash = findViewById<Button>(R.id.buttonDash)

        buttonDash.setOnClickListener {
            onDashButtonClicked()
        }

        val textEmailValue = findViewById<TextView>(R.id.textEmailValue)
        val textName = findViewById<TextView>(R.id.textName)

        val emailSaisi = intent.getStringExtra("EXTRA_EMAIL")
        val nomSaisi = intent.getStringExtra("EXTRA_NAME")

        if (nomSaisi != null) {
            textName.text = nomSaisi
        }
        if (emailSaisi != null) {
            textEmailValue.text = emailSaisi
        }
    }

    private fun onDashButtonClicked() {
        val intent = Intent(
            this,
            DashboardActivity::class.java
        )
        startActivity(intent);
    }
}