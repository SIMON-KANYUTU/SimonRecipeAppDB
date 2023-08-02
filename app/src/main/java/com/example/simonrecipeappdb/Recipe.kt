package com.example.simonrecipeappdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val serves: String,
    val ingredients: String,
    val difficulty: String,
    val steps: String,
    val ingredientsList: List<String>, // List of ingredients
    val preparationStepsList: List<String> // List of preparation steps

) : Serializable
