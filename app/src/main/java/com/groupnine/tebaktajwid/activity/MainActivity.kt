package com.groupnine.tebaktajwid.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.databinding.ActivityMainBinding
import com.groupnine.tebaktajwid.util.NotificationBarColor
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExit = false

    companion object {
        const val delay = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationBarColor().setColor(this, R.color.dark)

        with(binding) {
            btnMaterial.setOnClickListener {
                startActivity(Intent(this@MainActivity, ListMaterialActivity::class.java))
            }

            btnQuiz.setOnClickListener {
                startActivity(Intent(this@MainActivity, ListQuizActivity::class.java))
            }

            btnSearch.setOnClickListener {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            }

            btnAbout.setOnClickListener {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            finish()
            exitProcess(0)
        }

        this.doubleBackToExit = true
        Toast.makeText(this, "Klik kembali untuk keluar", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExit = false }, delay)
    }
}