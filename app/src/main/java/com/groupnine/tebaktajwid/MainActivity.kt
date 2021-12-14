package com.groupnine.tebaktajwid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.groupnine.tebaktajwid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnMaterial.setOnClickListener {
                startActivity(Intent(this@MainActivity, MaterialActivity::class.java))
            }

            btnQuiz.setOnClickListener {
                startActivity(Intent(this@MainActivity, QuizActivity::class.java))
            }

            btnSearch.setOnClickListener {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            }
        }
    }
}