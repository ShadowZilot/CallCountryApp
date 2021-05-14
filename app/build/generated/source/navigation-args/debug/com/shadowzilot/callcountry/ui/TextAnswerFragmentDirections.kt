package com.shadowzilot.callcountry.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.shadowzilot.callcountry.R

public class TextAnswerFragmentDirections private constructor() {
  public companion object {
    public fun actionTextAnswerFragmentToCountryDetailFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_textAnswerFragment_to_countryDetailFragment)
  }
}
