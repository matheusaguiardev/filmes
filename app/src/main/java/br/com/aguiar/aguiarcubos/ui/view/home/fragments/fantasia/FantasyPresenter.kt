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

class FantasyPresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope, FantasyContract.FantasyPresenter {

    private var fantasyJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + fantasyJob

    private val _fantasyMovie = MutableLiveData<MovieList>()
    override fun fantasyMovie(): LiveData<MovieList> = _fantasyMovie
    override var view: FantasyContract.FantasyView? = null

    override fun attachView(view: FantasyContract.FantasyView) {
        this.view = view
    }

    override fun detachView() {
        cancelJobs()
        view = null
    }

    override fun fetchMovies() {
        if (_fantasyMovie.value != null) return
        fantasyJob = launch {
            val result = interactor(listOf(Constants.FANTASIA_ID))
            _fantasyMovie.value = result
        }
    }

    private fun cancelJobs() {
        fantasyJob.cancel()
    }
}