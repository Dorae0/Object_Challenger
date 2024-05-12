package com.example.yolov8

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts
import android.app.Activity
import androidx.activity.result.ActivityResultLauncher

class selectActivity : AppCompatActivity() {

    var total_time:Long=0
    var startTime =System.currentTimeMillis()
    private val model1list: List<String> = listOf("cat", "dog", "pigeon","sparrow")
    private val model2list: List<String> = listOf("cctv")
    var result_code=1
    private lateinit var spinnerDifficulty: Spinner
    private lateinit var spinnerModel: Spinner
    private lateinit var spinnerRounds: Spinner
    private lateinit var buttonSubmit: Button
    lateinit var launcher: ActivityResultLauncher<Intent>
    var rounds1=0
    var selectedDifficulty1 =""
    var selectedModel1 = ""
    var selectedRounds1 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select) // 여기에 실제 사용하는 레이아웃 파일명을 넣어주세요

        spinnerDifficulty = findViewById(R.id.spinnerDifficulty)
        spinnerModel = findViewById(R.id.spinnerModel)
        spinnerRounds = findViewById(R.id.spinnerRounds)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        // 난이도 Spinner 설정
        setupDifficultySpinner()

        // 모델명 Spinner 설정
        setupModelSpinner()

        // 라운드 수 Spinner 설정
        setupRoundsSpinner()

        // 확인 버튼 클릭 리스너 설정
        // buttonSubmit.setOnClickListener { ... } 부분에서 변경된 부분


            // 선택된 라운드 수를 가져오는 함수
            fun getRounds(rounds: String): Int {
                return when (rounds) {
                    "1 라운드" -> 1
                    "2 라운드" -> 2
                    "3 라운드" -> 3
                    "4 라운드" -> 4
                    else -> 0
                }
            }


