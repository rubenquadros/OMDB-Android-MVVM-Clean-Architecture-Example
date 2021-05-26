package com.ruben.movieapplication.presentation

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.ruben.domain.model.SearchResultRecord
import com.ruben.movieapplication.base.BaseActivity
import com.ruben.movieapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var adapter: ResultAdapter
  private val searchViewModel: SearchViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    observeData()
    setupSearch()
  }

  private fun observeData() {
    addRepeatingJob(Lifecycle.State.STARTED) {
      searchViewModel.getSearchResult().collect {
        if (it.searchResults.isNotEmpty()) {
          adapter = ResultAdapter(it)
          binding.searchResultsRv.adapter = adapter
        }
      }
    }
  }

  private fun setupSearch() {
    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        //search for given query
        searchViewModel.getQueryResults(query ?: "", 1)
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        //do noting
        return true
      }

    })
  }
}