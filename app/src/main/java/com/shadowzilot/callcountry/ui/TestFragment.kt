package com.shadowzilot.callcountry.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textview.MaterialTextView
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.*
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.CAPITAL_LEVEL
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.FLAG_LEVEL
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.NAME_LEVEL


const val EXTRA_REFERENCE = "EXTRA_REFERENCE"
const val ERRORS_ARGUMENT = "errors_argument"
const val ERRORS_ID_LIST = "errors_id_list"

const val IS_CORRECT_REQUEST = 0

class TestFragment : Fragment() {
    private lateinit var mReference: LearningList
    private var mErrors = 0
    private val mListErrors = mutableListOf<Int>()

    private lateinit var mVibrator: Vibrator

    private lateinit var mFlag: ImageView
    private lateinit var mName: TextView
    private lateinit var mCapital: TextView
    private lateinit var mWorldPartLabel: TextView
    private lateinit var mTestToolBar: TextView

    private lateinit var mCurrentCountry: Country
    private lateinit var mCurrentQuestionKind: String

    private lateinit var mNextButton: ImageButton
    private lateinit var mRoot: View

    private lateinit var mFManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mReference = requireArguments().get(EXTRA_REFERENCE) as LearningList
        mVibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)

        mFManager = requireActivity().supportFragmentManager
        mNextButton = view.findViewById(R.id.learning_next_button)
        mNextButton.setOnClickListener {
            resetScreen()
        }

        mWorldPartLabel = view.findViewById(R.id.word_part_test)
        mFlag = view.findViewById(R.id.learning_flag)
        mName = view.findViewById(R.id.country_name_learning)
        mCapital = view.findViewById(R.id.country_capital_learning)
        initNextQuestion()
        mRoot = view

        return view
    }

    private fun initNextQuestion() {
        try {
            val args = mReference.getNext()
            mCurrentCountry = CountryLab.get(requireActivity()).getCountryById(
                    args.getInt(COUNTRY_ID_ARGUMENT))
            mCurrentQuestionKind = args.getString(TEST_KIND_ARGUMENT)!!
        } catch (e: Exception) {
            val intent = Intent()
            intent.putExtra(ERRORS_ARGUMENT, mErrors)
            intent.putExtra(ERRORS_ID_LIST, mListErrors.toIntArray())
            targetFragment!!.onActivityResult(REQUEST_END_LEARN,
                    Activity.RESULT_OK, intent)
            return
        }

        initAnswerFragment(mCurrentCountry.countryID, mCurrentQuestionKind)

        mWorldPartLabel.text = mCurrentCountry.getWorldPartName(requireContext())
        mWorldPartLabel.setTextColor(mCurrentCountry.getWorldPartColor(requireContext()))

        if (mCurrentQuestionKind == NAME_TEST) {
            mName.text = "???"
            mCapital.text = mCurrentCountry.countryCapital
            mFlag.setImageDrawable(mCurrentCountry.getDrawableFlag())
        }
        if (mCurrentQuestionKind == CAPITAL_TEST) {
            mName.text = mCurrentCountry.countryName
            mCapital.text = "???"
            mFlag.setImageDrawable(mCurrentCountry.getDrawableFlag())
        }
        if (mCurrentQuestionKind == FLAG_TEST) {
            mName.text = mCurrentCountry.countryName
            mCapital.text = mCurrentCountry.countryCapital
            mFlag.setImageDrawable(requireContext().getDrawable(R.drawable.ic_unknow))
        }
    }

    private fun initAnswerFragment(id: Int, testKind: String) {
        if (testKind == NAME_TEST || testKind == CAPITAL_TEST) {
            val transaction = mFManager.beginTransaction()
            val fragment = getTextAnswerFragmentInstance(
                    mCurrentCountry.countryID, testKind)
            fragment.setTargetFragment(this, IS_CORRECT_REQUEST)
            transaction.replace(R.id.answer_host, fragment).commit()
        } else {
            val transaction = mFManager.beginTransaction()
            val fragment = getFlagAnswersFragmentInstance(mCurrentCountry.countryID)
            fragment.setTargetFragment(this, IS_CORRECT_REQUEST)
            transaction.replace(R.id.answer_host, fragment).commit()
        }
    }

    private fun resetScreen() {
        mNextButton.visibility = View.GONE
        initNextQuestion()
    }

    private fun changeLevel(different: Int) {
        val base = CountryLab.get(requireActivity())
        when (mCurrentQuestionKind) {
            NAME_TEST -> {
                mName.text = mCurrentCountry.countryName
                base.changeCountryLevel(mCurrentCountry.countryID,
                        NAME_LEVEL, different)
            }

            CAPITAL_TEST -> {
                mCapital.text = mCurrentCountry.countryCapital
                base.changeCountryLevel(mCurrentCountry.countryID,
                        CAPITAL_LEVEL, different)
            }

            FLAG_TEST -> {
                base.changeCountryLevel(mCurrentCountry.countryID,
                        FLAG_LEVEL, different)
                mFlag.setImageDrawable(mCurrentCountry.getDrawableFlag())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IS_CORRECT_REQUEST) {
                val is_correct_answer = data!!.extras!!.get(EXTRA_IS_ANSWERED) as Int
                if (is_correct_answer == 1) {
                    changeLevel(-1)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        mVibrator.vibrate(VibrationEffect.createOneShot(
                            100, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        mVibrator.vibrate(100)
                    }
                    changeLevel(1)
                    mErrors++
                    mListErrors.add(mCurrentCountry.countryID)
                }
            }
        }
        mNextButton.visibility = VISIBLE
    }
}

fun getTestFragmentInstance(reference: LearningList): TestFragment {
    val instance = TestFragment()
    val args = Bundle()
    args.putBinder(EXTRA_REFERENCE, reference)
    instance.arguments = args
    return instance
}