//            launcher =
//                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    // 실행된 액티비티가 종료되면 여기에 결과를 처리하는 로직을 작성합니다.
//                    if (result.resultCode == Activity.RESULT_CANCELED) {
//                        Thread.sleep(100)
//                        if (result_code<rounds1)
//                        {
//                            val elapsedTime = System.currentTimeMillis() - startTime
//                            total_time=elapsedTime+total_time
//                            for (currentRound in 2..rounds1) {
//                                result_code=currentRound
//                                val intent = Intent(this@selectActivity, MainActivity::class.java)
//                                // 인텐트에 데이터를 추가하세요.
//
//                                // 라운드 정보를 인텐트에 추가합니다.
//                                intent.putExtra("difficulty", selectedDifficulty1)
//                                intent.putExtra("model", selectedModel1)
//                                intent.putExtra("rounds", rounds1)
//                                intent.putExtra("currentRound", currentRound)
//
//                                // 선택된 모델에 따라 랜덤한 값을 설정하여 추가합니다.
//                                val randomIndex = if (selectedModel1 == "동물") {
//                                    (0 until model1list.size).random()
//                                } else {
//                                    (0 until model2list.size).random()
//                                }
//                                val target = if (selectedModel1 == "동물") {
//                                    model1list[randomIndex]
//                                } else {
//                                    model2list[randomIndex]
//                                }
//                                intent.putExtra("target", target)
//
//                                Toast.makeText(
//                                    this@selectActivity,
//                                    "라운드 수: $currentRound\n목표 클래스: $target",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//
//                                // Launcher를 사용하여 실행된 액티비티를 시작합니다.
//                                launcher.launch(intent)
//                                startTime = System.currentTimeMillis()
//                            }
//
//                        }
//                        else
//                        {
//                            var timeintent=Intent(this@selectActivity, resultscreen::class.java)
//                            timeintent.putExtra("total_time",total_time)
//                            timeintent.putExtra("difficulty",selectedDifficulty1)
//                            startActivity(timeintent)
//                            finish()
//                            Toast.makeText(
//                                this@selectActivity,
//                                "총시간: $total_time",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//
//                    }
//                }
                    launcher =
                        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                            // 실행된 액티비티가 종료되면 여기에 결과를 처리하는 로직을 작성합니다.
                            if (result.resultCode == Activity.RESULT_CANCELED) {
                                Thread.sleep(100)
                                if (result_code<rounds1)
                                {
                                    val elapsedTime = System.currentTimeMillis() - startTime
                                    total_time=elapsedTime+total_time
                                        result_code=result_code+1
                                        val intent = Intent(this@selectActivity, GameMainActivity::class.java)
                                        // 인텐트에 데이터를 추가하세요.

                                        // 라운드 정보를 인텐트에 추가합니다.
                                        intent.putExtra("difficulty", selectedDifficulty1)
                                        intent.putExtra("model", selectedModel1)
                                        intent.putExtra("rounds", rounds1)
                                        intent.putExtra("currentRound", result_code)

                                        // 선택된 모델에 따라 랜덤한 값을 설정하여 추가합니다.
                                        val randomIndex = if (selectedModel1 == "동물") {
                                            (0 until model1list.size).random()
                                        } else {
                                            (0 until model2list.size).random()
                                        }
                                        val target = if (selectedModel1 == "동물") {
                                            model1list[randomIndex]
                                        } else {
                                            model2list[randomIndex]
                                        }
                                        intent.putExtra("target", target)

                                        Toast.makeText(
                                            this@selectActivity,
                                            "라운드 수: $result_code\n목표 클래스: $target",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        // Launcher를 사용하여 실행된 액티비티를 시작합니다.
                                        launcher.launch(intent)
                                        startTime = System.currentTimeMillis()


                                }
                                else
                                {
                                    var timeintent=Intent(this@selectActivity, resultscreen::class.java)
                                    timeintent.putExtra("total_time",total_time)
                                    timeintent.putExtra("difficulty",selectedDifficulty1)
                                    startActivity(timeintent)
                                    finish()
                                    Toast.makeText(
                                        this@selectActivity,
                                        "총시간: $total_time",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                        }


// 확인 버튼인 buttonSubmit이 클릭되었을 때 실행되는 클릭 리스너를 설정합니다.
            buttonSubmit.setOnClickListener {
                // 스피너에서 선택된 값을 가져옵니다.
                val selectedDifficulty = spinnerDifficulty.selectedItem.toString()
                val selectedModel = spinnerModel.selectedItem.toString()
                val selectedRounds = spinnerRounds.selectedItem.toString()

                // 선택된 라운드 수를 가져오는 함수를 사용하여 라운드를 설정합니다.
                val rounds = getRounds(selectedRounds)

                selectedDifficulty1=selectedDifficulty
                selectedModel1=selectedModel
                rounds1=rounds
                // 반복문을 사용하여 라운드 수에 따라 메인 액티비티를 실행합니다.
//                for (currentRound in 1..rounds) {
                    val intent = Intent(this@selectActivity, GameMainActivity::class.java)
//                    // 인텐트에 데이터를 추가하세요.
//
//                    // 라운드 정보를 인텐트에 추가합니다.
                    intent.putExtra("difficulty", selectedDifficulty)
                    intent.putExtra("model", selectedModel)
                    intent.putExtra("rounds", rounds)
                    intent.putExtra("currentRound", 1)
//
//                    // 선택된 모델에 따라 랜덤한 값을 설정하여 추가합니다.
                    val randomIndex = if (selectedModel == "동물") {
                        (0 until model1list.size).random()
                    } else {
                        (0 until model2list.size).random()
                    }
                    val target = if (selectedModel == "동물") {
                        model1list[randomIndex]
                    } else {
                        model2list[randomIndex]
                    }
                    intent.putExtra("target", target)
//
                    Toast.makeText(
                        this@selectActivity,
                        "라운드 수: 1 목표 클래스: $target",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Launcher를 사용하여 실행된 액티비티를 시작합니다.
                    launcher.launch(intent)
                    startTime = System.currentTimeMillis()



                }
            }





    private fun setupDifficultySpinner() {
        val difficultyList = listOf("쉬움", "보통", "어려움")
        val difficultyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, difficultyList)
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDifficulty.adapter = difficultyAdapter
    }

    private fun setupModelSpinner() {
        val modelList = listOf("동물", "물건")
        val modelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modelList)
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerModel.adapter = modelAdapter
    }

    private fun setupRoundsSpinner() {
        val roundsList = listOf("1 라운드", "2 라운드", "3 라운드", "4 라운드")
        val roundsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roundsList)
        roundsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRounds.adapter = roundsAdapter
    }
}

