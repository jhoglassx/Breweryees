package com.ciandt.breweryees.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ciandt.breweryees.R
import androidx.appcompat.widget.SearchView
import com.ciandt.breweryees.databinding.ActivityMainBinding
import com.ciandt.breweryees.ui.main.ResultFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    //private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_BreweryBees)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater, null, false)

        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchBar.clearFocus()
                viewSearch(query!!)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
//                binding.searchBar.clearFocus()
//                viewSearch(newText!!)
                return false
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        var menuInflater : MenuInflater = menuInflater

        menuInflater.inflate(R.menu.main_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val res_id = item.itemId
        if(res_id == R.id.action_rating){
            Toast.makeText(this,"Rating Option",Toast.LENGTH_LONG).show()
        }else if(res_id == R.id.action_favoritos){
            Toast.makeText(this,"Favoritos Option",Toast.LENGTH_LONG).show()
        }

        return true
    }

    fun viewSearch(search:String){
        val fragment = ResultFragment()
        val bundle = Bundle()
        bundle.putString("searchCity",search)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragment,fragment)
        fragment.arguments = bundle
        transaction.commit()
    }
}