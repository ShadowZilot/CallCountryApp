package com.shadowzilot.callcountry.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.shadowzilot.callcountry.R

public class LearningSummaryFragmentDirections private constructor() {
  public companion object {
    public fun actionLearningSummaryFragmentToCountryListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_learningSummaryFragment_to_countryListFragment)
  }
}
