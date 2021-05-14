package com.shadowzilot.callcountry.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.LearningList

const val REQUEST_END_LEARN = 5

class LearningFragment : Fragment() {
    private lateinit var learningList: LearningList
    private lateinit var mFManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        learningList = LearningList(requireActivity())
        mFManager = requireActivity().supportFragmentManager
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_learnig,
                container, false)

        val transaction = mFManager.beginTransaction()
        val fragment = getTestFragmentInstance(learningList)
        fragment.setTargetFragment(this, REQUEST_END_LEARN)
        transaction.replace(R.id.test_host, fragment).commit()

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_END_LEARN) {
                val errors = data!!.getIntExtra(ERRORS_ARGUMENT, 0)
                val errorsList = data.getIntArrayExtra(ERRORS_ID_LIST)
                val transaction = mFManager.beginTransaction()
                val fragment = getLearningSummaryFragmentInstance(errors, errorsList!!)
                transaction.replace(R.id.test_host, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}

fun getLearningFragmentInstance(): LearningFragment {
    return LearningFragment()
}