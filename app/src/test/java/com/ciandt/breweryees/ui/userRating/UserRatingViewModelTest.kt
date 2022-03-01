package com.ciandt.breweryees.ui.userRating

import com.ciandt.breweryees.Model.BreweriesModel
import com.ciandt.breweryees.repository.BreweriesRepositoryImp
import com.ciandt.breweryees.repository.BreweriesRespository
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserRatingViewModelTest {

    @get:Rule


    private lateinit var viewModel :UserRatingViewModel

    private var repository = mockk<BreweriesRespository>()

    @Before
    fun setup(){
        viewModel = UserRatingViewModel(repository)
    }

    @Test
    fun consultUserRating()= runTest{
        viewModel.getBreweriesUserRating("teste@teste.com")
    }


}