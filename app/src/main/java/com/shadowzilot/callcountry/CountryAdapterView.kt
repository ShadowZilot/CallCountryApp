package com.shadowzilot.callcountry

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shadowzilot.callcountry.database.Country
import com.shadowzilot.callcountry.databinding.CountryListItemBinding
import com.shadowzilot.callcountry.ui.OnItemClickListener

private const val LOG_TAG = "CountryAdapterView"

class CountryAdapterView(): RecyclerView.Adapter<CountryHolder>(), Filterable {
    private lateinit var mCountrys: ArrayList<Country>
    private lateinit var mCountrysFull: ArrayList<Country>
    private lateinit var mItemClickListener: OnItemClickListener

    constructor(countrys: ArrayList<Country>,
                itemClickListener: OnItemClickListener
    ): this() {
        mCountrys = countrys
        mCountrysFull = ArrayList(mCountrys)
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CountryListItemBinding>(inflater,
                R.layout.country_list_item, parent, false)

        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(mCountrysFull[position], mItemClickListener)
    }

    override fun getItemCount(): Int {
        return mCountrysFull.size
    }

    private val exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = ArrayList<Country>()

            if (constraint.isEmpty()) {
                filteredList.addAll(mCountrys)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                for (item in mCountrys) {
                    if (item.countryName.toLowerCase().contains(filterPattern)
                            || item.countryCapital.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val result = FilterResults()
            result.values = filteredList

            return result
        }

        override fun publishResults(constraint: CharSequence?,
                                    results: FilterResults?) {
            mCountrysFull.clear()
            mCountrysFull.addAll(results!!.values as Collection<Country>)
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }

}