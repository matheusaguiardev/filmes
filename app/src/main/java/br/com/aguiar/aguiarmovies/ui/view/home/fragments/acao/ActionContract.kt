package br.com.aguiar.aguiarmovies.ui.view.home.fragments.acao

import androidx.lifecycle.LiveData
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList

interface ActionContract {

    interface ActionView {
        val presenter: ActionPresenter
    }

    interface ActionPresenter {
        var view: ActionView?
        fun attachView(view: ActionView)
        fun detachView()
        fun fetchMovies()
        fun actionMovie(): LiveData<MovieList>
    }

}