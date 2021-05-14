package com.shadowzilot.callcountry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textview.MaterialTextView
import com.shadowzilot.callcountry.ARG_COUNTRY_ID
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.Country
import com.shadowzilot.callcountry.database.CountryLab

private const val LOG_TAG = "CountryDetailFragment"

class CountryDetailFragment: Fragment() {
    private lateinit var mCountry: Country
    private lateinit var mGovernmentInfo: TextView
    private lateinit var mReligionInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.get(ARG_COUNTRY_ID) as Int
        mCountry = CountryLab.get(requireActivity()).getCountryById(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_detail,
            container, false)

        view.findViewById<MaterialTextView>(R.id.country_name_label).text = mCountry.countryName

        view.findViewById<MaterialTextView>(R.id.capital_name_label).text = mCountry.countryCapital

        view.findViewById<ImageView>(R.id.detail_country_flag)
            .setImageDrawable(mCountry.getDrawableFlag())

        view.findViewById<MaterialTextView>(R.id.population_info)
                .text = mCountry.population

        view.findViewById<MaterialTextView>(R.id.square_info)
                .text = mCountry.square

        view.findViewById<MaterialTextView>(R.id.world_info)
            .text = mCountry.getWorldPartName(requireActivity())

        view.findViewById<MaterialTextView>(R.id.language_info)
                .text = mCountry.language

        view.findViewById<MaterialTextView>(R.id.currency_info)
                .text = mCountry.currency

        mGovernmentInfo = view.findViewById<MaterialTextView>(R.id.form_of_government_info)
        mGovernmentInfo.text = mCountry.formOfGovernment
        mGovernmentInfo.setOnClickListener {
            mGovernmentInfo.isSingleLine = mGovernmentInfo.maxLines != 1
        }

        mReligionInfo = view.findViewById<MaterialTextView>(R.id.religion_info)
        mReligionInfo.text = mCountry.religion
        mReligionInfo.setOnClickListener {
            mReligionInfo.isSingleLine = mReligionInfo.maxLines != 1
        }

        view.findViewById<TextView>(R.id.web_info_reference)
            .text = "${getString(R.string.wiki_additional_info)}" +
                " ${getString(R.string.wiki_web_pattern)}${mCountry.countryName
                    .replace(" ", "_")}"

        return view
    }
}

fun getCountryDetailFragmentInstance(id: Int): CountryDetailFragment {
    val args = Bundle()
    args.putInt(ARG_COUNTRY_ID, id)
    val instance = CountryDetailFragment()
    instance.arguments = args
    return instance
}
