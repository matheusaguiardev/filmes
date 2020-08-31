package br.com.aguiar.aguiarmovies.ui.view.home.fragments.drama

import androidx.lifecycle.LiveData
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList

interface DramaContract {

    interface DramaView {
        val presenter: DramaPresenter
    }

    interface DramaPresenter {
        var view: DramaView?
        fun attachView(view: DramaView)
        fun detachView()
        fun fetchMovies()
        fun dramaMovie(): LiveData<MovieList>
    }

}