package com.ruben.movieapplication.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.ruben.movieapplication.R
import com.ruben.movieapplication.presentation.search.SearchActivity
import com.ruben.movieapplication.util.AppUtility

/**
 * Created by ruben.quadros on 26/05/21.
 **/
abstract class BaseActivity: AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler { _, e -> handleUnCaughtExceptions(e)  }
    }
    
    private fun handleUnCaughtExceptions(e: Throwable) {
        Log.e(BaseActivity::class.simpleName, e.message.toString())
        AppUtility.showToast(this, getString(R.string.app_crash_message))
        val intent = Intent(this, SearchActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}