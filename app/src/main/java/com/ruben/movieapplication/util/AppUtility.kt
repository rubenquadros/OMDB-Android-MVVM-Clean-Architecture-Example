package com.ruben.movieapplication.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast

/**
 * Created by ruben.quadros on 26/05/21.
 **/
object AppUtility {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress(progressBar: ProgressBar, activity: Activity) {
        progressBar.visibility = View.VISIBLE
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun stopProgress(progressBar: ProgressBar, activity: Activity) {
        progressBar.visibility = View.GONE
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}