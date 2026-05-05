package com.example.androidapptpcours

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TP02"
    }

    private lateinit var textHomeMessage: TextView
    private lateinit var buttonStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        textHomeMessage = findViewById(R.id.textHomeMessage)
        buttonStart = findViewById(R.id.buttonStart)

        // Configure l'action du bouton.
        buttonStart.setOnClickListener {
            onStartButtonClicked()
        }

        Log.d(TAG, "MainActivity créée")
    }

    private fun onStartButtonClicked() {
        val intent = Intent(
            this,
            WelcomeActivity::class.java
        )
        startActivity(intent);

        textHomeMessage.text = getString(R.string.home_success_message)

        Toast.makeText(
            this,
            getString(R.string.toast_button_clicked),
            Toast.LENGTH_SHORT
        ).show()

        Log.d(TAG, "Bouton cliqué - message mis à jour")
    }


}