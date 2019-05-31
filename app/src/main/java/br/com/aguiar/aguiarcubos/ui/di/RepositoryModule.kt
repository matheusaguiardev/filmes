package br.com.aguiar.aguiarcubos.ui.di

import br.com.aguiar.aguiarcubos.data.repository.auth.AuthDataRepository
import br.com.aguiar.aguiarcubos.data.repository.genres.GenresDataRepository
import br.com.aguiar.aguiarcubos.data.repository.imagens.PicassoDataRepository
import br.com.aguiar.aguiarcubos.data.repository.movies.MoviesDataRepository
import br.com.aguiar.aguiarcubos.domain.repository.auth.AuthRepository
import br.com.aguiar.aguiarcubos.domain.repository.genres.GenresRepository
import br.com.aguiar.aguiarcubos.domain.repository.imagens.PicassoRepository
import br.com.aguiar.aguiarcubos.domain.repository.movies.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthDataRepository(get()) as AuthRepository }
    single { PicassoDataRepository() as PicassoRepository }
    single { MoviesDataRepository(get()) as MoviesRepository }
    single { GenresDataRepository(get()) as GenresRepository }
}