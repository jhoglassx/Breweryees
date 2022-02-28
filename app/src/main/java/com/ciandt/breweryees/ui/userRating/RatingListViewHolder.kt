package com.ciandt.breweryees.ui.userRating

import android.text.TextUtils
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.breweryees.Model.BreweriesModel
import com.ciandt.breweryees.R
import com.ciandt.breweryees.databinding.TopTenItemBinding
import com.ciandt.breweryees.databinding.UserRatingItemBinding
import com.squareup.picasso.Picasso

class RatingListViewHolder(private val binding: UserRatingItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(breweriesModel: BreweriesModel) = with(binding.root) {

        binding.txtLetra.text = TextUtils.substring(breweriesModel.name.toString(),0, 1)
        binding.txtName.text = breweriesModel.name.toString()
        binding.barRating.rating = breweriesModel.average!!.toFloat()
        binding.txtRating.text = breweriesModel.average.toString()
    }
}