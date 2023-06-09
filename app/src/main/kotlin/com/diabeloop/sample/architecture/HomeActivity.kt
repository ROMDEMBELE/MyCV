package com.diabeloop.sample.architecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diabeloop.architecture.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
