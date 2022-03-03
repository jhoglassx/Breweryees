package com.ciandt.breweryees.ui.userRating

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.ciandt.breweryees.R
import com.ciandt.breweryees.databinding.FragmentUserRatingEmailBinding
import com.ciandt.breweryees.ui.UserRatingActivity
import com.ciandt.breweryees.utils.Email
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserRatingEmailFragment : Fragment() {

    private lateinit var _binding : FragmentUserRatingEmailBinding
    private val binding get() = _binding

    private val viewModel: UserRatingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

        _binding= FragmentUserRatingEmailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        var email = sharedPref.getString("breweriesEmail",null)

        binding.txtEmail.setText(email)
        if(!email.isNullOrEmpty()){
            binding.btnConfirm.isEnabled = Email.validEmail(email)
        }

        binding.txtEmail.addTextChangedListener{
            email = binding.txtEmail.text.toString()
            binding.btnConfirm.isEnabled = Email.validEmail(email!!)
        }

        binding.btnConfirm.setOnClickListener{
            saveEmail(email!!)
            btnConfirmOnClick(email!!)
        }
    }

    private fun saveEmail(email: String){
        if(binding.ckbEmailRemember.isChecked){
            val sharedPref  = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString("breweriesEmail", email)
                apply()
            }
        }
    }

    private fun btnConfirmOnClick(email: String){
        viewModel.listBreweriesUserRating.observe(viewLifecycleOwner){ list ->
            if(list.isNotEmpty()){
                fragmentManagerCommit(email)
            }else{
                fragmentManagerError(email)
            }
        }
        viewModel.getBreweriesUserRating(email)
    }

    private fun fragmentManagerError(email: String) {
        val fragmentManager: FragmentManager = (activity as FragmentActivity).supportFragmentManager
        val fragment: Fragment = UserRatingErrorFragment()
        val bundle = Bundle()
        bundle.putString("email", email)
        fragment.arguments = bundle
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
    }

    private fun fragmentManagerCommit(email: String) {
        val fragmentManager: FragmentManager = (activity as FragmentActivity).supportFragmentManager
        val fragment: Fragment = UserRatingListFragment()
        val bundle = Bundle()
        bundle.putString("email", email)
        fragment.arguments = bundle
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
    }
}