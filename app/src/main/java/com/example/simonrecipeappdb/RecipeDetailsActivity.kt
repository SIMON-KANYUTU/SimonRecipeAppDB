package com.example.simonrecipeappdb

import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeDetailsActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val recipeTitleTextView: TextView = findViewById(R.id.recipeTitleTextView)
        val servesTextView: TextView = findViewById(R.id.servesTextView)
        val ingredientsTextView: TextView = findViewById(R.id.ingredientsTextView)
        val difficultyTextView: TextView = findViewById(R.id.difficultyTextView)
        val stepsTextView: TextView = findViewById(R.id.stepsTextView)

        // Suppress the deprecation warning for getParcelableExtra
        @Suppress("DEPRECATION")
        // Retrieve the recipe data from the intent extras
        val selectedRecipe = intent?.getParcelableExtra<Recipe>("recipe")
        if (selectedRecipe == null) {
            // Handle the case when the recipe data is not available
            // You can show an error message or finish the activity here
            finish()
            return
        }


        // Initialize RecyclerView and its adapters
        recyclerView = findViewById(R.id.recyclerView)

        // Set up RecyclerView with a LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)
       // Assuming you want to show ingredients initially

        // Bold the titles
        val servesTitle = SpannableString("Serves: ")
        servesTitle.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, servesTitle.length, 0)

        val ingredientsTitle = SpannableString("Ingredients: ")
        ingredientsTitle.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, ingredientsTitle.length, 0)

        val difficultyTitle = SpannableString("Difficulty: ")
        difficultyTitle.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, difficultyTitle.length, 0)

        val stepsTitle = SpannableString("Preparation Steps: ")
        stepsTitle.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, stepsTitle.length, 0)

        // Combine the titles with their respective data
        val servesText = SpannableStringBuilder()
        servesText.append(servesTitle)
        servesText.append(selectedRecipe.serves)

        val ingredientsText = SpannableStringBuilder()
        ingredientsText.append(ingredientsTitle)
        ingredientsText.append(selectedRecipe.ingredients)

        val difficultyText = SpannableStringBuilder()
        difficultyText.append(difficultyTitle)
        difficultyText.append(selectedRecipe.difficulty)

        val stepsText = SpannableStringBuilder()
        stepsText.append(stepsTitle)
        stepsText.append(selectedRecipe.steps)

        // Set the text to the TextViews
        recipeTitleTextView.text = selectedRecipe.title
        servesTextView.text = servesText
        ingredientsTextView.text = ingredientsText
        difficultyTextView.text = difficultyText
        stepsTextView.text = stepsText
    }
}
