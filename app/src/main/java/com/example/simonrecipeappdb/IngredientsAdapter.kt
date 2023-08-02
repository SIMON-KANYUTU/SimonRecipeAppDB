package com.example.simonrecipeappdb

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

// com.example.simonrecipeappdb.IngredientsAdapter.kt
class IngredientsAdapter(private val ingredientsList: List<String>) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredientsList[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingredientTextView: TextView = itemView.findViewById(R.id.ingredientTextView)

        fun bind(ingredient: String) {
            ingredientTextView.text = ingredient
        }
    }
}
