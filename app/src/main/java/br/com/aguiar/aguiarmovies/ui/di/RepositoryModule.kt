package br.com.aguiar.aguiarmovies.ui.di

import br.com.aguiar.aguiarmovies.data.repository.auth.AuthDataRepository
import br.com.aguiar.aguiarmovies.data.repository.genres.GenresDataRepository
import br.com.aguiar.aguiarmovies.data.repository.imagens.PicassoDataRepository
import br.com.aguiar.aguiarmovies.data.repository.movies.MoviesDataRepository
import br.com.aguiar.aguiarmovies.domain.repository.auth.AuthRepository
import br.com.aguiar.aguiarmovies.domain.repository.genres.GenresRepository
import br.com.aguiar.aguiarmovies.domain.repository.imagens.PicassoRepository
import br.com.aguiar.aguiarmovies.domain.repository.movies.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthDataRepository(get()) as AuthRepository }
    single { PicassoDataRepository() as PicassoRepository }
    single { MoviesDataRepository(get()) as MoviesRepository }
    single { GenresDataRepository(get()) as GenresRepository }
}