package com.egeperk.kotlincountrylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.egeperk.kotlincountrylist.R
import com.egeperk.kotlincountrylist.databinding.FragmentCountryDetailBinding
import com.egeperk.kotlincountrylist.util.downloadFromUrl
import com.egeperk.kotlincountrylist.util.placeHolderProgressBar
import com.egeperk.kotlincountrylist.viewmodel.CountryDetailViewModel
import kotlinx.android.synthetic.main.fragment_country_detail.*
import kotlinx.android.synthetic.main.item_country_row.*

class CountryDetailFragment : Fragment() {

    private lateinit var viewModel : CountryDetailViewModel

    private var countryUuid = 0

    private lateinit var dataBinding : FragmentCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_detail,container,false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryDetailFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)


        observeLiveData()


    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = it
                /*
                country_name_tv.text = country.countryName
                country_capital_tv.text = country.countryCapital
                country_region_tv.text = country.countryRegion
                country_currency_tv.text = country.countryCurrency
                country_language_tv.text = country.countryLanguage
                context?.let {
                    country_detail_view.downloadFromUrl(country.imageUrl, placeHolderProgressBar(it))

                 */
                }

            })
        }
    }


