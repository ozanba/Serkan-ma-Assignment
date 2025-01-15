package com.example.ma_assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipesAdapter(private var recipeList: MutableList<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.recipeTitle)
        val description: TextView = view.findViewById(R.id.recipeDescription)
        val image: ImageView = view.findViewById(R.id.recipeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.title.text = recipe.title
        holder.description.text = recipe.description
        holder.image.setImageResource(recipe.imageResId)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun updateList(newList: List<Recipe>) {
        recipeList.clear()
        recipeList.addAll(newList)
        notifyDataSetChanged()
    }
}