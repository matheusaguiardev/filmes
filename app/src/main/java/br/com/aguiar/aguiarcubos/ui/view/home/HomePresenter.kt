package br.com.aguiar.aguiarcubos.ui.view.home

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

) : CoroutineScope {

    private var genericMovieJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + genericMovieJob

    private val genericMovie = MutableLiveData<MovieList>()
    fun genericMovie(): LiveData<MovieList> = genericMovie

    fun fetchMovies(name: String) {
        genericMovieJob = launch {
            val result = interactor.searchMovieByName(name)
            genericMovie.value = result
        }
    }

    fun cancelJobs() {
        genericMovieJob.cancel()
    }

}