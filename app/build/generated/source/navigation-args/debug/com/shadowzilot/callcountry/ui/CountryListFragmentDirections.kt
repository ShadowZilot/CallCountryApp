package com.shadowzilot.callcountry.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.shadowzilot.callcountry.R

public class CountryListFragmentDirections private constructor() {
  public companion object {
    public fun actionCountryListFragmentToCountryDetailFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_countryListFragment_to_countryDetailFragment)

    public fun actionCountryListFragmentToPreLearningFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_countryListFragment_to_preLearningFragment)
  }
}
