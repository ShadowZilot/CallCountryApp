package com.shadowzilot.callcountry

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shadowzilot.callcountry.database.Country
import com.shadowzilot.callcountry.databinding.CountryListItemBinding
import com.shadowzilot.callcountry.ui.OnItemClickListener

private const val LOG_TAG = "CountryHolder"

class CountryHolder(item: View): RecyclerView.ViewHolder(item) {
    private lateinit var mBinding: CountryListItemBinding

    constructor(binding: CountryListItemBinding): this(binding.root) {
        mBinding = binding
        mBinding.viewModel = CountryViewModel(binding.root.context)
    }

    fun bind(country: Country, clickListener: OnItemClickListener) {
        mBinding.viewModel!!.setCountry(country)
        mBinding.executePendingBindings()

        itemView.setOnClickListener {
            clickListener.onItemClick(country)
        }
    }
}