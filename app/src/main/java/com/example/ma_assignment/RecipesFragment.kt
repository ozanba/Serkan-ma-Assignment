package com.example.ma_assignment
import androidx.appcompat.widget.SearchView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var adapter: RecipesAdapter
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        adapter = RecipesAdapter(mutableListOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        progressIndicator.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                    is UiState.Success -> {
                        progressIndicator.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        adapter.updateList(state.recipes)
                    }
                }
            }
        }

        // SearchView Listener
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateQuery(newText.orEmpty())
                return true
            }
        })



        return view
    }
}