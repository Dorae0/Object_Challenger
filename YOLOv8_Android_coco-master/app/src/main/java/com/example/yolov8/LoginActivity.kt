package com.example.yolov8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val joinbutton : Button = findViewById(R.id.button4)
        val loginbutton : Button = findViewById(R.id.button3)
        val email : TextView = findViewById(R.id.editID)
        val password : TextView = findViewById(R.id.editPassword)

        joinbutton.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
        loginbutton.setOnClickListener {
            if(email.text.toString().trim().isEmpty() || password.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "ID 또는 비밀번호가 입력되지 않았습니다.", Toast.LENGTH_LONG).show()
            }
            else {
                signIn(email.getText().toString(), password.getText().toString())
            }
        }
    }

    private fun signIn(email: String, password: String) {
        auth = Firebase.auth
        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    goToMainActivity(task.result?.user)
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun goToMainActivity(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}



