package com.shadowzilot.callcountry.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shadowzilot.callcountry.ARG_COUNTRY_ID
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.COUNTRY_ID_ARGUMENT
import com.shadowzilot.callcountry.database.Country
import com.shadowzilot.callcountry.database.CountryLab
import com.shadowzilot.callcountry.views.MaskedCardView

class FlagAnswersFragment: Fragment() {
    private lateinit var mCountry: Country
    private val mFlagAnswers = mutableListOf<FlagAnswer>()
    private var mIsAnswered = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = requireArguments().get(COUNTRY_ID_ARGUMENT) as Int
        mCountry = CountryLab.get(requireActivity()).getCountryById(id)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.flag_answers_field, container, false)

        val group = view.findViewById<ViewGroup>(R.id.flags_filed)
        for (i in (0 until group.childCount)) {
            val image = group.getChildAt(i) as ImageView
            mFlagAnswers.add(FlagAnswer(image))
        }

        initAnswers()

        return view
    }

    private fun initAnswers() {
        for (item in mFlagAnswers) {
            item.mImage.setOnClickListener {
                checkAnswer(item)
            }
        }
        val correctIndex = (0 until mFlagAnswers.size).random()
        mFlagAnswers[correctIndex].setID(mCountry.countryID, requireActivity())

        val countriesList = CountryLab.get(requireActivity()).mCountries
        val exceptions = mutableListOf<Int>()
        exceptions.add(mFlagAnswers[correctIndex].getID())
        for (i in (0 until mFlagAnswers.size)) {
            if (i != correctIndex) {
                var randomIndex = countriesList.random().countryID
                while (randomIndex in exceptions) {
                    randomIndex = countriesList.random().countryID
                }
                exceptions.add(randomIndex)

                mFlagAnswers[i].setID(randomIndex, requireActivity())
            }
        }
    }

    private fun checkAnswer(item: FlagAnswer) {
        if (mIsAnswered == 0) {
            mIsAnswered = if (item.getID() == mCountry.countryID) {
                item.setBackground(requireContext(), true)
                1
            } else {
                item.setBackground(requireContext(), false)
                for (flag in mFlagAnswers) {
                    if (flag.getID() == mCountry.countryID) {
                        flag.setBackground(requireContext(), true)
                    }
                }
                -1
            }
            sendAnswerResult()
        }
        else {
            val intent = Intent(requireContext(), ExamDetailActivity().javaClass)
            intent.putExtra(ARG_COUNTRY_ID, item.getID())
            startActivity(intent)
        }
    }

    private fun sendAnswerResult() {
        val arg = Intent()
        arg.putExtra(EXTRA_IS_ANSWERED, mIsAnswered)
        targetFragment!!.onActivityResult(IS_CORRECT_REQUEST, Activity.RESULT_OK, arg)
    }
}

class FlagAnswer(_image: ImageView) {
    val mImage = _image
    private var mCountryID: Int = 0

    fun setID(id: Int, context: Context) {
        mCountryID = id
        val country = CountryLab.get(context).getCountryById(id)
        mImage.setImageDrawable(country.getDrawableFlag())
    }

    fun setBackground(context: Context, isCorrect: Boolean) {
        if (isCorrect) {
            mImage.background = context.getDrawable(R.drawable.answer_field_correct)
        } else {
            mImage.background = context.getDrawable(R.drawable.answer_field_wrong)
        }
    }

    fun getID() = mCountryID
}

fun getFlagAnswersFragmentInstance(id: Int): FlagAnswersFragment {
    val args = Bundle()
    args.putInt(COUNTRY_ID_ARGUMENT, id)
    val instance = FlagAnswersFragment()
    instance.arguments = args
    return instance
}