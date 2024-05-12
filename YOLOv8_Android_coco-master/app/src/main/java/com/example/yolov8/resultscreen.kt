package com.example.yolov8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView


class resultscreen : AppCompatActivity() {
    var data1 = mutableMapOf<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultscreen)
        var resulttime = intent.getLongExtra("total_time",0)
        var difficulty = intent.getStringExtra("difficulty")

        if (difficulty == "쉬움") {
            for (i in 1..8) {
                data1["user${i}"] = (8 until 60).random()
            }
        } else if (difficulty == "보통") {
            for (i in 1..8) {
                data1["user${i}"] = (6 until 40).random()
            }
        } else {
            for (i in 1..8) {
                data1["user${i}"] = (1 until 30).random()
            }
        }
        resulttime=resulttime/1000
        var resulttime1=resulttime.toInt()
        data1["user"] = resulttime1
        val data = data1.toList().sortedBy { (_, time) -> time }.toMap()

        val button = findViewById<Button>(R.id.logoutbuttonmain)
        val tableLayout = findViewById<TableLayout>(R.id.result_resultable)

        for ((index, row) in data.entries.withIndex()) {
            val tableRow = TableRow(this)

            val rankTextView = TextView(this)
            rankTextView.text = (index + 1).toString() // 등수를 계산하여 출력
            rankTextView.setPadding(20, 10, 20, 10)
            rankTextView.gravity = Gravity.CENTER
            rankTextView.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )

            val userNameTextView = TextView(this)
            userNameTextView.text = row.key // 사용자 이름 출력
            userNameTextView.setPadding(20, 10, 20, 10)
            userNameTextView.gravity = Gravity.CENTER
            userNameTextView.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )

            val userTimeTextView = TextView(this)
            userTimeTextView.text = row.value.toString() // 사용자 시간 출력
            userTimeTextView.setPadding(20, 10, 20, 10)
            userTimeTextView.gravity = Gravity.CENTER
            userTimeTextView.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )

            tableRow.addView(rankTextView)
            tableRow.addView(userNameTextView)
            tableRow.addView(userTimeTextView)

            tableLayout.addView(tableRow)
        }

        button.setOnClickListener {
            finish()
        }
    }
}
