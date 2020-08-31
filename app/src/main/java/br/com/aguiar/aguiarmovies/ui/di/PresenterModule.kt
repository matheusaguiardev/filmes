package br.com.aguiar.aguiarmovies.ui.di

import br.com.aguiar.aguiarmovies.ui.view.detail.DetailContract
import br.com.aguiar.aguiarmovies.ui.view.detail.DetailPresenter
import br.com.aguiar.aguiarmovies.ui.view.home.activity.HomeContract
import br.com.aguiar.aguiarmovies.ui.view.home.activity.HomePresenter
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.acao.ActionContract
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.acao.ActionPresenter
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.drama.DramaContract
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.drama.DramaPresenter
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.fantasia.FantasyContract
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.fantasia.FantasyPresenter
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.ficcao.FictionContract
import br.com.aguiar.aguiarmovies.ui.view.home.fragments.ficcao.FictionPresenter
import org.koin.dsl.module

val presenterModule = module {
    single { HomePresenter(get()) as HomeContract.HomePresenter }
    single { ActionPresenter(get()) as ActionContract.ActionPresenter}
    single { DramaPresenter(get()) as DramaContract.DramaPresenter}
    single { FantasyPresenter(get()) as FantasyContract.FantasyPresenter}
    single { FictionPresenter(get()) as FictionContract.FictionPresenter }
    single { DetailPresenter(get()) as DetailContract.ContractPresenter }
}