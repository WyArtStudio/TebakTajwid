package com.groupnine.tebaktajwid.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.databinding.ActivityQuizBinding
import com.groupnine.tebaktajwid.model.Quiz
import com.groupnine.tebaktajwid.viewmodel.QuizViewModel
import java.util.*

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var viewModel: QuizViewModel
    private lateinit var dialog: Dialog
    private lateinit var btnQuit: FrameLayout
    private lateinit var btnContinue: FrameLayout
    private lateinit var tvAnswerStatus: TextView
    private lateinit var tvAnswerDescription: TextView
    private lateinit var answer: String
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var currentSong: MutableList<Int>

    companion object {
        const val RecordAudioRequestCode = 1
        const val REQ_CODE_SPEECH_INPUT = 100
        const val EXTRA_QUIZ = "extra_quiz"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        val quiz = intent.getParcelableExtra<Quiz>(EXTRA_QUIZ)

        bind(quiz)
        initDialog()
        speechRecognition()
        controlSound(currentSong[0])

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bind(quiz: Quiz?) {
        binding.titleBar.text = quiz?.title
        Glide.with(this).load(quiz?.question).into(binding.imgQuestion)
        answer = quiz?.answer!!
        currentSong = mutableListOf(quiz.audio!![0])
    }

    private fun initDialog() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_answer)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT)
        dialog.window?.attributes?.gravity = Gravity.CENTER
        dialog.setCancelable(false)
        btnContinue = dialog.findViewById(R.id.btn_restart)
        btnQuit = dialog.findViewById(R.id.btn_quit)
        tvAnswerStatus = dialog.findViewById(R.id.tv_answer_status)
        tvAnswerDescription = dialog.findViewById(R.id.tv_answer_description)
        btnContinue.setOnClickListener {
            dialog.dismiss()
        }
        btnQuit.setOnClickListener {
            onBackPressed()
            dialog.dismiss()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun speechRecognition() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(
                    this,
                    "Mohon setujui untuk keperluan izin",
                    Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO),
                    RecordAudioRequestCode
                )
            }
        }

        binding.btnMicrophone.setOnClickListener {

            if (viewModel.recordingStatus.value == 0) {
                binding.btnMicrophone.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mic_recording))
                binding.listeningAnimation.visibility = View.VISIBLE
                viewModel.setRecordingStatus(1)
                promptSpeechInput()
                binding.btnMicrophone.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mic_normal))
                binding.listeningAnimation.visibility = View.GONE
                viewModel.setRecordingStatus(0)
            } else {
                binding.btnMicrophone.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mic_normal))
                binding.listeningAnimation.visibility = View.GONE
                viewModel.setRecordingStatus(0)
            }
        }
    }

    private fun checkAnswer(data: String) {
        dialog.show()
        if (data.equals(answer, ignoreCase = true)) {
            tvAnswerStatus.text = resources.getString(R.string.status_correct)
            tvAnswerStatus.setTextColor(ContextCompat.getColor(this, R.color.answer_correct))
            tvAnswerDescription.text = resources.getString(R.string.answer_correct)
        } else {
            tvAnswerStatus.text = resources.getString(R.string.status_wrong)
            tvAnswerStatus.setTextColor(ContextCompat.getColor(this, R.color.answer_wrong))
            tvAnswerDescription.text = resources.getString(R.string.answer_wrong)
        }
    }

    private fun controlSound(id: Int) {
        binding.btnPlayPause.setOnClickListener {
            if (viewModel.playingStatus.value == 0) {
                binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
                viewModel.setPlayingStatus(1)
                if (mediaPlayer == null) {
                   mediaPlayer = MediaPlayer.create(this, id)
                   mediaPlayer!!.isLooping = true
                    initSeekBar()
                }

                mediaPlayer?.start()
            } else {
                binding.btnPlayPause.setImageResource(R.drawable.ic_play)
                viewModel.setPlayingStatus(0)
                if (mediaPlayer != null) {
                    mediaPlayer?.pause()
                }
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun initSeekBar() {
        binding.seekBar.max = mediaPlayer!!.duration
        val handler = Handler()
        handler.postDelayed(object : Runnable {
                    override fun run() {
                        try {
                            binding.seekBar.progress = mediaPlayer!!.currentPosition
                            handler.postDelayed(this, 1000)
                        } catch (e: Exception) {
                            binding.seekBar.progress = 0
                        }
                    }
                }, 0,)
    }

    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            getString(R.string.speech)
        )
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext,
                getString(R.string.not_supported),
                Toast.LENGTH_SHORT
            ).show()
            binding.btnMicrophone.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mic_normal))
            binding.listeningAnimation.visibility = View.GONE
            viewModel.setRecordingStatus(0)
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result: ArrayList<String>? = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    checkAnswer(result!![0])
                    binding.btnMicrophone.imageTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mic_normal))
                    binding.listeningAnimation.visibility = View.GONE
                    viewModel.setRecordingStatus(0)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
}