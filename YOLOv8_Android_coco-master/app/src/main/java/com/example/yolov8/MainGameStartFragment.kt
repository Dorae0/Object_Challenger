package com.example.yolov8

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yolov8.databinding.FragmentMainGameStartBinding
import com.example.yolov8.databinding.FragmentMainHomeBinding

class MainGameStartFragment : Fragment() {
    private var _binding: FragmentMainGameStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainGameStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gamestartbutton.setOnClickListener {
            // Intent를 사용하여 다른 Activity로 이동
            val intent = Intent(activity, selectActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}