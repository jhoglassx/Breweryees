package com.ciandt.breweryees.ui.details

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.breweryees.R
import com.ciandt.breweryees.databinding.FragmentGalleryBinding
import com.ciandt.breweryees.repository.BreweriesRepository
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.*


class GalleryFragment : Fragment() {

    private var _binding : FragmentGalleryBinding? = null
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        bundle?.getString("breweries_id").toString().apply {
            galleryRepositories(this)
        }
    }

    private fun galleryRepositories(breweriesId:String){
        val galleryFragmentJob : CompletableJob = Job()

        val errorHandler  = CoroutineExceptionHandler{ _, exception ->
            AlertDialog.Builder(context).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        val coroutineScope = CoroutineScope(galleryFragmentJob + Dispatchers.Main)
        coroutineScope.launch (errorHandler) {
            val resultList = BreweriesRepository().getBreweriesPhotos(breweriesId)
            galleryRecyclerView.adapter = GalleryAdapter(resultList)
            galleryViewIndicator.setRecyclerView(galleryRecyclerView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}