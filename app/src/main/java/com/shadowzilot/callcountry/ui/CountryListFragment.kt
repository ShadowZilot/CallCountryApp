package com.shadowzilot.callcountry.ui

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.animation.AnimationUtils
import com.shadowzilot.callcountry.ARG_COUNTRY_ID
import com.shadowzilot.callcountry.CountryAdapterView
import com.shadowzilot.callcountry.CountryDetailAction
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.Country
import com.shadowzilot.callcountry.database.CountryLab
import com.shadowzilot.callcountry.databinding.FragmentCountryListBinding


private const val LOG_TAG = "CountryListFragment"

class CountryListFragment: Fragment(), OnItemClickListener {
    private lateinit var mToolBar: Toolbar
    private lateinit var mRoot: FragmentCountryListBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mRoot = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_country_list, container,
                false)

        mRoot.listCountry.setHasFixedSize(true)

        mToolBar = mRoot.root.findViewById(R.id.list_toolbar)
        val act = requireActivity() as AppCompatActivity
        act.setSupportActionBar(mToolBar)

        mRoot.listCountry.layoutManager = LinearLayoutManager(requireActivity())
        mRoot.listCountry.adapter =
                CountryAdapterView(CountryLab.get(requireActivity()).mCountries as ArrayList<Country>,
                    this)
        setHasOptionsMenu(true)

        return mRoot.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val itemMenu = menu.findItem(R.id.action_search)
        val searchView = itemMenu.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val adapter = mRoot.listCountry.adapter as CountryAdapterView
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onItemClick(country: Country) {
        val intent = Intent(requireContext(), ExamDetailActivity().javaClass)

        intent.putExtra(ARG_COUNTRY_ID, country.countryID)
        startActivity(intent)
    }
}