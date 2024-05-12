package com.example.yolov8

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Main_Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)

        val btnLogout = view.findViewById<Button>(R.id.logoutbuttonmain)
        btnLogout.setOnClickListener {
            // 버튼 클릭 시 LoginActivity로 이동
            navigateToLoginActivity()
        }
        return view
    }

    private fun navigateToLoginActivity() {
        // Intent를 사용하여 LoginActivity로 이동
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }
}
