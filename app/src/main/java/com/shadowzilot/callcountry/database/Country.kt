package com.shadowzilot.callcountry.database

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.ImageReader
import android.util.Log
import androidx.core.content.ContextCompat
import com.shadowzilot.callcountry.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.lang.StringBuilder

private const val LOG_TAG = "Country"

class Country(countryID_: Int,
              countryName_: String,
              countryCapital_: String,
              countryFlag_: String,
              worldPartCode_: Int, population_: String,
              square_: String,
              language_: String,
              currency_: String,
              formOfGovernment_: String,
              religion_: String,
              context: Context): Comparable<Country> {
    val countryID: Int = countryID_
    val countryName: String = countryName_
    val countryCapital: String = countryCapital_
    val countryFlag: String = countryFlag_
    private val worldPartCode: Int = worldPartCode_
    val population: String = population_
    val square: String = square_
    val language: String = language_
    val currency: String = currency_
    val formOfGovernment: String = formOfGovernment_
    val religion: String = religion_
    val context: Context = context

    override fun toString(): String {
        val builder = StringBuilder()

        builder.append(countryID)
        builder.append(": ")
        builder.append("$countryName, ")
        builder.append(countryCapital)

        return builder.toString()
    }

    fun getWorldPartCode(): Int {
        return worldPartCode
    }

    fun getWorldPartColor(context: Context): Int {
        val color_array = context.resources.getIntArray(R.array.world_parts_colors)
        return color_array[worldPartCode]
    }

    fun getWorldPartName(context: Context): String {
        val world_parts = context.resources.getStringArray(R.array.world_parts)
        return world_parts[worldPartCode]
    }

    fun getDrawableFlag(): Drawable? {
        val manager = context.assets
        return try {
            val stream = manager.open("flags/${countryFlag}.png")
            Drawable.createFromStream(stream, null)
        } catch (e: FileNotFoundException) {
            Log.e(LOG_TAG, e.message!!)
            null
        }
    }

    override fun compareTo(other: Country) =
        when {
           this.countryName > other.countryName -> 1
            this.countryName < other.countryName -> -1
           else -> 0
        }
}