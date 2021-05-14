package com.shadowzilot.callcountry.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.shadowzilot.callcountry.ARG_COUNTRY_ID
import com.shadowzilot.callcountry.R

class ExamDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_detail)
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()

        val id = intent.getIntExtra(ARG_COUNTRY_ID, 0)
        val fragment = getCountryDetailFragmentInstance(id)
        transaction.replace(R.id.exam_detail_host, fragment).commit()
    }
}