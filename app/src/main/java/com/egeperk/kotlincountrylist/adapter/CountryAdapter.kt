package com.egeperk.kotlincountrylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.egeperk.kotlincountrylist.R
import com.egeperk.kotlincountrylist.databinding.ItemCountryRowBinding
import com.egeperk.kotlincountrylist.model.Country
import com.egeperk.kotlincountrylist.util.downloadFromUrl
import com.egeperk.kotlincountrylist.util.placeHolderProgressBar
import com.egeperk.kotlincountrylist.view.CountryListFragmentDirections
import kotlinx.android.synthetic.main.fragment_country_detail.view.*
import kotlinx.android.synthetic.main.item_country_row.view.*

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {

    class CountryViewHolder(var view: ItemCountryRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        //val view = inflater.inflate(R.layout.item_country_row, parent, false)

        val view = DataBindingUtil.inflate<ItemCountryRowBinding>(inflater,R.layout.item_country_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this

        /*
          holder.view.country_text_view.text = countryList[position].countryName
        holder.view.country_region_text.text = countryList[position].countryRegion

        holder.itemView.setOnClickListener {
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(countryList[position].uuid)
            //action.countryuuid

            Navigation.findNavController(it).navigate(action)
        }

        holder.view.country_image_view.downloadFromUrl(countryList[position].imageUrl,
            placeHolderProgressBar(holder.view.context))
         */
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClick(v: View) {
        val uuid = v.uuid_tv.text.toString().toInt()
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(uuid)
            //action.countryuuid
            Navigation.findNavController(v).navigate(action)
    }

}