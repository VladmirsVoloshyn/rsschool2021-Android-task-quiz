package com.rsschool.quiz.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityResultBinding
import kotlin.system.exitProcess

class ResultActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            exitProcess(0)
        }


        binding.exitButton.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EXIT", 0)
            startActivity(intent)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}