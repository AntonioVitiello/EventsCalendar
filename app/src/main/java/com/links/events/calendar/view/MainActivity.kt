package com.links.events.calendar.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.links.events.calendar.R
import com.links.events.calendar.tools.SingleEvent
import com.links.events.calendar.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModel.errorLiveData.observe(this, ::showError)
    }

    private fun showError(event: SingleEvent<Boolean>) {
        if (event.getContentIfNotHandled() == true) {
            Toast.makeText(this, "Please try again later.", Toast.LENGTH_SHORT).show()
        }
    }

}