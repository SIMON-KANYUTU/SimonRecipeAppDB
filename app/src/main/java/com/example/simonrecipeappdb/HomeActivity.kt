package com.example.simonrecipeappdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.Button
import androidx.activity.viewModels

class HomeActivity : ComponentActivity(), RecipeAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(emptyList(), this)
        recyclerView.adapter = recipeAdapter

        // Observe changes in the list of recipes and update the adapter
        homeViewModel.allRecipes.observe(this) { recipes ->
            recipeAdapter.setRecipes(recipes)
        }

        val addRecipeButton: Button = findViewById(R.id.addRecipeButton)
        addRecipeButton.setOnClickListener {
            // Call a method to insert a dummy recipe into the database
            insertDummyRecipe()
        }
    }

    private fun insertDummyRecipe() {
        val newRecipe = Recipe(
            title = "Dummy Recipe",
            description = "This is a dummy recipe.",
            serves = "2",
            ingredients = "Dummy Ingredient 1, Dummy Ingredient 2",
            difficulty = "Easy",
            steps = "1. Do something.\n2. Then do something else.",
            ingredientsList = listOf("Dummy Ingredient 1", "Dummy Ingredient 2"),
            preparationStepsList = listOf("1. Do something.", "2. Then do something else.")
        )
        homeViewModel.insert(newRecipe)
    }

    override fun onItemClick(position: Int) {
        val selectedRecipe = recipeAdapter.getRecipeAtPosition(position)

        val intent = Intent(this, RecipeDetailsActivity::class.java)
        intent.putExtra("recipe", selectedRecipe)
        startActivity(intent)
    }
}
