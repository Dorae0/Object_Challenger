package com.example.yolov8

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.yolov8.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var viewPager2Adatper = ViewPager2Adapter(this)
        viewPager2Adatper.addFragment(MainHomeFragment())
        viewPager2Adatper.addFragment(MainGameStartFragment())
        viewPager2Adatper.addFragment(MainOptionsFragment())

        //Adapter 연결
        binding.viewPager2.apply {
            adapter = viewPager2Adatper

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            Log.e("YMC", "ViewPager position: $position")
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "GameStart"
                2 -> tab.text = "Options"
            }
        }.attach()
    }
}