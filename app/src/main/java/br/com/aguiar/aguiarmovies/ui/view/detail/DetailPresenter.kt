package br.com.aguiar.aguiarmovies.ui.view.detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarmovies.domain.repository.imagens.PicassoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailPresenter(
    private val imgLoader: PicassoRepository
) : CoroutineScope, DetailContract.ContractPresenter {

    private var imgLoadedJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + imgLoadedJob

    private val imgLoaded = MutableLiveData<Bitmap>()
    override var view: DetailContract.ContractView? = null

    override fun attachView(view: DetailContract.ContractView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun imgLoaded(): LiveData<Bitmap> = imgLoaded

    override fun loadImage(path: String) {
        launch {
            val img = imgLoader.fetchImageWithPicasso(path)
            imgLoaded.value = img
        }
    }
}