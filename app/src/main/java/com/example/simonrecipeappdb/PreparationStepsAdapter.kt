package com.example.simonrecipeappdb

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

// com.example.simonrecipeappdb.PreparationStepsAdapter.kt

class PreparationStepsAdapter(private val preparationStepsList: List<String>) :
    RecyclerView.Adapter<PreparationStepsAdapter.PreparationStepsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreparationStepsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_preparation_step, parent, false)
        return PreparationStepsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PreparationStepsViewHolder, position: Int) {
        val step = preparationStepsList[position]
        holder.bind(step)
    }

    override fun getItemCount(): Int {
        return preparationStepsList.size
    }

    inner class PreparationStepsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stepTextView: TextView = itemView.findViewById(R.id.stepTextView)

        fun bind(step: String) {
            stepTextView.text = step
        }
    }
}
