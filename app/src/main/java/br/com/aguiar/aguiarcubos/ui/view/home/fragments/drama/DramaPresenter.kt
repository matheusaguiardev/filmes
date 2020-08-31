package br.com.aguiar.aguiarcubos.ui.view.home.fragments.drama

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
        if (dramaMovie.value != null) return
        dramaJob = launch {
            val result = interactor(listOf(Constants.DRAMA_ID))
            dramaMovie.value = result
        }
    }

    private fun cancelJobs() {
        dramaJob.cancel()
    }
}