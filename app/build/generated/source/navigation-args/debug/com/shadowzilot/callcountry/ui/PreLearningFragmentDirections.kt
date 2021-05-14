package com.shadowzilot.callcountry.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.shadowzilot.callcountry.R

public class PreLearningFragmentDirections private constructor() {
  public companion object {
    public fun actionPreLearningFragmentToLearningActivity(): NavDirections =
        ActionOnlyNavDirections(R.id.action_preLearningFragment_to_learningActivity)
  }
}
