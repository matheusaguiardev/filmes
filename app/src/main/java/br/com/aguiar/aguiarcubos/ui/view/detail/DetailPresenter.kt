package br.com.aguiar.aguiarcubos.ui.view.detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aguiar.aguiarcubos.domain.repository.imagens.PicassoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailPresenter(
    private val imgLoader: PicassoRepository
) : CoroutineScope {

    private var imgLoadedJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + imgLoadedJob

    private val imgLoaded = MutableLiveData<Bitmap>()
    fun imgLoaded(): LiveData<Bitmap> = imgLoaded

    fun loadImage(path: String) {
        launch {
            val img = imgLoader.fetchImageWithPicasso(path)
            imgLoaded.value = img
        }
    }
}