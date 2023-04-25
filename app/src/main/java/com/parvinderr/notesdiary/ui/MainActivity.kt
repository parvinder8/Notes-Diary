package com.parvinderr.notesdiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parvinderr.notesdiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}