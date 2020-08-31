package br.com.aguiar.aguiarmovies.ui.view.home.fragments.ficcao

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
            view?.setProgressVisibility(View.VISIBLE)
            val result = interactor(listOf(Constants.FICCAO_ID))
            _fictionMovie.value = result
            view?.setProgressVisibility(View.INVISIBLE)
            emptyState(result.result)
        }
    }

    private fun emptyState(list: List<MovieDetail>) {
        if(list.isEmpty()) view?.emptyState(View.VISIBLE) else view?.emptyState(View.GONE)
    }

    private fun cancelJobs() {
        fictionJob.cancel()
    }
}