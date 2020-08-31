package br.com.aguiar.aguiarmovies.ui.view.home.activity

import androidx.lifecycle.LiveData
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList

interface HomeContract {

    interface HomeView {
        val presenter: HomePresenter
    }

    interface HomePresenter {
        var view: HomeView?
        fun attachView(view: HomeView)
        fun detachView()
        fun fetchMovies(url: String)
        fun genericMovie(): LiveData<MovieList>
    }

}