package br.com.aguiar.aguiarcubos.ui.view.home.fragments.acao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarcubos.data.utils.Constants.ACAO_ID
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList
import br.com.aguiar.aguiarcubos.domain.model.movies.MoviesInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AcaoPresenter(
    private val interactor: MoviesInteractor
) : CoroutineScope {

    private var actionJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + actionJob

    private val actionMovie = MutableLiveData<MovieList>()
    fun actionMovie(): LiveData<MovieList> = actionMovie

    fun fetchMovies() {
        if (actionMovie.value != null) return

        actionJob = launch {
            val result = interactor(listOf(ACAO_ID))
            actionMovie.value = result
        }
    }

    fun cancelJobs() {
        actionJob.cancel()
    }
}