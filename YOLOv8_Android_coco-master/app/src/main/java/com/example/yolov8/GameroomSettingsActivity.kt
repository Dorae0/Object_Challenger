package com.example.yolov8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yolov8.databinding.ActivityGameroomSettingsBinding

private lateinit var binding : ActivityGameroomSettingsBinding

class GameroomSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameroomSettingsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_gameroom_settings)
    }

}