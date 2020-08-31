package br.com.aguiar.aguiarcubos.ui.di

import br.com.aguiar.aguiarcubos.ui.view.detail.DetailPresenter
import br.com.aguiar.aguiarcubos.ui.view.home.activity.HomePresenter
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.acao.AcaoPresenter
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.drama.DramaPresenter
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.fantasia.FantasiaPresenter
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.ficcao.FiccaoPresenter
import org.koin.dsl.module


val presenterModule = module {
    single { AcaoPresenter(get()) }
    single { DramaPresenter(get()) }
    single { FantasiaPresenter(get()) }
    single { FiccaoPresenter(get()) }
    single { DetailPresenter(get()) }
}