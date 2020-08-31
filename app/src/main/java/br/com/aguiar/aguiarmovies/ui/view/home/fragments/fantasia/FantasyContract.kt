package br.com.aguiar.aguiarmovies.ui.view.home.fragments.fantasia

import androidx.lifecycle.LiveData
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList

interface FantasyContract {

    interface FantasyView {
        val presenter: FantasyPresenter
    }

    interface FantasyPresenter {
        var view: FantasyView?
        fun attachView(view: FantasyView)
        fun detachView()
        fun fetchMovies()
        fun fantasyMovie(): LiveData<MovieList>
    }

}