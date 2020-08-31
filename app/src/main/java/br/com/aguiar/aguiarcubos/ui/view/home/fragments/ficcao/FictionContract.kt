package br.com.aguiar.aguiarcubos.ui.view.home.fragments.ficcao

import androidx.lifecycle.LiveData
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList

interface FictionContract {

    interface FictionView {
        val presenter: FictionPresenter
    }

    interface FictionPresenter {
        var view: FictionView?
        fun attachView(view: FictionView)
        fun detachView()
        fun fetchMovies()
        fun fictionMovie(): LiveData<MovieList>
    }

}