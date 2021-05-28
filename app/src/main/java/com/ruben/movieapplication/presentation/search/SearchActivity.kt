package com.ruben.movieapplication.presentation.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.ruben.domain.model.base.StatusRecord
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
      searchViewModel.getSearchResult().collect { record ->
        when (record.status) {
          StatusRecord.LOADING -> {
            //do nothing
          }
          StatusRecord.SUCCESS -> {
            record.data?.let {
              handleSearchResult(it)
            }
          }
          StatusRecord.FAIL -> {
            handleNoResults()
          }
        }
      }
    }
  }

  private fun setupSearch() {
    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        //search for given query
        if (!query.isNullOrEmpty() && searchViewModel.isValidQuery(query)) {
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
      binding.noResultContainer.visibility = View.GONE
      binding.searchResultsRv.visibility = View.VISIBLE
      resultAdapter.setItems(searchResultRecord)
      resultAdapter.setListener(this)
      resultAdapter.notifyDataSetChanged()
    }
  }

  private fun handleNoResults() {
    AppUtility.stopProgress(binding.searchPb, this@SearchActivity)
    binding.searchResultsRv.visibility = View.GONE
    binding.noResultContainer.visibility = View.VISIBLE
  }

  override fun onItemClick(result: String) {
    startActivity(DetailsActivity.newIntent(this, result))
  }
}