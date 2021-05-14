package com.shadowzilot.callcountry.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.shadowzilot.callcountry.R

public class FlagAnswersFragmentDirections private constructor() {
  public companion object {
    public fun actionFlagAnswersFragmentToCountryDetailFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_flagAnswersFragment_to_countryDetailFragment)
  }
}
