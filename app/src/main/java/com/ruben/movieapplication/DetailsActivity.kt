package com.ruben.movieapplication

import android.os.Bundle
import com.ruben.movieapplication.base.BaseActivity

class DetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}