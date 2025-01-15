package com.example.ma_assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView = findViewById<SearchView>(R.id.searchView)
        val recipiesRecyclerView = findViewById<RecyclerView>(R.id.recipeList)

        val recipes = listOf(
            Recipe(1, "Adana kebap", "A delicious Turkish food with meat and spices.", R.drawable.food_image),
            Recipe(2, "Pizza", "A traditional Italian dish with cheese and tomato.", R.drawable.food_image),
            Recipe(3, "Nohut pilav", "A traditional Turkish dish with chickpeas and rice.", R.drawable.food_image),
            Recipe(4, "Doner", "A popular Turkish dish with meat, vegetables, and sauce.", R.drawable.food_image),
            Recipe(5, "Baklava", "A sweet Turkish dessert made of layers of filo dough.", R.drawable.food_image)
        )

        adapter = RecipesAdapter(recipes.toMutableList())
        recipiesRecyclerView.adapter = adapter
        recipiesRecyclerView.layoutManager = LinearLayoutManager(this)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = recipes.filter { recipe ->
                    recipe.title.contains(newText ?: "", ignoreCase = true)
                }
                adapter.updateList(filteredList)
                return true
            }
        })
    }
}