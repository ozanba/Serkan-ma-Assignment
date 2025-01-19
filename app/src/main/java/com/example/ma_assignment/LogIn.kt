package com.example.ma_assignment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LogIn : Fragment() {

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var emailInputField: TextInputEditText
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var passwordInputField: TextInputEditText
    private lateinit var nextButton: Button
    private lateinit var registerNowTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        initializeViews(view)
        setupClickListeners()
        return view
    }

    private fun initializeViews(view: View) {
        emailInputLayout = view.findViewById(R.id.emailInputLayout)
        emailInputField = view.findViewById(R.id.emailInput)
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout)
        passwordInputField = view.findViewById(R.id.passwordInput)
        nextButton = view.findViewById(R.id.nextButton)
        registerNowTextView = view.findViewById(R.id.registerNowText)
    }

    private fun setupClickListeners() {
        nextButton.setOnClickListener { handleSignIn() }

        registerNowTextView.setOnClickListener {
            // Implement navigation to registration
            (activity as? MainActivity)?.navigateToRegister()
        }
    }

    private fun handleSignIn() {
        val email = emailInputField.text?.toString().orEmpty()
        val password = passwordInputField.text?.toString().orEmpty()

        validateCredentials(email, password)
    }

    private fun validateCredentials(email: String, password: String) {
        val isEmailValid = CredentialsManager.isEmailValid(email)
        val isPasswordValid = CredentialsManager.isPasswordValid(password)

        emailInputLayout.error = if (!isEmailValid) "Invalid email!" else null
        passwordInputLayout.error = if (!isPasswordValid) "Invalid password!" else null

        if (isEmailValid && isPasswordValid) {
            attemptLogin(email, password)
        }
    }

    private fun attemptLogin(email: String, password: String) {
        if (CredentialsManager.login(email, password)) {
            onLoginSuccess()
        } else {
            passwordInputLayout.error = "Incorrect credentials!"
        }
    }

    private fun onLoginSuccess() {
        Log.d("LogIn", "Login successful, navigating to recipes")
        (activity as? MainActivity)?.navigateToRecipes()
    }
}