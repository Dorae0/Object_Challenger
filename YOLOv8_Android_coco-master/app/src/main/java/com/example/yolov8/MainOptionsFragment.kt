package com.example.yolov8

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yolov8.databinding.FragmentMainHomeBinding
import com.example.yolov8.databinding.FragmentMainOptionsBinding

class MainOptionsFragment : Fragment() {
    private var _binding: FragmentMainOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainoptionbutton.setOnClickListener {
            // Intent를 사용하여 다른 Activity로 이동
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}