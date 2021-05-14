package com.shadowzilot.callcountry.ui

import android.os.Bundle
import androidx.navigation.NavArgs
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class LearningSummaryFragmentArgs(
  public val myArg: Int = 0
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("myArg", this.myArg)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): LearningSummaryFragmentArgs {
      bundle.setClassLoader(LearningSummaryFragmentArgs::class.java.classLoader)
      val __myArg : Int
      if (bundle.containsKey("myArg")) {
        __myArg = bundle.getInt("myArg")
      } else {
        __myArg = 0
      }
      return LearningSummaryFragmentArgs(__myArg)
    }
  }
}
