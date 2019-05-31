package br.com.aguiar.aguiarcubos.ui.viewholder

import android.graphics.Bitmap
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.aguiar.aguiarcubos.R
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarcubos.domain.repository.imagens.PicassoRepository
import br.com.aguiar.aguiarcubos.ui.extension.toGone
import br.com.aguiar.aguiarcubos.ui.extension.toVisible
import kotlinx.android.synthetic.main.viewholder_movie.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieViewHolder(
    itemView: View,
    private val imgProvider: PicassoRepository
) : RecyclerView.ViewHolder(itemView), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    fun bind(content: MovieDetail, callback: (MovieDetail, Bitmap?) -> Unit) {
        var img: Bitmap? = null
        with(itemView) {
            titleMovieTxt.text = content.title
            launch {
                content.posterPath?.let {
                    progress.toVisible()
                    img = imgProvider.fetchImageWithPicasso(it)
                    logoFilmeImg.setImageBitmap(img)
                    progress.toGone()
                } ?: run {
                    logoFilmeImg.setImageResource(R.mipmap.ic_launcher_round)
                    progress.toGone()
                }
            }
        }
        itemView.setOnClickListener { callback.invoke(content, img) }
    }
}