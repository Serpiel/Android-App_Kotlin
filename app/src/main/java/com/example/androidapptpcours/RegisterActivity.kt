package com.example.androidapptpcours

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TP04_REGISTER"
        private const val MIN_PASSWORD_LENGTH = 6
    }

    private lateinit var editTextFullName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var checkBoxTerms: CheckBox
    private lateinit var buttonRegister: Button
    private lateinit var buttonClear: Button
    private lateinit var textFormResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        // On récupère les vues avant de brancher les listeners.
        bindViews()
        configureListeners()

        Log.d(TAG, "Écran d'inscription créé")
    }

    private fun bindViews() {
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        checkBoxTerms = findViewById(R.id.checkBoxTerms)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonClear = findViewById(R.id.buttonClear)
        textFormResult = findViewById(R.id.textFormResult)
    }

    private fun configureListeners() {
        buttonRegister.setOnClickListener {
            // Le clic déclenche la future validation complète du formulaire.
            onRegisterClicked()
        }

        buttonClear.setOnClickListener {
            // Séparer le nettoyage du formulaire rend le code plus maintenable.
            clearForm()
        }
    }
    private fun onRegisterSuccess(formData: RegisterFormData) {
        textFormResult.text = getString(R.string.success_register)
        textFormResult.setTextColor(getColor(R.color.register_text_success))

        Toast.makeText(
            this,
            getString(R.string.toast_register_success),
            Toast.LENGTH_SHORT
        ).show()

        Log.d(TAG, "Inscription valide pour : ${formData.email}")

        clearFormAfterSuccess()
    }
    private fun clearFormAfterSuccess() {
        editTextFullName.text.clear()
        editTextEmail.text.clear()
        editTextPassword.text.clear()
        editTextConfirmPassword.text.clear()
        checkBoxTerms.isChecked = false
    }
    private fun onRegisterClicked() {
        val formData = readFormData()

        Log.d(TAG, "Tentative d'inscription pour : ${formData.email}")

        if (validateForm(formData)) {
            onRegisterSuccess(formData)
        } else {
            Log.d(TAG, "Formulaire invalide")
        }
    }

    private fun clearForm() {
        editTextFullName.text.clear()
        editTextEmail.text.clear()
        editTextPassword.text.clear()
        editTextConfirmPassword.text.clear()
        checkBoxTerms.isChecked = false

        clearFieldErrors()

        Toast.makeText(
            this,
            getString(R.string.toast_form_cleared),
            Toast.LENGTH_SHORT
        ).show()

        Log.d(TAG, "Formulaire effacé")
    }

    data class RegisterFormData(
        val fullName: String,
        val email: String,
        val password: String,
        val confirmPassword: String,
        val acceptedTerms: Boolean
    )

    private fun readFormData(): RegisterFormData {
        return RegisterFormData(
            fullName = editTextFullName.text.toString().trim(),
            email = editTextEmail.text.toString().trim(),
            password = editTextPassword.text.toString(),
            confirmPassword = editTextConfirmPassword.text.toString(),
            acceptedTerms = checkBoxTerms.isChecked
        )
    }

    private fun clearFieldErrors() {
        editTextFullName.error = null
        editTextEmail.error = null
        editTextPassword.error = null
        editTextConfirmPassword.error = null

        textFormResult.text = getString(R.string.form_ready)
        textFormResult.setTextColor(getColor(R.color.register_text_secondary))
    }

    private fun validateForm(formData: RegisterFormData): Boolean {
        var isValid = true

        clearFieldErrors()

        if (formData.fullName.isEmpty()) {
            editTextFullName.error = getString(R.string.error_full_name_required)
            isValid = false
        }

        if (formData.email.isEmpty()) {
            editTextEmail.error = getString(R.string.error_email_required)
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(formData.email).matches()) {
            editTextEmail.error = getString(R.string.error_email_invalid)
            isValid = false
        }

        if (formData.password.isEmpty()) {
            editTextPassword.error = getString(R.string.error_password_required)
            isValid = false
        } else if (formData.password.length < MIN_PASSWORD_LENGTH) {
            editTextPassword.error = getString(R.string.error_password_too_short)
            isValid = false
        }

        if (formData.confirmPassword.isEmpty()) {
            editTextConfirmPassword.error = getString(R.string.error_confirm_password_required)
            isValid = false
        } else if (formData.password != formData.confirmPassword) {
            editTextConfirmPassword.error = getString(R.string.error_passwords_not_matching)
            isValid = false
        }

        if (!formData.acceptedTerms) {
            textFormResult.text = getString(R.string.error_terms_required)
            textFormResult.setTextColor(getColor(R.color.register_text_error))
            isValid = false
        }

        return isValid
    }
}