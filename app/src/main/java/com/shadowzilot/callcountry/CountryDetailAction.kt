package com.shadowzilot.callcountry

import android.os.Bundle
import androidx.navigation.NavDirections

const val ARG_COUNTRY_ID = "country_id"

class CountryDetailAction private constructor(){

    private data class ActionViewDetailCountry(val countryID: Int): NavDirections {
        override fun getActionId(): Int {
            return R.id.action_countryListFragment_to_countryDetailFragment
        }

        override fun getArguments(): Bundle {
            val args = Bundle()
            args.putSerializable(ARG_COUNTRY_ID, countryID)
            return args
        }
    }

    companion object {
        fun actionViewToCountryDetail(countryID: Int): NavDirections {
            return ActionViewDetailCountry(countryID)
        }
    }
}