package br.com.aguiar.aguiarmovies.ui.view.home.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList
import br.com.aguiar.aguiarmovies.domain.model.movies.MoviesInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomePresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope, HomeContract.HomePresenter {

    private var genericMovieJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + genericMovieJob

    override var view: HomeContract.HomeView? = null

    private val genericMovie = MutableLiveData<MovieList>()
    override fun genericMovie(): LiveData<MovieList> = genericMovie

    override fun attachView(view: HomeContract.HomeView) {
       this.view = view
    }

    override fun detachView() {
        cancelJobs()
        view = null
    }

    override fun fetchMovies(url: String) {
        launch {
            val result = interactor.searchMovieByName(url)
            genericMovie.value = result
        }
    }

    private fun cancelJobs() {
        genericMovieJob.cancel()
    }

}