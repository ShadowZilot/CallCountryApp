package com.shadowzilot.callcountry.ui

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.shadowzilot.callcountry.ARG_COUNTRY_ID
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.*


const val EXTRA_IS_ANSWERED = "extra_is_answered"

class TextAnswerFragment : Fragment() {
    private val sLogTag = this.javaClass.simpleName
    private lateinit var mAnswersField: ViewGroup
    private val mListAnswers = mutableMapOf<AppCompatButton, Country?>();
    private lateinit var mCorrectView: TextView
    private lateinit var mCountry: Country
    private lateinit var mTestKind: String

    private lateinit var mCorrectDrawable: Drawable
    private lateinit var mWrongDrawable: Drawable

    private var mIsAnswered = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCountry = CountryLab.get(requireActivity()).getCountryById(
            requireArguments().getInt(COUNTRY_ID_ARGUMENT)
        )
        mTestKind = requireArguments().getString(TEST_KIND_ARGUMENT)!!

        mCorrectDrawable = requireContext().resources.getDrawable(
            R.drawable.answer_field_correct,
            null
        )
        mWrongDrawable = requireContext().resources.getDrawable(
            R.drawable.answer_field_wrong,
            null
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_answer_field, container, false)

        mAnswersField = view.findViewById(R.id.answer_filed)
        determineInitializer()

        return view
    }

    private fun determineInitializer() {
        // Find buttons and set listeners
        for (i in 0 until mAnswersField.childCount) {
            mListAnswers[mAnswersField.getChildAt(i) as AppCompatButton] = null
            // Setting listeners for check answer
            mAnswersField.getChildAt(i).setOnClickListener {
                if (mTestKind == NAME_TEST) {
                    checkNameAnswer(it as AppCompatButton)
                } else if (mTestKind == CAPITAL_TEST) {
                    checkCapitalAnswer(it as AppCompatButton)
                }
            }
        }

        // Random select button for displaying correct answer
        mCorrectView = mListAnswers.keys.random()
        mListAnswers[mCorrectView as AppCompatButton] = mCountry

        val countriesList = CountryLab.get(requireActivity()).mCountries
        val exceptions = mutableListOf<Int>()
        exceptions.add(mCountry.countryID)

        // Initialize all buttons by random countries excluding current country
        for (j in 0 until mAnswersField.childCount) {
            if (mAnswersField.getChildAt(j) != mCorrectView) {

                var randomID = countriesList.random().countryID
                while (randomID in exceptions) {
                    randomID = countriesList.random().countryID
                }

                mListAnswers[mAnswersField.getChildAt(j) as AppCompatButton] =
                    CountryLab.get(requireActivity()).getCountryById(randomID)
                exceptions.add(randomID)
            }
        }

        // Set text on buttons in depends on kind of question
        if (mTestKind == NAME_TEST) {
            initNameAnswers()
        } else if (mTestKind == CAPITAL_TEST) {
            initCapitalAnswers()
        }
    }

    private fun initNameAnswers() {
        for (key in mListAnswers.keys) {
            val name = mListAnswers[key]!!.countryName
            key.text = name
        }
    }

    private fun initCapitalAnswers() {
        for (key in mListAnswers.keys) {
            val name = mListAnswers[key]!!.countryCapital
            key.text = name
        }
    }

    private fun checkCapitalAnswer(view: AppCompatButton) {
        if (mIsAnswered == 0) {
            if (view.text == mCountry.countryCapital) {
                view.background = mCorrectDrawable
                mIsAnswered = 1
            } else {
                view.background = mWrongDrawable
                mCorrectView.background = mCorrectDrawable
                mIsAnswered = -1
            }
            sendAnswerResult()
        } else {
            val intent = Intent(requireContext(), ExamDetailActivity().javaClass)
            intent.putExtra(ARG_COUNTRY_ID, mListAnswers[view]!!.countryID)
            startActivity(intent)
        }
    }

    private fun checkNameAnswer(view: AppCompatButton) {
        if (mIsAnswered == 0) {
            if (view.text == mCountry.countryName) {
                view.background = mCorrectDrawable
                mIsAnswered = 1
            } else {
                view.background = mWrongDrawable
                mCorrectView.background = mCorrectDrawable
                mIsAnswered = -1
            }
            sendAnswerResult()
        } else {
            val intent = Intent(requireContext(), ExamDetailActivity().javaClass)

            intent.putExtra(ARG_COUNTRY_ID, mListAnswers[view]!!.countryID)
            startActivity(intent)
        }
    }

    private fun sendAnswerResult() {
        val args = Intent()
        args.putExtra(EXTRA_IS_ANSWERED, mIsAnswered)
        targetFragment!!.onActivityResult(IS_CORRECT_REQUEST, Activity.RESULT_OK, args)
    }
}


fun getTextAnswerFragmentInstance(id: Int, testKind: String): TextAnswerFragment {
    val args = Bundle()
    args.putInt(COUNTRY_ID_ARGUMENT, id)
    args.putString(TEST_KIND_ARGUMENT, testKind)

    val instance = TextAnswerFragment()
    instance.arguments = args

    return instance
}