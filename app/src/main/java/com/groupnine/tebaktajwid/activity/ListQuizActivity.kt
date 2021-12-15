package com.groupnine.tebaktajwid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.adapter.ListQuizAdapter
import com.groupnine.tebaktajwid.databinding.ActivityListQuizBinding
import com.groupnine.tebaktajwid.util.NotificationBarColor
import com.groupnine.tebaktajwid.viewmodel.QuizViewModel

class ListQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListQuizBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationBarColor().setColor(this, R.color.green_dark)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        viewModel.getListQuizData(this)
        viewModel.listQuiz.observe(this) {
            if (it != null) {
                val adapter = ListQuizAdapter(this, it)
                binding.rvQuiz.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}