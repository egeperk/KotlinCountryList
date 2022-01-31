package com.egeperk.kotlincountrylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.egeperk.kotlincountrylist.R
import com.egeperk.kotlincountrylist.adapter.CountryAdapter
import com.egeperk.kotlincountrylist.viewmodel.CountryListViewModel
import kotlinx.android.synthetic.main.fragment_country_list.*


class CountryListFragment : Fragment() {

    private lateinit var viewModel: CountryListViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)
        viewModel.refreshData()

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = countryAdapter

        swipe_refresh_layout.setOnRefreshListener {
            recycler_view.visibility = View.GONE
            country_error.visibility = View.GONE
            loading_progress_bar.visibility = View.VISIBLE

            swipe_refresh_layout.isRefreshing = false
            viewModel.refreshFromAPI()
        }

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                recycler_view.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    country_error.visibility = View.VISIBLE
                } else {
                    country_error.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    loading_progress_bar.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                    country_error.visibility = View.GONE
                } else {
                    loading_progress_bar.visibility = View.GONE
                }
            }
        })
    }

}