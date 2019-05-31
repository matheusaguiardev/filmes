package br.com.aguiar.aguiarcubos.ui.view.home.fragments.fantasia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarcubos.data.utils.Constants
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList
import br.com.aguiar.aguiarcubos.domain.model.movies.MoviesInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FantasiaPresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope {

    private var fantasyJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + fantasyJob

    private val fantasyMovie = MutableLiveData<MovieList>()
    fun fantasyMovie(): LiveData<MovieList> = fantasyMovie

    fun fetchMovies() {
        fantasyJob = launch {
            val result = interactor(listOf(Constants.FANTASIA_ID))
            fantasyMovie.value = result
        }
    }

    fun cancelJobs() {
        fantasyJob.cancel()
    }
}