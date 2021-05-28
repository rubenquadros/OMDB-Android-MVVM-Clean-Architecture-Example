package com.ruben.movieapplication.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.bumptech.glide.Glide
import com.ruben.domain.model.base.StatusRecord
import com.ruben.domain.model.details.DetailsRecord
import com.ruben.movieapplication.R
import com.ruben.movieapplication.base.BaseActivity
import com.ruben.movieapplication.databinding.ActivityDetailsBinding
import com.ruben.movieapplication.util.AppConstants
import com.ruben.movieapplication.util.AppUtility
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModel()

    companion object {
        fun newIntent(context: Context, id: String): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(AppConstants.IMDB_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        readIntent()
        observeData()
    }

    private fun readIntent() {
        intent.getStringExtra(AppConstants.IMDB_ID)?.let {
            detailsViewModel.getDetails(it)
        }
    }

    private fun observeData() {
        addRepeatingJob(Lifecycle.State.STARTED) {
            detailsViewModel.getDetailsResult().collect { record ->
                when (record.status) {
                    StatusRecord.LOADING -> {
                        AppUtility.showProgress(binding.detailsPb, this@DetailsActivity)
                    }
                    StatusRecord.SUCCESS -> {
                        record.data?.let {
                            showDetails(it)
                        }
                    }
                    StatusRecord.FAIL -> {
                        finish()
                    }
                }
            }
        }
    }

    private fun showDetails(detailsRecord: DetailsRecord) {
        binding.detailsContainer.visibility = View.VISIBLE
        Glide.with(this).load(detailsRecord.poster)
            .placeholder(R.drawable.placeholder)
            .into(binding.posterIv)
        binding.titleTv.text = detailsRecord.title
        binding.overviewTv.text = detailsRecord.overview
        binding.ratingLabel.text = getString(R.string.details_ratings_label)
        binding.ratingTv.text = detailsRecord.rating
        binding.releaseDateLabel.text = getString(R.string.details_release_label)
        binding.releaseDateTv.text = detailsRecord.releaseDate
        binding.languageLabel.text = getString(R.string.details_language_label)
        binding.languageTv.text = detailsRecord.language
        AppUtility.stopProgress(binding.detailsPb, this)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }
}