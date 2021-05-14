package com.shadowzilot.callcountry.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.shadowzilot.callcountry.ARG_COUNTRY_ID
import com.shadowzilot.callcountry.CountryAdapterView
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.Country
import com.shadowzilot.callcountry.database.CountryLab
import com.shadowzilot.callcountry.database.LENGTH_LIST
import com.shadowzilot.callcountry.views.MarkView

private const val LOG_TAG = "LearningSummaryFragment"

class LearningSummaryFragment: Fragment(), OnItemClickListener {
    private lateinit var mMarkView: MarkView
    private lateinit var mAppBar: Toolbar
    private lateinit var mErrorsView: RecyclerView

    private lateinit var mNoErrorsImage: ImageView
    private lateinit var mNoErrorsText: TextView

    private val mErrorsList = ArrayList<Country>()
    private var mMark: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val errors = requireArguments().getInt(ERRORS_ARGUMENT)
        mMark = kotlin.math.abs(
                (((errors.toFloat() / LENGTH_LIST.toFloat()) * 100) - 100).toInt()
        )
        val errorsIds = requireArguments().getIntArray(ERRORS_ID_LIST)!!
        val database = CountryLab.get(requireContext())

        for (id in errorsIds) {
            mErrorsList.add(database.getCountryById(id))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning_summary,
            container, false)
        mMarkView = view.findViewById(R.id.summary_mark)
        mAppBar = view.findViewById(R.id.summary_tool_bar)

        mNoErrorsImage = view.findViewById(R.id.no_errors_image)
        mNoErrorsText = view.findViewById(R.id.no_errors_label)
        mErrorsView = view.findViewById(R.id.list_of_wrongs)

        if (mErrorsList.isNotEmpty()) {
            mErrorsView.layoutManager = LinearLayoutManager(requireContext())
            mErrorsView.adapter = CountryAdapterView(mErrorsList, this)

        } else {
            mErrorsView.visibility = View.GONE
            mNoErrorsText.visibility = View.VISIBLE
            mNoErrorsImage.visibility = View.VISIBLE
        }

        mAppBar.setNavigationOnClickListener {
            requireActivity().finish()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animator = ObjectAnimator.ofFloat(mMarkView, "mark",
            0f, mMark.toFloat())
        animator.interpolator = DecelerateInterpolator(1.5f)
        animator.duration = 2500
        animator.startDelay = 200
        animator.start()
    }

    override fun onItemClick(country: Country) {
        val intent = Intent(requireContext(), ExamDetailActivity().javaClass)
        intent.putExtra(ARG_COUNTRY_ID, country.countryID)
        startActivity(intent)
    }
}

fun getLearningSummaryFragmentInstance(amountOfErrors: Int, listOfErrors: IntArray): LearningSummaryFragment {
    val args = bundleOf(ERRORS_ARGUMENT to amountOfErrors)
    args.putIntArray(ERRORS_ID_LIST, listOfErrors)
    val instance = LearningSummaryFragment()
    instance.arguments = args

    return instance
}