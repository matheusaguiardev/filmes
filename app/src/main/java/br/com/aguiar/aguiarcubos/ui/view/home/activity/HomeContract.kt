package br.com.aguiar.aguiarcubos.ui.view.home.activity

import androidx.lifecycle.LiveData
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList

interface HomeContract {

    interface HomeView {
        var presenter: HomePresenter
    }

    interface HomePresenter {
        var view: HomeView?
        fun attachView(view: HomeView)
        fun detachView()
        fun fetchMovies(url: String)
        fun genericMovie(): LiveData<MovieList>
        fun cancelJobs()
    }

}