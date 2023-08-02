package com.example.simonrecipeappdb

import androidx.lifecycle.LiveData

// RecipeRepository.kt

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }
}
