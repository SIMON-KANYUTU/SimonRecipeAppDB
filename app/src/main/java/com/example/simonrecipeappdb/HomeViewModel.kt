package com.example.simonrecipeappdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    val allRecipes: LiveData<List<Recipe>>

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
        allRecipes = repository.allRecipes
    }

    fun insert(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }
}
