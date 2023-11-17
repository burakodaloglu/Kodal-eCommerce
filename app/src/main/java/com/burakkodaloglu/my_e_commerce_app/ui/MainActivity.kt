package com.burakkodaloglu.my_e_commerce_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.burakkodaloglu.my_e_commerce_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}