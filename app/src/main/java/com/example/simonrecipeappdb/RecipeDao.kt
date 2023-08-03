package com.example.simonrecipeappdb
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

// RecipeDao.kt
@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(recipe: Recipe)
    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>
}
