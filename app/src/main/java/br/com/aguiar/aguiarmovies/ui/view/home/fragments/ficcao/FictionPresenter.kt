package br.com.aguiar.aguiarmovies.ui.view.home.fragments.ficcao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarmovies.data.utils.Constants
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList
import br.com.aguiar.aguiarmovies.domain.model.movies.MoviesInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FictionPresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope, FictionContract.FictionPresenter {

    private var fictionJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + fictionJob

    private val _fictionMovie = MutableLiveData<MovieList>()
    override fun fictionMovie(): LiveData<MovieList> = _fictionMovie
    override var view: FictionContract.FictionView? = null

    override fun attachView(view: FictionContract.FictionView) {
        this.view = view
    }

    override fun detachView() {
        cancelJobs()
        this.view = null
    }

    override fun fetchMovies() {
        launch {
            val result = interactor(listOf(Constants.FICCAO_ID))
            _fictionMovie.value = result
        }
    }

    private fun cancelJobs() {
        fictionJob.cancel()
    }
}