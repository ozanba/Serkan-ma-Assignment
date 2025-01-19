package com.example.ma_assignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Replace deprecated `launchWhenStarted` with `repeatOnLifecycle`
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                CredentialsManager.isLoggedIn.collect { isLoggedIn ->
                    if (isLoggedIn) {
                        navigateToRecipes()
                    } else {
                        navigateToLogin()
                    }
                }
            }
        }
    }

    fun navigateToRecipes() {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecipesFragment())
                .addToBackStack(null)
                .commit()
        } catch (e: Exception) {
            Log.e("MainActivity", "Navigation error: ${e.message}", e)

        }
    }

    fun navigateToRegister() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RegisterFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LogIn())
            .commit()
    }
}