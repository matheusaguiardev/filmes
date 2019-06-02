package br.com.aguiar.aguiarcubos.ui.view.home.fragments.ficcao

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

class FiccaoPresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope {

    private var fictionJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + fictionJob

    private val fictionMovie = MutableLiveData<MovieList>()
    fun fictionMovie(): LiveData<MovieList> = fictionMovie

    fun fetchMovies() {
        if (fictionMovie.value != null) return
        fictionJob = launch {
            val result = interactor(listOf(Constants.FICCAO_ID))
            fictionMovie.value = result
        }
    }

    fun cancelJobs() {
        fictionJob.cancel()
    }
}