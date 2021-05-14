package com.shadowzilot.callcountry

import android.content.Context
import android.graphics.drawable.Drawable
import android.media.Image
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shadowzilot.callcountry.database.Country
import com.shadowzilot.callcountry.ui.CountryListFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

private const val LOG_TAG = "CountryViewModel"

class CountryViewModel(private val mContext: Context,
                       private var mCountry: Country? = null): BaseObservable() {
    fun setCountry(country: Country) {
        mCountry = country
        notifyChange()
    }

    @Bindable
    fun getName() = mCountry!!.countryName

    @Bindable
    fun getCapitalName() = mCountry!!.countryCapital

    @Bindable
    fun getDrawableFlag(): Drawable? {
        return mCountry!!.getDrawableFlag()
    }

    @Bindable
    fun getWorldPart() = mCountry!!.getWorldPartName(mContext)

    @Bindable
    fun getWorldPartColor() = mCountry!!.getWorldPartColor(mContext)
}