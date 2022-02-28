package com.ciandt.breweryees.ui.userRating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciandt.breweryees.Model.BreweriesModel
import com.ciandt.breweryees.repository.BreweriesRespository
import kotlinx.coroutines.launch

class UserRatingViewModel(private var repository:BreweriesRespository): ViewModel() {

    private val _listBreweriesUserRating = MutableLiveData<List<BreweriesModel>>()
    val listBreweriesUserRating : LiveData<List<BreweriesModel>> = _listBreweriesUserRating

    fun getBreweriesUserRating(email: String){
        viewModelScope.launch {
            _listBreweriesUserRating.value = repository.getBreweriesUserEvaluations(email)
        }
    }
}