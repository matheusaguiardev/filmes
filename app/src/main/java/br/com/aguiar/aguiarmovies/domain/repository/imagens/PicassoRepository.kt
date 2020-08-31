package br.com.aguiar.aguiarmovies.domain.repository.imagens

import android.graphics.Bitmap

interface PicassoRepository {

    suspend fun fetchImageWithPicasso(url: String): Bitmap

}