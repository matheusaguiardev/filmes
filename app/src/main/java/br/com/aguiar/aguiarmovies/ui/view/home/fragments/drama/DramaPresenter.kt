package br.com.aguiar.aguiarmovies.ui.view.home.fragments.drama

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

class DramaPresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope, DramaContract.DramaPresenter {

    private var dramaJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + dramaJob

    private val dramaMovie = MutableLiveData<MovieList>()

    override fun dramaMovie(): LiveData<MovieList> = dramaMovie
    override var view: DramaContract.DramaView? = null

    override fun attachView(view: DramaContract.DramaView) {
        this.view = view
    }

    override fun detachView() {
        cancelJobs()
        view = null
    }

    override fun fetchMovies() {
        launch {
            view?.setProgressVisibility(View.VISIBLE)
            val result = interactor(listOf(Constants.DRAMA_ID))
            dramaMovie.value = result
            view?.setProgressVisibility(View.INVISIBLE)
            emptyState(result.result)
        }
    }

    private fun emptyState(list: List<MovieDetail>) {
        if(list.isEmpty()) view?.emptyState(View.VISIBLE) else view?.emptyState(View.GONE)
    }

    private fun cancelJobs() {
        dramaJob.cancel()
    }
}