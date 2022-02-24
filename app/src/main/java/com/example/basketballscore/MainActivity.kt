package com.example.basketballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basketballscore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.localScore.observe(this, Observer {
            localScoreValue ->
            binding.localScoreText.text=localScoreValue.toString()

        })

        viewModel.visitorScore.observe(this, Observer {
                visitorScoreValue ->
            binding.localScoreText.text=visitorScoreValue.toString()

        })

        setupButtons()
    }

    private fun setupButtons() {
        binding.localMinusButton.setOnClickListener {
        viewModel.decreaseLocalScore()

        }

        binding.localPlusButton.setOnClickListener {
            addPointsToScore(1, isLocal = true)
        }

        binding.localTwoPointsButton.setOnClickListener {
            addPointsToScore(2, isLocal = true)
        }

        binding.visitorMinusButton.setOnClickListener {
           viewModel.decreaseVisitorScore()

        }

        binding.visitorPlusButton.setOnClickListener {
            addPointsToScore(1, isLocal = false)
        }

        binding.visitorTwoPointsButton.setOnClickListener {
            addPointsToScore(2, isLocal = false)
        }

        binding.restartButton.setOnClickListener {
            viewModel.resetScores()

        }

        binding.resultsButton.setOnClickListener {
            startScoreActivity()
        }
    }



    private fun addPointsToScore(points: Int, isLocal: Boolean) {
        viewModel.addPointsToScore(points, isLocal)
    }

    private fun startScoreActivity() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, viewModel.localScore.value)
        intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, viewModel.visitorScore.value)
        startActivity(intent)
    }
}