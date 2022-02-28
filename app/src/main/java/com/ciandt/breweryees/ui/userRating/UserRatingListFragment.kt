package com.ciandt.breweryees.ui.userRating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.ciandt.breweryees.R
import com.ciandt.breweryees.databinding.FragmentRatingSortBinding
import com.ciandt.breweryees.databinding.FragmentUserRatingListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserRatingListFragment : Fragment() {

    private lateinit var _binding : FragmentUserRatingListBinding
    private val binding get() = _binding

    private lateinit var _bindingSort : FragmentRatingSortBinding
    private val bindingSort get() = _bindingSort

    private var sortby : Int? =null

    private val viewModel: UserRatingViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View{
        _binding = FragmentUserRatingListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments

        binding.btnShort.setOnClickListener {
            showDialogSort(bundle)
        }
        load(bundle,1)
    }

    private fun load(bundle: Bundle?, sortby: Int) {
        viewModel.listBreweriesUserRating.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                binding.recyclerRatingList.adapter = RatingListAdapter(
                    when (sortby){
                        2 -> list.sortedByDescending { it.average }
                        else -> list.sortedBy { it.name }
                    }
                )
                binding.txtQtd.text = "${list.size} resultados"
            }
        }

        bundle?.getString("email").toString().apply {
            viewModel.getBreweriesUserRating(this)
        }
    }

    private fun showDialogSort(bundle: Bundle?) {
        val dialog = dialog()
        _bindingSort = FragmentRatingSortBinding.inflate(layoutInflater)

        dialog.setContentView(bindingSort.root)
        dialog.show()

        bindingSort.ckbSortAz.setOnClickListener {
            dialog.dismiss()
            sortby = 1
            load(bundle,sortby!!)
        }

        bindingSort.ckbSortNota.setOnClickListener {
            dialog.dismiss()
            sortby = 2
            load(bundle,sortby!!)
        }
    }

    private fun dialog(): BottomSheetDialog {
        return BottomSheetDialog(layoutInflater.context, R.style.BottomSheetDialog).apply {
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }

}