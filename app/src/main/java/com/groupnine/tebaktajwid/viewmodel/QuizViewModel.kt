package com.groupnine.tebaktajwid.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.model.Quiz

class QuizViewModel: ViewModel() {
    private val _playingStatus = MutableLiveData<Int>()
    val playingStatus: LiveData<Int> get() = _playingStatus

    private val _recordingStatus = MutableLiveData<Int>()
    val recordingStatus: LiveData<Int> get() = _recordingStatus

    private val _listQuiz = MutableLiveData<ArrayList<Quiz>>()
    val listQuiz: LiveData<ArrayList<Quiz>> get() = _listQuiz

    init {
        _playingStatus.value = 0
        _recordingStatus.value = 0
    }

    fun setPlayingStatus(status: Int) {
        _playingStatus.value = status
    }

    fun setRecordingStatus(status: Int) {
        _recordingStatus.value = status
    }

    @SuppressLint("Recycle")
    fun getListQuizData(context: Context) {
        val list: ArrayList<Quiz> = ArrayList()

        val title = context.resources.getStringArray(R.array.quiz_title)
        val level = context.resources.getStringArray(R.array.quiz_level)
        val icon = context.resources.obtainTypedArray(R.array.quiz_icon)
        val question = context.resources.obtainTypedArray(R.array.quiz_question)
        val answer = context.resources.getStringArray(R.array.quiz_answer)
        for (i in title.indices) {
            val quiz = Quiz(title[i], level[i], icon.getResourceId(i, -1),
                    question.getResourceId(i, -1),null, answer[i])
            list.add(quiz)
        }

        _listQuiz.value = list
    }

}