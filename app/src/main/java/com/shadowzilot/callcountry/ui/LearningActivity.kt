package com.shadowzilot.callcountry.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.LearningList

private const val LOG_TAG = "LearningActivity"

class LearningActivity: AppCompatActivity() {
    private lateinit var dialog: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)

        val learningInstance = getLearningFragmentInstance()
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()

        transaction.replace(R.id.learning_host, learningInstance).commit()
        dialog = AlertDialog.Builder(this)
        makeDialog()
    }

    private fun makeDialog() {
        dialog.setTitle(R.string.dialog_label)

        dialog.setPositiveButton(R.string.positive_dialog) {_, _ ->
            finish()
        }

        dialog.setNegativeButton(R.string.negative_dialog) {_, _ ->

        }
        dialog.setCancelable(true)
        dialog.create()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        val last = fm.fragments[fm.fragments.size-1].javaClass
        Log.d(LOG_TAG, last.name)
        if (last == FlagAnswersFragment().javaClass || last == TextAnswerFragment().javaClass) {
            dialog.show()
        } else {
            finish()
        }
    }
}