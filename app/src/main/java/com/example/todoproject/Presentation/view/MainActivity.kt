package com.example.todoproject.Presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        manager.beginTransaction()
            .replace(R.id.frameContainer, MainFragment())
            .addToBackStack(null)
            .commit()
    }
}