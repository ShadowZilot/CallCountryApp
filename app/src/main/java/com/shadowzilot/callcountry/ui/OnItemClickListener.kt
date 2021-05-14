package com.shadowzilot.callcountry.ui

import com.shadowzilot.callcountry.database.Country

interface OnItemClickListener {
    fun onItemClick(country: Country)
}