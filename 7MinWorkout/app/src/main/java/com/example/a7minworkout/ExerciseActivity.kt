package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding : ActivityExerciseBinding? = null

    private var restTimer : CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar != null)    {
            //setup the back button on the toolbar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }
        setupRestView()
    }

    private fun setupRestView() {
        if(restTimer != null)   {
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setupExerciseView() {
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.text = "Exercise name"
        binding?.flExerciseView?.visibility = View.VISIBLE
        if(exerciseTimer != null)   {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
    }

    private fun setRestProgressBar()    {
        binding?.progressBar?.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000)    {
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                setupExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar()    {
        binding?.progressBarExercise?.progress = exerciseProgress
        restTimer = object : CountDownTimer(30000, 1000)    {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "You have finished your exercise", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null)   {
            restTimer?.cancel()
            restProgress = 0
        }
        if(exerciseTimer != null)   {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding = null
    }
}