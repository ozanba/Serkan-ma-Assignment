package com.example.ma_assignment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
    private val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitle)
    private val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescription)

    fun bind(recipe: Recipe) {
        recipeTitle.text = recipe.title
        recipeDescription.text = recipe.description

        recipeImage.setImageResource(R.drawable.food_image)
    }
}