package com.example.ma_assignment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ma_assignment.Recipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


sealed class UiState {
    object Loading : UiState()
    data class Success(val recipes: List<Recipe>) : UiState()
}

class RecipesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val allRecipes = listOf(
        Recipe(1, "Adana kebap", "A delicious Turkish food with meat and spices.", R.drawable.food_image),
        Recipe(2, "Pizza", "A traditional Italian dish with cheese and tomato.", R.drawable.food_image),
        Recipe(3, "Nohut pilav", "A traditional Turkish dish with chickpeas and rice.", R.drawable.food_image),
        Recipe(4, "Doner", "A popular Turkish dish with meat, vegetables, and sauce.", R.drawable.food_image),
        Recipe(5, "Baklava", "A sweet Turkish dessert made of layers of filo dough.", R.drawable.food_image)
    )

    private val _query = MutableStateFlow("")
    private val queryFlow: StateFlow<String> = _query

    init {
        viewModelScope.launch {
            queryFlow
                .debounce(300)
                .onEach {
                    _uiState.value = UiState.Loading
                    delay(2000) // Simulate loading delay
                    val filteredRecipes = if (it.isBlank()) {
                        allRecipes
                    } else {
                        allRecipes.filter { recipe ->
                            recipe.title.contains(it, ignoreCase = true) || recipe.description.contains(it, ignoreCase = true)
                        }
                    }
                    _uiState.value = UiState.Success(filteredRecipes)
                }
                .launchIn(this)
        }
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }
}