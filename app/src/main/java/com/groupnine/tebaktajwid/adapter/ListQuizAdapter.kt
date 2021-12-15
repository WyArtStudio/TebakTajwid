package com.groupnine.tebaktajwid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.activity.QuizActivity
import com.groupnine.tebaktajwid.databinding.ItemCardQuizBinding
import com.groupnine.tebaktajwid.model.Quiz

class ListQuizAdapter(private val context: Context, private val listQuiz: List<Quiz>) :
    RecyclerView.Adapter<ListQuizAdapter.ListViewHolder>() {

    companion object {
        const val BEGINNER = "Beginner"
        const val EASY = "Easy"
        const val NORMAL = "Normal"
        const val MEDIUM = "Medium"
        const val HARD = "Hard"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListQuizAdapter.ListViewHolder {
        val binding = ItemCardQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListQuizAdapter.ListViewHolder, position: Int) {
        holder.bind(listQuiz[position])
    }

    override fun getItemCount(): Int {
        return listQuiz.size
    }

    inner class ListViewHolder(private val binding: ItemCardQuizBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quiz: Quiz) {
            with(binding) {
                Glide.with(context).load(quiz.icon).into(imgIcon)
                tvTitle.text = quiz.title
                tvLevel.text = quiz.level

                when(quiz.level) {
                    BEGINNER -> {
                        tvLevel.setTextColor(ContextCompat.getColor(context, R.color.beginner))
                        quiz.audio = mutableListOf(R.raw.soal_1_idhar_al_lahab)
                    }
                    EASY -> {
                        tvLevel.setTextColor(ContextCompat.getColor(context, R.color.easy))
                        quiz.audio = mutableListOf(R.raw.soal_2_ikhfa_al_lahab)
                    }
                    NORMAL -> {
                        tvLevel.setTextColor(ContextCompat.getColor(context, R.color.normal))
                        quiz.audio = mutableListOf(R.raw.soal_3_ikhfa_syafawi_al_fiil)
                    }
                    MEDIUM -> {
                        tvLevel.setTextColor(ContextCompat.getColor(context, R.color.medium))
                        quiz.audio = mutableListOf(R.raw.soal_4_idgham_bighunnah_az_zalzalah)
                    }
                    HARD -> {
                        tvLevel.setTextColor(ContextCompat.getColor(context, R.color.hard))
                        quiz.audio = mutableListOf(R.raw.soal_5_mad_lazim_kilmi_al_fatihah)
                    }
                }

                itemView.setOnClickListener {
                    val intent = Intent(context, QuizActivity::class.java)
                    intent.putExtra(QuizActivity.EXTRA_QUIZ, quiz)
                    context.startActivity(intent)
                }
            }
        }
    }
}