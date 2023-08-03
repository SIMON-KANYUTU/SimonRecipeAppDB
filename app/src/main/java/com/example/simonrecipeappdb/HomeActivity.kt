package com.example.simonrecipeappdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.graphics.Typeface
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

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
        homeViewModel.allRecipes.observe(this, Observer<List<Recipe>> { recipes ->
            recipeAdapter.setRecipes(recipes)
        })

        // Find the instructionTextView in the layout
        val instructionTextView: TextView = findViewById(R.id.instructionTextView)

        // Set the text for the instructionTextView
        instructionTextView.text = "Click Add Recipe Button to add Dummy Recipes we've generated & Cick on Each Meal for More Details"

        // Bold the text in instructionTextView
        instructionTextView.setTypeface(null, Typeface.BOLD)

        // Center the text horizontally
        instructionTextView.gravity = Gravity.CENTER

        // Add padding to the instructionTextView (adjust the values as per your preference)
        instructionTextView.setPadding(0, 0, 0, 48) // 48dp bottom padding

        val addRecipeButton: Button = findViewById(R.id.addRecipeButton)
        addRecipeButton.setOnClickListener {
            // Call a method to insert a dummy recipe into the database
            insertDummyRecipe()
        }
    }

    private fun insertDummyRecipe() {
        val recipe1 = Recipe(
            title = "Spaghetti Carbonara",
            description = "Classic Italian pasta dish with eggs, cheese, bacon, and black pepper.",
            serves = "4",
            ingredients = "400g spaghetti\n4 large eggs\n1 cup grated Pecorino Romano cheese\n150g pancetta or guanciale, diced\n2 garlic cloves, minced\nSalt and black pepper to taste",
            difficulty = "Medium",
            steps = "1. Cook the spaghetti in a large pot of salted boiling water until al dente.\n" +
                    "2. In a separate bowl, whisk together the eggs, grated Pecorino Romano cheese, salt, and black pepper.\n" +
                    "3. In a skillet, sauté the diced pancetta or guanciale until crispy. Add minced garlic and cook for another minute.\n" +
                    "4. Drain the cooked spaghetti, reserving some pasta water.\n" +
                    "5. Quickly toss the hot spaghetti with the egg-cheese mixture, allowing the heat of the pasta to cook the eggs.\n" +
                    "6. Add the crispy pancetta or guanciale and mix well.\n" +
                    "7. If needed, add some reserved pasta water to create a creamy sauce.\n" +
                    "8. Serve immediately with extra grated cheese and black pepper on top."
        )

        val recipe2 = Recipe(
            title = "Chicken Stir-Fry",
            description = "A quick and flavorful chicken stir-fry with vegetables and a savory sauce.",
            serves = "3",
            ingredients = "400g boneless, skinless chicken breast, sliced\n2 cups mixed vegetables (bell peppers, broccoli, carrots, snap peas, etc.)\n3 tablespoons soy sauce\n2 tablespoons oyster sauce\n1 tablespoon cornstarch\n1 tablespoon vegetable oil\n2 cloves garlic, minced\n1 teaspoon grated ginger\nSalt and black pepper to taste",
            difficulty = "Easy",
            steps = "1. In a small bowl, mix together soy sauce, oyster sauce, and cornstarch to create the sauce.\n" +
                    "2. Season the sliced chicken with salt and black pepper.\n" +
                    "3. Heat vegetable oil in a wok or large skillet over medium-high heat.\n" +
                    "4. Add minced garlic and grated ginger, and stir-fry for a minute.\n" +
                    "5. Add the sliced chicken and cook until browned and fully cooked.\n" +
                    "6. Add the mixed vegetables and stir-fry until tender-crisp.\n" +
                    "7. Pour the sauce over the chicken and vegetables, stirring to coat everything evenly.\n" +
                    "8. Cook for another minute until the sauce thickens.\n" +
                    "9. Serve hot over steamed rice."
        )

        val recipe3 = Recipe(
            title = "Chocolate Chip Cookies",
            description = "Classic homemade chocolate chip cookies with a soft and chewy texture.",
            serves = "24",
            ingredients = "2 1/4 cups all-purpose flour\n1/2 teaspoon baking soda\n1 cup unsalted butter, softened\n1/2 cup granulated sugar\n1 cup packed light brown sugar\n1 teaspoon vanilla extract\n2 large eggs\n2 cups semisweet chocolate chips\n1 cup chopped nuts (optional)",
            difficulty = "Easy",
            steps = "1. Preheat the oven to 375°F (190°C) and line baking sheets with parchment paper.\n" +
                    "2. In a small bowl, mix together the flour and baking soda.\n" +
                    "3. In a large mixing bowl, beat the softened butter, granulated sugar, brown sugar, and vanilla extract until creamy.\n" +
                    "4. Add the eggs, one at a time, beating well after each addition.\n" +
                    "5. Gradually add the flour mixture to the butter mixture and mix until well combined.\n" +
                    "6. Stir in the chocolate chips and chopped nuts (if using).\n" +
                    "7. Drop rounded tablespoons of dough onto the prepared baking sheets.\n" +
                    "8. Bake for 9 to 11 minutes or until golden brown.\n" +
                    "9. Let the cookies cool on the baking sheets for a few minutes before transferring them to wire racks to cool completely."
        )

        insertDummyRecipe(recipe1)
        insertDummyRecipe(recipe2)
        insertDummyRecipe(recipe3)
    }
    private fun insertDummyRecipe(recipe: Recipe) {
        homeViewModel.insert(recipe)
    }


    override fun onItemClick(position: Int) {
        val selectedRecipe = recipeAdapter.getRecipeAtPosition(position)

        val intent = Intent(this, RecipeDetailsActivity::class.java)
        intent.putExtra("recipe", selectedRecipe)
        startActivity(intent)
    }
    override fun onDeleteClick(position: Int) {
        val selectedRecipe = recipeAdapter.getRecipeAtPosition(position)
        homeViewModel.deleteRecipe(selectedRecipe)
    }

}
