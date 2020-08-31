package br.com.aguiar.aguiarmovies.ui.view.detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData

interface DetailContract {

    interface ContractView {
        val presenter: ContractPresenter
    }

    interface ContractPresenter {
        var view: ContractView?
        fun attachView(view: ContractView)
        fun detachView()
        fun imgLoaded(): LiveData<Bitmap>
        fun loadImage(path: String)
    }

}