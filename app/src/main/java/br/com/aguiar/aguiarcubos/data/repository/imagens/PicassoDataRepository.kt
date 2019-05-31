package br.com.aguiar.aguiarcubos.data.repository.imagens

import br.com.aguiar.aguiarcubos.data.utils.Constants.BASE_URL_IMG
import br.com.aguiar.aguiarcubos.domain.repository.imagens.PicassoRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class PicassoDataRepository : PicassoRepository {

    override suspend fun fetchImageWithPicasso(
        url: String
    ) = withContext(Dispatchers.IO) {
        async { downloadImage(url) }
    }.await()

    private fun downloadImage(url: String) = Picasso.get()
        .load(BASE_URL_IMG + url)
        .get()
}