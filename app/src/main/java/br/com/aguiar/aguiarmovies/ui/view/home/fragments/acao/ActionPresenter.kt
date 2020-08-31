package br.com.aguiar.aguiarmovies.ui.view.home.fragments.acao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarmovies.data.utils.Constants.ACAO_ID
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList
import br.com.aguiar.aguiarmovies.domain.model.movies.MoviesInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ActionPresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope, ActionContract.ActionPresenter {

    private var actionJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + actionJob

    private val _actionMovie = MutableLiveData<MovieList>()
    override fun actionMovie(): LiveData<MovieList> = _actionMovie

    override var view: ActionContract.ActionView? = null

    override fun attachView(view: ActionContract.ActionView) {
        this.view = view
    }

    override fun detachView() {
        cancelJobs()
        this.view = null
    }

    override fun fetchMovies() {
       launch {
            val result = interactor(listOf(ACAO_ID))
            _actionMovie.value = result
        }
    }

    private fun cancelJobs() {
        actionJob.cancel()
    }
}