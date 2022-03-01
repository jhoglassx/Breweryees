package com.ciandt.breweryees.ui.userRating

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.breweryees.Model.BreweriesModel
import com.ciandt.breweryees.repository.BreweriesRespository
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import junit.runner.Version.id
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.getScopeId
import java.util.Collections.list

class UserRatingViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel :UserRatingViewModel

    private var repository: BreweriesRespository = mockk()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        viewModel = UserRatingViewModel(repository)
    }

    @After
    fun cleanup(){
        Dispatchers.resetMain()
    }

    @Test
    fun consultUserRating_isReturn_repositoty_getBreweriesUserEvaluations()= runTest{

        coEvery { repository.getBreweriesUserEvaluations("teste@teste.com") } returns fakeListUserRating()

        viewModel.getBreweriesUserRating("teste@teste.com")

        coVerify { repository.getBreweriesUserEvaluations(any()) }
    }

    @Test
    fun userRating_liveData_sucess()= runTest{

        coEvery { repository.getBreweriesUserEvaluations("teste@teste.com") } returns fakeListUserRating()

        viewModel.getBreweriesUserRating("teste@teste.com")

        assertTrue(viewModel.listBreweriesUserRating.value is List<BreweriesModel>)
    }

    @Test
    fun userRating_liveData_listEmpty()= runTest{

        coEvery { repository.getBreweriesUserEvaluations("teste@teste.com") } returns listOf<BreweriesModel>()

        viewModel.getBreweriesUserRating("teste@teste.com")

        assertTrue(viewModel.listBreweriesUserRating.value is List<BreweriesModel>)
    }





    private fun fakeListUserRating() : MutableList<BreweriesModel>{
        val list : MutableList<BreweriesModel> = mutableListOf()

        list.add(0,
            BreweriesModel(
                id = "lazy-horse-brewing-ohiowa",
                name = "Lazy Horse Brewing",
                brewery_type = "brewpub",
                street = "211 Road 20",
                address2 = null,
                address3 = null,
                city = "Ohiowa",
                state = "Nebraska",
                postal_code = "68416-3005",
                country = "United States",
                longitude = null,
                latitude = null,
                website_url = "http://lazyhorsebrewing.com",
                phone = null,
                average = 4.0,
                size_evaluations = 39,
                photos = null
            )
        )
        list.add(1,
            BreweriesModel(
                id = "jaw-brew-milngavie",
                name = "Jaw Brew",
                brewery_type = "micro",
                street = "26 Crossveggate",
                address2 = null,
                address3 = null,
                city = "Milngavie",
                state = null,
                postal_code = "G62 6RA",
                country = "Scotland",
                longitude = 55.9408943,
                latitude = -4.3131808,
                website_url = "https://www.jawbrew.co.uk/",
                phone = null,
                average = 3.8,
                size_evaluations = 14,
                photos = null
            )
        )
        list.add(2,
            BreweriesModel(
                id = "radiant-pig-craft-beers-new-york",
                name = "Radiant Pig Craft Beers",
                brewery_type = "contract",
                street = "122 W 27th St Fl 10",
                address2 = null,
                address3 = null,
                city = "New York",
                state = "New York",
                postal_code = "10001-6227",
                country = "United States",
                longitude = null,
                latitude = null,
                website_url = null,
                phone = null,
                average = 3.9,
                size_evaluations = 7,
                photos = null
            )
        )
        list.add(3,
            BreweriesModel(
                id = "death-avenue-new-york",
                name = "Death Avenue",
                brewery_type = "brewpub",
                street = "315 10th Ave B/W 28th & 29th St.",
                address2 = null,
                address3 = null,
                city = "New York",
                state = "New York",
                postal_code = "10001-1416",
                country = "United States",
                longitude = null,
                latitude = null,
                website_url = "http://www.deathave.com",
                phone = null,
                average = 4.0,
                size_evaluations = 3,
                photos = null
            )
        )
        list.add(4,
            BreweriesModel(
                id = "alphabet-city-brewing-co-new-york",
                name = "Alphabet City Brewing Co",
                brewery_type = "contract",
                street = "96 Avenue C Frnt 4",
                address2 = null,
                address3 = null,
                city = "New York",
                state = "New York",
                postal_code = "10009-7055",
                country = "United States",
                longitude = null,
                latitude = null,
                website_url = "http://www.acbnyc.com",
                phone = null,
                average = 3.6,
                size_evaluations = 49,
                photos = null
            )
        )
        list.add(5,
            BreweriesModel(
                id = "birreria-eataly-new-york",
                name = "Birreria @ Eataly",
                brewery_type = "brewpub",
                street = "200 5th Ave Fl 14",
                address2 = null,
                address3 = null,
                city = "New York",
                state = "New York",
                postal_code = "10010-3302",
                country = "United States",
                longitude = null,
                latitude = null,
                website_url = "http://www.eatalyny.com",
                phone = null,
                average = 3.8,
                size_evaluations = 11,
                photos = null
            )
        )

        return list
    }
}