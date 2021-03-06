package com.ciandt.breweryees.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ciandt.breweryees.R
import com.ciandt.breweryees.databinding.ActivityDetailsBinding
import com.ciandt.breweryees.databinding.FragmentRatingBinding
import com.ciandt.breweryees.databinding.FragmentRatingErrorBinding
import com.ciandt.breweryees.databinding.FragmentRatingSuccessBinding
import com.ciandt.breweryees.ui.details.DetailsViewModel
import com.ciandt.breweryees.ui.details.GalleryFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var breweriesId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        breweriesId = intent.getStringExtra("breweriesId").toString()
        var breweriesName ="Cervejaria A"

        viewModel.getBreweriesDetails.observe(this){ breweries ->
            binding.detailsItemName.text = breweries.name
            breweriesName = breweries.name.toString()
            //binding.detailsOpinionLetter.text = breweries.photos.toString()
            binding.detailsItemRating.text = breweries.average.toString()
            binding.detailsItemType.text = breweries.brewery_type
            binding.detailsItemStar.rating = breweries.average!!.toFloat()
            binding.detailsItemAvaliation.text = breweries.size_evaluations.toString()
            binding.detailsItemSite.text = breweries.website_url
            binding.detailsItemAdress.text = "${breweries.street} ${breweries.address2} ${breweries.address3} ${breweries.city} ${breweries.state} ${breweries.postal_code}"
            fragmentManagerCommit(breweriesId)
        }

        viewModel.getDetails(breweriesId)

        binding.btnRating.setOnClickListener{
            showRatingDialog(breweriesName)
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    private fun showRatingDialog(breweriesName: String) {
        val dialog = dialog()
        val sheetBind: FragmentRatingBinding = FragmentRatingBinding.inflate(layoutInflater)
        dialog.dismiss()
        dialog.setContentView(sheetBind.root)
        dialog.show()

        sheetBind.txtTitle.text = breweriesName

        sheetBind.btnSave.setOnClickListener{
            saveRating(sheetBind,dialog)
            dialog.dismiss()
        }
        sheetBind.btnExit.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun saveRating(sheetBind: FragmentRatingBinding, dialog: BottomSheetDialog) {
        val email = sheetBind.txtEmail.text.toString()
        val rating = sheetBind.ratingBar.rating.toDouble()

        viewModel.setRating(email,breweriesId,rating)

        viewModel.setBreweriesRating.observe(this){breweries ->
            if(breweries.email != null) {
                dialog.dismiss()
                dialogSuccess(dialog)
            }else{
                dialog.dismiss()
                dialogError(dialog)
            }
        }
    }

    private fun dialog(): BottomSheetDialog{
        return BottomSheetDialog(this, R.style.BottomSheetDialog).apply {
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }

    private fun dialogSuccess(dialog: BottomSheetDialog){
        val sheetSuccessBind: FragmentRatingSuccessBinding = FragmentRatingSuccessBinding.inflate(layoutInflater,null,false)
        dialog.setContentView(sheetSuccessBind.root)
        dialog.show()
    }

    fun dialogError(dialog: BottomSheetDialog){
        val sheetErrorBind: FragmentRatingErrorBinding = FragmentRatingErrorBinding.inflate(layoutInflater,null,false)
        dialog.setContentView(sheetErrorBind.root)
        dialog.show()

        sheetErrorBind.btnExit.setOnClickListener {
           dialog.dismiss()
        }
    }

    private fun fragmentManagerCommit(breweriesId: String) {
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("breweriesId", breweriesId)
            replace<GalleryFragment>(R.id.detailsFragment, args = bundle)
        }
    }



}