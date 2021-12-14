package com.groupnine.tebaktajwid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    private val _playingStatus = MutableLiveData<Int>()
    val playingStatus: LiveData<Int> get() = _playingStatus

    private val _recordingStatus = MutableLiveData<Int>()
    val recordingStatus: LiveData<Int> get() = _recordingStatus

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

}