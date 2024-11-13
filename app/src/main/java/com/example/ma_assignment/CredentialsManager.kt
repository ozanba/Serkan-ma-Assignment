package com.example.ma_assignment

import android.util.Patterns

class CredentialsManager {

    fun isEmailValid(email: String): Boolean{
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

}
