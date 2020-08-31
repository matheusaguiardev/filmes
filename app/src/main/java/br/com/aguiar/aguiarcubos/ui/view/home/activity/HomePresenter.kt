package br.com.aguiar.aguiarcubos.ui.view.home.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList
import br.com.aguiar.aguiarcubos.domain.model.movies.MoviesInteractor
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
        view = null
    }

    override fun fetchMovies(url: String) {
        genericMovieJob = launch {
            val result = interactor.searchMovieByName(url)
            genericMovie.value = result
        }
    }

    override fun cancelJobs() {
        genericMovieJob.cancel()
    }

}