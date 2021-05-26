package com.ruben.movieapplication.presentation.search

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.ruben.domain.model.search.SearchResultRecord
import com.ruben.movieapplication.base.BaseActivity
import com.ruben.movieapplication.base.ItemClickListener
import com.ruben.movieapplication.databinding.ActivityMainBinding
import com.ruben.movieapplication.presentation.details.DetailsActivity
import com.ruben.movieapplication.util.AppUtility
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SearchActivity : BaseActivity(), AndroidScopeComponent, ItemClickListener {

  override val scope: Scope by activityScope()
  private lateinit var binding: ActivityMainBinding
  private val searchViewModel: SearchViewModel by viewModel()
  private val resultAdapter: ResultAdapter by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    observeData()
    setupSearch()
    setupResultsView()
  }

  private fun observeData() {
    addRepeatingJob(Lifecycle.State.STARTED) {
      searchViewModel.getSearchResult().collect {
        handleSearchResult(it)
      }
    }
  }

  private fun setupSearch() {
    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        //search for given query
        if (!query.isNullOrEmpty()) {
          AppUtility.showProgress(binding.searchPb, this@SearchActivity)
          searchViewModel.getQueryResults(query, 1)
        }
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        //do noting
        return true
      }

    })
  }

  private fun setupResultsView() {
    binding.searchResultsRv.adapter = resultAdapter
  }

  private fun handleSearchResult(searchResultRecord: SearchResultRecord) {
    AppUtility.stopProgress(binding.searchPb, this)
    if (searchResultRecord.searchResults.isNotEmpty()) {
      resultAdapter.setItems(searchResultRecord)
      resultAdapter.setListener(this)
      resultAdapter.notifyDataSetChanged()
    }
  }

  override fun onItemClick(result: String) {
    startActivity(DetailsActivity.newIntent(this, result))
  }
}