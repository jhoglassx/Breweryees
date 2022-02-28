package com.ciandt.breweryees.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ciandt.breweryees.R
import com.ciandt.breweryees.databinding.ActivityUserRatingBinding
import com.ciandt.breweryees.databinding.FragmentUserRatingEmailBinding
import com.ciandt.breweryees.ui.userRating.UserRatingEmailFragment
import com.ciandt.breweryees.ui.userRating.UserRatingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserRatingActivity : AppCompatActivity() {

    private lateinit var _binding :ActivityUserRatingBinding
    private val binding get() = _binding

    private lateinit var _bindingEmail :FragmentUserRatingEmailBinding
    private val bindingEmail get() = _bindingEmail

    private val viewModel: UserRatingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserRatingBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.myToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()

        val email = intent.getStringExtra("email").toString()

        if(email == "null"){
            fragmentManagerCommit()
        }

    }

    private fun fragmentManagerCommit() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragment: Fragment = UserRatingEmailFragment()
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
    }
}