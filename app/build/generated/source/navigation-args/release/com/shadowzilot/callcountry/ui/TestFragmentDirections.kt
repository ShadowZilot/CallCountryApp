package com.shadowzilot.callcountry.ui

import android.os.Bundle
import androidx.navigation.NavDirections
import com.shadowzilot.callcountry.R
import kotlin.Int

public class TestFragmentDirections private constructor() {
  private data class ActionLearningFragmentToLearningSummaryFragment(
    public val amountOfErrors: Int = 0,
    public val myArg: Int = 0
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_learningFragment_to_learningSummaryFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putInt("amountOfErrors", this.amountOfErrors)
      result.putInt("myArg", this.myArg)
      return result
    }
  }

  public companion object {
    public fun actionLearningFragmentToLearningSummaryFragment(amountOfErrors: Int = 0, myArg: Int =
        0): NavDirections = ActionLearningFragmentToLearningSummaryFragment(amountOfErrors, myArg)
  }
}
