package com.example.ma_assignment

import android.util.Patterns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CredentialsManager {
    private val _isLoggedIn = MutableStateFlow(false) // Initial state
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val credentialsMap = mutableMapOf(Pair("test@example.com", "1234"))

    // Logout Functionality
    fun logout() {
        _isLoggedIn.value = false
    }

    // Validate Email Format
    fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        val regex = Regex(emailPattern)
        return regex.matches(email)
    }

    // Validate Password (non-empty check, can be extended for complexity rules)
    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    // Login Functionality
    fun login(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        val storedPassword = credentialsMap[normalizedEmail]
        return storedPassword?.equals(password) ?: false
    }

    // Register a New User
    fun register(fullName: String, email: String, phoneNumber: String, password: String): String {
        val normalizedEmail = email.lowercase()
        if (credentialsMap.containsKey(normalizedEmail)) {
            return "Error: Email already taken"
        }
        if (fullName.isBlank()) return "Error: Full name cannot be empty"
        if (!isEmailValid(email)) return "Error: Invalid email format"
        if (phoneNumber.isBlank()) return "Error: Phone number cannot be empty"
        if (!isPasswordValid(password)) return "Error: Password cannot be empty"

        credentialsMap[normalizedEmail] = password
        return "Registration successful"
    }
}