package br.com.aguiar.aguiarcubos.ui.di

import br.com.aguiar.aguiarcubos.domain.model.auth.AuthInteractor
import br.com.aguiar.aguiarcubos.domain.model.genres.GenresInteractor
import br.com.aguiar.aguiarcubos.domain.model.movies.MoviesInteractor
import org.koin.dsl.module

val interactorModule = module {
    single { AuthInteractor(get()) }
    single { GenresInteractor(get()) }
    single { MoviesInteractor(get()) }
}