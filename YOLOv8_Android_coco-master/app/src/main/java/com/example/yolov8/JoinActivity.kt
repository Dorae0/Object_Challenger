package com.example.yolov8

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val CancelButton = findViewById<Button>(R.id.Cancel)
        val OKButton = findViewById<Button>(R.id.OK)
        val Email = findViewById<EditText>(R.id.editID)
        val Password = findViewById<EditText>(R.id.editPassword)
        val PasswordCheck = findViewById<EditText>(R.id.editPasswordCheck)
        val nickname = findViewById<EditText>(R.id.editNickname)
        val IsPasswordSame = findViewById<TextView>(R.id.IsPasswordSame)

        var data = listOf("월", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        val monthSpinner : Spinner = findViewById(R.id.editMonth)
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        monthSpinner.adapter = adapter

        PasswordCheck.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable?) {
                if (Password.getText().toString() == PasswordCheck.getText().toString()) {
                    IsPasswordSame.setText("비밀번호가 일치합니다.")
                    IsPasswordSame.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.mainsky))
                    OKButton.setEnabled(true)
                }
                else{
                    IsPasswordSame.setText("비밀번호가 일치하지 않습니다.")
                    IsPasswordSame.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                    OKButton.setEnabled(false)
                }
            }
        })
        OKButton.setOnClickListener{
                signUp(Email.getText().toString(), Password.getText().toString())
        }
        CancelButton.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
        }
    }

    private fun signUp(email: String, password: String) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    goToMainActivity(task.result?.user)
                } else if (task.exception?.message.isNullOrEmpty() == false) {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                } else {
                    signIn(email, password)
                }
            }
    }

    private fun goToMainActivity(user: FirebaseUser?) {
        if (user != null) {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
}

