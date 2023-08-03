package com.example.simonrecipeappdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil


class RecipeDiffCallback(private val oldList: List<Recipe>, private val newList: List<Recipe>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}


class RecipeAdapter(private var recipes: List<Recipe>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        init {
            itemView.setOnClickListener(this)
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position)
                }
            }
        }
        init {
            itemView.setOnClickListener(this)
        }

        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.titleTextView.text = currentRecipe.title
        holder.descriptionTextView.text = currentRecipe.description
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setRecipes(recipeList: List<Recipe>) {
        // Calculate the differences between the old and new list using DiffUtil
        val diffResult = DiffUtil.calculateDiff(RecipeDiffCallback(recipes, recipeList))

        // Update the list with the new data
        recipes = recipeList

        // Dispatch the specific item changes to the adapter
        diffResult.dispatchUpdatesTo(this)
    }


    fun getRecipeAtPosition(position: Int): Recipe {
        return recipes[position]
    }
}
