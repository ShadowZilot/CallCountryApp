package com.shadowzilot.callcountry.ui

import android.animation.ObjectAnimator
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.CountryLab
import com.shadowzilot.callcountry.ui.GameWorldPartsSummary.APP_WPG_HIGH_SCORE
import com.shadowzilot.callcountry.views.LearningProgress
import com.shadowzilot.callcountry.views.MaskedCardView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class PreLearningFragment: Fragment() {
    private val LOG_TAG = PreLearningFragment::class.simpleName
    private val EXTRA_STARTED = "isMiniGameStarted"

    private lateinit var mLearningProgress: LearningProgress
    private lateinit var mDatabase: CountryLab
    private lateinit var mProgressLabel: TextView
    private lateinit var mJob: Job
    private lateinit var mHighWPG: TextView
    private lateinit var mBarrierTable: ViewGroup

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_pre_learning, container, false)

        mDatabase = CountryLab.get(requireContext())
        mProgressLabel = root.findViewById(R.id.learning_progress_label)
        mLearningProgress = root.findViewById(R.id.learning_progress)
        mBarrierTable = root.findViewById(R.id.wpg_barrier_table)

        if (mDatabase.getAmountOfLearnedCountries() >= 30) {
            mBarrierTable.visibility = View.GONE

            root.findViewById<MaskedCardView>(R.id.world_part_start).setOnClickListener {
                val intent = Intent(requireContext(), WorldPartGameActivity().javaClass)
                startActivity(intent)
            }
        }

        root.findViewById<AppCompatButton>(R.id.memory_start).setOnClickListener {
            findNavController().navigate(R.id.action_preLearningFragment_to_learningActivity)
        }

        mHighWPG = root.findViewById(R.id.wpg_high_score_label)

        return root
    }

    override fun onResume() {
        super.onResume()
        setHighScoreLabel()
        mJob = GlobalScope.launch {
            updateProgress()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }

    private fun setHighScoreLabel() {
        val highScore = try {
            val preferences = requireActivity().getSharedPreferences("records", MODE_PRIVATE)
            if (preferences.contains(APP_WPG_HIGH_SCORE)) {
                preferences.getInt(APP_WPG_HIGH_SCORE, 0)
            } else {
                0
            }
        } catch (_e: IOException) {
            _e.printStackTrace()
            0
        }

        mHighWPG.text = String.format(getString(R.string.high_score_pattern), highScore)
    }

    private suspend fun updateProgress() {
        val countriesAmount = mDatabase.mCountries.size
        val progress = mDatabase.getLearnedPercent()
        try {
            requireActivity().runOnUiThread {
                mProgressLabel.text = getString(R.string.progress_label).format(
                    mDatabase.getAmountOfLearnedCountries(),
                    countriesAmount
                )

                mLearningProgress.setMax(1f)
                val animator = ObjectAnimator.ofFloat(
                    mLearningProgress,
                    "progress", 0f, progress
                )
                animator.start()
            }
        } catch (e: IllegalStateException) {
            Log.w(LOG_TAG, "Warring ! Fragment is already detached!")
        }
    }
}