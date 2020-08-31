package br.com.aguiar.aguiarmovies.ui.view.home.fragments.fantasia

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarmovies.data.utils.Constants
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList
import br.com.aguiar.aguiarmovies.domain.model.movies.MoviesInteractor
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
        launch {
            view?.setProgressVisibility(View.VISIBLE)
            val result = interactor(listOf(Constants.FANTASIA_ID))
            _fantasyMovie.value = result
            view?.setProgressVisibility(View.INVISIBLE)
            emptyState(result.result)
        }
    }

    private fun emptyState(list: List<MovieDetail>) {
        if(list.isEmpty()) view?.emptyState(View.VISIBLE) else view?.emptyState(View.GONE)
    }

    private fun cancelJobs() {
        fantasyJob.cancel()
    }
}