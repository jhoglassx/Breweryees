package com.ciandt.breweryees.ui.userRating

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.breweryees.Model.BreweriesModel
import com.ciandt.breweryees.databinding.UserRatingItemBinding
import com.ciandt.breweryees.ui.DetailsActivity

class RatingListAdapter(private val breweries: List<BreweriesModel>) : RecyclerView.Adapter<RatingListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingListViewHolder {
        return RatingListViewHolder(UserRatingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holderTopTen: RatingListViewHolder, position: Int) {
        holderTopTen.bind(breweries[position])

        holderTopTen.itemView.setOnClickListener{ view ->
            Toast.makeText(view.context, breweries[position].id, Toast.LENGTH_LONG).show()

            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("breweriesId", breweries[position].id)
            view.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = breweries.size




}