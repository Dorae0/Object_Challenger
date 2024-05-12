package com.example.yolov8

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yolov8.databinding.FragmentMainHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainHomeFragment : Fragment() {
    private var _binding: FragmentMainHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainHomeBinding.inflate(inflater, container, false)
        textView = binding.UIDText
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logoutbuttonmain.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(activity, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        updateTextViewContent(getUid())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getUid(): String {
        val auth = Firebase.auth
        return auth.currentUser?.uid.toString()
    }

    // TextView 내용을 변경하는 함수
    fun updateTextViewContent(newContent: String) {
        textView.text = newContent
    }
}
