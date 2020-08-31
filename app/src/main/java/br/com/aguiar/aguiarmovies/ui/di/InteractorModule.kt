package br.com.aguiar.aguiarmovies.ui.di

import br.com.aguiar.aguiarmovies.domain.model.auth.AuthInteractor
import br.com.aguiar.aguiarmovies.domain.model.genres.GenresInteractor
import br.com.aguiar.aguiarmovies.domain.model.movies.MoviesInteractor
import org.koin.dsl.module

val interactorModule = module {
    single { AuthInteractor(get()) }
    single { GenresInteractor(get()) }
    single { MoviesInteractor(get()) }
}